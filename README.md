# How To Run:
## Method 1:
In this method the events data initializtion is done manually.

### Step 1:
- Open docker-compose.yml file in the project root directory
- Verify that ```spring.jpa.hibernate.ddl-auto=update``` and ```spring.sql.init.mode=never``` under ```app:environment```
- Run :
```bash 
docker-compose build
```
- Run :
```bash 
docker-compose up
```
Now the application should be up and running, but no events data exist in the DB. In the next setup we will initialize the data.
- Connect to Mysql container (use whichever suitable method). Database connection details can be found in ```.env``` file in the root directory.
- Copy the SQL query in ```src\main\resources\data.sql```.
- Execute the query above on the event table.
The API is now ready to use.
## Method 2:
- Open docker-compose.yml file in the project root directory
- Verify that ```spring.jpa.hibernate.ddl-auto=update``` and ```spring.sql.init.mode=never``` under ```app:environment```
- Run :
```bash 
docker-compose build
```
- Run :
```bash 
docker-compose up
```
- After the application starts shut it down.
- Open docker-compose.yml again and update these values ```spring.jpa.hibernate.ddl-auto=none``` and ```spring.sql.init.mode=always``` .
- Run :
```bash 
docker-compose up
```
The API is now ready to use but just to avoid any issues reset the values in the docker-compose to ```spring.jpa.hibernate.ddl-auto=update``` and ```spring.sql.init.mode=never```.

# How To Test:
To check the available endpoints and their definition go to http://localhost:6868/swagger-ui/
