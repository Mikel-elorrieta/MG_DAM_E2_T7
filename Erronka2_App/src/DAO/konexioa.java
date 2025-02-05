package DAO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class konexioa {
	private static Socket server;
	private static ObjectInputStream ois;
	private static DataOutputStream dos;

	public static void connect() {
		try {
			//server = new Socket("10.5.104.55", 10000);
			server = new Socket("10.5.104.61", 20000);
			System.out.println("Konexioa lortu da");
		} catch (UnknownHostException e) {
			System.out.println("Host-a ez da aurkitu");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Portua ez da aurkitu");
			e.printStackTrace();
		}

	}

	public static Object ask(String key) {
		connect();
		Object obj = null;
		try {
			dos = new DataOutputStream(server.getOutputStream());
			dos.writeUTF(key);
			
			ois = new ObjectInputStream(server.getInputStream());
			obj = ois.readObject();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println();	
			e.printStackTrace();
		}

		return obj;

	}
	
	public static void main(String[] args) {
		System.out.println(ask("getBilerakByUserId/4"));
//		System.out.println(ask("getHorariosByAlumnoId/3"));
	}
}
