package csd.cs203project.telegrambot;

import csd.cs203project.model.User;
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
public class TelegramBotServiceTest {

    @Mock
    private TelegramBot telegramBot;

    @InjectMocks
    private TelegramBotServiceImpl telegramBotService;

    @Test
    void sendUpdate_UpdatesExist_ReturnNull() {
        //Arrange
        String built = "New Covid-19 F&B Measures for restaurant have been released!\n\n" +
                "The Dine In Size changed from 3 to 2\n";
        List<String> updates = new ArrayList<>();
        updates.add("The Dine In Size changed from 3 to 2");
        User user = new User("john.doe@smu.edu.sg", "John Doe", "ROLE_EMPLOYEE");
        user.setTelegramChatId("12345678");
        List<User> users = List.of(user);
        String typeOfShop = "restaurant";
        doNothing().when(telegramBot).sendMessage(any(String.class), any(String.class));

        //Act
        telegramBotService.sendUpdate(updates, users, typeOfShop);

        //Assert
        verify(telegramBot, times(1)).sendMessage(built, "12345678");

    }

}
