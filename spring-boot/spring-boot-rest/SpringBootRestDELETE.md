Creating DELETE Endpoints with Spring Boot: A Quick Guide
======================================================
## Overview
Introducing readers to the process of building a **RESTful endpoint** using Spring Boot can open up a world of possibilities for creating robust and scalable web services. This article will guide you through the essential steps and best practices for implementing a RESTful endpoint in your Spring Boot application.

This article is part of a **series** of 4 articles on implementing REST web services with Spring Boot. This article focuses exclusively on **DELETE** endpoints. The other articles deal with other types of endpoints, more specifically endpoints of type:  POST, PUT, and GET.
## Version check
This tutorial has been tested with the following tools :
- Java 17
- Spring boot 3.2.1

## About the project
We assume we have a product entity with the following attributes: id, name, and price. The task is to implement a REST endpoint enabling an existing product to be deleted based on its ID. If successful, a return HTTP status code **200 (OK)** must be returned. When there is no product matching the provided ID, the return code should be HTTP **404 (NOT FOUND)**. For the sake of simplicity, all our data will be stored in memory. The project will be implemented using the Repository pattern (Controller - Service - Repository). We’ll not spend much time on the Service and Repository layers, but rather on the Controller layer. Let’s start!

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
    private  List<Product> products;

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

        List<Product> fakeProducts = new ArrayList<>();
        fakeProducts.add(p1);
        fakeProducts.add(p2);
        fakeProducts.add(p3);
        fakeProducts.add(p4);
        fakeProducts.add(p5);
        return fakeProducts;
    }
    public void delete(Product product){
        int index = this.indexOf(product);
        this.products.remove(index);
    }
    
    private int indexOf(Product product) {
        int index = 0;
        for (Product p:this.products) {
            if(Objects.equals(product.getId(), p.getId()))
                return index;
            index++;
        }
        return -1;
    }
}
```
**Explanation**: Since we are focusing in this tutorial on the Controller layer, the repository layer is managed by in-memory data. We've created a method to find the index of an existing Product within the List of Products. We then use the ```remove``` method of the List interface to delete the given element using its index. Some fake products are injected into the constructor for testing purposes.

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

    public void delete(Product product){
        this.productRepository.delete(product);
    }
}
```
The service layer is straightforward, just delegating calls to the repository layer with no additional work.

## Step 4: Create the controller layer.
Start by creating a simple ProductController class.

### RestController
Add ```@Restcontroller``` on top of the class to turn it into a RESTful controller.

Add ```@RequestMapping``` to provide a base path for your endpoints. Note that any URI you specify here applies to all the methods within the class. Let’s use “/products” as our URI.

### Deleting an existing Product Endpoint
Create a method named ```delete``` in your class.
Add the ```@DeleteMapping``` annotation to make it listen to DELETE calls. Since we’ll be accepting input from the user. We can either accept it as part of the URL or as a GET parameter. To comply with [REST best practices](https://swagger.io/resources/articles/best-practices-in-api-design/), we will accept the ID as part of the URL. We must then append ```{id}``` to the @DeleteMapping and add ```@PathVariable(“id”)``` to our method signature.

Before deleting a product, it is necessary to check that the ID of the product supplied already exists. If this is the case, the deletion is carried out normally and a return code 200 is returned. If the ID does not exist, a 404 return code is returned.

Finally, we use ResponseEntity to customize the status code. [ResponseEntity](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html) in Spring Framework is a Generic Class which represents the whole HTTP response. It can be used to customize the HTTP Status Code, the header, and the body. We'll be using it here to customize the Status Code of the response.

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
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        Optional<Product> existingProduct = this.productService.findById(id);
        if(existingProduct.isPresent()){
            this.productService.delete(existingProduct.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
```

## Testing
We are using [Postman](https://www.postman.com/downloads/) for this example, but you may use any other API testing tool that suits your needs.
**Delete an existing product**
![Capture d’écran 2023-12-28 à 13.33.04.png](https://ucarecdn.com/6b8e19e4-0c71-4d50-85ba-0ac6f8773af9/)

**Delete non-existing product**
![Capture d’écran 2023-12-28 à 13.34.05.png](https://ucarecdn.com/bf4a57a4-51e9-47e7-9062-203c739d65a1/)

## Conclusion
In this quick tutorial, we’ve seen how to build a **REST DELETE endpoint** returning a custom HTTP Status Code. In the next articles, we shall be focusing on other endpoint types: PUT, POST, and GET.

Did you find this blog post useful? Feel free to drop a thumbs up or comment. If you've had any difficulty completing this tutorial, leave me a comment and I'll be happy to help.

The complete code used in this article can be found [here in GitHub](https://github.com/elkamphy/kloudly-tutorials/tree/master/spring-boot/spring-boot-rest)

*Happy Coding! And more importantly, Clean as You Code!*

## Related articles
If you found this article useful, you might also consider checking out these other articles in the same series.
- Creating [POST]() Endpoints with Spring Boot: A Quick Guide
- Creating [PUT]() Endpoints with Spring Boot: A Quick Guide
- Creating [GET]() Endpoints with Spring Boot: A Quick Guide