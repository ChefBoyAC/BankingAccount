import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
public class main {
	public static void main(String[] args){

		        //ask for password 
		        System.out.println("Welcome to Aaron's Banking System");
		        System.out.print("Enter the pin that logs into your bank account: ");
		        Scanner pin = new Scanner(System.in);
		        Scanner bankAccountInfo = new Scanner(System.in);
		        int num;
		        while(pin.hasNextInt()) {
		        	num = pin.nextInt();
		        	if(num == 1234) {
		        		System.out.println("Correct password!");
		        		break;
		        	}
		        	else {
		        		System.out.print("Incorrect password! Try again: ");
		        	}
		        }
		        BankAccount firstAcc = new BankAccount("account1",1234);
		        
		        
		        //choose options 
		        char option;
		        Scanner choose = new Scanner(System.in);
		        System.out.println("\n");
		        System.out.println("a] Check Balance");
		        System.out.println("b] Deposit Amount");
		        System.out.println("c] Withdraw Amount");
		        System.out.println("d] Previous Transaction");
		        System.out.println("e] Monthly/Total Payment");
		        System.out.println("f] Exit");
		        
		        do {
		        	System.out.println("Choose an option");
		        	option = (char) choose.next().charAt(0);
		        	System.out.println("\n");
		        	
		        	switch(option) {
		        	case 'a':
		        		System.out.println("Balance = " + firstAcc.balance);
		        		System.out.println(".............................");
		        		System.out.println("\n");
		        		break;
		        	case 'b':
		        		System.out.println("Enter a amount to deposit: ");
		        		System.out.println("...........................");
		        		double amountD = bankAccountInfo.nextDouble();
		        		firstAcc.deposit(amountD);
		        		break;
		        		
		        	case 'c':
		        		System.out.println("Enter a amount to Withdraw: ");
		        		System.out.println("............................");
		        		double amountW = bankAccountInfo.nextDouble();
		        		firstAcc.withdraw(amountW);
		        		System.out.println();
		        		break;
		        	case 'd':
		        		System.out.println("Previous Transaction: ");
		        		firstAcc.getPreviousTrans();
		        		System.out.println("...................");
		        		System.out.println("\n");
		        	case 'e':
		        		//Ask for annualInterestRate, monthlyInterestRate, numberOfYears, and loanAmount 
		        		
		        		System.out.println("Please enter your interest rate");
				        double annualInterestRate = bankAccountInfo.nextDouble();
				        
				        System.out.println("Please enter the repayment years");
				        int numberOfYears = bankAccountInfo.nextInt();
				        
				        System.out.println("Please enter the amount of your loan");
				        double loanAmount = bankAccountInfo.nextDouble();
			        
					
			        	firstAcc.interestRate(annualInterestRate,numberOfYears,loanAmount);
			        	break;
		        	case 'f':
		        		System.out.println("Thank you so much for checking out my code");
		        		break;
		        	default:
		        		System.out.println("Please choose the right option");
		        		break;
		        	}
		        } while(option != 'f');
		        
	}

}
class BankAccount {
	double balance;
	double prevTrans;
	String customerName;
	int customerPassword;
	BankAccount(String customerName, int customerPassword){
		this.customerName = customerName;
		this.customerPassword = customerPassword;
	}
	public void deposit(double amount) {
		if(amount != 0) {
			balance += amount;
			prevTrans = amount;
		}
	}
	public void interestRate(double annualInterestRate, int numberOfYears, double loanAmount) {
		//calculate monthlyPayment and totaPayment 
        
        double monthlyInterestRate = annualInterestRate / 1200;
        
        double monthlyPayment = loanAmount * monthlyInterestRate / (1-1/Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
        if (Double.isInfinite(monthlyPayment)) { monthlyPayment = 0;}
        
        double totalPayment = monthlyPayment * numberOfYears * 12;
        if (Double.isInfinite(totalPayment)) { totalPayment = 0;}
        
        
        //print a receipt
        
        int refs = 1325 + (int)(Math.random() * 4238);
        
        //get the time and date of month 
        //fix time zone 
        Calendar timer = Calendar.getInstance();
        timer.getTime();
        SimpleDateFormat tTime = new SimpleDateFormat("HH:mm:ss");
        tTime.format(timer.getTime());
        
        
        //Text receipt with all of the necessary banking requirements 
        System.out.println("\tLoan Management Systems \n" + "Reference:\t\t\t" +
        					refs + "\n=================================\t " + "\nInterest Rate:\t\t\t "+
        					annualInterestRate + "\nRepayment years:\t\t "+ numberOfYears + "\nAmount of Loan: \t\t\t " +
        					"$"+ loanAmount + "\nMonthly payment:\t\t "+ monthlyPayment+ "\nTotal payment: \t\t\t"+ 
        					totalPayment + "\n===============================\t " + "\nDate: "+ java.time.LocalDate.now() +
        					"\t\tTime: "+ tTime.format(timer.getTime())+ "\n\n\t\t Thank you\n");
        
		
        
	}
	public void withdraw(double amount) {
		if(amount != 0 && balance >= amount) {
			balance -= amount;
			prevTrans = -(amount);
		} else if(balance < amount) {
			System.out.println("Bank balance insufficient");
		}
	}
	public void getPreviousTrans() {
		if(prevTrans > 0) {
			System.out.println("Deposited: "+ prevTrans);
		} else if(prevTrans < 0) {
			System.out.println("Withdrawn: "+ Math.abs(prevTrans));
		} else {
			System.out.println("No transaction occured");
		}
	}

	
	
}
