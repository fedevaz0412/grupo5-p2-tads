import uy.edu.um.prog2.adt.hash.exceptions.KeyNotFound;
import uy.edu.um.prog2.adt.hash.exceptions.UnavailableIndex;
import uy.edu.um.prog2.adt.heap.exceptions.EmptyHeapException;
import uy.edu.um.prog2.adt.heap.exceptions.FullHeap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static Util.CargaDatos.Cargar;
import static Util.Consultas.*;
import static Util.Conversores.msAs;

public class main {
    public static long start = 0, stop = 0;

    public static void main(String[] args) throws FullHeap, UnavailableIndex, EmptyHeapException, KeyNotFound {
        main main = new main();
        boolean menu = true;
        boolean cargaDatos = false;
        
        Scanner scanner = new Scanner(System.in);

        while(menu){
            System.out.println("Menu Principal:" + "\r\n" + "Seleccione la opción que desee:" + "\r\n" + "1. Carga de Datos" + "\r\n" + "2. Ejecutar consultas" + "\r\n" + "3. Salir");
            int opcion = scanner.nextInt();

            switch(opcion) {

                case 1:
                    if(!cargaDatos) {
                        start = System.currentTimeMillis();

                        //CARGAR DATOS PARA CADA ENTIDAD
                        Cargar();

                        stop = System.currentTimeMillis();

                        cargaDatos = true;

                        System.out.println("Carga de datos exitosa, tiempo de ejecución de la carga: " + msAs(stop - start) + "s." + "\r\n");
                    }
                    else{
                        System.out.println("Los datos ya fueron cargados");
                    }
                    break;
                case 2:
                    if(cargaDatos){
                        menuConsultas();
                    }
                    else{
                        System.out.println("Error: Se deben cargar los datos antes de realizar consultas." + "\r\n");
                    }
                    break;
                case 3:
                    menu = false;
                    break;
                default:
                    System.out.println("Opcion invalida" + "\r\n");

            }

        }
    }
    public static void menuConsultas() throws FullHeap, UnavailableIndex, EmptyHeapException, KeyNotFound {
        Scanner reader = new Scanner(System.in);
        boolean menu2 = true;
        while(menu2) {
            System.out.println("1. Listar las 10 casas de cerveza (breweries) con mas resenas en un ano."
                    + "\r\n" + "2. Top 15 catadores con mas resenas."
                    + "\r\n" + "3. Cantidad de reviews en un rango dado."
                    + "\r\n" + "4. Top 7 estilos de cervezas con mejor aroma."
                    + "\r\n" + "5. Top 5 cervezas con más reviews."
                    + "\r\n" + "6. Salir");

            int entrada = reader.nextInt();

            switch (entrada) {
                case 1:
                    System.out.println("Ingrese el año en formato yyyy: ");
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy");
                    String input = reader.next();
                    boolean isDate = false;
                    String datePattern = "\\d{4}";
                    isDate = input.matches(datePattern);
                    if (isDate) {
                        try {
                            Date year = formato.parse(input);//en año queda el año con formato Date
                            start = System.currentTimeMillis();
                            Consulta1(year);
                            stop = System.currentTimeMillis();
                            System.out.println("Tiempo de ejecución: " + (msAs(stop-start)) + "s." + "\r\n");
                        } catch (ParseException | KeyNotFound | UnavailableIndex | FullHeap | EmptyHeapException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("Error introduzca año con formato: yyyy ");
                    }

                    break;
                case 2:
                    start = System.currentTimeMillis();
                    Consulta2();
                    stop = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (msAs(stop-start)) + "s." + "\r\n");
                    break;
                case 3:
                    System.out.println("Ingrese la fecha de inicio en formato dd/MM/yyyy:");
                    SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
                    String input1 = reader.next();
                    System.out.println("Ingrese la fecha de finalización:");
                    String input2 = reader.next();
                    boolean isDate1 = false;
                    boolean isDate2 = false;
                    String datePattern2 = "\\d{1,2}/\\d{1,2}/\\d{4}";
                    isDate1 = input1.matches(datePattern2);
                    isDate2 = input2.matches(datePattern2);
                    if (isDate1 && isDate2) {
                        try {
                            Date inicio = formato2.parse(input1);//queda con formato Date
                            Date finalizacion = formato2.parse(input2);//queda con formato Date
                            start = System.currentTimeMillis();
                            Consulta3(inicio,finalizacion);
                            stop = System.currentTimeMillis();
                            System.out.println("Tiempo de ejecución: " + (msAs(stop-start)) + "s." + "\r\n");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("Error introduzca las fechas con formato: dd/MM/yyyy ");
                    }

                    break;
                case 4:
                    start = System.currentTimeMillis();
                    Consulta4();
                    stop = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (msAs(stop-start)) + "s." + "\r\n");
                    break;
                case 5:
                    start = System.currentTimeMillis();
                    Consulta5();
                    stop = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (msAs(stop-start)) + "s." + "\r\n");
                    break;
                case 6:
                    menu2 = false;
                    break;
                default:
                    System.out.println("\r\n" + "Opción inválida" + "\r\n");
            }
        }
    }
}




