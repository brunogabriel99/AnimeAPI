package academy.devdojo.util;

import academy.devdojo.requests.AnimePutRequestBody;

public class AnimePutRequestBodyCreator {

	public static AnimePutRequestBody createAnimePutRequestBody() {
		return AnimePutRequestBody.builder()
				.id(AnimeCreator.createValidUpdatedAnime().getId())
				.name(AnimeCreator.createValidUpdatedAnime().getName()).build();
	}
	

}
