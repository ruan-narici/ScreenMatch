package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.service.ConsumoApi;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
//        listaTemporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        // Criando uma unica lista contendo todos os episodios
        List<DadosEpisodio> listaEpisodios = listaTemporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        // Top 5 Episodios:
//        System.out.println("\n TOP 5 Episodios");
//        listaEpisodios.stream()
//                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .limit(5)
//                .forEach(System.out::println);

        // Criando novos objetos do tipo Episodio apos iterar a lista de temporadas
//        List<Episodio> listaObjetosEpisodios = listaTemporadas.stream()
//                .flatMap(t -> t.episodios().stream()
//                        .map(e -> new Episodio(t.numero(), e)))
//                .collect(Collectors.toList());
//        System.out.println(listaObjetosEpisodios);

//        System.out.println("Digite o ano buscado: ");
//        Integer anoBuscado = leitor.nextInt();
//        leitor.nextLine();
//        LocalDate dataBuscada = LocalDate.of(anoBuscado, 1, 1);
//        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        listaObjetosEpisodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBuscada))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getNumeroTemporada() +
//                                " Titulo: " + e.getTitulo() +
//                                " Data lançamento: " + e.getDataLancamento().format(formatadorData)
//                ));

        // Listando o top 10 episodios
        List<Episodio> listaTop10Episodios = listaTemporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                        .map(e -> new Episodio(t.numero(), e)))
                .sorted(Comparator.comparing(Episodio::getAvaliacao).reversed())
                .limit(10)
                .collect(Collectors.toList());

        listaTop10Episodios.stream()
                .forEach(e -> System.out.println(e.getTitulo()));

        // Buscando um episodio pelo titulo
//        System.out.println("Digite o titulo do episódio: ");
//        String episodioBuscado = leitor.nextLine();
//
//        Optional<Episodio> episodioEncontrado = listaTop10Episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(episodioBuscado.toUpperCase()))
//                .findFirst();
//
//        System.out.println(episodioEncontrado.get());


        // Realizando a media de avaliacao por temporada
        System.out.println("Média de avaliação por temporada");
        Map<Integer, Double> mediaAvaliacaoPorTemporada = listaTemporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                        .map(e -> new Episodio(t.numero(), e)))
                .collect(Collectors.groupingBy(Episodio::getNumeroTemporada, Collectors.averagingDouble(Episodio::getAvaliacao)));
        System.out.println(mediaAvaliacaoPorTemporada);



    }
}
