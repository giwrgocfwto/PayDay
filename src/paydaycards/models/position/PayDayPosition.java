package paydaycards.models.position;

import paydaycards.models.Player;
import paydaycards.models.cards.MailCard;

public class PayDayPosition implements Position{
    
    /**
     * creats a PayDayPosition() 
     */
    public PayDayPosition(){
        
    }
    
    /**
     * PreCondition: none i guess (?)
     * gives player who reaches paydayposition 2500 cash
     * then he pays all his bills and discards the cards
     * calls payLoan
     * if players cash is 1000 or more asks player if he wants to pay the loan or an amount of it
     * for ex if player has 2400 cash and a loan of 2k , asks him if he wants to pay 1000 or 2x 1000
     * if its the last month then discards all dealcards and makes reachEnd return true
     * if its not then he goes to the beginning again
     * PostCondition: all of the above
     * @param p 
     */
    @Override
    public void performAction(Player p) {
        if(p.getMonths() > 0){
            p.addCash(2500);
            p.payBills();
            for (MailCard mc : p.getMailCards()) {
                p.getMailCardPile().discardCard(mc);
            }
            if(p.getLoans() < p.getCash()){
                p.removeCash(p.getLoans());
                p.setLoans(0);
            }
            else{
                p.payLoans();
            }  
            if(p.getMonths() > 1){
                p.moveToStart();
                p.setMonths(p.getMonths()-1);
            }
            else{
                System.out.println("ggwp");
            }
        }
    }
    
    @Override
    public String toString(){ return null; }
}
