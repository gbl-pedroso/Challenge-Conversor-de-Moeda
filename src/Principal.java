import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        String apikey = ExchangeRateConfig.getApiKey();

        System.out.println("********************************************\n" +
                "Seja bem vindo ao conversor de moedas\n\n" +
                "1) Dolar =>> Peso Argentino\n" +
                "2) Peso Argentino =>> Dolar\n" +
                "3) Dolar =>> Real Brasileiro\n" +
                "4) Real Brasileiro =>> Dolar\n" +
                "5) Dolar =>> Peso Colombiano\n" +
                "6) Peso Colombiano =>> Dolar\n" +
                "7) Sair\n" +
                "Escolha uma opção valida:\n" +
                "********************************************\n");
        int opcaoEscolhida = sc.nextInt();
        System.out.println("Digite o valor que deseja converter: ");
        double valorParaConverter = sc.nextDouble();


        String endereco = "https://v6.exchangerate-api.com/v6/" + apikey + "/pair/BRL/USD/?amount=200.0";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(URI.create(endereco)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());





        //System.out.println(response.body());

        Gson gson = new Gson();

    }
}
