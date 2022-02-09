package academy.devdojo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import academy.devdojo.domain.Anime;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long>{
	
	public List<Anime> findByName(String name);
}
