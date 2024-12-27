# SalonLink - Seamless Salon Reservation Application

SalonLink is a user-centered web application designed to enable seamless salon service reservations. The application focuses on providing an intuitive user interface (UI) and enhanced user experience (UX) through thoughtful design, implementation, and iterative evaluation.

---

## Features
- **Registration and Login:**
  - Validations for name, email, address, and password.
  - Password visibility toggle on the login page.
  - Confirmation dialog upon successful registration.

- **Dashboard:**
  - Lists all available salons with key details.
  - Navigation bar for quick access to home, profile, and settings.

- **Search for Service:**
  - Users can search for salons offering specific services.
  - Error handling for empty results.
  - Visual elements like service icons for better user engagement.

- **Select Salon:**
  - Allows users to select multiple services using checkboxes.
  - Displays services offered by the selected salon.

- **Make Reservation:**
  - Users can finalize reservations with confirmation dialogs.
  - Feedback provided upon successful reservation.

---

## Technologies Used
- **Frontend:**
  - HTML5, CSS3, and Thymeleaf templates.
  - Responsive design for mobile and desktop compatibility.

- **Backend:**
  - Spring Boot with Java.
  - RESTful APIs for seamless communication.
  - BCrypt for password encryption and Spring Boot Web security.
  - PostgreSQL for data storage.

- **Testing and Deployment:**
  - Heuristic evaluation based on Nielsen's 8 Golden Rules.
  - Usability testing with user feedback integration.
  - Deployed on [AWS Free Tier](https://aws.amazon.com/free/).

---

## Getting Started
Follow these steps to set up and run the SalonLink application locally.

### Prerequisites

Ensure the following software is installed on your machine:

- [Java JDK 11 or higher](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Git](https://git-scm.com/)


### Installation and running
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/salonlink.git
   cd salonlink
   
2. Configure Application Properties
Edit the src/main/resources/application.properties file and provide your PostgreSQL database credentials:
spring.datasource.url=jdbc:postgresql://localhost:5432/salonlinkdb
spring.datasource.username=your-username
spring.datasource.password=your-password

3. Build and Run the Application
Use Maven to build and run the Spring Boot application:
bash
mvn spring-boot:run

4. Access the Application
Once the application is running, open your web browser and navigate to:
http://localhost:8080/api/SalonLink/registration

--- 

## Project Structure

salonlink/
├── src/
│   ├── main/
│   │   ├── java/com/SAD/SalonLinkApp/
│   │   │   ├── controller/
│   │   │   ├── security/
│   │   │   ├── service/
│   │   │   ├── repo/
│   │   │   └── model/
│   │   ├── resources/
│   │   │   ├── templates/  # HTML files
│   │   │   └── static/css/ # Stylesheets
│   └── test/
├── pom.xml  # Maven configuration
└── README.md

---

## Contributions
Contributions are welcome! Please open an issue or submit a pull request with your changes.
  
