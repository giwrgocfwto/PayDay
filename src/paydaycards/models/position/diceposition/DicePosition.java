package paydaycards.models.position.diceposition;

import paydaycards.models.Player;
import paydaycards.models.position.Position;

/**
 * interface for dice positions
 */
public abstract class DicePosition implements Position{
    public DicePosition(){
        
    }
    
    @Override
    public void performAction(Player p) {}
    
    @Override
    public String toString(){ return null; }
}
