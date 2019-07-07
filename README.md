# codcha - The coding challange

## Prerequisites
The provided solution is written in Java using maven. So the prerequisites for building the software are:
* Java JDK 11
* Maven 3

The environment variable **JAVA_HOME** must point to the root folder of the Java JDK 11.

## Building
After cloning the repository, call **mvn clean package** in the codcha folder.

## Testing
Create a folder with name **images** in the codcha folder. Put the dataset images there.

To start the software, call **java -jar target/codcha-0.0.1-SNAPSHOT.jar** in the codcha folder. After spring boot has successfully started, you may call the API using the port 8080.
