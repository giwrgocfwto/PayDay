package paydaycards.models;

/**
 * a class for the jackpot
 * @author Vasilis
 */
public class Jackpot {
    private int amount;
    
    /**
     * constructor for jackpot with 0 money in it
     */
    public Jackpot(){
        amount = 0;
    }
    
    /**
     * getters and setters for jackpot
     * @return 
     */
    public int getJackpot(){ return amount; }
    public void setJackpot(int amount){ this.amount = amount; }
    
    /**
     * when called adds a certain amount to the jackpot
     * @param amount 
     */
    public void addJackpot(int amount){
        this.amount += amount;
    }
    
    /**
     * gives all jackpot to the player who just rolled 6 with the dice 
     * @param p 
     */
    public void giveJackpot(Player p){
        p.addCash(amount);
        amount = 0;
    }
    
}
