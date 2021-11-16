package csd.cs203project.controller.webhook;

import csd.cs203project.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;

//@CrossOrigin(origins = "https://17c4-220-255-81-209.ngrok.io:443")
@RestController
public class WebhookController {

    @Autowired
    private TelegramBot telegramBot;

    @PostMapping("/webhook")
    public ResponseEntity handleUpdates(@RequestBody Update update) {
        telegramBot.onWebhookUpdateReceived(update);
        return new ResponseEntity(HttpStatus.OK);
    }
}
