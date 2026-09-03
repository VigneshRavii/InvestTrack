package com.examly.springapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringappApplicationTests {

    private String usertoken;
    private String admintoken;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Test
    @Order(1)
    void backend_testRegisterAdmin() {
        String requestBody = "{\"userId\": 1, \"email\": \"demoadmin@gmail.com\", \"password\": \"admin@1234\", \"username\": \"admin123\", \"userRole\": \"Admin\", \"mobileNumber\": \"9876543210\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(2)
    void backend_testRegisterUser() {
        String requestBody = "{\"userId\": 2, \"email\": \"demouser@gmail.com\", \"password\": \"user@1234\", \"username\": \"user123\", \"userRole\": \"User\", \"mobileNumber\": \"1122334455\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(3)
    void backend_testLoginAdmin() throws Exception {
        String requestBody = "{\"email\": \"demoadmin@gmail.com\", \"password\": \"admin@1234\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        System.out.println("Login Admin Response Status: " + response.getStatusCode());
        System.out.println("Login Admin Response Body: " + response.getBody());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody(), "Response body is null");

        try {
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            Assertions.assertTrue(responseBody.has("token"), "Token field missing in response");
            admintoken = responseBody.get("token").asText();
            Assertions.assertNotNull(admintoken, "Admin token is null");
            System.out.println("Admin Token: " + admintoken);
        } catch (Exception e) {
            Assertions.fail("Failed to parse login response: " + e.getMessage());
        }
    }

    @Test
    @Order(4)
    void backend_testLoginUser() throws Exception {
        String requestBody = "{\"email\": \"demouser@gmail.com\", \"password\": \"user@1234\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        System.out.println("Login User Response Status: " + response.getStatusCode());
        System.out.println("Login User Response Body: " + response.getBody());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody(), "Response body is null");

        try {
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            Assertions.assertTrue(responseBody.has("token"), "Token field missing in response");
            usertoken = responseBody.get("token").asText();
            Assertions.assertNotNull(usertoken, "User token is null");
            System.out.println("User Token: " + usertoken);
        } catch (Exception e) {
            Assertions.fail("Failed to parse login response: " + e.getMessage());
        }
    }

    @Test
    @Order(5)
    void backend_testAdminAddInvestment() throws Exception {
        Assertions.assertNotNull(admintoken, "Admin token should not be null");
        Assertions.assertNotNull(usertoken, "User token should not be null");

        String requestBody = "{\"name\": \"Tech Stock\", \"description\": \"Investment in tech sector\", \"type\": \"Stock\", \"purchasePrice\": 1000.0, \"currentPrice\": 1200.0, \"quantity\": 10, \"purchaseDate\": \"2025-02-27\", \"status\": \"Active\"}";

        // Admin test
        HttpHeaders adminHeaders = createHeaders();
        adminHeaders.set("Authorization", "Bearer " + admintoken);
        HttpEntity<String> adminRequest = new HttpEntity<>(requestBody, adminHeaders);

        ResponseEntity<String> adminResponse = restTemplate.exchange("/api/investments", HttpMethod.POST, adminRequest, String.class);
        System.out.println("Admin Add Investment Status: " + adminResponse.getStatusCode());
        System.out.println("Admin Add Investment Body: " + adminResponse.getBody());

        Assertions.assertEquals(HttpStatus.CREATED, adminResponse.getStatusCode());
        Assertions.assertNotNull(adminResponse.getBody(), "Admin response body is null");

        try {
            JsonNode responseBody = objectMapper.readTree(adminResponse.getBody());
            Assertions.assertEquals("Tech Stock", responseBody.get("name").asText());
            Assertions.assertEquals("Stock", responseBody.get("type").asText());
        } catch (Exception e) {
            Assertions.fail("Failed to parse admin response: " + e.getMessage());
        }

        // User test (expecting 403)
        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<String> userRequest = new HttpEntity<>(requestBody, userHeaders);

        ResponseEntity<String> userResponse = restTemplate.exchange("/api/investments", HttpMethod.POST, userRequest, String.class);
        System.out.println("User Add Investment Status: " + userResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode());
    }

    @Test
    @Order(6)
    void backend_testGetInvestmentByIdWithRoleValidation() throws Exception {
        Assertions.assertNotNull(admintoken, "Admin token should not be null");
        Assertions.assertNotNull(usertoken, "User token should not be null");

        long investmentId = 1;
        String url = "/api/investments/" + investmentId;

        // Admin test
        HttpHeaders adminHeaders = createHeaders();
        adminHeaders.set("Authorization", "Bearer " + admintoken);
        HttpEntity<Void> adminRequest = new HttpEntity<>(adminHeaders);

        ResponseEntity<String> adminResponse = restTemplate.exchange(url, HttpMethod.GET, adminRequest, String.class);
        System.out.println("Admin Get Investment Status: " + adminResponse.getStatusCode());

        Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());
        Assertions.assertNotNull(adminResponse.getBody(), "Admin response body is null");

        try {
            JsonNode responseBody = objectMapper.readTree(adminResponse.getBody());
            Assertions.assertEquals(investmentId, responseBody.get("investmentId").asLong());
            Assertions.assertEquals("Tech Stock", responseBody.get("name").asText());
            Assertions.assertEquals("Stock", responseBody.get("type").asText());
        } catch (Exception e) {
            Assertions.fail("Failed to parse admin response: " + e.getMessage());
        }

        // User test
        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<Void> userRequest = new HttpEntity<>(userHeaders);

        ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.GET, userRequest, String.class);
        System.out.println("User Get Investment Status: " + userResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());
    }

    @Test
    @Order(7)
    void backend_testGetAllInvestments() throws Exception {
        Assertions.assertNotNull(admintoken, "Admin token should not be null");
        Assertions.assertNotNull(usertoken, "User token should not be null");

        // Admin test
        HttpHeaders adminHeaders = createHeaders();
        adminHeaders.set("Authorization", "Bearer " + admintoken);
        HttpEntity<String> adminRequest = new HttpEntity<>(adminHeaders);

        ResponseEntity<String> adminResponse = restTemplate.exchange("/api/investments", HttpMethod.GET, adminRequest, String.class);
        System.out.println("Admin Get All Investments Status: " + adminResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());

        try {
            JsonNode responseBody = objectMapper.readTree(adminResponse.getBody());
            Assertions.assertTrue(responseBody.isArray());
        } catch (Exception e) {
            Assertions.fail("Failed to parse admin response: " + e.getMessage());
        }

        // User test
        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<String> userRequest = new HttpEntity<>(userHeaders);

        ResponseEntity<String> userResponse = restTemplate.exchange("/api/investments", HttpMethod.GET, userRequest, String.class);
        System.out.println("User Get All Investments Status: " + userResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());
    }

    @Test
    @Order(8)
    void backend_testUpdateInvestmentWithRoleValidation() throws Exception {
        Assertions.assertNotNull(admintoken, "Admin token should not be null");
        Assertions.assertNotNull(usertoken, "User token should not be null");

        long investmentId = 1;
        String url = "/api/investments/" + investmentId;

        String updateRequestBody = "{\"investmentId\": " + investmentId + ", \"name\": \"Updated Tech Stock\", \"description\": \"Updated investment\", \"type\": \"Stock\", \"purchasePrice\": 1100.0, \"currentPrice\": 1300.0, \"quantity\": 15, \"purchaseDate\": \"2025-02-28\", \"status\": \"Active\"}";

        // Admin test
        HttpHeaders adminHeaders = createHeaders();
        adminHeaders.set("Authorization", "Bearer " + admintoken);
        HttpEntity<String> adminRequest = new HttpEntity<>(updateRequestBody, adminHeaders);

        ResponseEntity<String> adminResponse = restTemplate.exchange(url, HttpMethod.PUT, adminRequest, String.class);
        System.out.println("Admin Update Investment Status: " + adminResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());

        try {
            JsonNode responseBody = objectMapper.readTree(adminResponse.getBody());
            Assertions.assertEquals(investmentId, responseBody.get("investmentId").asLong());
            Assertions.assertEquals("Updated Tech Stock", responseBody.get("name").asText());
        } catch (Exception e) {
            Assertions.fail("Failed to parse admin response: " + e.getMessage());
        }

        // User test
        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<String> userRequest = new HttpEntity<>(updateRequestBody, userHeaders);

        ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.PUT, userRequest, String.class);
        System.out.println("User Update Investment Status: " + userResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode());
    }

    @Test
    @Order(9)
    void backend_testUserCanAddInquiry() throws Exception {
        Assertions.assertNotNull(usertoken, "User token should not be null");
        Assertions.assertNotNull(admintoken, "Admin token should not be null");

        String requestBody = "{\"message\": \"Need advice\", \"status\": \"Pending\", \"inquiryDate\": \"2025-02-27T10:00:00\", \"priority\": \"High\", \"contactDetails\": \"user@example.com\", \"user\": {\"userId\": 2}, \"investment\": {\"investmentId\": 1}}";

        // User test
        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<String> userRequest = new HttpEntity<>(requestBody, userHeaders);

        ResponseEntity<String> userResponse = restTemplate.exchange("/api/inquiries", HttpMethod.POST, userRequest, String.class);
        System.out.println("User Add Inquiry Status: " + userResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.CREATED, userResponse.getStatusCode());

        try {
            JsonNode responseBody = objectMapper.readTree(userResponse.getBody());
            Assertions.assertEquals("Need advice", responseBody.get("message").asText());
        } catch (Exception e) {
            Assertions.fail("Failed to parse user response: " + e.getMessage());
        }

        // Admin test
        HttpHeaders adminHeaders = createHeaders();
        adminHeaders.set("Authorization", "Bearer " + admintoken);
        HttpEntity<String> adminRequest = new HttpEntity<>(requestBody, adminHeaders);

        ResponseEntity<String> adminResponse = restTemplate.exchange("/api/inquiries", HttpMethod.POST, adminRequest, String.class);
        System.out.println("Admin Add Inquiry Status: " + adminResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.FORBIDDEN, adminResponse.getStatusCode());
    }

    @Test
    @Order(10)
    void backend_testRoleBasedAccessForViewingAllInquiries() throws Exception {
        Assertions.assertNotNull(admintoken, "Admin token should not be null");
        Assertions.assertNotNull(usertoken, "User token should not be null");

        String url = "/api/inquiries";

        // Admin test
        HttpHeaders adminHeaders = createHeaders();
        adminHeaders.set("Authorization", "Bearer " + admintoken);
        HttpEntity<Void> adminRequest = new HttpEntity<>(adminHeaders);

        ResponseEntity<String> adminResponse = restTemplate.exchange(url, HttpMethod.GET, adminRequest, String.class);
        System.out.println("Admin View All Inquiries Status: " + adminResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());

        try {
            JsonNode responseBody = objectMapper.readTree(adminResponse.getBody());
            Assertions.assertTrue(responseBody.isArray());
        } catch (Exception e) {
            Assertions.fail("Failed to parse admin response: " + e.getMessage());
        }

        // User test
        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<Void> userRequest = new HttpEntity<>(userHeaders);

        ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.GET, userRequest, String.class);
        System.out.println("User View All Inquiries Status: " + userResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode());
    }

    @Test
    @Order(11)
    void backend_testUserCanViewOwnInquiries() throws Exception {
        Assertions.assertNotNull(usertoken, "User token should not be null");

        long userId = 2;
        String url = "/api/inquiries/user/" + userId;

        // User test
        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<Void> userRequest = new HttpEntity<>(userHeaders);

        ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.GET, userRequest, String.class);
        System.out.println("User View Own Inquiries Status: " + userResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());

        try {
            JsonNode responseBody = objectMapper.readTree(userResponse.getBody());
            Assertions.assertTrue(responseBody.isArray());
        } catch (Exception e) {
            Assertions.fail("Failed to parse user response: " + e.getMessage());
        }
    }

    @Test
    @Order(12)
    void backend_testAddFeedbackWithRoleValidation() throws Exception {
        Assertions.assertNotNull(admintoken, "Admin token should not be null");
        Assertions.assertNotNull(usertoken, "User token should not be null");

        String requestBody = "{\"feedbackId\": 1, \"feedbackText\": \"Great platform!\", \"date\": \"2025-02-27T10:00:00Z\", \"category\": \"General\", \"user\": {\"userId\": 2}, \"investment\": {\"investmentId\": 1}}";

        // User test
        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<String> userRequest = new HttpEntity<>(requestBody, userHeaders);

        ResponseEntity<String> userResponse = restTemplate.exchange("/api/feedback", HttpMethod.POST, userRequest, String.class);
        System.out.println("User Add Feedback Status: " + userResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.CREATED, userResponse.getStatusCode());

        try {
            JsonNode responseBody = objectMapper.readTree(userResponse.getBody());
            Assertions.assertEquals("Great platform!", responseBody.get("feedbackText").asText());
        } catch (Exception e) {
            Assertions.fail("Failed to parse user response: " + e.getMessage());
        }

        // Admin test
        HttpHeaders adminHeaders = createHeaders();
        adminHeaders.set("Authorization", "Bearer " + admintoken);
        HttpEntity<String> adminRequest = new HttpEntity<>(requestBody, adminHeaders);

        ResponseEntity<String> adminResponse = restTemplate.exchange("/api/feedback", HttpMethod.POST, adminRequest, String.class);
        System.out.println("Admin Add Feedback Status: " + adminResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.FORBIDDEN, adminResponse.getStatusCode());
    }

    @Test
    @Order(13)
    void backend_testGetAllFeedbackWithRoleValidation() throws Exception {
        Assertions.assertNotNull(admintoken, "Admin token should not be null");
        Assertions.assertNotNull(usertoken, "User token should not be null");

        // Admin test
        HttpHeaders adminHeaders = createHeaders();
        adminHeaders.set("Authorization", "Bearer " + admintoken);
        HttpEntity<String> adminRequest = new HttpEntity<>(adminHeaders);

        ResponseEntity<String> adminResponse = restTemplate.exchange("/api/feedback", HttpMethod.GET, adminRequest, String.class);
        System.out.println("Admin Get All Feedback Status: " + adminResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());

        // User test (permitAll in security config)
        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<String> userRequest = new HttpEntity<>(userHeaders);

        ResponseEntity<String> userResponse = restTemplate.exchange("/api/feedback", HttpMethod.GET, userRequest, String.class);
        System.out.println("User Get All Feedback Status: " + userResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode());
    }

    @Test
    @Order(14)
    void backend_testGetFeedbackByUserIdWithRoleValidation() throws Exception {
        Assertions.assertNotNull(admintoken, "Admin token should not be null");
        Assertions.assertNotNull(usertoken, "User token should not be null");

        String url = "/api/feedback/user/2";

        // User test
        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<String> userRequest = new HttpEntity<>(userHeaders);

        ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.GET, userRequest, String.class);
        System.out.println("User Get Feedback By UserId Status: " + userResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());

        // Admin test
        HttpHeaders adminHeaders = createHeaders();
        adminHeaders.set("Authorization", "Bearer " + admintoken);
        HttpEntity<String> adminRequest = new HttpEntity<>(adminHeaders);

        ResponseEntity<String> adminResponse = restTemplate.exchange(url, HttpMethod.GET, adminRequest, String.class);
        System.out.println("Admin Get Feedback By UserId Status: " + adminResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.FORBIDDEN, adminResponse.getStatusCode());
    }

    @Test
    @Order(15)
    void backend_testUserCanDeleteFeedback() throws Exception {
        Assertions.assertNotNull(usertoken, "User token should not be null");
        Assertions.assertNotNull(admintoken, "Admin token should not be null");

        // Create feedback
        String createFeedbackBody = "{\"feedbackId\": 2, \"feedbackText\": \"Another great experience!\", \"date\": \"2025-02-28T10:00:00Z\", \"category\": \"General\", \"user\": {\"userId\": 2}, \"investment\": {\"investmentId\": 1}}";

        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<String> createFeedbackRequest = new HttpEntity<>(createFeedbackBody, userHeaders);

        ResponseEntity<String> createFeedbackResponse = restTemplate.exchange("/api/feedback", HttpMethod.POST, createFeedbackRequest, String.class);
        System.out.println("Create Feedback Status: " + createFeedbackResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.CREATED, createFeedbackResponse.getStatusCode());

        try {
            JsonNode responseBody = objectMapper.readTree(createFeedbackResponse.getBody());
            Assertions.assertEquals("Another great experience!", responseBody.get("feedbackText").asText());
        } catch (Exception e) {
            Assertions.fail("Failed to parse create feedback response: " + e.getMessage());
        }

        // Delete feedback
        String deleteUrl = "/api/feedback/2";
        HttpEntity<Void> deleteFeedbackRequest = new HttpEntity<>(userHeaders);

        ResponseEntity<String> deleteFeedbackResponse = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, deleteFeedbackRequest, String.class);
        System.out.println("Delete Feedback Status: " + deleteFeedbackResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, deleteFeedbackResponse.getStatusCode());

        // Verify deletion
        ResponseEntity<String> getFeedbackResponse = restTemplate.exchange(deleteUrl, HttpMethod.GET, deleteFeedbackRequest, String.class);
        System.out.println("Get Feedback After Deletion Status: " + getFeedbackResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, getFeedbackResponse.getStatusCode());
    }
}