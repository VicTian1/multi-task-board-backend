
USE `task_tracker`;

DROP TABLE IF EXISTS `label`;

CREATE TABLE `label` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO `label` (`name`,`type`)
VALUES
("📚 Study","study"),
("💼 Work","work"),
("🏠 Life","life"),
("✈️ Travel","travel"),
("🏃 Health","health"),
("💰 Finance","finance"),
("📌 General","general")
