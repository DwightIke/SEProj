package main.model;

/**
 * Created by Cosmin on 5/13/2017...
 */
public class CM {

    public CM(int id, String username, String password, String name, String affiliation, String email, String webpage) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.affiliation = affiliation;
        this.email = email;
        this.webpage = webpage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    private int id;
    private String username;
    private String password;
    private String name;
    private String affiliation;
    private String email;
    private String webpage;

}
