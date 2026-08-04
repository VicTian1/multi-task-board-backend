
-- create database
CREATE DATABASE  IF NOT EXISTS `task_tracker`;
USE `task_tracker`;

-- Drop user first if they exist
DROP USER if exists 'multitaskboard'@'%' ;

-- Now create user with prop privileges
CREATE USER 'multitaskboard'@'%' IDENTIFIED BY 'multitaskboard';

GRANT ALL PRIVILEGES ON task_tracker. * TO 'multitaskboard'@'%';