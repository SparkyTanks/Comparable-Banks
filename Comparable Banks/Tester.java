import java.util.*;
import java.io.*;
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tester
{
    public static void main(String[] args) throws IOException
    {
        String[] bankInfo = ReadFromFile.ReadFile("E:/temp_nerd/BankofOSSM.txt");
        String name = (bankInfo[0].split("="))[1];
        int employ = Integer.parseInt((bankInfo[1].split("="))[1]);
        Bank theBank = new Bank( name, employ);
        SavingsAccount saAdder;
        BankAccount baAdder;
        for(int i = 2; i < bankInfo.length; i+=3)
        {
            if(bankInfo[i].startsWith("Savings"))
            {
                String accountName = (bankInfo[i].split("="))[1];
                int accountNum = Integer.parseInt((bankInfo[i+1].split("="))[1]);
                double balance = Double.parseDouble((bankInfo[i+2].split("="))[1]);
                double rate = Double.parseDouble((bankInfo[i+3].split("="))[1]);
                saAdder = new SavingsAccount( balance, accountName, rate );
                theBank.addAccount(saAdder);
                i++;
            }
            else
            {
                String accountName = (bankInfo[i].split("="))[1];
                int accountNum = Integer.parseInt((bankInfo[i+1].split("="))[1]);
                double balance = Double.parseDouble((bankInfo[i+2].split("="))[1]);
                baAdder = new BankAccount( balance, accountName );
                theBank.addAccount(baAdder);
            }
        }
        System.out.println(theBank.toString());
        System.out.println();

        Scanner kb = new Scanner(System.in);
        Scanner kbName = new Scanner(System.in);
        Scanner kbDouble = new Scanner(System.in);
        System.out.print("Do you want to add more accounts?  Y/N ");
        String addMore = kb.next();
        if(addMore.equalsIgnoreCase("Y"))
        {
            String more;
            do
            {
                System.out.print("Is this a Savings Account? Y/N ");
                String sAccount = kb.next();
                System.out.print("Please input the name for the account: ");
                String n = kbName.nextLine();
                System.out.print("Please input the balance for the account: ");
                double bal = kbDouble.nextDouble();
                if( sAccount.equalsIgnoreCase("Y"))
                {
                    System.out.print("Please input the interest rate for the account: ");
                    double r = kbDouble.nextDouble();
                    theBank.addAccount( new SavingsAccount(bal, n , r));
                }
                else
                {
                    theBank.addAccount(new BankAccount(bal, n));
                }

                System.out.print("Add another? Y/N ");
                more = kb.next();
            }while( more.equalsIgnoreCase("Y") );
            System.out.println();
        }

        boolean validChoice = false;
        while(validChoice == false)
        {
            Scanner srtChoice = new Scanner(System.in);
            System.out.println("Please select a method of sorting: ");
            System.out.println("1. By Account Number");
            System.out.println("2. By Account Name");
            System.out.println("3. By Account Type");
            int choice = srtChoice.nextInt();
            if(choice == 1)
            {
                numberSort(theBank);
                validChoice = true;
            }
            else if(choice == 2)
            {
                nameSort(theBank);
                validChoice = true;
            }
            else if(choice == 3)
            {
                typeSort(theBank);
                validChoice = true;
            }
            else
            {
                System.out.println("Invalid choice. Please select again.");
            }
        }

        FileWriter fw = new FileWriter("E:/temp_nerd/SortedAccounts.txt");
        PrintWriter pw = new PrintWriter(fw);

        //According to a previous project: we wanted to print to the screen of all the numbers per line in a file.

        //Solution: (but now print to file)
        Scanner sc = new Scanner(theBank.toString());
        while(sc.hasNext())
        {
            pw.println(sc.nextLine());
        }
        //To close the file before we end the program.
        //Sometimes, writing to the file does not occur until we close.
        pw.close();
        fw.close();
    }

    public static void nameSort( Bank b )
    {
        ArrayList<BankAccount> a = b.getAccounts();
        for( int i = 0; i < a.size(); i++ )
        {
            BankAccount min = a.get(i);
            int minIndex = i;
            for( int j = i+1; j < a.size(); j++ )
            {
                if( min.getName().compareTo((a.get(j)).getName()) > 0)
                {
                    //Change min and minValue
                    min = a.get(j);
                    minIndex = j;
                }
                else if( min.getName().compareTo((a.get(j)).getName()) == 0)
                {
                    if( (min.compareTo((a.get(j))) > 0 ))
                    {
                        min = a.get(j);
                        minIndex = j;
                    }
                    else if( (min.compareTo((a.get(j))) == 0 ))
                    {
                        if( min.getAccountNumber() > (a.get(j)).getAccountNumber() )
                        {
                            min = a.get(j);
                            minIndex = j;
                        }
                    }
                }
            }

            //Swap values here
            a.set( minIndex, a.get(i) );
            a.set( i, min );
        }
    }

    public static void numberSort( Bank b )
    {
        ArrayList<BankAccount> a = b.getAccounts();
        for( int i = 0; i < a.size(); i++ )
        {
            BankAccount min = a.get(i);
            int minIndex = i;
            for( int j = i+1; j < a.size(); j++ )
            {
                if( min.getAccountNumber() > (a.get(j)).getAccountNumber() )
                {
                    //Change min and minValue
                    min = a.get(j);
                    minIndex = j;
                }
            }

            //Swap values here
            a.set( minIndex, a.get(i) );
            a.set( i, min );
        }
    }

    public static void typeSort( Bank b )
    {
        ArrayList<BankAccount> a = b.getAccounts();
        Scanner sortType = new Scanner(System.in);
        System.out.println();
        System.out.println("Please choose which type of account to sort by: ");
        System.out.println("1. Bank Account");
        System.out.println("2. Savings Account");
        int choice = sortType.nextInt();
        BankAccount[] arr = new BankAccount[1];
        arr = a.toArray(arr);

        AccountTypeComparator compar = new AccountTypeComparator();

        Arrays.sort(arr, compar);

        b.updateAccounts(arr);
    }
}
