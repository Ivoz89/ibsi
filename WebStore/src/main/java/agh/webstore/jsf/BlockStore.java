package agh.webstore.jsf;

import agh.webstore.db.StoreDb;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import mistrzowie.model.Klocek;

/**
 *
 * @author Arek
 */
@Named("blockStore")
@RequestScoped
public class BlockStore implements Serializable {

    private List<Block> blocksInStore;

    private final StoreDb storeDb = new StoreDb();

    public BlockStore() {
        blocksInStore = new ArrayList<>();
        int podluzny = storeDb.pobierzIloscKlockow("podluzny");
        int kwadratowy = storeDb.pobierzIloscKlockow("kwadratowy");
        blocksInStore.add(new Block(new Klocek("podluzny"),podluzny));
        blocksInStore.add(new Block(new Klocek("kwadratowy"), kwadratowy));
    }

    public List<Block> getBlocksInStore() {
        return blocksInStore;
    }

    public void setBlocksInStore(List<Block> blocksInShop) {
        this.blocksInStore = blocksInShop;
    }

    public String addBlock(Block block) {
        storeDb.zmienIloscWMagazynie(block.getRodzaj(), block.getAmount() + 30);
        return "blockStore?faces-redirect=true";
    }
}
