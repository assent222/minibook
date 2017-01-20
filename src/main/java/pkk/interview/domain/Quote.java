package pkk.interview.domain;

public class Quote {

    private ActionType quoteActionType;
    private Order order;

    public Quote() {
    }

    public Quote(ActionType quoteActionType, Order order) {
        this.quoteActionType = quoteActionType;
        this.order = order;
    }

    public ActionType getQuoteActionType() {
        return quoteActionType;
    }

    public void setQuoteActionType(ActionType quoteActionType) {
        this.quoteActionType = quoteActionType;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quoteActionType=" + quoteActionType +
                ", order=" + order +
                '}';
    }
}
