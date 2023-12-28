Creating GET Endpoints with Spring Boot: A Quick Guide
======================================================
## Overview
Introducing readers to the process of building a RESTful endpoint using Spring Boot can open up a world of possibilities for creating robust and scalable web services. This article will guide you through the essential steps and best practices for implementing a RESTful endpoint in your Spring Boot application.

This article is part of a series of 4 articles on implementing REST web services with Spring Boot. This article focuses exclusively on **GET** endpoints. The other articles deal with other types of endpoints, more specifically endpoints of type: POST, PUT, and DELETE.
## Version check
This tutorial has been tested with the following tools :
- Java 17
- Spring boot 3.2.1

## About the project
We assume we have a product entity with the following attributes: id, name, and price. We are going to expose two endpoints: one to retrieve all the products from our repository and another one to find a product by its ID. In the nominal scenario, both APIs must return an HTTP Code 200 (OK). When a search for a product by its ID produces no results, the return code should be HTTP 404 (NOT FOUND). For the sake of simplicity, all our data will be stored in memory. The project will be implemented using the Repository pattern (Controller - Service - Repository). We’ll not spend much time on the Service and Repository layers, but rather on the Controller layer. Let’s start!

## Step 1: Generate a template project.
We’ll use Spring [initializr](https://start.spring.io/) to generate the skeleton of our project. Navigate to Spring [initializr](https://start.spring.io/) website and select the following options:
- **Project**: Maven
- **Language**: Java
- **Spring Boot**: 3.2.1

Complete the project metadata section as shown in the image below :

![Capture d’écran 2023-12-26 à 10.44.51.png](https://ucarecdn.com/582464c7-4b5c-47b6-9eb2-13545233702e/)
Once everything is filled up, generate the project and import it into your favorite IDE.

## Step 2: Create the repository layer
Create a Product class with the snippet code below.
```java
package com.kloudly.springbootrest.dao;

public class Product {
    private Long id;
    private String name;
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
```

Create a ProductRepository class with the code below.
```java
package com.kloudly.springbootrest.dao;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductRepository {
    private final List<Product> products;

    public ProductRepository(){
        this.products = buildFakeProducts();
    }

    private List<Product> buildFakeProducts(){
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Leaf Rake");
        p1.setPrice(20.12);
        Product p2 = new Product();
        p2.setId(2L);
        p2.setName("Garden Cart");
        p2.setPrice(40.99);
        Product p3 = new Product();
        p3.setId(3L);
        p3.setName("Hammer");
        p3.setPrice(7.98);
        Product p4 = new Product();
        p4.setId(5L);
        p4.setName("Saw");
        p4.setPrice(14.56);
        Product p5 = new Product();
        p5.setId(6L);
        p5.setName("Video Game Controller");
        p5.setPrice(39.94);

        return List.of(p1,p2,p3,p4,p5);
    }
    public List<Product> findAll(){
        return this.products;
    }

    public Optional<Product> findById(Long id){
        Optional<Product> ret = Optional.empty();
        List<Product> filteredProducts = this.products.stream().filter(p -> Objects.equals(p.getId(), id)).collect(Collectors.toList());
        if(!filteredProducts.isEmpty()){
            ret = Optional.of(filteredProducts.get(0));
        }
        return ret;
    }
}
```
**Explanation**: Since we are focusing in this tutorial on the Controller layer, the repository layer is managed by in-memory data. We use the stream API to implement the ```findById``` method. Some fake products are injected into the constructor for testing purposes.

## Step 3: Create the service layer.

Create a ProductService class with the code below.
```java
package com.kloudly.springbootrest.services;

import com.kloudly.springbootrest.dao.Product;
import com.kloudly.springbootrest.dao.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }
}
```
The service layer is straightforward, just delegating calls to the repository layer with no additional work.

## Step 4: Create the controller layer.
Start by creating a simple ProductController class.

### RestController
Add ```@Restcontroller``` on top of the class to turn it into a RESTful controller.

Add ```@RequestMapping``` to provide a base path for your endpoints. Note that any URI you specify here applies to all the methods within the class. Let’s use “/products” as our URI.

### Retrieving all products Endpoint
Create a method named ```findAll``` in your class.
Add the ```@GetMapping``` annotation to make it listen to GET calls. This annotation can accept many parameters among which we have "produces". This parameter accepts a string representing the Mime type. It's important to know that by default Spring Boot will produce an output in JSON format. Just to simplify the understanding, let's use the parameter anyway: ```@GetMapping(produces = "application/json")```

Finally, we use ResponseEntity to customize the status code. [ResponseEntity](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html) in Spring Framework is a Generic Class which represents the whole HTTP response. It can be used to customize the HTTP Status Code, the header, and the body. We'll be using it here to customize the Status Code of the response.
```java
	@GetMapping(produces = "application/json")
    public ResponseEntity<List<Product>> findAll(){
     List<Product> allProducts = productService.findAll();
     return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }
```

### Retrieving a specific product Endpoint
Create a method named ```findById``` in your class.
Add the ```@GetMapping``` annotation to make it listen to GET calls. Since we’ll be accepting input from the user. We can either accept it as part of the URL or as a GET parameter. To comply with [REST best practices](https://swagger.io/resources/articles/best-practices-in-api-design/), we will accept the ID as part of the URL. We must then append ```{id}``` to the @GetMapping and add ```@PathVariable(“id”)``` to our method signature. As seen before, we'll add the ```produces``` parameter to the GetMapping annotation to specify the content type.

Finally, we use ResponseEntity to customize the response status code.

Here is the full code of our ProductController class.
```java
package com.kloudly.springbootrest.controllers;

import com.kloudly.springbootrest.dao.Product;
import com.kloudly.springbootrest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Product>> findAll(){
     List<Product> allProducts = productService.findAll();
     return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id){
        Optional<Product> product = productService.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
```
**Explanation** : The ```findAll``` method explanation is straightforward. About ```findById```, we should note that the service layer is returning an optional. So we have to use the ```map``` method to handle situations where the id does not exist, so we can set the status code to 404.

## Testing
We are using [Postman](https://www.postman.com/downloads/) for this example, but you may use any other API testing tool that suits your needs.
**Retrieve all of the products**
![Capture d’écran 2023-12-27 à 11.59.27.png](https://ucarecdn.com/ec93284e-4bbc-4d3f-b6dc-fa75c8f9cb3b/)

**Retrieve a specific product**
![Capture d’écran 2023-12-27 à 12.01.02.png](https://ucarecdn.com/270f457d-c57d-40d0-b4b4-e81841fe5a36/)

**Retrieve non-existing product**
![Capture d’écran 2023-12-26 à 10.46.45.jpg](https://ucarecdn.com/2e503a7c-0f28-4cb2-90c6-b7d8e221b70f/)

## Conclusion
In this quick tutorial, we’ve seen how to build a REST GET endpoint producing JSON and returning a custom HTTP Status Code. In the next articles, we shall be focusing on other endpoint types: PUT, POST, and DELETE.

Did you find this blog post useful? Feel free to drop a thumbs up or comment. If you've had any difficulty completing this tutorial, leave me a comment and I'll be happy to help.

The complete code used in this article can be found [here in GitHub](https://github.com/elkamphy/kloudly-tutorials/tree/master/spring-boot/spring-boot-rest)

*Happy Coding! And more importantly, Clean as You Code!*

## Related articles
If you found this article useful, you might also consider checking out these other articles in the same series.
- Creating [POST]() Endpoints with Spring Boot: A Quick Guide
- Creating [PUT]() Endpoints with Spring Boot: A Quick Guide
- Creating [DELETE]() Endpoints with Spring Boot: A Quick Guide