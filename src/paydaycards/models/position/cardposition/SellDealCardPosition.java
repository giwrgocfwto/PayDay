package paydaycards.models.position.cardposition;

import java.util.Random;
import paydaycards.models.Player;

/**
 * a class for the positions where we can sell deal cards
 */
public class SellDealCardPosition extends DealCardPosition{
    /**
     * creates a SellDealCardPosition
     */
    public SellDealCardPosition(){
        
    }
    
    /**
     * PreCondition: player has at least 1 deal card owned
     * if player has bought any deal cards can select one of them and sell them
     * (discards card in process)
     * PostCondition: the above
     * @param p 
     */
    @Override
    public void performAction(Player p) { 
        if(p.getDealCards().size() > 0){
            Random rn = new Random();
            int c = rn.nextInt(p.getDealCards().size());
            p.getDealCards().remove(c);
        }
    }
    
    @Override
    public String toString(){ return null; }
}
