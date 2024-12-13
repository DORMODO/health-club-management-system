package modules;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Coach extends User {
    private ArrayList<Member> members;

    public Coach(String username, String password, String ID) {
        super(username, password, ID);
        this.username = username;
        this.password = password;
    }
    // -----------------------------------------------------
    // Member management methods
    // !!!!!!!! this methods should be in admin.. 3nd mohammed 
    // public void assignMember(Member member) {
    // // Check if member exists
    // // Add member to list
    // }

    // public void removeMember(Member member) {
    // // Remove member from list
    // // Remove associated training plan
    // }
    // -------------------------------------------------------
    public void createTrainingPlan(Member member, Date startDate, Date endDate) {
        if (member == null) {
            System.out.println("Invalid member.");
            return;
        }

        String trainingPlan = String.format("Training Plan for %s: Start Date: %s, End Date: %s",
                member.getUsername(),
                new SimpleDateFormat("yyyy-MM-dd").format(startDate),
                new SimpleDateFormat("yyyy-MM-dd").format(endDate));

        member.addTrainingPlan(trainingPlan);
        System.out.println("Training plan created successfully for " + member.getUsername());
    }

    // Add exercise to a member's training plan
    public void addExerciseToTrainingPlan(Member member, String exercise) {
        if (member == null) {
            System.out.println("Invalid member.");
            return;
        }

        if (exercise == null || exercise.isEmpty()) {
            System.out.println("Exercise cannot be empty.");
            return;
        }

        member.addTrainingPlan("Exercise: " + exercise);
        System.out.println("Exercise added to " + member.getUsername() + "'s training plan.");
    }

    // Set a member's schedule and associate it with their training plan
    public void setMemberSchedule(Member member, String schedule) {
        if (member == null) {
            System.out.println("Invalid member.");
            return;
        }

        if (schedule == null || schedule.isEmpty()) {
            System.out.println("Schedule cannot be empty.");
            return;
        }

        member.addTrainingPlan("Schedule: " + schedule);
        System.out.println("Schedule updated for " + member.getUsername() + ".");
    }

    // Send a message to all members
    public void sendMessageToAllMembers(String message) {
        if (message == null || message.isEmpty()) {
            System.out.println("Message cannot be empty.");
            return;
        }

        File file = new File("Notifications.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("Coach: " + message);
            writer.newLine();
            System.out.println("Message sent to all members.");
        } catch (IOException e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }

    // public TrainingPlan getTrainingPlan(Member member) {
    // // return trainingPlans.get(member.getUsername());
    // }

    public void setName(String name) {
        this.setUsername(name);
    }

    public String getName() {
        return this.getUsername();
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s", getUsername(), getPassword(), getID());
    }
}
