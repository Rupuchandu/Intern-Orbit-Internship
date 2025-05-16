import java.time.LocalDateTime;
import java.util.*;

class Transaction {
    String type;
    double amount;
    LocalDateTime dateTime;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return dateTime + " - " + type + ": $" + amount;
    }
}

class BankAccount {
    private String username;
    private String password;
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();

    public BankAccount(String username, String password, double initialDeposit) {
        this.username = username;
        this.password = password;
        this.balance = initialDeposit;
        transactions.add(new Transaction("Initial Deposit", initialDeposit));
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
        System.out.println("Deposited: $" + amount);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrew: $" + amount);
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }

    public void viewTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public void calculateInterest(double rate, int years) {
        double interest = (balance * rate * years) / 100;
        System.out.printf("Interest for %.2f%% over %d years = $%.2f%n", rate, years, interest);
    }
}

public class BankSystem {
    static Scanner scanner = new Scanner(System.in);
    static BankAccount account;

    public static void main(String[] args) {
        System.out.println("--- Bank Account Registration ---");
        System.out.print("Set a username: ");
        String user = scanner.nextLine();
        System.out.print("Set a password: ");
        String pass = scanner.nextLine();
        System.out.print("Initial deposit: ");
        double deposit = scanner.nextDouble();
        scanner.nextLine();

        account = new BankAccount(user, pass, deposit);
        System.out.println("Account created successfully!\n");

        // Login loop
        while (true) {
            System.out.println("--- Login ---");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (account.authenticate(username, password)) {
                System.out.println("Login successful!\n");
                break;
            } else {
                System.out.println("Invalid credentials. Try again.\n");
            }
        }

        // Main menu
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Calculate Interest");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("Enter amount to deposit: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    account.deposit(amount);
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    account.withdraw(amount);
                }
                case 3 -> account.checkBalance();
                case 4 -> account.viewTransactionHistory();
                case 5 -> {
                    System.out.print("Enter interest rate (%): ");
                    double rate = scanner.nextDouble();
                    System.out.print("Enter time (years): ");
                    int years = scanner.nextInt();
                    scanner.nextLine();
                    account.calculateInterest(rate, years);
                }
                case 6 -> {
                    System.out.println("Exiting... Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
