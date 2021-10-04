package csd.cs203project.service.SES;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import java.util.List;

public interface SESService {
    public void sendMessageEmailRequest(List<String> recipient, String bodyHTML);

    void send(SesClient client,
                            String sender,
                            String recipient,
                            String subject,
                            String bodyText,
                            String bodyHTML
    ) throws Exception;
}
