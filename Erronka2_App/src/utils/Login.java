package utils;

import java.util.List;
import modelo.Users;


public class Login {

	private List<Users> users;

	public Login(List<Users> users) {
		this.users = users;
	}

	public boolean balidatu(String username, String password) {
		for (Users user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				if (user.getTipos() != null && "profesor".equalsIgnoreCase(user.getTipos().getName())) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
