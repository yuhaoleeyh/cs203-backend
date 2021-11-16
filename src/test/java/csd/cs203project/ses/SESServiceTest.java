package csd.cs203project.ses;

import csd.cs203project.model.User;
import csd.cs203project.service.SES.SESServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.awscore.exception.AwsErrorDetails;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.SendTemplatedEmailRequest;
import software.amazon.awssdk.services.ses.model.SesException;

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

    @Mock
    private SesClient sesClient;

    @Mock
    private SesException sesException;

    /**
     * Test for sending email message request, with changes
     */
    @Test
    void sendMessageEmailRequest_WithChanges_ReturnNull() {
        //Arrange
        String built = "The Dine In Size changed from 3 to 2<br>";
        List<String> changes = new ArrayList<>();
        changes.add("The Dine In Size changed from 3 to 2");
        User user = new User("john.doe@smu.edu.sg", "John Doe", "ROLE_EMPLOYEE");
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

    /**
     * Test for sending email message to Valid users
     */
    @Test
    void send_ValidUsers_ReturnNull() {

        //Arrange
        String built = "The Dine In Size changed from 3 to 2<br>";
        String sender = "covfeed203@gmail.com";
        User user = new User("john.doe@smu.edu.sg", "John Doe", "ROLE_EMPLOYEE");
        List<User> users = List.of(user);
        String typeOfShop = "restaurant";
        SesClient client = SesClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .build();
        when(sesClient.sendTemplatedEmail(any(SendTemplatedEmailRequest.class))).thenReturn(null);

        //Act
        sesService.send(sesClient, sender, users, typeOfShop, built);

        //Assert
        verify(sesClient, times(1)).sendTemplatedEmail(any(SendTemplatedEmailRequest.class));
    }

    /**
     * Test for sending email message to Invalid users
     */
    @Test
    void send_InvalidUsers_ExceptionThrownAndHandled() {

        //Arrange
        String built = "The Dine In Size changed from 3 to 2<br>";
        String sender = "covfeed203@gmail.com";
        User user = new User("john.doe@smu.edu.sg", "John Doe", "ROLE_EMPLOYEE");
        List<User> users = List.of(user);
        String typeOfShop = "restaurant";
        SesClient client = SesClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .build();
        when(sesClient.sendTemplatedEmail(any(SendTemplatedEmailRequest.class))).thenThrow(sesException);
        when(sesException.awsErrorDetails()).thenReturn(mock(AwsErrorDetails.class));

        //Act
        sesService.send(sesClient, sender, users, typeOfShop, built);

        //Assert
        verify(sesClient, times(1)).sendTemplatedEmail(any(SendTemplatedEmailRequest.class));
        verify(sesException, times(1)).awsErrorDetails();
    }
}
