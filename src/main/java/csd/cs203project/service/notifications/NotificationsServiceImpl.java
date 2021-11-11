package csd.cs203project.service.notifications;

import csd.cs203project.model.User;
import csd.cs203project.service.SES.SESService;
import csd.cs203project.service.telegrambot.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationsServiceImpl implements NotificationsService{

    private TelegramBotService telegramBotService;
    private SESService sesService;

    @Autowired
    public NotificationsServiceImpl(TelegramBotService telegramBotService, SESService sesService) {
        this.telegramBotService = telegramBotService;
        this.sesService = sesService;
    }

    @Override
    public void sendChangedMeasures(List<String> changes, List<User> affectedUsers, String typeOfShop) {
        System.out.println("Pass 3");
        List<String> affectedUsersEmails = affectedUsers.stream()
                .map(affectedUser -> affectedUser.getEmail())
                .collect(Collectors.toList());
        telegramBotService.sendUpdate(changes, affectedUsers, typeOfShop);
        sesService.sendMessageEmailRequest(changes, affectedUsers, typeOfShop);

    }
}
