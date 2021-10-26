package csd.cs203project.footfalldata;

import csd.cs203project.repository.footfalldata.FootfallDataRepository;
import csd.cs203project.repository.footfalldata.LastUpdateDateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FootfallDataIntegrationTest {
    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FootfallDataRepository footfallDataRepository;

    @Autowired
    private LastUpdateDateRepository lastUpdateDateRepository;

    @Test
    public void getFootfallData_Success () throws Exception {
        URI uri = new URI(baseUrl + port + "/footfallData");

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//        String jsonObject = result.getBody();

        assertEquals(200, result.getStatusCode().value());
    }

    @Test
    public void postFootfallData_Success () throws Exception {
        URI uri = new URI(baseUrl + port + "/footfallData");

        ResponseEntity<Void> result = restTemplate.postForEntity(uri, null, Void.class);

        assertEquals(200, result.getStatusCode().value());
    }
}
