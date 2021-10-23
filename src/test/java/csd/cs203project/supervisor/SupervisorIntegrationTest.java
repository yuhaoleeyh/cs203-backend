package csd.cs203project.supervisor;

import org.dom4j.util.UserDataAttribute;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import csd.cs203project.model.User;
import csd.cs203project.repository.user.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.List;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SupervisorIntegrationTest {
    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    // @Autowired
	// private UserRepository userRepository;


    // @Test
    // public void getEmployees_Success() throws Exception {
    //     URI uri = new URI(baseUrl + port + "/employees/KFC");

    //    restTemplate.getForEntity(uri, User[].class);


    // //    User[] listOfEmployees = result.getBody();

    // //    assertEquals(200, result.getStatusCode().value());

    // //    assertEquals(1, listOfEmployees.length);
    
    // }

    // @Test 
    // public void addEmployees_Success() throws Exception {
    //     URI uri = new URI(baseUrl + port + "/employees");

    //     User user = new User("hi@gmail.com", "Mary", "Admin", "KFC");

    //     ResponseEntity<User> result = restTemplate.postForEntity(uri, user, User.class);

    //     assertEquals(201, result.getStatusCode().value());
	// 	assertEquals(user.getEmail(), result.getBody().getEmail());
    // }

    

    

}