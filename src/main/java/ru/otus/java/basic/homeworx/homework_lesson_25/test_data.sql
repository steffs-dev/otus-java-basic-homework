-- наполнение таблицы tests
BEGIN;
INSERT INTO tests VALUES (1, 'Capitals');
INSERT INTO tests VALUES (2, 'Maths');
COMMIT;

SELECT * from tests;

-- наполнение таблицы questions
BEGIN;
INSERT INTO questions (test_id, question)
VALUES
    (1, 'Choose the capital of Russia?'),
    (1, 'What is the capital of USA?'),
    (1, 'Which one is the capital of France?'),
    (2, '7 * 7 - 49 =');
COMMIT;

SELECT * FROM questions;

-- наполнение таблицы answers
BEGIN;
INSERT INTO answers (question_id, answer, is_correct)
VALUES (1, 'Moscow', TRUE);
INSERT INTO answers (question_id, answer, is_correct)
VALUES (1, 'Helsinki', FALSE);
INSERT INTO answers (question_id, answer, is_correct)
VALUES (1, 'Tokio', FALSE);
COMMIT;

BEGIN;
INSERT INTO answers (question_id, answer, is_correct)
VALUES (2, 'New York', FALSE);
INSERT INTO answers (question_id, answer, is_correct)
VALUES (2, 'Washington', TRUE);
INSERT INTO answers (question_id, answer, is_correct)
VALUES (1, 'Berlin', FALSE);
COMMIT;

BEGIN;
INSERT INTO answers (question_id, answer, is_correct)
VALUES (4, '10', FALSE);
INSERT INTO answers (question_id, answer, is_correct)
VALUES (4, '0', TRUE);
COMMIT;

SELECT * FROM answers;

--ошибка с удалением, когда остается менее 2 ответов к вопросам
BEGIN;
DELETE FROM answers WHERE answer LIKE 'New York';
COMMIT;

--ошибка с добавлением менее 2 ответов к вопросу
BEGIN;
INSERT INTO answers (question_id, answer, is_correct)
VALUES (3, 'Paris', TRUE);
COMMIT;

--ошибка с добавлением более 5 ответов к вопросу
BEGIN;
INSERT INTO answers (question_id, answer, is_correct)
VALUES (1, 'London', FALSE);
INSERT INTO answers (question_id, answer, is_correct)
VALUES (1, 'Madrid', FALSE);
COMMIT;

--ошибка с отсутствием правильного ответа на вопрос
BEGIN;
INSERT INTO answers (question_id, answer, is_correct)
VALUES (3, 'Warsaw', FALSE);
INSERT INTO answers (question_id, answer, is_correct)
VALUES (3, 'Zurich', FALSE);
COMMIT;

--ошибка с наличием более, чем 1 правильного ответа на вопрос
BEGIN;
UPDATE answers SET is_correct = true WHERE id = 6;
COMMIT;

--ошибка в связи со сменой id вопроса
BEGIN;
UPDATE answers SET question_id = 2 WHERE id = 6;
COMMIT;
