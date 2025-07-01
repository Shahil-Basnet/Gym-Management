/**
 * Write a description of class doc here.
 *
 * @author Shahil Basnet
 * @version 1
 */
public abstract class GymMember{
    //attributes of the gymmember class
    protected int id;
    protected int attendance;
    //personal details of member
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    //dates associated with member
    protected String DOB;
    protected String membershipStartDate;
    //members details associated with gym
    protected double loyaltyPoints;
    protected boolean activeStatus;
    
    /*Constructor for class GymMember --> initializes the values of members
     * id = unique id given to each member
     * name : name of the member
     * location = location of member
     * phone = contact number of member
     * email = email of member
     * DOB = Date of Birth of member
     * membershipStartDate = date when the member joined the gym
     *loyaltyPoints = points accumulated by the member by attending the gym
     * activeStatus = membership status
     * attendance = days of attendance
     */
    GymMember(int id,String name,String location,String phone,String email,String gender,String DOB,String membershipStartDate)
    {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.membershipStartDate = membershipStartDate;
        this.loyaltyPoints = 0.0;
        this.activeStatus = false;
        this.attendance = 0;
    }
    //ACCESSORS
    //accessor for id
    public int getId(){
        return this.id;
    }
    //accessor for attendance
    public int getAttendance(){
        return this.attendance;
    }
    //accessor for name
    public String getName(){
        return this.name;
    }
    //accessor for location
    public String getLocation(){
        return this.location;
    }
    //accessor for phone
    public String getPhone(){
        return this.phone;
    }
    //accessor for email
    public String getEmail(){
        return this.email;
    }
    //accessor for gender
    public String getGender(){
        return this.gender;
    }
    //accessor for dateOfBirth
    public String getDOB(){
        return this.DOB;
    }
    //accessor for membershipStartDate
    public String getMembershipStartDate(){
        return this.membershipStartDate;
    }
    //how much loyalty points the member has accumulated
    public double getLoyaltyPoints(){
        return this.loyaltyPoints;
    }
    //checks if the member's membership is active
    public boolean getActiveStatus(){
        return this.activeStatus;
    }
    //END OF ACCESSORS
    public abstract void markAttendance();//this method will mark attendance of the member, also has no implementation in this class
    
    public void activateMembership(){//activates the membership when called
        this.activeStatus = true;
    }

    public void deactivateMembership(){//deactivates the membership when called
        if(this.activeStatus){
            this.activeStatus = false;
        }
        else{
            System.out.println("The membership is not active!");
        }
    }
    /*this method resets and deascivates the membership 
     * resets the values of attendance and loyaltyPoints to their default values
     */
    public void resetMember(){
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0.0;
    }
    //displays all details of member
    public void display(){
        System.out.println("Name: "+ name);
        System.out.println("ID: "+ id);
        System.out.println("Location: "+ location);
        System.out.println("Phone: "+ phone);
        System.out.println("Email: "+ email);
        System.out.println("Gender:"+ gender);
        System.out.println("Date of Birth: "+ DOB);
        System.out.println("Started on: "+ membershipStartDate);
        System.out.println("Attendance: "+ attendance);
        System.out.println("Loyalty points: "+ loyaltyPoints);
        if(activeStatus){
            System.out.println("Active status: ACTIVE");
        }
        else{
            System.out.println("Active status: INACTIVE");
        }
        
    }
}