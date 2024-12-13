package demo;

import services.FileHandler;
import modules.Member;
import modules.Coach;
import java.util.ArrayList;

public class FileHandlerDemo {
    public static void main(String[] args) {
        // Demo for Member operations
        demonstrateMemberOperations();

        // Demo for Coach operations
        demonstrateCoachOperations();
    }

    private static void demonstrateMemberOperations() {
        System.out.println("=== Member File Operations Demo ===");
        
        // Create some sample members
        ArrayList<Member> members = new ArrayList<>();
        members.add(new Member("member10", "pass1", "M001"));
        members.add(new Member("member20", "pass2", "M002"));
        members.add(new Member("member30", "pass3", "M003"));

        // Save members to file
        System.out.println("Saving members to file...");
        FileHandler.saveMemberData(members);
        System.out.println("Members saved successfully!\n");

        // Load members from file
        System.out.println("Loading members from file...");
        ArrayList<Member> loadedMembers = FileHandler.loadMemberData();
        
        // Display loaded members
        System.out.println("\nLoaded Members:");
        System.out.println("+-----------+----------+---------+");
        System.out.println("| Username  | Password |   ID    |");
        System.out.println("+-----------+----------+---------+");
        for (Member member : loadedMembers) {
            String[] data = member.toString().split("/");
            System.out.printf("| %-9s | %-8s | %-7s |\n", data[0], data[1], data[2]);
        }
        System.out.println("+-----------+----------+---------+");
    }

    private static void demonstrateCoachOperations() {
        System.out.println("\n=== Coach File Operations Demo ===");

        // Create some sample coaches
        ArrayList<Coach> coaches = new ArrayList<>();
        coaches.add(new Coach("coach1", "cPass1", "C001"));
        coaches.add(new Coach("coach2", "cPass2", "C002"));
        coaches.add(new Coach("coach3", "cPass3", "C003"));

        // Save coaches to file
        System.out.println("Saving coaches to file...");
        FileHandler.saveCoachData(coaches);
        System.out.println("Coaches saved successfully!\n");

        // Load coaches from file
        System.out.println("Loading coaches from file...");
        ArrayList<Coach> loadedCoaches = FileHandler.loadCoachData();

        // Display loaded coaches
        System.out.println("\nLoaded Coaches:");
        System.out.println("+-----------+----------+---------+");
        System.out.println("| Username  | Password |   ID    |");
        System.out.println("+-----------+----------+---------+");
        for (Coach coach : loadedCoaches) {
            String[] data = coach.toString().split("/");
            System.out.printf("| %-9s | %-8s | %-7s |\n", data[0], data[1], data[2]);
        }
        System.out.println("+-----------+----------+---------+");
    }
}
