

### TODO: Create Basic Structure

- [X] Product Service

- [X] Account Service

- [X] Auth Service

- [X] Order Service
- [ ] Stock Tracking Service

- [X] Payment Service
    - [ ] Payment gateway
    - [ ] Paypal Service
    - [X] Credit Card Service
    - [ ] Bank Transfer Service

- [X] Shipping Service
- [X] Transaction Service

> __8080__: Reserved (Don't Use)
> __Greater than 8081__: Application micro-services
> __8070-8079__: Application supporting-services
> __LESS than 8070__: Others

| Service               | Port |
|-----------------------|------|
| product-service       | 8081 |
| account-service       | 8082 |
| order-service         | 8083 |
| --------------        | ---- |
| bankaccount-service   | 8084 |
| credit-service        | 8085 |
| paypal-service        | 8086 |
| --------------        | ---- |
| stock-service         | 8087 |
| shipping-service      | 8088 |
| transaction-service   | 8089 |
| --------------        | ---- |
| auth-service          | 8070 |
| --------------        | ---- |
| _eureka-server (DEV)_ | 8761 |


### TODO: Basic functionality for each Services and it's Database

1. Refer to the  Mind Map on [README.md](./README.md)
   - Add features as required
   - Separate Database configuration, MYSQL on localhost is fine for now
     - Will use same mysql server, But each services will have their own schemas/database.
2. General requirements __(PRODUCTION)__  __MUST BE DONE !!!__
   - Cloud-Config
   - Feign client
   - Security
   - Main Gateway (/auth, /product, /account, /order), And Payment gateway (/ccard, /paypal, /bank)
   - Docker to create images
   - Phase 2: Kubernetes:
     - Kubernetes deployment configs to deploy docker images
     - Spring-Cloud-Kubernetes (Implementing Discovery server using kubernetes, replacing eureka)
3. Development requirements __(DEVELOPMENT)__ __WORK-AROUND FOR LOCAL ENVIRONMENT !!!__
   - Discovery Server (Eureka), (To be handled by kubernetes, we will just remove this later)
   - Docker Compose (To test overall system on local machine, until we are ready with Kubernetes)
   - Optional: Will use secret encryption using spring-cloud-config for DEVELOPMENT, Later we can use kubernetes vault


### TODO: Deployment & Testing
- Deployment:
  - __Dockerfile__ for all services and __docker-compose.yml__ to run & test whole system on local
  - __k8s folder__ consisting of k8s config and instruction __k8s-readme__  for deployment.
- Testing:
  - Include __postman_exported_file__ to show available endpoints of services. So, its easier to work with.
  - Simple instruction "TEST.md" to run and test the overall system.

---  

> __Note: Please, Inform teammates what you are working on:__
> - Either update on Trello: [https://trello.com/b/YQffoOQR/miniproject-2](https://trello.com/b/YQffoOQR/miniproject-2)
> - Or mentioned it on readme [TODO.md](./TODO.md)
> - Or mentioned it on the teams
