package ua.sustavov.gateway.gateway.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;

@Service
public class ConnectorImpl implements Connector {

    private static String BASE_URL = "https://apitest.authorize.net/xml/v1/request.api";

    @Override
    public String sendData(String requestContent) {

        Client client = new Client();
        WebResource webResource = client.resource(BASE_URL);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, requestContent);

        return response.getEntity(String.class);
    }
}
