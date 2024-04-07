import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PuttingIntoPractice {
    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

    }

    // Find all transactions for the year 2011 and sort them by amount
    public static List<Transaction> findTransaction(List<Transaction> transactions) {
        return transactions.stream()
                .filter((Transaction transaction) -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getYear))
                .toList();
    }

    //  Show a list of unique cities in which traders work.
    public static List<String> getUniqueCities(List<Transaction> transactions) {
        return transactions.stream()
                .map((Transaction transaction) -> transaction.getTrader().getCity())
                .distinct()
                .toList();
    }

    // Find all the traders from Cambridge and sort them by name.
    public static List<Trader> getTraders(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .toList();
    }

    //Return a string with all trader names sorted in alphabetical order.
    public static String getTradersNames(List<Transaction> transactions) {
        return transactions.stream()
                .map((Transaction transaction) -> transaction.getTrader().getName())
                .sorted(String::compareTo)
                .collect(Collectors.joining(","));

    }

    // Find out if there’s any traders from Milan.
    public static Trader getTraderFromMilan(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter((Trader trader) -> trader.getCity().equals("Milan"))
                .findAny()
                .orElse(null);
    }

    // Withdraw all transactions from Cambridge
    public static int withDrawTransactions(List<Transaction> transactions) {
        return transactions.stream()
                .filter((Transaction transaction) -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum)
                .get();
    }

    //  What is the maximum amount of all transactions?
    public static int getMaxValue(List<Transaction> transactions) {
        return transactions.stream()
                .max(Comparator.comparing(Transaction::getValue))
                .get().getValue();
    }

    //   What is the minimum amount of all transactions?
    public static int getMinValue(List<Transaction> transactions) {
        return transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .get().getValue();
    }
}