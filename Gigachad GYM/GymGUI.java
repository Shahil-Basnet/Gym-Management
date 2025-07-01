import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GymGUI{
    //array list to store objects of RegularMember and PremiumMember
    private ArrayList<GymMember> members;

    //------------------------------------------variables for frame(s)--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private JFrame mainFrame,upgradePlanFrame,displayFrame;
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //------------------------------------------variables for panels------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private JPanel homePanel,logoPanel,membershipActionPanel,memberActionPanel,regularPanel,regularTitlePanel,memberDetailpanel,premiumPanel,displayPanel,premiumTitlePanel,premiumMemberDetailPanel;
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //------------------------------------------variables for labels--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //variables for labels only in regular member
    private JLabel memberIdLabel,nameLabel,locationLabel,phoneLabel,emailLabel,genderLabel,dobLabel,membershipStartDateLabel,backButtonLabel,planLabel,priceLabel;
    //variables for labels only in premium member
    private JLabel premiumMemberIdLabel,premiumNameLabel,premiumLocationLabel,premiumPhoneLabel,premiumEmailLabel,premiumGenderLabel,premiumDobLabel,premiumMembershipStartDateLabel,premiumBackButtonLabel, premiumTrainerLabel, premiumChargeLabel;
    //variable fot labels in display panel
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //------------------------------------------variables for text fields--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //variables for text fields only in regular member
    private JTextField memberIdField,nameField,locationField,phoneField,emailField,priceField,planField;
    //variables for text fields only in premium member
    private JTextField premiumMemberIdField, premiumNameField, premiumLocationField, premiumPhoneField, premiumEmailField,premiumTrainerField,premiumChargeField;
    //variable for pricefield in upgradeframe
    private JTextField upgradePriceField;
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //------------------------------------------variables for text area--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private JTextArea headerArea,memberInfoArea;
    //------------------------------------------variables for radio buttons--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // buttongroup for premium radio buttons in regular panel
    private ButtonGroup genderButtonGroup;
    // buttongroup for premium radio buttons in premium panel
    private ButtonGroup premiumGenderButtonGroup;
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //------------------------------------------variables for buttons--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // variables for buttons in home panel
    private JButton addRegularButton,addPremiumButton,resetRegularButton,resetPremiumButton,activateButton,deactivateButton,markAttendanceButton,displayButton,calculateDiscountButton,payDueAmountButton,upgradePlanButton,saveToFileButton,upgradeButton,readFromFileButton;
    // variables for buttons in regular panel
    private JButton clearButton,addRegularMemberButton;
    // variables for buttons in premium panel
    private JButton addPremiumMemberButton, premiumClearButton;

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //------------------------------------------variables for combo box--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //combobox for dob and membership start date in regular panel
    private JComboBox<String> dayComboBox,monthComboBox,yearComboBox,dayMembershipComboBox,monthMembershipComboBox,yearMembershipComboBox,planComboBox;
    //combobox for dob and membership start date in premium panel
    private JComboBox<String> premiumDayComboBox, premiumMonthComboBox, premiumYearComboBox, premiumDayMembershipComboBox, premiumMonthMembershipComboBox, premiumYearMembershipComboBox;

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //------------------------------------------ARRAYS--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //arrays for combo box (days,months,years and plans)
    //array for days
    private String[] days = {"1","2","3","4","5","6","7","8","9","10",
            "11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30","31"};
    //array for months
    private String[] months = {"Jan","Feb","Mar","Apr","May","Jun",
            "Jul","Aug","Sep","Oct","Nov","Dec"};
    //array for years
    private String[] years = {"1980", "1981", "1982", "1983","1984","1985","1986","1987","1988","1989",
            "1990", "1991", "1992", "1993", "1994","1995","1996","1997","1998","1999",
            "2000", "2001", "2002", "2003", "2004","2005","2006","2007","2008","2009",
            "2010", "2011", "2012", "2013", "2014","2015","2016","2017","2018","2019",
            "2020", "2021", "2022", "2023", "2024","2025"
        };
    //different years for membership start date 
    private String[] yearsMembership = {
            "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
            "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019",
            "2020", "2021", "2022", "2023", "2024", "2025"
        };
    //array for plans
    private String[] plans = {"Basic","Standard","Deluxe"};
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //----------------------------------------variables for radio buttons----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // radio buttons for gender selection in regular panel
    private JRadioButton maleRadioButton,femaleJRadioButton,otherRadioButton;
    // radio buttons for gender selection in premium panel
    private JRadioButton premiumMaleRadioButton, premiumFemaleRadioButton, premiumOtherRadioButton;
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private Font pixelish, pixelishLabel,pixelDisplay;

    //constructor for gui
    public GymGUI(){

        //fonts
        try{
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/minecraft-font/MinecraftRegular-Bmg3.otf"));

            this.pixelish = pixelFont.deriveFont(24f);
            this.pixelishLabel = pixelFont.deriveFont(20f);
            this.pixelDisplay = pixelFont.deriveFont(16f);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            // Fallback to default font
            this.pixelish = new Font("SansSerif", Font.PLAIN, 24);
            this.pixelishLabel = new Font("SansSerif", Font.PLAIN, 20);
            this.pixelDisplay = new Font("SansSerif", Font.PLAIN, 16);
        }
        //array list for storing objects of regular and premium members
        members = new ArrayList<>();
        mainFrame = new JFrame("Gigachad Management System");
        mainFrame.setLayout(null);
        mainFrame.setSize(1610,900);

        //custom colors using hex values
        Color darkPurple = Color.decode("#554971");
        Color semiTransparentGrayColor = new Color(109,106,117,169);
        Color transparentColor = new Color(0,0,0,0);

        //custom borders
        Border sizedBorder1 = BorderFactory.createMatteBorder(3, 3, 3, 3,Color.BLACK);
        Border etchedBorder = BorderFactory.createEtchedBorder();

        //main panel
        homePanel = new JPanel();
        homePanel.setLayout(null);
        homePanel.setBounds(0,0,1600,870);

        //baackground image
        ImageIcon homeBgImage = new ImageIcon("images/homePanelBG.jpeg");
        Image scaledHomeBg = homeBgImage.getImage().getScaledInstance(1600,870,Image.SCALE_SMOOTH);
        JLabel homeBgLabel = new JLabel(new ImageIcon(scaledHomeBg));
        homeBgLabel.setBounds(0,0,1600,870);

        logoPanel = new JPanel();
        logoPanel.setLayout(null);
        logoPanel.setBackground(semiTransparentGrayColor);
        logoPanel.setBounds(5,5,1586,323);
        homePanel.add(logoPanel);

        ImageIcon logoImage = new ImageIcon("images/gigachadLOGO.png");
        Image scaled = logoImage.getImage().getScaledInstance(500, 334, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaled));
        logoLabel.setBounds(549,15,500,334);
        logoPanel.add(logoLabel);

        //panel for buttons with membership actions like, adding members, activating members reseting members...
        membershipActionPanel = new JPanel();
        membershipActionPanel.setLayout(null);
        membershipActionPanel.setBackground(new Color(109,106,117,235));
        membershipActionPanel.setBorder(BorderFactory.createCompoundBorder(sizedBorder1,etchedBorder));
        membershipActionPanel.setBounds(158,374,1281,216);
        homePanel.add(membershipActionPanel);

        /*
        Buttons for membership actions
         */

        //margin/padding for buttons
        Insets borderInset = new Insets(10,0,0,0);

        //button to add regular member
        addRegularButton = new JButton("ADD REGULAR MEMBER");
        addRegularButton.setBounds(42,25,335,45);
        styleButton(addRegularButton,darkPurple,borderInset,pixelish);
        hoverEffect(addRegularButton,darkPurple);
        membershipActionPanel.add(addRegularButton);
        //eventlistener at the end of constructor

        //button to add premium member
        addPremiumButton = new JButton("ADD PREMIUM MEMBER");
        addPremiumButton.setBounds(877,25,335,45);
        styleButton(addPremiumButton,darkPurple,borderInset,pixelish);
        hoverEffect(addPremiumButton,darkPurple);
        membershipActionPanel.add(addPremiumButton);
        //eventlistener at the end of constructor

        //button to reset member
        resetRegularButton = new JButton("RESET REGULAR MEMBER");
        resetRegularButton.setBounds(473,25,335,45);
        styleButton(resetRegularButton,darkPurple,borderInset,pixelish);
        hoverEffect(resetRegularButton,darkPurple);
        membershipActionPanel.add(resetRegularButton);
        resetRegularButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = JOptionPane.showInputDialog( mainFrame, "Enter member id", "Reset regular member", JOptionPane.INFORMATION_MESSAGE);
                    if(input == null) {
                        return;
                    }
                    try {
                        int memberId = Integer.parseInt(input);
                        boolean memberExists = false;                    
                        for (GymMember member : members) {
                            if (member.getId() == memberId) {
                                memberExists = true;

                                if (!member.getActiveStatus()) {
                                    JOptionPane.showMessageDialog(mainFrame, 
                                        "Member is not active", "Not active", 
                                        JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                if (member instanceof RegularMember) {
                                    String removalReason = JOptionPane.showInputDialog(
                                            mainFrame, "Enter removal reason", "Removal reason", JOptionPane.QUESTION_MESSAGE);

                                    if (removalReason == null || removalReason.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(mainFrame, "Removal reason cannot be empty", "Error",JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    ((RegularMember)member).revertRegularMember(removalReason);
                                    JOptionPane.showMessageDialog(mainFrame, "Member with ID: " + memberId + " reset successfully","Member reset", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else{
                                    JOptionPane.showMessageDialog(mainFrame, "Member with ID: " + memberId + " is not a regular member", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                break;
                            }
                        }
                        if (!memberExists) {
                            JOptionPane.showMessageDialog(mainFrame, "Member with ID: " + memberId + " does not exist", "Not found", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(mainFrame, "Please enter a valid member ID", "Invalid input", JOptionPane.ERROR_MESSAGE);
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(mainFrame, "Error resetting member: " + ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            });
        // button to reset premium members
        resetPremiumButton = new JButton("RESET PREMIUM MEMBER");
        resetPremiumButton.setBounds(473,150,335,45);
        styleButton(resetPremiumButton,darkPurple,borderInset,pixelish);
        hoverEffect(resetPremiumButton,darkPurple);
        membershipActionPanel.add(resetPremiumButton);
        resetPremiumButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        int memberId = Integer.parseInt(JOptionPane.showInputDialog( mainFrame, "Enter member id", "Reset regular member", JOptionPane.INFORMATION_MESSAGE));
                        boolean memberExists = false;                        
                        for (GymMember member : members) {
                            if (member.getId() == memberId) {
                                memberExists = true;

                                if (!member.getActiveStatus()) {
                                    JOptionPane.showMessageDialog(mainFrame, 
                                        "Member is not active", "Not active", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                if (member instanceof PremiumMember) {
                                    String removalReason = JOptionPane.showInputDialog( mainFrame, "Enter removal reason", "Removal reason", JOptionPane.QUESTION_MESSAGE);

                                    if (removalReason == null || removalReason.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(mainFrame, "Removal reason cannot be empty", "Error",JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    ((PremiumMember)member).revertPremiumMember(removalReason);
                                    JOptionPane.showMessageDialog(mainFrame, "Member with ID: " + memberId + " reset successfully","Member reset", JOptionPane.INFORMATION_MESSAGE);
                                }
                                break;
                            }
                        }
                        if (!memberExists) {
                            JOptionPane.showMessageDialog(mainFrame, 
                                "Member with ID: " + memberId + " does not exist", "Not found", JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(mainFrame, "Member with ID: " + memberId + " is not a premium member", "Not a premium member", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(mainFrame, "Please enter a valid member ID", "Invalid input", JOptionPane.ERROR_MESSAGE);
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(mainFrame, "Error resetting member: " + ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            });

        //button to activate membership
        activateButton = new JButton("ACTIVATE MEMBERSHIP");
        activateButton.setBounds(42,150,335,45);
        styleButton(activateButton,darkPurple,borderInset,pixelish);
        hoverEffect(activateButton,darkPurple);
        membershipActionPanel.add(activateButton);
        activateButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    try{
                        int memberId = Integer.parseInt(JOptionPane .showInputDialog(mainFrame,"Enter member id","Activate membership",JOptionPane.INFORMATION_MESSAGE));
                        boolean memberExists = false;
                        for(GymMember member : members){
                            if(member.getId() == memberId){
                                if(member.getActiveStatus() == true){
                                    JOptionPane.showMessageDialog(mainFrame,memberId+" is already active!","Already active",JOptionPane.INFORMATION_MESSAGE);
                                    return;
                                }
                                member.activateMembership();
                                memberExists= true;
                                JOptionPane.showMessageDialog(mainFrame,"Activated membership for ID:"+memberId,"Member activation",JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                        }
                        if(!memberExists){
                            JOptionPane.showMessageDialog(mainFrame,"Member does not exist","Nonexistent",JOptionPane.ERROR_MESSAGE);
                        }}
                    catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(mainFrame,"Invalid input!","Error", JOptionPane.ERROR_MESSAGE);
                    }}
            });

        //button to activate membership
        deactivateButton = new JButton("DEACTIVATE MEMBERSHIP");
        deactivateButton.setBounds(877,150,335,45);
        styleButton(deactivateButton,darkPurple,borderInset,pixelish);
        hoverEffect(deactivateButton,darkPurple);
        membershipActionPanel.add(deactivateButton);
        deactivateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    try{
                        int memberId = Integer.parseInt(JOptionPane .showInputDialog(mainFrame,"Enter member id","Deactivate membership",JOptionPane.INFORMATION_MESSAGE));
                        boolean memberExists = false;
                        for(GymMember member : members){
                            if(member.getId() == memberId){
                                if(member.getActiveStatus() == false){
                                    JOptionPane.showMessageDialog(mainFrame,memberId+" is not active!","Already inactive",JOptionPane.INFORMATION_MESSAGE);
                                    return;
                                }
                                member.deactivateMembership();
                                memberExists= true;
                                JOptionPane.showMessageDialog(mainFrame,"Deactivated membership for ID: "+memberId,"Member deactivation",JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                        }
                        if(!memberExists){
                            JOptionPane.showMessageDialog(mainFrame,"Member does not exist","Tried to deactivate nothing 💀",JOptionPane.ERROR_MESSAGE);
                        }}
                    catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(mainFrame,"Invalid input!","Error", JOptionPane.ERROR_MESSAGE);
                    }}
            });

        /*
         * panel for buttons with actions for registered members like mark attendance, pay due amount...
         */ 
        memberActionPanel = new JPanel();
        memberActionPanel.setLayout(null);
        memberActionPanel.setBackground(new Color(109,106,117,235));
        memberActionPanel.setBorder(BorderFactory.createCompoundBorder(sizedBorder1,etchedBorder));
        memberActionPanel.setBounds(158,628,1281,216);
        homePanel.add(memberActionPanel);

        //button to marking attendance of member
        markAttendanceButton = new JButton("MARK ATTENDANCE");
        markAttendanceButton.setBounds(42,25,335,45);
        styleButton(markAttendanceButton,darkPurple,borderInset,pixelish);
        hoverEffect(markAttendanceButton,darkPurple);
        memberActionPanel.add(markAttendanceButton);
        markAttendanceButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    try{
                        int memberId = Integer.parseInt(JOptionPane.showInputDialog(mainFrame,"Enter member id","Mark attendance",JOptionPane.INFORMATION_MESSAGE));
                        boolean memberExists = false;
                        for(GymMember member : members){
                            if(member.getId() == memberId){
                                if(member.getActiveStatus() == false){
                                    JOptionPane.showMessageDialog(mainFrame,memberId+" is not active!","Inactive member",JOptionPane.INFORMATION_MESSAGE);
                                    return;
                                }
                                //calls relevant member(regular/premium) method to mark attendance
                                member.markAttendance();
                                memberExists = true;
                                JOptionPane.showMessageDialog(mainFrame,"Marked attendance for ID: "+memberId,"Attendance marked",JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                        }
                        // if no member found show this
                        if(!memberExists){
                            JOptionPane.showMessageDialog(mainFrame,"Member does not exist","Not a member",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    // if input is not a number or is empty
                    catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(mainFrame,"Invalid input!","Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        //button to pay due of member
        payDueAmountButton = new JButton("PAY DUE AMOUNT");
        payDueAmountButton.setBounds(877,25,335,45);
        styleButton(payDueAmountButton,darkPurple,borderInset,pixelish);
        hoverEffect(payDueAmountButton,darkPurple);
        memberActionPanel.add(payDueAmountButton);
        payDueAmountButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    try{
                        int memberId = Integer.parseInt(JOptionPane.showInputDialog(mainFrame,"Enter member id","Pay Due Amount",JOptionPane.INFORMATION_MESSAGE));
                        boolean memberExists = false;
                        for(GymMember member : members){
                            if(member.getId() == memberId){
                                memberExists = true;
                                if(member instanceof RegularMember){
                                    throw new ClassCastException();
                                }
                                if(!member.getActiveStatus()){
                                    JOptionPane.showMessageDialog(mainFrame,memberId+" is not active!","Inactive member",JOptionPane.INFORMATION_MESSAGE);
                                    return;
                                }
                                
                                    while (true){
                                        double paidAmount = Double.parseDouble(JOptionPane.showInputDialog(mainFrame, "Enter amount to pay" ,"Pay due amount", JOptionPane.PLAIN_MESSAGE));
                                        String result = ((PremiumMember)member).payDueAmount(paidAmount);
                                        if(paidAmount <= 0){
                                            JOptionPane.showMessageDialog(mainFrame, result,"Invalid input", JOptionPane.ERROR_MESSAGE);
                                            continue;
                                        }
                                        if(((PremiumMember)member).getIsFullPayment()){
                                            JOptionPane.showMessageDialog(mainFrame, result,"Already fully paid", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        }
                                        if(paidAmount > ((PremiumMember)member).getPremiumCharge()){
                                            JOptionPane.showMessageDialog(mainFrame, result,"Amount exceeded", JOptionPane.ERROR_MESSAGE);
                                            continue;
                                        }
                                        double remaining = ((PremiumMember)member).getPremiumCharge()-((PremiumMember)member).getPaidAmount();
                                        if(remaining == 0.0){
                                            JOptionPane.showMessageDialog(mainFrame, result,"Fully paid", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        }
                                        JOptionPane.showMessageDialog(mainFrame, result,"Successfull Payment", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                        }
                                                   
                                }
                            }
                    // if no member found show this
                                if(!memberExists){
                                    JOptionPane.showMessageDialog(mainFrame,"Member does not exist","Not a member",JOptionPane.ERROR_MESSAGE);
                                }}
                            // if input is not a number or is empty }
                            catch(NumberFormatException ex){
                                JOptionPane.showMessageDialog(mainFrame,"Invalid input!","Error", JOptionPane.ERROR_MESSAGE);
                            }
                        catch(ClassCastException ex1){
                            JOptionPane.showMessageDialog(mainFrame,"Only premium members can pay due amount!","Error", JOptionPane.ERROR_MESSAGE);
                        }
                    catch(Exception ex2){
                        JOptionPane.showMessageDialog(mainFrame,"Error occurred!","Error", JOptionPane.ERROR_MESSAGE);
                    }}
                
            });
        

        displayFrame = new JFrame("Member Information");
        displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayFrame.setSize(1600, 700);
        displayFrame.setLayout(null);
        displayFrame.add(new JScrollPane(new JTextArea(20, 60)), BorderLayout.CENTER);
        displayFrame.setVisible(false);

        headerArea = new JTextArea();
        headerArea.setEditable(false);
        headerArea.setFont(pixelDisplay);
        headerArea.setLineWrap(true);
        headerArea.setWrapStyleWord(true);
        headerArea.setBackground(Color.decode("#F0F0F0"));
        headerArea.setBounds(0, 0, 1600,40);
        headerArea.setText(getHeader());
        displayFrame.add(headerArea);

        memberInfoArea = new JTextArea();
        memberInfoArea.setEditable(false);
        memberInfoArea.setFont(pixelDisplay);
        memberInfoArea.setLineWrap(true);
        memberInfoArea.setWrapStyleWord(true);
        memberInfoArea.setBackground(Color.decode("#F0F0F0"));
        memberInfoArea.setBounds(0, 40, 1600, 650);
        displayFrame.add(memberInfoArea);

        

        //button to display information of member
        displayButton = new JButton("DISPLAY INFO");
        displayButton.setBounds(473,90,335,45);
        styleButton(displayButton,darkPurple,borderInset,pixelish);
        hoverEffect(displayButton,darkPurple);
        memberActionPanel.add(displayButton);
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String allMembersInfo = "--------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
                if( members.size() != 0){
                for (GymMember member : members){
                            if (member instanceof RegularMember){
                                allMembersInfo += String.format("%-4s %-20s %-10s %-15s %-12s %-25s %-18s %-10s %-8s %-15s %-15s %-12s %-10s %-15s %-10s\n",member.getId(), member.getName(), member.getGender(), member.getLocation(), member.getPhone(), member.getEmail(), member.getMembershipStartDate(),((RegularMember)member).getPlan(),((RegularMember)member).getPrice(), member.getAttendance(), member.getLoyaltyPoints(), member.getActiveStatus(), "N/A", "N/A", "N/A");
                            }
                            else if(member instanceof PremiumMember){
                                allMembersInfo += String.format("%-4s %-20s %-10s %-15s %-12s %-25s %-18s %-10s %-8s %-15s %-15s %-12s %-10s %-15s %-10s\n", member.getId(), member.getName(), member.getGender(), member.getLocation(), member.getPhone(), member.getEmail(),member.getMembershipStartDate(),"N/A", "N/A", member.getAttendance(), member.getLoyaltyPoints(), member.getActiveStatus(), ((PremiumMember)member).getDiscountAmount(), ((PremiumMember)member).getPersonalTrainer(), ((PremiumMember)member).getPaidAmount());
                            }
                        }
                }
                else{
                    allMembersInfo = "No members found";
                    
                }
                memberInfoArea.setText(allMembersInfo);
                displayFrame.setVisible(true);
                displayFrame.toFront();
            }
        });

        //button to calculate discount of premium member
        calculateDiscountButton = new JButton("CALCULATE DISCOUNT");
        calculateDiscountButton.setBounds(42,150,335,45);
        styleButton(calculateDiscountButton,darkPurple,borderInset,pixelish);
        hoverEffect(calculateDiscountButton,darkPurple);
        memberActionPanel.add(calculateDiscountButton);
        calculateDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                        int memberId = Integer.parseInt(JOptionPane.showInputDialog(mainFrame,"Enter member id","Pay Due Amount",JOptionPane.INFORMATION_MESSAGE));
                        boolean memberExists = false;
                        for(GymMember member : members){
                            if(member.getId() == memberId){
                                memberExists = true;
                                if(member instanceof RegularMember){
                                    throw new ClassCastException();
                                }
                                if(!member.getActiveStatus()){
                                    JOptionPane.showMessageDialog(mainFrame,memberId+" is not active!","Inactive member",JOptionPane.INFORMATION_MESSAGE);
                                    return;
                                }
                                ((PremiumMember)member).calculateDiscount();
                                JOptionPane.showMessageDialog(mainFrame,"ID: "+ memberId+" discount is "+((PremiumMember)member).getDiscountAmount(),"Discount",JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                    // if no member found show this
                                if(!memberExists){
                                    JOptionPane.showMessageDialog(mainFrame,"Member does not exist","Not a member",JOptionPane.ERROR_MESSAGE);
                                }}
                            // if input is not a number or is empty }
                            catch(NumberFormatException ex){
                                JOptionPane.showMessageDialog(mainFrame,"Invalid input!","Error", JOptionPane.ERROR_MESSAGE);
                            }
                        catch(ClassCastException ex1){
                            JOptionPane.showMessageDialog(mainFrame,"Only premium members can get discount!","Error", JOptionPane.ERROR_MESSAGE);
                        }
                        catch (RuntimeException ex3){
                            JOptionPane.showMessageDialog(mainFrame,ex3.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                        }
                    catch(Exception ex2){
                        JOptionPane.showMessageDialog(mainFrame,"Error occurred!","Error", JOptionPane.ERROR_MESSAGE);
                    }}
                
            });
                


        upgradePlanButton = new JButton("UPGRADE PLAN");
        upgradePlanButton.setBounds(473,25,335,45);
        styleButton(upgradePlanButton,darkPurple,borderInset,pixelish);
        hoverEffect(upgradePlanButton,darkPurple);
        memberActionPanel.add(upgradePlanButton);

        displayPanel = new JPanel();
        displayPanel.setBounds(0, 0, 1600, 870);
        displayPanel.setBackground(Color.WHITE);
        displayPanel.setLayout(new BorderLayout());
        


        readFromFileButton = new JButton("READ FROM FILE");
        readFromFileButton.setBounds(473,150,335,45);
        styleButton(readFromFileButton,darkPurple,borderInset,pixelish);
        hoverEffect(readFromFileButton,darkPurple);
        memberActionPanel.add(readFromFileButton);
        readFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileMembersInfo = "--------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
                try {
                    // read from file
                    File file = new File("members.txt");
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(",");
                        String id = data[0];
                        String name = data[1]; 
                        String gender = data[2];
                        String location = data[3];
                        String phone = data[4];
                        String email = data[4];
                        String startDate = data[6];
                        String plan = data[7];
                        String price = String.valueOf(data[8]);
                        String attendance = String.valueOf(data[9]);
                        String loyaltyPoints = String.valueOf(data[10]);
                        String status = data[11];
                        String discountAmt= String.valueOf(data[12]);
                        String trainer = data[13];
                        String paidAmt = String.valueOf(data[14]);

                        fileMembersInfo += String.format("%-4s %-20s %-10s %-15s %-12s %-25s %-18s %-10s %-8s %-15s %-15s %-12s %-10s %-15s %-10s\n",
                            id, name, gender, location, phone, email, startDate, plan, price, attendance, loyaltyPoints, status, discountAmt, trainer, paidAmt);
                        }
                        br.close();
                    memberInfoArea.setText(fileMembersInfo);
                    displayFrame.setVisible(true);
                    displayFrame.toFront();
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(mainFrame,"Error reading from file","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }}
);


        //save to file button
        saveToFileButton = new JButton("SAVE TO FILE");
        saveToFileButton.setBounds(877,150,335,45);
        styleButton(saveToFileButton,darkPurple,borderInset,pixelish);
        hoverEffect(saveToFileButton,darkPurple);
        memberActionPanel.add(saveToFileButton);
        saveToFileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // save data to file
                    if(members.size() == 0){
                        JOptionPane.showMessageDialog(mainFrame, "No data to save","No data",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        // create file
                        File file = new File("members.txt");
                        // create writer
                        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
                        // write data to file
                        for (GymMember member : members){
                            if (member instanceof RegularMember){
                                fileWriter.write(member.getId()+ "," 
                                    + member.getName() + "," + member.getGender() + "," + member.getLocation() + "," + member.getPhone() + "," + member.getEmail() +
                                    "," + member.getMembershipStartDate() + "," + ((RegularMember)member).getPlan() + "," + ((RegularMember)member).getPrice() + ","
                                    + member.getAttendance() + "," + member.getLoyaltyPoints() + "," + member.getActiveStatus() + "," + "N/A" + "," + "N/A" + "," + "N/A");
                                fileWriter.newLine();
                            }
                            else if( member instanceof PremiumMember){
                                fileWriter.write(member.getId()+ "," 
                                    + member.getName() + "," +  member.getGender() + "," + member.getLocation()  + "," + member.getPhone() + "," + member.getEmail() +
                                    "," + member.getMembershipStartDate() + "," + "N/A" + "," + "N/A" + ","
                                    + member.getAttendance() + "," + member.getLoyaltyPoints() + "," + member.getActiveStatus() + "," + ((PremiumMember)member).getDiscountAmount() + "," + ((PremiumMember)member).getPersonalTrainer() + "," + ((PremiumMember)member).getPaidAmount());
                                fileWriter.newLine();
                            }
                        }
                        JOptionPane.showMessageDialog(mainFrame, "Data saved to file successfully");
                        fileWriter.close();
                    }
                    catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error saving data to file");
                    }
                }
            });

        //+++++++++++++++REGULAR MEMBER PAENL+++++++++++++++++++
        regularPanel = new JPanel();
        regularPanel.setLayout(null);
        regularPanel.setBackground(new Color(109,106,117,235));
        regularPanel.setBounds(0,0,1600,870);
        regularPanel.setVisible(false);

        regularTitlePanel = new JPanel();
        regularTitlePanel.setLayout(null);
        regularTitlePanel.setBackground(darkPurple);
        regularTitlePanel.setBounds(5,5,1586,85);
        regularPanel.add(regularTitlePanel);

        JLabel regularTitle = new JLabel("Regular Member");
        regularTitle.setFont(pixelish);
        regularTitle.setForeground(Color.WHITE);
        regularTitle.setBounds(700,25,400,50);
        regularTitlePanel.add(regularTitle);

        backButtonLabel = new JLabel("<html><u>⇦Back to home</u></html>");
        backButtonLabel.setBounds(17,91,150,28);
        backButtonLabel.setForeground(Color.BLACK);
        backButtonLabel.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        regularPanel.add(backButtonLabel);

        //panel for entering info of regular members
        memberDetailpanel = new JPanel();
        memberDetailpanel.setLayout(null);
        memberDetailpanel.setBackground(Color.WHITE);
        memberDetailpanel.setBounds(5,130,1585,570);
        regularPanel.add(memberDetailpanel);

        memberIdLabel = new JLabel("Member ID:");
        memberIdLabel.setBounds(37,67,124,30);
        memberIdLabel.setFont(pixelishLabel);
        memberIdLabel.setForeground(Color.BLACK);
        memberDetailpanel.add(memberIdLabel);

        memberIdField = new JTextField();
        memberIdField.setBounds(195,45,380,50);
        memberIdField.setFont(pixelishLabel);
        memberIdField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.BLACK));
        memberDetailpanel.add(memberIdField);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(37,166,124,50);
        nameLabel.setFont(pixelishLabel);
        nameLabel.setForeground(Color.BLACK);
        memberDetailpanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(195,150,380,50);
        nameField.setFont(pixelishLabel);
        nameField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.BLACK));
        memberDetailpanel.add(nameField);

        genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(37,265,124,30);
        genderLabel.setFont(pixelishLabel);
        genderLabel.setForeground(Color.BLACK);
        memberDetailpanel.add(genderLabel);

        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setActionCommand("Male"); 
        maleRadioButton.setBounds(195,265,100,30);
        maleRadioButton.setFont(pixelishLabel);
        maleRadioButton.setBackground(Color.WHITE);
        maleRadioButton.setFocusPainted(false);
        memberDetailpanel.add(maleRadioButton);

        femaleJRadioButton = new JRadioButton("Female");
        femaleJRadioButton.setActionCommand("Female"); 
        femaleJRadioButton.setBounds(305,265,100,30);
        femaleJRadioButton.setFont(pixelishLabel);
        femaleJRadioButton.setBackground(Color.WHITE);
        femaleJRadioButton.setFocusPainted(false);
        memberDetailpanel.add(femaleJRadioButton);

        otherRadioButton = new JRadioButton("Other");
        otherRadioButton.setActionCommand("Other"); 
        otherRadioButton.setBounds(415,265,100,30);
        otherRadioButton.setFont(pixelishLabel);
        otherRadioButton.setBackground(Color.WHITE);
        otherRadioButton.setFocusPainted(false);
        memberDetailpanel.add(otherRadioButton);

        //group so only one is selected at a time
        genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleJRadioButton);
        genderButtonGroup.add(otherRadioButton);

        dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(37,365,124,30);
        dobLabel.setFont(pixelishLabel);
        dobLabel.setForeground(Color.BLACK);
        memberDetailpanel.add(dobLabel);

        dayComboBox = new JComboBox<>(days);
        dayComboBox.setBounds(195,365,100,30);
        dayComboBox.setFont(pixelishLabel);
        dayComboBox.setBackground(Color.WHITE);
        dayComboBox.setSelectedIndex(0);
        memberDetailpanel.add(dayComboBox);

        monthComboBox = new JComboBox<>(months);
        monthComboBox.setBounds(305,365,100,30);
        monthComboBox.setFont(pixelishLabel);
        monthComboBox.setBackground(Color.WHITE);
        monthComboBox.setSelectedIndex(0);
        memberDetailpanel.add(monthComboBox);

        yearComboBox = new JComboBox<>(years);
        yearComboBox.setBounds(415,365,100,30);
        yearComboBox.setFont(pixelishLabel);
        yearComboBox.setBackground(Color.WHITE);
        yearComboBox.setSelectedIndex(0);
        memberDetailpanel.add(yearComboBox);

        planLabel = new JLabel("Plan:");
        planLabel.setBounds(37,465,124,30);
        planLabel.setFont(pixelishLabel);
        planLabel.setForeground(Color.BLACK);
        memberDetailpanel.add(planLabel);

        planField = new JTextField();
        planField.setBounds(195,465,380,50);
        planField.setFont(pixelishLabel);
        planField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.BLACK));
        planField.setText("Basic");
        planField.setEditable(false);
        memberDetailpanel.add(planField);

        locationLabel = new JLabel("Location:");
        locationLabel.setBounds(1001,67,124,28);
        locationLabel.setFont(pixelishLabel);
        locationLabel.setForeground(Color.BLACK);
        memberDetailpanel.add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(1160,45,380,50);
        locationField.setFont(pixelishLabel);
        locationField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.BLACK));
        memberDetailpanel.add(locationField);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(1001,166,124,28);
        phoneLabel.setFont(pixelishLabel);
        phoneLabel.setForeground(Color.BLACK);
        memberDetailpanel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(1160,145,380,50);
        phoneField.setFont(pixelishLabel);
        phoneField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.BLACK));
        memberDetailpanel.add(phoneField);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(1001,265,124,28);
        emailLabel.setFont(pixelishLabel);
        emailLabel.setForeground(Color.BLACK);
        memberDetailpanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(1160,245,380,50);
        emailField.setFont(pixelishLabel);
        emailField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.BLACK));
        memberDetailpanel.add(emailField);

        membershipStartDateLabel = new JLabel("Membership Start Date:");
        membershipStartDateLabel.setBounds(1001,365,124,28);
        membershipStartDateLabel.setFont(pixelishLabel);
        membershipStartDateLabel.setForeground(Color.BLACK);
        memberDetailpanel.add(membershipStartDateLabel);

        dayMembershipComboBox = new JComboBox<>(days);
        dayMembershipComboBox.setBounds(1160,365,100,30);
        dayMembershipComboBox.setFont(pixelishLabel);
        dayMembershipComboBox.setBackground(Color.WHITE);
        dayMembershipComboBox.setSelectedIndex(0);
        memberDetailpanel.add(dayMembershipComboBox);

        monthMembershipComboBox = new JComboBox<>(months);
        monthMembershipComboBox.setBounds(1270,365,100,30);
        monthMembershipComboBox.setFont(pixelishLabel);
        monthMembershipComboBox.setBackground(Color.WHITE);
        monthMembershipComboBox.setSelectedIndex(0);
        memberDetailpanel.add(monthMembershipComboBox);

        yearMembershipComboBox = new JComboBox<>(yearsMembership);
        yearMembershipComboBox.setBounds(1385,365,100,30);
        yearMembershipComboBox.setFont(pixelishLabel);
        yearMembershipComboBox.setBackground(Color.WHITE);
        yearMembershipComboBox.setSelectedIndex(0);
        memberDetailpanel.add(yearMembershipComboBox);

        //label for price 
        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(1001,465,124,28);
        priceLabel.setFont(pixelishLabel);
        priceLabel.setForeground(Color.BLACK);
        memberDetailpanel.add(priceLabel);

        // label for price field
        priceField = new JTextField();
        priceField.setBounds(1160,445,380,50);
        priceField.setFont(pixelishLabel);
        priceField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.BLACK));
        priceField.setEditable(false);
        priceField.setText("6500");
        memberDetailpanel.add(priceField);

        addRegularMemberButton = new JButton("Add Regular Member");
        addRegularMemberButton.setBounds(150,760,335,45);
        styleButton(addRegularMemberButton, darkPurple, borderInset, pixelishLabel);
        hoverEffect(addRegularMemberButton, darkPurple);
        regularPanel.add(addRegularMemberButton);
        addRegularMemberButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    // Validate required fields
                    
                    if (memberIdField.getText().isEmpty() || nameField.getText().isEmpty() || locationField.getText().isEmpty() || emailField.getText().isEmpty() || phoneField.getText().isEmpty() || genderButtonGroup.getSelection() == null){
                        JOptionPane.showMessageDialog(mainFrame,"Please fill in all the required fields.","Empty fields", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    try {
                        int memberId = Integer.parseInt(memberIdField.getText());
                        if(memberId <= 0){
                        throw new NumberFormatException("ID less than 1");
                        }
                        String referralSource = JOptionPane.showInputDialog(mainFrame, "Enter referral source: ");
                        if (referralSource.isEmpty()){
                            JOptionPane.showMessageDialog(mainFrame,"Please enter referral source.","Empty fields", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        // Check for duplicate member ID
                        for (GymMember member : members){
                            if (member.getId() == memberId){JOptionPane.showMessageDialog(mainFrame,"Member ID already exists!","Duplicate ID",JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        // Collect all member data
                        String name = nameField.getText();
                        String location = locationField.getText();
                        String email = emailField.getText();
                        String phone = phoneField.getText();
                        String gender = genderButtonGroup.getSelection().getActionCommand();

                        //dates
                        String dobDate = dayComboBox.getSelectedItem() + "/" + monthComboBox.getSelectedItem() + "/" + yearComboBox.getSelectedItem();

                        String startDate =dayMembershipComboBox.getSelectedItem() + "/" + monthMembershipComboBox.getSelectedItem() + "/" + yearMembershipComboBox.getSelectedItem();

                        // Create and add new member to the arrayList
                        members.add(new RegularMember(memberId,name,location,phone,email,gender,dobDate,startDate,referralSource));

                        // Show success message
                        JOptionPane.showMessageDialog(mainFrame,"Regular Member Added Successfully","Member Added",JOptionPane.INFORMATION_MESSAGE);   
                    }
                    catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(mainFrame,"Member ID must be a valid number","Invalid ID",JOptionPane.ERROR_MESSAGE);
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(mainFrame,"Error adding member: " + ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        clearButton = new JButton("Clear");
        clearButton.setBounds(1155,760,335,45);
        styleButton(clearButton, darkPurple, borderInset, pixelishLabel);
        hoverEffect(clearButton, darkPurple);
        regularPanel.add(clearButton);
        clearButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    memberIdField.setText("");
                    nameField.setText("");
                    emailField.setText("");
                    phoneField.setText("");
                    locationField.setText("");

                    dayComboBox.setSelectedIndex(0);
                    monthComboBox.setSelectedIndex(0);
                    yearComboBox.setSelectedIndex(0);
                    dayMembershipComboBox.setSelectedIndex(0);
                    monthMembershipComboBox.setSelectedIndex(0);
                    yearMembershipComboBox.setSelectedIndex(0);

                    genderButtonGroup.clearSelection();
                    JOptionPane.showMessageDialog(mainFrame,"All fields cleared", "Premium fields clear", JOptionPane.INFORMATION_MESSAGE);
                }});

        // ++++++++++++++PREMIUM MEMBER PANEL++++++++++++++++
        premiumPanel = new JPanel();
        premiumPanel.setLayout(null);
        premiumPanel.setBackground(new Color(109,106,117,235));
        premiumPanel.setBounds(0,0,1600,870);
        premiumPanel.setVisible(false);

        //panel for premium title
        premiumTitlePanel = new JPanel();
        premiumTitlePanel.setLayout(null);
        premiumTitlePanel.setBackground(new Color(212,175,55)); //gold color for premium
        premiumTitlePanel.setBounds(5,5,1586,85);
        premiumPanel.add(premiumTitlePanel);

        JLabel premiumTitle = new JLabel("Premium Member");
        premiumTitle.setFont(pixelish);
        premiumTitle.setForeground(Color.BLACK);
        premiumTitle.setBounds(700,25,400,50);
        premiumTitlePanel.add(premiumTitle);

        //back button for premium panel
        premiumBackButtonLabel = new JLabel("<html><u>⇦Back to home</u></html>");
        premiumBackButtonLabel.setBounds(17,91,150,28);
        premiumBackButtonLabel.setForeground(Color.BLACK);
        premiumBackButtonLabel.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        premiumPanel.add(premiumBackButtonLabel);

        //panel for entering info of premium members
        premiumMemberDetailPanel = new JPanel();
        premiumMemberDetailPanel.setLayout(null);
        premiumMemberDetailPanel.setBackground(Color.WHITE);
        premiumMemberDetailPanel.setBounds(5,130,1585,570);
        premiumPanel.add(premiumMemberDetailPanel);

        //member ID label and field
        premiumMemberIdLabel = new JLabel("Member ID:");
        premiumMemberIdLabel.setBounds(37,67,124,30);
        premiumMemberIdLabel.setFont(pixelishLabel);
        premiumMemberIdLabel.setForeground(Color.BLACK);
        premiumMemberDetailPanel.add(premiumMemberIdLabel);

        premiumMemberIdField = new JTextField();
        premiumMemberIdField.setBounds(195,45,380,50);
        premiumMemberIdField.setFont(pixelishLabel);
        premiumMemberIdField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,new Color(212,175,55))); //gold border
        premiumMemberDetailPanel.add(premiumMemberIdField);

        //name label and field
        premiumNameLabel = new JLabel("Name:");
        premiumNameLabel.setBounds(37,166,124,50);
        premiumNameLabel.setFont(pixelishLabel);
        premiumNameLabel.setForeground(Color.BLACK);
        premiumMemberDetailPanel.add(premiumNameLabel);

        premiumNameField = new JTextField();
        premiumNameField.setBounds(195,145,380,50);
        premiumNameField.setFont(pixelishLabel);
        premiumNameField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,new Color(212,175,55)));
        premiumMemberDetailPanel.add(premiumNameField);

        //gender selection
        premiumGenderLabel = new JLabel("Gender:");
        premiumGenderLabel.setBounds(37,265,124,30);
        premiumGenderLabel.setFont(pixelishLabel);
        premiumGenderLabel.setForeground(Color.BLACK);
        premiumMemberDetailPanel.add(premiumGenderLabel);

        premiumMaleRadioButton = new JRadioButton("Male");
        premiumMaleRadioButton.setActionCommand("Male");
        premiumMaleRadioButton.setBounds(195,265,100,30);
        premiumMaleRadioButton.setFont(pixelishLabel);
        premiumMaleRadioButton.setBackground(Color.WHITE);
        premiumMaleRadioButton.setFocusPainted(false);
        premiumMemberDetailPanel.add(premiumMaleRadioButton);

        premiumFemaleRadioButton = new JRadioButton("Female");
        premiumFemaleRadioButton.setActionCommand("Female");
        premiumFemaleRadioButton.setBounds(305,265,100,30);
        premiumFemaleRadioButton.setFont(pixelishLabel);
        premiumFemaleRadioButton.setBackground(Color.WHITE);
        premiumFemaleRadioButton.setFocusPainted(false);
        premiumMemberDetailPanel.add(premiumFemaleRadioButton);

        premiumOtherRadioButton = new JRadioButton("Other");
        premiumOtherRadioButton.setActionCommand("Other");
        premiumOtherRadioButton.setBounds(415,265,100,30);
        premiumOtherRadioButton.setFont(pixelishLabel);
        premiumOtherRadioButton.setBackground(Color.WHITE);
        premiumOtherRadioButton.setFocusPainted(false);
        premiumMemberDetailPanel.add(premiumOtherRadioButton);

        //group gender radio buttons
        premiumGenderButtonGroup = new ButtonGroup();
        premiumGenderButtonGroup.add(premiumMaleRadioButton);
        premiumGenderButtonGroup.add(premiumFemaleRadioButton);
        premiumGenderButtonGroup.add(premiumOtherRadioButton);

        //date of birth selection
        premiumDobLabel = new JLabel("Date of Birth:");
        premiumDobLabel.setBounds(37,365,124,30);
        premiumDobLabel.setFont(pixelishLabel);
        premiumDobLabel.setForeground(Color.BLACK);
        premiumMemberDetailPanel.add(premiumDobLabel);

        premiumDayComboBox = new JComboBox<>(days);
        premiumDayComboBox.setBounds(195,365,100,30);
        premiumDayComboBox.setFont(pixelishLabel);
        premiumDayComboBox.setBackground(Color.WHITE);
        premiumDayComboBox.setSelectedIndex(0);
        premiumMemberDetailPanel.add(premiumDayComboBox);

        premiumMonthComboBox = new JComboBox<>(months);
        premiumMonthComboBox.setBounds(305,365,100,30);
        premiumMonthComboBox.setFont(pixelishLabel);
        premiumMonthComboBox.setBackground(Color.WHITE);
        premiumMonthComboBox.setSelectedIndex(0);
        premiumMemberDetailPanel.add(premiumMonthComboBox);

        premiumYearComboBox = new JComboBox<>(years);
        premiumYearComboBox.setBounds(415,365,100,30);
        premiumYearComboBox.setFont(pixelishLabel);
        premiumYearComboBox.setBackground(Color.WHITE);
        premiumYearComboBox.setSelectedIndex(0);
        premiumMemberDetailPanel.add(premiumYearComboBox);

        //premium-only fields - trainer selection
        premiumTrainerLabel = new JLabel("Trainer:");
        premiumTrainerLabel.setBounds(37,465,124,30);
        premiumTrainerLabel.setFont(pixelishLabel);
        premiumTrainerLabel.setForeground(Color.BLACK);
        premiumMemberDetailPanel.add(premiumTrainerLabel);

        premiumTrainerField = new JTextField();
        premiumTrainerField.setBounds(195,465,380,50);
        premiumTrainerField.setFont(pixelishLabel);
        premiumTrainerField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,new Color(212,175,55)));
        premiumMemberDetailPanel.add(premiumTrainerField);

        //right side fields - location
        premiumLocationLabel = new JLabel("Location:");
        premiumLocationLabel.setBounds(1001,67,124,28);
        premiumLocationLabel.setFont(pixelishLabel);
        premiumLocationLabel.setForeground(Color.BLACK);
        premiumMemberDetailPanel.add(premiumLocationLabel);

        premiumLocationField = new JTextField();
        premiumLocationField.setBounds(1160,45,380,50);
        premiumLocationField.setFont(pixelishLabel);
        premiumLocationField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,new Color(212,175,55)));
        premiumMemberDetailPanel.add(premiumLocationField);

        //phone field
        premiumPhoneLabel = new JLabel("Phone:");
        premiumPhoneLabel.setBounds(1001,166,124,28);
        premiumPhoneLabel.setFont(pixelishLabel);
        premiumPhoneLabel.setForeground(Color.BLACK);
        premiumMemberDetailPanel.add(premiumPhoneLabel);

        premiumPhoneField = new JTextField();
        premiumPhoneField.setBounds(1160,145,380,50);
        premiumPhoneField.setFont(pixelishLabel);
        premiumPhoneField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,new Color(212,175,55)));
        premiumMemberDetailPanel.add(premiumPhoneField);

        //email field
        premiumEmailLabel = new JLabel("Email:");
        premiumEmailLabel.setBounds(1001,265,124,28);
        premiumEmailLabel.setFont(pixelishLabel);
        premiumEmailLabel.setForeground(Color.BLACK);
        premiumMemberDetailPanel.add(premiumEmailLabel);

        premiumEmailField = new JTextField();
        premiumEmailField.setBounds(1160,245,380,50);
        premiumEmailField.setFont(pixelishLabel);
        premiumEmailField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,new Color(212,175,55)));
        premiumMemberDetailPanel.add(premiumEmailField);

        //membership start date
        premiumMembershipStartDateLabel = new JLabel("Start Date:");
        premiumMembershipStartDateLabel.setBounds(1001,365,124,28);
        premiumMembershipStartDateLabel.setFont(pixelishLabel);
        premiumMembershipStartDateLabel.setForeground(Color.BLACK);
        premiumMemberDetailPanel.add(premiumMembershipStartDateLabel);

        premiumDayMembershipComboBox = new JComboBox<>(days);
        premiumDayMembershipComboBox.setBounds(1160,365,100,30);
        premiumDayMembershipComboBox.setFont(pixelishLabel);
        premiumDayMembershipComboBox.setBackground(Color.WHITE);
        premiumDayMembershipComboBox.setSelectedIndex(0);
        premiumMemberDetailPanel.add(premiumDayMembershipComboBox);

        premiumMonthMembershipComboBox = new JComboBox<>(months);
        premiumMonthMembershipComboBox.setBounds(1270,365,100,30);
        premiumMonthMembershipComboBox.setFont(pixelishLabel);
        premiumMonthMembershipComboBox.setBackground(Color.WHITE);
        premiumMonthMembershipComboBox.setSelectedIndex(0);
        premiumMemberDetailPanel.add(premiumMonthMembershipComboBox);

        premiumYearMembershipComboBox = new JComboBox<>(yearsMembership);
        premiumYearMembershipComboBox.setBounds(1385,365,100,30);
        premiumYearMembershipComboBox.setFont(pixelishLabel);
        premiumYearMembershipComboBox.setBackground(Color.WHITE);
        premiumYearMembershipComboBox.setSelectedIndex(0);
        premiumMemberDetailPanel.add(premiumYearMembershipComboBox);

        //premium-only fields - payment info
        premiumChargeLabel = new JLabel("Premium Charge:");
        premiumChargeLabel.setBounds(1001,465,160,28);
        premiumChargeLabel.setFont(pixelishLabel);
        premiumChargeLabel.setForeground(Color.BLACK);
        premiumMemberDetailPanel.add(premiumChargeLabel);

        premiumChargeField = new JTextField();
        premiumChargeField.setBounds(1340,445,200,50);
        premiumChargeField.setFont(pixelishLabel);
        premiumChargeField.setBorder(BorderFactory.createMatteBorder(1,1,2,2,new Color(212,175,55)));
        premiumChargeField.setEditable(false);
        premiumChargeField.setText("50000");
        premiumMemberDetailPanel.add(premiumChargeField);

        //add premium member button
        addPremiumMemberButton = new JButton("Add Premium Member");
        addPremiumMemberButton.setBounds(150,760,335,45);
        styleButton(addPremiumMemberButton, new Color(212,175,55), borderInset, pixelishLabel); //gold color
        hoverEffect(addPremiumMemberButton, new Color(212,175,55));
        premiumPanel.add(addPremiumMemberButton);
        addPremiumMemberButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    // Validate required fields
                    if (premiumMemberIdField.getText().isEmpty() || premiumNameField.getText().isEmpty() || premiumLocationField.getText().isEmpty() || premiumEmailField.getText().isEmpty() || premiumPhoneField.getText().isEmpty() || premiumGenderButtonGroup.getSelection() == null){
                        JOptionPane.showMessageDialog(mainFrame,"Please fill in all the required fields.","Empty fields", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    try{
                        int memberId = Integer.parseInt(premiumMemberIdField.getText());
                        if(memberId <= 0){
                        throw new NumberFormatException("ID less than 1");
                        }
                        // Check for duplicate member ID
                        for (GymMember member : members){
                            if (member.getId() == memberId){JOptionPane.showMessageDialog(mainFrame,"Member ID already exists!","Duplicate ID",JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }

                        // Collect all member data
                        String name = premiumNameField.getText();
                        String location = premiumLocationField.getText();
                        String email = premiumEmailField.getText();
                        String phone = premiumPhoneField.getText();
                        String gender = premiumGenderButtonGroup.getSelection().getActionCommand();
                        String pt = premiumTrainerField.getText();
                        //dates
                        String dobDate = premiumDayComboBox.getSelectedItem() + "/" + premiumMonthComboBox.getSelectedItem() + "/" + premiumYearComboBox.getSelectedItem();

                        String startDate =premiumDayMembershipComboBox.getSelectedItem() + "/" + premiumMonthMembershipComboBox.getSelectedItem() + "/" + premiumYearMembershipComboBox.getSelectedItem();

                        // Create and add new member to the arrayList
                        members.add(new PremiumMember(memberId,name,location,phone,email,gender,dobDate,startDate,pt));

                        // Show success message
                        JOptionPane.showMessageDialog(mainFrame,"Premium Member Added Successfully","Member Added",JOptionPane.INFORMATION_MESSAGE);   
                    }
                    catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(mainFrame,"Member ID must be a valid number","Invalid ID",JOptionPane.ERROR_MESSAGE);
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(mainFrame,"Error adding member: " + ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        //clear button for premium panel
        premiumClearButton = new JButton("Clear");
        premiumClearButton.setBounds(1155,760,335,45);
        styleButton(premiumClearButton, new Color(212,175,55), borderInset, pixelishLabel);
        hoverEffect(premiumClearButton, new Color(212,175,55));
        premiumPanel.add(premiumClearButton);
        premiumClearButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    premiumMemberIdField.setText("");
                    premiumNameField.setText("");
                    premiumEmailField.setText("");
                    premiumPhoneField.setText("");
                    premiumLocationField.setText("");
                    premiumTrainerField.setText("");

                    premiumDayComboBox.setSelectedIndex(0);
                    premiumMonthComboBox.setSelectedIndex(0);
                    premiumYearComboBox.setSelectedIndex(0);
                    premiumDayMembershipComboBox.setSelectedIndex(0);
                    premiumMonthMembershipComboBox.setSelectedIndex(0);
                    premiumYearMembershipComboBox.setSelectedIndex(0);

                    premiumGenderButtonGroup.clearSelection();
                    JOptionPane.showMessageDialog(mainFrame,"All fields cleared", "Fields clear", JOptionPane.INFORMATION_MESSAGE);
                }
            });

        //back button for regular panel
        backButtonLabel.addMouseListener(new MouseAdapter(){
                @Override
                //takes the user back to homePanel
                public void mouseClicked(MouseEvent e){
                    homePanel.setVisible(true);
                    regularPanel.setVisible(false);
                    mainFrame.setTitle("Gigachad Management System");

                    memberIdField.setText("");
                    nameField.setText("");
                    emailField.setText("");
                    phoneField.setText("");
                    locationField.setText("");

                    dayComboBox.setSelectedIndex(0);
                    monthComboBox.setSelectedIndex(0);
                    yearComboBox.setSelectedIndex(0);
                    dayMembershipComboBox.setSelectedIndex(0);
                    monthMembershipComboBox.setSelectedIndex(0);
                    yearMembershipComboBox.setSelectedIndex(0);

                    genderButtonGroup.clearSelection();
                };

                @Override
                //hover effect
                public void mouseEntered(MouseEvent e){
                    backButtonLabel.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e){
                    backButtonLabel.setForeground(Color.BLACK);
                    backButtonLabel.setBackground(transparentColor);
                }
            });
        //back button for premium panel
        premiumBackButtonLabel.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    premiumPanel.setVisible(false);
                    homePanel.setVisible(true);
                    mainFrame.setTitle("Gigachad Management System");
                    
                    //clears all the fields again
                    premiumMemberIdField.setText("");
                    premiumNameField.setText("");
                    premiumEmailField.setText("");
                    premiumPhoneField.setText("");
                    premiumLocationField.setText("");
                    premiumTrainerField.setText("");

                    premiumDayComboBox.setSelectedIndex(0);
                    premiumMonthComboBox.setSelectedIndex(0);
                    premiumYearComboBox.setSelectedIndex(0);
                    premiumDayMembershipComboBox.setSelectedIndex(0);
                    premiumMonthMembershipComboBox.setSelectedIndex(0);
                    premiumYearMembershipComboBox.setSelectedIndex(0);

                    premiumGenderButtonGroup.clearSelection();
                }
                //hover effect
                @Override
                public void mouseEntered(MouseEvent e){
                    premiumBackButtonLabel.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e){
                    premiumBackButtonLabel.setForeground(Color.BLACK);
                    premiumBackButtonLabel.setBackground(transparentColor);
                }
            });
        upgradePlanFrame = new JFrame("Upgrade Plan");
        upgradePlanFrame.setLayout(null);
        upgradePlanFrame.setBackground(Color.LIGHT_GRAY);
        upgradePlanFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        upgradePlanFrame.setBounds(500, 300,470,210);
        upgradePlanFrame.setResizable(false);
        upgradePlanFrame.setVisible(false);

        // ------------for upgradePlan button------------
        planComboBox = new JComboBox<>(plans);
        planComboBox.setBounds(40,30,180,70);
        planComboBox.setFont(pixelishLabel);
        planComboBox.setBackground(Color.WHITE);
        planComboBox.setSelectedIndex(0);
        upgradePlanFrame.add(planComboBox);
        planComboBox.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedPlan = (String) planComboBox.getSelectedItem();
                    if(selectedPlan.equals("Basic")){
                        upgradePriceField.setText(String.valueOf(RegularMember.getPlanPrice(selectedPlan)));
                    }
                    else if(selectedPlan.equals("Premium")){
                        upgradePriceField.setText(String.valueOf(RegularMember.getPlanPrice(selectedPlan)));
                    }
                    else{
                        upgradePriceField.setText(String.valueOf(RegularMember.getPlanPrice(selectedPlan)));
                    }
                }});
        upgradePriceField = new JTextField();
        upgradePriceField.setBounds(230,30,180,70);
        upgradePriceField.setFont(pixelishLabel);
        upgradePriceField.setBackground(Color.WHITE);
        upgradePriceField.setEditable(false);
        upgradePriceField.setText(String.valueOf(RegularMember.getPlanPrice("Basic")));
        upgradePlanFrame.add(upgradePriceField);
        
        upgradeButton = new JButton("Upgrade");
        upgradeButton.setBounds(135,120,180,40);
        styleButton(upgradeButton, darkPurple, borderInset, pixelishLabel);
        hoverEffect(upgradeButton, darkPurple);
        upgradePlanFrame.add(upgradeButton);
        upgradeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                        int memberId = Integer.parseInt(JOptionPane.showInputDialog( mainFrame, "Enter member id", "Reset regular member", JOptionPane.INFORMATION_MESSAGE));
                        boolean memberExists = false;                        
                        for (GymMember member : members) {
                            if (member.getId() == memberId) {
                                memberExists = true;
                                String plan = (String)planComboBox.getSelectedItem();
                                String currentPlan = ((RegularMember)member).getPlan();
                                if(((RegularMember)member).getIsEligibleForUpgrade()){
                                    if(currentPlan.equalsIgnoreCase(plan)){
                                    JOptionPane.showMessageDialog(mainFrame, "You are already on this plan", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    else{
                                    ((RegularMember)member).upgradePlan(plan);
                                    JOptionPane.showMessageDialog(mainFrame, "Plan upgraded successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(mainFrame, "Member is not eligible for this plan", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                break;
                            }
                        }
                        if(!memberExists){
                            JOptionPane.showMessageDialog(mainFrame, "Member id does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                    }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                catch (ClassCastException ex1){
                    JOptionPane.showMessageDialog(mainFrame, "Only regular members can be upgraded.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }});


        //switch panels: homePanel to regularPanel
        addRegularButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    regularPanel.setVisible(true);
                    homePanel.setVisible(false);
                    mainFrame.setTitle("Regular Member Registration");
                }
            });
        addPremiumButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    premiumPanel.setVisible(true);
                    homePanel.setVisible(false);
                    mainFrame.setTitle("Premium Member Registration");
                }
            });
            upgradePlanButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    upgradePlanFrame.setVisible(true);
                    planComboBox.setSelectedIndex(0);
                    upgradePriceField.setText(String.valueOf(RegularMember.getPlanPrice("Basic")));
            }});        
        homePanel.add(homeBgLabel); // adding last so it shows up last
        mainFrame.add(homePanel);
        mainFrame.add(regularPanel);
        mainFrame.add(premiumPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
    //method to add style to buttons passed as parameter
    private void styleButton(JButton button,Color backgroundColor,Insets inset,Font font){
        button.setFont(font);
        button.setMargin(inset);
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
    //method to add hover effect to buttons passed as parameter
    private void hoverEffect(JButton button, Color backgroundColor){
        //gets original border 
        Border originalBorder = button.getBorder();

        button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e){
                    button.setBorderPainted(true);
                    button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    button.setBackground(backgroundColor.brighter());
                    button.setForeground(Color.BLACK);
                }

                @Override
                public void mouseExited(MouseEvent e){
                    button.setBorderPainted(false);
                    button.setBorder(originalBorder);//for some reason setting null doesnt reset the inset bug :///////////////////
                    button.setBackground(backgroundColor);
                    button.setForeground(Color.WHITE);
                }
            });
    }
    private String getHeader(){
        String header = "--------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        header += String.format(
    "%-4s %-20s %-8s %-15s %-12s %-25s %-18s %-10s %-8s %-13s %-13s %-12s %-10s %-15s %-10s",
    "ID", "Name", "Gender", "Location", "Phone", "Email", "Membership Date", "Plan", "Price", "Attendance", "Loyalty Pts", "Active", "Discount", "Trainer", "Paid");
        return header;
    }

    public static void main(String args[]){
        GymGUI myGym = new GymGUI();
    }
}