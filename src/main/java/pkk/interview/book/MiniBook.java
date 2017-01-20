package pkk.interview.book;

import pkk.interview.domain.Order;
import pkk.interview.domain.OrderType;
import pkk.interview.domain.Quote;

import java.util.*;

public class MiniBook {

    private final Map<String, Order> orders;
    private final Set<Order> bids;
    private final Set<Order> offers;

    public MiniBook() {
        orders = new HashMap<>();
        bids = new TreeSet<>(new BidComparator());
        offers = new TreeSet<>(new OfferComparator());
    }

    static class BidComparator implements Comparator<Order> {
        @Override
        public int compare(Order a, Order b) {
            if (a == b) {
                return 0;
            }
            int res;
            //the best price is the highest price
            res = a.getPrice().compareTo(b.getPrice());
            if (res != 0) {
                return -res;
            }
            //from high to low
            res = a.getVolume().compareTo(b.getVolume());
            if (res != 0) {
                return -res;
            }
            //most recent to least recent
            res = Long.compare(a.getTime(), b.getTime());
            if (res != 0) {
                return -res;
            }
            return a.getOrderId().compareTo(b.getOrderId());
        }

    }

    static class OfferComparator implements Comparator<Order> {
        @Override
        public int compare(Order a, Order b) {
            if (a == b) {
                return 0;
            }
            int res;
            //the best price is the lowest price
            res = a.getPrice().compareTo(b.getPrice());
            if (res != 0) {
                return res;
            }
            //from high to low
            res = a.getVolume().compareTo(b.getVolume());
            if (res != 0) {
                return -res;
            }
            //most recent to least recent
            res = Long.compare(a.getTime(), b.getTime());
            if (res != 0) {
                return -res;
            }
            return a.getOrderId().compareTo(b.getOrderId());
        }
    }

    public synchronized void put(Quote quote) {
        switch (quote.getQuoteActionType()) {
            case NEW:
                putNEW(quote.getOrder());
                break;
            case UPDATE:
                putUPDATE(quote.getOrder());
                break;
            case DELETE:
                putDELETE(quote.getOrder());
                break;
        }
    }

    private void putNEW(Order order) {
        if (orders.putIfAbsent(order.getOrderId(), order) != null) {
            throw new IllegalStateException("Order " + order + " already exists");
        }
        if (order.getOrderType() == OrderType.BID) {
            bids.add(order);
        } else {
            offers.add(order);
        }
    }

    private void putUPDATE(Order order) {
        putDELETE(order);
        putNEW(order);
    }

    private void putDELETE(Order order) {
        Order oldOrder = orders.remove(order.getOrderId());
        if (oldOrder == null) {
            throw new IllegalStateException("Cant delete due to" + order + " is absent");
        }
        if (oldOrder.getOrderType() == OrderType.BID) {
            bids.remove(oldOrder);
        } else {
            offers.remove(oldOrder);
        }
    }

    public synchronized Set<Order> getBids() {
        return Collections.unmodifiableSet(bids);
    }

    public synchronized Set<Order> getOffers() {
        return Collections.unmodifiableSet(offers);
    }
}