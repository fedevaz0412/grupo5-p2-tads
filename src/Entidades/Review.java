package Entidades;

import java.util.Date;

public class Review {

    //Atributos
    private long id;
    private Date date;
    private double overallScore;
    private double aromaScore;
    private double appearanceScore;
    private double flavourScore;
    private long beerId;

    //Constructor
    public Review(long id, Date date, double overallScore, double aromaScore, double appearanceScore, double flavourScore, long beerId) {
        this.id = id;
        this.date = date;
        this.overallScore = overallScore;
        this.aromaScore = aromaScore;
        this.appearanceScore = appearanceScore;
        this.flavourScore = flavourScore;
        this.beerId = beerId;

    }
    //Getters
    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public double getAromaScore() {
        return aromaScore;
    }

    public double getAppearanceScore() {
        return appearanceScore;
    }

    public double getFlavourScore() {
        return flavourScore;
    }
}
