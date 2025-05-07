import config.ExchangeRateConfig;
import dto.MoedaDTO;
import enums.CodeMoedas;
import exceptions.ConnectionApiFailException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String apikey = ExchangeRateConfig.getApiKey();
        GerenciadorDeConversao gerencia = new GerenciadorDeConversao();

        System.out.println(MENU);
        int opcaoEscolhida = sc.nextInt();

        while (opcaoEscolhida < 1 || opcaoEscolhida > 7) {
            System.out.println("Opção invalida, por favor escolha novamente a opção");
            opcaoEscolhida = sc.nextInt();
        }

        try {
            while (opcaoEscolhida != 7) {

                switch (opcaoEscolhida) {
                    case 1: geraSaida(sc, apikey, CodeMoedas.USD.toString(), CodeMoedas.ARS.toString(), gerencia);
                    break;

                    case 2: geraSaida(sc, apikey, CodeMoedas.ARS.toString(), CodeMoedas.USD.toString(), gerencia);
                    break;

                    case 3: geraSaida(sc, apikey, CodeMoedas.USD.toString(), CodeMoedas.BRL.toString(), gerencia);
                    break;

                    case 4: geraSaida(sc, apikey, CodeMoedas.BRL.toString(), CodeMoedas.USD.toString(), gerencia);
                    break;

                    case 5: geraSaida(sc, apikey, CodeMoedas.COP.toString(), CodeMoedas.USD.toString(), gerencia);
                    break;

                    case 6: geraSaida(sc, apikey, CodeMoedas.USD.toString(), CodeMoedas.COP.toString(), gerencia);
                    break;
                }

                System.out.println(MENU);
                opcaoEscolhida = sc.nextInt();

            }
        } catch (ConnectionApiFailException e) {
            System.out.println("Erro ao se conectar com a API");
        }

        System.out.println("Obrigado por usar o conversor de Moedas! Volte sempre!");
    }


    public static void geraSaida(Scanner sc, String apikey, String base, String alvo, GerenciadorDeConversao gerencia) {
        System.out.println("Digite o valor que deseja converter: ");
        double valorParaConversao = sc.nextDouble();
        String endPoint = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/?amount=%s", apikey, base, alvo, valorParaConversao);
        MoedaDTO dto = gerencia.buscaConversao(endPoint);

        System.out.printf("Valor %s [%s] corresponde ao valor final de =>>> %f [%s]\n\n", valorParaConversao, dto.base_code(), dto.conversion_result(), dto.target_code());


    }

    private static final String MENU = """
            ********************************************
            Seja bem vindo ao conversor de moedas
            
            1) Dolar => Peso Argentino
            2) Peso Argentino => Dolar
            3) Dolar => Real Brasileiro
            4) Real Brasileiro => Dolar
            5) Dolar => Peso Colombiano
            6) Peso Colombiano => Dolar
            7) Sair
            Escolha uma opção válida:
            ********************************************
            """;


}

