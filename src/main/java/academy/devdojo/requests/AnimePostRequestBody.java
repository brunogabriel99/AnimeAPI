package academy.devdojo.requests;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimePostRequestBody {
	@NotEmpty(message = "The anime name cannot be null or empty")
	private String name;
}
