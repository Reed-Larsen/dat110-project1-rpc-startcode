package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCClientStopStub;
import no.hvl.dat110.system.display.DisplayImpl;

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
		System.out.println("Connected to Display");
		sensorclient.connect();
		System.out.println("Connected to Sensor");

		// Løkke for å lese temperatur fra sensor og skrive til display
		for (int i = 0; i < N; i++) {
			System.out.println("Controller: Requesting temperature...");
			int temp = sensor.read();
			System.out.println("Controller: Received temperature " + temp);

			System.out.println("Controller: Sending temperature to display...");
			display.write("Temperature: " + temp);
			System.out.println("Controller: Temperature sent to display: " + temp);


			try {
				Thread.sleep(1000);
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
