package mistrzowie.shop.webshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import mistrzowie.model.Klocek;
import mistrzowie.shop.logic.api.IStoreService;
import org.primefaces.context.RequestContext;

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
        blocksInShop.get(0).setAmount(35);
        blocksInShop.get(1).setAmount(35);
    }

    public List<Block> getBlocksInShop() {
        return blocksInShop;
    }

    public void setBlocksInShop(List<Block> blocksInShop) {
        this.blocksInShop = blocksInShop;
    }

    public void buyBlock(Block block) {
        int storeAmount = 0;
        if (block.getAmount() < 25) {
            storeAmount = storeService.getBlocksFromStore(block.getRodzaj());
        }
        if (block.getAmount() > 0) {
            block.setAmount(block.getAmount() - 1 + storeAmount);
        }
    }
}
