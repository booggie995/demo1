package com.example.demo.client;

import com.example.demo.dto.UserDTO;
import org.springframework.web.client.RestTemplate;

public class UserRegistrationClient {
    private static RestTemplate restTemplate = new RestTemplate();
    private static final String USER_REGISTRATION_BASE_URL = "http://localhost:8080/api/user/";

    /*GET USER BY ID*/
    public UserDTO getUserById(final Long userId) {
        return restTemplate.getForObject(USER_REGISTRATION_BASE_URL + "/{id}", UserDTO.class, userId);
    }

    /*GET ALL USERS*/
    public UserDTO[] getAllUsers() {
        return restTemplate.getForObject(USER_REGISTRATION_BASE_URL, UserDTO[].class);
    }


    /*CREATE USER*/
    public UserDTO createUser(final UserDTO user) {
        return restTemplate.postForObject(USER_REGISTRATION_BASE_URL, user, UserDTO.class);
    }

    /*UPDATE USER*/
    public void updateUser(final Long userId, final UserDTO user) {
        restTemplate.put(USER_REGISTRATION_BASE_URL + "/{id}", user, userId);
    }

    /*DELETE USER*/
    public void deleteUser(final Long userId) {
        restTemplate.delete(USER_REGISTRATION_BASE_URL + "/{id}", userId);
    }
}
