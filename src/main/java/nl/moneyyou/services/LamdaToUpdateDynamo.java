package nl.moneyyou.services;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import nl.moneyyou.models.MessageDetails;

public class LamdaToUpdateDynamo implements RequestHandler<MessageDetails, MessageDetails> {
     
    private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "DeliveredMessages";
    private Regions REGION = Regions.EU_WEST_1;

    @Override
    public MessageDetails handleRequest(MessageDetails messageDetails, Context context) {
  
        this.initDynamoDbClient();
 
        persistData(messageDetails);

        return messageDetails;
    }
 
    private PutItemOutcome persistData(MessageDetails messageDetails)
      throws ConditionalCheckFailedException {
        return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
          .putItem(
            new PutItemSpec().withItem(new Item()
              .withString("messageId", messageDetails.getMessageId())
              .withString("mobileNo", messageDetails.getMobileNo())
              .withString("email", messageDetails.getEmail())
                .withString("payload", messageDetails.getPayload())));
    }
 
    private void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);
    }
}