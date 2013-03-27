package com.git.ifly6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * All Commands relevant to "Help" are located here.
 * 
 * @author ifly6
 * @since 3.0_dev06
 */
public class HelpCommands extends Console {

	/**
	 * Downloads a change-log file with the latest version stated at the top. It
	 * then reads the file and displays the output.
	 * 
	 * @since 1.2
	 */
	public static void changeLog() {
		try {
			String[] url = { "curl", "-o", UtilitiesPro_DIR + "/changelog.txt",
					"http://ifly6server.no-ip.org/UtilitiesPro/changelog.txt" };
			rt.exec(url);
			Thread.sleep(500);
			FileReader fstream;
			fstream = new FileReader(UtilitiesPro_DIR + "/changelog.txt");
			Scanner scan = new Scanner(fstream);
			while (scan.hasNextLine()) {
				out(scan.nextLine());
			}
			log("Changelog Processing Trigger Completed");
		} catch (FileNotFoundException e) {
		} catch (InterruptedException e) {
		} catch (IOException e) {
		}
	}

	/**
	 * Displays about text. Displays a long string, about in a JOptionPane. It
	 * then calls the change-log method.
	 * 
	 * @since 3.0_dev07
	 * @see com.git.ifly6.HelpCommands#changelog()
	 */
	public static void about() {
		String about = ("Utilities Pro - "
				+ Console.version
				+ " '"
				+ Console.keyword
				+ "'\n"
				+ "\nUtilities Pro is a Java Runtime/ProcessBuilder tapper. "
				+ "\nIt is to serve as a terminal in restricted enviornments, such as "
				+ "\nschools or universities. Tapping Java's ProcessBuilder or Runtime"
				+ "\ncommand system, its possible to bypass MCX, and most other" + "\ncontrols on effective computer work.");
		JOptionPane.showMessageDialog(null, about);
		HelpCommands.changeLog();
	}

	/**
	 * Downloads an acknowledgements file from ifly6.no-ip.org. It then reads it
	 * and displays the output.
	 * 
	 * @since 1.3
	 */
	public static void acknowledgements() {
		try {
			String[] url = { "curl", "-o",
					UtilitiesPro_DIR + "/acknowledgements.txt",
					"http://ifly6.no-ip.org/Utilities Pro/acknowledgements.txt" };
			rt.exec(url);
			Thread.sleep(500);
			FileReader fstream;
			fstream = new FileReader(
					"/Users/"
							+ userName
							+ "/Library/Application Support/Utilities Pro/acknowledgements.txt");
			Scanner scan = new Scanner(fstream);
			while (scan.hasNextLine()) {
				out(scan.nextLine());
			}
			log("Acknowledgements Processing Trigger Completed");
		} catch (FileNotFoundException e) {
		} catch (InterruptedException e) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Displays the non-null contents of Console.commText.
	 * 
	 * @see Console.commText
	 * @since 2.3
	 */
	public static void helpList() {
		append("== Utilities Pro Internal Commands ==");
		for (String element : commText) {
			if (!(("null").equals(element))) {
				out("* " + element);
			}
		}
	}

	/**
	 * Displays the End User Licence Agreement for this programme.
	 * 
	 * @since 2.3 (though there was one in iAccelerate)
	 */
	public static void licence() {
		append("Utilities Pro Licence");
		out("* You accept all responsibility for anything caused by this programme.");
		out("* You will not change this programme to preform malicious work.");
		out("* You will credit the authors of this programme for anything based heavily upon it.");
		out("* You will not use this programme to accomplish anything illegal.");
		out("* You will not claim warranty or mandate assistance from anyone on this programme.");
		out("* You will not distribute any modified copies under the author's name.");
		out("* Any distribution of a modified version of this programme must be accompanied by public source.");
		out("* Any distribution of a modified version of this programme will be following this same licence.");
		out("By the way, if you actually read this, we are highly surprised.");

	}

	/**
	 * Opens (in default browser) a web site with an archive of A-Z OSX Bash
	 * Commands.
	 * 
	 * @since 3.0_dev06
	 */
	public static void bashHelp() {
		String[] command = { "open", "http://ss64.com/osx/" };
		ExecEngine.exec(command);
	}
}