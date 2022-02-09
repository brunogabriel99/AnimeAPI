package academy.devdojo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import academy.devdojo.domain.Anime;
import academy.devdojo.requests.AnimePostRequestBody;
import academy.devdojo.requests.AnimePutRequestBody;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
	public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
	public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);
	
	public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);
	
}
