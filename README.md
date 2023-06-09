
# Table of Contents
* [Find & GO](#description)
* [Features](#features)
* [StoreLocator](#storeLocator)
* [Technologies](#technologies)
* [Installation](#installation)
* [UserStories](#userstories)
* [Acknowledgements](#acknowledgements)
* [Contributing](#contributing)



--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
![FastShoppingCart](https://github.com/Jaypad07/Find-Go-Angular/blob/main/src/assets/Find%26GOTransparent.png)

# Find&GO
The Find&GO project is a web application designed to help users quickly locate specific items when they are in a hurry. It aims to provide a seamless experience for finding items efficiently and minimizing the time spent in the shopping process. The application is primarily targeted towards individuals who lead busy lives and value time-saving solutions. In addition, the application also provides users with accurate and up-to-date stock information for popular and trending items that may be of interest to their children. This feature ensures that users stay informed about the availability of sought-after products, allowing for informed purchasing decisions.

This project aims to create a Spring Boot-inspired API framework or library using the Go programming language. Spring Boot is a popular framework in the Java ecosystem for building Java-based applications, especially web applications, with ease.

## Features

#### Store Locator:
No need to waste time asking for directions or wandering aimlessly in the store. Find & Go uses your current location to find the nearest stores that have the item you're looking for.

#### Quick Search:
Find & Go allows you to search for specific items using its powerful search filter. Simply enter the item you need, and the app will provide step-by-step directions to the exact section or location where your desired item is located. Say goodbye to confusion and wasted time.

#### Time-Saving Solution:
Find & Go is designed to help you get in and out of stores as quickly as possible. No more waiting in line at information desks or searching through aisles. With Find & Go, you can find what you need and be on your way in no time.

![MainPic](https://github.com/Jaypad07/Find-Go-Angular/blob/main/src/assets/Find%26GO-ReadmeImg2.png)


## Technologies
* [Git Bash](https://gitforwindows.org/) - to traverse the file system, execute system
- **Java 17**: The programming language used for developing this application.
- **Cucumber 6.8.1**: The testing tool used for behavior-driven development.
- **Spring 2.7.8**: The framework for creating this web application.
    - **Tomcat** The server the Spring application runs on.
- **[Spring Initializer](https://start.spring.io/)**: The application used to boostrap the project structure.
- **Maven**: The build tool used to source dependencies.
- **IntelliJ IDEA**: This is the IDE (integrated development environment) used to create the application.
    - Build System: IntelliJ
    - JDK: corretto-17, Amazon Correto version 17.0.6
- **Postman**: Used to test endpoints and debug the API.
- **Git**: Used for version control on local computer and pushing changes to remote repository.
- **GitHub**: The hosting service for the remote repository, used for version control and branch management.
    - **[Github Projects](https://github.com/users/knnguyen2410/projects/3/views/3)**: Used for project planning and documentation of deliverables and timelines.
- **Google Chrome**: The browser used for accessing the H2 database engine.
- **[dbdiagram ERD Tool](https://dbdiagram.io/d/64777d42722eb7749428ec9f)**: Used to create the entity relationship diagram (ERD) during the planning process.
- **[Markdown Table Generator](https://www.tablesgenerator.com/markdown_tables)**: Used to document the REST API endpoints.


## Installation

1. Clone the Repository: Start by cloning the Find & Go repository to your local machine. Open your command line interface and run the following command:

git clone https://github.com/Jaypad07/Find-Go-Angular.git

2. Navigate to the Project Directory: Once the cloning process is complete, navigate to the project directory using the cd command:

cd Find-Go-Angular

3. Install Dependencies: Next, install the project dependencies. Usually, an Angular project utilizes npm (Node Package Manager) for managing dependencies. Run the following command to install the dependencies:

npm install
This command will read the package.json file and download all the necessary dependencies listed in it.

4. Build the Project: Once the dependencies are installed, you may need to build the project. In Angular projects, you can use the Angular CLI (Command Line Interface) to build the application. Run the following command:

ng build
This command will compile the project's source code and generate the necessary build artifacts.

5. Start the Application: After the project is built, you can start the application. Use the following command to start the development server:

ng serve
This command will start the application on a local development server. Open your web browser and visit http://localhost:4200 to view the running application.


## UserStories

Bronze:
1. Set up a new project using Angular, TypeScript, and Java.
2. Configure the development environment, including IDEs and necessary tools.
3. As a user, I want to be able to search for a specific item and find its location within the store using the app.
4. As a user, I want to see real-time stock inventory information for the items I'm interested in, so I can make informed decisions about my purchases.
5. Create RESTful API endpoints using Java.
6. Implement endpoints for fetching the store map and real-time stock inventory.
7. Implement endpoint for retrieving the user's GPS location.
8. As a user, I want to see multiple staplers and related supplies when I search for a stapler. This can be achieved by checking all items associated with the chosen item using the "Office Supplies Category" feature.
9. As an admin, I want to add fake store items for testing purposes.

Silver:
1. As a store manager, I want to be able to update the store map and layout in the app to reflect any changes in the store's physical setup.
2. As a store manager, I want to manage the stock inventory information within the app and ensure it is accurate and up to date.
3. As a store manager, I want to analyze data and insights from the app to make informed decisions about inventory management and store layout optimization.
4. As a manager, I should be able to update product prices.
5. As an app administrator, I want to have control over user permissions and access levels to ensure the security and integrity of the app's data.
6. As an admin, I want to create a custom store map.

Gold:
1. As a user, I want to view a store map on my phone to navigate through different sections and find the products I need.
2. As a user, I want the app to provide me with the most efficient route to locate all the items on my shopping list.
3. As a user, I want to receive notifications or alerts when items on my shopping list are out of stock or when there are special offers available.


## Dependencies

<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-java</artifactId>
			<version>${cucumber.version}</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-junit</artifactId>
			<version>${cucumber.version}</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-spring</artifactId>
			<version>${cucumber.version}</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>io.rest-assured</groupId>
			<artifactId>rest-assured</artifactId>
			<version>4.3.0</version>
			<scope>test</scope>
		</dependency>

		<!-- POSTGRES DEPENDENCY, connects PostGres to our Java Application  -->
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

		<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api -->
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl -->
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>

		<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson -->
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
	</dependencies>

## Support

For support, email me at JayPadilla.dev@gmail.com


## Acknowledgements

- [Kevin Barrios](https://github.com/dayjyun)
- [Kim Nguyen](https://github.com/knnguyen2410)
- [Maksym Zinchenko](https://github.com/maklaut007)
- [Lorena Rojas](https://github.com/lrojas4)
- [Obinna Umerah](https://github.com/ObinnaUmerah)
- [Suresh Sigera](https://github.com/sureshmelvinsigera)
- Dhrubo Chowdhury
- [Leonardo Rodriguez](https://github.com/LRodriguez92)


## Contributing

Contributions to the Find & Go project are always welcome!

Send me a message and let's get started.


