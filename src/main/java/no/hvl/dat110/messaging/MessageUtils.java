package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;
	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		

		byte[] data = message.getData(); //Henter data fra meldingen.
		int dataLength = data.length; //Finner lengden på dataen

		if (dataLength > 127){
			throw new IllegalArgumentException("Data length exceed 127 bytes");
		}
		byte[] segment = new byte[SEGMENTSIZE];
		segment[0] = (byte) dataLength; //Setter første byte til lengden av dataen.
		System.arraycopy(data, 0, segment, 1, dataLength);

		return segment; //Returnerer det innkapslede segmentet.
		
	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;
		
		// TODO - START
		// decapsulate segment and put received payload data into a message
		
		if (true)
			throw new UnsupportedOperationException(TODO.method());
		
		// TODO - END
		
		return message;
		
	}
	
}
