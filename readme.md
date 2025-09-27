# Task Manager REST API

## Project Overview
This is a REST API for managing and executing shell commands, built with Spring Boot and MongoDB.

## Features
- Create, read, update, delete tasks
- Execute shell commands
- Search tasks by name
- MongoDB data persistence

## Technology Stack
- Java 11
- Spring Boot
- MongoDB
- Maven

## API Endpoints
- `GET /api/tasks` - Get all tasks or specific task by ID
- `PUT /api/tasks` - Create new task
- `DELETE /api/tasks` - Delete task by ID  
- `GET /api/tasks/search` - Search tasks by name
- `PUT /api/tasks/execute` - Execute task command

## Screenshots

### 1. MongoDB Running Successfully
![MongoDB Running](screenshots/mongodb.png)

### 2. Spring Boot Application Starting
![Spring Boot Starting](screenshots/spring-boot.png)

### 3. API Testing - Create Task
![Create Task](screenshots/create-task.png)

### 4. API Testing - Execute Task  
![Execute Task](screenshots/execute-task.png)

## How to Run
1. Start MongoDB: `mongod --dbpath ~/data/db`
2. Build project: `mvn clean install`
3. Run application: `mvn spring-boot:run`
4. Test endpoints on `http://localhost:8080/api/tasks`