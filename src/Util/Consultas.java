package Util;

import Entidades.Brewery;
import Entidades.Review;
import Entidades.Style;
import Entidades.User;
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
        MyClosedHashImpl<Long, Review> reviewHashYear = new MyClosedHashImpl<>(1600000);//hash con reviews en el año indicado
        ListaArray<Long> idsRev = reviewHash.getArraylistKeys();
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
        MyClosedHashImpl<Long, Integer> hashCons1 = new MyClosedHashImpl<>(1600000);//hash con id de la brewery y cant de reviews para esa brewery
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

        ListaArray<Long> idsBrewerysHashCons1 = hashCons1.getArraylistKeys();
        HeapImpl<Integer, Brewery> heapTopReviews = new HeapImpl<>(hashCons1.size()*2);
        for (int i = 0; i< idsBrewerysHashCons1.size();i++){
            Long breweryIdCurrent = idsBrewerysHashCons1.get(i);
            int cant = hashCons1.get(breweryIdCurrent);
            Brewery breweryCurrent = breweryHash.get(breweryIdCurrent);
            heapTopReviews.insertMaxHeap(cant,breweryCurrent);
        }
        for (int z = 0; z < 10; z++) {
            try {HeapNode<Integer,Brewery> actual = heapTopReviews.delete();
                System.out.println("Brewery ID: " + actual.getData().getId() + "\n"
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
                System.out.println("User: " + actual.getData().getUsername() + "\n"
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
            String currentRevStyle = currentRev.getBeerStyle();
            Style currentStyle = styleHash.get(currentRevStyle);
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
                System.out.println("Style: " + actual.getData().getName() + "\n"
                        + "Puntuación: " + actual.getKey() + "\r\n");
            }
            catch(EmptyHeapException E) {
                System.out.println("ERROR, no hay puntaje");
                break;
            }
        }


        System.out.println("Consulta 4 finalizada");
    }
}
