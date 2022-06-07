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


public class CargaDatos {

    public static MyClosedHashImpl<String, User> userHash = new MyClosedHashImpl<>(1500000);

    public static MyClosedHashImpl<String, Review> reviewHash = new MyClosedHashImpl<>(1500000);

    public static MyClosedHashImpl<Long, Beer> beerHash = new MyClosedHashImpl<>(1500000);

    public static MyClosedHashImpl<String, Brewery> breweryHash = new MyClosedHashImpl<>(1500000);

    public static MyClosedHashImpl<String, Style> styleHash = new MyClosedHashImpl<>(1500000);

    public static void Cargar() {
        String filename = "beer_dataset_test.csv";
        Path pathToFile1 = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(pathToFile1, StandardCharsets.UTF_8)) {
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                String[] atributos = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Beer nuevaBeer = new Beer(Long.parseLong(atributos[13]), atributos[11], Double.parseDouble(atributos[12]));

                try {
                    //VALIDAR QUE NO EXISTA O QUE SEA NULL!!!!
                    beerHash.put(nuevaBeer.getId(), nuevaBeer);
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