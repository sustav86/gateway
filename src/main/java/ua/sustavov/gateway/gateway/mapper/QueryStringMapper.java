package ua.sustavov.gateway.gateway.mapper;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.QueryParam;

@Getter
@Setter
public class QueryStringMapper {

    @QueryParam("amount") int amount;
    @QueryParam("expirationDate") String expirationDate;
    @QueryParam("firstName") String firstName;
    @QueryParam("lastName") String lastName;
    @QueryParam("city") String city;
    @QueryParam("state") String state;
    @QueryParam("description") String description;
    @QueryParam("name") String name;
    @QueryParam("transactionKey") String transactionKey;
    @QueryParam("cardNumber") String cardNumber;
    @QueryParam("cardCode") String cardCode;
    @QueryParam("id") String id;
    @QueryParam("address") String address;
    @QueryParam("zip") String zip;
    @QueryParam("poNumber") String poNumber;
    @QueryParam("country") String country;
}
