package paydaycards.models.position.cardposition;

import paydaycards.models.Board;
import paydaycards.models.Player;
import static paydaycards.models.cards.Type.Charity;
import static paydaycards.models.cards.Type.MadMoney;
import static paydaycards.models.cards.Type.PayTheNeighbor;

public class MailCardPosition extends CardPosition{   
    private Board board;
    public MailCardPosition(){
        
    }
    
    /**
     * setters and getters for board
     * @param board 
     */
    public void setBoard(Board board){ this.board = board; }
    public Board getBoard(){ return board; }
    
    /**
     * draws a card from the MailCard pile 
     * then with a switch case performs some certain actions depending on the type
     * if type is pay the neighbor then it removes the amount from the player and adds it to the other player's cash
     * if the type is madmoney then removes the amount from the opponents and adds it to player's cash
     * if type is charity then removes cash from the player and ads it to jackpot
     * if type is bill then adds the card to players mailcards
     * if type is movetobuyer moves player to the next dealposition
     * if the card is advertisement then nothing happens
     * calls showMailCardTab too
     * @param p 
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void performAction(Player p) { 
        p.addMailCard(p.getMailCards().get(p.getMailCards().size()-1));
        switch (p.getMailCards().get(p.getMailCards().size()-1).getType()) {
            case PayTheNeighbor:
                if(p.getMailCards().get(p.getMailCards().size()-1).getEuro() < p.getCash()){
                    p.removeCash(p.getMailCards().get(p.getMailCards().size()-1).getEuro());
                    p.getOpponent().addCash(p.getMailCards().get(p.getMailCards().size()-1).getEuro());
                }
                else{
                    while(p.getMailCards().get(p.getMailCards().size()-1).getEuro() > p.getCash()){
                        p.getLoan();
                    }
                    p.removeCash(p.getMailCards().get(p.getMailCards().size()-1).getEuro());
                    p.getOpponent().addCash(p.getMailCards().get(p.getMailCards().size()-1).getEuro());
                }  
                break;
            case MadMoney:
                if(p.getMailCards().get(p.getMailCards().size()-1).getEuro() < p.getOpponent().getCash()){
                    p.getOpponent().removeCash(p.getMailCards().get(p.getMailCards().size()-1).getEuro());
                    p.addCash(p.getMailCards().get(p.getMailCards().size()-1).getEuro());
                }
                else{
                    while(p.getMailCards().get(p.getMailCards().size()-1).getEuro() > p.getOpponent().getCash()){
                        p.getOpponent().getLoan();
                    }
                    p.getOpponent().removeCash(p.getMailCards().get(p.getMailCards().size()-1).getEuro());
                    p.addCash(p.getMailCards().get(p.getMailCards().size()-1).getEuro());
                }  
                break;
            case Charity:
                if(p.getMailCards().get(p.getMailCards().size()-1).getEuro() < p.getCash()){
                    p.removeCash(p.getMailCards().get(p.getMailCards().size()-1).getEuro());
                    p.getJackpot().addJackpot(p.getMailCards().get(p.getMailCards().size()-1).getEuro());
                }
                else{
                    while(p.getMailCards().get(p.getMailCards().size()-1).getEuro() > p.getCash()){
                        p.getLoan();
                    }
                    p.removeCash(p.getMailCards().get(p.getMailCards().size()-1).getEuro());
                    p.getJackpot().addJackpot(p.getMailCards().get(p.getMailCards().size()-1).getEuro());
                }
                break;
            case Bill:
                p.setBills(p.getBills() + p.getMailCards().get(p.getMailCards().size()-1).getEuro());
            case MoveToDealBuyer:
                for(int i = p.getPosIndex(); i <= 30; i++){
                    p.setPosIndex(i);
                    if(board.getPosition(i) instanceof BuyDealCardPosition){
                        //p.moveTo(i);
                        break;    
                    }
                }
                if(p.getPosIndex() == 30){
                    //p.moveTo(p.getPosIndex());
                }
            case Αdvertisement:
                //do nothing
        }
    }
    
    @Override
    public String toString(){ return null; }
}
