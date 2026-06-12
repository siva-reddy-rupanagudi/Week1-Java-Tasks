package week1.task3;

import java.util.Arrays;
import java.util.function.Consumer;

public class Main {
    static int gst = 18;

    public static void main(String[] args) {
        Order order = new Order("ORD101", "John Doe", Arrays.asList("Laptop", "Mouse"), 5000.0, "GENAI10"
        );
        double amount = order.getTotalAmount();

        OrderProcessor validateOrder = o -> {
            if (o.getTotalAmount() < 0 || o.getItems().isEmpty())
                throw new IllegalArgumentException("Invalid Order Details");
            return o;
        };

        OrderProcessor applyCoupon = o -> {
            double dicountAmount = 10 / 100.0 * amount;

            o.setTotalAmount(amount - dicountAmount);
            return o;
        };
        OrderProcessor calculateGst = o -> {
            double gstAmount = 0.18 * o.getTotalAmount();

            o.setTotalAmount(gstAmount + o.getTotalAmount());
            return o;
        };
        OrderProcessor confirmPayment = o -> {
            o.setPaymentStatus("Success");
            return o;
        };
        OrderProcessor assignDeliveryPartner = o -> o;
        OrderProcessor updateDeliveryStatus = o -> {
            if (o.getPaymentStatus().equalsIgnoreCase("success")) o.setDeliveryStatus("success");
            return o;
        };
        Order finalOrder = validateOrder
                .andThen(applyCoupon)
                .andThen(calculateGst)
                .andThen(confirmPayment)
                .andThen(assignDeliveryPartner)
                .andThen(updateDeliveryStatus)
                .process(order);
        Consumer<Order> con = o -> {
            System.out.println("Order ID: " + o.getOrderId());
            System.out.println("Original Amount: " + amount);
            System.out.println("Coupon Applied: " + o.getCouponCode());
            System.out.println("GST Added: " + gst + "%");
            System.out.println("Final Amount: " + o.getTotalAmount());
            System.out.println("Payment Status: " + o.getPaymentStatus());
            System.out.println("Delivery Status: " + o.getDeliveryStatus());
        };
        con.accept(order);


    }
}
