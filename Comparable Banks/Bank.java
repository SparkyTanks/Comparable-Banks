import java.util.ArrayList;
/**
 * Write a description of class Bank here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bank
{
    private String bankName;
    private int numEmploy;
    private ArrayList<BankAccount> allAccounts;

    public Bank(String name, int employ)
    {
        bankName = name;
        numEmploy = employ;
        allAccounts = new ArrayList<BankAccount>();
    }

    public String getName()
    {
        return bankName;
    }

    public int getEmployeeNum()
    {
        return numEmploy;
    }

    public ArrayList<BankAccount> getAccounts()
    {
        return allAccounts;
    }

    public void addAccount(BankAccount b)
    {
        allAccounts.add(b);
    }

    public String toString()
    {
        String s = "";
        s+= "BankName=" + bankName + "\n";
        s+= "NumberofEmployees=" + numEmploy + "\n";
        for( BankAccount ba : allAccounts )
        {
            if( ba instanceof SavingsAccount )
            {
                s+= "SavingsAccount=" + ba.getName() + "\n";
                s+= "AccountNumber=" + ba.getAccountNumber() + "\n";
                s+= "Balance=" + ba.getBalance() + "\n";
                s+= "Rate=" + ((SavingsAccount)ba).getRate() + "\n";
            }
            else
            {
                s+= "BankAccount=" + ba.getName() + "\n";
                s+= "AccountNumber=" + ba.getAccountNumber() + "\n";
                s+= "Balance=" + ba.getBalance() + "\n";
            }
        }
        return s;
    }
    
    public void updateAccounts( BankAccount[] arr )
    {
        allAccounts = new ArrayList<BankAccount>();
        for(int i = 0; i < arr.length; i++)
        {
            allAccounts.add(arr[i]);
        }
    }
}
