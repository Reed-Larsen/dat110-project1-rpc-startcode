package no.hvl.dat110.rpc;


import no.hvl.dat110.TODO;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class RPCUtils {

	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		byte[] rpcmsg = new byte[1 + payload.length];
		rpcmsg[0] = rpcid;
		System.arraycopy(payload, 0, rpcmsg, 1, payload.length);
		return rpcmsg;
	}

	public static byte[] decapsulate(byte[] rpcmsg) {
		return Arrays.copyOfRange(rpcmsg, 1, rpcmsg.length);
	}

	public static byte[] marshallString(String str) {
		return str.getBytes();
	}

	public static String unmarshallString(byte[] data) {
		return new String(data);
	}

	public static byte[] marshallVoid() {
		return new byte[0];
	}

	public static void unmarshallVoid(byte[] data) {
		// No action needed for void return type
	}

	public static byte[] marshallBoolean(boolean b) {
		byte[] encoded = new byte[1];
		encoded[0] = (byte) (b ? 1 : 0);
		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {
		return data[0] > 0;
	}

	public static byte[] marshallInteger(int x) {
		return ByteBuffer.allocate(4).putInt(x).array();
	}

	public static int unmarshallInteger(byte[] data) {
		return ByteBuffer.wrap(data).getInt();
	}
}


