package fc;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] arg) {

        System.out.println("\n");

        //-----------INICIALIZACION DE TODAS LAS VARIABLES
        int lines = 0;
        int count = 1;
        int countPosition = 0;
        String testEmail;
        StringBuilder sbNull = new StringBuilder();
        StringBuilder sbRepeated = new StringBuilder();
        StringBuilder sbErrors = new StringBuilder();

        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        List<User> users = new ArrayList<>();


        //-----------LECTURA DEL ARCHIVO .CSV || DIVIDIMOS POR "," PARA PODER DIFERENCIAR ENTRE TIPOS DE DATOS
        //-----------ALMACENAMOS LOS DATOS EN SU ARRAYLIST CORRESPONDIENTE
        try (BufferedReader br = new BufferedReader(new FileReader("src/fc/Usuarios.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0]== null || values[0].equals("") || values[0].isEmpty()) {
                    values[0] = "NULL";
                    sbNull.append("ATENCION: hay un error en el campo Email de la linea ").append(lines+1).append(" y se ha reemplazado por el texto 'NULL' ");
                }if (values[1]== null || values[1].equals("") || values[1].isEmpty()) {
                    values[1] = "NULL";
                    sbNull.append("ATENCION: hay un error en el campo Nombre de la linea ").append(lines+1).append(" y se ha reemplazado por el texto 'NULL' ");
                }if (values[2]== null || values[2].equals("") || values[2].isEmpty()) {
                    values[2] = "NULL";
                    sbNull.append("ATENCION: hay un error en el campo Usuario de la linea ").append(lines+1).append(" y se ha reemplazado por el texto 'NULL' ");
                }
                    User user = new User(values[0], values[1], values[2]);
                    users.add(lines, user);
                    lines++;

            }

        } catch (IOException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        // IMPRIMIMOS LOS ERRORES RECOLECTADO DE EL PROCESO DE LECTURA DE ARCHIVO .CSV
        System.err.println(sbNull + "\n");



        //-----------IMPRESION DE CANTIDAD DE LINEAS Y DE CADA UNO DE LOS USUARIOS
        System.out.println("\nSe han procesado un total de " + lines + " usuarios \n");
        System.out.println("A continuacion se imprimira cada uno de ellos \n");
        for (User user : users
        ) {
            System.out.println(count + " " + user);
            count++;
        }
        System.out.println("\n");

        //-----------COMPROBACION DE EMAILS REPETIDOS EN BASE DE DATOS
        for (User user : users) {
            testEmail = user.email;
            countPosition++;
            if (set.contains(testEmail)) {
                list.add(testEmail);
                sbRepeated.append("El email ").append(testEmail).append(" ubicado en la linea ").append(countPosition).append(" ya se encuentra en la base de datos");

            } else {
                set.add(testEmail);
            }
        }

        //-----------SI HAY ALGUN EMAIL REPETIDO, SE IMPRIMIRA EN LA PANTALLA EL TEXTO CON LA INFORMACION ALMACENADA DEL ERROR
        if (!sbRepeated.isEmpty()) {
            System.err.println(sbRepeated + "\n");
        }

        //-----------VACIAMOS LA LISTA QUE CONTENIA ERRORES PARA REUTILIZARLA Y REINICIAMOS EL CONTADOR
        list.clear();
        countPosition = 0;


        //-----------COMPROBACION DE ERRORES DE EMAIL  BASE DE DATOS
        for (User user : users) {
            testEmail = user.email;
            countPosition++;
            if (!testEmail.contains("@") || !testEmail.contains(".")) {
                list.add(testEmail);
                sbErrors.append("El email ").append(testEmail).append(" ubicado en la linea ").append(countPosition).append(" contiene errores");

            } else {
                set.add(testEmail);
            }
        }

        //-----------SI HAY ALGUN EMAIL CON ERRORES, SE IMPRIMIRA EN LA PANTALLA EL TEXTO CON LA INFORMACION ALMACENADA DEL ERROR
        if (!sbErrors.isEmpty()) {
            System.err.println(sbErrors + "\n");
        }
    }
}