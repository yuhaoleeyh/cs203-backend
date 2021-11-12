// package csd.cs203project.user;

// import org.dom4j.util.UserDataAttribute;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.boot.web.server.LocalServerPort;
// import org.springframework.http.ResponseEntity;

// import org.springframework.transaction.annotation.Transactional;

// import csd.cs203project.model.User;
// import csd.cs203project.repository.user.UserRepository;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.net.URI;

// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;

// import java.util.List;
// import java.util.Optional;


// @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// public class UserIntegrationTest {
//     @LocalServerPort
//     private int port;

//     private final String baseUrl = "http://localhost:";

//     @Autowired
//     private TestRestTemplate restTemplate;

//     @Autowired
// 	private UserRepository userRepository;


//     // @Test
//     // public void getEmployees_Success() throws Exception {
//     //     URI uri = new URI(baseUrl + port + "/employees/KFC");

//     //    restTemplate.getForEntity(uri, User[].class);


//     // //    User[] listOfEmployees = result.getBody();

//     // //    assertEquals(200, result.getStatusCode().value());

//     // //    assertEquals(1, listOfEmployees.length);
    
//     // }

//     @Test 
//     public void addEmployee_Success() throws Exception {
//         URI uri = new URI(baseUrl + port + "/employees");

//         User user = new User("hi@gmail.com", "Mary", "Admin", "KFC");

//         ResponseEntity<User> result = restTemplate.postForEntity(uri, user, User.class);

//         assertEquals(201, result.getStatusCode().value());
// 		assertEquals(user.getEmail(), result.getBody().getEmail());
//     }

//     @Test
//     public void addEmployee_Failure() throws Exception {
//         URI uri = new URI(baseUrl + port + "/employees");
//         User user = new User("a@b", "Mary", "Admin", "KFC");

//         ResponseEntity<User> result = restTemplate.postForEntity(uri, user, User.class);

//         assertEquals(409, result.getStatusCode().value());
//     }

//     @Test
//     public void updateEmployee_ValidEmail_Success() throws Exception {
//         User user = new User("hi@gmail.com", "Mary", "Admin", "KFC");
//         User updatedUser = new User("hi@gmail.com", "MaryUpdatedName", "Admin", "KFC");
//         URI uri = new URI(baseUrl + port + "/employees/" + user.getEmail());

//         ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(updatedUser), User.class);

//         assertEquals(200, result.getStatusCode().value());
// 		assertEquals(updatedUser.getEmail(), result.getBody().getEmail());
//     }

//     @Test
//     public void updateEmployee_InvalidEmail_Failure() throws Exception {
//         User user = new User("EFSGFDCDSFDSF", "Mary", "Admin", "KFC");
//         User updatedUser = new User("EFSGFDCDSFDSF", "222", "Admin", "KFC");
//         URI uri = new URI(baseUrl + port + "/employees/" + user.getEmail());

//         ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(updatedUser), User.class);

//         assertEquals(404, result.getStatusCode().value());
//     }

//     @Test 
//     public void deleteEmployee_ValidEmail_Success() throws Exception {
//         User user = new User("hi@gmail.com", "MaryUpdatedName", "Admin", "KFC");
//         URI uri = new URI(baseUrl + port + "/employees/" + "abcde");

//         userRepository.save(new User("abcde", "edcba", "Admin", "KFC"));

//         ResponseEntity<Void> result = restTemplate.exchange(uri, HttpMethod.DELETE, null, Void.class);
//         assertEquals(200,result.getStatusCode().value());

//         Optional<User>emptyValue = Optional.empty();
//         assertEquals(emptyValue,userRepository.findByEmail(user.getEmail()));


//     }

//     @Test 
//     public void deleteEmployee_InvalidEmail_Failure() throws Exception {
//         User user = new User("EFSGFDCDSFDSF", "Mary", "Admin", "KFC");
//         URI uri = new URI(baseUrl + port + "/employees/" + user.getEmail());

//         ResponseEntity<Void> result = restTemplate.exchange(uri, HttpMethod.DELETE, null, Void.class);

//         assertEquals(404,result.getStatusCode().value());




//     }

    

    

// }