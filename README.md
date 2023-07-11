# Library Management System
It is a Spring Boot project which represents online library, where users can authenticate and interact with the service.

## General info
### Functional
The functional of the application is scaleable. By now it includes issuing, downloading and donating books. 

To issue the book you need to open the catalogue where information of each book is displayed in the table. It is possible to see which books is issued for the authenticated user and download them.

### Security
The authentication is implemented with JSON Web Token. Token is generated when user is authorized, stored in the cookie 'user-id' and expires in 24 hours.

## Technologies
The project is created with:
* Java 19
* Maven
* Spring Framework
  + Spring Boot
  + Spring Security: authentication logic
  + Spring Data JPA: database queries generation
* Lombok: cuts down the extra code
* MySQL
* Thymeleaf, CSS: designing page layout

## Setup
