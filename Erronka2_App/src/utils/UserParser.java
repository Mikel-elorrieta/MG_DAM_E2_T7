package utils;

import java.util.ArrayList;
import java.util.List;
import modelo.Users;

public class UserParser {
	public static List<Users> parseUsers(Object serverResponse) {
	    List<Users> users = new ArrayList<>();

	    if (serverResponse instanceof List<?>) {
	        for (Object obj : (List<?>) serverResponse) {
	            if (obj instanceof Users) {
	                users.add((Users) obj);
	            } else {
	                System.err.println("Objeto no es una instancia de Users: " + obj.getClass().getName());
	            }
	        }
	    } else {
	        System.err.println("serverResponse no es una lista: " + serverResponse.getClass().getName());
	    }

	    return users;
	}

}

