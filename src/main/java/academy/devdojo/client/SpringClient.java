package academy.devdojo.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import academy.devdojo.domain.Anime;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SpringClient {
	public static void main(String[] args) {
		ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/1", Anime.class);
		log.info(entity);
		
		Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 1);
		
		log.info(object);
		
		Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
		
		log.info(Arrays.toString(animes));
		
		ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
		
		log.info(exchange.getBody());
		
		Anime kingdom = Anime.builder().name("kingdom").build();
		Anime kingdomSaved = new RestTemplate().postForObject("http://localhost:8080/animes/", kingdom, Anime.class);
		log.info("saved anime {}", kingdomSaved);
		
		Anime samuraiChamploo = Anime.builder().name("Samurai Champloo").build();
		ResponseEntity<Anime> samuraiChamplooSaved = new RestTemplate().exchange("http://localhost:8080/animes/", HttpMethod.POST, new HttpEntity<>(samuraiChamploo, createJsonHeader()), Anime.class);
		log.info("saved anime {}", samuraiChamplooSaved);
		
		Anime animeToBeUpdated = samuraiChamplooSaved.getBody();
		animeToBeUpdated.setName("Samurai Champloo 2");
		ResponseEntity<Void> samuraiChamplooUpdated = new RestTemplate().exchange("http://localhost:8080/animes/", HttpMethod.PUT, new HttpEntity<>(animeToBeUpdated, createJsonHeader()), Void.class);
		log.info("Updated anime {}", samuraiChamplooUpdated);
		
		ResponseEntity<Void> samuraiChamplooDelete = new RestTemplate().exchange("http://localhost:8080/animes/{id}", HttpMethod.DELETE, null, Void.class, animeToBeUpdated.getId());
		log.info("Updated anime {}", samuraiChamplooDelete);
		
		
		
	}

	private static HttpHeaders createJsonHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}
}
