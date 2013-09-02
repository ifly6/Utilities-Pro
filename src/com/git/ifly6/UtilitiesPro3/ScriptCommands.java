package com.git.ifly6.UtilitiesPro3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Contains all relevant scripts to the Script menu in the programme.
 * 
 * @author ifly6
 * @since 3.0 All Toolbar>Script Commands go here
 */
public class ScriptCommands extends Utilities_Pro {

	/**
	 * Method runs a Runtime section to purge inactive memory. It relies on a
	 * command inside OSX, called purge.
	 * 
	 * @since 2.3_dev3
	 */
	public static void purge() {
		out("Please wait. Purging inactive memory cache...");
		ExecEngine.exec("purge");
	}

	/**
	 * It turns off then 500 milliseconds later, turns on wireless adaptor (en0)
	 * for the computer. This is nearly word for word copied from iUtilities
	 * v1.0's implementation
	 * 
	 * @since iUtilities v1.0
	 */
	public static void wireless() {
		try {
			ExecEngine.exec("networksetup -setairportpower en1 off");
			Thread.sleep(500);
			ExecEngine.exec("networksetup -setairportpower en1 on");
		} catch (InterruptedException e) {
			log("Airport Restart Failed");
		}
		String outPut = ("Airport Connection Restarted.");
		out(outPut);
		log(outPut);
	}

	/**
	 * This is a system using a runtime to get information about the system
	 * which the programme is running upon. This entire section here, is very
	 * messy. It has been pruned little since we just slammed every single thing
	 * we could think of into it.
	 * 
	 * @since 2.2
	 */
	public static void readout() {
		String[] com0 = { "ps", "ax" };
		String[] com1 = { "ifconfig" };
		String[] com2 = { "lsof", "-i" };

		log("System Readout Invoked.");
		out("Generated By: " + version + " '" + keyword + "' \n");

		try {
			out(" -- Current Running Processes -- ");
			rt.exec(com0);
			out(" -- Internet Interface Information -- ");
			rt.exec(com1);
			out(" -- Processes Information -- ");
			rt.exec(com2);
		} catch (IOException e) {
			log("Error in Running System Readout.");
		}

		// Hardware
		out("");
		out("Note: Due to the nature of Java, there may be errors in the memory readout.");
		out("Available cores: " + rt.availableProcessors());
		out("Free memory (kilobytes): " + (rt.freeMemory() / 1024));
		long maxMemory = rt.maxMemory();
		out("Max. memory (Kilobytes): " + (maxMemory / 1024));
		out("Total memory (Kilobytes): " + (rt.totalMemory() / 1024));
		File[] roots = File.listRoots();
		out("");
		for (File root : roots) {
			out("File System root: " + root.getAbsolutePath());
			out("File System Capacity (bytes): " + root.getTotalSpace());
			out("File System Free (bytes): " + root.getFreeSpace());
			out("File System Usable (bytes): " + root.getUsableSpace());
		}
		out("");
		out(System.getProperty("java.runtime.name") + " version "
				+ System.getProperty("java.runtime.version")
				+ System.getProperty("java.vm.version") + " by "
				+ System.getProperty("java.vm.vendor"));
		out("Execution Directory: " + System.getProperty("user.dir"));
		out("");
		String nameOS = "os.name";
		String versionOS = "os.version";
		out("Operating System: " + System.getProperty(nameOS) + " "
				+ System.getProperty(versionOS));
		out("User: " + System.getProperty("user.name") + " ... with Home at: "
				+ System.getProperty("user.home"));
	}

	/**
	 * Method used to download the programme "Mindterm" from mirror at
	 * ifly6.no-ip.org
	 * 
	 * @since 2.1
	 * @see {@link http://www.cryptzone.com/products/mindterm/#editionsstart}
	 */
	public static void mindterm() {
		log("Mindterm Download Commenced.");
		ExecEngine.download("http://ifly6.no-ip.org/Public/mindterm.jar",
				Downloads_DIR);
		out("Mindterm Downloaded to: "
				+ Downloads_DIR
				+ "\nThis is a full Java Based SSH/Telnet Client, capable of creating an SSH tunnel, and using it as a SOCKS proxy."
				+ "\nIt is, however, not made by the Utilities Pro Team, and therefore, does not fall under our perview.");
	}

	/**
	 * Loads and Executes a file.
	 * 
	 * @since 3.1_dev01
	 * @param file
	 *            - The file we want to load.
	 */
	public static void scriptExec(File file) {
		log("ScriptExec looking for file: " + file);

		try {
			FileReader fileReader = new FileReader(file);
			Scanner scan = new Scanner(fileReader);
			while (scan.hasNextLine()) {
				String operand = scan.nextLine();
				if (operand.startsWith("#")) {
					// Do absolutely nothing.
				} else {
					ExecEngine.exec(operand);
				}
			}
		} catch (FileNotFoundException e) {
			log("Script-File Not Found");
			out("Script-File Not Found");
		}
	}
}
