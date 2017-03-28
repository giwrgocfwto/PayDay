package paydaycards.models.position.cardposition;

import paydaycards.models.Player;

/**
 * these are the positions that player can buy a deal card
 */
public class BuyDealCardPosition extends DealCardPosition{
    /**
     * creates a BuyDealCardPosition
     */
    public BuyDealCardPosition(){
        
    }
    
    /**
     * actually everything from this happens on controller
     * so if the player has accepted the action then it removes the cash from him (buys the offer)
     * @param p 
     */
    @Override
    public void performAction(Player p) { 
        if(p.isAccepted()){
            p.removeCash(p.getDealCards().get(p.getDealCards().size()-1).getCost());
        }
    }
    
    
    @Override
    public String toString(){ return null; }
}
