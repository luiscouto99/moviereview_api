# Movie Review API - SpringBoot
***

### API LINK
***
https://moviereview-api.herokuapp.com/swagger-ui/index.html
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

## RESOURCE GRUPS
***
### MOVIE ( api/v1/movie )
| Request  | Description    | Link              |
|----------|----------------|-------------------|
| `GET`    | Get All Movies | api/v1/movie      |
| `DELETE` | Delete Movie   | api/v1/movie/{id} |

| Request  | Description    | Link              |
|----------|----------------|-------------------|
| `POST`   | Add Movie      | api/v1/movie      |
| `PUT`    | Update Movie   | api/v1/movie/{id} |
            {
                "title": "The Shawshank Redemption",
                "fullTitle": "The Shawshank Redemption (1994)",
                "image": "https://imdb-api.com/images/original/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_Ratio0.6751_AL_.jpg",
                "year": "1994",
                "releaseDate": "1994-10-14",
                "runtimeStr": "2h 22min",
                "contentRating": "PG",
                "actorList": [
                    {
                        "id": 1
                    }
                ],
                "directorList": [
                    {
                        "id": 1
                    }
                ],
                "writerList": [
                    {
                        "id": 1
                    }
                ],
                "genreList": [
                    {
                        "id": 1
                    }
                ]
            }

### MOVIE_SEARCH ( api/v1/movie/search )
| Request | Description            | Link                       | Parameters                     |
|---------|------------------------|----------------------------|--------------------------------|
| `GET`   | Search By              | api/v1/movie/search        | id, title, year, contentrating |
| `GET`   | Search Movie Actor Has | api/v1/movie/search/actor  | name                           |
| `GET`   | Search By Genre        | api/v1/movie/search/genre  | genre                          |
| `GET`   | Search Movie By Rating | api/v1/movie/search/rating | id                             |

### ACTOR ( api/v1/actor )
| Request  | Description    | Link              |
|----------|----------------|-------------------|
| `GET`    | Get All Actors | api/v1/actor      |
| `DELETE` | Delete Actor   | api/v1/actor/{id} |

| Request  | Description    | Link              |
|----------|----------------|-------------------|
| `POST`   | Add Actor      | api/v1/actor      |
| `PUT`    | Update Actor   | api/v1/actor/{id} |
            {
                "image": "https://upload.wikimedia.org/wikipedia/en/9/9a/Trollface_non-free.png",
                "name": "nuno"
            }

### DIRECTOR ( api/v1/director )
| Request  | Description        | Link            |
|----------|--------------------|-----------------|
| `GET`    | Get All Directors  | api/v1/director |
| `DELETE` | Delete Director    | api/v1/director |

| Request  | Description        | Link            |
|----------|--------------------|-----------------|
| `POST`   | Add Director       | api/v1/director |
| `PUT`    | Update Director    | api/v1/director |
            {
                "name": "José"
            }

### WRITER ( api/v1/writer )
| Request  | Description     | Link               |
|----------|-----------------|--------------------|
| `GET`    | Get All Writers | api/v1/writer      |
| `DELETE` | Delete Writer   | api/v1/writer/{id} |

| Request  | Description     | Link               |
|----------|-----------------|--------------------|
| `POST`   | Add Writer      | api/v1/writer      |
| `PUT`    | Update Writer   | api/v1/writer/{id} |
            {
                "name": "José"
            }

### GENRE ( api/v1/genre )
| Request  | Description    | Link              |
|----------|----------------|-------------------|
| `GET`    | Get All Genres | api/v1/genre      |
| `DELETE` | Delete Genre   | api/v1/genre/{id} |

| Request  | Description    | Link              |
|----------|----------------|-------------------|
| `POST`   | Add Genre      | api/v1/genre      |
| `PUT`    | Update Genre   | api/v1/genre/{id} |
            {
                "value": "Drama"
            }

---

### USER ( api/v1/users )
| Request  | Description   | Link              |
|----------|---------------|-------------------|
| `GET`    | Get All Users | api/v1/users      |
| `DELETE` | Delete User   | api/v1/users/{id} |

| Request  | Description   | Link              |
|----------|---------------|-------------------|
| `PUT`    | Update User   | api/v1/users/{id} |
        {
            "firstName": "Joaquim",
            "lastName": "Alberto",
            "email": "jqi@email.com",
            "dateOfBirth": "1976-10-02",
            "password": "palavrapass"
        }

### USER_SEARCH ( api/v1/users )
| Request  | Description    | Link                | Parameters                         |
|----------|----------------|---------------------|------------------------------------|
| `GET`    | Get User By Id | api/v1/users/{id}   | id                                 |
| `GET`    | Search Users   | api/v1/users/search | roleid, firstname, lastname, email |

### ROLE ( api/v1/users/roles )
| Request  | Description   | Link                    |
|----------|---------------|-------------------------|
| `GET`    | Get All Roles | api/v1/users/roles      |
| `DELETE` | Delete Role   | api/v1/users/roles/{id} |

| Request  | Description   | Link                    |
|----------|---------------|-------------------------|
| `POST`   | Add Role      | api/v1/users/roles      |
| `PUT`    | Update Role   | api/v1/users/roles/{id} |
            {
                "roleName": "Client"
            }

### FAVOURITES ( api/v1/users/favourite )
| Request  | Description        | Link                        | Parameters      |
|----------|--------------------|-----------------------------|-----------------|
| `GET`    | Get All Favourites | api/v1/users/favourite/{id} | id              |
| `POST`   | Add Favourite      | api/v1/users/favourite      | userid, movieid |
| `DELETE` | Delete Favourite   | api/v1/users/favourite      | userid, movieid |

---

### REVIEW ( api/v1/review )
| Request  | Description     | Link          |
|----------|-----------------|---------------|
| `GET`    | Get All Reviews | api/v1/review |

| Request  | Description     | Link               |
|----------|-----------------|--------------------|
| `POST`   | Add Review      | api/v1/review      |
| `PUT`    | Update Review   | api/v1/review/{id} |
            {
                "userId": "1",
                "movieId": "1",
                "review": "this movie is really really really bad",
                "ratingId": 1
            }

| Request  | Description     | Link            |
|----------|-----------------|-----------------|
| `DELETE` | Delete Review   | api/v1/review   |
            {
                    "id": 2,
                    "userId": "1",
                    "movieId": "1",
                    "review": "this movie is really really really bad",
                    "ratingId": 1
            }

### REVIEW_GET ( api/v1/review/byuser )
| Request  | Description          | Link                      | Parameters      |
|----------|----------------------|---------------------------|-----------------|
| `GET`    | Get All User Reviews | api/v1/review/byuser/{id} | id              |

### RATING ( api/v1/rating )
| Request  | Description     | Link               |
|----------|-----------------|--------------------|
| `GET`    | Get All Ratings | api/v1/rating      |
| `DELETE` | Delete Rating   | api/v1/rating/{id} |

| Request  | Description     | Link               |
|----------|-----------------|--------------------|
| `POST`   | Add Rating      | api/v1/rating      |
| `PUT`    | Update Rating   | api/v1/rating      |
            {
                "rate": "1"
            }

---
