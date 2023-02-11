package de.fhac.rn.udp.udpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

	public static void main(String[] args) throws IOException  {
		//Starten eines UDP-Sockets
		DatagramSocket clientSocket = new DatagramSocket();
		BufferedReader input =new BufferedReader(new InputStreamReader(System.in));
		InetAddress IPAddress = InetAddress.getByName("localhost");
		//Paketgröße 1024 Bytes
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		//Solange Tastatureingaben vorhanden sind Senden
		while(true){
			String sentence = input.readLine();
			sendData = sentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
			System.out.println("die Lange der versendeten String : "+sendData.length);
			clientSocket.send(sendPacket);
            //Empfangen des vom Server zurückgeschickten Pakets und Ausgabe des Inhalts
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
			clientSocket.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData());
			System.out.println("From Server:" + modifiedSentence);
			

		}
	}

}
