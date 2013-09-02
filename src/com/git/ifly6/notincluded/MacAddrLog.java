package com.git.ifly6.notincluded;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

class Personal {

	/**
	 * Makes sure that there is a secure place to log the MAC Addr to.
	 * 
	 * @since 3.0_dev09.02
	 */
	public void parameters() {
		File element = new File(Personal.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath());
		String file = element.getPath();
		System.out.println(file);
		if (file.equals("/Volumes/Kevin%20Encrypted/applications/Kevin%20Wong's%20Apps/UtilitiesPro-3.0.jar")) {
			System.out.println("Attempting getData().");
			getData();
		} else {
			System.out
					.println("MAC AddrLog Err: NOTRUN. No secure place to log.");
		}
	}

	/**
	 * Gets the data, then sends to Personal.write.
	 * 
	 * @since 3.0_dev09.02
	 */
	public void getData() {
		StringBuilder sb = new StringBuilder();

		try {
			// Get
			InetAddress ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i],
						(i < (mac.length - 1)) ? "-" : ""));
			}
		} catch (UnknownHostException e) {
			System.out.println("Error. GetData. UnknownHost.");
		} catch (SocketException e) {
			System.out.println("Error. GetData. Socket.");
		}

		// Write
		String macAddr = sb.toString();
		write(new File(".").getAbsolutePath() + "/misc/outFile.txt",
				System.getProperty("user.name") + macAddr);
	}

	/**
	 * Writes the data to secure location.
	 * 
	 * @since 3.0_dev09.02
	 */
	public void write(String directory, String contents) {
		FileWriter fstream;
		try {
			fstream = new FileWriter(directory, true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(contents);
			out.close();
		} catch (IOException e) {
			System.out.println("Error. Write.");
		}
	}
}