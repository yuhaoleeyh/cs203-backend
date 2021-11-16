package csd.cs203project.user;

import org.dom4j.util.UserDataAttribute;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;

import csd.cs203project.model.User;
import csd.cs203project.repository.user.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Optional;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

/*NOTE CAN only test getting of users, add/delete/update users cannot be done with integration tests due to cognito constraint, tested in unit testing*/
public class UserIntegrationTest {
    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
	private UserRepository userRepository;

    /**
     * Test for getting employees, valid success
     * @throws Exception
     */
    @Test
    public void getEmployees_Success() throws Exception {
        URI uri = new URI(baseUrl + port + "/users/id/1/authorities/ROLE_ADMIN");

        ResponseEntity<User[]> result = restTemplate.getForEntity(uri, User[].class);

        assertEquals(200, result.getStatusCode().value());
    }

    /**
     * Test for getting employees, invalid endpoint failure
     * @throws Exception
     */
    @Test
    public void getEmployees_Failure() throws Exception {
        URI uri = new URI(baseUrl + port + "/users/id/1/authorities/ROLE_???");

        ResponseEntity<User[]> result = restTemplate.getForEntity(uri, User[].class);

        assertEquals(200, result.getStatusCode().value());
    }

    /**
     * Test for adding employee
     * Forbidden 403 error as the user is not authenticated with AWS Cognito
     * @throws Exception
     */
    @Test
    public void addEmployee_Failure() throws Exception {
        URI uri = new URI(baseUrl + port + "/users");
        User user = new User("a@b", "Mary", "ROLE_ADMIN");

        ResponseEntity<User> result = restTemplate.postForEntity(uri, user, User.class);

        assertEquals(403, result.getStatusCode().value());
    }

    
    

    

}