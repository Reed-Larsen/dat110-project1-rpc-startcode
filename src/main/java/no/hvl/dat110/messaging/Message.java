package no.hvl.dat110.messaging;

import no.hvl.dat110.TODO;

public class Message {

	// the up to 127 bytes of data (payload) that a message can hold
	private static final int MAXSIZE = 127;
	private byte[] data;

	// construction a Message with the data provided
	public Message(byte[] data) {
		if (data == null){
			throw new IllegalArgumentException("Data cannot be null");

		}
		if (data.length > MAXSIZE){
			throw new IllegalArgumentException("Data length cannot exceed" + MAXSIZE+ " bytes");
		}
		this.data = data;
	}

	public byte[] getData() {
		return this.data; 
	}

}
