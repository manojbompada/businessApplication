# businessApplication
application for business profiles

Developed using spring boot with database as mysql

Steps to run api project:

->	Clone the project to your local from https://github.com/manojbompada/businessApplication.git
->	Open in IDE (I used eclipse) as Maven project.
->	Make sure all the properties like database url, username, password, server port are properly configured in application.properties file as per your credentials. These properties are currently set as per my credentials.
-> Make sure you are using the server port where no other process is running.
-> If you are using your own DB, please refer the DDL scripts below and create the tables.
->	Run the project by going into BusinessApplication.java file and run as Java application.


DDL scripts for tables creation:

Business_clients 

CREATE TABLE business_clients (
  id bigint(100) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  phone_number bigint(10) NOT NULL,
  url varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id) ) 

business_schedules 

CREATE TABLE business_schedules (
  business_id bigint(100) NOT NULL,
  week_day_id int(11) NOT NULL,
  open_time bigint(100) NOT NULL,
  close_time bigint(100) NOT NULL,
  PRIMARY KEY (business_id,week_day_id),
  KEY day_id (week_day_id),
  CONSTRAINT day_id FOREIGN KEY (week_day_id) REFERENCES days_of_the_week (day_id),
  CONSTRAINT `id` FOREIGN KEY (`business_id`) REFERENCES `business_clients` (`id`) ON DELETE CASCADE)

days_of_the_week 

CREATE TABLE days_of_the_week (
  day_id int(11) NOT NULL,
  day_name varchar(45) NOT NULL,
  PRIMARY KEY (day_id),
  UNIQUE KEY day_id_UNIQUE (day_id),
  UNIQUE KEY day_name_UNIQUE (day_name)
) 
