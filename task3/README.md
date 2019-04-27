# Task 3

## Note

You can find the image repo in [https://hub.docker.com/r/1197744123/word-ladder-jwt-auth-spring](https://hub.docker.com/r/1197744123/word-ladder-jwt-auth-spring)

The related Dockerfile is in /hw2/Dockerfile


## User Guide
1. pull image for wordLadder
```
docker pull 1197744123/word-ladder-jwt-auth-spring
```

2. pull mysql image
```
docker run -d \
      -p 2012:3306 \
     --name mysql-docker-container \
     -e MYSQL_ROOT_PASSWORD=root123 \
     -e MYSQL_DATABASE=spring_app_db \
     -e MYSQL_USER=app_user \
     -e MYSQL_PASSWORD=test123 \
        mysql:latest
```
***Note:*** In the wordLadder project, I use database to store user info. The default database connector use mysql container named *mysql-docker-container* with username *app_user* and password *test123*. Therefore, the command line related should be ***identical*** as shown above. However you can change port 2012 to whatever you like.

3. initialize mysql database
```
mysql -u app_user -p -h [your localhost] -P 2012
```
Use the command to connect to mysql in the container you just ran.
```
use spring_app_db;
insert into roles value(1,"ROLE_ADMIN");
insert into roles vlaue(2,"ROLE_USER");
```
Now you have finish initializing database.

4. run wordLadder container
```
docker run -t --name wordLadder-container --link mysql-docker-container:mysql -p 8087:8080 1197744123/word-ladder-jwt-auth-spring
```
you can now have access to wordLadder in localhost:8087.


