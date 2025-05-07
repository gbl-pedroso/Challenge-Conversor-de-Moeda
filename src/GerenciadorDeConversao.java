import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.MoedaDTO;
import exceptions.ConnectionApiFailException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.core.*;
public class GerenciadorDeConversao {


    public MoedaDTO buscaConversao(String endereco) {

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(endereco)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {

                throw new ConnectionApiFailException("Erro ao se conectar com a API do ExchangeRate");
            }


            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            String baseCode = jsonObject.get("base_code").getAsString();
            String targetCode = jsonObject.get("target_code").getAsString();
            double conversionResult = jsonObject.get("conversion_result").getAsDouble();




            return new MoedaDTO(baseCode,targetCode,conversionResult);



        } catch (IOException | InterruptedException e) {
           throw new RuntimeException("Erro Inesperado: " + e.getMessage());
        }

    }
}