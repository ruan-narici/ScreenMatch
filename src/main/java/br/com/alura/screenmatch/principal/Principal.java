package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.service.ConsumoApi;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static final String ENDERECO_URL = "https://www.omdbapi.com/?t=";
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

        List<DadosTemporada> listaTemporadas = new ArrayList<>();
        for (int i = 1; i <= dadosSerie.temporadas(); i++) {
            json = consumoApi.obterDados(ENDERECO_URL + nomeSerie + "&season=" + i + CHAVE_API);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            listaTemporadas.add(dadosTemporada);
        }

//        listaTemporadas.forEach(System.out::println);

        // Imprimindo o titulo dos episodios utilizando lambda
        listaTemporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        // Criando uma unica lista contendo todos os episodios
        List<DadosEpisodio> listaEpisodios = listaTemporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        // Top 5 Episodios:
        System.out.println("\n TOP 5 Episodios");
        listaEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        // Criando novos objetos do tipo Episodio apos iterar a lista de temporadas
        List<Episodio> listaObjetosEpisodios = listaTemporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(e -> new Episodio(t.numero(), e)))
                .collect(Collectors.toList());
        System.out.println(listaObjetosEpisodios);
    }
}
