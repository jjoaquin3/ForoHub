USE FOROHUB_DB;

-- Inserts USER con password encriptado con BCryptPasswordEncoder por defecto = "12345"
INSERT INTO `USER` (`email`, `password`, `role`, `username`)
VALUES
('usuario1@example.com', '$2a$10$e6rE4AqNj3q11kcdmEkVH.9hhIT07G6uUnVOkLEJ95edtI4t6W5CK', 'USER', 'usuario1'),
('usuario2@example.com', '$2a$10$FqklEGbWxvqgQa4Knk5Km.eJkL7BxDq8Bd0IdFnVo9M4pTb.6rE7S', 'ADMIN', 'admin1'),
('usuario3@example.com', '$2a$10$x6b4LHnZvrMEw1S9MMdFLO4Wk6xUCkL7R0PMWxN8nE1S8YdYsJ5/2', 'USER', 'usuario3'),
('usuario4@example.com', '$2a$10$6iL4xgkzQhKs.jb3JHr18Ox7jRzTN4YmZ1nTR9d64eBwz2fR1H2DW', 'USER', 'usuario4'),
('usuario5@example.com', '$2a$10$flh0w5rA3f1aJZMy4VevkeBqZkafmZB4zPlr60z5Cxg7Z1.Hk3e5G', 'ADMIN', 'admin2');

-- Inserts COURSE
INSERT INTO `COURSE` (`category`, `name`)
VALUES
('Programming', 'Java Basics'),
('Web Development', 'HTML/CSS Fundamentals'),
('Data Science', 'Introduction to Machine Learning'),
('Design', 'Graphic Design Principles'),
('Business', 'Entrepreneurship 101');

-- Inserts TOPIC
INSERT INTO `TOPIC` (`creation_date`, `message`, `status`, `title`, `course`, `user`)
VALUES
('2024-07-15 10:00:00', '¿Qué es Java y cómo empezar?', 'ACTIVE', 'Introducción a Java', 1, 1),
('2024-07-15 11:00:00', 'Aprender HTML y CSS desde cero', 'ACTIVE', 'Fundamentos de HTML y CSS', 2, 2),
('2024-07-15 12:00:00', '¿Cómo implementar Machine Learning en Python?', 'ACTIVE', 'Machine Learning Básico', 3, 3),
('2024-07-15 13:00:00', 'Principios básicos de diseño gráfico', 'ACTIVE', 'Conceptos de Diseño Gráfico', 4, 4),
('2024-07-15 14:00:00', 'Fundamentos de emprendimiento para startups', 'ACTIVE', 'Emprendimiento 101', 5, 5),
('2024-07-15 15:00:00', 'Estructuras de datos en Java', 'ACTIVE', 'Data Structures in Java', 1, 1),
('2024-07-15 16:00:00', 'Desarrollo web avanzado con frameworks modernos', 'ACTIVE', 'Avanzando en Desarrollo Web', 2, 2),
('2024-07-15 17:00:00', 'Aplicaciones de Machine Learning en la vida real', 'ACTIVE', 'Machine Learning Avanzado', 3, 3),
('2024-07-15 18:00:00', 'Diseño visual y UX/UI', 'ACTIVE', 'Diseño Visual y UX/UI', 4, 4),
('2024-07-15 19:00:00', 'Estrategias de crecimiento empresarial', 'ACTIVE', 'Crecimiento Empresarial', 5, 5);
