package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.ConverteDados;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;

import java.util.Scanner;

public class Principal {

    private static final String ENDERECO_URL = "https://www.omdbapi.com/?t=gilmore+girls";
    private static final String CHAVE_API = "&apikey=bb1eaabe";
    private Scanner leitor = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();


    public void exibeMenu() {
        System.out.println("Digite o nome da serie para buscar: ");
        String nomeSerie = leitor.nextLine().replace(" ", "+");
        String json =  consumoApi.obterDados(ENDERECO_URL + nomeSerie + CHAVE_API);

        DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);
    }
}
