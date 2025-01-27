
package utils;

import java.util.ArrayList;
import java.util.List;
import modelo.Users;

public class UserParser {
    public static List<Users> parseUsers(Object serverResponse) {
       
        List<Users> users = new ArrayList<>(); // Crear una lista vacía para almacenar los objetos Users

        if (serverResponse instanceof List<?>) { // Verificar si la respuesta del servidor es una instancia de List

            for (Object obj : (List<?>) serverResponse) { // Iterar sobre cada elemento en la lista
               
                if (obj instanceof Users) { // Verificar si el elemento es una instancia de Users
                    
                    users.add((Users) obj); // Agregar el objeto Users a la lista
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
