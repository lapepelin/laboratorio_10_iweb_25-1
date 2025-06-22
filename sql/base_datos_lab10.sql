CREATE DATABASE IF NOT EXISTS buscaminas;
USE buscaminas;

CREATE TABLE configuracion (
    idMina INT PRIMARY KEY AUTO_INCREMENT,
    dimMinaX INT NOT NULL,
    dimMinaY INT NOT NULL,
    cantBombas INT NOT NULL,
    cantIntentos INT NOT NULL,
    cantIntentosActual INT NOT NULL
);

CREATE TABLE posicionbomba (
    idBomba INT PRIMARY KEY AUTO_INCREMENT,
    coordenadaX INT NOT NULL,
    coordenadaY INT NOT NULL,
    idMina INT NOT NULL,
    FOREIGN KEY (idMina) REFERENCES configuracion(idMina)
);

CREATE TABLE movimiento (
    idMovimiento INT PRIMARY KEY AUTO_INCREMENT,
    idMina INT NOT NULL,
    x INT NOT NULL,
    y INT NOT NULL,
    estado VARCHAR(30) NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idMina) REFERENCES configuracion(idMina)
);

