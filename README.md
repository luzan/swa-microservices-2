### Mind Map
![Mind Map](docs/SWA%20Microservice.jpg)

## # How to run ? _Options:_  

```cmd
mvn clean install -DskipTests

# If minkiube, also run -> minikube docker-env
docker compose build

kubectl create -f k8s/configs-secrets
kubectl create -f k8s/appcore
kubectl create -f services
kubectl create -f k8s/gateways

# add "192.168.49.2 backend.minikube.local" to /etc/host
sudo nano /etc/hosts

==========================
127.0.0.1	localhost
192.168.49.2 backend.minikube.local
==========================
```

Testing
```
# This is for create user

curl --location --request POST 'http://backend.minikube.local/auth/create-user' \
--header 'Content-Type: application/json' \
--data-raw '{
    "fullName": "Test user",
    "email": "test1",
    "password": "test1"
}'

# This is for Login

curl --location --request POST 'http://backend.minikube.local/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "test1",
    "password": "test1"
}'
```


__Development:__
1. Running Single Service at a time:
   - __If Using IDE (Using IntelliJ)__: Run each services by clicking their "run" icon
   - __Without IDE (Using source code)__: On terminal of specific service, type `mvn spring-boot:run`
   - __Only JAR file__: First, Create jar `mvn clean install` and run `java -jar target/service1.jar`

2. Running the application using docker
   - If cloned first time, In the root directory run `mvn clean install` or use IDE, maven -> lifecycle -> install (to create jar for all services) 
   - Then, In terminal type: `docker compose up`. (this will run mysql, and other services)

    ```cmd 
   mvn clean install
   docker compose up
    ```

> Running mysql using docker compose here, will create multiple schemas
> these are defined in folder - `zmysql/init/`, 01-schemas.sql file and specified in docker-compose yml

__Deployment:__
- __Using Kubernetes:__ Create kubernetes config files and use it to deploy each services images
  - Push all images to docker hub, and deploy using that image url

  

## # How to create this project, and how to add new modules/services ?  
__Steps: Creating multi-module project__  
1. Create new project "swa-2", and select buildSystem maven.
2. Then either Create new services or Add existing ones
   - __Create (New Module):__ right click on project "swa-2" and create any module
      - e.g click on: new -> module -> springInitializr
   - __Add (Add Module):__ right click on "swa-2", open module settings
     - under Project Settings -> Modules -> click on + -> import module -> Select .pom file or directory -> then import module from external model -> select maven -> Apply
     - After that go to pom.xml of root project "swa-2" and add to `<modules> ... <modules>`

## # How to contribute to the project ?

__INSTRUCTION:__
1. Use _"develop"_ branch to make and commit changes.
2. We will merge to __"main"__ on the day-end or when services are integrated and working fine.

>  __Want to know, What to contribute and how to do it ?__ 
> 
> Please, Check out [TODO.md](./TODO.md) , and discuss with the team

--- 

## # Need Further help ?

__Let's Discuss and lets do it together.__



