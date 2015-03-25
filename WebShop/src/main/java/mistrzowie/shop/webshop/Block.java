package mistrzowie.shop.webshop;

import java.io.Serializable;
import mistrzowie.model.Klocek;

/**
 *
 * @author Arek
 */
public class Block implements Serializable {
    
    private Klocek block;
    private int amount;
    
    public Block(Klocek block) {
        this.block = block;
    }

    public String getRodzaj() {
        return block.getRodzaj();
    }

    public void setBlock(Klocek block) {
        this.block = block;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
}
