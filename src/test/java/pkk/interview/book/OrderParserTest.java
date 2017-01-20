package pkk.interview.book;

import org.junit.Before;
import org.junit.Test;
import pkk.interview.domain.ActionType;
import pkk.interview.domain.Order;
import pkk.interview.domain.OrderType;
import pkk.interview.domain.Quote;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class OrderParserTest {

    private OrderParser orderParser;

    @Before
    public void setUp() throws Exception {
        orderParser = new OrderParser();
    }

    @Test
    public void parse() throws Exception {
        Quote expected = new Quote(ActionType.NEW,new Order("Q1", OrderType.OFFER, new BigDecimal("1.31"), new BigInteger("1000000")));
        String line = "Q1/O/N/1.31/1000000";

        Quote actual = orderParser.parse(line);

        assertEquals(expected.getQuoteActionType(), actual.getQuoteActionType());
        assertEquals(expected.getOrder().getOrderId(),actual.getOrder().getOrderId());
        assertEquals(expected.getOrder().getOrderType(),actual.getOrder().getOrderType());
        assertEquals(expected.getOrder().getPrice(),actual.getOrder().getPrice());
        assertEquals(expected.getOrder().getVolume(),actual.getOrder().getVolume());
    }
}