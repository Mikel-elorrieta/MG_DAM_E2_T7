package controller;

import java.util.List;

import modelo.Users;
import utils.Login;
import utils.UserParser;
import vista.MainFrame;
import vista.MenuFrame;
import vista.OrdutegiFrame;

public class FrameMugimendu {
	public static void menuraJoan() {
		try {
			MenuFrame frame = new MenuFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public static void loginJoan() {
		try {
			DAO.konexioa.connect();
            Object serverResponse = DAO.konexioa.ask("getAllUsers/");
            System.out.println(serverResponse);

            List<Users> users = UserParser.parseUsers(serverResponse);

            Login login = new Login(users);
			MainFrame frame = new MainFrame(login);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void ordutegiJoan() {
		try {
			OrdutegiFrame frame = new OrdutegiFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void besteOrdutegiJoan() {
		try {
			vista.BesteOrdutegiFrame frame = new vista.BesteOrdutegiFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void bileraJoan() {
		try {
			vista.BilerakFrame frame = new vista.BilerakFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
