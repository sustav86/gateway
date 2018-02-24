package ua.sustavov.gateway.gateway.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import ua.sustavov.gateway.gateway.serialization.AuthTransactionDeserializer;
import ua.sustavov.gateway.gateway.serialization.AuthTransactionSerializer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@ToString
@Getter
@Setter
@JsonSerialize(using = AuthTransactionSerializer.class)
@JsonDeserialize(using = AuthTransactionDeserializer.class)
public class AuthTransaction implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String apiKey;
    private String amount;
    private String customerId;
    private String billToFirstName;
    private String billToLastName;
    private String billToAddress;
    private String billToCity;
    private String billToState;
    private String billToZip;
    private String billToPhoneNumber;
    private String description;
    private String resultCode;
    private String messageCode;
    private String messageText;
    private String responseCode;
    private String authCode;
    private String avsResultCode;
    private String cvvResultCode;
    private String transId;
    private String approvedAmount;
    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public AuthTransaction(String userName,
                           String apiKey,
                           String amount,
                           String customerId,
                           String billToFirstName,
                           String billToLastName,
                           String billToAddress,
                           String billToCity,
                           String billToState,
                           String billToZip,
                           String billToPhoneNumber,
                           String description,
                           String resultCode,
                           String messageCode,
                           String messageText,
                           String responseCode,
                           String authCode,
                           String avsResultCode,
                           String cvvResultCode,
                           String transId,
                           String approvedAmount,
                           Transaction transaction) {
        this.userName = userName;
        this.apiKey = apiKey;
        this.amount = amount;
        this.customerId = customerId;
        this.billToFirstName = billToFirstName;
        this.billToLastName = billToLastName;
        this.billToAddress = billToAddress;
        this.billToCity = billToCity;
        this.billToState = billToState;
        this.billToZip = billToZip;
        this.billToPhoneNumber = billToPhoneNumber;
        this.description = description;
        this.resultCode = resultCode;
        this.messageCode = messageCode;
        this.messageText = messageText;
        this.responseCode = responseCode;
        this.authCode = authCode;
        this.avsResultCode = avsResultCode;
        this.cvvResultCode = cvvResultCode;
        this.transId = transId;
        this.approvedAmount = approvedAmount;
        this.transaction = transaction;
    }
}
