package csd.cs203project.supervisor;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
public class SupervisorIntegrationTest {
    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void getEmployeesTest() throws Exception {
        URI uri = new URI(baseUrl + port + "/employees/KFC");

       ResponseEntity<Object[]> result = restTemplate.getForEntity(uri, Object[].class);
       Object[] listOfEmployees = result.getBody();

       assertEquals(200, result.getStatusCode().value());
    
    }

    @Test 
    public void testAddition() {
        assertEquals(3, 3);
    }

}