package gitPrueba4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {

        int contador = 0;

        try {
            File archivoEntrada = new File("numeros.txt");
            Scanner lector = new Scanner(archivoEntrada);

            FileWriter escritor = new FileWriter("pares.txt");

            escritor.write("Números pares encontrados:\n");

            while (lector.hasNextInt()) {

                int numero = lector.nextInt();

                if (numero % 2 == 0) {
                    escritor.write(numero + "\n");
                    contador++;
                }
              
            }

            escritor.write("\nTotal de números pares: " + contador);

            lector.close();
            escritor.close();

            System.out.println("Archivo pares.txt creado correctamente");
            System.out.println(escritor);

        } catch (IOException e) {
            System.out.println("Error al trabajar con los archivos");
        }
    }
}
