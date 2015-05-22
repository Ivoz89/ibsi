package mistrzowie.shop.logic.impl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.Serializable;
import mistrzowie.shop.logic.api.IStoreService;

/**
 *
 * @author Arek
 */
public class RESTStoreService implements IStoreService, Serializable {
    
    private static String baseRestUrl = "http://localhost:8080/WebStore/rest/";
    
    public RESTStoreService() {
        PropertiesUtil propMgr = new PropertiesUtil("rest-config.properties");
        baseRestUrl = propMgr.getProperty("rest.url");
    }

    @Override
    public int getBlocksFromStore(String blockType) {
        Client client = new Client();
        
        WebResource webResource = client.resource(baseRestUrl + "store/" + blockType);
        
        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
        
        String output = response.getEntity(String.class);
        return Integer.parseInt(output);
    }
    
}
