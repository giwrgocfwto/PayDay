package paydaycards.models.position.diceposition;

import paydaycards.models.Player;

public class TwoPlayerDicePosition extends DicePosition{
    private TypeDicePosition type;

    
    /**
     * setters and getters
     * @return 
     */
    public TypeDicePosition getType() {
        return type;
    }
    public void setType(TypeDicePosition type) {
        this.type = type;
    }
    
    /**
     * creates a two player position with a certain type
     * @param type 
     */
    public TwoPlayerDicePosition(TypeDicePosition type) {
        this.type = type;
    }
    
    /**
     * PreCondition: none
     * first of all a dice is rolled internally each time needed by picking randomly a number between 1-6
     * If type is lottery then p1 rolles dice and picks a number , then p2 rolles dice and picks a different! number
     * then a dice is rolled until 1 of the 2 numbers picked is rolled , then 1000 cash are added to the player that wins cash'
     * If type is radio contest then the 2 players roll the dice and the 1000 cash are added to winner's cash 
     * if its a tie then they roll again
     * PostConditions: all the above
     * @param p 
     */
    @Override
    public void performAction(Player p) {
        switch (type) {
            case Lottery:
                int d1 = p.throwDie();
                int d2 = p.getOpponent().throwDie();
                int d12 = 0, d22 = 0;
                while((d12 != d1)||(d22 != d2)){
                    d12 = p.throwDie();
                    if(d12 == d1){
                        p.addCash(1000);
                        break;
                    }
                    d22 = p.getOpponent().throwDie();
                    if(d22 == d2){
                        p.getOpponent().addCash(1000);
                        break;
                    }
                }
            case RadioContest:
                d1 = 0;
                d2 = 1;
                while(d1 != d2){
                    d1 = p.throwDie();
                    d2 = p.getOpponent().throwDie();
                }
                if(d1 > d2){
                    p.addCash(1000);
                }
                else{
                    p.getOpponent().addCash(1000);
                }
        }
    }
    
    @Override
    public String toString(){ return null; }
}
