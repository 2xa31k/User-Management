/*
 * Api Documentation
 * Api Documentation
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.user.management.client.api;

import com.user.management.client.model.UserDto;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for UserControllerApi
 */
@Ignore
public class UserControllerApiTest {

    private final UserControllerApi api = new UserControllerApi();

    
    /**
     * addUser
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addUserUsingPOSTTest() {
        UserDto dto = null;
        UserDto response = api.addUserUsingPOST(dto);

        // TODO: test validations
    }
    
    /**
     * deleteUser
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteUserUsingDELETETest() {
        Long id = null;
        api.deleteUserUsingDELETE(id);

        // TODO: test validations
    }
    
    /**
     * getAll
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getAllUsingGETTest() {
        List<UserDto> response = api.getAllUsingGET();

        // TODO: test validations
    }
    
    /**
     * getById
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getByIdUsingGETTest() {
        Long id = null;
        UserDto response = api.getByIdUsingGET(id);

        // TODO: test validations
    }
    
    /**
     * updateUser
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateUserUsingPUTTest() {
        UserDto dto = null;
        Long id = null;
        UserDto response = api.updateUserUsingPUT(dto, id);

        // TODO: test validations
    }
    
}
