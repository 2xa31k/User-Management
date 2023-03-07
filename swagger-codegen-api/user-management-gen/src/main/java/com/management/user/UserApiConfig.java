package com.management.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.user.management.client.api.UserControllerApi;
import com.user.management.client.invoker.ApiClient;

@Configuration
public class UserApiConfig {

	@Bean
    public UserControllerApi petApi() {
        return new UserControllerApi(apiClient());
    }
    
    @Bean
    public ApiClient apiClient() {
        return new ApiClient();
    }
}
