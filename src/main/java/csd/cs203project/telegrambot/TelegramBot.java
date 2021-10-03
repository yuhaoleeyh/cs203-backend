package csd.cs203project.telegrambot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@PropertySource(value="classpath:value.properties")
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return "SpringTestBot";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message updateMessage = update.getMessage();
        if (updateMessage != null) {
            String text = updateMessage.getText();
            String[] textArray = text.split(" ");
            if (textArray[0].equals("/start") && textArray.length == 2) {
                System.out.println(textArray[1]);
            }
            System.out.println(update.getMessage().getChatId());
            System.out.println(update.getMessage().getText());

            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(update.getMessage().getFrom().getFirstName());
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
