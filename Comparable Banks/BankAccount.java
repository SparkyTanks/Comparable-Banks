
/**
 * Write a description of class BankAccount here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BankAccount extends Account implements Comparable<BankAccount>
{
    private double balance;
    private String nameMain;
    private int accountNumber;

    private static int totalAccounts = 0;

    public BankAccount( double amount, String name)
    {
        balance = amount;
        nameMain = name;
        accountNumber = totalAccounts + 1;
        totalAccounts++;
    }

    public void deposit(double depositAmount)
    {
        if ( depositAmount > 0)
        {
            balance = balance+depositAmount;
        }
        else
        {
            System.out.println("Error: Invalid Amount");
        }
    }

    public void withdraw(double withdrawAmount)
    {
        if ( withdrawAmount <= balance && withdrawAmount > 0 )
        {
            balance = balance - withdrawAmount;
        }
        else
        {
            System.out.println("Error: Invalid Withdraw Amount.");
        }
    }

    public double getBalance()
    {
        return balance;
    }

    public int compareTo(BankAccount otherObject)
    { 
        //first sort by balance 
        if(this.balance - otherObject.balance < 0)
        {
            return -1; 
        }
        else if( this.balance - otherObject.balance > 0 )
        {
            return 1;
        }
        else if( this.balance == otherObject.balance )
        {
            return 0;
        }
        return 0;
    }

    public String toString()
    {
        return "Name=" + this.nameMain + " Balance=" + this.balance + " Account Number=" + this.accountNumber; 
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public String getName()
    {
        return nameMain;
    }
}
