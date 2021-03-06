package teamHarambe;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Calendar;

import org.json.JSONObject;

public class Server {
	static List<Team> teams = new LinkedList<>();
	static Scanner console = new Scanner(System.in);
	static Schedule schedule;
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(1234);
		JSONObject database = getDatabase();
		
		System.out.println("Server ready to accept clients.");
		
		while (true) {
			Socket s = server.accept();
			Runnable connectionHandler = new ConnectionHandler(s, database);
			new Thread(connectionHandler).start();
		}
	} 
	
	private static JSONObject getDatabase() throws IOException {
		File dbFile = new File("resources/Database.json");
		if (!dbFile.exists()) {
			dbFile.createNewFile();
			
			System.out.println("Welcome to first time setup!");
			String password = promptInitialPassword();
			
			System.out.println("How many teams will play in the debate season?");
			int numTeams = promptInt(2);
			promptTeams(numTeams);
			
			System.out.println("How many referees will manage the season?");
			int numReferees = promptInt(numTeams/2);		
			List<Referee> referees = generateRefereeList(numReferees);
			
			System.out.println("Enter the season start date.");
			Calendar startDate = promptDate();
		
			schedule = new Schedule(teams, referees);
			
			String filePath = dbFile.getPath();
			String dbString = "{\n \"Schedule\": "+ schedule.toJSON() + "\n}";
			PrintWriter writer = new PrintWriter(filePath, "UTF-8");
			writer.println(dbString);
			writer.close();
		}

		return new JSONObject(readFile("resources/Database.json", StandardCharsets.UTF_8));
	}

	private static int promptInt(int minValue) {
		int input = 0;
		
		do {
			try {
				input =  console.nextInt();
				if (input < minValue) {
					System.out.print("Please input an integer >= " + minValue + ": ");
				}
			}
			catch (NumberFormatException e) {
				System.out.print("Please input an integer >= " + minValue + ": ");
			}
		} while (input < minValue);
		
		return input;
	}
	
	private static void promptTeams(int numTeams) {
        System.out.println("Please enter " + numTeams + " team names.");
        for (int i = 0; i < numTeams; i++) {
            System.out.print((i + 1) + ". ");
            teams.add(new Team(console.next()));
        }
    }
	
	private static Calendar promptDate() {
		Calendar selectedDate = Calendar.getInstance();//shitty Java library
		int selectedYear, selectedMonth, selectedDay;
		int currentYear = selectedDate.get(Calendar.YEAR);
		int currentMonth = selectedDate.get(Calendar.MONTH);
		int currentDay = selectedDate.get(Calendar.DAY_OF_MONTH);
		
		System.out.print("Enter the season start year: ");
		selectedYear = promptInt(currentYear);
		System.out.print("Enter the season start month: ");
		selectedMonth = promptInt(selectedYear == currentYear ? currentMonth+1 : 1)-1;
		System.out.print("Enter the season start day: ");
		selectedDay = promptInt(selectedYear == currentYear && selectedMonth == currentMonth ? currentDay : 1);
		
		selectedDate.set(selectedYear, selectedMonth, selectedDay);
		
		return selectedDate;
	}
	
	private static String promptInitialPassword() {
		String password;
		System.out.print("Please create a password: ");
		
		password = console.nextLine();
		System.out.println("Your password is " + password);
		
		return password;
	}
	
	private static List<Referee> generateRefereeList(int numReferees) {
		List<Referee> referees = new LinkedList<>();
		Random r = new Random();
		
		for (int i=0; i < numReferees; i ++) {
			referees.add(new Referee(r.nextInt(999999999) + 111111111, i==0));
		}
		
		return referees;
	}
	
	private static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
