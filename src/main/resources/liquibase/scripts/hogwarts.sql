-- liquibase formatted sql

-- changeset dnikulin:1
CREATE INDEX student_name_index ON student(name)

-- changeset dnikulin:2
CREATE INDEX faculty_name_color_index ON faculty(name, color)