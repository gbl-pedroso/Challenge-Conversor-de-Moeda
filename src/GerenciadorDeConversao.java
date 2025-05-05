import Exceptions.ConnectionApiFailException;
import com.google.gson.Gson;
import dto.Moeda;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GerenciadorDeConversao {


    public Moeda buscaConversao(String endereco) {

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder(URI.create(endereco)).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {

                throw new ConnectionApiFailException("Erro ao se conectar com a API do ExchangeRate");
            }
            Gson gson = new Gson();
            return gson.fromJson(response.body(), Moeda.class);

        } catch (ConnectionApiFailException | IOException | InterruptedException e) {
            e.getMessage();
        }
        return new Moeda(null,null,null);
    }
}