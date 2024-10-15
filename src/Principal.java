import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        List <Conversor> conversor1 = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();;


        while(true) {
            System.out.println("Bienvenido/a, Por favor selecciona una opcion : ");
            System.out.println("******************************************* ");
            System.out.println("* Menu Conversor de monedas :             * ");
            System.out.println("* 1- Codigos de las monedas de los paises * ");
            System.out.println("* 2- Convertir monedas                    * ");
            System.out.println("* 3- Historial de conversion.             * ");
            System.out.println("* 4- Salir.                               * ");
            System.out.println("******************************************* ");

            int busqueda = Integer.parseInt(lectura.nextLine());

            if (busqueda == 1) {
                System.out.println("Estos son los codigos de las monedas de  los paises : ");
                String codigos = "https://v6.exchangerate-api.com/v6/1375b39a939fc04a91e394e2/codes/";

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(codigos))
                        .build();

                HttpClient client = HttpClient.newHttpClient();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();


                JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
                JsonArray supportedCodes = jsonObject.getAsJsonArray("supported_codes");

                List<Codigos> codigo = new ArrayList<>();
                for (JsonElement elemento : supportedCodes) {
                    JsonArray codigoNombre = elemento.getAsJsonArray();
                    Codigos codigos1 = new Codigos();
                    codigos1.setCodigo(codigoNombre.get(0).getAsString());
                    codigos1.setNombre(codigoNombre.get(1).getAsString());
                    codigo.add(codigos1);
                }

                for (Codigos codigos1 : codigo) {
                    System.out.println(codigos1.getCodigo() + "-" + codigos1.getNombre());
                }
            }
               if (busqueda == 2) {
                   System.out.println("Ingrese el codigo del la moneda del pais de origen: ");
                   Scanner moneda = new Scanner(System.in);
                   var moneda1 = moneda.nextLine();

                   System.out.println("Ingrese el codigo de la moneda del pais de destino : ");
                   Scanner monedad = new Scanner(System.in);
                   var moneda2 = monedad.nextLine();

                   System.out.println("Ingrese el valor a convertir (sin puntos ni comas): ");
                   Scanner valor = new Scanner(System.in);
                   String valor1 = valor.nextLine();


                   String conver = "https://v6.exchangerate-api.com/v6/1375b39a939fc04a91e394e2/pair/"
                                    + moneda1 +"/" + moneda2 + "/" + valor1 ;

                   HttpRequest request = HttpRequest.newBuilder()
                           .uri(URI.create(conver))
                           .build();

                   HttpClient client = HttpClient.newHttpClient();

                   HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                   String json = response.body();


                   ConversorExch conversorExch = gson.fromJson(json,ConversorExch.class);
                   try {
                       Conversor conversor = new Conversor(conversorExch);
                       System.out.println(conversor);

                       conversor1.add(conversor);

                   } catch (Exception e) {
                       System.out.println("ocurrio un error uno  o mas de los codigos no es valido. ");
                       System.out.println(e.getMessage());
                       System.out.println("puedes usar la opcion 1 del menu para conocer los codigos validos.");

                   }


               }
               if (busqueda == 3) {
                   System.out.println(conversor1);
                   FileWriter escritura = new FileWriter("conversiones.json");

                   escritura.write(gson.toJson(conversor1));
                   escritura.close();
               }
               if (busqueda == 4) {
                break;
               }

        }
        System.out.println("Finalizo el programa Gracias por utilizarlo");
    }


}
