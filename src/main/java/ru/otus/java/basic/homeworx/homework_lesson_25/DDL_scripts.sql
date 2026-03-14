-- создание БД
CREATE DATABASE quiz;

-- создание таблицы с тестами (tests).
-- name (название теста): max количество символов = 100, значения д.б. уникальны, не null, не пустое
CREATE TABLE tests
(
id SERIAL PRIMARY KEY,
name VARCHAR(100) UNIQUE NOT NULL
CHECK(name != '')
);

-- создание таблицы с вопросами (questions).
-- test_id - id теста (foreign key), к которому относится вопрос, поле д.б. не null, при удалении теста, удаляется относящийся к нему вопрос
-- question (содержание вопроса): поле должно не быть пустым. Количество символов, составляющих вопрос не ограничено
CREATE TABLE questions
(
id SERIAL PRIMARY KEY,
test_id INTEGER NOT NULL REFERENCES tests(id) ON DELETE CASCADE,
question TEXT CHECK(question != '')
);

-- создание таблицы с ответами (answers).
-- question_id - id вопроса (foreign key), к которому относится ответ, поле д.б. не null, при удалении теста, удаляется относящийся к нему вопрос
-- answer (содержание ответа): поле должно не быть пустым. Количество символов, составляющих ответ не ограничено
-- is_correct (флаг правильности ответа): TRUE если ответ верный, FALSE - если нет
CREATE TABLE answers
(
id SERIAL PRIMARY KEY,
question_id INTEGER NOT NULL REFERENCES questions(id) ON DELETE CASCADE,
answer TEXT NOT NULL CHECK(answer != ''),
is_correct BOOLEAN DEFAULT FALSE
);

-- создание функции для проверки соответствия ответов заданным критериям (д.б. от 2 до 5 ответов; должен быть 1 верный ответ)
CREATE OR REPLACE FUNCTION answers_count_validation()
RETURNS TRIGGER AS $$
DECLARE
	current_question_id INTEGER;   -- id проверяемого вопроса
	answers_count INTEGER;         -- общее количество ответов
	correct_answers_count INTEGER; -- общее количество верных ответов
BEGIN
	-- определение вопроса, в отношении которого осуществляется проверка (в зависимости от операции)
	IF TG_OP = 'DELETE' THEN
		current_question_id = OLD.question_id;
	ELSE
		current_question_id = NEW.question_id;
	END IF;

	-- запрещен перенос ответа между вопросами
	IF TG_OP = 'UPDATE' AND OLD.question_id != NEW.question_id THEN
		RAISE EXCEPTION 'Запрещена смена id вопроса, к которому относится ответ';
	END IF;

	-- подсчет общего количества ответов для данного вопроса (вопросы, в отношении которых осуществляется проверка уже учтены в транзакции)
	SELECT COUNT(*) INTO answers_count FROM answers WHERE question_id = current_question_id;

	-- проверка общего количества ответов (должно быть от 2 до 5)
	IF  NOT (answers_count BETWEEN 2 AND 5) THEN
		RAISE EXCEPTION 'Количество ответов должно быть от 2 до 5, а с учетом изменения будет %',
			answers_count;
	END IF;

	-- подсчет количества правильных ответов для этого вопроса
	SELECT COUNT(*) INTO correct_answers_count FROM answers
	WHERE question_id = current_question_id AND is_correct = true;

	-- проверка - должен быть ровно один правильный ответ
	IF correct_answers_count != 1 THEN
		RAISE EXCEPTION 'Должен быть 1 правильный ответ, представлено %',
			correct_answers_count;
	END IF;

	-- возвращаемое значение
	RETURN COALESCE(NEW, OLD);

END;
$$ LANGUAGE plpgsql;

-- триггеры на все операции (вставка, обновление, удаление), вызывающие функцию .
-- проверка осуществляется по итоговому состоянию только при коммите.
-- до коммита проверка не осуществляется, т.к. иначе при добавлении первого ответа будет ошибка, поскольку количество ответов будет меньше 2
CREATE CONSTRAINT TRIGGER trigger_answers_insert
AFTER INSERT ON answers
DEFERRABLE INITIALLY DEFERRED
FOR EACH ROW
EXECUTE FUNCTION answers_count_validation();

CREATE CONSTRAINT TRIGGER trigger_answers_delete
AFTER DELETE ON answers
DEFERRABLE INITIALLY DEFERRED
FOR EACH ROW
EXECUTE FUNCTION answers_count_validation();

CREATE CONSTRAINT TRIGGER trigger_answers_update
AFTER UPDATE ON answers
DEFERRABLE INITIALLY DEFERRED
FOR EACH ROW
EXECUTE FUNCTION answers_count_validation();