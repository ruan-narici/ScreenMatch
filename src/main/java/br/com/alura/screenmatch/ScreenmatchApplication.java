package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.ConverteDados;
import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.principal.Principal;
import br.com.alura.screenmatch.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();

//		String apiKey = "bb1eaabe";
//		ConsumoApi consumoApi = new ConsumoApi();
//		String json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=" + apiKey);
//
//		ConverteDados converteDados = new ConverteDados();
//		DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
//		System.out.println(dadosSerie);
//
//		json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=" + apiKey);
//		DadosEpisodio dadosEpisodio = converteDados.obterDados(json, DadosEpisodio.class);
//		System.out.println(dadosEpisodio);
//
//		List<DadosTemporada> listaDadosTemporada = new ArrayList<>();
//		for (int i = 1; i <= dadosSerie.temporadas(); i++) {
//			json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=" + apiKey);
//			DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
//			listaDadosTemporada.add(dadosTemporada);
//		}
//
//		listaDadosTemporada.forEach(System.out::println);
//
	}
}
