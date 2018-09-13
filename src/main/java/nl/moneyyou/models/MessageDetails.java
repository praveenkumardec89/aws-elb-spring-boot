package nl.moneyyou.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class MessageDetails {

    @JsonIgnore
    private String messageId;

    private String payload;
    private String email;
}
