# Movie Review API - SpringBoot
***

### API LINK
***
https://moviereview-api.herokuapp.com
<br/><br/>

### EXTERNAL API
***
Our app is powered by IMDb API.
<br/><br/>

### IMPLEMENTATIONS
***
- Model Relationships
- Tests
- Spring Security
- External API
- Swagger
- Postman Collection
- Docker Compose
- Redis Cloud
- Heroku PostGres DB deployment
- Heroku Deployment
  <br/><br/>

### METHODS
***
| Request  | Description                                 |
|----------|---------------------------------------------|
| `GET`    | Returns information of one or more records. |
| `POST`   | Used to create new record in DB.            |
| `PUT`    | Updates date from a record.                 |
| `DELETE` | Deletes a record from the DB.               |
<br/><br/>

### RESPONSES
***
| Responses | Description                          |
|-----------|--------------------------------------|
| `200`     | Request executed succesfully.        |
| `400`     | Validation errors.                   |
| `403`     | Forbidden Access.                    |
| `404`     | Searched record not found.           |
| `405`     | Method not implemented.              |
| `409`     | Conflict trying to save same record. |
| `500`     | Server error.                        |
<br/><br/>

### AUTHENTICATION - AUTH0
***
Our API uses [AuthO](https://auth0.com/) as a way of authentication/authorization.
<br/><br/>
**Sign Up and Login:**

| Request | Description | Link          |
|---------|-------------|---------------|
| `POST`  | `SignUp`    | /api/v1/users |
              {
                 "firstName": "Example",
                 "lastName": "Example",
                 "email": "example@email.com",
                 "dateOfBirth": "1960-09-07",
                 "password": "palavrapass"
              }

| Request | Description | Link          |
|---------|-------------|---------------|
| `POST`  | `Login`     | /login        |
          {
             "email": "joao@email.com",
             "password": "palavrapass"
          }

<br/><br/>
### RESOURCE GRUPS
***