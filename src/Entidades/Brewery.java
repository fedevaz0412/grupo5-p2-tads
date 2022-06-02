package Entidades;

public class Brewery {
    //Atributos
    private long id;
    private String name;
    //Constructor
    public Brewery(long id, String name) {
        this.id = id;
        this.name = name;
    }
    //Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
