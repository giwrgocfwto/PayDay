package paydaycards.models.position;

import paydaycards.models.Player;

/**
 * class for sunday football match
 */
public class SundayFootballMatchPosition implements Position{

    public SundayFootballMatchPosition(){
        
    }
    
    /**
     * gets a bet from view and a dieroll from the player
     * then if the player bet on something then if the roll is the same as the thing he bet on then he doubles the amount bet
     * if he loses then he loses his bet
     * if he choses not to play nothing happens
     * @param p 
     */
    @Override
    public void performAction(Player p) {
        int bet = p.getBet();
        int die = p.getDieRoll();
        switch (die) {
            case 1:
            case 2:
                switch (bet) {
                    case 1:
                    case 2:
                        p.addCash(500);
                        System.out.println("Congrats");
                        break;
                    case 0:
                        break;
                    default:
                        p.removeCash(500);
                        System.out.println("Lame");
                        break;
                }   break;
            case 3:
            case 4:
                switch (bet) {
                    case 3:
                    case 4:
                        p.addCash(500);
                        System.out.println("Congrats");
                        break;
                    case 0:
                        break;
                    default:
                        p.removeCash(500);
                        System.out.println("Lame");
                        break;
                }   break;
            default:
                switch (bet) {
                    case 5:
                    case 6:
                        p.addCash(500);
                        System.out.println("Congrats");
                        break;
                    case 0:
                        break;
                    default:
                        p.removeCash(500);
                        System.out.println("Lame");
                        break;
                }   break;
        }
    }
    
}
