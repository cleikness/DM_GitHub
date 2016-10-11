package teamHarambe;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Debate_Management {

    static String password;
    static Scanner in;

    static List<Team> teamlist = new LinkedList<>();
    static List<Match> matchlist = new LinkedList<>();
    static List<Match> organizedlist = new LinkedList<>();
    static List<Match> schedulelist = new LinkedList<>();
    static boolean add = false;
    static boolean generated = false;


    public static void main(String args[]) throws InterruptedException {
        System.out.println("Welcome to the DM system.");
        TimeUnit.SECONDS.sleep(1);
        passwordSet();
        teamSet();
        createSchedule();
    }

    static String userString() throws InterruptedException {
        in = new Scanner(System.in);
        String newString = null;
        try {
            newString = in.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please type a number");
            TimeUnit.SECONDS.sleep(1);
            in.next();
        }
        return newString;
    }


    static void teamSet() throws InterruptedException {
        System.out.println("Please enter the ten team names, each followed by the enter key");
        for (int i = 0; i < 10; i++) {
            System.out.print((i + 1) + ". ");
            teamlist.add(new Team(userString(), 0, false));
        }
        System.out.println("Thank you. Your teams are: ");
        TimeUnit.SECONDS.sleep(1);
        for (int i = 0; i < teamlist.size(); i++) {
            System.out.println((i + 1) + ". " + teamlist.get(i).getName());
        }
    }

    static void createSchedule() throws InterruptedException {
        Schedule schedule = new Schedule(teamlist);
        System.out.println("Schedule: ");
        System.out.println(schedule.toString());
            }


    static int userPick() throws InterruptedException
    {
        in = new Scanner(System.in);
        int choice = 0;
        try
        {
            choice = in.nextInt();
        }
        catch(InputMismatchException e)
        {
            System.out.println("Please input a number");
            TimeUnit.SECONDS.sleep(1);
            in.next();
        }
        return choice;
    }

    static void passwordSet() throws InterruptedException
    {
        System.out.println("Please set a super referee password:");
        System.out.println();
        password = userString();
        System.out.println("Password is set to: " + password);
        TimeUnit.SECONDS.sleep(1);
        int userPick;
        System.out.println("Are you sure you want to use this password?\n1. yes\n2. no");
        userPick = userPick();
        while (userPick != 1)
        {
            System.out.println("Please set a super referee password:");
            System.out.println();
            password = userString();
            System.out.println("Password is set to: " + password);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Are you sure you want to use this password?\n1. yes\n2. no");
            userPick = userPick();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Password is set");
        TimeUnit.SECONDS.sleep(1);
    }

    static Match getFreshMatch(List<Match> e)
    {
        for (Match m : e) {
            if (!m.getTeam1().isPlayingCurrently && !m.getTeam2().isPlayingCurrently) {
                return m;
            }
        }
        return null;
    }

    static void resetIsPlaying(List<Match> e)
    {
        for (Match m : e)
        {
            m.getTeam1().isPlayingCurrently = false;
            m.getTeam2().isPlayingCurrently = false;
        }
    }
        }
