import java.util.*;
/**
 * Write a description of class AccountTypeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class AccountTypeComparator implements Comparator<BankAccount>
{
    public int compare( BankAccount b1, BankAccount b2 )
    {
        if( b1 instanceof SavingsAccount && !(b2 instanceof SavingsAccount) )
        {
            return -1;
        }
        else if ( b2 instanceof SavingsAccount && !(b1 instanceof SavingsAccount) )
        {
            return 1;
        }
        else
        {
            if( b1.getBalance() < b2.getBalance() )
            {
                return 1;
            }
            else if ( b1.getBalance() > b2.getBalance() )
            {
                return -1;
            }
            else // balances of each account are the same
            {
                if( b1 instanceof SavingsAccount && b2 instanceof SavingsAccount )
                {
                    SavingsAccount a1 = (SavingsAccount)b1;
                    SavingsAccount a2 = (SavingsAccount)b2;
                    if( a1.getRate() < a2.getRate() )
                    {
                        return 1;
                    }
                    else if( a1.getRate() > a2.getRate() )
                    {
                        return -1;
                    }
                    else
                    {
                        return 0;
                    }
                }
                else
                    return 0;
            }
        }
    }
}
