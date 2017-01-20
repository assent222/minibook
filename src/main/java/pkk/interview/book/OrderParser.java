package pkk.interview.book;

import pkk.interview.domain.Order;
import pkk.interview.domain.Quote;
import pkk.interview.domain.ActionType;
import pkk.interview.domain.OrderType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class OrderParser {

    private Validator validator;
    private final String delimiter;

    public OrderParser() {
        this("/");
    }

    public OrderParser(String delimiter) {
        this.validator = new Validator();
        this.delimiter = delimiter;
    }

    public Quote parse(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        if (!validator.validate(str)) {
            throw new IllegalArgumentException("arg=" + str + "not pass " + validator.pattern.toString());
        }
        StringTokenizer tokenizer = new StringTokenizer(str, delimiter);

        String quoteID = tokenizer.nextToken();
        OrderType quoteType = tokenizer.nextToken().equals("B") ? OrderType.BID : OrderType.OFFER;
        String tmp = tokenizer.nextToken();
        ActionType actionType = tmp.equals("N") ? ActionType.NEW :
                tmp.equals("U") ? ActionType.UPDATE : ActionType.DELETE;
        BigDecimal price = new BigDecimal(tokenizer.nextToken());
        BigInteger volume = new BigInteger(tokenizer.nextToken());

        Quote quote = new Quote();
        quote.setQuoteActionType(actionType);
        quote.setOrder(new Order(quoteID, quoteType, price, volume));

        return quote;
    }

    static class Validator {
        private final Pattern pattern = Pattern.compile("\\w+/[B|O]/[N|U|D]/\\d+\\.?\\d*/(0|[1-9]\\d*)");

        boolean validate(String str) {
            return pattern.matcher(str).matches();
        }
    }
}
