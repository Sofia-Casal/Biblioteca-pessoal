DROP DATABASE IF EXISTS bibliotecadb;
CREATE DATABASE bibliotecadb CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE bibliotecadb;

CREATE TABLE book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    genero VARCHAR(255),
    data_inicio DATE,
    data_fim DATE,
    lido BOOLEAN NOT NULL,
    nota DOUBLE NOT NULL CHECK (nota >= 0 AND nota <= 5)
);

INSERT INTO book (titulo, autor, genero, data_inicio, data_fim, lido, nota)
VALUES
('Cidade dos anjos caídos', 'Cassandra Clare', 'Fantasia', '2021-06-29', '2021-07-03', true, 0.0),
('A terra das sombras (A Mediadora)', 'Meg Cabot', 'Fantasia Juvenil', '2021-07-06', '2021-07-06', true, 0.0),
('O arcano nove (A Mediadora)', 'Meg Cabot', 'Fantasia Juvenil', '2021-07-06', '2021-07-06', true, 0.0),
('Reunião (A Mediadora)', 'Meg Cabot', 'Fantasia Juvenil', '2021-07-07', '2021-07-07', true, 0.0),
('A hora mais sombria (A Mediadora)', 'Meg Cabot', 'Fantasia Juvenil', '2021-07-07', '2021-07-07', true, 0.0),
('Assombrado (A Mediadora)', 'Meg Cabot', 'Fantasia Juvenil', '2021-07-07', '2021-07-08', true, 0.0),
('Crepúsculo (A Mediadora)', 'Meg Cabot', 'Fantasia Juvenil', '2021-07-08', '2021-07-08', true, 0.0),
('Cidade das almas perdidas', 'Cassandra Clare', 'Fantasia', '2021-07-10', '2021-07-16', true, 0.0),
('Cidade do fogo celestial (metade)', 'Cassandra Clare', 'Fantasia', '2021-07-17', '2021-07-20', true, 0.0),
('Anjo mecânico', 'Cassandra Clare', 'Fantasia', '2021-07-20', '2021-07-20', true, 0.0),
('Príncipe mecânico', 'Cassandra Clare', 'Fantasia', '2021-07-21', '2021-07-21', true, 0.0),
('Princesa mecânica', 'Cassandra Clare', 'Fantasia', '2021-07-22', '2021-07-23', true, 0.0),
('Cidade do fogo celestial (fim)', 'Cassandra Clare', 'Fantasia', '2021-07-24', '2021-07-24', true, 5.0),
('Divergente', 'Veronica Roth', 'Distopia', '2021-12-06', '2021-12-06', true, 0.0),
('Caraval', 'Stephanie Garber', 'Fantasia', '2021-12-06', '2021-12-06', true, 0.0),
('De Lukov, com amor', 'Mariana Zapata', 'Romance', '2022-08-14', '2022-08-16', true, 0.0),
('Era uma vez um coração partido', 'Stephanie Garber', 'Fantasia', '2023-01-28', '2023-01-29', true, 5.0),
('The ballad of never after', 'Stephanie Garber', 'Fantasia', '2023-01-29', '2023-01-29', true, 5.0);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nome VARCHAR(150)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

INSERT INTO users (email, password, nome)
VALUES ('admin@gmail.com', '123', 'Administrador')
ON DUPLICATE KEY UPDATE email = email;

