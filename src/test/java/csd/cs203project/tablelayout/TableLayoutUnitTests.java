package csd.cs203project.tablelayout;

import org.apache.http.client.methods.RequestBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import csd.cs203project.controller.tablelayout.TableLayoutController;
import csd.cs203project.model.TableLayout;
import csd.cs203project.model.User;
import csd.cs203project.service.tablelayout.TableLayoutService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import java.util.List;

import javax.ws.rs.core.Application;

import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;


// import org.junit.platform.runner.JUnitPlatform;





// @RunWith(.class)
// @ExtendWith(SpringExtension.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// public class TableLayoutUnitTests {
//    @LocalServerPort
//    private int port;

//    private final String baseUrl = "http://localhost:";

//    @Autowired
//    /**
//     * Use TestRestTemplate for testing a real instance of your application as an external actor.
//     * TestRestTemplate is just a convenient subclass of RestTemplate that is suitable for integration tests.
//     * It is fault tolerant, and optionally can carry Basic authentication headers.
//     */
//    private TestRestTemplate restTemplate;

//    @Bean
// 	public RestTemplate getRestTemplate() {
// 		return new RestTemplate();
// 	}



//    @Test
//    public void getFootfallData_Success () throws Exception {
        
             
 
         
         
//         //Verify request succeed
//         // Assert.assertEquals(201, result.getStatusCodeValue());



//        URI uri = new URI(baseUrl + port + "/employees/CoffeeBean");

//        TableLayout tableLayout = new TableLayout(5000, 4000, 100, 200, 20);

//        HttpHeaders headers = new HttpHeaders();
//       //  headers.set("X-COM-PERSIST", "true");      
 
//         HttpEntity<TableLayout> request = new HttpEntity<>(tableLayout, headers);

//        ResponseEntity<Object[]> result = restTemplate.postForEntity(uri, request, Object[].class);
//        Assert.assertEquals(200, result.getStatusCodeValue());
//    }
// }


// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration
// @WebAppConfiguration
// // @RunWith(SpringRunner.class)
// // @WebMvcTest(value = TableLayoutController.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
// // @ContextConfiguration(classes=Application.class)
// public class TableLayoutUnitTests {
//     private MockMvc mvc;
//     @MockBean TableLayoutService tableLayoutService;

//     @Autowired
//     private WebApplicationContext wac;
//     // @LocalServerPort
//     // private int port;

//     // private final String baseUrl = "http://localhost:";

//     // @Autowired
//     // private TestRestTemplate restTemplate;


//     @Before
//     public void setup() {
//         // this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//         mvc = MockMvcBuilders
// 				.webAppContextSetup(wac)
// 				.build();
//     }


//     @Test
//     public void getTableLayout() throws Exception {
//         // URI uri = new URI(baseUrl + port + "/tablelayout");

//         TableLayout tableLayout = new TableLayout(5000, 4000, 100, 200, 20);

//         MockHttpServletRequestBuilder request = MockMvcRequestBuilders
//         // .get("/tablelayout");
//         .get("/employees/CoffeeBean");
//         // .accept(MediaType.APPLICATION_JSON)
//         // .content("{\"widthOfShop\":5000,\"heightOfShop\":6000,\"widthOfTable\":100,\"heightOfTable\":100, \"numOfTables\":20}");
//         // .contentType(MediaType.APPLICATION_JSON);

//         mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
                    
//     //    ResponseEntity<User[]> result = restTemplate.getForEntity(uri, User[].class);
//     //    User[] listOfEmployees = result.getBody();

//     //    assertEquals(200, result.getStatusCode().value());
    
//     }
// }
