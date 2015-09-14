package mistrzowie.shop.logic.impl;

import hashtag.developers.datamodel.Shipping;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import mistrzowie.shop.dao.ItemDao;
import mistrzowie.shop.dao.ItemEntity;

/**
 *
 * @author Arek
 */
@WebService(serviceName = "ShopWebService")
public class SOAPStoreService {
    
    @EJB
    private ItemDao dao;

    @WebMethod
    public String processOrderFromWarehouse(Shipping shipping) {
        try {
        ItemEntity entity = dao.findByName(
                shipping.getProductOrder().getProduct().getName());
        entity.setAmount(entity.getAmount() + shipping.getProductOrder().getQuantity());
        dao.update(entity);
        } catch (Exception ex) {
            return "ERROR";
        }
        return "OK";
    }

    public static void main(String[] argv) {
        Object implementor = new SOAPStoreService();
        String address = "http://localhost:9000/";
        Endpoint.publish(address, implementor);
    }
}
