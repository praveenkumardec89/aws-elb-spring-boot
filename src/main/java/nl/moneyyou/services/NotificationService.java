package nl.moneyyou.services;

import lombok.AllArgsConstructor;
import nl.moneyyou.models.MessageDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    public String postMessage(MessageDetails messageDetails) {
        LOGGER.info("getting basic details for talent {}", messageDetails);
        return "Completed";
    }
}