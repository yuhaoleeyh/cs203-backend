package csd.cs203project.service.SES;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.SesException;
import software.amazon.awssdk.services.ses.model.*;

import java.util.List;

@Service
public class SESServiceImpl implements SESService{

    public void sendMessageEmailRequest(List<String> realRecipient, List<String> changes) {
//        final String USAGE = "\n" +
//                "Usage:\n" +
//                " SendMessage <sender> <recipient> <subject> \n\n" +
//                "Where:\n" +
//                " sender - an email address that represents the sender. \n" +
//                " recipient - an email address that represents the recipient. \n" +
//                " subject - the subject line. \n";
//        if (args.length != 3) {
//            System.out.println(USAGE);
//            System.exit(1);
//        }
        String sender = "";
        String recipient = "";
        String subject = "Testing AWS SES";
        Region region = Region.AP_SOUTHEAST_1;
        SesClient client = SesClient.builder()
                .region(region)
                .build();
        // The email body for non-HTML email clients
        String bodyText = "Hello,\r\n" + "See the list of customers. ";
        // The HTML body of the email
        String bodyHTML = "<html>" + "<head></head>" + "<body>" + "<h1>Hello!</h1>"
                + "<p> This is a test of AWS SES.</p>" + "</body>" + "</html>";
        try {
            send(client, sender, recipient, subject, bodyText, bodyHTML);
            client.close();
            System.out.println("Done");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public void send(SesClient client,
                            String sender,
                            String recipient,
                            String subject,
                            String bodyText,
                            String bodyHTML
    ) throws Exception {
        Destination destination = Destination.builder()
                .toAddresses(recipient)
                .build();
        Content content = Content.builder()
                .data(bodyHTML)
                .build();
        Content sub = Content.builder()
                .data(subject)
                .build();
        Body body = Body.builder()
                .html(content)
                .build();
        Message msg = Message.builder()
                .subject(sub)
                .body(body)
                .build();
        SendEmailRequest emailRequest = SendEmailRequest.builder()
                .destination(destination)
                .message(msg)
                .source(sender)
                .build();
        try {
            System.out.println("Attempting to send an email through Amazon SES " + "using the AWS SDK for Java...");
            client.sendEmail(emailRequest);
        } catch (SesException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
