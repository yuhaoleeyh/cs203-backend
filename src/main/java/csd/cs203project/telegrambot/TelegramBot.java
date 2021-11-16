package csd.cs203project.telegrambot;

import csd.cs203project.model.User;
import csd.cs203project.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.NoSuchElementException;

@Component
public class TelegramBot extends TelegramWebhookBot {


    @Autowired
    private UserRepository userRepository;


    @Value("${telegramBotToken}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return "SpringTestBot";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    /**
     * Sends message to user with specified chat ID
     * @param message
     * @param chatId
     */
    public void sendMessage(String message, String chatId) {
        SendMessage telegramMessage = new SendMessage();
        telegramMessage.setText(message);
        telegramMessage.setChatId(chatId);
        try{
            execute(telegramMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles webhook request, adds user telegram chat ID if start command given
     */
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        Message updateMessage = update.getMessage();
        if (updateMessage != null) {
            String chatId = Long.toString(updateMessage.getChatId());
            String text = updateMessage.getText();
            String[] textArray = text.split(" ");
            if (textArray[0].equals("/start") && textArray.length == 2) {
                try {
                    User user = userRepository.findByTelegramSignUpToken(textArray[1]).orElseThrow();
                    user.setTelegramChatId(chatId);
                    userRepository.save(user);
                }
                catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public String getBotPath() {
        return "/webhook";
    }
}
