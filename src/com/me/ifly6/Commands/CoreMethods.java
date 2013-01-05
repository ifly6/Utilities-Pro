package com.me.ifly6.Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.me.ifly6.TextProc;

public class CoreMethods extends TextProc{
	// Name: Core Methods Required for Functioning

	private static final long serialVersionUID = 1L;

	// EXECUTION STREAM
	public static void exec() throws IOException {
		Runnable runner = new Runnable() {
			public void run() {
	
				// Output Stream
				ProcessBuilder builder = new ProcessBuilder(operand);
				try {
					process = builder.start();
					log.append("Execution of Operand Beginning.");
				} catch (IOException e) { log("ProcessBuilder Error: IOException"); }
				InputStream stderr = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(stderr);
				BufferedReader br = new BufferedReader(isr);
				String line;
				try {
					while ((line = br.readLine()) != null) { append(line); }
				} catch (IOException e) { }
	
				// Error Stream
				InputStream stderr1 = process.getErrorStream();
				InputStreamReader isr1 = new InputStreamReader(stderr1);
				BufferedReader br1 = new BufferedReader(isr1);
				String line1 = null;
				try {
					while ((line1 = br1.readLine()) != null) { append(line1); }
				} catch (IOException e) { }
			}
		};
		new Thread(runner).start();
	}

	public static void help() throws IOException{
		for (int x = 0; x<10; x++){
			if (!(commText[x].equals(null))){
				append("* " + commText[x]);
			}
		}
		log("Help Processing Trigger Completed");
	}

}