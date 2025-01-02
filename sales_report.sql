-- Clear existing database
DROP DATABASE IF EXISTS `sales_report`;
CREATE DATABASE `sales_report`;
USE `sales_report`;

-- Drop existing tables and triggers if they exist
DROP TABLE IF EXISTS `stock`;
DROP TABLE IF EXISTS `discounts`;
DROP TABLE IF EXISTS `sales`;
DROP TABLE IF EXISTS `accumulated_sale`;
DROP TRIGGER IF EXISTS `update_accumulated_sale`;
DROP TRIGGER IF EXISTS `update_accumulated_sale_v2`;

-- Creating the stock table
CREATE TABLE `stock` (
  `product_id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_type` VARCHAR(100) NOT NULL,
  `product_name` VARCHAR(255) NOT NULL,
  `manufacturer_name` VARCHAR(100) NOT NULL,
  `manufacturer_id` VARCHAR(50) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Creating the discounts table
CREATE TABLE `discounts` (
  `discount_id` INT(11) NOT NULL AUTO_INCREMENT,
  `discount_code` VARCHAR(255) NOT NULL,
  `discount_percentage` DECIMAL(5,2) DEFAULT NULL, -- Discount in percentage
  `discount_usd` DECIMAL(10,2) DEFAULT NULL, -- Discount in USD
  PRIMARY KEY (`discount_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Creating the discounts table
CREATE TABLE `sales_persons` (
  `sales_person_id` INT(11) NOT NULL AUTO_INCREMENT,
  `sales_person_name` VARCHAR(255) NOT NULL,
  `sales_person_surname` VARCHAR(255) DEFAULT NULL, -- Discount in percentage
  PRIMARY KEY (`sales_person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Creating the sales table
CREATE TABLE `sales` (
  `sale_id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_id` INT(11) NOT NULL,
  `discount_id` INT(11) DEFAULT NULL, -- Linked to the discounts table
  `price` DECIMAL(10,2) NOT NULL, -- Sale price after discount
  `store_type` ENUM('Online', 'In-store') NOT NULL,
  `sales_person_id` INT(11) DEFAULT NULL,
  PRIMARY KEY (`sale_id`),
  FOREIGN KEY (`product_id`) REFERENCES `stock`(`product_id`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`discount_id`) REFERENCES `discounts`(`discount_id`)
    ON DELETE SET NULL ON UPDATE CASCADE,
  FOREIGN KEY (`sales_person_id`) REFERENCES `sales_persons`(`sales_person_id`)
    ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Creating the accumulated_sale table
CREATE TABLE `accumulated_sale` (
  `product_id` INT(11) NOT NULL,
  `total_quantity_sold` INT(11) NOT NULL DEFAULT 0,
  `total_profit` DECIMAL(15,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`product_id`),
  FOREIGN KEY (`product_id`) REFERENCES `stock`(`product_id`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Insert sample data into `stock` for selected categories
INSERT INTO `stock` (`product_type`, `product_name`, `manufacturer_name`, `manufacturer_id`, `price`) VALUES
('Laptop', 'XPS 13', 'Dell', 'DELL-XPS13', 2500.00),
('Laptop', 'MacBook Pro 16-inch', 'Apple', 'APPLE-MBPRO16', 2800.00),
('Laptop', 'ThinkPad X1 Carbon', 'Lenovo', 'LENOVO-X1CARBON', 2200.00),
('Laptop', 'Surface Laptop 4', 'Microsoft', 'MS-SURFACELAPTOP4', 1800.00),
('Laptop', 'Pavilion 15', 'HP', 'HP-PAVILION15', 1200.00),
('Laptop', 'Latitude 9510', 'Dell', 'DELL-LAT9510', 1700.00),
('Laptop', 'Razer Blade 15', 'Razer', 'RAZER-BLADE15', 2200.00),
('Laptop', 'Envy 14', 'HP', 'HP-ENVY14', 1500.00),
('Laptop', 'Asus ZenBook 14', 'Asus', 'ASUS-ZENBOOK14', 1300.00),

('Smartphone', 'Galaxy S23', 'Samsung', 'SAMSUNG-GS23', 1200.50),
('Smartphone', 'iPhone 14', 'Apple', 'APPLE-IPHONE14', 999.99),
('Smartphone', 'Pixel 7 Pro', 'Google', 'GOOGLE-PIXEL7PRO', 899.00),
('Smartphone', 'OnePlus 11', 'OnePlus', 'ONEPLUS-11', 749.99),
('Smartphone', 'Xiaomi 13', 'Xiaomi', 'XIAOMI-13', 650.00),
('Smartphone', 'Galaxy Z Fold 4', 'Samsung', 'SAMSUNG-GALAXYZFOLD4', 1900.00),
('Smartphone', 'iPhone SE 2022', 'Apple', 'APPLE-IPHONESE2022', 429.99),

('Tablet', 'iPad Pro', 'Apple', 'APPLE-IPADPRO', 800.00),
('Tablet', 'Galaxy Tab S8', 'Samsung', 'SAMSUNG-GALAXYTAB8', 700.00),
('Tablet', 'Surface Pro 9', 'Microsoft', 'MS-SURFACEPRO9', 1100.00),
('Tablet', 'Lenovo Tab P11', 'Lenovo', 'LENOVO-TABP11', 250.00),
('Tablet', 'Fire HD 10', 'Amazon', 'AMAZON-FIREHD10', 150.00),
('Tablet', 'Samsung Galaxy Tab S7', 'Samsung', 'SAMSUNG-GALAXYTAB7', 650.00),

('Graphics Card', 'RTX 4090', 'NVIDIA', 'NVIDIA-RTX4090', 4000.00),
('Graphics Card', 'RTX 3080', 'NVIDIA', 'NVIDIA-RTX3080', 1200.00),
('Graphics Card', 'RX 6900 XT', 'AMD', 'AMD-RX6900XT', 1500.00),
('Graphics Card', 'RX 6800 XT', 'AMD', 'AMD-RX6800XT', 1100.00),
('Graphics Card', 'RTX 3070', 'NVIDIA', 'NVIDIA-RTX3070', 700.00),

('Processor', 'Core i9-13900K', 'Intel', 'INTEL-I913900K', 1500.00),
('Processor', 'Ryzen 9 7950X', 'AMD', 'AMD-RYZEN97950X', 1800.00),
('Processor', 'Core i7-12700K', 'Intel', 'INTEL-I712700K', 900.00),
('Processor', 'Ryzen 7 7700X', 'AMD', 'AMD-RYZEN77700X', 700.00),
('Processor', 'Core i5-12600K', 'Intel', 'INTEL-I512600K', 400.00),

('RAM sticks', 'HyperX Fury 16GB', 'Kingston', 'KINGSTON-HYPERX16GB', 60.00),
('RAM sticks', 'Ballistix Sport LT 16GB', 'Crucial', 'CRUCIAL-BALLISTIX16GB', 55.00),
('RAM sticks', 'Trident Z 32GB', 'G.SKILL', 'GSKILL-TRIDENTZ32GB', 150.00);


-- Inserting 5 example discounts into the discounts table
INSERT INTO `discounts` (`discount_code`, `discount_percentage`, `discount_usd`) VALUES
('DISCOUNT10', 10.00, NULL),  -- 10% discount
('DISCOUNT50USD', NULL, 20.00),  -- $20 discount
('DISCOUNT20', 20.00, NULL), 
('DISCOUNT30', 30.00, NULL),
('DISCOUNT50USD', NULL, 50.00), 
('DISCOUNT100USD', NULL, 100.00);

-- Inserting 5 example sale_persons into the sale_persons table
INSERT INTO `sales_persons` (`sales_person_name`, `sales_person_surname`) VALUES
('Tim', 'Cook'),
('Elon', 'Musk'),
('Jensen', 'Huang'),
('Lisa', 'Su'),
('Warren', 'Buffet');

-- Create trigger to automatically update `accumulated_sale`
DELIMITER //
CREATE TRIGGER `update_accumulated_sale` 
AFTER INSERT ON `sales`
FOR EACH ROW
BEGIN
  -- Insert or update the accumulated_sale table
  INSERT INTO `accumulated_sale` (`product_id`, `total_quantity_sold`, `total_profit`)
  VALUES (NEW.product_id, 1, NEW.price)
  ON DUPLICATE KEY UPDATE
    `total_quantity_sold` = `total_quantity_sold` + 1,  -- Increment quantity sold
    `total_profit` = `total_profit` + NEW.price;  -- Add the sale price to total profit
END //
DELIMITER ; -- Reset the delimiter back to the default

INSERT INTO `sales` (`product_id`, `discount_id`, `price`, `store_type`, `sales_person_id`) VALUES
(1, 1, 1.00, 'Online', NULL),
(1, 2, 1.00, 'In-store', 1),
(2, NULL, 1.00, 'Online', NULL),
(3, 3, 1.00, 'In-store', 5),
(3, NULL, 1.00, 'Online', NULL),
(4, 2, 1.00, 'In-store', 2),
(5, NULL, 1.00, 'Online', NULL),
(5, 4, 1.00, 'In-store', 3),
(6, NULL, 1.00, 'In-store', 1),
(7, 1, 1.00, 'Online', NULL),
(7, NULL, 1.00, 'In-store', 2),
(8, 4, 1.00, 'Online', NULL),
(8, 3, 1.00, 'In-store', 4),
(9, NULL, 1.00, 'Online', NULL),
(9, 3, 1.00, 'Online', NULL),
(10, NULL, 1.000, 'Online', NULL),
(10, NULL, 1.00, 'Online', NULL),
(10, 1, 1.00, 'Online', NULL),
(10, NULL, 1.00, 'In-store', 3),
(11, 2, 1.00, 'Online', NULL),
(12, 1, 1.00, 'In-store', 1),
(13, 5, 1.00, 'Online', NULL),
(14, NULL, 1.00, 'In-store', 4),
(14, 3, 1.00, 'Online', NULL),
(16, NULL, 1.00, 'Online', NULL),
(16, NULL, 1.00, 'In-store', 3),
(17, 3, 1.00, 'Online', NULL),
(18, NULL, 1.00, 'Online', NULL),
(19, NULL, 1.0, 'In-store', 5),
(20, 2, 1.00, 'In-store', 1),
(20, NULL, 1.00, 'Online', NULL),
(21, 3, 1.00, 'In-store', 3),
(22, NULL, 1.00, 'Online', NULL),
(23, 4, 1.00, 'In-store', 2),
(23, NULL, 1.00, 'In-store', 2),
(23, 4, 1.00, 'Online', NULL),
(24, 5, 1.00, 'Online', NULL),
(24, 5, 1.00, 'Online', NULL),
(25, NULL, 1.00, 'In-store', 1);

DELIMITER //
-- Create trigger to automatically update accumulated_sale after price update in sales
CREATE TRIGGER `update_accumulated_sale_after_price_update`
AFTER UPDATE ON `sales`
FOR EACH ROW
BEGIN
  -- If the price in sales has changed, update the accumulated_sale table
  IF OLD.price != NEW.price THEN
    -- Update total_quantity_sold and total_profit based on the new price
    UPDATE `accumulated_sale`
    SET
      `total_profit` = `total_profit` + NEW.price - OLD.price  -- Adjust total profit based on the price change
    WHERE `product_id` = NEW.product_id;
  END IF;
END //
DELIMITER ; -- Reset the delimiter back to the default

-- Update price of a sale (price from stock and discount)
UPDATE `sales` s
JOIN `stock` st ON s.product_id = st.product_id
LEFT JOIN `discounts` d ON s.discount_id = d.discount_id
SET s.price = 
    CASE
        WHEN d.discount_percentage IS NOT NULL THEN st.price * (1 - d.discount_percentage / 100)  -- Discount in %
        WHEN d.discount_usd IS NOT NULL THEN st.price - d.discount_usd  -- discount in USD
        ELSE st.price  -- No discount
    END;