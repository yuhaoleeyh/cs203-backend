package csd.cs203project.controller.webhook;

import csd.cs203project.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;

//@CrossOrigin(origins = "https://17c4-220-255-81-209.ngrok.io:443")
@RestController
public class WebhookController {

    @Autowired
    private TelegramBot telegramBot;

    
    /** 
     * Handle updates for webhook
     * @param update updates for webhook
     */
    @PostMapping("/webhook")
    public void handleUpdates(@RequestBody Update update) {
        telegramBot.onWebhookUpdateReceived(update);
    }
}
