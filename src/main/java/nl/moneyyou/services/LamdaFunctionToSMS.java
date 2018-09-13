package nl.moneyyou.services;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import nl.moneyyou.models.MessageDetails;

import java.util.HashMap;
import java.util.Map;

public class LamdaFunctionToSMS implements RequestHandler<MessageDetails, MessageDetails>{

    private Map<String, MessageAttributeValue> setSmsAttributes() {
        Map<String, MessageAttributeValue> smsAttributes =
                new HashMap<>();
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                .withStringValue("Praveen Gundu") //The sender ID shown on the device.
                .withDataType("String"));
        smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
                .withStringValue("0.50") //Sets the max price to 0.50 USD.
                .withDataType("Number"));
        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                .withStringValue("Promotional") //Sets the type to promotional.
                .withDataType("String"));
        return smsAttributes;
    }

     private static void sendSMSMessage(AmazonSNS snsClient, String message,
                                       String phoneNumber, Map<String, MessageAttributeValue> smsAttributes) {
        PublishResult result = snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes));
       // System.out.println(result); // Prints the message ID.
    }

    @Override
    public MessageDetails handleRequest(MessageDetails messageDetails, Context context) {
        AWSCredentialsProvider awsCredentialsProvider = new ClasspathPropertiesFileCredentialsProvider();
        AmazonSNS snsClient = AmazonSNSClient.builder()
                .withRegion(Regions.EU_WEST_1)
                .withCredentials(awsCredentialsProvider).build();
       // MessageDetails messageDetails = (MessageDetails)input;
        String message = messageDetails.getPayload();
        String phoneNumber = messageDetails.getEmail();
        sendSMSMessage(snsClient, message, phoneNumber, setSmsAttributes());
        return messageDetails;
    }

}
