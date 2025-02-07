package utils;

import java.util.List;

import DAO.konexioa;
import modelo.Reuniones;
import modelo.Users;

public class ReunionesController {

    /**
     * Cargar las reuniones por ID de profesor.
     * 
     * @param profesorId ID del profesor.
     * @return Lista de reuniones asociadas al profesor.
     */
    public static List<Reuniones> cargarReunionesPorProfesor(int profesorId) {
        Object response = konexioa.ask("getBilerakByProfesorId/" + profesorId);

        if (response instanceof List<?> && !((List<?>) response).isEmpty() && ((List<?>) response).get(0) instanceof Reuniones) {
            return (List<Reuniones>) response;
		} else {
			System.out.println("Error al cargar las reuniones");
		}
        return null;
    }

	
    public static void actualizarEstado(String id, String estado) {
    	Object response = konexioa.ask("updateStatus/" + estado + "/" + id);	
		if (response instanceof Boolean && (Boolean) response) {
			System.out.println("Estado actualizado correctamente");
		} else {
			System.out.println("Error al actualizar el estado");
		}
    	
	}
}

