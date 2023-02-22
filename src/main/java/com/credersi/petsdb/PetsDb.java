package com.credersi.petsdb;

import java.io.Closeable;
import java.util.Scanner;

import org.hsqldb.server.Server;

public class PetsDb implements Closeable {
	private static final String NAME = "Pets";
	private static final String PATH = "file:Pets";
	
	private Server server = null;
	
	public PetsDb() {
		this.start();
	}
	
	public void close() {
		if (this.server != null) {
			this.server.stop();
			this.server = null;
		}
	}
	
	public void start() {
		if (this.server == null) {
			this.server = new Server();
			this.server.setLogWriter(null);
			this.server.setSilent(true);
			this.server.setDatabaseName(0, NAME);
			this.server.setDatabasePath(0, PATH);
			this.server.start();
		}
	}
	
	public static void main(String[] args) {
		try (PetsDb db = new PetsDb(); ) {
			System.out.println("Pets database started!");
			System.out.println("Press any key to exit!");
			try (Scanner in = new Scanner(System.in)) {
				in.nextLine();
			}
		}
	}
}