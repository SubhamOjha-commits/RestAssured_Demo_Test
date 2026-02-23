# REST Assured API Testing Project

A comprehensive REST Assured API testing framework demonstrating various API testing scenarios including HTTP methods, authentication, headers, cookies, path/query parameters, and JSON handling.

## 🚀 Features

- ✅ GET, POST, PUT, DELETE request examples
- ✅ API Key authentication
- ✅ Header validation
- ✅ Cookie handling
- ✅ Path and Query parameters
- ✅ JSON request/response handling
- ✅ POJO (Plain Old Java Object) support
- ✅ TestNG integration
- ✅ Request/Response logging

## 📋 Prerequisites

- Java 11 or higher
- Maven 3.6+
- IntelliJ IDEA (recommended) or any Java IDE

## 🛠️ Technologies Used

- **REST Assured** 5.4.0 - API testing framework
- **TestNG** 7.9.0 - Testing framework
- **Maven** - Build and dependency management
- **Hamcrest** 2.2 - Assertion library
- **Gson** 2.10.1 - JSON processing
- **org.json** 20231013 - JSONObject support

## 📦 Installation

1. Clone the repository:
```bash
git clone https://github.com/SubhamOjha-commits/RestAssured_Demo_Test.git
cd RestAssured_Demo_Test
```

2. Install dependencies:
```bash
mvn clean install
```

## 🧪 Running Tests

### Run all tests:
```bash
mvn test
```

### Run specific test class:
```bash
mvn test -Dtest=HttpReqest
mvn test -Dtest=CookiesDemo
mvn test -Dtest=HeadersDemo
```

### Run specific test method:
```bash
mvn test -Dtest=HttpReqest#getUser
mvn test -Dtest=PojoTest#testWithJSONObject
```

## 📁 Project Structure

```
RestAssured_Demo_Test/
├── src/
│   ├── Body/
│   │   └── Body.Json              # Sample JSON request body
│   └── test/
│       └── java/
│           └── Apitest/
│               ├── HttpReqest.java         # Basic HTTP requests (GET, POST)
│               ├── CookiesDemo.java        # Cookie handling examples
│               ├── HeadersDemo.java        # Header validation examples
│               ├── PathAndQuerry.java      # Path & Query parameters
│               ├── PojoTest.java           # JSONObject examples
│               └── Pojo_Post/
│                   └── PoJo_postRequest.java  # POJO request examples
├── pom.xml                        # Maven configuration
├── testng.xml                     # TestNG suite configuration
└── README.md                      # This file
```

## 📝 Test Examples

### 1. Basic GET Request
```java
@Test
public void getUser(){
    given()
        .header("x-api-key", "your_api_key")
        .log().all()
    .when()
        .get("https://reqres.in/api/users?page=2")
    .then()
        .statusCode(200)
        .body("page", equalTo(2));
}
```

### 2. POST Request with HashMap
```java
@Test
public void CreateUser(){
    HashMap<String,String> hm = new HashMap<>();
    hm.put("name", "subham");
    hm.put("job", "leader");
    
    given()
        .header("x-api-key", "your_api_key")
        .contentType("application/json")
        .body(hm)
    .when()
        .post("https://reqres.in/api/users")
    .then()
        .statusCode(201)
        .body("name", equalTo("subham"));
}
```

### 3. Using JSONObject
```java
@Test
public void testWithJSONObject(){
    JSONObject data = new JSONObject();
    data.put("name", "Subham");
    data.put("job", "Lead");
    
    given()
        .header("Content-Type", "application/json")
        .body(data.toString())
    .when()
        .post("https://reqres.in/api/users")
    .then()
        .statusCode(201);
}
```

## 🔑 API Configuration

This project uses **ReqRes API** for testing. To use your own API key:

1. Get a free API key from [ReqRes](https://app.reqres.in/api-keys)
2. Replace `"your_api_key"` in test files with your actual key

## 📊 Test Reports

After running tests, reports are generated in:
- `target/surefire-reports/` - TestNG HTML reports
- Console output with detailed request/response logs



## 👤 Author

**Subham Ojha**
- GitHub: [@SubhamOjha-commits](https://github.com/SubhamOjha-commits)

## 🙏 Acknowledgments

- [REST Assured Documentation](https://rest-assured.io/)
- [ReqRes API](https://reqres.in/)
- [JSONPlaceholder](https://jsonplaceholder.typicode.com/)
