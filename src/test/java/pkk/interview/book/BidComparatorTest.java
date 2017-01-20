package pkk.interview.book;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pkk.interview.domain.Order;
import pkk.interview.domain.OrderType;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BidComparatorTest {
    private MiniBook.BidComparator comparator;

    @Before
    public void before() {
        comparator = new MiniBook.BidComparator();
    }

    @Test
    public void compareSymmetry() throws Exception {
        Order a, b;

        //a == b
        a = new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000);
        b = new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000);

        Assert.assertEquals(comparator.compare(a, b), comparator.compare(b, a));
    }


    @Test
    public void compareAnticommutation() throws Exception {
        Order a, b;

        //a == b
        a = new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000);
        b = new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000);
        Assert.assertEquals(comparator.compare(a, b), -comparator.compare(b, a));

        //a > b
        a = new Order("Q1", OrderType.BID, new BigDecimal("11.123"), new BigInteger("1000"), 1000);
        b = new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000);
        Assert.assertEquals(comparator.compare(a, b), -comparator.compare(b, a));

        //a < b
        a = new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000);
        b = new Order("Q1", OrderType.BID, new BigDecimal("11.123"), new BigInteger("1000"), 1000);
        Assert.assertEquals(comparator.compare(a, b), -comparator.compare(b, a));
    }

    @Test
    public void compareTransitive() throws Exception {
        Order a, b, c;

        //a < b < c
        a = new Order("Q1", OrderType.BID, new BigDecimal("111.123"), new BigInteger("1000"), 1000);
        b = new Order("Q1", OrderType.BID, new BigDecimal("11.123"), new BigInteger("1000"), 1000);
        c = new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000);

        Assert.assertTrue(comparator.compare(a, b) < 0 && comparator.compare(b, c) < 0 && comparator.compare(a, c) < 0);
        Assert.assertTrue(comparator.compare(c, b) > 0 && comparator.compare(b, a) > 0 && comparator.compare(c, a) > 0);

        //a == b == c
        a = new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000);
        b = new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000);
        c = new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000);

        Assert.assertTrue(comparator.compare(c, b) == 0 && comparator.compare(b, a) == 0 && comparator.compare(c, a) == 0);
    }

    @Test
    public void compareConsistency() throws Exception {
        Order a, b;

        //a == b
        a = new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000);
        b = new Order("Q1", OrderType.BID, new BigDecimal("1.123"), new BigInteger("1000"), 1000);

        Assert.assertTrue(comparator.compare(a, b) == 0 && a.equals(b));
    }
}