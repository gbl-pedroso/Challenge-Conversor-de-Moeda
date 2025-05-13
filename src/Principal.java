import config.ExchangeRateConfig;
import dto.MoedaDTO;
import enums.CodeMoedas;
import exceptions.ConnectionApiFailException;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String apikey = ExchangeRateConfig.getApiKey();
        GerenciadorDeConversao gerencia = new GerenciadorDeConversao();
        List<MoedaDTO> historico = new ArrayList<>();


        try {
            System.out.println(MENU);
            int opcaoEscolhida = sc.nextInt();


        while (opcaoEscolhida < 1 || opcaoEscolhida > 9) {
            System.out.println("Opção invalida, por favor escolha novamente a opção");
            opcaoEscolhida = sc.nextInt();
        }


            while (opcaoEscolhida != 9) {

                switch (opcaoEscolhida) {
                    case 1:
                        geraSaida(sc, apikey, CodeMoedas.USD.toString(), CodeMoedas.ARS.toString(), gerencia, historico);
                        break;

                    case 2:
                        geraSaida(sc, apikey, CodeMoedas.ARS.toString(), CodeMoedas.USD.toString(), gerencia, historico);
                        break;

                    case 3:
                        geraSaida(sc, apikey, CodeMoedas.USD.toString(), CodeMoedas.BRL.toString(), gerencia, historico);
                        break;

                    case 4:
                        geraSaida(sc, apikey, CodeMoedas.BRL.toString(), CodeMoedas.USD.toString(), gerencia, historico);
                        break;

                    case 5:
                        geraSaida(sc, apikey, CodeMoedas.COP.toString(), CodeMoedas.USD.toString(), gerencia, historico);
                        break;

                    case 6:
                        geraSaida(sc, apikey, CodeMoedas.USD.toString(), CodeMoedas.COP.toString(), gerencia, historico);
                        break;

                    case 7:
                        System.out.println("Digite a sigla da Moeda de origem: ");
                        String siglaMoedaOrigem = sc.next();
                        while (moedaValida(siglaMoedaOrigem)) {
                            System.out.println("Sigla invalida, por favor digite uma sigla valida");
                            siglaMoedaOrigem = sc.next();
                        }

                        System.out.println("Digite a sigla da Moeda para conversão: ");
                        String siglaMoedaConversao = sc.next();

                        while (moedaValida(siglaMoedaConversao)) {
                            System.out.println("Sigla invalida, por favor digite uma sigla valida");
                            siglaMoedaConversao = sc.next();
                        }
                        geraSaida(sc, apikey, siglaMoedaOrigem, siglaMoedaConversao, gerencia, historico);
                        break;

                    case 8:
                        System.out.println("Historico de conversões: ");
                        if (historico.isEmpty()) {
                            System.out.println("Este historico está vazio");
                        } else {
                            for (MoedaDTO moeda : historico) {
                                System.out.printf(
                                        "Moeda %s convertida para %s | Valor: %.2f [%s]%n",
                                        moeda.baseCode(),
                                        moeda.targetCode(),
                                        moeda.conversionResult(),
                                        moeda.dataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                                );
                            }
                        }
                }

                System.out.println(MENU);
                opcaoEscolhida = sc.nextInt();

            }
        } catch (ConnectionApiFailException e) {
            System.out.println("Erro ao se conectar com a API");
        }catch (InputMismatchException e){
            System.out.println("Erro: o valor digitado é invalido!! programa será encerrado.");

        }

        System.out.println("Obrigado por usar o conversor de Moedas! Volte sempre!");
    }


    public static void geraSaida(Scanner sc, String apikey, String base, String alvo, GerenciadorDeConversao gerencia, List<MoedaDTO> list) {
        System.out.println("Digite o valor que deseja converter: ");
        double valorParaConversao = sc.nextDouble();
        String endPoint = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/?amount=%s", apikey, base, alvo, valorParaConversao);


        MoedaDTO dto = gerencia.buscaConversao(endPoint);
        list.add(dto);

        System.out.printf("Valor %s [%s] corresponde ao valor final de =>>> %f [%s]\n\n", valorParaConversao, dto.baseCode(), dto.conversionResult(), dto.targetCode());


    }

    public static boolean moedaValida(String codigo) {

       return Arrays.stream(CodeMoedas.values()).noneMatch(moeda -> moeda.name().equalsIgnoreCase(codigo));

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
            
            7) Escolher Moedas para Conversão
            8) Exibir Registro de Conversões
            9) Sair
            Escolha uma opção válida:
            ********************************************
            """;


}

