package paydaycards.models;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import paydaycards.models.cards.CardPile;
import paydaycards.models.cards.DealCard;
import paydaycards.models.cards.MailCard;
import paydaycards.models.cards.Type;
import paydaycards.models.position.Position;

/**
 * Its the game model
 * everything is created here (kind of lel)
 */
public final class GameModel {
    private Player[] players;
    private int activePlayerIndex;
    private Board board;
    private int months;
    private boolean endTurn;
    private Jackpot jackpot;
    private CardPile mailCardPile;
    private CardPile dealCardPile;
    
    /**
     * calls restart
     * @throws IOException 
     */
    public GameModel() throws IOException{
        restart();
    }
    
    /**
     * Here 2 Player objects will be created with a certain name.
     * the first player is picked
     * Also creates a new Board object and shuffles it
     * Int months will be an int that it will randomly be either 1 , 2 or 3. 
     * and then sets those to the players
     * endTurn will be set as false .
     * creates new jackpot
     * creates mail/deal card piles , loads inside all cards then shuffles them.
     * lastly sets to each player an opponent , the jackpot and the cardpiles
     * PostCondition: all necessary objects have been created successfully; no failure while reading the cards data files.
     * @throws java.io.IOException
     */
    public void restart() throws IOException {
        players = new Player[] { 
            new Player("player1"), 
            new Player("player2")
        };
        pickFirstPlayer();
        board = new Board(); 
        board.shuffle();
        Random rn = new Random();
        months = rn.nextInt(3) + 1;
        players[0].setMonths(months);
        players[1].setMonths(months);
        endTurn = false;
        jackpot = new Jackpot();
        mailCardPile = new CardPile();
        loadMailCards();
        mailCardPile.shuffle();
        dealCardPile = new CardPile();
        loadDealCards();
        dealCardPile.shuffle();
        players[0].setOpponent(players[1]);
        players[1].setOpponent(players[0]);
        players[0].setJackpot(jackpot);
        players[1].setJackpot(jackpot);
        players[0].setDealCardPile(dealCardPile);
        players[0].setMailCardPile(mailCardPile);
        players[1].setDealCardPile(dealCardPile);
        players[1].setMailCardPile(mailCardPile);
    }

    /**
     * all needed setters and getters
     * @return 
     */
    public Player getPlayer1(){ return players[0]; }
    public Player getPlayer2(){ return players[1]; }
    public Board getBoard(){ return board; }
    public int getMonths(){ return months; }
    public boolean getEndTurn(){ return endTurn; }
    public CardPile getMailCards(){ return mailCardPile; }
    public CardPile getDealCards(){ return dealCardPile; }
    public Jackpot getJackpot() {
        return jackpot;
    }
    public void setJackpot(Jackpot jackpot) {
        this.jackpot = jackpot;
    }
    
    
    
    /**
     * rolls 2 dices (1 for each player)
     * and if first player has a higher roll then returns true
     * else false
     * edit. i just change the active player index after all , i just kept it being a boolean , it doesnt affect anything
     * @return true or false
     */
    public boolean pickFirstPlayer(){
        int i = 0, j = 0;
        while(i == j){
            i = rollDice();
            j = rollDice();
        }
        activePlayerIndex = i > j ? 0 : 1;
        return i > j;
    }
    
    /**
     * picks one of the 6 numbers contained in the dice array
     * if it is 6 , gives evrything contained in jackpote to the one who rolled the dice
     * @return a number from 1 to 6
     */
    public int rollDice(){
        Random rn = new Random();
        int d = rn.nextInt(6) + 1;
        //jackpot.giveJackpot(players[activePlayerIndex]);
        return d;
    }
    
    /**
     * after the dice is rolled the player goes to a certain position on the board
     * then calls perform action
     * @param dice
     */
    public void move(int dice){
        Player p = getActivePlayer();
        Position pos = board.getPosition(p.getPosIndex());
        pos.performAction(p);
    }
    
    /**
     * rolls dice 
     * moves player 
     * and then switches players
     */
    public void turn(){
        int d = rollDice();
        move(d);
        switchPlayers();
    }
    
    /**
     * switches the active player by switching the index number
     */
    public void switchPlayers() {
        activePlayerIndex = 1 - activePlayerIndex;
    }
    
    /**
     * @return a player
     */
    public Player getActivePlayer() {
        return players[activePlayerIndex];
    }
    
    /**
     * @return a players opponent
     */
    public Player getCurrentOpponent() { 
        return players[1-activePlayerIndex];
    }
    
    /**
     * checks if player has reached finish line
     * @param p
     * @return true or false
     */
    public boolean reachEnd(Player p){
        return p.getMonths() == 0;// && p.getPosIndex() == 30;
    }
    
    /**
     * after both of them have reached end then it sees who has won
     * @param p1
     * @param p2
     * @return 
     */
    public Player getWinner(Player p1, Player p2){
        if(gameEnded(p1,p2)){
            if(p1.getCash() > p2.getCash()){
                return p1;
            }
            else{
                return p2;
            }
        }
        return null;
    }
    
    /**
     * checks if both players have reached end , then if they do then game ended returns true
     * @param p1
     * @param p2
     * @return 
     */
    public boolean gameEnded(Player p1, Player p2){
        return reachEnd(p1) && reachEnd(p2);
    }

    /**
     * loads mail cards ( i copied the code used on the PayDayCards.java file given , just change it a bit to fit my program)
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void loadMailCards() throws FileNotFoundException, IOException {
        ClassLoader cldr = this.getClass().getClassLoader();
        URL fileURL = cldr.getResource("resources/mailCards.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(fileURL.getFile()))
        ) {
            String line;
            br.readLine(); // skip headers!
            while ((line = br.readLine())!= null) {
                String[] mailCard = line.split(",");
                MailCard mc;
                Type type = Type.valueOf(mailCard[1]);
                int euro = Integer.parseInt(mailCard[4]);
                URL imageURL = cldr.getResource("resources/images/" + mailCard[5]); //image
		Image image = new ImageIcon(imageURL).getImage();
		image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                mc = new MailCard(type,mailCard[2],mailCard[3],euro,image);
                mailCardPile.addCard(mc);
            }
            br.close();
        }
    }
    
    /**
     * same with the above but deal cards
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void loadDealCards() throws FileNotFoundException, IOException {
        ClassLoader cldr = this.getClass().getClassLoader();
        URL fileURL = cldr.getResource("resources/dealCards.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(fileURL.getFile())) //??
        ) {
            String line;
            br.readLine();
            while ((line = br.readLine())!= null) {
                String[] dealCard = line.split(",");
                DealCard dc;
                int cost = Integer.parseInt(dealCard[3]);
                int value = Integer.parseInt(dealCard[4]);
                URL imageURL = cldr.getResource("resources/images/" + dealCard[5]); //image
		Image image = new ImageIcon(imageURL).getImage();
		image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                Type type = Type.valueOf(dealCard[1]);
                dc = new DealCard(dealCard[2],cost,value,image,dealCard[6],dealCard[7]);
                dealCardPile.addCard(dc);
            }
            br.close();
        }
    }
    
}
