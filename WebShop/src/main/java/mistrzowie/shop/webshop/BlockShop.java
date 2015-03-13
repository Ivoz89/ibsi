package mistrzowie.shop.webshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import mistrzowie.model.Klocek;

/**
 *
 * @author Arek
 */
@Named("blockShop")
@SessionScoped
public class BlockShop implements Serializable {
    
    private List<Klocek> blocksInShop;

    public BlockShop() {
        blocksInShop = new ArrayList<>();
    }

    public List<Klocek> getBlocksInShop() {
        return blocksInShop;
    }

    public void setBlocksInShop(List<Klocek> blocksInShop) {
        this.blocksInShop = blocksInShop;
    }
}