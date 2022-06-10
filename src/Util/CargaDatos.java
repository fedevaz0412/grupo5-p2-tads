package Util;

import Entidades.*;
import uy.edu.um.prog2.adt.hash.MyClosedHashImpl;
import uy.edu.um.prog2.adt.hash.exceptions.UnavailableIndex;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Util.Conversores.epochToDate;


public class CargaDatos {

    public static MyClosedHashImpl<String, User> userHash = new MyClosedHashImpl<>(1500000);

    public static MyClosedHashImpl<Long, Review> reviewHash = new MyClosedHashImpl<>(1500000);

    public static MyClosedHashImpl<Long, Beer> beerHash = new MyClosedHashImpl<>(1500000);

    public static MyClosedHashImpl<Long, Brewery> breweryHash = new MyClosedHashImpl<>(1500000);

    public static MyClosedHashImpl<String, Style> styleHash = new MyClosedHashImpl<>(1500000);

    public static void Cargar() {
        String filename = "beer_dataset_test.csv";
        Path pathToFile1 = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(pathToFile1, StandardCharsets.UTF_8)) {
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                String[] atributos = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                //CREAR ENTIDADES EN BASE A LA LÍNEA
                Beer nuevaBeer = new Beer(Long.parseLong(atributos[13]), atributos[11], Double.parseDouble(atributos[12]));
                User nuevoUser = new User(atributos[7]);
                Date review_date = epochToDate(atributos[3]);
                Review nuevaReview = new Review(Long.parseLong(atributos[0]),review_date,
                        Double.parseDouble(atributos[4]),Double.parseDouble(atributos[5]),
                        Double.parseDouble(atributos[6]),Double.parseDouble(atributos[10]), //se toma review_taste como flavour
                        Long.parseLong(atributos[13]));
                Brewery nuevaBrewery = new Brewery(Long.parseLong(atributos[1]),atributos[2]);
                Style nuevoStyle = new Style(atributos[8]);

                //PONER NUEVOS EN SUS RESPECTIVOS TADS
                try {
                    //BEER
                    if(atributos[13] != "" && atributos[11] != "" && atributos[12] != ""
                            && !beerHash.contains(nuevaBeer.getId())){
                        beerHash.put(nuevaBeer.getId(), nuevaBeer);
                    }
                    //USER
                    if (atributos[7] != "" && !userHash.contains(nuevoUser.getUsername())){
                        userHash.put(nuevoUser.getUsername(), nuevoUser);
                    }
                    //REVIEW
                    if (atributos[0] != "" && atributos[3] != "" && atributos[4] != "" && atributos[5] != "" &&
                            atributos[6] != "" && atributos[7] != "" && atributos[8] != "" &&
                            !reviewHash.contains(nuevaReview.getId())){
                        reviewHash.put(nuevaReview.getId(),nuevaReview);
                    }
                    //BREWERY
                    if (atributos[1] != "" && atributos[2] != "" && !breweryHash.contains(nuevaBrewery.getId())){
                        breweryHash.put(nuevaBrewery.getId(), nuevaBrewery);
                    }
                    //STYLE
                    if (atributos[8] != "" && !styleHash.contains(nuevoStyle.getName())){
                        styleHash.put(nuevoStyle.getName(),nuevoStyle);
                    }

                } catch (UnavailableIndex unavailableIndex) {
                    unavailableIndex.printStackTrace();
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}