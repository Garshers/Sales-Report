-- Clear existing database
DROP DATABASE IF EXISTS `sales_report`;
CREATE DATABASE `sales_report`;
USE `sales_report`;

DROP TABLE IF EXISTS `stock`;
DROP TABLE IF EXISTS `sales`;
DROP TABLE IF EXISTS `accumulated_sale`;
DROP TRIGGER IF EXISTS `update_accumulated_sale`;
DROP TRIGGER IF EXISTS `update_accumulated_sale_2`;

-- Table: stock
CREATE TABLE `stock` (
  `product_id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_type` VARCHAR(100) NOT NULL,
  `product_name` VARCHAR(255) NOT NULL,
  `manufacturer_name` VARCHAR(100) NOT NULL,
  `manufacturer_id` VARCHAR(50) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Table: sales
CREATE TABLE `sales` (
  `sale_id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_id` INT(11) NOT NULL,
  `sale_price` DECIMAL(10,2) NOT NULL,
  `discount_name` VARCHAR(255) DEFAULT NULL,
  `discount_id` VARCHAR(50) DEFAULT NULL,
  `store_type` ENUM('Online', 'In-store') NOT NULL,
  `salesperson` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`sale_id`),
  FOREIGN KEY (`product_id`) REFERENCES `stock`(`product_id`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Table: accumulated_sale
CREATE TABLE `accumulated_sale` (
  `product_id` INT(11) NOT NULL,
  `total_quantity_sold` INT(11) NOT NULL DEFAULT 0,
  `total_profit` DECIMAL(15,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`product_id`),
  FOREIGN KEY (`product_id`) REFERENCES `stock`(`product_id`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Insert sample data into `stock`
INSERT INTO `stock` (`product_type`, `product_name`, `manufacturer_name`, `manufacturer_id`, `price`) VALUES
('Laptop', 'XPS 13', 'Dell', 'DELL-XPS13', 2500.00),
('Smartphone', 'Galaxy S23', 'Samsung', 'SAMSUNG-GS23', 1200.50),
('Tablet', 'iPad Pro', 'Apple', 'APPLE-IPADPRO', 800.00),
('Headphones', 'WH-1000XM5', 'Sony', 'SONY-WH1000XM5', 150.75),
('Monitor', 'UltraGear 27GN950', 'LG', 'LG-ULTRAGEAR27', 950.00),
('Router', 'Archer AX6000', 'TP-Link', 'TPLINK-AX6000', 350.00),
('Printer', 'LaserJet Pro MFP', 'HP', 'HP-LASERJETPRO', 600.00),
('Power Bank', 'PowerCore 26800', 'Anker', 'ANKER-PC26800', 120.00),
('Graphics Card', 'RTX 4090', 'NVIDIA', 'NVIDIA-RTX4090', 4000.00),
('Processor', 'Core i9-13900K', 'Intel', 'INTEL-I913900K', 1500.00);

-- Create trigger to automatically update `accumulated_sale`
DELIMITER //

CREATE TRIGGER `update_accumulated_sale` 
AFTER INSERT ON `sales`
FOR EACH ROW
BEGIN
  DECLARE discount_applied DECIMAL(10,2);

  -- Calculate the discount applied (if any)
  SET discount_applied = COALESCE(NEW.sale_price, 0);

  -- Insert or update the accumulated_sale table
  INSERT INTO `accumulated_sale` (`product_id`, `total_quantity_sold`, `total_profit`)
  VALUES (NEW.product_id, 1, discount_applied)
  ON DUPLICATE KEY UPDATE
    `total_quantity_sold` = `total_quantity_sold` + 1,  -- Increment quantity sold
    `total_profit` = `total_profit` + discount_applied;  -- Add the sale price to total profit
END;
//

-- Reset the delimiter back to the default
DELIMITER ;

-- Insert sample data into `sales`
INSERT INTO `sales` (`product_id`, `sale_price`, `discount_name`, `discount_id`, `store_type`, `salesperson`) VALUES
(1, 2250.00, 'Holiday Sale', 'DISC-001', 'Online', NULL),
(1, 2400.00, NULL, NULL, 'In-store', 'John Doe'),
(2, 1080.45, 'Black Friday', 'DISC-002', 'Online', NULL),
(2, 1200.50, NULL, NULL, 'In-store', 'Jane Smith'),
(3, 720.00, 'Black Friday', 'DISC-003', 'Online', NULL),
(4, 135.68, 'Holiday Discount', 'DISC-004', 'In-store', 'Emily White'),
(5, 900.00, 'Cyber Monday', 'DISC-005', 'Online', NULL),
(6, 315.00, 'Tech Upgrade', 'DISC-006', 'In-store', 'Michael Brown'),
(7, 600.00, NULL, NULL, 'In-store', 'Rachel Green'),
(8, 110.00, 'Seasonal Discount', 'DISC-007', 'Online', NULL),
(9, 3600.00, 'Gaming Festival', 'DISC-008', 'Online', NULL),
(10, 1450.00, 'New Year Sale', 'DISC-009', 'In-store', 'Paul Adams'),
(5, 900.00, NULL, NULL, 'Online', NULL),
(3, 750.00, 'Holiday Discount', 'DISC-003', 'In-store', 'Alice Johnson'),
(9, 3800.00, 'Special Promo', 'DISC-010', 'Online', NULL),
(7, 580.00, 'Back to School', 'DISC-011', 'In-store', 'Henry Ford'),
(1, 2300.00, NULL, NULL, 'Online', NULL),
(2, 1150.00, 'Weekly Deal', 'DISC-012', 'In-store', 'Emma Brown'),
(10, 1490.00, NULL, NULL, 'In-store', 'Chris Taylor'),
(6, 310.00, 'Clearance Sale', 'DISC-013', 'Online', NULL),
(8, 115.00, 'Winter Sale', 'DISC-014', 'Online', NULL);
