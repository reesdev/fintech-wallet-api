Wallet Service API
Overview

A fintech-style backend system for managing users, wallets, and financial transactions.

This project demonstrates production-ready backend practices such as:

- JWT authentication

- Transaction consistency

- Idempotent transfer handling

- Pagination and filtering

- Excel export

- Structured API responses

Tech Stack

- Java 21

- Spring Boot

- Spring Security (JWT)

- PostgreSQL

- JPA / Hibernate

- Apache POI

Features
- Authentication

    - Register

    - Login (JWT-based)

- Wallet

    - Get wallet balance

    - Deposit money

    - Transfer money (with idempotency)

- Transactions

    - Transaction history (pagination + filtering)

    - Excel export

- User

    - Get all users

    - Get user by ID

API Structure
- /api/v1/auth
- /api/v1/wallet
- /api/v1/transactions
- /api/v1/users

Key Concepts Implemented

- Layered Architecture

- DTO & Validation

- Global Exception Handling

- Transactional Consistency

- Idempotent API (referenceId)

- Clean API Response Structure

![Wallet Service Api System Architecture.png](Wallet%20Service%20Api%20System%20Architecture.png)

![Wallet Service Api ERD (Entity Relationship Diagram).png](Wallet%20Service%20Api%20ERD%20%28Entity%20Relationship%20Diagram%29.png)

Example API Response:

    {
        "timestamp": "...",
        "status": "SUCCESS",
        "code": "TRANSFER_SUCCESS",
        "message": "Transfer successful",
        "data": {
            "balance": 900 
        }
    }

How to Run :

    git clone <your-repo>
    cd wallet-service-api
    mvn clean install
    mvn spring-boot:run

Database Setup:
1. Create PostgreSQL database:
 
        CREATE DATABASE wallet_db;

2. Update application.properties:


        spring.datasource.url=jdbc:postgresql://localhost:5432/wallet_db 
        spring.datasource.username=your_username 
        spring.datasource.password=your_password

3. Run the application:

   Tables will be created automatically by Hibernate.

Author: Reesky Adhadina

