# Amazin Online Bookstore
[![Java CI with Maven](https://github.com/owen-petersen/Amazin_Online_Bookstore/actions/workflows/maven.yml/badge.svg)](https://github.com/owen-petersen/Amazin_Online_Bookstore/actions/workflows/maven.yml)
[![Build and deploy JAR app to Azure Web App - amazin-online-bookstore](https://github.com/owen-petersen/Amazin_Online_Bookstore/actions/workflows/main_amazin-online-bookstore.yml/badge.svg)](https://github.com/owen-petersen/Amazin_Online_Bookstore/actions/workflows/main_amazin-online-bookstore.yml)
---
**Deployed site link**: https://amazin-online-bookstore.azurewebsites.net/

---
## Project Description
The project prototypes an online book store. The project goal is to gain experience with the Spring framework and use it to create a webapp with sufficient features. 

It aims to provide the following key features:
- Store owner can upload and edit book details and inventory
- Users can search and browse through the store's books and sort/filter based on specific book details like:
  - Author
  - Publisher
  - Publishing date
  - Genre
- Users can place books they are interested in into their cart (or remove them from the cart if needed) and proceed to checkout and purchase the books
  - The purchases will be simulated
  - Purchases will not exceed store inventory
- Users can view book recommendations based on past purchases
  - This will involve using the similar purchases of other users to recommend books that a user has not purchased

### Project State
#### Oct 31, 2025
- No Status
  - Allow user to search/filter list of books
  - Refactor code for supporting user sessions
  - Implement checkout page
  - Allow adding and removing books from inventory
  - Add roles for users
  - Display book pictures on UI
- Todo
  - Create user log-in
- In Progress
  - Create item checkout cart
  - Create main web pages
- Done
  - Create CrudRepository classes for all tables
- Plan for next sprint
  - Allow user to search/filter list of books
  - Refactor code for supporting user sessions
  - Implement checkout page
  - Allow adding and removing books from inventory
  - Add roles for users
  - Display book pictures on UI
#### Nov 17, 2025
- No Status
  - Refactor code for supporting user sessions
  - Display book pictures on UI
- Todo
  - Add roles for users
  - Allow adding and removing books from inventory
  - Allow user to search/filter list of books
  - Add reference to Person in each Purchase
- In Progress
  - Implement checkout page
- Done
  - Create user log-in
  - Create item checkout cart
  - Create main web pages
  - Create readme file
  - Create CrudRepository classes for all tables
  - Create focus web page for each book
- Plan for next spring
  - TBD

---
## UML Diagrams
![](/src/main/resources/project%20diagrams/Person.png)
![](/src/main/resources/project%20diagrams/Book.png)
![](/src/main/resources/project%20diagrams/Purchase.png)

## Database Schema Diagram 
![](/src/main/resources/project%20diagrams/Database_schema.png)
---
## Contributors
- Faareh Bashir (@Faareh)
- Faiaz Ahsan (@faiazahsan)
- Mahmoud Chaaban (@Ma7vx)
- Marvel Adotse-Ogah (@marvel-ao)
- Owen Petersen (@owen-petersen)
