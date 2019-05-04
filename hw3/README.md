# split wordLadder

## Usage
run word ladder project
```
cd wordladder-oauth-consumer
mvn spring-boot:run
```

run authentication project
```
cd wordladder-oauth-server
mvn spring-boot:run
```

get wordLadder without authentication will redirect to the login page of wordladder-oauth-server
![](images_for_readme/noAuth.png)

This project uses oauth2 authentication code type. Use postman to login. The username is 'username' and password is 'password'.
![](images_for_readme/oauth2-auth-up.png)

After authentication, we can access word ladder.
![](images_for_readme/authed.png)

## docker
you can get project from docker
```
docker pull 1197744123/wordladder-oauth2-consumer
docker pull 1197744123/wordladder-oauth2-server
```