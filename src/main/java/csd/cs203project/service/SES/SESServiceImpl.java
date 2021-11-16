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
 
    /** 
     * Send Message Email Request given the changes, recipients, and type of shop
     * @param changes updates to include in notification email
     * @param recipients users to send the notification email
     * @param typeOfShop type of shop for the notification email
     */
    public void sendMessageEmailRequest(List<String> changes, List<User> recipients, String typeOfShop) {
        String sender = "covfeed203@gmail.com";
        Region region = Region.AP_SOUTHEAST_1;
        SesClient client = SesClient.builder()
                .region(region)
                .build();
        StringBuilder sb = new StringBuilder();
        for (String change: changes) {
            sb.append(change + "<br>");
        }

        send(client, sender, recipients, typeOfShop, sb.toString());
        client.close();
    }

    /** 
     * Send the email notification for updated measures to recipients
     * @param client Amazon Simple Email Service client
     * @param sender Email Sender
     * @param recipients Email Recipients
     * @param typeOfShop type of shop for the notification email
     * @param changes updates to include in notification email
     */
    public void send(SesClient client,
                            String sender,
                            List<User> recipients,
                            String typeOfShop,
                            String changes
    ) {
        for (User recipient : recipients) {
            String templateData = String.format("{\"name\":\"%s\", \"shopType\":\"%s\", \"changes\":\"%s\"}", recipient.getName(), typeOfShop, changes);
            Destination destination = Destination.builder()
                    .toAddresses(recipient.getEmail())
                    .build();
            SendTemplatedEmailRequest emailRequest = SendTemplatedEmailRequest.builder()
                    .destination(destination)
                    .source(sender)
                    .template("cs203test4")
                    .templateData(templateData)
                    .build();

            try {
                SendTemplatedEmailResponse r = client.sendTemplatedEmail(emailRequest);
            } catch (SesException e) {
                System.err.println(e.awsErrorDetails().errorMessage());
            }
        }
    }
}
