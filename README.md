# PROJECT DESCRIPTION
This project is a Spring Boot-based Quiz Application backend localhost designed for managing a question bank and generating quizzes dynamically. It provides a RESTful API that allows clients (such as Postman, frontend applications) to:
- Create and manage multiple-choice questions.
- Build quizzes, each quiz will be selected by random questions from an input of specific category and the number of questions as well.
- Retrieve quiz questions without exposing the correct answers.
- Submit quiz answers and receive a total score.

The application uses PostgreSQL for data presistence and Spring Data JPA for easy database operations. It follows a clean architecture with clear separation between controller, service, and data-access layers. 

## Requirements
- Java 17
- PostgreSQL 17
- Maven
- Postman (for testing)
- Spring Boot 4.0.0

## Base URL
```
http://localhost:8080/question
```
The details of API Endpoints will be written in [`api_endpoints_document.md`](https://github.com/tma1863/Quiz-Game-Application/blob/main/api_endpoints_document.md).

# OVERALL ARCHITECTURE
This project follows a clean 3-layer Spring Boot architecture as below:
![](image/quizapp_drawio.png)

## 1. Controller Layer (`controller/`)
- Handles HTTP requests and routes them to the service layer.
- Does not contain any processing logic.
- Returns `ResponsesEntity<>` to provide proper HTTP status codes.

### Classes
- `QuestionController`
- `QuizController`

## 2. Service Layer (`service/`)
- Contains all processing logic.
- Interacts with DAOs to fetch or update data.
- Converts entities to DTOs.

### Classes
- `QuestionService`
- `QuizService`

## 3. DAO Layer (`dao/`)
- Responsible for interacting with the database via Spring Data JPA.
- Contains custom queries (native SQl for random question selection).

### Interfaces
- `QuestionDao`
- `QuizDao`

## 4. Model Package (`model/`)
- Contains database entities and DTOs.
- Entities: `Question`, `Quiz`.
- DTOs: `QuestionWrapper`, `Response`.

## 5. Application
- The entry point of the entire application is: `QuizApplication.java`.


# MAIN FEATURES
## 1. Question Management (performing CRUD)
- Add new questions.
- Update existing questions by ID.
- Delete questions by ID.
- Fetch all questions.
- Fetch questions by category.

## 2. Quiz Creation
- Create a quiz by category, number of questions, quiz title.
- Random question selection using a native SQL query.

## 3. Secure Quiz Question Retrieval
When fetching quiz questions:
- Correct answer are hidden using a `QuestionWrapper` DTO.
- Only safe fields are returned, such as questionTitle, and 4 multiple choice answers.

## 4. Quiz Submission & Auto-Scoring
- Accepts user/client responses as JSON with 2 fiels `question_id` and `responses`.
- Compares user answers with the correct ones.
- Return the total score.

## 5. PostgreSQL Intergration
- Automatic schema creation/updates via Hibernate.
- Uses PostgreSQl dialect for optimal compatibility.

## 6. Error Handling & HTTP Status Codes
Proper responses for:
- 200 OK
- 201 CREATED
- 202 ACCEPTED
- 400 BAD REQUEST
- 404 NOT FOUND
- 500 INTERNAL SERVER ERROR

# FOLDER STRUCTURE
```
src/main/java/com/demo/quizapp/
│
├── QuizappApplication.java
│
├── controller/
│   ├── QuestionController.java
│   └── QuizController.java
│
├── model/
│   ├── Question.java
│   ├── QuestionWrapper.java
│   ├── Quiz.java
│   └── Response.java
│
├── service/
│   ├── QuestionService.java
│   └── QuizService.java
│
└── dao/
    ├── QuestionDao.java
    └── QuizDao.java
```
The `application.properties` is located under:
```
src/main/resources/application.properties
```

# DATABASE SCHEMA
The database contains 3 main tables:
## `question` table:

| Column             | Type      | Description                    |
| ------------------ | --------- | ------------------------------ |
| `id`               | SERIAL PK | Primary key, auto-generated    |
| `question_title`   | VARCHAR   | Text of the question           |
| `option1`          | VARCHAR   | Multiple-choice option 1       |
| `option2`          | VARCHAR   | Multiple-choice option 2       |
| `option3`          | VARCHAR   | Multiple-choice option 3       |
| `option4`          | VARCHAR   | Multiple-choice option 4       |
| `right_answer`     | VARCHAR   | Correct answer                 |
| `difficulty_level` | VARCHAR   | Easy / Medium / Hard           |
| `category`         | VARCHAR   | Question category (e.g., Java, Python, Kotlin) |

## `quiz` table

| Column  | Type      | Description |
| ------- | --------- | ----------- |
| `id`    | SERIAL PK | Primary key |
| `title` | VARCHAR   | Quiz title  |

## `quiz_question` table
Since `Quiz` has a `@ManyToMany` relationship with `Question`, Spring Boot will automatically create a join table `quiz_question` as below:

| Column  | Type      | Description |
| ------- | --------- | ----------- |
| `quiz_id`    | INT | Quiz id |
| `question_id` | INT  | Question id  |


