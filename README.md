# springboot-kotlin-boilerplate

The project was generated from https://start.spring.io/

How to

1. build and run the project with Gradle

2. call `localhost:8080/api/users` on the browser, the page will redirect to login form

3. use username and password below to access the api
  username: `user`
  password: `userX`
  
4. to call the API via `curl`  or `Postman`, please use Basic Auth with the same user & pass

---- Build & Run on docker ----

1. build a docker container using command below
`docker build -t triplesic/kotlin-sample .`

2. create new container instant using command below
`docker run -d --net="host" -p 8080:8080 -v /<YOUR_CONFIG_PATH>:/config --name springboot-kotlin-01 triplesic/kotlin-sample`
* please change "<YOUR_CONFIG_PATH>" to the real application.properties path