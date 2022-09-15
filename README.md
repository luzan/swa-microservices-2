### Mind Map
![Mind Map](docs/SWA%20Microservice.jpg)

## # How to run ? _Options:_  
__Development:__
- Run each services using IDE. "run" icon to run
- To run services, On their terminal type `./mvnw spring-boot:run`
- Create jar and run. `./mvnw clean install` to build jar and `java -jar service1.jar`

__Preferred:__
- Docker compose file, in terminal `docker compose up`

__Deployment:__
- Create kubernetes config files and use it to deploy services images on k8s cluster 

  

## # How to create this project Or continue creating new modules/services ?  
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



