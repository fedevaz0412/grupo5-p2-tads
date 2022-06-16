package Util;

import Entidades.*;
import uy.edu.um.prog2.adt.arraylist.ListaArray;
import uy.edu.um.prog2.adt.hash.MyClosedHashImpl;
import uy.edu.um.prog2.adt.hash.exceptions.KeyNotFound;
import uy.edu.um.prog2.adt.hash.exceptions.UnavailableIndex;
import uy.edu.um.prog2.adt.heap.HeapImpl;
import uy.edu.um.prog2.adt.heap.HeapNode;
import uy.edu.um.prog2.adt.heap.exceptions.EmptyHeapException;
import uy.edu.um.prog2.adt.heap.exceptions.FullHeap;

import java.time.LocalDateTime;
import java.util.Date;

import static Util.CargaDatos.*;
import static Util.Conversores.convertToLocalDateTimeViaInstant;

public class Consultas {
    public static void Consulta1(Date year) throws KeyNotFound, UnavailableIndex, FullHeap, EmptyHeapException {
        System.out.println("Haciendo consulta 1");
        MyClosedHashImpl<Long, Review> reviewHashYear = new MyClosedHashImpl<>(1600000);//hash con reviews en el año indicado
        ListaArray<Long> idsRev = reviewHash.getArraylistKeys();
        //System.out.println("size array de idsRev: " + idsRev.size());
        LocalDateTime yearEnLocalDate = convertToLocalDateTimeViaInstant(year);
        int yearENInt = yearEnLocalDate.getYear();
        for (int i = 0; i< idsRev.size();i++){       //ORDEN N
            Long idRevCurrent = idsRev.get(i);
            Review currentRev = reviewHash.get(idRevCurrent);
            LocalDateTime revDateEnLocalDate = convertToLocalDateTimeViaInstant(currentRev.getDate());
            if (revDateEnLocalDate.getYear() == yearENInt){
                reviewHashYear.put(currentRev.getId(),currentRev);
            }
        }

        ListaArray<Long> idsRevEnYear = reviewHashYear.getArraylistKeys();
        //System.out.println("cant idsRevENYear: " + idsRevEnYear.size());
        MyClosedHashImpl<Long, Integer> hashCons1 = new MyClosedHashImpl<>(1600000);//hash con id de la brewery y cant de reviews para esa brewery
        //System.out.println("cant reviewHashYear: " + reviewHashYear.size());//hash con reviews en el año indicado
        for (int i = 0; i<idsRevEnYear.size();i++){
            Long revIdCurrent = idsRevEnYear.get(i);
            Review revCurrent = reviewHashYear.get(revIdCurrent);
            Long breweryIdCurrent = revCurrent.getBreweryId();
            if (hashCons1.contains(breweryIdCurrent)){
                int count = hashCons1.get(breweryIdCurrent);
                hashCons1.put(breweryIdCurrent,(count+1));
            }else{
                hashCons1.put(breweryIdCurrent,1);
            }
        }
        //System.out.println("hashCons1 size: " + hashCons1.size());
        //System.out.println("cant brewerys: " + breweryHash.size());
        ListaArray<Long> idsBrewerysHashCons1 = hashCons1.getArraylistKeys();
        HeapImpl<Integer, Brewery> heapTopReviews = new HeapImpl<>(hashCons1.size()*2);
        for (int i = 0; i< idsBrewerysHashCons1.size();i++){
            Long breweryIdCurrent = idsBrewerysHashCons1.get(i);
            int cant = hashCons1.get(breweryIdCurrent);
            Brewery breweryCurrent = breweryHash.get(breweryIdCurrent);
            //Integer cant = hashCons1.getPosition(i);
            heapTopReviews.insertMaxHeap(cant,breweryCurrent);
        }
        for (int z = 0; z < 10; z++) {
            try {HeapNode<Integer,Brewery> actual = heapTopReviews.delete();
                System.out.println((z+1) + ") " + "Brewery ID: " + actual.getData().getId() + "\n"
                        + "Brewery name: " + actual.getData().getName() + "\n"
                        + "Cantidad de reviews: " + actual.getKey() + "\r\n");
            }
            catch(EmptyHeapException E) {
                System.out.println("ERROR, no hay reseñas");
                break;
            }
        }

        System.out.println("Consulta 1 finalizada");
    }
    public static void Consulta2() throws KeyNotFound, UnavailableIndex, FullHeap, EmptyHeapException {
        ListaArray<Long> idsReviews = reviewHash.getArraylistKeys();
        MyClosedHashImpl<String, Integer> hashCons2 = new MyClosedHashImpl<>(1600000);//hash con el usuario y cant de reviews del usuario
        for (int i = 0; i<idsReviews.size();i++){
            Long revIdCurrent = idsReviews.get(i);
            Review revCurrent = reviewHash.get(revIdCurrent);
            String userIdCurrent = revCurrent.getUserId();
            if (hashCons2.contains(userIdCurrent)){
                int count = hashCons2.get(userIdCurrent);
                hashCons2.put(userIdCurrent,(count+1));
            }else{
                hashCons2.put(userIdCurrent,1);
            }
        }
        ListaArray<String> usersHashCons2 = hashCons2.getArraylistKeys();
        HeapImpl<Integer, User> heapTopReviews = new HeapImpl<>(hashCons2.size()*2);
        for (int i = 0; i< usersHashCons2.size();i++){
            String userCurrent = usersHashCons2.get(i);
            int cant = hashCons2.get(userCurrent);

            User usernameCurrent = userHash.get(userCurrent);
            heapTopReviews.insertMaxHeap(cant,usernameCurrent);
        }
        for (int z = 0; z < 15; z++) {
            try {HeapNode<Integer,User> actual = heapTopReviews.delete();
                System.out.println((z+1) + ") " + "User: " + actual.getData().getUsername() + "\n"
                        + "Cantidad de reviews: " + actual.getKey() + "\r\n");
            }
            catch(EmptyHeapException E) {
                System.out.println("ERROR, no hay reseñas");
                break;
            }
        }

        System.out.println("Consulta 2 finalizada");
    }

    public static void Consulta3(Date inicio, Date finalizacion) throws KeyNotFound {
        System.out.println("estoy en consulta 3");
        int count = 0;

        ListaArray<Long> idsRev = reviewHash.getArraylistKeys();
        for (int i = 0; i< idsRev.size();i++){
            Long idRevCurrent = idsRev.get(i);
            Review currentRev = reviewHash.get(idRevCurrent);
            if (currentRev.getDate().compareTo(inicio) > 0 && currentRev.getDate().compareTo(finalizacion) < 0){//está después de inicio y antes de finalización
                count++;
            }else if(currentRev.getDate().compareTo(inicio) == 0 || currentRev.getDate().compareTo(finalizacion) == 0){
                count++;
            }
        }

        System.out.println("Cantidad de reviews entre las fechas dadas: " + count);
        System.out.println("Consulta 3 finalizada");


    }

    public static void Consulta4() throws KeyNotFound, UnavailableIndex, FullHeap {
        ListaArray<Long> idsReviews = reviewHash.getArraylistKeys();
        MyClosedHashImpl<String, Style> hashCons4 = new MyClosedHashImpl<>(1600000);
        for (int i = 0; i< idsReviews.size();i++) {
            Long idRevCurrent = idsReviews.get(i);
            Review currentRev = reviewHash.get(idRevCurrent);
            long currentRevBeer = currentRev.getBeerId();
            Beer currentBeer = beerHash.get(currentRevBeer);
            String currentBeerStyle = currentBeer.getStyle();
            Style currentStyle = styleHash.get(currentBeerStyle);
            double sumaCU = currentStyle.getSuma();
            double aromaScoreCurrent = currentRev.getAromaScore();
            sumaCU = sumaCU + aromaScoreCurrent;
            currentStyle.setSuma(sumaCU);
            int countCU = currentStyle.getCount();
            countCU++;
            currentStyle.setCount(countCU);
            hashCons4.put(currentStyle.getName(),currentStyle);
        }

        ListaArray<String> idsHashCons4 = hashCons4.getArraylistKeys();
        HeapImpl<Double, Style> heapTop = new HeapImpl<>(hashCons4.size()*2);
        for (int i = 0; i<idsHashCons4.size();i++){
            String idStyleCurrent = idsHashCons4.get(i);
            Style styleCurrent = hashCons4.get(idStyleCurrent);
            double sumaCU = styleCurrent.getSuma();
            int countCU = styleCurrent.getCount();
            double promedio = sumaCU/countCU;//ver si hay que mostrar solo tamtas cifras dsp de la coma
            heapTop.insertMaxHeap(promedio,styleCurrent);
        }

        for (int z = 0; z < 7; z++) {
            try {HeapNode<Double,Style> actual = heapTop.delete();
                System.out.println((z+1) + ") " + "Style: " + actual.getData().getName() + "\n"
                        + "Puntuación: " + actual.getKey() + "\r\n");
            }
            catch(EmptyHeapException E) {
                System.out.println("ERROR, no hay puntaje");
                break;
            }
        }

        System.out.println("Consulta 4 finalizada");
    }
    public static void Consulta5() throws KeyNotFound, UnavailableIndex, FullHeap {
        ListaArray<Long> idsReviews = reviewHash.getArraylistKeys();
        MyClosedHashImpl<Long, Integer> hashCons5 = new MyClosedHashImpl<>(1600000);//hash cervezas, cant de reviews de cerveza y su promedio
        for (int i = 0; i<idsReviews.size();i++){
            Long revIdCurrent = idsReviews.get(i);
            Review revCurrent = reviewHash.get(revIdCurrent);
            long beerIdCurrent = revCurrent.getBeerId();
            if (hashCons5.contains(beerIdCurrent)){
                int count = hashCons5.get(beerIdCurrent);
                hashCons5.put(beerIdCurrent,(count+1));
            }else{
                hashCons5.put(beerIdCurrent,1);
            }
        }

        ListaArray<Long> beerHashCons5 = hashCons5.getArraylistKeys();
        HeapImpl<Integer, Beer> heapTopReviews = new HeapImpl<>(hashCons5.size()*2);
        for (int i = 0; i< beerHashCons5.size();i++){
            Long beerIdCurrent = beerHashCons5.get(i);
            int cant = hashCons5.get(beerIdCurrent);

            Beer beerCurrent = beerHash.get(beerIdCurrent);
            heapTopReviews.insertMaxHeap(cant,beerCurrent);
        }

        for (int z = 0; z < 5; z++) {
            try {HeapNode<Integer,Beer> actual = heapTopReviews.delete();
                System.out.println("Beer: " + actual.getData().getName() + "\n"
                        + "Cantidad de reviews: " + actual.getKey() + "\r\n"
                        + "AVG reviews: " + String.format("%.2f",actual.getData().getReviewPromedio());
            }
            catch(EmptyHeapException E) {
                System.out.println("ERROR, no hay reseñas");
                break;
            }
        }

        System.out.println("Consulta 5 finalizada");
    }

}
