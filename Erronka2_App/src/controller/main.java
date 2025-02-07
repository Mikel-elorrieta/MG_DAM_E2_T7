package controller;

import java.awt.EventQueue;
import java.util.List;

import modelo.Users;
import utils.Login;
import utils.UserParser;
import vista.MainFrame;

public class main {
	 /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
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
        });
    }

}
