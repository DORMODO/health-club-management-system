package modules;

import services.Billing;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

public class Admin extends User {
    private ArrayList<Member> members;
    private ArrayList<Coach> coaches;
    private ArrayList<Billing> bills;

    // Constructor
    public Admin(String username, String password, String ID) {
        super(username, password, ID);
        // Initialize ArrayLists
        this.members = new ArrayList<Member>();
        this.coaches = new ArrayList<Coach>();
        this.bills = new ArrayList<Billing>();
    }

    // Methods to manage members
    public void addMember(Member member) {
        if (member == null) {
            return;
        }
        // Add member to list
        members.add(member);
        // Save data
    }

    public void removeMember(Member member) {
        // Check if the member is null
        if (member == null) {
            return;
        }

        // Check if the member exists in the list
        if (!members.contains(member)) {
            return;
        }

        // Remove the member from the list
        members.remove(member);
    }

    public void updateMember(Member member, String newUsername, String newEmail) {
        // Validate the member object
        if (member == null) {
            return;
        }

        // Check if the member exists in the list
        if (!members.contains(member)) {
            return;
        }

        // Update member information
        if (newUsername != null && !newUsername.isEmpty()) {

            /* There is no setUsername in the Member class. */
            member.setUsername(newUsername);
        }

        if (newEmail != null && !newEmail.isEmpty()) {

            /* There is not setEmail in the Member class. */
            //member.setEmail(newEmail);
        }

    }

    // Methods to manage coaches
    public void addCoach(Coach coach) {
        // Validate coach
        if (coach == null) {
            return;
        }
        // Add coach to list
        coaches.add(coach);
        // Save data
    }

    public void removeCoach(Coach coach) {
        // Validate coach
        if (coach == null) {
            return;
        }

        // Check if the member exists in the list
        if (!coaches.contains(coach)) {
            return;
        }
        // Remove coach assignments from members
        // need this if any thing happend to coach data type in member class
        // for (Member member : coach.getMembers()) {
        // // Check if the member is assigned to the coach
        // if (member.getCoach() != null && member.getCoach().equals(coach.getName())) {
        // member.setCoach(null); // Clear the coach assignment for the member
        // }
        // }
        // Remove the coach from the list
        coaches.remove(coach);
        // Save data (this could be saving to a database, file, etc.)
        // Save data
    }

    public void updateCoach(Coach coach, String newName) {
        // Validate coach
        if (coach == null) {
            return;
        }
        // Check if the member exists in the list
        if (!coaches.contains(coach)) {
            return;
        }
        // Update coach information
        coach.setUsername(newName);
        // coach.setSpecialization(newSpecialization);
        // Save data
    }

    // Member-Coach assignment
    public void assignMemberToCoach(Member member, Coach coach) {
        // Validate coach
        if (coach == null || !coaches.contains(coach)) {
            // System.out.println("Invalid coach or coach not found in the system.");
            return;
        }

        // Validate member
        if (member == null || !members.contains(member)) {
            // System.out.println("Invalid member or member not found in the system.");
            return;
        }

        // Check if the member is already assigned to a coach
        if (member.getCoach() != null) {
            // System.out.println("Member is already assigned to a coach. Reassigning...");
            // Remove the member from the previous coach
            Coach previousCoach = member.getCoach();
            //previousCoach.getMembers().remove(member);
        }

        // Assign member to coach
//        if (!coach.getMembers().contains(member)) {
//            coach.getMembers().add(member);
//        } else {
//            // System.out.println("Member is already assigned to this coach.");
//        }

        // Update member's coach
        member.setCoach(coach);

        // Save data (optional implementation)
        // System.out.println("Member successfully assigned to coach: " + coach.getName());
    }


    // Billing management
    public void createBill(String billed, String memberId, double amount) {
        // Validate memberId and amount
        if (memberId == null || memberId.isEmpty()) {
            // System.out.println("Invalid member ID.");
            return;
        }
        if (amount <= 0) {
            // System.out.println("Amount must be greater than zero.");
            return;
        }

        // Generate a unique bill ID
        String billId = java.util.UUID.randomUUID().toString();

        // Create a new Bill instance
        Billing bill = new Billing(billId, memberId, amount);

        // Add the bill to the bills list
        bills.add(bill);

        // Print confirmation
        // System.out.println("Bill created successfully for member ID: " + memberId + ", Amount: $" + amount);

        // Save data (if needed)
    }

    //i dont think this logic is correct
    public void processPayment(String billId) {
        // Validate billId
        if (billId == null || billId.isEmpty()) {
            // System.out.println("Invalid bill ID.");
            return;
        }

        // Find the bill by ID
        Billing billToProcess = null;
        for (Billing bill : bills) {
            if (bill.getBillId().equals(billId)) {
                billToProcess = bill;
                break;
            }
        }

        // Check if the bill was found
        if (billToProcess == null) {
            // System.out.println("Bill with ID " + billId + " not found.");
            return;
        }

        // Process the payment
        if (billToProcess.isPaid()) {
            // System.out.println("Bill with ID " + billId + " is already paid.");
            return;
        }

        billToProcess.setPaid(true);
        // System.out.println("Payment processed successfully for bill ID: " + billId);

        // Save data (if applicable)
    }


    // ! Search functionality
    public ArrayList<Member> searchMembers(String keyword) {
        // Validate input
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>(); // Return an empty list for invalid input
        }

        // Prepare a list for matching members
        ArrayList<Member> matchingMembers = new ArrayList<>();

        // Search for matching members
//        for (Member member : members) {
//            if (member.getUsername().toLowerCase().contains(keyword.toLowerCase()) ||
//                member.getEmail().toLowerCase().contains(keyword.toLowerCase())) {
//                matchingMembers.add(member);
//            }
//        }

        return matchingMembers; // Return the list of matches
    }

    // Search coaches by name
    public ArrayList<Coach> searchCoaches(String keyword) {
        // Validate input
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>(); // Return an empty list for invalid input
        }
        ArrayList<Coach> matchingCoachs = new ArrayList<>();

        // Search for matching members
//        for (Coach coach : coaches) {
//            if (coach.getUsername().toLowerCase().contains(keyword.toLowerCase()) ||
//                coach.getEmail().toLowerCase().contains(keyword.toLowerCase())) {
//                matchingCoachs.add(coach);
//            }
//        }

        return matchingCoachs; // Return the list of matches
    }

    // Reporting
    public void generateReports() {
        // Generate member statistics
        int numberOfMembersHaveCoaches=0;
        int numberOfMembersHaveSubscription=0;
        int numberOfMembersHaveSubscriptionCurrently=0;
        Date sumOfSubscriptionTime=new Date(0);
        for (Member member:members){
            if (member.getCoach()!=null){
                numberOfMembersHaveCoaches+=1; //statistics for members that has coach
            }
            if (member.getSubscription()!=null){
                numberOfMembersHaveSubscription+=1; //statistic for members that has subscription
                Subscription subscription=member.getSubscription();
                long sumOfSubscriptionTimeInMillis = sumOfSubscriptionTime.getTime() + subscription.getEndDate().getTime() - subscription.getStartDate().getTime();
                sumOfSubscriptionTime = new Date(sumOfSubscriptionTimeInMillis);
                if (subscription.isActive()){
                    numberOfMembersHaveSubscriptionCurrently+=1;
                }
            }
        }
        long averageOfSubscriptionInMillis=sumOfSubscriptionTime.getTime()/numberOfMembersHaveSubscription;
        Date averageOfSubscriptionTime=new Date(averageOfSubscriptionInMillis);
        //the average time of subscriptions
        double percentageOfMembersHaveCoach= (double) numberOfMembersHaveCoaches /members.size()*100;
        //the percentage of members that have coach
        double percentageOfMembersHaveSubscription= (double) numberOfMembersHaveSubscription /members.size()*100;
        //the percentage of members that have subscription
        double percentageOfMembersHaveSubscriptionCurrently= (double) numberOfMembersHaveSubscriptionCurrently /members.size()*100;
        //the percentage of members that have subscription that is active

        // Generate billing statistics
        double amountOfBills=0;
        int numberOfPaidBills=0;
        for (Billing bill:bills){
            amountOfBills+=bill.getAmount();
            if (bill.isPaid()){
                numberOfPaidBills+=1;
            }
        }

        double averageAmountOfBills=amountOfBills/bills.size();
        //the average amount of bills
        double averageOfPaidBills= (double) numberOfPaidBills /bills.size()*100;
        //the average percentage of paid bills
        double averageOfNotPaidBills= (bills.size()-amountOfBills)/bills.size()*100;
        //the avergae percentage of not paid bills

        // Generate subscription statistics
        // Generate coach statistics
    }

    // Subscription management

    // Getters
    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<Coach> getCoaches() {
        return coaches ;
    }

    public ArrayList<Billing> getBills() {
        return bills;
    }
}
