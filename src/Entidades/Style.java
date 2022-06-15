package Entidades;

public class Style {
    //Atributos
    private String name;
    private double suma;
    private int count;
    //Constructor
    public Style(String name) {
        this.name = name;
        this.suma = 0.0;//se usa en consulta 4
        this.count = 0;
    }
    //Getters
    public String getName() {
        return name;
    }

    public double getSuma() { return suma; }

    public void setSuma(double suma) { this.suma = suma; }

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }
}
