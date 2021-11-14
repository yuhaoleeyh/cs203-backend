package csd.cs203project.ses;

import csd.cs203project.model.User;
import csd.cs203project.service.SES.SESServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class SESServiceTest {

    @Spy
    @InjectMocks
    private SESServiceImpl sesService;

    @Test
    void sendMessageEmailRequest_WithChanges_ReturnNull() {
        //Arrange
        String built = "The Dine In Size changed from 3 to 2<br>";
        List<String> changes = new ArrayList<>();
        changes.add("The Dine In Size changed from 3 to 2");
        User user = new User("john.doe@smu.edu.sg", "John Doe", "ROLE_EMPLOYEE", "SMU");
        List<User> users = List.of(user);
        String typeOfShop = "restaurant";
        SesClient client = SesClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .build();
        doNothing().when(sesService).send(any(SesClient.class), any(String.class), any(List.class), any(String.class), any(String.class));

        //Act
        sesService.sendMessageEmailRequest(changes, users, typeOfShop);

        //Assert
        verify(sesService, times(1)).send(any(SesClient.class), eq("covfeed203@gmail.com"), eq(users), eq(typeOfShop), eq(built));
    }

}
