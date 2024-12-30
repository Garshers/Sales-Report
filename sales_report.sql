-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 29, 2024 at 06:38 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sales_report`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `sales`
--

CREATE TABLE `sale` (
  `id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales`
--

INSERT INTO `sale` (`id`, `product_name`, `quantity`, `price`) VALUES
(1, 'Laptop', 5, 2500.00),
(2, 'Smartphone', 10, 1200.50),
(3, 'Tablet', 7, 800.00),
(4, 'Headphones', 20, 150.75),
(5, 'Keyboard', 15, 120.99),
(6, 'Mouse', 25, 50.49),
(7, 'Monitor', 8, 950.00),
(8, 'Printer', 3, 600.00),
(9, 'External Hard Drive', 12, 300.89),
(10, 'USB Cable', 50, 15.99),
(11, 'Smartwatch', 6, 750.00),
(12, 'Gaming Chair', 4, 1200.00),
(13, 'Desk Lamp', 18, 75.00),
(14, 'Webcam', 10, 250.00),
(15, 'Speakers', 9, 500.00),
(16, 'Router', 7, 350.00),
(17, 'Power Bank', 14, 120.00),
(18, 'Graphics Card', 2, 4000.00),
(19, 'Processor', 3, 1500.00),
(20, 'RAM', 10, 300.00);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
