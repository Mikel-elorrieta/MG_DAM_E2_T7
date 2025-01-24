package utils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import DAO.konexioa;
import modelo.Horarios;
import modelo.Users;

public class HorariosController {
    private Map<String, Integer> profesorMap;

    public HorariosController() {
        this.profesorMap = new TreeMap<>();
    }

    public Map<String, Integer> cargarProfesores() {
        Object response = konexioa.ask("getAllUsers/");

        if (response instanceof List<?>) {
            List<?> lista = (List<?>) response;

            if (!lista.isEmpty() && lista.get(0) instanceof Users) {
                for (Users profesor : (List<Users>) lista) {
                    profesorMap.put(profesor.getNombre(), profesor.getId());
                }
            }
        }
        return profesorMap;
    }

    public List<Horarios> cargarHorariosPorProfesor(int profeId) {
        Object response = konexioa.ask("getHorariosByUserId/" + profeId);

        if (response instanceof List<?> && !((List<?>) response).isEmpty() && ((List<?>) response).get(0) instanceof Horarios) {
            return (List<Horarios>) response;
        }
        return null;
    }
}
