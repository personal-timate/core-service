--liquibase formatted sql
--changeset Nicholas-Dietz:c2

/* Create task */
INSERT INTO timate.TASKS(name, grouped, TASK_EISENHAUER_TYPE_id, TASK_URGENCY_id, TASK_IMPACT_id) VALUES ('Task A', true, 1, 1, 1);
INSERT INTO timate.TASKS(name, grouped, TASK_EISENHAUER_TYPE_id, TASK_URGENCY_id, TASK_IMPACT_id) VALUES ('Task B', true, 2, 2, 2);
INSERT INTO timate.TASKS(name, grouped, TASK_EISENHAUER_TYPE_id, TASK_URGENCY_id, TASK_IMPACT_id) VALUES ('Task C', true, 3, 3, 3);
INSERT INTO timate.TASKS(name, grouped, TASK_EISENHAUER_TYPE_id, TASK_URGENCY_id, TASK_IMPACT_id) VALUES ('Task D', true, 4, 4, 4);
INSERT INTO timate.TASKS(name, grouped, TASK_EISENHAUER_TYPE_id, TASK_URGENCY_id, TASK_IMPACT_id) VALUES ('Task E', false, 1, 2, 3);

/* Create Task Group */
INSERT INTO timate.TASK_GROUP(main, subtask) VALUES (1, 2);
INSERT INTO timate.TASK_GROUP(main, subtask) VALUES (1, 3);
INSERT INTO timate.TASK_GROUP(main, subtask) VALUES (1, 4);