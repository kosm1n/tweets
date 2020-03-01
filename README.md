# How to run

## From IDE

In a terminal launch the docker containers :

```bash
cd tweets/src/test/resources/docker
docker-compose up

Create a new `Run Configuration` (`Run -> Edit configurations...`) with the following data:


| Param                     | Value           
| -------------             |:-------------:
| _Main class_              | _com.orangebank.TweetsApplication_   
| _Use classpath of module_ | _clans-app_ 
| _JRE_                     | _&gt; 11_      

## From Maven

```bash
mvn spring-boot:run
```

## External requirements
* Postgresql

Install Docker: [https://www.docker.com/products/docker-desktop]

## First step

Go to application.yml and set your oauth twitter credentials

com.cosmin.tweets.config.oauth:
* consumerKey: ****
* consumerSecret: ****
* accessToken: ****
* accessTokenSecret: ****

## Second step

Call [/tweets/consume] with a valid twitter user name to gather some tweets.

## Third step

Call other endpoints like [/tweets] to get the saved tweets.