package config;

public class ExchangeRateConfig {

    private static final String API_KEY = System.getenv("EXCHANGERATE_API_KEY");


    public static String getApiKey() {
        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new IllegalStateException("Chave da API do ExchangeRate não configurada. Configure a variável de ambiente EXCHANGERATE_API_KEY.");
        }
        return API_KEY;
    }
}
