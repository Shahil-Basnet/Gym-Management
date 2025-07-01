/**
 * Write a description of class doc here.
 *
 * @author Shahil Basnet
 * @version 1
 */
public class RegularMember extends GymMember{
    
    private final int ATTENDANCE_LIMIT;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;
    
    RegularMember(int id,String name,String location,String phone,String email,String gender,String DOB,String membershipStartDate,String referralSource){
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.ATTENDANCE_LIMIT = 30;//the member can only attend the gym for this many days
        this.isEligibleForUpgrade = false;
        this.plan = "BASIC";
        this.price = 6500;
        this.referralSource = referralSource;
    }
    public boolean getIsEligibleForUpgrade(){
        return this.isEligibleForUpgrade;
    }
    @Override
    public void markAttendance(){
        if(this.attendance == this.ATTENDANCE_LIMIT){
            this.isEligibleForUpgrade = true;
            }
        this.attendance += 1;
        this.loyaltyPoints += 5;
    }
    
    public String getReferralSource(){
        return this.referralSource;
    } 
    public String getPlan(){
        return this.plan;
    }
    public double getPrice(){
        return this.price;
    }
    //this method returns the price of the selected plan using switch case
    public static double getPlanPrice(String plan){
        switch(plan.toUpperCase()){//converting to uppercase to make it case insensitive
            
            case "BASIC":
                return 6500;
            case "STANDARD":
                return 12500;
            case "DELUXE":
                return 18500;
            default:
                return -1;
        }
    }
    public String upgradePlan(String plan){
        if(!this.isEligibleForUpgrade){
            return "The member is not eligible for upgrade!(Reason:Attendance requirement not met.)";
        }
        this.plan = plan;
        //if the above condition is met the program goes to check next condition(s)
        if(this.plan.equalsIgnoreCase(plan)){// if the same plan it selected, it informs the user
            return "This plan \""+this.plan+"\" is already selected.\n";
        }
        else if(getPlanPrice(plan) == -1){//if invalid plan is selected it informs the user and shows available plans
            return "Invalid plan! Please enter a valid plan(Basic/Standard/Deluxe)";
        }
        this.plan = plan;
        this.price = getPlanPrice(plan);
        return "Congratulation you have been upgraded to \""+this.plan+"\"!\n";
    }
    // this method resets overall 
    public void revertRegularMember(String removalReason){
        super.resetMember();
        isEligibleForUpgrade = false;
        this.plan = "Basic";
        this.price = 6500;
        this.removalReason = removalReason;
        System.out.println("Member reverted successfully.");
    }
    
    @Override
    public void display(){
        super.display();
        System.out.println("Plan:"+this.plan+" Price:"+this.price);
        if(this.removalReason != null){
            System.out.println("Reason for removal:"+this.removalReason);
        }
    }
}