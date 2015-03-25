package mistrzowie.shop.logic.api;

import java.util.List;
import javax.ejb.Local;
import mistrzowie.model.Klocek;

/**
 *
 * @author Arek
 */
@Local
public interface IStoreService {
    
    List<Klocek> getBlocksFromStore();
}
