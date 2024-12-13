package services;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import modules.Coach;
import modules.Member;

/**
 * FileHandler class manages all file operations for the Health Club Management
 * System.
 * This class provides methods to save and load data for Members and Coaches.
 * 
 * File Format Specifications:
 * - All data is stored in comma-separated format (CSV)
 * - Member format: username,password,memberID
 * - Coach format: username,password,coachID
 * - Member IDs format: M### (e.g., M001)
 * - Coach IDs format: C### (e.g., C001)
 */
public class FileHandler {
    // ==================== File Path Constants ====================
    private static final String MEMBERS_FILE = "resources/members.txt";
    private static final String COACHES_FILE = "resources/coaches.txt";
    private static final String BILLING_FILE = "bills.txt";

    // ==================== Member Operations ====================
    /**
     * Saves a list of members to the members file.
     * Each member is stored in the format: username,password,memberID
     * 
     * @param members ArrayList of Member objects to be saved
     */
    public static void saveMemberData(ArrayList<Member> members) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBERS_FILE))) {
            // Write each member's data on a new line
            for (Member member : members) {
                writer.println(member.toString());
            }
        } catch (IOException e) {
            System.err.println("Error saving member data: " + e.getMessage());
        }
    }

    /**
     * Loads member data from the members file.
     * Expects each line to be in the format: username,password,memberID
     * 
     * @return ArrayList<Member> List of members loaded from the file
     */
    public static ArrayList<Member> loadMemberData() {
        ArrayList<Member> members = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(MEMBERS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split("/");
                // Verify data format before creating member object
                if (data.length == 3) {
                    // Create member with username, password, and ID
                    Member member = new Member(data[0], data[1], data[2]);
                    members.add(member);
                } else {
                    System.err.println("Invalid member data format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading member data: " + e.getMessage());
        }
        return members;
    }

    // ==================== Coach Operations ====================
    /**
     * Saves a list of coaches to the coaches file.
     * Each coach is stored in the format: username,password,coachID
     * 
     * @param coaches ArrayList of Coach objects to be saved
     */
    public static void saveCoachData(ArrayList<Coach> coaches) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COACHES_FILE))) {
            // Write each coach's data on a new line
            for (Coach coach : coaches) {
                writer.println(coach.toString());
            }
        } catch (IOException e) {
            System.err.println("Error saving coach data: " + e.getMessage());
        }
    }

    /**
     * Loads coach data from the coaches file.
     * Expects each line to be in the format: username,password,coachID
     * 
     * @return ArrayList<Coach> List of coaches loaded from the file
     */
    public static ArrayList<Coach> loadCoachData() {
        ArrayList<Coach> coaches = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(COACHES_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split("/");
                // Verify data format before creating coach object
                if (data.length == 3) {
                    // Create coach with username, password, and ID
                    Coach coach = new Coach(data[0], data[1], data[2]);
                    coaches.add(coach);
                } else {
                    System.err.println("Invalid coach data format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading coach data: " + e.getMessage());
        }
        return coaches;
    }
}