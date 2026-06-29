# 🛒 Platzi Fake Store API Automation Framework

## 🚀 Overview

This project is a **API Test Automation Framework** developed using **Rest Assured** for testing the **Platzi Fake Store API**.

The framework follows modern automation architecture principles including:

* ✅ Service Layer Design Pattern
* ✅ Request & Response Models
* ✅ Data-Driven Testing
* ✅ Request/Response Specifications
* ✅ Reusable Utilities
* ✅ Centralized Configuration Management
* ✅ Positive and Negative Testing Strategies
* ✅ Extensible Reporting and Logging Mechanisms
* ✅ CI/CD Integration using GitHub Actions

---

## 🛠️ Tech Stack

| Technology               | Purpose                      |
| ------------------------ | ---------------------------- |
| ☕ Java                   | Programming Language         |
| 🌐 Rest Assured          | API Automation               |
| 🧪 TestNG                | Test Execution               |
| 📦 Maven                 | Dependency Management        |
| 📊 Extent Reports        | Reporting                    |
| 📑 Jackson/Gson          | JSON Serialization           |
| 🔄 Faker Library         | Dynamic Test Data Generation |
| 📈 Allure/Custom Reports | Execution Reporting          |

---

## 🌍 API Under Test

**Platzi Fake Store API**

Features covered:

* 🔐 Authentication APIs
* 📦 Products APIs
* 🏷️ Categories APIs
* 🔎 Product Filtering APIs

---

## 🏗️ Framework Architecture

The framework follows a layered architecture:

```text
Tests Layer
     ↓
Service Layer
     ↓
Request/Response Models
     ↓
Request Specifications
     ↓
Rest Assured Client
     ↓
Platzi Fake Store API
```

---

## 📁 Project Structure

### Main Framework Structure

```text
src
└── main
    └── java
        └── com.platziapi
            ├── customlisteners
            ├── models
            │   ├── request
            │   └── response
            │
            ├── services
            │   ├── AuthAWTServices
            │   ├── CategoryServices
            │   └── ProductServices
            │
            ├── specs
            │   ├── RequestSpecs
            │   └── ResponseSpecs
            │
            ├── utils
            │   ├── datamanager
            │   │   ├── DataProvider
            │   │   └── JsonReader
            │   │
            │   ├── helper
            │   │   ├── Constants
            │   │   ├── QueryParams
            │   │   └── RestHelper
            │   │
            │   ├── manager
            │   ├── report
            │   ├── FileUtils
            │   ├── OSUtils
            │   └── StatusCode
```

---

### 🧪 Test Structure

```text
src
└── test
    └── java
        └── com.platzistore.testcases
            │
            ├── AuthAWT
            │   ├── LoginPositiveTest
            │   └── LoginNegativeTest
            │
            ├── categories
            │   └── CategoryPositiveTest
            │
            ├── filterproducts
            │   ├── FilterProductsPositiveTest
            │   └── FilterProductsNegativeTest
            │
            ├── products
            │   ├── ProductPositiveTest
            │   └── ProductsNegativeTest
            │
            └── BaseTest
```



---

## ✅ Test Coverage

### 🔐 Authentication

* Valid Login Scenarios
* Invalid Login Scenarios
* Token Validation

### 📦 Products

* Get Products
* Create Product
* Validate Product Details
* Invalid Product Scenarios

### 🏷️ Categories

* Retrieve Categories
* Validate Category Responses

### 🔎 Product Filters

* Filter Products by Category
* Invalid Filter Requests
* Response Validation

---

## 🎯 Framework Features

### ✅ Service Layer Pattern

Business logic and API calls are separated from test classes to improve maintainability.

### ✅ Request and Response Models

POJO models are used for serialization and deserialization.

### ✅ Reusable Specifications

Common headers, authentication, and response validations are centralized.

### ✅ Data Driven Testing

External JSON files are used to manage test data.

### ✅ Reporting Support

Detailed execution reports and logs simplify debugging and analysis.

### ✅ Cross Platform Support

Framework utilities support execution across different operating systems.

---

## ▶️ Running Tests

Execute all tests:

```bash
mvn clean test
```

Execute a specific suite:

```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## 📊 Reporting

After execution, reports are automatically generated and can be found inside the reports directory.

Example report information:

* 📈 Passed Tests
* ❌ Failed Tests
* ⏱️ Execution Time
* 📄 Request and Response Logs




