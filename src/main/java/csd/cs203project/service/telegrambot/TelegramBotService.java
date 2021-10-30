package csd.cs203project.service.telegrambot;

import csd.cs203project.model.User;

import java.util.List;

public interface TelegramBotService {

    void sendUpdate(List<String> updates, List<User> users, String typeOfShop);
}
