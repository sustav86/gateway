package ua.sustavov.gateway.gateway.dto;

import com.fasterxml.jackson.annotation.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDto implements Serializable {
    @JsonIgnore
    private Long id;
    @Size(max = 25)
    @JsonProperty(value = "name")
    private String userName;
    @Size(max = 50)
    @JsonProperty(value = "transactionKey")
    private String apiKey;
    private int amount;
    @Size(max = 19)
    @JsonProperty(value = "cardNumber")
    private String accountNumber;
    @Size(max = 4)
    private String expirationDate;
    @Transient
    @JsonProperty(value = "cardCode")
    private String cscCode;
    @Size(max = 50)
    @JsonProperty(value = "id")
    private String customerAccountCode;
    @Size(max = 50)
    private String firstName;
    @Size(max = 50)
    private String lastName;
    @Size(max = 100)
    @JsonProperty(value = "address")
    private String street;
    @Size(max = 50)
    private String city;
    @Size(max = 2)
    private String state;
    @Size(max = 15)
    @JsonProperty(value = "zip")
    private String zipCode;
    @Size(max = 15)
    @JsonProperty(value = "poNumber")
    private String phoneNumber;
    @Size(max = 100)
    private String description;

}
