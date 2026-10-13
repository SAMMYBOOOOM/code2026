# Code2026: Full-Stack E-Commerce Platform

**Code2026** is a comprehensive, full-stack E-Commerce simulation project designed to demonstrate modern web development practices. It covers the entire software development lifecycle, from scaffolding and user management to complex order processing, shipping systems, and backend data visualization.

This project serves as a practical implementation of a **Spring Boot 3 + Vue 3** architecture, simulating a real-world shopping platform.

## 📸 Screenshots

![chart](https://github.com/SAMMYBOOOOM/code2026/blob/16_Chart/img/chart.png)
![goods](https://github.com/SAMMYBOOOOM/code2026/blob/16_Chart/img/goods.png)
![users](https://github.com/SAMMYBOOOOM/code2026/blob/16_Chart/img/users.png)
![store](https://github.com/SAMMYBOOOOM/code2026/blob/16_Chart/img/store.png)
![orders](https://github.com/SAMMYBOOOOM/code2026/blob/16_Chart/img/orders.png)
![cart](https://github.com/SAMMYBOOOOM/code2026/blob/16_Chart/img/cart.png)

## 🚀 Tech Stack

### Backend

- **Language:** Java 21 (JDK 21)
    
- **Framework:** Spring Boot 3
    
- **ORM:** MyBatis
    
- **Database:** MySQL 8
    
- **Build Tool:** Maven
    

### Frontend

- **Framework:** Vue 3 (Composition API)
    
- **UI Library:** Element Plus
    
- **Charts:** ECharts
    
- **State Management:** Pinia
    
- **HTTP Client:** Axios
    

### Development Tools

- **IDE:** IntelliJ IDEA / VS Code
    
- **Database GUI:** Navicat
    
- **Runtime:** Node.js
    
- **Version Control:** Git
    

---

## ✨ Features

The project is developed iteratively, covering the following modules:

### 1. Foundation & User System

- **Scaffolding:** Initial setup of frontend and backend environments.
    
- **Authentication:** Secure Login, Sign-up, and Update Info functionality.
    
- **User Manager:** Basic user management capabilities.
    

### 2. Product & Content Management

- **Product Manager:** CRUD operations for products.
    
- **Product Info:** Detailed product information display.
    
- **Search & Categorize:** Advanced filtering and categorization logic.
    
- **Carousel Management:** Backend management of homepage carousels.
    
- **Home Carousel:** Dynamic frontend display of featured items.
    

### 3. Shopping Experience

- **Goods Detail:** Rich product detail pages.
    
- **Goods Favorite:** User wishlists and favorite items.
    
- **Shopping Cart:** Full cart logic (Add, Remove, Update Quantity).
    
- **Search:** Real-time product search functionality.
    

### 4. Transaction & Logistics

- **Balance Simulation:** Mock payment and balance handling.
    
- **Order System:** Complete order lifecycle (Creation, History, Status).
    
- **Shipping System:** Logistics simulation and tracking.
    
- **Product Rating:** User reviews and rating system.
    

### 5. Analytics

- **Backend Stats:** Data visualization dashboard using ECharts for sales and user metrics.
    

---

## 📂 Project Structure


```text
code2026/
├── backend/                # Spring Boot Application
│   ├── src/main/java/      # Java Source Code
│   │   ├── controller/     # API Endpoints
│   │   ├── service/        # Business Logic
│   │   ├── mapper/         # MyBatis Interfaces
│   │   └── entity/         # Database Models
│   └── pom.xml             # Maven Dependencies
├── frontend/               # Vue 3 Application
│   ├── src/
│   │   ├── api/            # Axios Requests
│   │   ├── assets/         # Static files
│   │   ├── components/     # Reusable Components
│   │   ├── views/          # Page Views
│   │   ├── router/         # Vue Router Config
│   │   └── store/          # Pinia Stores
│   └── package.json        # Node Dependencies
└── database/               # SQL Scripts
    └── code2026_db.sql     # Database Schema & Seed Data
```

---

## 🛠️ Installation & Setup

### Prerequisites

Ensure you have the following installed:

- JDK 21 or higher
    
- Node.js (v16+)
    
- MySQL Server
    
- Maven
    

### 1. Database Setup

1. Create a database named `code2026_db` in your MySQL instance.
    
2. Import the SQL file located at `/database/code2026_db.sql`.
    

### 2. Backend Setup

1. Navigate to the backend directory:
    
    ```bash
    cd backend
    ```
    
1. Configure your database credentials in `src/main/resources/application.properties`:
    
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/code2026_db
    spring.datasource.username=YOUR_USERNAME
    spring.datasource.password=YOUR_PASSWORD
    ```
    
1. Build and run the application:
    
    ```bash
    mvn spring-boot:run
    ```
    
    The backend will start on `http://localhost:8080`.
    

### 3. Frontend Setup

1. Navigate to the frontend directory:
    
    
    
    ```bash
    cd frontend
    ```
    
2. Install dependencies:
    
    
    
    ```bash
    npm install
    ```
    
3. Start the development server:
    
    
    
    ```bash
    npm run serve
    ```
    
    The frontend will start on `http://localhost:5173` (or the port specified in the console).
    
---

## 🔮 Future Improvements

- Integration with Redis for caching.
    
- Implementation of JWT for stateless authentication.
    
- Integration with a real payment gateway (Stripe/PayPal).
    
- Docker containerization for easier deployment.
