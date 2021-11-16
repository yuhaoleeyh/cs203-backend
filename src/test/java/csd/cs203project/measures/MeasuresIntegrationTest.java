package csd.cs203project.measures;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import csd.cs203project.model.Measures;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MeasuresIntegrationTest {
    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    
    /** 
     * @throws Exception
     */
    @Test
    public void getMeasures_Success() throws Exception {
        URI uri = new URI(baseUrl + port + "/measures");

        ResponseEntity<Measures[]> result = restTemplate.getForEntity(uri, Measures[].class);
        Measures[] measures = result.getBody();

        assertEquals(200, result.getStatusCode().value());
    } 

}
