package ua.sustavov.gateway.gateway.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
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

}
