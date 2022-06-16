package Entidades;

import uy.edu.um.prog2.adt.arraylist.ListaArray;
import uy.edu.um.prog2.adt.hash.exceptions.KeyNotFound;

import static Util.CargaDatos.reviewHash;

public class Beer {
    //Atributos
    private long id;
    private String name;
    private double abv;
    private long idBrewery;
    private String style;
    //Constructor
    public Beer(long id, String name, double abv, long idBrewery, String style) {
        this.id = id;
        this.name = name;
        this.abv = abv;
        this.idBrewery = idBrewery;
        this.style = style;
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

    public long getIdBrewery() { return idBrewery; }

    public String getStyle() { return style; }
}
