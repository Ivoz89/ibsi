package mistrzowie.shop.logic.impl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.Serializable;
import java.util.List;
import mistrzowie.model.Klocek;
import mistrzowie.shop.logic.api.IStoreService;

/**
 *
 * @author Arek
 */
public class RESTStoreService implements IStoreService, Serializable {
    
    private static final String baseRestUrl = "http://localhost:8080/WebStore/rest/";

    @Override
    public int getBlocksFromStore(String blockType) {
        Client client = new Client();
        
        WebResource webResource = client.resource(baseRestUrl + "store/" + blockType);
        
        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
        
        String output = response.getEntity(String.class);
        System.out.println(output);
        return 0;
    }
    
}
