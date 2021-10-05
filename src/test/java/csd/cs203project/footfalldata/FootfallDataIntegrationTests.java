package csd.cs203project.footfalldata;

import csd.cs203project.model.FootfallData;
import csd.cs203project.repository.footfalldata.FootfallDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FootfallDataIntegrationTests {
//    @LocalServerPort
//    private int port;
//
//    private final String baseUrl = "http://localhost:";
//
//    @Autowired
//    /**
//     * Use TestRestTemplate for testing a real instance of your application as an external actor.
//     * TestRestTemplate is just a convenient subclass of RestTemplate that is suitable for integration tests.
//     * It is fault tolerant, and optionally can carry Basic authentication headers.
//     */
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private FootfallDataRepository footfallDataRepository;
//
//    @Test
//    public void getFootfallData_Success () throws Exception {
//        URI uri = new URI(baseUrl + port + "/footfallData");
//
//        ResponseEntity<FootfallData[]> result = restTemplate.getForEntity(uri, FootfallData[].class);
//        FootfallData[] footfallDataArr = result.getBody();
//
//        assertEquals(200, result.getStatusCode().value());
//        assertEquals(60, footfallDataArr.length);
//    }
}
