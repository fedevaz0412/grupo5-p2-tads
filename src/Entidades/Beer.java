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
    
    public float getReviewPromedio(int cant) throws KeyNotFound {
        ListaArray<Long> idsReviews = reviewHash.getArraylistKeys();
        float sumaOverall = 0;
        float cantidadReviews = 0; 
        for(int j=0; j<idsReviews.size(); j++) {
            Long revId = idsReviews.get(j);
            Review review = reviewHash.get(revId);
            if (this.getId() == review.getBeerId()) {
                sumaOverall += review.getOverallScore();
            }
            if (review.getId() == reviewHash.get(revId)){
                cantidadReviews++
            }
        }
        float overallAvg = sumaOverall/cantidadReviews;
        return overallAvg;
    }
}
