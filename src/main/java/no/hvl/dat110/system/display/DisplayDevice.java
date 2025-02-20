package no.hvl.dat110.system.display;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.system.controller.Common;


public class DisplayDevice {

	public static void main(String[] args) {
		System.out.println("Display server starting ...");

		// Opprett RPC-server
		RPCServer displayServer = new RPCServer(Common.DISPLAYPORT);

		// Registrer DisplayImpl for handling av write RPC
		DisplayImpl display = new DisplayImpl((byte) Common.WRITE_RPCID, displayServer);

		// Start serveren og vent på forespørsler
		displayServer.run();

		// Stopp serveren når RPC-stopp blir kalt
		displayServer.stop();

		System.out.println("Display server stopping ...");
	}
}
