import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getLinks {
	public static void main(String[] args) throws IOException, InterruptedException {
		FileWriter f = new FileWriter("links/saleLinks.txt");
		int k = 960;

		// initial url
		String z = "https://www.indeed.com/jobs?q=sales&l=California&filter=0&start=0";

		// connecting to url above

		int counter = 9688;

		while (counter < 10000) {
			Document doc = Jsoup.connect(z).get();
			boolean load = false;
			System.out.println("Break");
			Thread.sleep(1000);
			System.out.println("Resume");
			List<Object> links = new ArrayList<Object>();
			// gets job title and link
			try {
				for (Element e : doc.getElementsByClass("tapItem")) {
					load = true;
					System.out.println(counter++);
					// links.add("https://www.indeed.com" + e.select("a[href]").attr("href"));
					f.write("https://www.indeed.com" + e.select("a[href]").attr("href") + "\n");
				}
				if (load == true) {
					k += 10;
					z = "https://www.indeed.com/jobs?q=sales&l=California&filter=0&start=" + k;
				} else {
					System.out.println("RECONNECT TO VPN");
					// r();
					Scanner in = new Scanner(System.in);
					in.nextLine();
					r();
				}
			} catch (Exception e) {
				System.out.println(e);

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

	}
}