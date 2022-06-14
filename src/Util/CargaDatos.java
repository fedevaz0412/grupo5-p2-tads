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
import java.util.Objects;

import static Util.Conversores.*;
import static Util.Conversores.stringALong;


public class CargaDatos {

    public static MyClosedHashImpl<String, User> userHash = new MyClosedHashImpl<>(1600000);

    public static MyClosedHashImpl<Long, Review> reviewHash = new MyClosedHashImpl<>(1600000);

    public static MyClosedHashImpl<Long, Beer> beerHash = new MyClosedHashImpl<>(1600000);

    public static MyClosedHashImpl<Long, Brewery> breweryHash = new MyClosedHashImpl<>(1600000);

    public static MyClosedHashImpl<String, Style> styleHash = new MyClosedHashImpl<>(1600000);

    public static void Cargar() {
        String filename = "beer_dataset_full.csv";
        Path pathToFile1 = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(pathToFile1, StandardCharsets.UTF_8)) {
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                String[] atributos = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                //CREAR ENTIDADES EN BASE A LA LÍNEA
                Beer nuevaBeer = new Beer(stringALong(atributos[13]), atributos[11], stringADouble(atributos[12]), stringALong(atributos[1]));
                User nuevoUser = new User(atributos[7]);
                Date review_date = epochToDate(atributos[3]);
                Review nuevaReview = new Review(stringALong(atributos[0]),review_date,
                        stringADouble(atributos[4]),stringADouble(atributos[5]),
                        stringADouble(atributos[6]),stringADouble(atributos[10]), //se toma review_taste como flavour
                        stringALong(atributos[13]),stringALong(atributos[1]));
                Brewery nuevaBrewery = new Brewery(stringALong(atributos[1]),atributos[2]);
                Style nuevoStyle = new Style(atributos[8]);

                //PONER NUEVOS EN SUS RESPECTIVOS TADS
                try {
                    //BEER
                    if(!Objects.equals(atributos[13], "") && !Objects.equals(atributos[11], "") && !Objects.equals(atributos[12], "") && !Objects.equals(atributos[1], "")
                            && !beerHash.contains(nuevaBeer.getId())){
                        beerHash.put(nuevaBeer.getId(), nuevaBeer);
                    }
                    //USER
                    if (!Objects.equals(atributos[7], "") && !userHash.contains(nuevoUser.getUsername())){
                        userHash.put(nuevoUser.getUsername(), nuevoUser);
                    }
                    //REVIEW
                    if (!Objects.equals(atributos[0], "") && !Objects.equals(atributos[3], "") && !Objects.equals(atributos[4], "") && !Objects.equals(atributos[5], "") &&
                            !Objects.equals(atributos[6], "") && !Objects.equals(atributos[7], "") && !Objects.equals(atributos[8], "") && !Objects.equals(atributos[1], "") &&
                            !reviewHash.contains(nuevaReview.getId())){
                        reviewHash.put(nuevaReview.getId(),nuevaReview);
                    }
                    //BREWERY
                    if (!Objects.equals(atributos[1], "") && !Objects.equals(atributos[2], "") && !breweryHash.contains(nuevaBrewery.getId())){
                        breweryHash.put(nuevaBrewery.getId(), nuevaBrewery);
                    }
                    //STYLE
                    if (!Objects.equals(atributos[8], "") && !styleHash.contains(nuevoStyle.getName())){
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