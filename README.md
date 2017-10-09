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

1. go to directory that contains 'Dockerfile' then build a docker container using command below
`docker build -t triplesic/springboot-kotlin-boilerplate .`

2. create new container instant using command below

run on ubuntu cloud host
`docker run -d --net="host" -p 8080:8080 -v <YOUR_CONFIG_PATH>:/config --name springboot-kotlin-01 triplesic/springboot-kotlin-boilerplate`

run on mac localhost
`docker run -d -p 8080:8080 -v <YOUR_CONFIG_PATH>:/config --name springboot-kotlin-01 triplesic/springboot-kotlin-boilerplate`


* please change "<YOUR_CONFIG_PATH>" to the real application.properties path


-- remark --
when you call other 'localhost' api(s) via  docker on Mac
you have change 'localhost' to 'docker.for.mac.localhost'

such as MySql url
jdbc:mysql://localhost/factory
you have to change to
jdbc:mysql://docker.for.mac.localhost/factory