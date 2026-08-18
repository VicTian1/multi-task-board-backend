USE `task_tracker`;

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` TEXT DEFAULT NULL,
  `label` varchar(45) DEFAULT NULL,
  `due_date` DATE DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_task_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


INSERT INTO `task` (`user_id`, `title`, `description`, `label`, `due_date`, `status`)
VALUES
(1, 'Learn React Props', 'Complete Module 2 of the Scrimba React course and practice using props in reusable components.', 'study', '2026-07-01', 'TODO'),
(1, 'Plan Portfolio Project', 'Outline the main features and page structure for the personal portfolio website.', 'work', '2026-07-18', 'DONE'),
(1, 'Review Java Collections', 'Review List, Set, and Map in Java before continuing backend development.', 'life', '2026-07-20', 'TODO'),
(1, 'Build Kanban Board UI', 'Finish the static UI layout including columns, cards, and responsive spacing.', 'travel', '2026-07-16', 'TODO'),
(2, 'Practice LeetCode Arrays', 'Solve three array problems and review different solution approaches.', 'health', '2026-07-17', 'DOING'),
(2, 'Setup Vite Project', 'Create the React project using Vite and organize the initial folder structure.', 'finance', '2026-08-10', 'DOING'),
(1, 'Complete Navbar', 'Implement the fixed navigation bar with search input and add task button.', 'general', '2026-08-12', 'DONE'),
(2, 'Design Task Card', 'Finish the reusable task card layout and hover animation.', 'travel', '2026-08-31', 'DOING');
