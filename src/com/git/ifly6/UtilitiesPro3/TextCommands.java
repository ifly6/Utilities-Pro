package com.git.ifly6.UtilitiesPro3;

import java.io.File;
import java.io.IOException;

public class TextCommands extends Utilities_Pro {

	public static String preoperand = Utilities_Pro.getInputField();

	public static void processInputField() {
		preoperand = Utilities_Pro.getInputField();
		process(preoperand);
	}

	/**
	 * Processes the Text for Application-specific functions. This should be the only method called
	 * from this class. All others should be called from this class under certain conditions. All
	 * internal commands MUST begin with "/", just like in Minecraft. Updated in 3.2 to accept
	 * escape characters for space (that being '\ ').
	 * 
	 * @since 1.2
	 * @see com.me.ifly6.UtilitiesPro2.TextProc
	 */
	public static void process(String preoperand) {

		// Get and Save Command
		command(preoperand);
		addToHistory(preoperand);
		Utilities_Pro.clearText(3);

		// Split this using this Regular Expression
		String[] operand = preoperand.split("(?<!\\\\)\\s+");

		// Remove all remaining backslashes, as Java doesn't like them.
		for (int x = 0; x < operand.length; x++) {
			if (operand[x].contains("\\")) {
				operand[x] = operand[x].replace("\\", "");
			}
		}

		// Command Evaluation
		if (commText.get(0).equals(operand[0])) {
			HelpCommands.changeLog();
			log("Changelog Called");
		} else if (commText.get(1).equals(operand[0])) {
			HelpCommands.about();
			log("'About' Processing Trigger Invoked");
		} else if (commText.get(2).equals(operand[0])) {
			HelpCommands.helpList();
			log("Help Processing Trigger Invoked");
		} else if (commText.get(3).equals(operand[0])) {
			Utilities_Pro.clearText(1);
			Utilities_Pro.clearText(2);
			Utilities_Pro.clearText(3);
			log("JTextAreas Cleared");
		} else if (commText.get(4).equals(operand[0])) {
			HelpCommands.licence();
			log("EULA Displayed");
		} else if (commText.get(5).equals(operand[0])) {
			FileCommands.export(1);
			log("Invoked Export of outText");
		} else if (commText.get(6).equals(operand[0])) {
			FileCommands.export(2);
			log("Invoked Export of logText");
		} else if (commText.get(7).equals(operand[0])) {
			FileCommands.configManage(3);
			log("Called Configuration Generation thru CLI");
		} else if (commText.get(8).equals(operand[0])) {
			ScriptCommands.readout();
			log("System Information Processing Trigger Called");
		} else if ((commText.get(9)).equals(operand[0])) {
			ScriptCommands.mindterm();
			log("Mindterm Download Processing Trigger Called");
		} else if (commText.get(10).equals(operand[0])) {
			log("ScriptExecution Trigger Called");
			ExecEngine.scriptEngine(operand[1]);
		} else if ((commText.get(11)).equals(operand[0])) {
			CommandCommands.terminateChoose();
			log("Process Termination Processing Trigger Called");
		} else if (commText.get(12).equals(operand[0])) {
			System.exit(0);
			log("System.exit(0)");
		}

		/*
		 * If it is not a '/' type internal command, see if it is calling for the implementation of
		 * 'cd' or anything else like that.
		 */
		else if (operand[0].equals("cd")) {
			cd(operand);
		} else if (operand[0].equals("path")) {
			path();
		}

		/*
		 * If it is not a internal command, treat it as it it were a bash command.
		 */
		else {
			ExecEngine.exec(operand);
		}

	}

	/**
	 * The CD Subsystem. Much waiting was done for this. One epiphany later, it was solved. Updated
	 * in 3.1 to include way of dealing with spaces in filenames. However, when the entire space
	 * system was overhauled in 3.2, it became unnecessary due to the escape char for space ('\ ').
	 * Since 3.1_03, it also checks whether the DIR you are trying to go to actually exists.
	 * 
	 * @since 3.0_dev09.03
	 * @param operand
	 *            - The command which was put in. This command can begin with anything, but when
	 *            called, should only being with 'cd'.
	 */
	public static void cd(String[] operand) {
		String nonCanonical = "";

		// Deal with Files that start with '/'
		if (operand[1].startsWith("/")) {
			if (new File(operand[1]).exists()) {
				nonCanonical = operand[1];
				try {
					currentDir = new File(nonCanonical).getCanonicalPath();
				} catch (IOException e) {
					out("Changing Directory somehow failed. Report this error to GitHub.");
				}
			} else {
				out("The directory you are looking for does not exist.");
			}

		}

		// Deal with things that start with '~'
		else if (operand[1].startsWith("~")) {
			String newDir = operand[1].replaceAll("~",
					System.getProperty("user.home"));
			if (new File(newDir).exists()) {
				nonCanonical = newDir;
				try {
					currentDir = new File(nonCanonical).getCanonicalPath();
				} catch (IOException e) {
					out("Changing Directory somehow failed. Report this error to GitHub.");
				}
			} else {
				out("The directory you are looking for does not exist.");
			}

		}

		// Deal with Everything Else
		else {
			if (new File(Utilities_Pro.currentDir + "/" + operand[1]).exists()) {
				nonCanonical = Utilities_Pro.currentDir + "/" + operand[1];
				try {
					currentDir = new File(nonCanonical).getCanonicalPath();
				} catch (IOException e) {
					out("Changing Directory somehow failed. Report this error to GitHub.");
				}
			} else {
				out("The directory you are looking for does not exist.");
			}
		}

	}

	/**
	 * Display the current Path.
	 * 
	 * @since 3.1_02_dev01
	 */
	static void path() {
		out(currentDir);
	}
}
