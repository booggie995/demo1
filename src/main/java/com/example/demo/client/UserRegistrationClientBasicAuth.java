package com.example.demo.client;

import com.example.demo.dto.UserDTO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class UserRegistrationClientBasicAuth {
    private static final String securityUserName = "admin";
    private static final String securityUserPassword = "password";
    private static final String USER_REGISTRATION_BASE_URL = "http://localhost:8080/api/user/";
    private static RestTemplate restTemplate = new RestTemplate();

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

    public void deleteUserByIdExchange(Long userId) {
        HttpEntity<Void> httpEntity = new HttpEntity<Void>(encodedHeader());
        restTemplate.exchange(USER_REGISTRATION_BASE_URL + "/{id}", HttpMethod.DELETE, httpEntity, Void.class, userId);
    }

    private HttpHeaders encodedHeader() {
        String userCredential = securityUserName + ":" + securityUserPassword;
        byte[] base64UserCredentialData = Base64.encodeBase64(userCredential.getBytes());
        HttpHeaders authenticationHeaders = new HttpHeaders();
        authenticationHeaders.set("Authorization", "Basic " + new String(base64UserCredentialData));
        return authenticationHeaders;
    }
}
