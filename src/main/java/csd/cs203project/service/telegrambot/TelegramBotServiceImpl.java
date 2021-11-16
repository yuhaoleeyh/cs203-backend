package csd.cs203project.service.telegrambot;

import csd.cs203project.model.User;
import csd.cs203project.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramBotServiceImpl implements TelegramBotService{

    @Autowired
    private TelegramBot telegramBot;


    @Override
    public void sendUpdate(List<String> updates, List<User> users, String typeOfShop) {
        StringBuilder sb = new StringBuilder();
        sb.append("New Covid-19 F&B Measures for " + typeOfShop + " have been released!\n\n");
        for (String update: updates) {
            sb.append(update + "\n");
        }

        for (User user: users) {
            String userChatId = user.getTelegramChatId();
            if (userChatId != null && !userChatId.equals("")) {
                telegramBot.sendMessage(sb.toString(), userChatId);
            }

        }
    }
}
