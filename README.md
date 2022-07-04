# Movie Review API - SpringBoot
***

## OUR API LINK
***
https://moviereview-api.herokuapp.com


## EXTERNAL API
***
Our app is powered by IMDb API.

## IMPLEMENTATIONS
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



### METHODS
***
| Methods  | Description                                 |
|----------|---------------------------------------------|
| `GET`    | Returns information of one or more records. |
| `POST`   | Used to create new record in DB.            |
| `PUT`    | Updates date from a record.                 |
| `DELETE` | Deletes a record from the DB.               |


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

### AUTHENTICATION - AUTH0
***
Our API uses [AuthO](https://auth0.com/) as a way of authentication/authorization.

**How to Sign Up and Login:**

| Request | Link                      | Body                                                                                                               |
|---------|---------------------------|--------------------------------------------------------------------------------------------------------------------|
| `POST`  | /api/v1/users             | ![signup](https://user-images.githubusercontent.com/103673996/177134217-af200217-50ac-4845-b83a-c9c3f9afb730.png)  |
| `POST`  | /login                    | ![login](https://user-images.githubusercontent.com/103673996/177135060-4e368afa-313f-4877-84f0-75292f1fe6ce.png)  |

