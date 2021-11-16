package csd.cs203project.notifications;

import csd.cs203project.model.User;
import csd.cs203project.service.SES.SESServiceImpl;
import csd.cs203project.service.notifications.NotificationsServiceImpl;
import csd.cs203project.service.telegrambot.TelegramBotServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationsServiceTest {
    @Mock
    private TelegramBotServiceImpl telegramBotService;

    @Mock
    private SESServiceImpl sesService;

    @InjectMocks
    private NotificationsServiceImpl notificationsService;

    /**
     * Test for sending changed measures using both SES and Telegram
     */
    @Test
    void sendChangedMeasures_CalledBothSESAndTelegram(){
        //Arrange
        List<String> changes = new ArrayList<>();
        List<User> affectedUsers = new ArrayList<>();
        String typeOfShop = "";
        doNothing().when(telegramBotService).sendUpdate(any(List.class), any(List.class), any(String.class));
        doNothing().when(sesService).sendMessageEmailRequest(any(List.class), any(List.class), any(String.class));

        //Act
        notificationsService.sendChangedMeasures(changes, affectedUsers, typeOfShop);

        //Assert & Verify
        verify(telegramBotService, times(1)).sendUpdate(changes, affectedUsers, typeOfShop);
        verify(sesService, times(1)).sendMessageEmailRequest(changes, affectedUsers, typeOfShop);
    }
}
