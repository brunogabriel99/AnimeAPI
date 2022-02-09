package academy.devdojo.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import academy.devdojo.domain.Anime;
import academy.devdojo.util.AnimeCreator;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
public class AnimeRepositoryTest {

	@Autowired
	private AnimeRepository animeRepository;

	@Test
	@DisplayName("Save persists anime when successful")
	public void save_PersistAnime_WhenSuccessful() {
		Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();
		Anime animeSaved = animeRepository.save(createAnimeToBeSaved);
		Assertions.assertThat(animeSaved).isNotNull();
		Assertions.assertThat(animeSaved.getId()).isNotNull();
		Assertions.assertThat(animeSaved.getName()).isEqualTo(createAnimeToBeSaved.getName());
	}

	@Test
	@DisplayName("Save updates anime when successful")
	public void save_UpdatesAnime_WhenSuccessful() {
		Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();

		Anime animeSaved = animeRepository.save(createAnimeToBeSaved);

		animeSaved.setName("Overlord");

		Anime animeUpdated = animeRepository.save(animeSaved);
		
		Assertions.assertThat(animeUpdated).isNotNull();
		Assertions.assertThat(animeUpdated.getId()).isNotNull();
		Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
	}
	
	@Test
	@DisplayName("Delete removes anime when successful")
	public void delete_RemovesAnime_WhenSuccessful() {
		Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();

		Anime animeSaved = animeRepository.save(createAnimeToBeSaved);

		animeRepository.delete(animeSaved);
		
		Optional<Anime> animeOptional = animeRepository.findById(animeSaved.getId());
		
		Assertions.assertThat(animeOptional).isEmpty();
	}
	
	@Test
	@DisplayName("Find By Name returns list of anime when successful")
	public void findByName_ReturnsListOfAnime_WhenSuccessful() {
		Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();

		Anime animeSaved = animeRepository.save(createAnimeToBeSaved);

		String name = animeSaved.getName();
		
		List<Anime> animes = animeRepository.findByName(name);
		
		Assertions.assertThat(animes).isNotEmpty()
		.contains(animeSaved);
	}
	
	@Test
	@DisplayName("Find By Name returns empty list when no anime is successful")
	public void findByName_ReturnsEmptyList_WhenAnimeIsNotFound() {
		
		List<Anime> animes = animeRepository.findByName("xaxa");
		
		Assertions.assertThat(animes).isEmpty();
	}
	
	@Test
	@DisplayName("Save throw ConstraintViolationException when name is empty")
	public void save_ConstraintViolationException_WhenNameIsEmpty() {
		Anime anime = new Anime();
		
		//Assertions.assertThatThrownBy(() -> animeRepository.save(anime))
		//.isInstanceOf(ConstraintViolationException.class);
		
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
		.isThrownBy(() -> animeRepository.save(anime))
		.withMessageContaining("The anime name cannot be null or empty");
		
	}
}
