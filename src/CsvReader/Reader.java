package CsvReader;
import uy.edu.um.prog2.adt.arraylist.ArrayList;
import uy.edu.um.prog2.adt.arraylist.ListaArray;


public class Reader {
    public Reader(){}

    public String[] stringPlace(String places) {
        String[] separacion;
        if (places.contains("[")) {
            separacion = places.split("\\[");
            separacion = separacion[1].split(",");
        } else {
            separacion = places.split(",");
        }
        String[] returnString = new String[3];
        if (separacion.length > 2) {
            returnString[2] = separacion[separacion.length - 3];//CIUDAD
            returnString[1] = separacion[separacion.length - 2];//STATE
            returnString[0] = separacion[separacion.length - 1];//PAIS
        }
        if (separacion.length == 2) {
            returnString[0] = separacion[1];//PAIS
            returnString[1] = separacion[0];//ESTADO
            returnString[2] = null;
        }
        if (separacion.length == 1) {
            returnString[0] = separacion[0];
            returnString[1] = null;
            returnString[2] = null;
        }
        return returnString;
    }


    public ListaArray<String> listStringsComa(String initialString) {
        String[] stringPartido = initialString.split(",");
        ListaArray<String> returnList = new ArrayList<>(stringPartido.length);
        for (String s : stringPartido) {
            returnList.addLast(s);
        }
        return returnList;
    }

    public ListaArray<String> listStringsComaSinComi(String initialString) {
        String[] stringPartido = initialString.split(",");
        ListaArray<String> returnList = new ArrayList<>(stringPartido.length);
        if(stringPartido.length==3) {
            if (stringPartido[0].charAt(0) == '"') {
                stringPartido[0] = stringPartido[0].substring(1);
            }
            if (stringPartido[1].charAt(0) == ' ') {
                stringPartido[1] = stringPartido[1].substring(1);
            }
            if (stringPartido[2].charAt(stringPartido[2].length() - 1) == '"' && stringPartido[2].charAt(0) == ' ') {
                stringPartido[2] = stringPartido[2].substring(1, stringPartido[2].length() - 1);
            }
        }else if(stringPartido.length==2){
            if (stringPartido[0].charAt(0) == '"') {
                stringPartido[0] = stringPartido[0].substring(1);
            }
            if (stringPartido[1].charAt(stringPartido[1].length() - 1) == '"' && stringPartido[1].charAt(0) == ' ') {
                stringPartido[1] = stringPartido[1].substring(1, stringPartido[1].length() - 1);
            }
        }
        for (String s : stringPartido) {
            returnList.addLast(s);
        }
        return returnList;
    }
}








