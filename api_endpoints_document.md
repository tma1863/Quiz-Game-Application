# ENDPOINTS
## 1. Question Endpoints
### GET /question/all
**Description** : Get all questions in the database.

```
[
  {
    "id": 1,
    "questionTitle": "What is Java?",
    "option1": "A programming language",
    "option2": "An OS",
    "option3": "A mobile app",
    "option4": "None",
    "rightAnswer": "A programming language",
    "difficultyLevel": "Easy",
    "category": "Java"
  }
]
```
### GET /question/category/{category}
**Description** : get questions by category (Java, Python, Kotlin, etc.).

```
GET / question/category/Java
```

### POST /question/add
**Description**: Add a new question.
**RequestBody**:

```
[    
    {
        "questionTitle": "What is Spring Boot?",
        "option1": "A Java Framework",
        "option2": "A car brand",
        "option3": "A game engine",
        "option4": "None",
        "rightAnswer": "A Java Framework",
        "difficultyLevel": "Medium",
        "category": "Spring"
    }
]
```

### PUT /question/update/{id}
**Description**: Update an existing question by ID.
**RequestBody**:

```
[
    {
        "questionTitle": "What is Spring Boot?",
        "id": 25,
        "option1": "A Java Framework",
        "option2": "A car brand",
        "option3": "A game engine",
        "option4": "None",
        "rightAnswer": "A Java Framework",
        "difficultyLevel": "Medium",
        "category": "Spring"
    }
]
```

### DELETE /question/delete/{id}
**Description**: Delete an existing question by ID.

## 2. Quiz Endpoints
### GET /quiz/create
**Description**: Return a quiz entity with number of random questions by category from database.

```
GET /quiz/create?category={category}&numOfQuestion={numOfQuestion}&title={title}
```

### POST /quiz/submit/{quiz_id}
**Description**: Calculate client score bases on their answers on given questions in a specific quiz.
**RequestBody**:

```
[
    {
        "id": 8,
        "response": "to extends"
    },
    {
        "id": 10,
        "response": "255"
    }
]
```






