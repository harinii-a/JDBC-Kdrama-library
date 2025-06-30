# 🎬 K-Drama Library – Java JDBC Project

A simple console-based Java application to manage your favorite K-Dramas using **JDBC** with **MySQL**. This project helps users **view**, **add**, **update**, **delete**, and **search** K-Dramas based on genres and status.

---

## 📌 Features

-  View all K-Dramas
-  Add new K-Dramas
-  Search by genre
-  Update status (e.g., *Completed*, *Ongoing*)
-  Delete a drama by title
-  Uses parameterized SQL to prevent SQL injection

---
## 💻 Sample Console UI


1.View K-Drama  
2.Add K-Drama  
3.Search K-Drama by Genre  
4.Update status of K-Drama  
5.Delete K-Drama  
6.Exit  

Enter your choice:

---

## 🛠 Technologies Used

- Java (JDK 8+)
- JDBC (Java Database Connectivity)
- MySQL
- Console I/O (`Scanner`)
- Environment variables for DB password security

---

## 🗂 Table Structure

```sql
CREATE DATABASE kdramalist;

USE kdramalist;

CREATE TABLE kdrama (
    title VARCHAR(100) PRIMARY KEY,
    genre VARCHAR(50),
    status VARCHAR(50)
);
```


## ▶️ How to Run the Project

```bash
# 1. Clone or download the repository
git clone https://github.com/your-username/kdrama-library.git
cd kdrama-library
sql
Copy
Edit
-- 2. Set up MySQL and execute the following SQL schema
CREATE DATABASE kdramalist;

USE kdramalist;

CREATE TABLE kdrama (
    title VARCHAR(100) PRIMARY KEY,
    genre VARCHAR(50),
    status VARCHAR(50)
);
bash
Copy
Edit
# 3. Set your MySQL DB password as an environment variable

# For Linux/macOS
export DB_PASSWORD=your_mysql_password

# For Windows CMD
set DB_PASSWORD=your_mysql_password
bash
Copy
Edit
# 4. Compile and run the Java file
javac JDBCmain.java
java JDBCmain

---



