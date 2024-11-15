![image](https://github.com/user-attachments/assets/e2b1bbcc-8b35-4eb0-8895-0f8564eb06a2)# nexign
Nexign developer test project

1. Spring Boot
2. PostreSQL
3. REST API
4. Kafka
5. Spring Data JPA
6. Docker
7. Swagger http://localhost:8080/swagger-ui/index.html
8. Redisson for concurrent lock mechanism

POTs:

1. Containers
![image](https://github.com/user-attachments/assets/d01b2b94-63f2-45d5-8336-f36481291f42)
2. Get all tasks
![image](https://github.com/user-attachments/assets/e8475f30-ac9c-4e30-9c80-553ce695b374)
3. Get task status by id
![image](https://github.com/user-attachments/assets/b3565999-6719-486e-a67e-3393e74caf7d)
4. Logs example with recieved message (schedulled kafka producer with random task name and duration)
![image](https://github.com/user-attachments/assets/645f7a3d-77ec-4691-9a8d-378286c50d79)
6. Error durring attemt to delete task which is is progress (lock mechanism with redisson)
   6.1 Manual task creation by REST
   ![image](https://github.com/user-attachments/assets/31ddd338-05f9-454c-92ee-fe147c2792b3)
   6.2 Delete attempt by id
   ![image](https://github.com/user-attachments/assets/8b071d6e-e815-45f2-a002-034e413b0aff)
