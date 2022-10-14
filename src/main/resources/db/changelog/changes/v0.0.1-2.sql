--liquibase formatted sql
--changeset Nicholas-Dietz:c2

/* Create eisenhauer task types */
INSERT INTO timate.TASK_EISENHAUER_TYPE(type) VALUES ('A');
INSERT INTO timate.TASK_EISENHAUER_TYPE(type) VALUES ('B');
INSERT INTO timate.TASK_EISENHAUER_TYPE(type) VALUES ('C');
INSERT INTO timate.TASK_EISENHAUER_TYPE(type) VALUES ('D');

/* Create task impacts */
INSERT INTO timate.TASK_IMPACT(impact, description) VALUES ('1', 'High impact');
INSERT INTO timate.TASK_IMPACT(impact, description) VALUES ('2', 'Medium impact');
INSERT INTO timate.TASK_IMPACT(impact, description) VALUES ('3', 'Low impact');
INSERT INTO timate.TASK_IMPACT(impact, description) VALUES ('4', 'Very low impact');

/* Create task urgency */
INSERT INTO timate.TASK_URGENCY(urgency, description) VALUES ('1', 'High urgency');
INSERT INTO timate.TASK_URGENCY(urgency, description) VALUES ('2', 'Medium urgency');
INSERT INTO timate.TASK_URGENCY(urgency, description) VALUES ('3', 'Low urgency');
INSERT INTO timate.TASK_URGENCY(urgency, description) VALUES ('4', 'Very low urgency');