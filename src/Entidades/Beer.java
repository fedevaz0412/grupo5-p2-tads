package Entidades;

public class Beer {
    //Atributos
    private long id;
    private String name;
    private double abv;
    //Constructor
    public Beer(long id, String name, double abv) {
        this.id = id;
        this.name = name;
        this.abv = abv;
    }
    //Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAbv() {
        return abv;
    }
}
