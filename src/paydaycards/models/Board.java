package paydaycards.models;

import paydaycards.models.position.Position;
import java.util.ArrayList;
import java.util.Random;
import paydaycards.models.position.PayDayPosition;
import paydaycards.models.position.cardposition.BuyDealCardPosition;
import paydaycards.models.position.cardposition.MailCardPosition;
import paydaycards.models.position.cardposition.SellDealCardPosition;
import paydaycards.models.position.diceposition.OnePlayerDicePosition;
import paydaycards.models.position.diceposition.TwoPlayerDicePosition;
import static paydaycards.models.position.diceposition.TypeDicePosition.FamilyCasinoNight;
import static paydaycards.models.position.diceposition.TypeDicePosition.Lottery;
import static paydaycards.models.position.diceposition.TypeDicePosition.RadioContest;
import static paydaycards.models.position.diceposition.TypeDicePosition.Sweepstakes;
import static paydaycards.models.position.diceposition.TypeDicePosition.YardSale;

/**
 * its the board with that on it are the positions the jackpot and tha card piles
 */
public class Board {
    
    private ArrayList<Position> board;
    
    /**
     * this constructor creates a new arraylist of position
     * firstly adds a null position (for start)
     * then adds all the positions needed 
     * 8 mail card positions (couldnt do 4 single and 4 double so i just did 8 signles),
     * 5 buyer positions , 6 seller positions and then all the dice positions left
     * lastly adds the payday positions
     */
    public Board(){
        board = new ArrayList();
        board.add(null);
        for(int i = 0; i<8; i++){
            MailCardPosition mCard = new MailCardPosition();
            board.add(mCard);
            mCard.setBoard(this);
        }
        for(int i = 0; i<5; i++){
            BuyDealCardPosition buyer = new BuyDealCardPosition();
            board.add(buyer);
        }
        for(int i = 0; i<2; i++){
            OnePlayerDicePosition sweeps = new OnePlayerDicePosition(Sweepstakes);
            board.add(sweeps);
        }
        for(int i = 0; i < 3; i++){
            TwoPlayerDicePosition lottery = new TwoPlayerDicePosition(Lottery);
            board.add(lottery);
        }
        for(int i = 0; i <2; i++){
            TwoPlayerDicePosition radio = new TwoPlayerDicePosition(RadioContest);
            board.add(radio);
        }
        for(int i = 0; i <6; i++){
            SellDealCardPosition seller = new SellDealCardPosition();
            board.add(seller);
        }
        for(int i = 0; i<2;i++){
            OnePlayerDicePosition casino = new OnePlayerDicePosition(FamilyCasinoNight);
            board.add(casino);
        }
        for(int i = 0; i<2;i++){
            OnePlayerDicePosition yard = new OnePlayerDicePosition(YardSale);
            board.add(yard);
        }
        PayDayPosition payDay = new PayDayPosition();
        board.add(payDay);
    }
    
    public ArrayList<Position> getBoard(){ return board; }
    
    /**
     * a shuffle method to shuffle all the positions except the start and the payday
     */
    public void shuffle(){
        for(int i = 1; i<100; i++){
            Random rn = new Random();
            int j = rn.nextInt(29) + 1;
            int k = rn.nextInt(29) + 1;
            Position temp = board.get(j);
            board.set(j, board.get(k));
            board.set(k, temp);
        }
    }
    
    /**
     * a getter
     * @param index
     * @return 
     */
    public Position getPosition(int index) {
        return board.get(index);
    }
    
    @Override
    public String toString(){ return null; }
}
