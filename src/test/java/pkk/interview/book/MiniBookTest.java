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

public class MiniBookTest {

    private MiniBook miniBook;

    @Before
    public void setUp() throws Exception {
        miniBook = new MiniBook();
    }

    @Test
    public void put() throws Exception {
        Quote quote;
        quote = new Quote(ActionType.NEW, new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000));
        miniBook.put(quote);
        assertEquals(1, miniBook.getBids().size());
        assertEquals(quote.getOrder(), miniBook.getBids().toArray()[0]);
//        System.out.println(miniBook.getBids().toArray()[0]);

        quote = new Quote(ActionType.UPDATE, new Order("Q1", OrderType.BID, new BigDecimal("111.123"), new BigInteger("2000"), 1000));
        miniBook.put(quote);
        assertEquals(1, miniBook.getBids().size());
        assertEquals(quote.getOrder(), miniBook.getBids().toArray()[0]);
//        System.out.println(miniBook.getBids().toArray()[0]);

        quote = new Quote(ActionType.DELETE, new Order("Q1", OrderType.BID, new BigDecimal("0"), new BigInteger("0"), 1000));
        miniBook.put(quote);
        assertTrue(miniBook.getBids().isEmpty());
    }


    @Test(expected = IllegalStateException.class)
    public void put_exception_0() throws Exception {
        Quote quote;
        quote = new Quote(ActionType.NEW, new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000));
        miniBook.put(quote);
        miniBook.put(quote);
    }

    @Test(expected = IllegalStateException.class)
    public void put_exception_1_0() throws Exception {
        Quote q1 = new Quote(ActionType.UPDATE, new Order("Q1", OrderType.BID, new BigDecimal("111.123"), new BigInteger("1000"), 1000));
        miniBook.put(q1);
    }

    @Test(expected = IllegalStateException.class)
    public void put_exception_1_1() throws Exception {
        Quote q1 = new Quote(ActionType.NEW, new Order("Q1", OrderType.BID, new BigDecimal("111.123"), new BigInteger("1000"), 1000));
        Quote q2 = new Quote(ActionType.UPDATE, new Order("Q2", OrderType.BID, new BigDecimal("11.123"), new BigInteger("1000"), 1000));
        miniBook.put(q1);
        miniBook.put(q2);
    }

    @Test(expected = IllegalStateException.class)
    public void put_exception_2_0() throws Exception {
        Quote q1 = new Quote(ActionType.DELETE, new Order("Q1", OrderType.BID, new BigDecimal("111.123"), new BigInteger("1000"), 1000));
        miniBook.put(q1);
    }

    @Test(expected = IllegalStateException.class)
    public void put_exception_2_1() throws Exception {
        Quote q1 = new Quote(ActionType.NEW, new Order("Q1", OrderType.BID, new BigDecimal("111.123"), new BigInteger("1000"), 1000));
        Quote q2 = new Quote(ActionType.DELETE, new Order("Q2", OrderType.BID, new BigDecimal("11.123"), new BigInteger("1000"), 1000));
        miniBook.put(q1);
        miniBook.put(q2);
    }


    @Test
    public void getBids() {
        Quote q1 = new Quote(ActionType.NEW, new Order("Q1", OrderType.BID, new BigDecimal("111.123"), new BigInteger("1000"), 1000));
        Quote q2 = new Quote(ActionType.NEW, new Order("Q2", OrderType.BID, new BigDecimal("11.123"), new BigInteger("1000"), 1000));
        Quote q3 = new Quote(ActionType.NEW, new Order("Q3", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000));
        Quote q4 = new Quote(ActionType.NEW, new Order("Q4", OrderType.BID, new BigDecimal("1.123"), new BigInteger("500"), 1000));
        Quote q5 = new Quote(ActionType.NEW, new Order("Q5", OrderType.BID, new BigDecimal("1.123"), new BigInteger("500"), 500));
        miniBook.put(q3);
        miniBook.put(q1);
        miniBook.put(q5);
        miniBook.put(q4);
        miniBook.put(q2);

        assertEquals(5, miniBook.getBids().size());

        assertArrayEquals(new Order[]{
                q1.getOrder(),
                q2.getOrder(),
                q3.getOrder(),
                q4.getOrder(),
                q5.getOrder(),
        }, miniBook.getBids().toArray());
    }

    @Test
    public void getOffers() {
        Quote q1 = new Quote(ActionType.NEW, new Order("Q1", OrderType.OFFER, new BigDecimal("1.123"), new BigInteger("1000"), 1000));
        Quote q2 = new Quote(ActionType.NEW, new Order("Q2", OrderType.OFFER, new BigDecimal("11.123"), new BigInteger("1000"), 1000));
        Quote q3 = new Quote(ActionType.NEW, new Order("Q3", OrderType.OFFER, new BigDecimal("111.123"), new BigInteger("1000"), 1000));
        Quote q4 = new Quote(ActionType.NEW, new Order("Q4", OrderType.OFFER, new BigDecimal("111.123"), new BigInteger("500"), 1000));
        Quote q5 = new Quote(ActionType.NEW, new Order("Q5", OrderType.OFFER, new BigDecimal("111.123"), new BigInteger("500"), 500));
        miniBook.put(q3);
        miniBook.put(q1);
        miniBook.put(q5);
        miniBook.put(q4);
        miniBook.put(q2);

        assertEquals(5, miniBook.getOffers().size());

        assertArrayEquals(new Order[]{
                q1.getOrder(),
                q2.getOrder(),
                q3.getOrder(),
                q4.getOrder(),
                q5.getOrder(),
        }, miniBook.getOffers().toArray());
    }
}