/**
 * Write a description of class doc here.
 *
 * @author Shahil Basnet
 * @version 1
 */
public class PremiumMember extends GymMember{
    private final double PREMIUM_CHARGE;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;
    
    PremiumMember(int id,String name,String location,String phone,String email,String gender,String DOB,String membershipStartDate,String personalTrainer){
        super(id, name, location, phone, email, gender,DOB, membershipStartDate);
        PREMIUM_CHARGE = 50000;
        paidAmount = 0.0;
        this.personalTrainer = personalTrainer;
        isFullPayment = false;
        discountAmount = 0.0;
    }
    //implementing the markAttendance() method from parent GymMember
    @Override
    public void markAttendance(){
        this.attendance++;
        this.loyaltyPoints += 10;
    }
    // getter methods
    public double getPremiumCharge(){
        return this.PREMIUM_CHARGE;
    }
    public double getPaidAmount(){
        return this.paidAmount;
    }
    public double getDiscountAmount(){
        return this.discountAmount;
    }
    public String getPersonalTrainer(){
        return this.personalTrainer;
    }
    public boolean getIsFullPayment(){
        return this.isFullPayment;
        }
        
    /*This method is used to pay charges by a member
     * paidAmount = amount paid by user
     */
    public String payDueAmount(double paidAmount){
        if(isFullPayment){//
            return "No charges remaining.";
        }
        if(paidAmount <= 0){
            return "Invalid payment amount.";
        }
        this.paidAmount += paidAmount;
        if(this.paidAmount > this.PREMIUM_CHARGE){
            this.paidAmount -= paidAmount;
            return "Paid amount exceeds premium charge.(Premium charge = "+PREMIUM_CHARGE + ")";
        }
        
        double remainingAmount = this.PREMIUM_CHARGE - this.paidAmount;
        if(remainingAmount == 0.0){
            isFullPayment = true;
            return "Full amount paid. Thank you!" +this.name;
        }
        return paidAmount +" paid. Total paid: "+this.paidAmount+" Remaining: "+remainingAmount;
    }

    public void calculateDiscount(){
        if(isFullPayment){
            discountAmount = 0.1 * PREMIUM_CHARGE;
        }
        else{
            throw new RuntimeException("Discount can only be calculated when full payment is made.");
        }
    }

    public void revertPremiumMember(String removalReason){
        super.resetMember();
        this.isFullPayment = false;
        this.paidAmount = 0.0;
        this.discountAmount = 0.0;
        this.personalTrainer = null;
    }
    @Override
    public void display(){
        super.display();
        System.out.println("Personal trainer: "+personalTrainer);
        System.out.println("Total paid amount: "+paidAmount);
        if(isFullPayment){
            System.out.println("No charges remaining.\n Discount amount: "+discountAmount);
        }
        else{
            double remainingAmount = PREMIUM_CHARGE -paidAmount;
            System.out.println("Amount remaining to be paid: "+remainingAmount);
        }
    }
}