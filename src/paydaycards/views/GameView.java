/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paydaycards.views;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import paydaycards.models.Board;
import paydaycards.models.Player;
import paydaycards.models.cards.Card;
import paydaycards.models.cards.DealCard;
import paydaycards.models.cards.MailCard;
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
 * a class for a position holder for some panels and labels
 */
class PositionHolder {
    public JPanel panel;
    public JLabel dayLabel;
    public JLabel iconLabel;
    /**
     * constructor
     * @param panel
     * @param dayLabel
     * @param iconLabel 
     */
    public PositionHolder(JPanel panel, JLabel dayLabel, JLabel iconLabel) {
        this.panel = panel;
        this.dayLabel = dayLabel;
        this.iconLabel = iconLabel;
    }
}

/**
 * class for game view
 */
public class GameView extends javax.swing.JFrame {
    
    private ClassLoader cldr;
    private PositionHolder[] holders;
    /**
     * adds all compontents in position holder on a holders array
     */
    public GameView() {
        initComponents();
        holders = new PositionHolder[] {
            new PositionHolder(startPanel, StartLabel, Start),
            new PositionHolder(Monday1Panel, Monday1Label, Monday1),
            new PositionHolder(Tuesday2Panel, Tuesday2Label, Tuesday2),
            new PositionHolder(Wednesday3Panel, Wednesday3Label, Wednesday3),
            new PositionHolder(Thursday4Panel, Thursday4Label, Thursday4),
            new PositionHolder(Friday5Panel, Friday5Label, Friday5),
            new PositionHolder(Saturday6Panel, Saturday6Label, Saturday6),
            new PositionHolder(Sunday7Panel, Sunday7Label, Sunday7),
            new PositionHolder(Monday8Panel, Monday8Label, Monday8),
            new PositionHolder(Tuesday9Panel, Tuesday9Label, Tuesday9),
            new PositionHolder(Wednesday10Panel, Wednesday10Label, Wednesday10),
            new PositionHolder(Thursday11Panel, Thursday11Label, Thursday11),
            new PositionHolder(Friday12Panel, Friday12Label, Friday12),
            new PositionHolder(Saturday13Panel, Saturday13Label, Saturday13),
            new PositionHolder(Sunday14Panel, Sunday14Label, Sunday14),
            new PositionHolder(Monday15Panel, Monday15Label, Monday15),
            new PositionHolder(Tuesday16Panel, Tuesday16Label, Tuesday16),
            new PositionHolder(Wednesday17Panel, Wednesday17Label, Wednesday17),
            new PositionHolder(Thursday18Panel,Thursday18Label, Thursday18),
            new PositionHolder(Friday19Panel, Friday19Label, Friday19),
            new PositionHolder(Saturday20Panel, Saturday20Label, Saturday20),
            new PositionHolder(Sunday21Panel, Sunday21Label, Sunday21),
            new PositionHolder(Monday22Panel, Monday22Label, Monday22),
            new PositionHolder(Tuesday23Panel, Tuesday23Label, Tuesday23),
            new PositionHolder(Wednesday24Panel, Wednesday24Label, Wednesday24),
            new PositionHolder(Thursday25Panel, Thursday25Label, Thursday25),
            new PositionHolder(Friday26Panel, Friday26Label, Friday26),
            new PositionHolder(Saturday27Panel, Saturday27Label, Saturday27),
            new PositionHolder(Sunday28Panel, Sunday28Label, Sunday28),
            new PositionHolder(Monday29Panel, Monday29Label, Monday29),
            new PositionHolder(Tuesday30Panel, Tuesday30Label, Tuesday30),
            new PositionHolder(PayDayPanel, Wednesday31Label, PayDayPos)
        };    
    }
    
    /**
     * adds all positions on the board (except start and payday that are already started)
     * and then the pawns
     * @param b 
     */
    public void doFreshStart(Board b) {
        for(int i = 1; i <= 30; i++){
            if(b.getPosition(i) instanceof MailCardPosition){
                holders[i].iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/mc1.png")));
            }
            else if(b.getPosition(i) instanceof BuyDealCardPosition){
                holders[i].iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/deal.png")));
            }
            else if(b.getPosition(i) instanceof OnePlayerDicePosition){
                OnePlayerDicePosition p = (OnePlayerDicePosition) b.getPosition(i);
                if (null != p.getType())switch (p.getType()) {
                    case Sweepstakes:
                        holders[i].iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/sweep.png")));
                        break;
                    case FamilyCasinoNight:
                        holders[i].iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/casino.png")));
                        break;
                    case YardSale:
                        holders[i].iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/yard.png")));
                        break;
                    default:
                        break;
                }
            }
            else if(b.getPosition(i) instanceof TwoPlayerDicePosition){
                TwoPlayerDicePosition p = (TwoPlayerDicePosition) b.getPosition(i);
                if(p.getType() == Lottery){
                    holders[i].iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/lottery.png")));
                }
                else if(p.getType() == RadioContest){
                    holders[i].iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/radio.png")));
                }
            }
            else if(b.getPosition(i) instanceof SellDealCardPosition){
                holders[i].iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/buyer.png")));
            }
        }
        tsoxaPanel.setLayout(null);
        cldr = this.getClass().getClassLoader();
        URL url2 = cldr.getResource("paydaycards/views/images/pawn_blue.png");
        Image img2 = new ImageIcon(url2).getImage();
        img2 = img2.getScaledInstance(pawn1.getWidth(), pawn1.getHeight(), java.awt.Image.SCALE_SMOOTH);
        ImageIcon pawn2icon = new ImageIcon(img2);
        pawn1.setIcon(pawn2icon);
        pawn1.setVisible(true);
        pawn1.setLocation(
                holders[0].panel.getLocation().x + holders[0].iconLabel.getLocation().x,
                holders[0].panel.getLocation().y + holders[0].iconLabel.getLocation().y);
        tsoxaPanel.setComponentZOrder(pawn1, 1);
        
        URL url1 = cldr.getResource("paydaycards/views/images/pawn_yellow.png");
        Image img1 = new ImageIcon(url1).getImage();
        img1 = img1.getScaledInstance(pawn2.getWidth(), pawn2.getHeight(), java.awt.Image.SCALE_SMOOTH);
        ImageIcon pawn1icon = new ImageIcon(img1);
        pawn2.setIcon(pawn1icon);
        pawn2.setVisible(true);
        pawn2.setLocation(
                holders[0].panel.getLocation().x + holders[0].iconLabel.getLocation().x,
                holders[0].panel.getLocation().y + holders[0].iconLabel.getLocation().y);
        tsoxaPanel.setComponentZOrder(pawn2, 1);
         //TODO: clear messages
    }
    
    /**
     * sets the icon of each players dice depending on the die rolled
     * @param p
     * @param dieRoll 
     */
    public void setDieNumber(Player p, int dieRoll) {
        if("player1".equals(p.getName())){
            switch (dieRoll) {
                case 1:
                    Dice1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/dice-1.jpg")));
                    break;
                case 2:
                    Dice1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/dice-2.jpg")));
                    break;
                case 3:
                    Dice1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/dice-3.jpg")));
                    break;
                case 4:
                    Dice1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/dice-4.jpg")));
                    break;
                case 5:
                    Dice1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/dice-5.jpg")));
                    break;
                case 6:
                    Dice1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/dice-6.jpg")));
                    break;
                default:
                    break;
            }
        }
        else if("player2".equals(p.getName())){
            switch (dieRoll) {
                case 1:
                    Dice2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/dice-1.jpg")));
                    break;
                case 2:
                    Dice2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/dice-2.jpg")));
                    break;
                case 3:
                    Dice2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/dice-3.jpg")));
                    break;
                case 4:
                    Dice2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/dice-4.jpg")));
                    break;
                case 5:
                    Dice2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/dice-5.jpg")));
                    break;
                case 6:
                    Dice2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/dice-6.jpg")));
                    break;
                default:
                    break;
            }
        }
    }
    
    /**
     * updates player position (thw pawns's psoition) on the board
     * @param p
     * @param die 
     */
    public void updatePlayerPosition(Player p, int die) {
        if("player1".equals(p.getName())){
            int pos = p.getPosIndex();
            if(pos + die < 32){
                pawn1.setLocation(
                    holders[pos + die].panel.getLocation().x + holders[pos + die].iconLabel.getLocation().x,
                    holders[pos + die].panel.getLocation().y + holders[pos + die].iconLabel.getLocation().y);
                tsoxaPanel.setComponentZOrder(pawn1, 1);
            }
            else{
                pawn1.setLocation(
                    holders[31].panel.getLocation().x + holders[31].iconLabel.getLocation().x,
                    holders[31].panel.getLocation().y + holders[31].iconLabel.getLocation().y);
                tsoxaPanel.setComponentZOrder(pawn1, 1);
            }
        }
        else if("player2".equals(p.getName())){
            int pos = p.getPosIndex();
            if(pos + die < 32){
                pawn2.setLocation(
                    holders[pos + die].panel.getLocation().x + holders[pos + die].iconLabel.getLocation().x,
                    holders[pos + die].panel.getLocation().y + holders[pos + die].iconLabel.getLocation().y);
                tsoxaPanel.setComponentZOrder(pawn2, 1);
            }
            else{
                pawn2.setLocation(
                    holders[31].panel.getLocation().x + holders[31].iconLabel.getLocation().x,
                    holders[31].panel.getLocation().y + holders[31].iconLabel.getLocation().y);
                tsoxaPanel.setComponentZOrder(pawn2, 1);
            }
        }
    }
    
    
    /**
     * sets action to playerMoveAction
     */
    private MoveAction playerMoveAction;
    public void setPlayerMove(MoveAction action) {
        playerMoveAction = action;
    }
    
    /**
     * sets action to numberSelectionAction
     */
    private NumberSelectionAction numberSelectionAction;
    public void setNumberSelectionAction(NumberSelectionAction action) {
        numberSelectionAction = action;
    }
    
    /**
     * unused after all i think
     */
    public void requestNumber() {
        String[] buttons = {"1", "2", "3", "4", "5", "6"};
        int response = JOptionPane.showOptionDialog(
                this,
                "Please select a number between 1 and 6 using the buttons bellow:",
                "Select a number",
                JOptionPane.DEFAULT_OPTION,
                0,
                null,
                buttons,
                buttons[0]
        );
        numberSelectionAction.performAction(response + 1);
        System.out.println(response + 1);
    }
    
    /**
     * sets action to playerAcceptedAction
     */
    private ChoiceAction playerAcceptedAction;
    public void setPlayerAccepted(ChoiceAction action) {
        playerAcceptedAction = action;
    }
    
    /**
     * unused after all
     */
    public void selectDealCard(){
//        String[] buttons = { "Buy", "Don't Buy"};
//        Image image = getcard().getIcon();
//        int rc = JOptionPane.showOptionDialog(null, "Bet on Barca-Real derby", "Sunday Football Match",
//            JOptionPane.WARNING_MESSAGE, 0, new ImageIcon(image), buttons, buttons[0]);
    }
    
    /**
     * sets action to betAction
     */
    private BetAction betAction;
    public void setBetAction(BetAction action){
        betAction = action;
    }
    
    /**
     * the window of the sunday footbal match
     * @return 
     */
    public int requestBet(){
        String[] buttons = { "Barcelona Wins", "Tie", "Real Wins", "Don't want to bet!" };
        URL imageURL = cldr.getResource("paydaycards/views/Images/Barcelona_Real.jpg"); //image
        Image image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        int rc = JOptionPane.showOptionDialog(null, "Bet on Barca-Real derby", "Sunday Football Match",
            JOptionPane.WARNING_MESSAGE, 0, new ImageIcon(image), buttons, buttons[3]);
        int i = 0;
        switch (rc) {
            case 0:
                i = 1;
                break;
            case 1:
                i = 3;
                break;
            case 2:
                i = 5;
                break;
            case 3:
                i = 0;
                break;
            default:
                break;
        }
        if (betAction != null) {
            betAction.performAction(i);
            betAction = null;
        } else {
            JOptionPane.showMessageDialog(this, "You cannot place a bet at this time");
        }
        return i;
    }
    
    /**
     * sets action to playerCardAction
     * also enables the card panel needed each time and changes the order label
     */
    private CardAction playerCardAction;
    public void setCardAction(Card card,CardAction action){
        if(card instanceof MailCard){
            MailCards.setEnabled(rootPaneCheckingEnabled);
            Order.setText("Please draw a Mail Card!!");
        }
        else if(card instanceof DealCard){
            DealCards.setEnabled(rootPaneCheckingEnabled);
            Order.setText("Please draw a Deal Card!!");
        }
        playerCardAction = action;
    }
    
    /**
     * the window of the drawn mail card (copied too from the PayDayCard.java , changed some stuff too)
     * @param card
     * @return 
     */
    public int showMailCard(MailCard card){
        Object[] options = {card.getChoice()};
		Image image = card.getImage();
		image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		JOptionPane p = new JOptionPane();
		int n = JOptionPane.showOptionDialog(this,
				card.getMessage(),
				card.getType().toString(),
				JOptionPane.OK_OPTION,
				0,
				new ImageIcon(image),
				options,
				options[0]);
        return n;
    }
    
    /**
     * same with showMailCard
     * @param card
     * @return 
     */
    public int showDealCard(DealCard card){
        Object[] options = {card.getChoice1(), card.getChoice2()};
		Image image = card.getImage();
		image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		JOptionPane p = new JOptionPane();
		int n = JOptionPane.showOptionDialog(this,
				card.getMessage() + "\nΤιμή Αγοράς: " + card.getCost() + " Ευρώ \nΤιμή Πώλησης: " + card.getValue() + " Ευρώ \n",
				card.getType().Deal.toString(),
				JOptionPane.OK_OPTION,
				0,
				new ImageIcon(image),
				options,
				options[0]);
        return n;
    }
    
    /**
     * sets action to playerRolledDieAction
     * also enables the throw die button for each player to click and changes the order label
     */
    private GameAction playerRolledDieAction;
    public void setPlayerRolledDie(Player player, GameAction action) {
        if("player1".equals(player.getName())){
            RollDice1.setEnabled(rootPaneCheckingEnabled);
            Order.setText("Player 1 roll the die!!");
        }
        else if("player2".equals(player.getName())){
            RollDice2.setEnabled(rootPaneCheckingEnabled);
            Order.setText("Player 2 roll the die!!");
        }
        playerRolledDieAction = action;
    }
    
    /**
     * disables the die button when needed
     * @param p 
     */
    public void disableDieButton(Player p){
        if("player1".equals(p.getName())){
            RollDice1.setEnabled(false);
        }
        else if("player2".equals(p.getName())){
            RollDice2.setEnabled(false);
        }
    }
    
    /**
     * disables the mail card button when needed
     */
    public void disableMailCard(){
        MailCards.setEnabled(false);
    }
    
    /**
     * same
     */
    public void disableDealCard(){
        DealCards.setEnabled(false);
    }
    
    /**
     * sets cash label
     * @param p
     * @param i 
     */
    public void setCashLabel(Player p, int i){
        if("player1".equals(p.getName())){
            Money1.setText("Money: "+i+" Euro");
        }
        else if("player2".equals(p.getName())){
            Money2.setText("Money: "+i+" Euro");
        }
    }
    
    /**
     * sets jackpot label
     * @param i 
     */
    public void setJackpotLabel(int i){
        JackPotEuro.setText("Jackpot: "+ i+" Euro");
    }
    
    /**
     * sets turn label
     * @param p 
     */
    public void setTurnLabel(Player p){
        if("player1".equals(p.getName())){
            Turn.setText("Player 1's Turn");
        }
        else if("player2".equals(p.getName())){
            Turn.setText("Player 2's Turn");
        }
    }
    
    /**
     * sets month label
     * @param i 
     */
    public void setMonthsLabel(int i){
        Months.setText("Months Left: "+i);
    }
    
    /**
     * sets bills label
     * @param p
     * @param i 
     */
    public void setBillsLabel(Player p,int i){
        if("player1".equals(p.getName())){
            Bills1.setText("Bills: "+i+" Euro");
        }
        else if("player2".equals(p.getName())){
            Bills2.setText("Bills: "+i+" Euro");
        }
    }
    
    /**
     * sets loans label
     * @param p
     * @param i 
     */
    public void setLoansLabel(Player p, int i){
        if("player1".equals(p.getName())){
            Loan1.setText("Loans: "+i+" Euro");
        }
        else if("player2".equals(p.getName())){
            Loan2.setText("Loans: "+i+" Euro");
        }
    }
    
    private void RollDiceActionPerformed(java.awt.event.ActionEvent evt) {                                          
        if (playerRolledDieAction != null)
            playerRolledDieAction.performAction();
        playerRolledDieAction = null;
    }

    
    /**
     * everything from down here is created in the design 
     */
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tsoxaPanel = new javax.swing.JPanel();
        pawn1 = new javax.swing.JLabel();
        pawn2 = new javax.swing.JLabel();
        startPanel = new javax.swing.JPanel();
        StartLabel = new javax.swing.JLabel();
        Start = new javax.swing.JLabel();
        Tuesday2Panel = new javax.swing.JPanel();
        Tuesday2Label = new javax.swing.JLabel();
        Tuesday2 = new javax.swing.JLabel();
        Wednesday3Panel = new javax.swing.JPanel();
        Wednesday3Label = new javax.swing.JLabel();
        Wednesday3 = new javax.swing.JLabel();
        Thursday4Panel = new javax.swing.JPanel();
        Thursday4Label = new javax.swing.JLabel();
        Thursday4 = new javax.swing.JLabel();
        Friday5Panel = new javax.swing.JPanel();
        Friday5Label = new javax.swing.JLabel();
        Friday5 = new javax.swing.JLabel();
        Saturday6Panel = new javax.swing.JPanel();
        Saturday6Label = new javax.swing.JLabel();
        Saturday6 = new javax.swing.JLabel();
        Monday1Panel = new javax.swing.JPanel();
        Monday1Label = new javax.swing.JLabel();
        Monday1 = new javax.swing.JLabel();
        Sunday7Panel = new javax.swing.JPanel();
        Sunday7Label = new javax.swing.JLabel();
        Sunday7 = new javax.swing.JLabel();
        Sunday21Panel = new javax.swing.JPanel();
        Sunday21Label = new javax.swing.JLabel();
        Sunday21 = new javax.swing.JLabel();
        Sunday28Panel = new javax.swing.JPanel();
        Sunday28Label = new javax.swing.JLabel();
        Sunday28 = new javax.swing.JLabel();
        Sunday14Panel = new javax.swing.JPanel();
        Sunday14Label = new javax.swing.JLabel();
        Sunday14 = new javax.swing.JLabel();
        Monday8Panel = new javax.swing.JPanel();
        Monday8Label = new javax.swing.JLabel();
        Monday8 = new javax.swing.JLabel();
        Tuesday9Panel = new javax.swing.JPanel();
        Tuesday9Label = new javax.swing.JLabel();
        Tuesday9 = new javax.swing.JLabel();
        Saturday20Panel = new javax.swing.JPanel();
        Saturday20Label = new javax.swing.JLabel();
        Saturday20 = new javax.swing.JLabel();
        Thursday11Panel = new javax.swing.JPanel();
        Thursday11Label = new javax.swing.JLabel();
        Thursday11 = new javax.swing.JLabel();
        Friday12Panel = new javax.swing.JPanel();
        Friday12Label = new javax.swing.JLabel();
        Friday12 = new javax.swing.JLabel();
        Saturday13Panel = new javax.swing.JPanel();
        Saturday13Label = new javax.swing.JLabel();
        Saturday13 = new javax.swing.JLabel();
        Monday15Panel = new javax.swing.JPanel();
        Monday15Label = new javax.swing.JLabel();
        Monday15 = new javax.swing.JLabel();
        Monday22Panel = new javax.swing.JPanel();
        Monday22Label = new javax.swing.JLabel();
        Monday22 = new javax.swing.JLabel();
        Monday29Panel = new javax.swing.JPanel();
        Monday29Label = new javax.swing.JLabel();
        Monday29 = new javax.swing.JLabel();
        Tuesday16Panel = new javax.swing.JPanel();
        Tuesday16Label = new javax.swing.JLabel();
        Tuesday16 = new javax.swing.JLabel();
        Tuesday23Panel = new javax.swing.JPanel();
        Tuesday23Label = new javax.swing.JLabel();
        Tuesday23 = new javax.swing.JLabel();
        Tuesday30Panel = new javax.swing.JPanel();
        Tuesday30Label = new javax.swing.JLabel();
        Tuesday30 = new javax.swing.JLabel();
        Wednesday17Panel = new javax.swing.JPanel();
        Wednesday17Label = new javax.swing.JLabel();
        Wednesday17 = new javax.swing.JLabel();
        Wednesday24Panel = new javax.swing.JPanel();
        Wednesday24Label = new javax.swing.JLabel();
        Wednesday24 = new javax.swing.JLabel();
        PayDayPanel = new javax.swing.JPanel();
        Wednesday31Label = new javax.swing.JLabel();
        PayDayPos = new javax.swing.JLabel();
        Thursday18Panel = new javax.swing.JPanel();
        Thursday18Label = new javax.swing.JLabel();
        Thursday18 = new javax.swing.JLabel();
        Thursday25Panel = new javax.swing.JPanel();
        Thursday25Label = new javax.swing.JLabel();
        Thursday25 = new javax.swing.JLabel();
        Friday19Panel = new javax.swing.JPanel();
        Friday19Label = new javax.swing.JLabel();
        Friday19 = new javax.swing.JLabel();
        Wednesday10Panel = new javax.swing.JPanel();
        Wednesday10Label = new javax.swing.JLabel();
        Wednesday10 = new javax.swing.JLabel();
        Saturday27Panel = new javax.swing.JPanel();
        Saturday27Label = new javax.swing.JLabel();
        Saturday27 = new javax.swing.JLabel();
        Friday26Panel = new javax.swing.JPanel();
        Friday26Label = new javax.swing.JLabel();
        Friday26 = new javax.swing.JLabel();
        MainLabel = new javax.swing.JLabel();
        Jackpot = new javax.swing.JLabel();
        JackPotEuro = new javax.swing.JLabel();
        controlPanel = new javax.swing.JPanel();
        firstPlayerPanel = new javax.swing.JPanel();
        Player1 = new javax.swing.JLabel();
        Money1 = new javax.swing.JLabel();
        Loan1 = new javax.swing.JLabel();
        Bills1 = new javax.swing.JLabel();
        RollDice1 = new javax.swing.JButton();
        DealCards1 = new javax.swing.JButton();
        Dice1 = new javax.swing.JLabel();
        secondPlayerPanel = new javax.swing.JPanel();
        Player2 = new javax.swing.JLabel();
        Money2 = new javax.swing.JLabel();
        Loan2 = new javax.swing.JLabel();
        Bills2 = new javax.swing.JLabel();
        RollDice2 = new javax.swing.JButton();
        DealCards2 = new javax.swing.JButton();
        Dice2 = new javax.swing.JLabel();
        InfoPanel = new javax.swing.JPanel();
        InfoBox = new javax.swing.JLabel();
        Turn = new javax.swing.JLabel();
        Months = new javax.swing.JLabel();
        Order = new javax.swing.JLabel();
        MailCards = new javax.swing.JButton();
        DealCards = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 0));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tsoxaPanel.setBackground(new java.awt.Color(0, 102, 0));
        tsoxaPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tsoxaPanel.add(pawn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, 60, 60));
        tsoxaPanel.add(pawn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 60, 60));

        startPanel.setBackground(new java.awt.Color(255, 255, 102));
        startPanel.setPreferredSize(new java.awt.Dimension(70, 80));

        StartLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        StartLabel.setForeground(new java.awt.Color(204, 0, 204));
        StartLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StartLabel.setText("Start");

        Start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/start - Αντιγραφή.png"))); // NOI18N

        javax.swing.GroupLayout startPanelLayout = new javax.swing.GroupLayout(startPanel);
        startPanel.setLayout(startPanelLayout);
        startPanelLayout.setHorizontalGroup(
            startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(StartLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        startPanelLayout.setVerticalGroup(
            startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startPanelLayout.createSequentialGroup()
                .addComponent(StartLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Start, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tsoxaPanel.add(startPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 147, -1, -1));

        Tuesday2Panel.setBackground(new java.awt.Color(255, 255, 102));
        Tuesday2Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Tuesday2Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Tuesday2Label.setForeground(new java.awt.Color(204, 0, 204));
        Tuesday2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tuesday2Label.setText("Tuesday 2");

        Tuesday2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/mc1.png"))); // NOI18N

        javax.swing.GroupLayout Tuesday2PanelLayout = new javax.swing.GroupLayout(Tuesday2Panel);
        Tuesday2Panel.setLayout(Tuesday2PanelLayout);
        Tuesday2PanelLayout.setHorizontalGroup(
            Tuesday2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tuesday2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Tuesday2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Tuesday2PanelLayout.setVerticalGroup(
            Tuesday2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tuesday2PanelLayout.createSequentialGroup()
                .addComponent(Tuesday2Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tuesday2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Tuesday2Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 147, -1, -1));

        Wednesday3Panel.setBackground(new java.awt.Color(255, 255, 102));
        Wednesday3Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Wednesday3Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Wednesday3Label.setForeground(new java.awt.Color(204, 0, 204));
        Wednesday3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Wednesday3Label.setText("Wednes. 3");

        Wednesday3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/mc1.png"))); // NOI18N

        javax.swing.GroupLayout Wednesday3PanelLayout = new javax.swing.GroupLayout(Wednesday3Panel);
        Wednesday3Panel.setLayout(Wednesday3PanelLayout);
        Wednesday3PanelLayout.setHorizontalGroup(
            Wednesday3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Wednesday3Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Wednesday3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Wednesday3PanelLayout.setVerticalGroup(
            Wednesday3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Wednesday3PanelLayout.createSequentialGroup()
                .addComponent(Wednesday3Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Wednesday3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Wednesday3Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 147, -1, -1));

        Thursday4Panel.setBackground(new java.awt.Color(255, 255, 102));
        Thursday4Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Thursday4Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Thursday4Label.setForeground(new java.awt.Color(204, 0, 204));
        Thursday4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Thursday4Label.setText("Thursday 4");

        Thursday4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/mc1.png"))); // NOI18N

        javax.swing.GroupLayout Thursday4PanelLayout = new javax.swing.GroupLayout(Thursday4Panel);
        Thursday4Panel.setLayout(Thursday4PanelLayout);
        Thursday4PanelLayout.setHorizontalGroup(
            Thursday4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Thursday4Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Thursday4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Thursday4PanelLayout.setVerticalGroup(
            Thursday4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Thursday4PanelLayout.createSequentialGroup()
                .addComponent(Thursday4Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Thursday4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Thursday4Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 147, -1, -1));

        Friday5Panel.setBackground(new java.awt.Color(255, 255, 102));
        Friday5Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Friday5Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Friday5Label.setForeground(new java.awt.Color(204, 0, 204));
        Friday5Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Friday5Label.setText("Friday 5");

        Friday5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/mc2.png"))); // NOI18N

        javax.swing.GroupLayout Friday5PanelLayout = new javax.swing.GroupLayout(Friday5Panel);
        Friday5Panel.setLayout(Friday5PanelLayout);
        Friday5PanelLayout.setHorizontalGroup(
            Friday5PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Friday5Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Friday5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Friday5PanelLayout.setVerticalGroup(
            Friday5PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Friday5PanelLayout.createSequentialGroup()
                .addComponent(Friday5Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Friday5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Friday5Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 147, -1, -1));

        Saturday6Panel.setBackground(new java.awt.Color(255, 255, 102));
        Saturday6Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Saturday6Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Saturday6Label.setForeground(new java.awt.Color(204, 0, 204));
        Saturday6Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Saturday6Label.setText("Saturday 6");

        Saturday6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/mc2.png"))); // NOI18N

        javax.swing.GroupLayout Saturday6PanelLayout = new javax.swing.GroupLayout(Saturday6Panel);
        Saturday6Panel.setLayout(Saturday6PanelLayout);
        Saturday6PanelLayout.setHorizontalGroup(
            Saturday6PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Saturday6Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Saturday6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Saturday6PanelLayout.setVerticalGroup(
            Saturday6PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Saturday6PanelLayout.createSequentialGroup()
                .addComponent(Saturday6Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Saturday6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Saturday6Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 147, -1, -1));

        Monday1Panel.setBackground(new java.awt.Color(255, 255, 102));
        Monday1Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Monday1Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Monday1Label.setForeground(new java.awt.Color(204, 0, 204));
        Monday1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Monday1Label.setText("Monday 1");

        Monday1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/mc1.png"))); // NOI18N

        javax.swing.GroupLayout Monday1PanelLayout = new javax.swing.GroupLayout(Monday1Panel);
        Monday1Panel.setLayout(Monday1PanelLayout);
        Monday1PanelLayout.setHorizontalGroup(
            Monday1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Monday1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Monday1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Monday1PanelLayout.setVerticalGroup(
            Monday1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Monday1PanelLayout.createSequentialGroup()
                .addComponent(Monday1Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Monday1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Monday1Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 147, -1, -1));

        Sunday7Panel.setBackground(new java.awt.Color(255, 255, 102));
        Sunday7Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Sunday7Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Sunday7Label.setForeground(new java.awt.Color(204, 0, 204));
        Sunday7Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sunday7Label.setText("Sunday 7");

        Sunday7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/mc2.png"))); // NOI18N

        javax.swing.GroupLayout Sunday7PanelLayout = new javax.swing.GroupLayout(Sunday7Panel);
        Sunday7Panel.setLayout(Sunday7PanelLayout);
        Sunday7PanelLayout.setHorizontalGroup(
            Sunday7PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Sunday7Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Sunday7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Sunday7PanelLayout.setVerticalGroup(
            Sunday7PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sunday7PanelLayout.createSequentialGroup()
                .addComponent(Sunday7Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sunday7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Sunday7Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 233, -1, -1));

        Sunday21Panel.setBackground(new java.awt.Color(255, 255, 102));
        Sunday21Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Sunday21Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Sunday21Label.setForeground(new java.awt.Color(204, 0, 204));
        Sunday21Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sunday21Label.setText("Sunday 21");

        Sunday21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/buyer.png"))); // NOI18N

        javax.swing.GroupLayout Sunday21PanelLayout = new javax.swing.GroupLayout(Sunday21Panel);
        Sunday21Panel.setLayout(Sunday21PanelLayout);
        Sunday21PanelLayout.setHorizontalGroup(
            Sunday21PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Sunday21Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Sunday21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Sunday21PanelLayout.setVerticalGroup(
            Sunday21PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sunday21PanelLayout.createSequentialGroup()
                .addComponent(Sunday21Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sunday21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Sunday21Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 405, -1, -1));

        Sunday28Panel.setBackground(new java.awt.Color(255, 255, 102));
        Sunday28Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Sunday28Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Sunday28Label.setForeground(new java.awt.Color(204, 0, 204));
        Sunday28Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sunday28Label.setText("Sunday 28");

        Sunday28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/casino.png"))); // NOI18N

        javax.swing.GroupLayout Sunday28PanelLayout = new javax.swing.GroupLayout(Sunday28Panel);
        Sunday28Panel.setLayout(Sunday28PanelLayout);
        Sunday28PanelLayout.setHorizontalGroup(
            Sunday28PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Sunday28Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Sunday28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Sunday28PanelLayout.setVerticalGroup(
            Sunday28PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sunday28PanelLayout.createSequentialGroup()
                .addComponent(Sunday28Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sunday28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Sunday28Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 491, -1, -1));

        Sunday14Panel.setBackground(new java.awt.Color(255, 255, 102));
        Sunday14Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Sunday14Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Sunday14Label.setForeground(new java.awt.Color(204, 0, 204));
        Sunday14Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sunday14Label.setText("Sunday 14");

        Sunday14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/sweep.png"))); // NOI18N

        javax.swing.GroupLayout Sunday14PanelLayout = new javax.swing.GroupLayout(Sunday14Panel);
        Sunday14Panel.setLayout(Sunday14PanelLayout);
        Sunday14PanelLayout.setHorizontalGroup(
            Sunday14PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Sunday14Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Sunday14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Sunday14PanelLayout.setVerticalGroup(
            Sunday14PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sunday14PanelLayout.createSequentialGroup()
                .addComponent(Sunday14Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sunday14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Sunday14Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 319, -1, -1));

        Monday8Panel.setBackground(new java.awt.Color(255, 255, 102));
        Monday8Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Monday8Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Monday8Label.setForeground(new java.awt.Color(204, 0, 204));
        Monday8Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Monday8Label.setText("Monday 8");

        Monday8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/mc2.png"))); // NOI18N

        javax.swing.GroupLayout Monday8PanelLayout = new javax.swing.GroupLayout(Monday8Panel);
        Monday8Panel.setLayout(Monday8PanelLayout);
        Monday8PanelLayout.setHorizontalGroup(
            Monday8PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Monday8Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Monday8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Monday8PanelLayout.setVerticalGroup(
            Monday8PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Monday8PanelLayout.createSequentialGroup()
                .addComponent(Monday8Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Monday8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Monday8Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 233, -1, -1));

        Tuesday9Panel.setBackground(new java.awt.Color(255, 255, 102));
        Tuesday9Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Tuesday9Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Tuesday9Label.setForeground(new java.awt.Color(204, 0, 204));
        Tuesday9Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tuesday9Label.setText("Tuesday 9");

        Tuesday9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/deal.png"))); // NOI18N

        javax.swing.GroupLayout Tuesday9PanelLayout = new javax.swing.GroupLayout(Tuesday9Panel);
        Tuesday9Panel.setLayout(Tuesday9PanelLayout);
        Tuesday9PanelLayout.setHorizontalGroup(
            Tuesday9PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tuesday9Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Tuesday9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Tuesday9PanelLayout.setVerticalGroup(
            Tuesday9PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tuesday9PanelLayout.createSequentialGroup()
                .addComponent(Tuesday9Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tuesday9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Tuesday9Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 233, -1, -1));

        Saturday20Panel.setBackground(new java.awt.Color(255, 255, 102));
        Saturday20Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Saturday20Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Saturday20Label.setForeground(new java.awt.Color(204, 0, 204));
        Saturday20Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Saturday20Label.setText("Saturday 20");

        Saturday20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/radio.png"))); // NOI18N

        javax.swing.GroupLayout Saturday20PanelLayout = new javax.swing.GroupLayout(Saturday20Panel);
        Saturday20Panel.setLayout(Saturday20PanelLayout);
        Saturday20PanelLayout.setHorizontalGroup(
            Saturday20PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Saturday20Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Saturday20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Saturday20PanelLayout.setVerticalGroup(
            Saturday20PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Saturday20PanelLayout.createSequentialGroup()
                .addComponent(Saturday20Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Saturday20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Saturday20Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 319, -1, -1));

        Thursday11Panel.setBackground(new java.awt.Color(255, 255, 102));
        Thursday11Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Thursday11Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Thursday11Label.setForeground(new java.awt.Color(204, 0, 204));
        Thursday11Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Thursday11Label.setText("Thursday 11");

        Thursday11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/deal.png"))); // NOI18N

        javax.swing.GroupLayout Thursday11PanelLayout = new javax.swing.GroupLayout(Thursday11Panel);
        Thursday11Panel.setLayout(Thursday11PanelLayout);
        Thursday11PanelLayout.setHorizontalGroup(
            Thursday11PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Thursday11Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Thursday11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Thursday11PanelLayout.setVerticalGroup(
            Thursday11PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Thursday11PanelLayout.createSequentialGroup()
                .addComponent(Thursday11Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Thursday11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Thursday11Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 233, -1, -1));

        Friday12Panel.setBackground(new java.awt.Color(255, 255, 102));
        Friday12Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Friday12Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Friday12Label.setForeground(new java.awt.Color(204, 0, 204));
        Friday12Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Friday12Label.setText("Friday 12");

        Friday12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/deal.png"))); // NOI18N

        javax.swing.GroupLayout Friday12PanelLayout = new javax.swing.GroupLayout(Friday12Panel);
        Friday12Panel.setLayout(Friday12PanelLayout);
        Friday12PanelLayout.setHorizontalGroup(
            Friday12PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Friday12Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Friday12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Friday12PanelLayout.setVerticalGroup(
            Friday12PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Friday12PanelLayout.createSequentialGroup()
                .addComponent(Friday12Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Friday12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Friday12Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 233, -1, -1));

        Saturday13Panel.setBackground(new java.awt.Color(255, 255, 102));
        Saturday13Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Saturday13Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Saturday13Label.setForeground(new java.awt.Color(204, 0, 204));
        Saturday13Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Saturday13Label.setText("Saturday 13");

        Saturday13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/deal.png"))); // NOI18N

        javax.swing.GroupLayout Saturday13PanelLayout = new javax.swing.GroupLayout(Saturday13Panel);
        Saturday13Panel.setLayout(Saturday13PanelLayout);
        Saturday13PanelLayout.setHorizontalGroup(
            Saturday13PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Saturday13Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Saturday13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Saturday13PanelLayout.setVerticalGroup(
            Saturday13PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Saturday13PanelLayout.createSequentialGroup()
                .addComponent(Saturday13Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Saturday13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Saturday13Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 233, -1, -1));

        Monday15Panel.setBackground(new java.awt.Color(255, 255, 102));
        Monday15Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Monday15Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Monday15Label.setForeground(new java.awt.Color(204, 0, 204));
        Monday15Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Monday15Label.setText("Monday 15");

        Monday15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/sweep.png"))); // NOI18N

        javax.swing.GroupLayout Monday15PanelLayout = new javax.swing.GroupLayout(Monday15Panel);
        Monday15Panel.setLayout(Monday15PanelLayout);
        Monday15PanelLayout.setHorizontalGroup(
            Monday15PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Monday15Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Monday15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Monday15PanelLayout.setVerticalGroup(
            Monday15PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Monday15PanelLayout.createSequentialGroup()
                .addComponent(Monday15Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Monday15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Monday15Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 319, -1, -1));

        Monday22Panel.setBackground(new java.awt.Color(255, 255, 102));
        Monday22Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Monday22Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Monday22Label.setForeground(new java.awt.Color(204, 0, 204));
        Monday22Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Monday22Label.setText("Monday 22");

        Monday22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/buyer.png"))); // NOI18N

        javax.swing.GroupLayout Monday22PanelLayout = new javax.swing.GroupLayout(Monday22Panel);
        Monday22Panel.setLayout(Monday22PanelLayout);
        Monday22PanelLayout.setHorizontalGroup(
            Monday22PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Monday22Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Monday22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Monday22PanelLayout.setVerticalGroup(
            Monday22PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Monday22PanelLayout.createSequentialGroup()
                .addComponent(Monday22Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Monday22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Monday22Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 405, -1, -1));

        Monday29Panel.setBackground(new java.awt.Color(255, 255, 102));
        Monday29Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Monday29Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Monday29Label.setForeground(new java.awt.Color(204, 0, 204));
        Monday29Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Monday29Label.setText("Monday 29");

        Monday29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/yard.png"))); // NOI18N

        javax.swing.GroupLayout Monday29PanelLayout = new javax.swing.GroupLayout(Monday29Panel);
        Monday29Panel.setLayout(Monday29PanelLayout);
        Monday29PanelLayout.setHorizontalGroup(
            Monday29PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Monday29Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Monday29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Monday29PanelLayout.setVerticalGroup(
            Monday29PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Monday29PanelLayout.createSequentialGroup()
                .addComponent(Monday29Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Monday29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Monday29Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 491, -1, -1));

        Tuesday16Panel.setBackground(new java.awt.Color(255, 255, 102));
        Tuesday16Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Tuesday16Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Tuesday16Label.setForeground(new java.awt.Color(204, 0, 204));
        Tuesday16Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tuesday16Label.setText("Tuesday 16");

        Tuesday16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/lottery.png"))); // NOI18N

        javax.swing.GroupLayout Tuesday16PanelLayout = new javax.swing.GroupLayout(Tuesday16Panel);
        Tuesday16Panel.setLayout(Tuesday16PanelLayout);
        Tuesday16PanelLayout.setHorizontalGroup(
            Tuesday16PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tuesday16Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Tuesday16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Tuesday16PanelLayout.setVerticalGroup(
            Tuesday16PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tuesday16PanelLayout.createSequentialGroup()
                .addComponent(Tuesday16Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tuesday16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Tuesday16Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 319, -1, -1));

        Tuesday23Panel.setBackground(new java.awt.Color(255, 255, 102));
        Tuesday23Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Tuesday23Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Tuesday23Label.setForeground(new java.awt.Color(204, 0, 204));
        Tuesday23Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tuesday23Label.setText("Tuesday 23");

        Tuesday23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/buyer.png"))); // NOI18N

        javax.swing.GroupLayout Tuesday23PanelLayout = new javax.swing.GroupLayout(Tuesday23Panel);
        Tuesday23Panel.setLayout(Tuesday23PanelLayout);
        Tuesday23PanelLayout.setHorizontalGroup(
            Tuesday23PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tuesday23Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Tuesday23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Tuesday23PanelLayout.setVerticalGroup(
            Tuesday23PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tuesday23PanelLayout.createSequentialGroup()
                .addComponent(Tuesday23Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tuesday23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Tuesday23Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 405, -1, -1));

        Tuesday30Panel.setBackground(new java.awt.Color(255, 255, 102));
        Tuesday30Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Tuesday30Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Tuesday30Label.setForeground(new java.awt.Color(204, 0, 204));
        Tuesday30Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tuesday30Label.setText("Tuesday 30");

        Tuesday30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/yard.png"))); // NOI18N

        javax.swing.GroupLayout Tuesday30PanelLayout = new javax.swing.GroupLayout(Tuesday30Panel);
        Tuesday30Panel.setLayout(Tuesday30PanelLayout);
        Tuesday30PanelLayout.setHorizontalGroup(
            Tuesday30PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tuesday30Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Tuesday30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Tuesday30PanelLayout.setVerticalGroup(
            Tuesday30PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tuesday30PanelLayout.createSequentialGroup()
                .addComponent(Tuesday30Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tuesday30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Tuesday30Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 491, -1, -1));

        Wednesday17Panel.setBackground(new java.awt.Color(255, 255, 102));
        Wednesday17Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Wednesday17Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Wednesday17Label.setForeground(new java.awt.Color(204, 0, 204));
        Wednesday17Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Wednesday17Label.setText("Wednes. 17");

        Wednesday17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/lottery.png"))); // NOI18N

        javax.swing.GroupLayout Wednesday17PanelLayout = new javax.swing.GroupLayout(Wednesday17Panel);
        Wednesday17Panel.setLayout(Wednesday17PanelLayout);
        Wednesday17PanelLayout.setHorizontalGroup(
            Wednesday17PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Wednesday17Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Wednesday17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Wednesday17PanelLayout.setVerticalGroup(
            Wednesday17PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Wednesday17PanelLayout.createSequentialGroup()
                .addComponent(Wednesday17Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Wednesday17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Wednesday17Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 319, -1, -1));

        Wednesday24Panel.setBackground(new java.awt.Color(255, 255, 102));
        Wednesday24Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Wednesday24Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Wednesday24Label.setForeground(new java.awt.Color(204, 0, 204));
        Wednesday24Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Wednesday24Label.setText("Wednes. 24");

        Wednesday24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/buyer.png"))); // NOI18N

        javax.swing.GroupLayout Wednesday24PanelLayout = new javax.swing.GroupLayout(Wednesday24Panel);
        Wednesday24Panel.setLayout(Wednesday24PanelLayout);
        Wednesday24PanelLayout.setHorizontalGroup(
            Wednesday24PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Wednesday24Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Wednesday24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Wednesday24PanelLayout.setVerticalGroup(
            Wednesday24PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Wednesday24PanelLayout.createSequentialGroup()
                .addComponent(Wednesday24Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Wednesday24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Wednesday24Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 405, -1, -1));

        PayDayPanel.setBackground(new java.awt.Color(255, 255, 102));
        PayDayPanel.setPreferredSize(new java.awt.Dimension(70, 80));

        Wednesday31Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Wednesday31Label.setForeground(new java.awt.Color(204, 0, 204));
        Wednesday31Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Wednesday31Label.setText("Wednes. 31");

        PayDayPos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/pay.png"))); // NOI18N

        javax.swing.GroupLayout PayDayPanelLayout = new javax.swing.GroupLayout(PayDayPanel);
        PayDayPanel.setLayout(PayDayPanelLayout);
        PayDayPanelLayout.setHorizontalGroup(
            PayDayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Wednesday31Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PayDayPos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PayDayPanelLayout.setVerticalGroup(
            PayDayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PayDayPanelLayout.createSequentialGroup()
                .addComponent(Wednesday31Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PayDayPos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(PayDayPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 491, -1, -1));

        Thursday18Panel.setBackground(new java.awt.Color(255, 255, 102));
        Thursday18Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Thursday18Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Thursday18Label.setForeground(new java.awt.Color(204, 0, 204));
        Thursday18Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Thursday18Label.setText("Thursday 18");

        Thursday18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/lottery.png"))); // NOI18N

        javax.swing.GroupLayout Thursday18PanelLayout = new javax.swing.GroupLayout(Thursday18Panel);
        Thursday18Panel.setLayout(Thursday18PanelLayout);
        Thursday18PanelLayout.setHorizontalGroup(
            Thursday18PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Thursday18Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Thursday18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Thursday18PanelLayout.setVerticalGroup(
            Thursday18PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Thursday18PanelLayout.createSequentialGroup()
                .addComponent(Thursday18Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Thursday18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Thursday18Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 319, -1, -1));

        Thursday25Panel.setBackground(new java.awt.Color(255, 255, 102));
        Thursday25Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Thursday25Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Thursday25Label.setForeground(new java.awt.Color(204, 0, 204));
        Thursday25Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Thursday25Label.setText("Thursday 25");

        Thursday25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/buyer.png"))); // NOI18N

        javax.swing.GroupLayout Thursday25PanelLayout = new javax.swing.GroupLayout(Thursday25Panel);
        Thursday25Panel.setLayout(Thursday25PanelLayout);
        Thursday25PanelLayout.setHorizontalGroup(
            Thursday25PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Thursday25Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Thursday25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Thursday25PanelLayout.setVerticalGroup(
            Thursday25PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Thursday25PanelLayout.createSequentialGroup()
                .addComponent(Thursday25Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Thursday25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Thursday25Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 405, -1, -1));

        Friday19Panel.setBackground(new java.awt.Color(255, 255, 102));
        Friday19Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Friday19Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Friday19Label.setForeground(new java.awt.Color(204, 0, 204));
        Friday19Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Friday19Label.setText("Friday 19");

        Friday19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/radio.png"))); // NOI18N

        javax.swing.GroupLayout Friday19PanelLayout = new javax.swing.GroupLayout(Friday19Panel);
        Friday19Panel.setLayout(Friday19PanelLayout);
        Friday19PanelLayout.setHorizontalGroup(
            Friday19PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Friday19Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Friday19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Friday19PanelLayout.setVerticalGroup(
            Friday19PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Friday19PanelLayout.createSequentialGroup()
                .addComponent(Friday19Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Friday19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Friday19Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 319, -1, -1));

        Wednesday10Panel.setBackground(new java.awt.Color(255, 255, 102));
        Wednesday10Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Wednesday10Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Wednesday10Label.setForeground(new java.awt.Color(204, 0, 204));
        Wednesday10Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Wednesday10Label.setText("Wednes. 10");

        Wednesday10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/deal.png"))); // NOI18N

        javax.swing.GroupLayout Wednesday10PanelLayout = new javax.swing.GroupLayout(Wednesday10Panel);
        Wednesday10Panel.setLayout(Wednesday10PanelLayout);
        Wednesday10PanelLayout.setHorizontalGroup(
            Wednesday10PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Wednesday10Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Wednesday10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Wednesday10PanelLayout.setVerticalGroup(
            Wednesday10PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Wednesday10PanelLayout.createSequentialGroup()
                .addComponent(Wednesday10Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Wednesday10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Wednesday10Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 233, -1, -1));

        Saturday27Panel.setBackground(new java.awt.Color(255, 255, 102));
        Saturday27Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Saturday27Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Saturday27Label.setForeground(new java.awt.Color(204, 0, 204));
        Saturday27Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Saturday27Label.setText("Saturday 27");

        Saturday27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/casino.png"))); // NOI18N

        javax.swing.GroupLayout Saturday27PanelLayout = new javax.swing.GroupLayout(Saturday27Panel);
        Saturday27Panel.setLayout(Saturday27PanelLayout);
        Saturday27PanelLayout.setHorizontalGroup(
            Saturday27PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Saturday27Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Saturday27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Saturday27PanelLayout.setVerticalGroup(
            Saturday27PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Saturday27PanelLayout.createSequentialGroup()
                .addComponent(Saturday27Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Saturday27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Saturday27Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 405, -1, -1));

        Friday26Panel.setBackground(new java.awt.Color(255, 255, 102));
        Friday26Panel.setPreferredSize(new java.awt.Dimension(70, 80));

        Friday26Label.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Friday26Label.setForeground(new java.awt.Color(204, 0, 204));
        Friday26Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Friday26Label.setText("Friday 26");

        Friday26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/buyer.png"))); // NOI18N

        javax.swing.GroupLayout Friday26PanelLayout = new javax.swing.GroupLayout(Friday26Panel);
        Friday26Panel.setLayout(Friday26PanelLayout);
        Friday26PanelLayout.setHorizontalGroup(
            Friday26PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Friday26Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Friday26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Friday26PanelLayout.setVerticalGroup(
            Friday26PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Friday26PanelLayout.createSequentialGroup()
                .addComponent(Friday26Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Friday26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tsoxaPanel.add(Friday26Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 405, -1, -1));

        MainLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/logo1.png"))); // NOI18N
        tsoxaPanel.add(MainLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 550, -1));

        Jackpot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/jackpot.png"))); // NOI18N
        tsoxaPanel.add(Jackpot, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 491, -1, -1));

        JackPotEuro.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        JackPotEuro.setForeground(new java.awt.Color(255, 255, 255));
        JackPotEuro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JackPotEuro.setText("JackPot: X Euro");
        tsoxaPanel.add(JackPotEuro, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 547, 155, -1));

        getContentPane().add(tsoxaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 574));

        controlPanel.setBackground(new java.awt.Color(0, 102, 0));

        firstPlayerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2));

        Player1.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Player1.setForeground(new java.awt.Color(0, 0, 255));
        Player1.setText("Player 1");

        Money1.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        Money1.setForeground(new java.awt.Color(0, 0, 255));
        Money1.setText("Money: X Euro");

        Loan1.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        Loan1.setForeground(new java.awt.Color(0, 0, 255));
        Loan1.setText("Loan: X Euro");

        Bills1.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        Bills1.setForeground(new java.awt.Color(0, 0, 255));
        Bills1.setText("Bills: X Euro");

        RollDice1.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        RollDice1.setForeground(new java.awt.Color(0, 0, 255));
        RollDice1.setText("Roll Dice");
        RollDice1.setEnabled(false);
        RollDice1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RollDice1ActionPerformed(evt);
            }
        });

        DealCards1.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        DealCards1.setForeground(new java.awt.Color(0, 0, 255));
        DealCards1.setText("Deal Cards");
        DealCards1.setEnabled(false);
        DealCards1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DealCards1ActionPerformed(evt);
            }
        });

        Dice1.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout firstPlayerPanelLayout = new javax.swing.GroupLayout(firstPlayerPanel);
        firstPlayerPanel.setLayout(firstPlayerPanelLayout);
        firstPlayerPanelLayout.setHorizontalGroup(
            firstPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstPlayerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(firstPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(firstPlayerPanelLayout.createSequentialGroup()
                        .addGroup(firstPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Player1)
                            .addComponent(Money1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Dice1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(firstPlayerPanelLayout.createSequentialGroup()
                        .addGroup(firstPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Bills1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(firstPlayerPanelLayout.createSequentialGroup()
                                .addComponent(RollDice1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DealCards1))
                            .addComponent(Loan1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        firstPlayerPanelLayout.setVerticalGroup(
            firstPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstPlayerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(firstPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(firstPlayerPanelLayout.createSequentialGroup()
                        .addComponent(Player1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Money1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Dice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Loan1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bills1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(firstPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RollDice1)
                    .addComponent(DealCards1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        secondPlayerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 2));
        secondPlayerPanel.setPreferredSize(new java.awt.Dimension(4, 150));

        Player2.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        Player2.setForeground(new java.awt.Color(255, 0, 0));
        Player2.setText("Player 2");

        Money2.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        Money2.setForeground(new java.awt.Color(255, 0, 0));
        Money2.setText("Money: X Euro");

        Loan2.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        Loan2.setForeground(new java.awt.Color(255, 0, 0));
        Loan2.setText("Loan: X Euro");

        Bills2.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        Bills2.setForeground(new java.awt.Color(255, 0, 0));
        Bills2.setText("Bills: X Euro");

        RollDice2.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        RollDice2.setForeground(new java.awt.Color(255, 0, 0));
        RollDice2.setText("Roll Dice");
        RollDice2.setEnabled(false);
        RollDice2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RollDice2ActionPerformed(evt);
            }
        });

        DealCards2.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        DealCards2.setForeground(new java.awt.Color(255, 0, 0));
        DealCards2.setText("Deal Cards");
        DealCards2.setEnabled(false);
        DealCards2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DealCards2ActionPerformed(evt);
            }
        });

        Dice2.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout secondPlayerPanelLayout = new javax.swing.GroupLayout(secondPlayerPanel);
        secondPlayerPanel.setLayout(secondPlayerPanelLayout);
        secondPlayerPanelLayout.setHorizontalGroup(
            secondPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secondPlayerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(secondPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Bills2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(secondPlayerPanelLayout.createSequentialGroup()
                        .addComponent(RollDice2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DealCards2))
                    .addComponent(Loan2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, secondPlayerPanelLayout.createSequentialGroup()
                        .addGroup(secondPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Player2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Money2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(Dice2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        secondPlayerPanelLayout.setVerticalGroup(
            secondPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secondPlayerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(secondPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(secondPlayerPanelLayout.createSequentialGroup()
                        .addComponent(Player2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Money2))
                    .addGroup(secondPlayerPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(Dice2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Loan2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bills2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(secondPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RollDice2)
                    .addComponent(DealCards2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        InfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        InfoBox.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        InfoBox.setText("InfoBox");

        Turn.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        Turn.setText("Player's X Turn");

        Months.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        Months.setText("Months Left");

        Order.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        Order.setText("Orders");

        javax.swing.GroupLayout InfoPanelLayout = new javax.swing.GroupLayout(InfoPanel);
        InfoPanel.setLayout(InfoPanelLayout);
        InfoPanelLayout.setHorizontalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Months, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Turn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Order, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(InfoPanelLayout.createSequentialGroup()
                        .addComponent(InfoBox)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        InfoPanelLayout.setVerticalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoPanelLayout.createSequentialGroup()
                .addComponent(InfoBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Months)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Turn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Order)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MailCards.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/mailCard.png"))); // NOI18N
        MailCards.setEnabled(false);
        MailCards.setPreferredSize(new java.awt.Dimension(80, 50));
        MailCards.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MailCardsActionPerformed(evt);
            }
        });

        DealCards.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paydaycards/views/Images/dealCard.png"))); // NOI18N
        DealCards.setEnabled(false);
        DealCards.setPreferredSize(new java.awt.Dimension(80, 50));
        DealCards.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DealCardsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(InfoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(firstPlayerPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(MailCards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DealCards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(secondPlayerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(firstPlayerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(InfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MailCards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DealCards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(secondPlayerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(controlPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(556, 0, -1, 574));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RollDice1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RollDice1ActionPerformed
        playerRolledDieAction.performAction();
    }//GEN-LAST:event_RollDice1ActionPerformed

    private void DealCards1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DealCards1ActionPerformed

    }//GEN-LAST:event_DealCards1ActionPerformed

    private void RollDice2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RollDice2ActionPerformed
        playerRolledDieAction.performAction();
    }//GEN-LAST:event_RollDice2ActionPerformed

    private void DealCards2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DealCards2ActionPerformed

    }//GEN-LAST:event_DealCards2ActionPerformed

    private void MailCardsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MailCardsActionPerformed
        playerCardAction.performAction();
    }//GEN-LAST:event_MailCardsActionPerformed

    private void DealCardsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DealCardsActionPerformed
        playerCardAction.performAction();
    }//GEN-LAST:event_DealCardsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bills1;
    private javax.swing.JLabel Bills2;
    private javax.swing.JButton DealCards;
    private javax.swing.JButton DealCards1;
    private javax.swing.JButton DealCards2;
    private javax.swing.JLabel Dice1;
    private javax.swing.JLabel Dice2;
    private javax.swing.JLabel Friday12;
    private javax.swing.JLabel Friday12Label;
    private javax.swing.JPanel Friday12Panel;
    private javax.swing.JLabel Friday19;
    private javax.swing.JLabel Friday19Label;
    private javax.swing.JPanel Friday19Panel;
    private javax.swing.JLabel Friday26;
    private javax.swing.JLabel Friday26Label;
    private javax.swing.JPanel Friday26Panel;
    private javax.swing.JLabel Friday5;
    private javax.swing.JLabel Friday5Label;
    private javax.swing.JPanel Friday5Panel;
    private javax.swing.JLabel InfoBox;
    private javax.swing.JPanel InfoPanel;
    private javax.swing.JLabel JackPotEuro;
    private javax.swing.JLabel Jackpot;
    private javax.swing.JLabel Loan1;
    private javax.swing.JLabel Loan2;
    private javax.swing.JButton MailCards;
    private javax.swing.JLabel MainLabel;
    private javax.swing.JLabel Monday1;
    private javax.swing.JLabel Monday15;
    private javax.swing.JLabel Monday15Label;
    private javax.swing.JPanel Monday15Panel;
    private javax.swing.JLabel Monday1Label;
    private javax.swing.JPanel Monday1Panel;
    private javax.swing.JLabel Monday22;
    private javax.swing.JLabel Monday22Label;
    private javax.swing.JPanel Monday22Panel;
    private javax.swing.JLabel Monday29;
    private javax.swing.JLabel Monday29Label;
    private javax.swing.JPanel Monday29Panel;
    private javax.swing.JLabel Monday8;
    private javax.swing.JLabel Monday8Label;
    private javax.swing.JPanel Monday8Panel;
    private javax.swing.JLabel Money1;
    private javax.swing.JLabel Money2;
    private javax.swing.JLabel Months;
    private javax.swing.JLabel Order;
    private javax.swing.JPanel PayDayPanel;
    private javax.swing.JLabel PayDayPos;
    private javax.swing.JLabel Player1;
    private javax.swing.JLabel Player2;
    private javax.swing.JButton RollDice1;
    private javax.swing.JButton RollDice2;
    private javax.swing.JLabel Saturday13;
    private javax.swing.JLabel Saturday13Label;
    private javax.swing.JPanel Saturday13Panel;
    private javax.swing.JLabel Saturday20;
    private javax.swing.JLabel Saturday20Label;
    private javax.swing.JPanel Saturday20Panel;
    private javax.swing.JLabel Saturday27;
    private javax.swing.JLabel Saturday27Label;
    private javax.swing.JPanel Saturday27Panel;
    private javax.swing.JLabel Saturday6;
    private javax.swing.JLabel Saturday6Label;
    private javax.swing.JPanel Saturday6Panel;
    private javax.swing.JLabel Start;
    private javax.swing.JLabel StartLabel;
    private javax.swing.JLabel Sunday14;
    private javax.swing.JLabel Sunday14Label;
    private javax.swing.JPanel Sunday14Panel;
    private javax.swing.JLabel Sunday21;
    private javax.swing.JLabel Sunday21Label;
    private javax.swing.JPanel Sunday21Panel;
    private javax.swing.JLabel Sunday28;
    private javax.swing.JLabel Sunday28Label;
    private javax.swing.JPanel Sunday28Panel;
    private javax.swing.JLabel Sunday7;
    private javax.swing.JLabel Sunday7Label;
    private javax.swing.JPanel Sunday7Panel;
    private javax.swing.JLabel Thursday11;
    private javax.swing.JLabel Thursday11Label;
    private javax.swing.JPanel Thursday11Panel;
    private javax.swing.JLabel Thursday18;
    private javax.swing.JLabel Thursday18Label;
    private javax.swing.JPanel Thursday18Panel;
    private javax.swing.JLabel Thursday25;
    private javax.swing.JLabel Thursday25Label;
    private javax.swing.JPanel Thursday25Panel;
    private javax.swing.JLabel Thursday4;
    private javax.swing.JLabel Thursday4Label;
    private javax.swing.JPanel Thursday4Panel;
    private javax.swing.JLabel Tuesday16;
    private javax.swing.JLabel Tuesday16Label;
    private javax.swing.JPanel Tuesday16Panel;
    private javax.swing.JLabel Tuesday2;
    private javax.swing.JLabel Tuesday23;
    private javax.swing.JLabel Tuesday23Label;
    private javax.swing.JPanel Tuesday23Panel;
    private javax.swing.JLabel Tuesday2Label;
    private javax.swing.JPanel Tuesday2Panel;
    private javax.swing.JLabel Tuesday30;
    private javax.swing.JLabel Tuesday30Label;
    private javax.swing.JPanel Tuesday30Panel;
    private javax.swing.JLabel Tuesday9;
    private javax.swing.JLabel Tuesday9Label;
    private javax.swing.JPanel Tuesday9Panel;
    private javax.swing.JLabel Turn;
    private javax.swing.JLabel Wednesday10;
    private javax.swing.JLabel Wednesday10Label;
    private javax.swing.JPanel Wednesday10Panel;
    private javax.swing.JLabel Wednesday17;
    private javax.swing.JLabel Wednesday17Label;
    private javax.swing.JPanel Wednesday17Panel;
    private javax.swing.JLabel Wednesday24;
    private javax.swing.JLabel Wednesday24Label;
    private javax.swing.JPanel Wednesday24Panel;
    private javax.swing.JLabel Wednesday3;
    private javax.swing.JLabel Wednesday31Label;
    private javax.swing.JLabel Wednesday3Label;
    private javax.swing.JPanel Wednesday3Panel;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel firstPlayerPanel;
    private javax.swing.JLabel pawn1;
    private javax.swing.JLabel pawn2;
    private javax.swing.JPanel secondPlayerPanel;
    private javax.swing.JPanel startPanel;
    private javax.swing.JPanel tsoxaPanel;
    // End of variables declaration//GEN-END:variables
}
