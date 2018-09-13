package nl.moneyyou.services;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import lombok.AllArgsConstructor;
import nl.moneyyou.models.MessageDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class NotificationService {
    @Autowired
    AmazonSMSService amazonSMSService;

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    public String postMessage(MessageDetails messageDetails) {
        LOGGER.info("posting message for {}", messageDetails);
        return amazonSMSService.publishMessage(messageDetails);
    }

    public List<Map<String, AttributeValue>> getMessagesForRecipient(String mobileNo) {
        Condition scanFilterCondition = new Condition()
                .withComparisonOperator(ComparisonOperator.EQ.toString())
                .withAttributeValueList(new AttributeValue().withS(mobileNo));
        Map<String, Condition> conditions = new HashMap<>();
        conditions.put("status", scanFilterCondition);

        ScanRequest scanRequest = new ScanRequest()
                .withTableName("DeliveredMessages")
                .withScanFilter(conditions);
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(Regions.EU_WEST_1));
        DynamoDB dynamoDB = new DynamoDB(client);
        return  client.scan(scanRequest).getItems();
    }
}