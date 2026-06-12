package week1.task6;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        NotificationSender emailSender = notification -> System.out.println("Email sent to :" + notification.getEmail());
        NotificationSender smsSender = notification -> System.out.println("SMS sent to : " + notification.getMobile());
        NotificationSender whatsappSender = notification -> System.out.println("WhatsApp Sent to " + notification.getMobile());
        NotificationSender pushSender = notification -> System.out.println("Push Notification Sent to " + notification.getUserName());
        Map<String, NotificationSender> senders = new HashMap<>();
        senders.put("EMAIL", emailSender);
        senders.put("SMS", smsSender);
        senders.put("WHATSAPP", whatsappSender);
        senders.put("PUSH", pushSender);
        Notifications n = new Notifications(
                "N001", "Ravi", "ravi@gmail.com", "9876543210", "Your class starts at 7:30 AM", "WHATSAPP", "HIGH"
        );
        System.out.println("Sending "+n.getPriority()+" priority notifications...");
        boolean isHighPriority= n.getPriority().equalsIgnoreCase("HIGH");
        String type=n.getNotificationType().toUpperCase();
        if(isHighPriority){
            if(isValid(n.getEmail())) senders.get("EMAIL").send(n);
            else System.err.println("Email not Provided");
            if(isValid(n.getMobile())) senders.get("WHATSAPP").send(n);
        }else{
            boolean send=true;
            if(("SMS".equalsIgnoreCase(type) || "WHATSAPP".equalsIgnoreCase(type))&& !isValid(n.getMobile())) send=false;
            else if("EMAIL".equalsIgnoreCase(type) && !isValid(n.getEmail())) send=false;

            if(send && senders.containsKey(type)) senders.get(type).send(n);
            else System.out.println("Skipped: Missing contact info or invalid channel.");
        }
        System.out.println("Message: "+n.getMessage());

    }
    public static boolean isValid(String data){
        return  data!=null && !data.trim().isEmpty();
    }
}
