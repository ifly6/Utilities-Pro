package com.me.ifly6;

import java.io.*;

import javax.swing.text.DefaultCaret;

public class Addons extends API {

	private static final long serialVersionUID = 1L;
	static Runtime rt = Runtime.getRuntime();
	static String userName = System.getProperty("user.name");
	private static final String IUTILITIES_DIR = "/Users/" + userName + "/Library/Application Support/iUtilities";

	public static void save(String[] args) throws IOException {
		log("Output Saving System Invoked.");
		String report = output.getText();
		long time = System.currentTimeMillis();
		File f = new File(IUTILITIES_DIR);
		f.mkdirs();
		Writer output = null;
		File file = new File("/Users/" + userName + "/Library/Application Support/iUtilities/report" + time + ".txt");
		output = new BufferedWriter(new FileWriter(file));
		output.write(report);
		output.close();
		append("Contents Exported.");
	}
	public static void script() {
		append("** Checking ~/Library/Application Support/iUtilities/script/check.txt");
		// Some stuff.
	}
	public static void mindterm() throws IOException {
		File folder = new File("/Users/" + userName + "/Library/Application Support/iUtilities/");
		folder.mkdirs();
		String[] url = { "curl", "-o", "/Users/" + userName + 
				"/Library/Application Support/iUtilities/mindterm.jar", "http://ifly6server.no-ip.org/Public/mindterm.jar" };
		rt.exec(url);
		log("Mindterm Download Invoked.");
		append("Mindterm Downloaded to: " + IUTILITIES_DIR);
	}

	public static void purge(String[] args) throws IOException {
		log("Inactive Memory Purged");
		append("" + computername + "~ $ purge");
		log("Mindterm Download Invoked.");
		append("Mindterm Downloaded to: " + IUTILITIES_DIR + "\nThis is a full Java Based SSH/Telnet Client, capable of using SSH -D." +
				"\nIt is however, not made by the iUtilities Team, and therefore, does not fall under our perview.");
	}
	public static void debug(String[] args) throws IOException {
		log("iUtilities Debug Readout Command Executed");
		String debug = log.getText();
		long time = System.currentTimeMillis();
		File f = new File(IUTILITIES_DIR);
		f.mkdirs();
		Writer output = null;
		File file = new File(IUTILITIES_DIR + "/report" + time + ".txt");
		output = new BufferedWriter(new FileWriter(file));
		output.write(debug);
		output.close();
		append("Debug Contents Exported to File in: " + IUTILITIES_DIR);
	}
	public static void info(String[] args) throws InterruptedException, IOException{
		log("System Readout Invoked.");
		output.setText(null);
		append("Generated By: " + Info.version + " '" + Info.password + "' \n");
		append(" -- Current Running Processes -- ");
		String[] com = { "ps", "ax" };
		String[] com1 = { "ifconfig" };
		Process proc = rt.exec(com);
		Process proc1 = rt.exec(com1);

		InputStream stderr = proc.getInputStream();
		InputStreamReader isr = new InputStreamReader(stderr);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		while ((line = br.readLine()) != null) {
			append(line + "\n"); }

		InputStream stderr1 = proc1.getInputStream();
		InputStreamReader isr1 = new InputStreamReader(stderr1);
		BufferedReader br1 = new BufferedReader(isr1);
		String line1 = null;
		append(" -- Internet Information -- ");
		while ((line1 = br1.readLine()) != null) {
			append(" " + line1 + "\n");
		}
		
		// Hardware
		append("Available cores: " + 
				Runtime.getRuntime().availableProcessors());
		append("Free memory (bytes): " + 
				Runtime.getRuntime().freeMemory());
		long maxMemory = Runtime.getRuntime().maxMemory();
		append("Max. memory (bytes): " + (Long.valueOf(maxMemory)));
		append("Total memory (bytes): " + 
				Runtime.getRuntime().totalMemory());
		File[] roots = File.listRoots();
		append(null);
		for (File root : roots) {
			append("File system root: " + root.getAbsolutePath());
			append("FS Capacity (bytes): " + root.getTotalSpace());
			append("FS Free (bytes): " + root.getFreeSpace());
			append("FS Usable (bytes): " + root.getUsableSpace());
		}
		append(null);
		append(System.getProperty("java.runtime.name") + " version " + System.getProperty("java.runtime.version") + System.getProperty("java.vm.version") + " by " + System.getProperty("java.vm.vendor"));
		append("Execution Directory: " + System.getProperty("user.dir"));
		append(null);
		String nameOS = "os.name";
		String versionOS = "os.version";
		append("Operating System: " + System.getProperty(nameOS) + " " + System.getProperty(versionOS));
		append("User: " + System.getProperty("user.name") + " ... with Home at: " + System.getProperty("user.home"));
		append("Desktop: " + System.getProperty("sun.desktop"));
	}
	public static void defaultCarat(Object object) {
		DefaultCaret caret = (DefaultCaret)output.getCaret();
		caret.setUpdatePolicy(2);
	}
	public static void delete(String[] args) throws IOException {
		log("iUtilities Folder Deletion Commencing.");
		String[] delete = {"rm","-rf","'~/Library/Application Support/iUtilities'"};
		ProcessBuilder builder = new ProcessBuilder(delete);
		builder.start();
	}
}