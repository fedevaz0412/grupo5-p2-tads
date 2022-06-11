package Util;

import Entidades.Review;
import uy.edu.um.prog2.adt.arraylist.ListaArray;
import uy.edu.um.prog2.adt.hash.MyClosedHashImpl;
import uy.edu.um.prog2.adt.hash.exceptions.KeyNotFound;
import uy.edu.um.prog2.adt.hash.exceptions.UnavailableIndex;

import java.time.LocalDateTime;
import java.util.Date;

import static Util.CargaDatos.breweryHash;
import static Util.CargaDatos.reviewHash;
import static Util.Conversores.convertToLocalDateTimeViaInstant;

public class Consultas {
    public static void Consulta1(Date year) throws KeyNotFound, UnavailableIndex {
        System.out.println("Haciendo consulta 1");
        MyClosedHashImpl<Long, Review> reviewHashYear = new MyClosedHashImpl<>(1500000);//hash con reviews en el año indicado
        ListaArray<Long> idsRev = reviewHash.getArraylistKeys();
        System.out.println("size array de idsRev: " + idsRev.size());
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
        System.out.println("cant idsRevENYear: " + idsRevEnYear.size());
        MyClosedHashImpl<Long, Integer> hashCons1 = new MyClosedHashImpl<>(1500000);//hash con id de la brewery y cant de reviews para esa brewery
        System.out.println("cant reviewHashYear: " + reviewHashYear.size());//hash con reviews en el año indicado
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
        System.out.println("hashCons1 size: " + hashCons1.size());
        System.out.println("cant brewerys: " + breweryHash.size());

        System.out.println("Consulta 1 finalizada");
    }

    public static void Consulta3(Date inicio, Date finalizacion){
        System.out.println("estoy en consulta 3");
        System.out.println(inicio);
        System.out.println(finalizacion);
    }
}
