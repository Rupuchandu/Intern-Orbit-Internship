import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        // Sample exchange rates based on 1 USD
        Map<String, Double> exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.00);
        exchangeRates.put("EUR", 0.92);
        exchangeRates.put("INR", 83.45);
        exchangeRates.put("GBP", 0.79);
        exchangeRates.put("JPY", 155.28);
        exchangeRates.put("AUD", 1.50);
        exchangeRates.put("CAD", 1.36);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        if (!exchangeRates.containsKey(baseCurrency) || !exchangeRates.containsKey(targetCurrency)) {
            System.out.println("Currency not supported.");
        } else {
            double baseToUSD = 1 / exchangeRates.get(baseCurrency);
            double usdToTarget = exchangeRates.get(targetCurrency);
            double convertedAmount = amount * baseToUSD * usdToTarget;

            System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
        }

        scanner.close();
    }
}
