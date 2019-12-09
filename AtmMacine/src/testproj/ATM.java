package testproj;


import java.util.*;


public class ATM {

    public static Scanner wob = new Scanner(System.in);
    // The checkID method determines if acctNum is a valid account number
    // and pwd is the correct password for the account.  If the account information
    // is valid, the method returns the current account balance, as a string.
    // If the account information is invalid, the method returns the string "error".
    public static String checkID(String acctNum, String pwd)
    {
        String result = "Invalid Pin or Account Number";

        // Strings a, b, and c contain the valid account numbers and passwords.
        // For each string, the account number is listed first, followed by
        // a space, followed by the password for the account, followed by a space,
        // followed by the current balance.
        String a = "34567890987 1234 520.36";
        String b = "87211123456 2345 48.20";
        String c = "98223456787 3456 96.74";

        if (acctNum.equals(a.substring(0, a.indexOf(" "))) && 
                pwd.equals(a.substring(a.indexOf(" ")+1,a.lastIndexOf(" "))))
            return result = a.substring(a.lastIndexOf(" ") + 1);

        if (acctNum.equals(b.substring(0, b.indexOf(" "))) && 
                pwd.equals(b.substring(b.indexOf(" ")+1,b.lastIndexOf(" "))))
            return result = b.substring(b.lastIndexOf(" ") + 1);

        if (acctNum.equals(c.substring(0, c.indexOf(" "))) && 
                pwd.equals(c.substring(c.indexOf(" ") + 1,c.lastIndexOf(" "))))
            return result = c.substring(c.lastIndexOf(" ") + 1);

        return result;
    }

    public static int menu()
    {
        int menuChoice;
        do
        { 
            System.out.print("\nPlease Choose From the Following Options:"
                    + "\n 1. Display Balance \n 2. Deposit"
                    + "\n 3. Withdraw\n 4. Log Out\n\n");

            menuChoice = wob.nextInt();

            if (menuChoice < 1 || menuChoice > 4){
                System.out.println("invalid Option, Plese choose correct option");
            }

        }while (menuChoice < 1 || menuChoice > 4);

        return menuChoice;
    }

    public static void displayBalance(double x)
    {
        System.out.printf("\nYour Current Balance is $%.2f\n", x);
    }

    public static double deposit(double x, double y)
    {
        double depositAmt = y, currentBal = x;
        double newBalance = depositAmt + currentBal;

        System.out.printf("Your New Balance is $%.2f\n",  newBalance);

        return newBalance;
    }
    /*
    public void transfer(AccountDetails from, AccountDetails to, double amount)
    {    
    	AccountDetails.withdraw(amount);    
    	AccountDetails.deposit(amount);    
    }
*/
    public static double withdraw(double x, double y)
    {
        double withdrawAmt = y, currentBal = x, newBalance;

        newBalance = currentBal - withdrawAmt;
        System.out.printf("Your New Balance is %.2f\n",newBalance);

        return newBalance;  
    }

    public static void main(String[] args) {

        String accNum, pass, origBal = "Error:";
        int count = 0, menuOption = 0;
        double depositAmt = 0, withdrawAmt = 0, currentBal=0; 
        boolean  foundNonDigit;
        //loop that will count the number of login attempts
        //you make and will exit program if it is more than 3.
        //as long as oriBal equals an error.  
        do{
            foundNonDigit = false;
            System.out.println("Please Enter Your 11 digit Account Number: ");
            accNum = wob.next();

            System.out.println("Enter Your 4 digit pin: ");
            pass = wob.next();

            origBal = checkID(accNum, pass);

            count++;

            if (count >= 3 && origBal.equals("Invalid Pin or Account Number")){
                System.out.print("SORRY :(\n But You Have Exceeded Maximum Login Ateempt.");
                System.exit(0);
            }
            if (!(origBal.equals("Invalid Pin or Account Number"))){
                System.out.println("\nYour New Balance is: $ "+ origBal);
            }
            else
                System.out.println(origBal);


        }while(origBal.equals("Invalid Pin or Account Number"));

        currentBal=Double.parseDouble(origBal);
        //this loop will keep track of the options that 
        //the user inputs in for the menu. and will 
        //give the option of deposit, withdraw, or logout.

        while (menuOption != 4)
        { 
            menuOption=menu();
            switch (menuOption)
            {
            case 1:
                displayBalance(currentBal);
                break;
            case 2:
                System.out.print("\nEnter Amount You Wish to Deposit: $ ");
                depositAmt = wob.nextDouble();
                currentBal=deposit(depositAmt, currentBal);
                break;
            case 3:
                System.out.print("\nEnter Amount You Wish to Withdrawl: $ ");
                withdrawAmt = wob.nextDouble();

                while(withdrawAmt>currentBal){
                    System.out.print("ERROR: INSUFFICIENT FUNDS!! "
                            + "PLEASE ENTER A DIFFERENT AMOUNT: $");
                    withdrawAmt = wob.nextDouble();
                }

                currentBal = withdraw(currentBal, withdrawAmt);
                break;
            case 4:
                System.out.print("\nThank For Using Altimatrik Online Banking.  Have a Nice Day.  Good-Bye!");
                System.exit(0);
                break;
            }
        }
    }
}