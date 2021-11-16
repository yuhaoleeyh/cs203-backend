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

    
    /** 
     * Get username for Telegram bot
     * @return String
     */
    @Override
    public String getBotUsername() {
        return "SpringTestBot";
    }

    
    /** 
     * Get token for Telegram bot
     * @return String
     */
    @Override
    public String getBotToken() {
        return botToken;
    }

    /** 
     * Send Telegram notification message using Telegram bot
     * @param message notification message to send
     * @param chatId id for Telegram chat to send the notification message
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
     * Handle Webhook Update
     * @param update Webhook Update
     * @return BotApiMethod<?>
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

    
    /** 
     * Get path for Telegram bot
     * @return String path for Telegram bot
     */
    @Override
    public String getBotPath() {
        return "/webhook";
    }
}
