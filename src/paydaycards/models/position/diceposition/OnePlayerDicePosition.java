package paydaycards.models.position.diceposition;

import paydaycards.models.Player;
import paydaycards.models.cards.DealCard;

public class OnePlayerDicePosition extends DicePosition{
    private TypeDicePosition type;

    /**
     * getters and setters for type
     * @return 
     */
    public TypeDicePosition getType() {
        return type;
    }
    public void setType(TypeDicePosition type) {
        this.type = type;
    }
    
    /**
     * creates an one player dice position with a certain type
     * @param type 
     */
    public OnePlayerDicePosition(TypeDicePosition type) {
        this.type = type;
    }
    
    /**
     * PreCondition: none
     * first of all all the dicerolles will be internally in the perform action by creating a random int between 1-6.
     * If its type is sweepstakes then rolls the dice and gives player dicex1000
     * If type is family casino night then he rolls the dice , if its 1-3-5 he gives jackpot 500 cash,
     * if its 2-4-6 then 500 cash are added to his cash
     * If type is yard sale then the player rolls the dice and pays dicex100 , then he takes a card from the dealcardpile for free
     * PostCondition: all the above
     * @param p 
     */
    @Override
    public void performAction(Player p) {
        if(null != type)switch (type) {
            case Sweepstakes:{
                int dice = p.getDieRoll();
                p.addCash(dice*1000);
                    break;
                }
            case FamilyCasinoNight:{
                int dice = p.throwDie();
                if(dice % 2 == 1){
                    p.getJackpot().addJackpot(500);
                    p.removeCash(500);
                }
                else{
                    p.addCash(500);
                }       break;
                }
            case YardSale:{
                int dice = p.throwDie();
                p.removeCash(500*dice);
                DealCard c = (DealCard) p.getDealCardPile().drawCard();
                p.addDealCard(c);
                    break;
                }
            default:
                break;
        }
    }
    
    @Override
    public String toString(){ return null; }
    
}
