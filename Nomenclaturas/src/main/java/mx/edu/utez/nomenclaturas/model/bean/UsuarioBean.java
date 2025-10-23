package mx.edu.utez.nomenclaturas.model.bean;
public class UsuarioBean {
    private int id;
    private String username;
    private String password;

    public UsuarioBean() {}

    public UsuarioBean(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

