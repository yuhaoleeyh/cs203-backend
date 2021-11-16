package csd.cs203project.service.SES;

import csd.cs203project.model.User;
import software.amazon.awssdk.services.ses.SesClient;

import java.util.List;

public interface SESService {
    public void sendMessageEmailRequest(List<String> changes, List<User> recipients, String typeOfShop);

    void send(SesClient client,
            String sender,
            List<User> recipients,
              String typeOfShop,
              String changes
    ) throws Exception;
}
