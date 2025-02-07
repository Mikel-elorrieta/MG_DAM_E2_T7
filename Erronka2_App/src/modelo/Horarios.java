package modelo;

public class Horarios implements java.io.Serializable {
	
	private static final long serialVersionUID = 8261138074800654137L;
    private HorariosId id;
    private Users users;
    private Modulos modulos;

    public Horarios() {
    }

    public Horarios(HorariosId id, Users users, Modulos modulos) {
        this.id = id;
        this.users = users;
        this.modulos = modulos;
    }

    public HorariosId getId() {
        return this.id;
    }

    public void setId(HorariosId id) {
        this.id = id;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Modulos getModulos() {
        return this.modulos;
    }

    public void setModulos(Modulos modulos) {
        this.modulos = modulos;
    }

	@Override
	public String toString() {
		return "Horarios [id=" + id + ", users=" + users + ", modulos=" + modulos + "]";
	}
    
    

}