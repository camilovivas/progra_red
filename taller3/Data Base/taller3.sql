-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-10-2020 a las 22:40:29
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `taller3`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actores`
--

CREATE TABLE `actores` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `apellido` varchar(100) COLLATE utf8mb4_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `actores`
--

INSERT INTO `actores` (`id`, `nombre`, `apellido`) VALUES
(1, 'Leonardo', 'DiCaprio'),
(4, 'Vin', 'Diesel'),
(5, 'Paul', 'Walker'),
(6, 'Arnold', 'Schwarzeneger'),
(7, 'Linda', 'Hamilton'),
(8, 'Zac', 'Efron'),
(9, 'Vanessa', 'Hudgens');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `id` int(11) NOT NULL,
  `tipo` varchar(100) COLLATE utf8mb4_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`id`, `tipo`) VALUES
(1, 'comedia'),
(2, 'terror'),
(3, 'romance'),
(4, 'suspenso'),
(5, 'accion');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas`
--

CREATE TABLE `peliculas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `generoID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`id`, `nombre`, `year`, `generoID`) VALUES
(1, 'titanic', 1997, 3),
(6, 'el lobo de wall street', 2013, 1),
(7, 'raapido y furioso  7', 2015, 5),
(8, 'rapido y furioso 1', 2001, 5),
(9, 'terminator', 1984, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_pivote`
--

CREATE TABLE `tabla_pivote` (
  `id` int(11) NOT NULL,
  `peliculaID` int(11) DEFAULT NULL,
  `actorID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `tabla_pivote`
--

INSERT INTO `tabla_pivote` (`id`, `peliculaID`, `actorID`) VALUES
(1, 1, 1),
(9, 6, 1),
(10, 7, 4),
(11, 8, 4),
(12, 8, 5),
(13, 9, 6),
(14, 9, 7);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actores`
--
ALTER TABLE `actores`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `generoID` (`generoID`);

--
-- Indices de la tabla `tabla_pivote`
--
ALTER TABLE `tabla_pivote`
  ADD PRIMARY KEY (`id`),
  ADD KEY `peliculaID` (`peliculaID`),
  ADD KEY `actorID` (`actorID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actores`
--
ALTER TABLE `actores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `tabla_pivote`
--
ALTER TABLE `tabla_pivote`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD CONSTRAINT `peliculas_ibfk_1` FOREIGN KEY (`generoID`) REFERENCES `genero` (`id`);

--
-- Filtros para la tabla `tabla_pivote`
--
ALTER TABLE `tabla_pivote`
  ADD CONSTRAINT `tabla_pivote_ibfk_1` FOREIGN KEY (`peliculaID`) REFERENCES `peliculas` (`id`),
  ADD CONSTRAINT `tabla_pivote_ibfk_2` FOREIGN KEY (`actorID`) REFERENCES `actores` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
