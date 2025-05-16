import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public String deposit(double amount) {
        if (amount <= 0) {
            return "Deposit amount must be positive.";
        }
        balance += amount;
        return "Successfully deposited $" + amount + ". New balance: $" + balance;
    }

    public String withdraw(double amount) {
        if (amount <= 0) {
            return "Withdrawal amount must be positive.";
        }
        if (amount > balance) {
            return "Insufficient balance for this withdrawal.";
        }
        balance -= amount;
        return "Successfully withdrew $" + amount + ". New balance: $" + balance;
    }

    public String checkBalance() {
        return "Current balance: $" + balance;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n----- ATM MENU -----");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Choose an option (1-4): ");
    }

    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(account.checkBalance());
                    break;
                case "2":
                    System.out.print("Enter amount to deposit: ");
                    try {
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        System.out.println(account.deposit(depositAmount));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;
                case "3":
                    System.out.print("Enter amount to withdraw: ");
                    try {
                        double withdrawAmount = Double.parseDouble(scanner.nextLine());
                        System.out.println(account.withdraw(withdrawAmount));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;
                case "4":
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose between 1 and 4.");
            }
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(500.0); // Starting balance
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}
