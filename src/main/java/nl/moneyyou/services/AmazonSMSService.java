package nl.moneyyou.services;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import lombok.AllArgsConstructor;
import nl.moneyyou.models.MessageDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AmazonSMSService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonSMSService.class);
    private static final String topicArn = "arn:aws:sns:eu-west-1:862504189109:cloud-formation-sns-MoneyYouTopic-1OXJONI3Y89S";

    public String publishMessage(MessageDetails messageDetails) {
        LOGGER.info("getting basic details for talent {}", messageDetails);
        //publish to an SNS topic
        String msg = "My text published to SNS topic with email endpoint";
        //create a new SNS client and set endpoint
        AmazonSNSClient snsClient = new AmazonSNSClient(new ClasspathPropertiesFileCredentialsProvider());
        snsClient.setRegion(Region.getRegion(Regions.EU_WEST_1));
        PublishRequest publishRequest = new PublishRequest(topicArn, messageDetails.getPayload());
        PublishResult publishResult = snsClient.publish(publishRequest);
        //print MessageId of message published to SNS topic
        LOGGER.info("MessageId - {}" , publishResult.getMessageId());
        return publishResult.getMessageId();
    }
}