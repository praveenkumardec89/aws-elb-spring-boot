package nl.moneyyou.services;

import lombok.AllArgsConstructor;
import nl.moneyyou.models.MessageDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NotificationService {
    @Autowired
    AmazonSMSService amazonSMSService;

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    public String postMessage(MessageDetails messageDetails) {
        LOGGER.info("getting basic details for talent {}", messageDetails);
        return amazonSMSService.publishMessage(messageDetails);
    }

    public List<MessageDetails> getMessagesForRecipient() {
        return null;
    }
}