package csd.cs203project.supervisor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import csd.cs203project.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.List;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SupervisorTests {
    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;


    //Work in progress, not working at the moment. Other tests will be added into the final project repo
    // @Test
    // public void getEmployeesTest() throws Exception {
    //     URI uri = new URI(baseUrl + port + "/employees/KFC");

    //    ResponseEntity<User[]> result = restTemplate.getForEntity(uri, User[].class);
    //    User[] listOfEmployees = result.getBody();

    //    assertEquals(200, result.getStatusCode().value());
    
    // }

}