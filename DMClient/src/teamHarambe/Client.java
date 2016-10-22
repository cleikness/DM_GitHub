package teamHarambe;
import javafx.application.Application;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	static Socket s;
	static BufferedReader fromServer;
	static PrintStream toServer;
	static boolean serverRunning = false;

	public static void main(String[] args) throws UnknownHostException, IOException {
		try {
			s = new Socket("127.0.0.1", 1234);
			fromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
			toServer = new PrintStream(s.getOutputStream());
			serverRunning = true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Please make sure the server is running.", "Unable to pinpoint server", JOptionPane.ERROR_MESSAGE);
		}

		if (serverRunning) {

			//START INITIAL CHECK FOR SERVER SETUP
			if (MethodProvider.checkForSetup() == false) {
				Application.launch(GUI.SuperUserSetup_1.class, args);
			} else {
				Application.launch(GUI.Main_3.class, args);
			}


			toServer.println("Get_Schedule");
			System.out.println("Sent request for schedule to server.");
			while (true) {
				System.out.println(fromServer.readLine());
			}
		}
	}
}
