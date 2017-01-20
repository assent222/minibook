package pkk.interview.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Order {
    private final String orderId;
    private final OrderType orderType;
    private final BigDecimal price;
    private final BigInteger volume;
    private final long time;

    public Order(String orderId, OrderType orderType, BigDecimal price, BigInteger volume) {
        this(orderId, orderType, price, volume, System.currentTimeMillis());
    }

    public Order(String orderId, OrderType orderType, BigDecimal price, BigInteger volume, long time) {
        this.orderId = orderId;
        this.orderType = orderType;
        this.price = price;
        this.volume = volume;
        this.time = time;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigInteger getVolume() {
        return volume;
    }

    public long getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return orderId != null ? orderId.equals(order.orderId) : order.orderId == null;
    }

    @Override
    public int hashCode() {
        return orderId != null ? orderId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderType=" + orderType +
                ", price=" + price +
                ", volume=" + volume +
                ", time=" + time +
                '}';
    }
}
