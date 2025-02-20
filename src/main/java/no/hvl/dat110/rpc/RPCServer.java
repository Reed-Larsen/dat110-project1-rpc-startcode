package no.hvl.dat110.rpc;

import java.util.HashMap;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.MessageConnection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

public class RPCServer {

	private MessagingServer msgserver;
	private MessageConnection connection;
	private HashMap<Byte, RPCRemoteImpl> services;

	public RPCServer(int port) {
		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<>();
	}

	public void run() {
		RPCRemoteImpl rpcstop = new RPCServerStopImpl(RPCCommon.RPIDSTOP, this);
		System.out.println("RPC SERVER RUN - Services: " + services.size());

		connection = msgserver.accept();
		System.out.println("RPC SERVER ACCEPTED");

		boolean stop = false;

		while (!stop) {
			Message requestmsg = connection.receive();
			byte[] requestdata = requestmsg.getData();
			byte rpcid = requestdata[0];
			byte[] params = RPCUtils.decapsulate(requestdata);

			RPCRemoteImpl method = services.get(rpcid);
			byte[] response = method.invoke(params);

			byte[] replydata = RPCUtils.encapsulate(rpcid, response);
			connection.send(new Message(replydata));

			if (rpcid == RPCCommon.RPIDSTOP) {
				stop = true;
			}
		}
	}

	public void register(byte rpcid, RPCRemoteImpl impl) {
		services.put(rpcid, impl);
	}

	public void stop() {
		if (connection != null) {
			connection.close();
		}
		if (msgserver != null) {
			msgserver.stop();
		}
	}
}
