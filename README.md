# Sales Report Application

This is a Spring Boot application that integrates with a MySQL database to generate and display an interactive sales report. The application uses Thymeleaf for dynamic HTML page rendering and Spring Data JPA for database management.


## Prerequisites

Before you can run the application, ensure you have the following installed:

- **JDK** (Java Development Kit) - [Download](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Maven** - [Download](https://maven.apache.org/download.cgi)
- **MySQL** - A MySQL database server (can be set up via XAMPP or installed independently).
- **IDE**: IntelliJ IDEA, Eclipse, or any other Java IDE.
- **Web Browser**: Any modern browser (e.g., Google Chrome, Firefox).

### XAMPP Setup Instructions

1. **Install XAMPP**:
   - Download and install XAMPP from the official website.
   - Once installed, start the Apache, MySQL and Tomcat services from the XAMPP Control Panel.

2. **Set Up the Database**:
   - Open PHPMyAdmin by navigating to `http://localhost/phpmyadmin` in your web browser.
   - Import the database schema and seed data by using the provided file `sales_report.sql`. This file will create the necessary database tables and populate them with initial data.

3. **Run the Project**:
   - Make sure to check if the Apache server and MySQL is running from the XAMPP Control Panel.
   - Run project.
   - Open your web browser and navigate to `http://localhost:8080/sales`.
   - The website should now be up and running with the functionality described in the features section.

## Features

- **View Sales Report**: Displays the sales data in a table format. Tables are interactive (on hoover), and allowing the user to sort every column.
- **Interactive Sales Chart**: Charts that display total profit per product, and total profit per employee. Charts are interactive, allowing the user to filter the data by the smallest or largest sum of total profit.
- **Dynamic Data Rendering**: The sales data is fetched from a MySQL database and displayed dynamically using Thymeleaf templates.
- **Spring Data JPA Integration**: The application interacts with a MySQL database via Spring Data JPA to fetch the sales data.
- **Simple Backend and Frontend Integration**: The Spring Boot controller handles business logic, and Thymeleaf renders the HTML view with the fetched data.
- **CSS Styling**: The report page is styled with a custom CSS file (`style.css`) to ensure it is visually appealing.
- **Java Script**: Enables creation of sorting algoritms as well as creating tables and charts.

## Examples
![Sales Report Tables](src/main/resources/static/images/README/sales-report-tables.gif)
![Sales Report Charts](src/main/resources/static/images/README/sales-report-charts.gif)

## Technologies Used
This project is built using the following technologies:

- **Spring Boot**: A Java-based framework that simplifies the development of production-ready applications. It provides embedded servers, configuration management, and easy integration with databases and other services.
- **Spring Data JPA**: A part of the Spring Data family that simplifies data access using the Java Persistence API (JPA). It provides an easy way to interact with relational databases.
- **Hibernate**: An ORM (Object-Relational Mapping) framework used with JPA to map Java objects to relational database tables. It handles database operations for the application.
- **MySQL**: A relational database management system used for storing and managing the sales data. The application communicates with MySQL via Spring Boot and JDBC.
- **Thymeleaf**: A modern Java template engine used to render dynamic HTML pages. It is integrated with Spring Boot to dynamically generate web pages based on data from the backend.
- **Maven**: A build automation tool used for managing project dependencies, building, and packaging the application.
- **CSS**: Custom styles applied to the front-end HTML pages to improve the user experience and visual appearance of the sales report page.
- **Chart.js**: A JavaScript charting library used to render interactive charts on the frontend. It is used in the `charts.html` template to display a sales chart based on total profit data.


## Troubleshooting

1. **Database Connection Issues**:
   - Ensure that your MySQL service is running.
   - Double-check your MySQL connection settings in the `application.properties` file.
   - Ensure that the database (`sales_report`) and the table (`sales`) exist.
   - *"MySQL shutdown unexpectedly"* Error in XAMPP

You may encounter the following error in XAMPP Control Panel:
   ```bash
   [mysql] Error: MySQL shutdown unexpectedly.
   [mysql] This may be due to a blocked port, missing dependencies,
   [mysql] improper privileges, a crash, or a shutdown by another method.
   [mysql] Press the Logs button to view error logs and check
   [mysql] the Windows Event Viewer for more clues.
   [mysql] If you need more help, copy and post this
   [mysql] entire log window on the forums.
   ```

***Important:*** Do not delete the `ibdata1` file as it contains critical data for your databases.

1. **Stop MySQL Service:**
   - Open XAMPP Control Panel.
   - Stop the MySQL service if it is running.

2. **Backup Existing Data:**
   - Go to the `xampp/mysql` folder.
   - Rename the `data` folder to `data_old`.

3. **Restore from Backup:**
   - Locate the `backup` folder inside `xampp/mysql`.
   - Copy the `backup` folder and rename the copy to `data`.

4. **Restore Your Databases:**
   - Open the `data_old` folder.
   - Copy all your database folders (except `mysql`, `performance_schema`, and `phpmyadmin`) into the new `data` folder.

5. **Restore the `ibdata1` File:**
   - Copy the `ibdata1` file from `data_old` to the new `data` folder.

6. **Start MySQL:**
   - Open XAMPP Control Panel.
   - Start the MySQL service and verify that it runs successfully.


### Additional Notes

- If MySQL still fails to start, check the `mysql_error.log` file located in the `xampp/mysql/data` folder for more details.
- Ensure that no other application is using port **3306**. If there is a conflict, change the MySQL port in `my.ini` configuration file.

2. **Page Not Loading or 404 Error**:
   - Verify that the application started successfully without errors. Check the terminal logs for any exceptions.
   - Ensure that the URL `http://localhost:8080/sales` is correct.
   - If the page is not loading, try restarting the application.

3. **Port 8080 Already in Use**:
   - If you encounter an error like `Address already in use`, it means another process is using port 8080.
   - To resolve this, you can kill the process occupying port 8080.

### How to Find and Kill a Process Using Port 8080:

#### On macOS or Linux:
1. Open a terminal (as administrator) and run the following command to find the process using port 8080:

    ```bash
   lsof -i :8080
   ```
This will show the process ID (PID) of the application using port 8080.

1. To kill the process, use the kill command with the PID obtained from the previous step:
    ```bash
   kill -9 <PID>
   ```
Replace <PID> with the process ID.

#### On Windows:
1. Open a terminal (as administrator) and run the following command to find the process using port 8080:

    ```bash
   netstat -ano | findstr :8080
   ```
This will show the process ID (PID) of the application using port 8080.

1. To kill the process, use the kill command with the PID obtained from the previous step:
    ```bash
   taskkill /PID <PID> /F
   ```
Replace <PID> with the process ID.

## License

This project is licensed under the MIT License.

"# Sales-Report" 
