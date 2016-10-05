package teamHarambe;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Debate_Management2 
{
	//creating global variables
	static String password;
	//static list of teams
	static ArrayList<String> teams = new ArrayList<String>(10);
	//dynamic list of teams for each week's schedule
	static ArrayList<String> teamSchedule = new ArrayList<String>(10);
	static Scanner in;
	
	//random number generate to select teams in the team schedule array
	public static int d10()
	{
		int max = (teamSchedule.size()) - 2;
		int min = 0;
		Random rand = new Random();
		int r = rand.nextInt((max - min) + 1) + min;
		return r;
	}
	//scanner to take interger input
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
	//scanner to take in a string input
	static String userString() throws InterruptedException 
	{
		in = new Scanner(System.in);
		String newString = null;
		try
		{
		newString = in.nextLine();
		}
		 catch(InputMismatchException e)
		{
	            System.out.println("Please type a number");
	            TimeUnit.SECONDS.sleep(1);
	            in.next();
		}
		return newString;
	}
	//method to get the super referee password
	static void passwordSet() throws InterruptedException
	{
		System.out.println("Please set a super referee password:");
		System.out.println();
		password = userString();
		System.out.println("Password is set to: " + password);
		TimeUnit.SECONDS.sleep(1);
		int userPick = 2;
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
	}
	//method to get team names and set them to the teams arraylist
	static void teamSet() throws InterruptedException
	{
		System.out.println("Please enter the ten team names, each followed by the enter key");
		for (int i = 0; i < 10; i++)
		{
			System.out.print((i+1) + ". ");
			teams.add(new String(userString()));
			System.out.println();
		}
		System.out.println("Thankyou. Your teams are: ");
		TimeUnit.SECONDS.sleep(1);
		for (int i = 0; i < teams.size(); i++)
		{
			System.out.println((i+1) + ". " + teams.get(i));
		}
	}
	//main
	public static void main( String args[]) throws InterruptedException
	{
		System.out.println("Welcome to the DM system.");
		TimeUnit.SECONDS.sleep(1);
		//set the password
		passwordSet();
		TimeUnit.SECONDS.sleep(1);
		//get the ten team names
		teamSet();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Please set start date for project");
		//start date function TBD
		TimeUnit.SECONDS.sleep(1);
		
		
		System.out.println("Thankyou. Generating Schedule...");
		TimeUnit.SECONDS.sleep(1);
		
		//String of all possible games
		String[] games = new String[45];
		
		
		
		int index = 0;
		int arindex = 0;
		//for loop to get all possible games
	    for (String team1 : teams) {
	        index++;
	        for (int j = index; j < teams.size(); ++j) {
	            System.out.println(team1 + " plays " + teams.get(j));
	            //set each game in the loop to a certain array index
	            games[arindex] = team1 + " vs " + teams.get(j);
	            arindex++;
	        }
	    }
	    //sets the dynamic arraylist for the scheduler
	    for (int i = 0; i < teams.size(); i++)
			teamSchedule.add(teams.get(i));
		//loops for scheduling each game in one week
	    //first for loop: loops once per week
		for (int i = 0; i < 9; i++)
		{
			System.out.println("Week " + (i+1) + ": ");
			TimeUnit.SECONDS.sleep(1);
			
			//second loop: loops 5 times per week for each game
			//CURRENTLY NOT CORRECT
			//does not take into account that teams only play each team one time
			for (int j = 0; j < 5; j++)
			{
				//getting random teams
				int teamOne = d10();
				int teamTwo = d10();
				//set the string for the first team for the teamschedule array
				String team1 = teamSchedule.get(teamOne);
				//remove that team from the array so it is not scheduled more than once in the same week
				teamSchedule.remove(teamOne);
				String team2 = teamSchedule.get(teamTwo);				
				teamSchedule.remove(teamTwo);
				//print the matchup
				System.out.println(team1 + " vs " + team2);
			}
			//reset the dynamic array for the next week
			for (int k = 0; k < teams.size(); k++)
				teamSchedule.add(teams.get(k));
			System.out.println();
		}
		
		
	}

}