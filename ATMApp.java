import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            System.out.println("Cannot initialize with negative balance. Setting to 0.");
            this.balance = 0;
        } else {
            this.balance = initialBalance;
        }
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Amount to deposit must be positive!");
        } else {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Amount to withdraw must be positive!");
            return false;
        } else if (amount > balance) {
            System.out.println("Insufficient balance for withdrawal.");
            return false;
        } else {
            balance -= amount;
            System.out.println("Successfully withdraw: $" + amount);
            return true;
        }
    }

    public double getBalance() {
        return balance;
    }
}

class ATMSystem {
    private BankAccount account;
    private Scanner scanner;

    public ATMSystem(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n------ ATM Menu ------");
            System.out.println("1. Withdraw Money");
            System.out.println("2. Deposit Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleWithdraw();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleBalanceCheck();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting ATM. Have a nice day!");
                    break;
                default:
                    System.out.println("Invalid option! Please select a valid option (1-4).");
            }
        }
    }

    private void handleWithdraw() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        boolean success = account.withdraw(amount);
        if (success) {
            System.out.println("Transaction successful.");
        } else {
            System.out.println("Transaction failed.");
        }
    }

    private void handleDeposit() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Transaction successful.");
    }

    private void handleBalanceCheck() {
        System.out.println("Current balance: $" + account.getBalance());
    }
}

public class ATMApp {
    public static void main(String[] args) {

        BankAccount userAccount = new BankAccount(1000.00);

        ATMSystem atmSystem = new ATMSystem(userAccount);

        atmSystem.showMenu();
    }
}
