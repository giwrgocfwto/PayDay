package paydaycards.controllers;

import paydaycards.models.GameModel;
import paydaycards.models.Player;
import paydaycards.models.cards.Card;
import paydaycards.models.cards.DealCard;
import paydaycards.models.cards.MailCard;
import static paydaycards.models.cards.Type.Bill;
import paydaycards.models.position.PayDayPosition;
import paydaycards.models.position.Position;
import paydaycards.models.position.SundayFootballMatchPosition;
import paydaycards.models.position.cardposition.BuyDealCardPosition;
import paydaycards.models.position.cardposition.DealCardPosition;
import paydaycards.models.position.cardposition.MailCardPosition;
import paydaycards.models.position.cardposition.SellDealCardPosition;
import paydaycards.models.position.diceposition.OnePlayerDicePosition;
import paydaycards.models.position.diceposition.TwoPlayerDicePosition;
import paydaycards.views.CardAction;
import paydaycards.views.ChoiceAction;
import paydaycards.views.GameView;
import paydaycards.views.GameAction;
import paydaycards.views.MoveAction;
import paydaycards.views.NumberSelectionAction;
import paydaycards.views.BetAction;
        
/**
 * GameController "controls" the game by connecting 
 * GameModel and GameView
 */
public class GameController {
    public ClassLoader cldr;
    private GameModel model;
    private GameView view;
    
    /**
     * Constructor
     * PostCondition: Constructs a new GameContoller with a given model and view
     * @param model
     * @param view 
     */
    public GameController(GameModel model , GameView view){
        this.model = model;
        this.view = view;
    }
    
    /**
     * PostCondition: a board created with all player and infobox labels as well as the jackpots ones
     * board positions are also created and 2 pawns on the beginning when this is over
     * and the 1st players rolls the die
     */
    public void startGame() {
        view.doFreshStart(model.getBoard());
        view.setCashLabel(model.getActivePlayer(), model.getActivePlayer().getCash());
        view.setCashLabel(model.getActivePlayer().getOpponent(), model.getActivePlayer().getOpponent().getCash());
        view.setBillsLabel(model.getActivePlayer(), model.getActivePlayer().getBills());
        view.setBillsLabel(model.getActivePlayer().getOpponent(), model.getActivePlayer().getOpponent().getBills());
        view.setLoansLabel(model.getActivePlayer(), model.getActivePlayer().getLoans());
        view.setLoansLabel(model.getActivePlayer().getOpponent(), model.getActivePlayer().getOpponent().getLoans());
        view.setJackpotLabel(model.getJackpot().getJackpot());
        view.setTurnLabel(model.getActivePlayer());
        view.setMonthsLabel(model.getMonths());
        view.setPlayerRolledDie(model.getActivePlayer(), new PlayerMoveDieAction(model.getActivePlayer()));
    }
    
    /**
     * getters for model and view
     * @return model / view
     */
    public GameModel getModel(){ return model; }
    public GameView getView(){ return view; }
    
    /**
     * moves pawn , also player on another position
     * (didnt use it after all)
     */
    public class PlayerMoveAction implements MoveAction {
        @Override
        public void performAction(Player p) {
            
        }
    }
    
    /**
     * class for card position actions
     */
    public class PlayerCardAction implements CardAction {
        private final Player p;
        private final Card card;
        PlayerCardAction(Player p , Card card){
            this.p = p;
            this.card = card;
        }
        /**
         * calls show mail/deal card tab from view
         * then the perform actions from the model with the actions that were given from view (if given)
         * then disables the card button
         */
        @Override
        public void performAction() {
            if(card instanceof MailCard){
                int i = view.showMailCard((MailCard)card);
                if(i == 0){
                    Position pos = model.getBoard().getPosition(p.getPosIndex());
                    pos.performAction(p);
                    if(p.getMailCards().get(p.getMailCards().size() - 1).getType() != Bill){
                        p.getMailCards().remove(p.getMailCards().size() - 1);
                    }
                    updateBoard();
                    gameStatus();
                }
                view.disableMailCard();
            }
            else if(card instanceof DealCard){
                int i = view.showDealCard((DealCard)card);
                if(i == 0){
                    p.setAccepted(true);
                    Position pos = model.getBoard().getPosition(p.getPosIndex());
                    p.addDealCard((DealCard)card);
                    pos.performAction(p);
                    updateBoard();
                    gameStatus();
                }
                else if(i == 1){
                    p.setAccepted(false);
                    p.getDealCardPile().discardCard(card);
                    updateBoard();
                    gameStatus();
                }
                view.disableDealCard();
            }
        }
      
        /**
         * this one checks when a turn has ended if the game has ended or not and 
         * if it switches the players properly each time and continues the dice rolls when needed
         * if a player has reached finish then only the 1 moves 
         * if game has ended prints who has won
         */
        public void gameStatus(){
            if (!(model.reachEnd(p) && model.reachEnd(p.getOpponent()))){
                model.switchPlayers();
                view.setPlayerRolledDie(model.getActivePlayer(), new PlayerMoveDieAction(model.getActivePlayer()));
            }
            else if(!(model.reachEnd(p)) && model.reachEnd(p.getOpponent())){
                view.setPlayerRolledDie(model.getActivePlayer(), new PlayerMoveDieAction(model.getActivePlayer()));
            }
            else if(model.reachEnd(p) && !(model.reachEnd(p.getOpponent()))){
                model.switchPlayers();
                view.setPlayerRolledDie(model.getActivePlayer(), new PlayerMoveDieAction(model.getActivePlayer()));
            }
            else if(model.reachEnd(p) && model.reachEnd(p.getOpponent())){
                System.out.println("GG WP! Player " + model.getWinner(p, p.getOpponent()).getName() + "won!!"); 
            }
            view.setTurnLabel(model.getActivePlayer());
        }
        
        /**
         * updates all the parameter labels on the board such as jackpot and each players' bills loans and cash
         */
        public void updateBoard(){
            view.setJackpotLabel(model.getJackpot().getJackpot());
            view.setBillsLabel(model.getActivePlayer(), model.getActivePlayer().getBills());
            view.setBillsLabel(model.getActivePlayer().getOpponent(), model.getActivePlayer().getOpponent().getBills());
            view.setLoansLabel(model.getActivePlayer(), model.getActivePlayer().getLoans());
            view.setLoansLabel(model.getActivePlayer().getOpponent(), model.getActivePlayer().getOpponent().getLoans());
            view.setCashLabel(model.getActivePlayer(), model.getActivePlayer().getCash());
            view.setCashLabel(model.getActivePlayer().getOpponent(), model.getActivePlayer().getOpponent().getCash());
        }
        
    }
    
    /**
     * connects the visual dice and the actions performed to roll the dice
     */
    public class PlayerRolledDieAction implements GameAction {
        private final Player p;
        private int dieRoll;
        public Player getPlayer() { return p; }
        public int getDieRoll() { return dieRoll; }
        PlayerRolledDieAction(Player p) {
            this.p = p;
        }
        /**
         * rolls the die and sets it on the dieRoll
         * then changes the die icon on view by setDieNumber
         * lastly updates the player position on the board depending on the dice rolled
         */
        @Override
        public void performAction() {
            dieRoll = p.throwDie();
            view.setDieNumber(p, dieRoll);
            view.updatePlayerPosition(p, dieRoll);
        }
        
        
    }
   
    /**
     * this one is for the player moves
     */
    public class PlayerMoveDieAction extends PlayerRolledDieAction {
        PlayerMoveDieAction(Player p) {
            super(p);
        }
        /**
         * after the die has been rolled and player has moved to new position then we create a new position 
         * and set it to be the one the position index of the player is on
         * then depending on the context of that position different actions are performed
         */
        @Override
        public void performAction() {
            super.performAction();
            getPlayer().move(getDieRoll());
            Position p = model.getBoard().getPosition(getPlayer().getPosIndex());
            // it disables all the buttons and draws a mail card and gives it to the player
            //then calls setCardAction
            if(p instanceof MailCardPosition){
                view.disableDieButton(getPlayer().getOpponent());
                view.disableDieButton(getPlayer());
                MailCard mcard;
                mcard = (MailCard)getPlayer().getMailCardPile().drawCard();
                getPlayer().addMailCard(mcard);
                view.setCardAction(mcard, new PlayerCardAction(getPlayer(),mcard));
            }
            //same as the above only that it doesnt add the card(its added later)
            else if(p instanceof BuyDealCardPosition){
                view.disableDieButton(getPlayer().getOpponent());
                view.disableDieButton(getPlayer());
                DealCard dcard;
                dcard = (DealCard)getPlayer().getDealCardPile().drawCard();
                view.setCardAction(dcard, new PlayerCardAction(getPlayer(),dcard));
            }
            //disables buttons 
            //calls the performAction of sell deal card position and 
            //calls updateBoard and gameStatus
            else if(p instanceof SellDealCardPosition){
                view.disableDieButton(getPlayer().getOpponent());
                view.disableDieButton(getPlayer());
                p.performAction(getPlayer());
                updateBoard();
                gameStatus();
            }
            //one and 2 player dice positions and payday work the same.
            //they perform the action , disable the player button 
            //and call gameStatus and updateBoard
            else if(p instanceof OnePlayerDicePosition){
                p.performAction(getPlayer());
                view.disableDieButton(getPlayer());
                updateBoard();
                gameStatus();
            }
            else if(p instanceof TwoPlayerDicePosition){
                p.performAction(getPlayer());
                view.disableDieButton(getPlayer());
                updateBoard();
                gameStatus();
            }
            else if(p instanceof PayDayPosition){
                p.performAction(getPlayer());
                view.disableDieButton(getPlayer());
                updateBoard();
                gameStatus();
            }
            //this is for the sunday match
            //it happens only if its sunday together with the action of the position
            //it creates a new bet , then calls the setBetAction from view
            //calls the requestBet window from view and the answer is set to the players' bet
            //then just performs the actions that are supposed to be performed on the model
            if((getPlayer().getPosIndex() % 7) == 0){
                BetAction bet = new PlayerBetAction(getPlayer());
                view.setBetAction(bet);
                model.getActivePlayer().setBet(view.requestBet());
                SundayFootballMatchPosition sp = new SundayFootballMatchPosition();
                sp.performAction(getPlayer());
            }
        }
        
        //these are the same as PlayerCardAction ones , i just used them twice
        public void gameStatus(){
            if((model.getActivePlayer().getPosIndex() == 31) && !(model.reachEnd(model.getActivePlayer()))){
                
                model.switchPlayers();
                view.setPlayerRolledDie(model.getActivePlayer(), new PlayerMoveDieAction(model.getActivePlayer()));
            }
            if (!(model.reachEnd(getPlayer()) && model.reachEnd(getPlayer().getOpponent()))){
                model.switchPlayers();
                view.setPlayerRolledDie(model.getActivePlayer(), new PlayerMoveDieAction(model.getActivePlayer()));
            }
            else if(!(model.reachEnd(getPlayer())) && model.reachEnd(getPlayer().getOpponent())){
                view.setPlayerRolledDie(model.getActivePlayer(), new PlayerMoveDieAction(model.getActivePlayer()));
            }
            else if(model.reachEnd(getPlayer()) && !(model.reachEnd(getPlayer().getOpponent()))){
                model.switchPlayers();
                view.setPlayerRolledDie(model.getActivePlayer(), new PlayerMoveDieAction(model.getActivePlayer()));
            }
            else if(model.reachEnd(getPlayer()) && model.reachEnd(getPlayer().getOpponent())){
                System.out.println("GG WP! Player " + model.getWinner(getPlayer(), getPlayer().getOpponent()).getName() + "won!!"); 
            }
            view.setTurnLabel(model.getActivePlayer());
        }
        
        //same with this
        public void updateBoard(){
            view.setJackpotLabel(model.getJackpot().getJackpot());
            view.setBillsLabel(model.getActivePlayer(), model.getActivePlayer().getBills());
            view.setBillsLabel(model.getActivePlayer().getOpponent(), model.getActivePlayer().getOpponent().getBills());
            view.setLoansLabel(model.getActivePlayer(), model.getActivePlayer().getLoans());
            view.setLoansLabel(model.getActivePlayer().getOpponent(), model.getActivePlayer().getOpponent().getLoans());
            view.setCashLabel(model.getActivePlayer(), model.getActivePlayer().getCash());
            view.setCashLabel(model.getActivePlayer().getOpponent(), model.getActivePlayer().getOpponent().getCash());
        }
    }
    
    public class PlayerSelectNumberAction implements NumberSelectionAction {
        
        private Player p;
        
        PlayerSelectNumberAction(Player p) {
            this.p = p;
        }

        @Override
        public void performAction(int numberSelected) {
            p.setSelectedNumber(numberSelected);
        }   
    }
    
    public class PlayerBetAction implements BetAction {
        Player p;
        
        public PlayerBetAction(Player p){
            this.p = p;
        }
        
        @Override
        public void performAction(int bet) {
            p.setBet(bet);
        }
        
    }
    
    /**
     * this one is for dealcard that you have to make a choice between 2 answers
     * PreCondition: that an answer between accepted or not is given from view
     * PostCondition: that number is given to the model 
     */
    public class PlayerAcceptedAction implements ChoiceAction {
        private Player p;
        
        PlayerAcceptedAction(Player p){
            this.p = p;
        }
        @Override
        public void performAction(boolean accepted) {
            p.setAccepted(accepted);
        }
    }
    
    /**
     * if view.getDealCardAnswer is true then proceeds to call BuyDealCardPosition 
     * @param d 
     */
    public void DealActionPerformed(DealCardPosition d){
        
    }
}
