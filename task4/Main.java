package week1.task4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Customer c = new Customer(
                101,
                "Siva Kumar",
                28,
                45000.0,
                750,
                5000.0,
                "Salaried",
                200000.0
        );
        LoanRule isValidAge=customer -> customer.getAge()>=21 && customer.getAge()<=60;
        LoanRule isValidSalary=customer -> customer.getMonthlySalary()>=50000;
        LoanRule isValidCreditScore=customer -> customer.getCreditScore()>=750;
        LoanRule isValidEmi=customer -> customer.getExistingEmi()<(40/100.0)*customer.getMonthlySalary();
        LoanRule isValidLoanAmount=customer -> customer.getRequestedLoanAmount()<=customer.getMonthlySalary()*20;
        LoanRule isValidEmployment=customer -> customer.getEmploymentType().equalsIgnoreCase("salaried") || customer.getEmploymentType().equalsIgnoreCase("Business");
        List<String> reasons=new ArrayList<>();
        String status="";
        if (isValidAge.and(isValidEmi).and(isValidCreditScore).and(isValidSalary).and(isValidLoanAmount).and(isValidEmployment).validate(c)){
            reasons.add("All eligibility conditions satisfied");
            status="APPROVED";

        }else {
            status="REJECTED";
            if (!isValidAge.validate(c)) reasons.add("- Your age is not meet our Criteria ");
            if (!isValidSalary.validate(c)) reasons.add("- Your salary is meet our Criteria ");
            if (!isValidCreditScore.validate(c)) reasons.add("- Credit score is below 750 ");
            if (!isValidEmi.validate(c)) reasons.add("- Existing EMI is more than allowed limit ");
            if (!isValidLoanAmount.validate(c)) reasons.add("- Requested loan amount Exceed the Limit ");
            if (!isValidEmployment.validate(c)) reasons.add("- For Your employementType we cannot provide loans ");

        }
        System.out.println("Customer: "+c.getCustomerName());
        if (status.equalsIgnoreCase("approved")){
            System.out.println("Loan Status : "+status);
            System.out.println("Reason: "+reasons.getFirst());
        }else {
            System.out.println("Loan Status : "+status);
            System.out.println("Failed Rules: ");
            reasons.forEach(System.out::println);
        }

    }




}
