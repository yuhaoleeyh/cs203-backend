package csd.cs203project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import csd.cs203project.model.Shop;
import csd.cs203project.model.User;
import csd.cs203project.repository.shop.ShopRepository;
import csd.cs203project.repository.user.UserRepository;


@SpringBootApplication
public class Cs203ProjectApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Cs203ProjectApplication.class, args);

		// JPA user repository init
        UserRepository users = ctx.getBean(UserRepository.class);
		ShopRepository shops = ctx.getBean(ShopRepository.class);
        System.out.println("[Add shop]: " + shops.save(new Shop("Coffee Bean")));
		System.out.println("[Add user]: " + users.save(new User("Mary", "Supervisor", "NUS")));
        System.out.println("[Add user]: " + users.save(new User("James", "Admin", "SMU")));
	}

}
