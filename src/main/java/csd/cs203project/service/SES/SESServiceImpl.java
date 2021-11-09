package csd.cs203project.service.SES;

import csd.cs203project.model.User;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.SesException;
import software.amazon.awssdk.services.ses.model.*;

import java.util.List;

@Service
public class SESServiceImpl implements SESService{

    public void sendMessageEmailRequest(List<String> changes, List<User> recipients, String typeOfShop) {
        System.out.println(changes);
        String sender = "richardyer@gmail.com";
        Region region = Region.AP_SOUTHEAST_1;
        SesClient client = SesClient.builder()
                .region(region)
                .build();
        StringBuilder sb = new StringBuilder();
        for (String change: changes) {
            sb.append(change + "<br>");
        }
        try {
            send(client, sender, recipients, typeOfShop, sb.toString());
            client.close();
            System.out.println("Done");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public void send(SesClient client,
                            String sender,
                            List<User> recipients,
                            String typeOfShop,
                            String changes
    ) throws Exception {
        for (User recipient : recipients) {
            String templateData = String.format("{\"name\":\"%s\", \"shopType\":\"%s\", \"changes\":\"%s\", \"favoriteanimal\": \"cat\"}", recipient.getName(), typeOfShop, changes);
            System.out.println(templateData);
            Destination destination = Destination.builder()
                    .toAddresses(recipient.getEmail())
                    .build();
            SendTemplatedEmailRequest emailRequest = SendTemplatedEmailRequest.builder()
                    .destination(destination)
                    .source(sender)
                    .template("cs203test2")
                    .templateData(templateData)
                    .build();

            try {
                System.out.println("Attempting to send an email through Amazon SES " + "using the AWS SDK for Java...");
                SendTemplatedEmailResponse r = client.sendTemplatedEmail(emailRequest);
                System.out.println(r.toString());
            } catch (SesException e) {
                System.err.println(e.awsErrorDetails().errorMessage());
            }
        }
    }
}
