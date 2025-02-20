package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCClientStopStub;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) {

		System.out.println("Controller starting ...");

		// Opprett RPC-klienter for sensor og display
		RPCClient displayclient = new RPCClient(Common.DISPLAYHOST, Common.DISPLAYPORT);
		RPCClient sensorclient = new RPCClient(Common.SENSORHOST, Common.SENSORPORT);

		// Opprett stubber for sensor og display
		DisplayStub display = new DisplayStub(displayclient);
		SensorStub sensor = new SensorStub(sensorclient);

		// Koble til RPC-servere
		displayclient.connect();
		sensorclient.connect();

		// Løkke for å lese temperatur fra sensor og skrive til display
		for (int i = 0; i < N; i++) {
			int temp = sensor.read();
			display.write("Temperature: " + temp);

			try {
				Thread.sleep(1000); // Simulerer en oppdatering hvert sekund
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Stopp servere via RPC-stubber
		RPCClientStopStub stopdisplay = new RPCClientStopStub(displayclient);
		RPCClientStopStub stopsensor = new RPCClientStopStub(sensorclient);
		stopdisplay.stop();
		stopsensor.stop();

		// Koble fra RPC-servere
		displayclient.disconnect();
		sensorclient.disconnect();

		System.out.println("Controller stopping ...");
		
	}
}
