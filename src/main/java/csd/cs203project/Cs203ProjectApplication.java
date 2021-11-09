package csd.cs203project;

import csd.cs203project.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import csd.cs203project.model.Shop;
import csd.cs203project.model.User;
import csd.cs203project.repository.shop.ShopRepository;
import csd.cs203project.repository.user.UserRepository;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@SpringBootApplication
public class Cs203ProjectApplication {

	public static void main(String[] args) throws Exception{
		ApplicationContext ctx = SpringApplication.run(Cs203ProjectApplication.class, args);

	}


}
