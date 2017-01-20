package pkk.interview;

import pkk.interview.book.MiniBook;
import pkk.interview.book.OrderParser;
import pkk.interview.domain.Order;
import pkk.interview.domain.Quote;

import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/quotes.txt");
        System.out.println(file.getAbsolutePath());
        OrderParser parser = new OrderParser();
        MiniBook miniBook = new MiniBook();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        System.out.println("READ INPUT");
        while((line = br.readLine()) != null){
            Quote quote = parser.parse(line);
            System.out.println(quote);
            miniBook.put(quote);
        }

        System.out.println("OFFER");
        for (Order order : miniBook.getOffers()) {
            System.out.println(order);
        }

        System.out.println("BID");
        for (Order order : miniBook.getBids()) {
            System.out.println(order);
        }
    }
}
