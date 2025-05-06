
import config.ExchangeRateConfig;
import enums.CodeMoedas;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String apikey = ExchangeRateConfig.getApiKey();



        /*System.out.println("********************************************\n" +
                "Seja bem vindo ao conversor de moedas\n\n" +
                "1) Dolar =>> Peso Argentino\n" +
                "2) Peso Argentino =>> Dolar\n" +
                "3) Dolar =>> Real Brasileiro\n" +
                "4) Real Brasileiro =>> Dolar\n" +
                "5) Dolar =>> Peso Colombiano\n" +
                "6) Peso Colombiano =>> Dolar\n" +
                "7) Sair\n" +
                "Escolha uma opção valida:\n" +
                "********************************************\n");*/

        int opcaoEscolhida = sc.nextInt();

        System.out.println("Digite o valor que deseja converter: ");
        float valorParaConverter = sc.nextFloat();


        String endereco = String.format("https://v6.exchangerate-api.com/v6/%s/pair/BRL/USD/?amount=%s", apikey,valorParaConverter);

        GerenciadorDeConversao gerencia = new GerenciadorDeConversao();


        System.out.println(gerencia.buscaConversao(endereco));


    }


}

