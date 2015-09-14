package mistrzowie.shop.webshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import mistrzowie.model.Klocek;
import mistrzowie.shop.dao.ItemDao;
import mistrzowie.shop.dao.ItemEntity;
import mistrzowie.shop.logic.api.IStoreService;
import mistrzowie.shop.logic.impl.JMSStoreService;

/**
 *
 * @author Arek
 */
@Named("blockShop")
@SessionScoped
public class BlockShop implements Serializable {

    private List<ItemEntity> blocksInShop;
    
    @EJB
    private ItemDao dao;

    @Inject
    private IStoreService storeService;
    
    @EJB
    private JMSStoreService jms;
    
    public BlockShop() {
        
    }
    
    @PostConstruct
    public void init() {
        blocksInShop = new ArrayList<>();
        ItemEntity entity1 = new ItemEntity();
        entity1.setName("Frytki");
        entity1.setAmount(15);
        entity1 = dao.insert(entity1);
        
        blocksInShop.add(entity1);
    }

    public List<ItemEntity> getBlocksInShop() {
        return blocksInShop;
    }

    public void setBlocksInShop(List<ItemEntity> blocksInShop) {
        this.blocksInShop = blocksInShop;
    }

    public String buyBlock(ItemEntity block) {
        ItemEntity blockTmp = dao.findByName(block.getName());
        if (blockTmp.getAmount() != block.getAmount()) {
            block.setAmount(blockTmp.getAmount());
        }
        if(block.getAmount() < 20) {
            //TODO dorobic jms
            jms.send(block);
        }
        if(block.getAmount() > 0) {
            block.setAmount(block.getAmount() - 1);
            dao.update(block);
        }
        return null;
    }
}
