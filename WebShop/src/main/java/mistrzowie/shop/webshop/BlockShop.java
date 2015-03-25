package mistrzowie.shop.webshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import mistrzowie.model.Klocek;
import mistrzowie.shop.logic.api.IStoreService;

/**
 *
 * @author Arek
 */
@Named("blockShop")
@SessionScoped
public class BlockShop implements Serializable {
    
    private List<Block> blocksInShop;
    
    @Inject
    private IStoreService storeService;

    public BlockShop() {
        blocksInShop = new ArrayList<>();
        blocksInShop.add(new Block(new Klocek("podluzny")));
        blocksInShop.add(new Block(new Klocek("kwadratowy")));
        blocksInShop.get(0).setAmount(50);
        blocksInShop.get(1).setAmount(50);
    }

    public List<Block> getBlocksInShop() {
//        storeService.getBlocksFromStore("podluzny");
        return blocksInShop;
    }

    public void setBlocksInShop(List<Block> blocksInShop) {
        this.blocksInShop = blocksInShop;
    }
}