package ua.sustavov.gateway.gateway.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@ToString
@Getter
@Setter
@Data
@NoArgsConstructor
public class Transaction implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String apiKey;
    private int amount;
    private String accountNumber;
    private String expirationDate;
    private String cscCode;
    private String customerAccountCode;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String description;
    private String responseCode;
    private String responseMessage;
    private String authCode;
    private String avsResultCode;
    private String cvvResultCode;

}
