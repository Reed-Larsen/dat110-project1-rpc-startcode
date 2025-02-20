package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}

	public void write(String message) {
		// Marshalling String to byte array
		byte[] request = RPCUtils.marshallString(message);

		// Making the remote procedure call for write
		byte[] response = rpcclient.call((byte) Common.WRITE_RPCID, request);

		// Unmarshalling the return value (void, no actual value expected)
		RPCUtils.unmarshallVoid(response);
	}
		
}

