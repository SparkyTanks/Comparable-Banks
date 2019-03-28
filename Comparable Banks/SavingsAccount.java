
/**
 * Write a description of class SavingsAccount here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SavingsAccount extends BankAccount
{
    private double rate;
    
    public SavingsAccount( double deposit, String name, double r )
    {
        super(deposit,name);
        rate = r;
    }
    
    public void computeInterest()
    {
        double interest = rate*getBalance();
        this.deposit(interest);
    }
    
    public double getRate()
    {
        return rate;
    }
}
