package nl.moneyyou.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Data
@Builder
@Wither
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "DeliveredMessages")
public class MessageDetails {

    private String messageId;
    private String payload;
    private String mobileNo;
    private String email;
}
