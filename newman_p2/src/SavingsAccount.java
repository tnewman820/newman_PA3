//Application used for testing
class Application {
    //main method used to test  savings account class
    public static void main(String[] args){
        SavingsAccount saver1 = new SavingsAccount(2000.00);
        SavingsAccount saver2 = new SavingsAccount(3000.00);
        //set initial int rate at 4%
        SavingsAccount.modifyInterestRate(0.04);
        //calc monthly int for 12 months, set new balances and print new balances
        System.out.println("-Month----------Saver 1----------Saver 2----Interest Rate-");
        for(int i = 1; i < 13; i++){
            saver1.calculateMonthlyInterest();
            saver2.calculateMonthlyInterest();
            if(i<=9) {
                System.out.printf("  %d            $%.02f         $%.02f        %.02f%%\n", i, saver1.getSavingBalance(), saver2.getSavingBalance(), (saver1.getInterestRate()*100.0));
            }
            else{
                System.out.printf("  %d           $%.02f         $%.02f        %.02f%%\n", i, saver1.getSavingBalance(), saver2.getSavingBalance(), (saver1.getInterestRate()*100.0));
            }
        }
        //set int rate to 5%
        SavingsAccount.modifyInterestRate(0.05);
        //calc next months int and print new balances
        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();
        System.out.printf("  13           $%.02f         $%.02f        %.02f%%\n", saver1.getSavingBalance(), saver2.getSavingBalance(),(saver1.getInterestRate()*100.0));
        System.out.println("");
    }
}
public class SavingsAccount {
    static private double annualInterestRate;
    private double savingsBalance;
    public SavingsAccount(double savingsBalance){
        this.savingsBalance = savingsBalance;
    }
    public double getSavingBalance(){
        return this.savingsBalance;
    }
    public double getInterestRate(){
        return annualInterestRate;
    }
    public void calculateMonthlyInterest(){
        double currentMonth;
        currentMonth = (double)(this.savingsBalance*annualInterestRate/12);
        this.savingsBalance += currentMonth;
    }
    public static void modifyInterestRate(double interestRate){
        annualInterestRate = interestRate;
    }

}
