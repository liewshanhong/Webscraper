import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetDescriptions {
	public static void main(String[] args) throws Exception {
		FileWriter f = new FileWriter("test.txt");
		Scanner s = new Scanner(new File("links/pilotLinks.txt"));

		// set to 0 initially
		int counter = 0;
		List<String> links = new ArrayList<String>();

		while (s.hasNextLine()) {
			links.add(s.nextLine());
		}

		System.out.println("LOADED DATA");
		// getting webpage info and saving it
		for (int i = counter; i < links.size(); i++) {

			Thread.sleep((int) Math.random() * 1000);

			try {
				Document d2 = Jsoup.connect(links.get(i).toString()).get();
				f.write(d2.getElementById("jobDescriptionText").text() + "\n");
				System.out.println(counter++);
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("RECONECT VPN");
				r();
				System.out.println("RECONNECTED");

			}
		}
		f.close();
	}

	public static void r() throws Exception {
		String command = "C:\\Program Files (x86)\\Cisco\\Cisco AnyConnect Secure Mobility Client\\vpnui.exe";
		Runtime run = Runtime.getRuntime();
		Robot r = new Robot();
		run.exec(command);

		// disconnect
		Thread.sleep(1000);

		r.mouseMove(900, 450);
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(10000);

		// reconnect
		r.mouseMove(900, 450);
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(1000);

		// passwword

	}

}