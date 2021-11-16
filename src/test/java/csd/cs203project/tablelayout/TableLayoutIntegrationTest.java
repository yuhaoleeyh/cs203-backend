package csd.cs203project.tablelayout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import csd.cs203project.model.TableLayout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;


import org.junit.jupiter.api.Test;





@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TableLayoutIntegrationTest {
   @LocalServerPort
   private int port;

   private final String baseUrl = "http://localhost:";

   @Autowired
    private TestRestTemplate restTemplate;



   @Test
   /*NOT AUTHORISED, FAILURE 
     Passed tests in unit testing only
   */
   public void getTableLayoutUnAuthenticated_Failure () throws Exception {
       


       URI uri = new URI(baseUrl + port + "/tablelayout");

       TableLayout tableLayout = new TableLayout(500, 400, 1, 2, 2);

       HttpHeaders headers = new HttpHeaders();
 
        HttpEntity<TableLayout> request = new HttpEntity<>(tableLayout, headers);


       ResponseEntity<Object> result = restTemplate.postForEntity(uri, request, Object.class);
       assertEquals(403, result.getStatusCode().value());
   }
}