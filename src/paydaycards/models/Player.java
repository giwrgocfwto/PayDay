package paydaycards.models;

import java.util.ArrayList;
import java.util.Random;
import paydaycards.models.cards.CardPile;
import paydaycards.models.cards.DealCard;
import paydaycards.models.cards.MailCard;

/**
 * its a player with a name , cash , bills . loans and some cards
 * also has some actions that involve players money(cash bills & loans)
 */
public class Player {
    
    private String name;
    private int cash;
    private int bills;
    private int loans;
    private ArrayList<MailCard> mcards;
    private ArrayList<DealCard> dcards;
    private int positionIndex;
    private int months;
    private int dieRoll;
    private int selectedNumber;
    private boolean accepted;
    private int bet;

    /**
     * creates a new Player object with a given name and cash
     * bills is set to 0 so is loans
     * creats two new cards arraylist that are the cards that each player holds
     * and position index is set to 0
     * PostCondition: a Player object
     * @param name
     */
    public Player(String name){
        this.name = name;
        cash = 2500;
        bills = 0;
        loans = 0;
        mcards = new ArrayList();
        dcards = new ArrayList();
        positionIndex = 0;
    }
    
    /**
     * all setters and getters for player
     * @return 
     */
    public int getBet() {
        return bet;
    }
    public void setBet(int bet) {
        this.bet = bet;
    }
    public boolean isAccepted() {
        return accepted;
    }
    public void setAccepted(boolean setAccepted) {
        this.accepted = setAccepted;
    }
    public int getSelectedNumber() {
        return selectedNumber;
    }
    public void setSelectedNumber(int selectedNumber) {
        this.selectedNumber = selectedNumber;
    }
    public int getDieRoll() {
        return dieRoll;
    }
    public void setDieRoll(int dieRoll) {
        this.dieRoll = dieRoll;
    }
    public void setName(String name){ this.name = name; }
    public String getName(){ return name; }
    public void setCash(int cash){ this.cash = cash; }
    public int getCash(){ return cash; }
    public void setBills(int bills){ this.bills = bills; }
    public int getBills(){ return bills; }
    public void setLoans(int loans){ this.loans = loans; }
    public int getLoans(){ return loans; }
    public ArrayList<MailCard> getMailCards(){ return mcards; }
    public ArrayList<DealCard> getDealCards(){ return dcards; }
    public void setMonths(int turns){ this.months = turns; }
    public int getMonths(){ return months; }
    public void setPosIndex(int positionIndex){ this.positionIndex = positionIndex; }
    public int getPosIndex(){ return positionIndex; }
    
    /**
     * Moves the player according to the number from the die.
     * @param dice
     */
    public void move(int dice) {
        positionIndex += dice;
        if (positionIndex > 30) 
            positionIndex=30;
    }
    
    /**
     * moves player to a certain position
     * @param index 
     */
    public void moveTo(int index){
        if(positionIndex < index){
            positionIndex = index;
            if (positionIndex > 30) 
                positionIndex=30;
        }
    }
    
    /**
     * 
     * @return a random number between 1 and 6
     */
    public int throwDie() {
        Random rn = new Random();
        dieRoll = rn.nextInt(6) + 1;
        return dieRoll;
    }
    
    /**
     * moves player to the beginning of the board
     */
    public void moveToStart(){
        positionIndex = 0;
    }
    
    /**
     * sets game model to the player
     */
    private GameModel gameModel;
    public void setGameModel(GameModel theModel) {
        gameModel = theModel;
    }
    
    /**
     * sets and gets an opponent to the player
     */
    private Player opponent;
    public void setOpponent(Player p) {
        opponent = p;
    }
    public Player getOpponent() {
        return opponent;
    }
    
    /**
     * sets and gets jackpot to the player
     */
    private Jackpot jackpot;
    public void setJackpot(Jackpot j){
        jackpot = j;
    }
    public Jackpot getJackpot(){
        return jackpot;
    }
    
    /**
     * setters and getters for cardpiles of the board
     */
    private CardPile mailCardPile;
    public void setMailCardPile(CardPile pile) { mailCardPile = pile; }
    public CardPile getMailCardPile() { return mailCardPile; }
    
    private CardPile dealCardPile;
    public void setDealCardPile(CardPile pile) { dealCardPile = pile; }
    public CardPile getDealCardPile() { return dealCardPile; }
    
    
    /**
     * adds more cash to the player whenever needed
     * @param cash 
     */
    public void addCash( int cash ){
        this.cash += cash;
    }
    
    /**
     * removes cash from the user
     * when he doesnt have enough to be removed then he gets a loan 
     * of 1000x (whatever needed)
     * @param cash 
     */
    public void removeCash( int cash ){
        if(this.cash > cash){
            this.cash -= cash;
        }
        else{
            while(this.cash < cash){
                getLoan();
            }
            this.cash -= cash;
        }
    }
    
    /**
     * adds 1000 to players loan and cash
     */
    public void getLoan(){
        cash += 1000;
        loans += 1000;
    }
    
    /**
     * gives player a mailcard that he needs to pay in the future
     * (adds it in the arrayList)
     * @param c 
     */
    public void addMailCard(MailCard c){
        mcards.add(c);
    }
    
    /**
     * gives player a deal card that he just bought
     * so that he can sell it in the future
     * (adds it in the arrayList)
     * @param c 
     */
    public void addDealCard(DealCard c){
        dcards.add(c);
    }
    
    /**
     * pays 10% of the loan the player has 
     * (removes it from the loan)
     * if he/she doesnt have enough cash then takes another loan
     */
    public void payLoans(){
        double per = loans * 0.1;
        if(cash > per){
            cash -= per;
            loans -= per;
        }
        else{
            while(cash < per){
                getLoan();
            }
            cash -= per;
            loans -= per;
        }
    }
    
    /**
     * pays bills the player has 
     * (makes them 0)
     */
    public void payBills(){
        if(cash > bills){
            cash -= bills;
            bills = 0;
        }
        else{
            while(cash < bills){
                getLoan();
            }
            cash -= bills;
            bills = 0;
        }
    }
    
    @Override
    public String toString(){ return name + cash + bills + loans + mcards + dcards; }
}
