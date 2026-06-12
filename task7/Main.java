package week1.task7;

import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        PaymentResponse pr = new PaymentResponse();
        PaymentRequest request = new PaymentRequest(
                "PAY12345",
                "Siva",
                25000.0,
                "Wallet",
                "COURSE10",
                "HDFC Bank",
                "Paytm Wallet"
        );

        double originalAmount = request.getAmount();

        Predicate<PaymentRequest> isValidAmount = r -> r.getAmount() > 0;
        Predicate<PaymentRequest> isValidCouponCode = r -> r.getCouponCode().equalsIgnoreCase("COURSE10");
        Predicate<PaymentRequest> isValidCustomer = r -> r.getCustomerName() != null && !r.getCustomerName().trim().isEmpty();
        Predicate<PaymentRequest> isValidBank = r -> r.getBankName() != null && !r.getBankName().trim().isEmpty();
        Predicate<PaymentRequest> isValidWallet = r -> r.getWalletName() != null && !r.getWalletName().trim().isEmpty();
        BiFunction<PaymentRequest, Double, PaymentRequest> addGateWayCharges = (r, rate) -> {
            double charge = r.getAmount() * (rate / 100.0);
            r.setAmount(r.getAmount() + charge);
            return r;
        };

        Function<PaymentRequest, PaymentRequest> applyingCoupon = r -> {
            if (isValidCouponCode.test(r)) {
                r.setAmount(r.getAmount() - r.getAmount() * 0.1);
            }
            return r;
        };

        Supplier<String> transactionIdGen = () -> {
            StringBuilder id = new StringBuilder("TXN");
            for (int i = 0; i < 12; i++) id.append((int) (Math.random() * 10));
            return id.toString();
        };

        BiConsumer<PaymentRequest, PaymentResponse> display = (r, response) -> {
            System.out.println("Payment Mode: " + r.getPaymentMode());
            System.out.println("Original Amount: " + originalAmount);
            if (isValidCouponCode.test(r)) System.out.println("Coupon Applied: " + r.getCouponCode());
            else System.out.println("Invalid CouponCode");
            System.out.println("Final Amount: " + response.getFinalAmount());
            System.out.println("Transaction ID: " + response.getTransactionId());
            System.out.println("Payment Status: " + response.getPaymentStatus());
        };

        PaymentGateway upi = r -> processPayment(r, pr, 0.0, isValidCustomer.and(isValidAmount).and(isValidBank), applyingCoupon, addGateWayCharges, transactionIdGen);
        PaymentGateway creditCard = r -> processPayment(r, pr, 3.0, isValidCustomer.and(isValidAmount).and(isValidBank), applyingCoupon, addGateWayCharges, transactionIdGen);
        PaymentGateway netBanking = r -> processPayment(r, pr, 2.0, isValidCustomer.and(isValidAmount).and(isValidBank), applyingCoupon, addGateWayCharges, transactionIdGen);
        PaymentGateway wallet = r -> processPayment(r, pr, 1.5, isValidCustomer.and(isValidAmount).and(isValidWallet), applyingCoupon, addGateWayCharges, transactionIdGen);

        PaymentResponse pres = null;
        switch (request.getPaymentMode().toLowerCase()) {
            case "upi":
                pres = upi.pay(request);
                break;
            case "creditcard":
                pres = creditCard.pay(request);
                break;
            case "netbanking":
                pres = netBanking.pay(request);
                break;
            case "wallet":
                pres = wallet.pay(request);
                break;
            default:
                System.out.println("Unsupported Payment Mode");
        }

        if (pres != null) display.accept(request, pres);
    }

    private static PaymentResponse processPayment(PaymentRequest r, PaymentResponse pr,
                                                  double gatewayRate,
                                                  Predicate<PaymentRequest> validation,
                                                  Function<PaymentRequest, PaymentRequest> couponFn,
                                                  BiFunction<PaymentRequest, Double, PaymentRequest> gatewayFn,
                                                  Supplier<String> txnGen) {
        String txnId = txnGen.get();
        if (validation.test(r)) {
            gatewayFn.apply(r, gatewayRate);
            couponFn.apply(r);
            pr.setPaymentStatus("Success");
            pr.setTransactionId(txnId);
            pr.setFinalAmount(r.getAmount());
            pr.setMessage("Payment Successful");
        } else {
            pr.setPaymentStatus("Failed");
            pr.setTransactionId(txnId);
            pr.setMessage("Transaction Failed due to invalid details");
        }
        return pr;
    }
}
