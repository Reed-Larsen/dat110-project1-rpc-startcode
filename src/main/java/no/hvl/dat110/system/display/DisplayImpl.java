package no.hvl.dat110.system.display;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCRemoteImpl;
import no.hvl.dat110.rpc.RPCUtils;
import no.hvl.dat110.rpc.RPCServer;

public class DisplayImpl extends RPCRemoteImpl {

	public DisplayImpl(byte rpcid, RPCServer rpcserver) {
		super(rpcid, rpcserver);
	}

	public void write(String message) {
		System.out.println("DISPLAY: " + message);
	}

	@Override
	public byte[] invoke(byte[] param) {
		// Unmarshall the received string
		String message = RPCUtils.unmarshallString(param);

		// Print the message to simulate a display output
		write(message);

		// Return marshalled void response
		return RPCUtils.marshallVoid();
	}

}

