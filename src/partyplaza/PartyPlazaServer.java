package partyplaza;

import java.io.IOException;
import java.net.*;

public class PartyPlazaServer {
	public static final int CLIENTPORT = 9000;
	public static final int SERVERPORT = 9001;
	private static DatagramSocket udpSocket;
	private static ServerSocket tcpSocket;
	private static Socket[] connections;
	
	
	public static void main(String[] args) throws IOException {
		udpSocket = new DatagramSocket(SERVERPORT);
		tcpSocket = new ServerSocket(SERVERPORT);
		connections = new Socket[4];
		System.out.println("Server set, waiting for clients...");
		//Wait for people to join
		while (true){
			//Get a UDP join request if you're joining
			//Get a UDP start request to stop listening for people
			DatagramPacket in = null;
			String msg = getPacket(in);
			if(msg.equals("join")){//msg is "join"
				connect();
			}
			else{break;} //If you're not joining, you want to start.
		}		
		//If if start select map
		
		
	}
	private static String getPacket(DatagramPacket in){
		byte[] inBuffer = new byte[20];
		in = new DatagramPacket(inBuffer, inBuffer.length);
		try { udpSocket.receive(in);}
		catch (IOException e) {	e.printStackTrace(); }
		return new String(in.getData());
	}
	private static void connect(){
		//If joining establish TCP connection and add that to list.
	}
	
	public DatagramSocket getUDPSocket(){ return udpSocket; }
	public ServerSocket getTCPSocket(){ return tcpSocket; }
}
