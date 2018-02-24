package ua.sustavov.gateway.gateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthTransactionDto {
    @JsonIgnore
    private Long id;
    @Size(max = 25)
    @JsonProperty(value = "name")
    private String userName;
    @Size(max = 50)
    @JsonProperty(value = "transactionKey")
    private String apiKey;
    @Size(max = 15)
    private String amount;
    @Size(max = 20)
    @JsonProperty(value = "id")
    private String customerId;
    @Size(max = 50)
    @JsonProperty(value = "firstName")
    private String billToFirstName;
    @Size(max = 50)
    @JsonProperty(value = "lastName")
    private String billToLastName;
    @Size(max = 60)
    @JsonProperty(value = "street")
    private String billToAddress;
    @Size(max = 40)
    @JsonProperty(value = "city")
    private String billToCity;
    @Size(max = 2)
    @JsonProperty(value = "state")
    private String billToState;
    @Size(max = 10)
    @JsonProperty(value = "zip")
    private String billToZip;
    @Size(max = 25)
    @JsonProperty(value = "poNumber")
    private String billToPhoneNumber;
    @Size(max = 255)
    private String description;
    @Size(max = 5)
    private String resultCode;
    @Size(max = 10)
    @JsonProperty(value = "code")
    private String messageCode;
    @Size(max = 255)
    @JsonProperty(value = "text")
    private String messageText;
    @Size(max = 1)
    private String responseCode;
    @Size(max = 6)
    private String authCode;
    @Size(max = 1)
    private String avsResultCode;
    @Size(max = 1)
    private String cvvResultCode;
    @Size(max = 50)
    private String transId;
    @Size(max = 15)
    private String approvedAmount;
    private long transactionId;

}
