-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2020 at 12:48 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hazardreportdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `actions`
--

CREATE TABLE `actions` (
  `id` int(11) NOT NULL,
  `description` text NOT NULL,
  `result` text DEFAULT NULL,
  `risk` text DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp(),
  `action_type` int(11) NOT NULL,
  `report_progress` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `actions`
--

INSERT INTO `actions` (`id`, `description`, `result`, `risk`, `date`, `action_type`, `report_progress`) VALUES
(12, '<p><strong style=\"margin: 0px; padding: 0px; font-family: \'Open Sans\', Arial, sans-serif; font-size: 14px; text-align: justify; background-color: #ffffff;\">Lorem Ipsum</strong><span style=\"font-family: \'Open Sans\', Arial, sans-serif; font-size: 14px; text-align: justify; background-color: #ffffff;\">&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</span></p>', '<p><strong style=\"margin: 0px; padding: 0px; font-family: \'Open Sans\', Arial, sans-serif; font-size: 14px; text-align: justify; background-color: #ffffff;\">Lorem Ipsum</strong><span style=\"font-family: \'Open Sans\', Arial, sans-serif; font-size: 14px; text-align: justify; background-color: #ffffff;\">&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</span></p>', '<p><strong style=\"margin: 0px; padding: 0px; font-family: \'Open Sans\', Arial, sans-serif; font-size: 14px; text-align: justify; background-color: #ffffff;\">Lorem Ipsum</strong><span style=\"font-family: \'Open Sans\', Arial, sans-serif; font-size: 14px; text-align: justify; background-color: #ffffff;\">&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</span></p>', '2020-12-12 04:41:05', 2, 144);

-- --------------------------------------------------------

--
-- Table structure for table `action_types`
--

CREATE TABLE `action_types` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `action_types`
--

INSERT INTO `action_types` (`id`, `name`) VALUES
(1, 'LONG_TERM'),
(2, 'IMMEDIATE_ACTION');

-- --------------------------------------------------------

--
-- Table structure for table `floors`
--

CREATE TABLE `floors` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `description` varchar(255) NOT NULL,
  `admin` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `floors`
--

INSERT INTO `floors` (`id`, `name`, `description`, `admin`) VALUES
(4, 'Lantai 1', 'Ruangan yang berada di lantai 1', 'USER-00028');

-- --------------------------------------------------------

--
-- Table structure for table `image_attachments`
--

CREATE TABLE `image_attachments` (
  `id` int(11) NOT NULL,
  `image_path` varchar(100) NOT NULL,
  `report` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `image_attachments`
--

INSERT INTO `image_attachments` (`id`, `image_path`, `report`) VALUES
(20, 'rasulullah-jatuh-pingsan-mendengar-keadaan-neraka-dan-penghuninya-1-z0G-thumb.jpg', 16);

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `id` int(11) NOT NULL,
  `notification_message` int(11) NOT NULL,
  `report` int(11) NOT NULL,
  `read_status` int(1) NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT current_timestamp(),
  `user` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`id`, `notification_message`, `report`, `read_status`, `datetime`, `user`) VALUES
(126, 1, 16, 0, '2020-12-12 11:17:50', 'USER-00212'),
(127, 7, 16, 0, '2020-12-12 11:17:50', 'USER-00028'),
(128, 7, 16, 0, '2020-12-12 11:17:50', 'USER-00101'),
(129, 2, 16, 0, '2020-12-12 11:19:33', 'USER-00212'),
(130, 3, 16, 0, '2020-12-12 11:40:57', 'USER-00212'),
(131, 6, 16, 0, '2020-12-12 11:40:57', 'USER-00212'),
(132, 9, 16, 0, '2020-12-12 11:40:57', 'USER-00028'),
(133, 9, 16, 0, '2020-12-12 11:40:57', 'USER-00101');

-- --------------------------------------------------------

--
-- Table structure for table `notification_messages`
--

CREATE TABLE `notification_messages` (
  `id` int(11) NOT NULL,
  `message` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notification_messages`
--

INSERT INTO `notification_messages` (`id`, `message`) VALUES
(1, 'Report sent successfully'),
(2, 'Report was successfully added to queue'),
(3, 'Your report has been responded to by the floor warden'),
(4, 'Your report is being forwarded to HSE'),
(5, 'Your report has been responded by HSE'),
(6, 'Your report is complete'),
(7, 'There\'s a new report'),
(8, 'Floor warden forwarded report to you'),
(9, 'Report is complete and closed'),
(10, 'Your report is forwarded to HSE');

-- --------------------------------------------------------

--
-- Table structure for table `priorities`
--

CREATE TABLE `priorities` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `priorities`
--

INSERT INTO `priorities` (`id`, `name`) VALUES
(1, 'Low'),
(2, 'Medium'),
(3, 'High'),
(4, 'Default');

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp(),
  `originator` varchar(50) NOT NULL,
  `room` int(11) NOT NULL,
  `description` text NOT NULL,
  `priority` int(11) DEFAULT NULL,
  `current_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`id`, `date`, `originator`, `room`, `description`, `priority`, `current_status`) VALUES
(16, '2020-12-12 04:17:50', 'USER-00212', 14, '<p>AC ruangan sepertinya mati, suhu didalam terasa sangat panas</p>', 2, 6);

--
-- Triggers `reports`
--
DELIMITER $$
CREATE TRIGGER `create_notification` AFTER UPDATE ON `reports` FOR EACH ROW BEGIN
IF !(new.current_status <=> old.current_status) THEN
INSERT INTO notifications (notification_message, report, read_status, user) values (new.current_status, new.id, 0, new.originator);

IF(new.current_status = 4) 
THEN
INSERT INTO notifications (notification_message, report, read_status, user) values (10, new.id, 0, (SELECT admin FROM floors, rooms WHERE floors.id = rooms.floor and rooms.id = new.room));
END IF;

IF(new.current_status = 4) 
THEN
INSERT INTO notifications (notification_message, report, read_status, user) values (10, new.id, 0, (SELECT admin FROM floors, rooms WHERE floors.id = rooms.floor and rooms.id = new.room)), (8, new.id, 0, (SELECT id FROM users WHERE roles = 1));
END IF;

IF(new.current_status = 6) 
THEN
INSERT INTO notifications (notification_message, report, read_status, user) values (9, new.id, 0, (SELECT admin FROM floors, rooms WHERE floors.id = rooms.floor and rooms.id = new.room)), (9, new.id, 0, (SELECT id FROM users WHERE roles = 1));
END IF;

END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `initial_notification` AFTER INSERT ON `reports` FOR EACH ROW INSERT INTO notifications (notification_message,report, read_status, user) values (1, new.id, 0, new.originator), (7, new.id, 0, (SELECT admin FROM floors, rooms WHERE floors.id = rooms.floor and rooms.id = new.room)), (7, new.id, 0, (SELECT id FROM users WHERE roles = 1))
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `initial_progress` AFTER INSERT ON `reports` FOR EACH ROW INSERT INTO report_progresses(status,report) VALUES (new.current_status, new.id)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `on_delete` BEFORE DELETE ON `reports` FOR EACH ROW BEGIN
DELETE from image_attachments WHERE report = old.id;
DELETE from notifications WHERE report = old.id;
DELETE from actions WHERE report_progress IN (SELECT id from report_progresses WHERE report = old.id);
DELETE from report_progresses WHERE report = old.id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_progress` AFTER UPDATE ON `reports` FOR EACH ROW BEGIN
IF !(new.current_status <=> old.current_status) THEN
INSERT INTO report_progresses(status,report) VALUES (new.current_status, old.id);
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `report_progresses`
--

CREATE TABLE `report_progresses` (
  `id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `report` int(11) NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `report_progresses`
--

INSERT INTO `report_progresses` (`id`, `status`, `report`, `date`) VALUES
(142, 1, 16, '2020-12-12 18:17:50'),
(143, 2, 16, '2020-12-12 18:19:33'),
(144, 3, 16, '2020-12-12 18:40:57'),
(145, 6, 16, '2020-12-12 18:40:57');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`, `description`) VALUES
(1, 'HSE', 'HSE Position'),
(2, 'FW', 'FLOOR WARDEN POSITION'),
(3, 'EMPLOYEE', 'EMPLOYEE POSITION');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `floor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `name`, `description`, `floor`) VALUES
(13, 'Toilet utara', 'Toilet yang berada disisi utara', 4),
(14, 'Ruangan kontrol cctv', 'Ruangan khusus kontrol cctv semua gedung', 4),
(15, 'Ruangan security', 'Ruangan untuk koordinasi security', 4);

-- --------------------------------------------------------

--
-- Table structure for table `statuses`
--

CREATE TABLE `statuses` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `message` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `statuses`
--

INSERT INTO `statuses` (`id`, `name`, `message`) VALUES
(1, 'New', 'Report sent'),
(2, 'Pending', 'Report added to queue'),
(3, 'Pending', 'Report responded by floor warden'),
(4, 'Pending', 'Report forwarded to HSE'),
(5, 'Pending', 'Report responded by HSE'),
(6, 'Finished', 'Report closed');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `roles` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `username`, `phone`, `roles`) VALUES
('USER-00021', 'Agustinus Yoga Pangestu', 'yogapanges1@gmail.com', 'yogapanges', NULL, 3),
('USER-00028', 'Adnan Ghofar Mana', 'adnangofar.ag@gmail.com', 'sweetcassava', NULL, 2),
('USER-00101', 'Kelvin Kurniawan Oktavianto', '672018149@student.uksw.edu', 'kelvink', '082226733123', 1),
('USER-00210', 'Hazard Report Employee ', 'segywu@digital10network.com', 'hremp1', '082123231313', 3),
('USER-00211', 'Hazard Report FW', 'beledufy@ivyandmarj.com', 'hrfw1', '0888888213', 2),
('USER-00212', 'Hazard Report Employee 2', 'ziratyme@ivyandmarj.com', 'hremp2', '08123443243', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `actions`
--
ALTER TABLE `actions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `action_type` (`action_type`),
  ADD KEY `report_progress` (`report_progress`);

--
-- Indexes for table `action_types`
--
ALTER TABLE `action_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `floors`
--
ALTER TABLE `floors`
  ADD PRIMARY KEY (`id`),
  ADD KEY `admin` (`admin`);

--
-- Indexes for table `image_attachments`
--
ALTER TABLE `image_attachments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `report` (`report`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user` (`user`),
  ADD KEY `notification_message` (`notification_message`),
  ADD KEY `report` (`report`);

--
-- Indexes for table `notification_messages`
--
ALTER TABLE `notification_messages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `priorities`
--
ALTER TABLE `priorities`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room` (`room`),
  ADD KEY `priority` (`priority`),
  ADD KEY `current_status` (`current_status`),
  ADD KEY `originator` (`originator`);

--
-- Indexes for table `report_progresses`
--
ALTER TABLE `report_progresses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `report` (`report`),
  ADD KEY `status` (`status`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`),
  ADD KEY `floor` (`floor`);

--
-- Indexes for table `statuses`
--
ALTER TABLE `statuses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `roles` (`roles`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `actions`
--
ALTER TABLE `actions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `action_types`
--
ALTER TABLE `action_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `floors`
--
ALTER TABLE `floors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `image_attachments`
--
ALTER TABLE `image_attachments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=134;

--
-- AUTO_INCREMENT for table `notification_messages`
--
ALTER TABLE `notification_messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `priorities`
--
ALTER TABLE `priorities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `report_progresses`
--
ALTER TABLE `report_progresses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=146;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `statuses`
--
ALTER TABLE `statuses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `actions`
--
ALTER TABLE `actions`
  ADD CONSTRAINT `actions_ibfk_1` FOREIGN KEY (`report_progress`) REFERENCES `report_progresses` (`id`),
  ADD CONSTRAINT `actions_ibfk_2` FOREIGN KEY (`action_type`) REFERENCES `action_types` (`id`);

--
-- Constraints for table `floors`
--
ALTER TABLE `floors`
  ADD CONSTRAINT `floors_ibfk_1` FOREIGN KEY (`admin`) REFERENCES `users` (`id`);

--
-- Constraints for table `image_attachments`
--
ALTER TABLE `image_attachments`
  ADD CONSTRAINT `image_attachments_ibfk_1` FOREIGN KEY (`report`) REFERENCES `reports` (`id`);

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `notifications_ibfk_3` FOREIGN KEY (`user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `notifications_ibfk_4` FOREIGN KEY (`notification_message`) REFERENCES `notification_messages` (`id`),
  ADD CONSTRAINT `notifications_ibfk_5` FOREIGN KEY (`report`) REFERENCES `reports` (`id`);

--
-- Constraints for table `reports`
--
ALTER TABLE `reports`
  ADD CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`originator`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `reports_ibfk_2` FOREIGN KEY (`room`) REFERENCES `rooms` (`id`),
  ADD CONSTRAINT `reports_ibfk_3` FOREIGN KEY (`priority`) REFERENCES `priorities` (`id`),
  ADD CONSTRAINT `reports_ibfk_4` FOREIGN KEY (`current_status`) REFERENCES `statuses` (`id`);

--
-- Constraints for table `report_progresses`
--
ALTER TABLE `report_progresses`
  ADD CONSTRAINT `report_progresses_ibfk_1` FOREIGN KEY (`report`) REFERENCES `reports` (`id`),
  ADD CONSTRAINT `report_progresses_ibfk_2` FOREIGN KEY (`status`) REFERENCES `statuses` (`id`);

--
-- Constraints for table `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`floor`) REFERENCES `floors` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`roles`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
