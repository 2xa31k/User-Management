# UserControllerApi

All URIs are relative to *https://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addUserUsingPOST**](UserControllerApi.md#addUserUsingPOST) | **POST** /user/ | addUser
[**deleteUserUsingDELETE**](UserControllerApi.md#deleteUserUsingDELETE) | **DELETE** /user/{id} | deleteUser
[**getAllUsingGET**](UserControllerApi.md#getAllUsingGET) | **GET** /user/ | getAll
[**getByIdUsingGET**](UserControllerApi.md#getByIdUsingGET) | **GET** /user/{id} | getById
[**updateUserUsingPUT**](UserControllerApi.md#updateUserUsingPUT) | **PUT** /user/{id} | updateUser


<a name="addUserUsingPOST"></a>
# **addUserUsingPOST**
> UserDto addUserUsingPOST(dto)

addUser

### Example
```java
// Import classes:
//import com.user.management.client.invoker.ApiException;
//import com.user.management.client.api.UserControllerApi;


UserControllerApi apiInstance = new UserControllerApi();
UserDto dto = new UserDto(); // UserDto | dto
try {
    UserDto result = apiInstance.addUserUsingPOST(dto);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserControllerApi#addUserUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dto** | [**UserDto**](UserDto.md)| dto |

### Return type

[**UserDto**](UserDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="deleteUserUsingDELETE"></a>
# **deleteUserUsingDELETE**
> deleteUserUsingDELETE(id)

deleteUser

### Example
```java
// Import classes:
//import com.user.management.client.invoker.ApiException;
//import com.user.management.client.api.UserControllerApi;


UserControllerApi apiInstance = new UserControllerApi();
Long id = 789L; // Long | id
try {
    apiInstance.deleteUserUsingDELETE(id);
} catch (ApiException e) {
    System.err.println("Exception when calling UserControllerApi#deleteUserUsingDELETE");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| id |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getAllUsingGET"></a>
# **getAllUsingGET**
> List&lt;UserDto&gt; getAllUsingGET()

getAll

### Example
```java
// Import classes:
//import com.user.management.client.invoker.ApiException;
//import com.user.management.client.api.UserControllerApi;


UserControllerApi apiInstance = new UserControllerApi();
try {
    List<UserDto> result = apiInstance.getAllUsingGET();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserControllerApi#getAllUsingGET");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;UserDto&gt;**](UserDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getByIdUsingGET"></a>
# **getByIdUsingGET**
> UserDto getByIdUsingGET(id)

getById

### Example
```java
// Import classes:
//import com.user.management.client.invoker.ApiException;
//import com.user.management.client.api.UserControllerApi;


UserControllerApi apiInstance = new UserControllerApi();
Long id = 789L; // Long | id
try {
    UserDto result = apiInstance.getByIdUsingGET(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserControllerApi#getByIdUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| id |

### Return type

[**UserDto**](UserDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="updateUserUsingPUT"></a>
# **updateUserUsingPUT**
> UserDto updateUserUsingPUT(dto, id)

updateUser

### Example
```java
// Import classes:
//import com.user.management.client.invoker.ApiException;
//import com.user.management.client.api.UserControllerApi;


UserControllerApi apiInstance = new UserControllerApi();
UserDto dto = new UserDto(); // UserDto | dto
Long id = 789L; // Long | id
try {
    UserDto result = apiInstance.updateUserUsingPUT(dto, id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserControllerApi#updateUserUsingPUT");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dto** | [**UserDto**](UserDto.md)| dto |
 **id** | **Long**| id |

### Return type

[**UserDto**](UserDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

