package no.hvl.dat110.messaging;


import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;

public class MessagingClient {

	// name/IP address of the messaging server
	private String server;

	// server port on which the messaging server is listening
	private int port;
	
	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}

	// setup of a messaging connection to a messaging server
	public MessageConnection connect () {

		Socket clientSocket;
		MessageConnection connection = null;

		try {
			// Opprett en TCP-tilkobling til serveren
			clientSocket = new Socket(server, port);

			// Opprett en MessageConnection basert på denne sokkelen
			connection = new MessageConnection(clientSocket);
		} catch (IOException ex) {
			System.out.println("Error connecting to server: " + ex.getMessage());
			ex.printStackTrace();
		}

		return connection;
	}
}
