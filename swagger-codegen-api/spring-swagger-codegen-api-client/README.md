# spring-swagger-codegen-api-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>com.user.management</groupId>
    <artifactId>spring-swagger-codegen-api-client</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.user.management:spring-swagger-codegen-api-client:0.0.1-SNAPSHOT"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/spring-swagger-codegen-api-client-0.0.1-SNAPSHOT.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import com.user.management.client.invoker.*;
import com.user.management.client.invoker.auth.*;
import com.user.management.client.model.*;
import com.user.management.client.api.UserControllerApi;

import java.io.File;
import java.util.*;

public class UserControllerApiExample {

    public static void main(String[] args) {
        
        UserControllerApi apiInstance = new UserControllerApi();
        UserDto dto = new UserDto(); // UserDto | dto
        try {
            UserDto result = apiInstance.addUserUsingPOST(dto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserControllerApi#addUserUsingPOST");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://localhost:8080*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*UserControllerApi* | [**addUserUsingPOST**](docs/UserControllerApi.md#addUserUsingPOST) | **POST** /user/ | addUser
*UserControllerApi* | [**deleteUserUsingDELETE**](docs/UserControllerApi.md#deleteUserUsingDELETE) | **DELETE** /user/{id} | deleteUser
*UserControllerApi* | [**getAllUsingGET**](docs/UserControllerApi.md#getAllUsingGET) | **GET** /user/ | getAll
*UserControllerApi* | [**getByIdUsingGET**](docs/UserControllerApi.md#getByIdUsingGET) | **GET** /user/{id} | getById
*UserControllerApi* | [**updateUserUsingPUT**](docs/UserControllerApi.md#updateUserUsingPUT) | **PUT** /user/{id} | updateUser


## Documentation for Models

 - [UserDto](docs/UserDto.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author



