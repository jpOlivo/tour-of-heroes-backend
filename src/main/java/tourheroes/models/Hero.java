package tourheroes.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Document(collection = "heroes")
@ApiModel(description = "Representation of a Hero.")
public class Hero {
	@Id
	@ApiModelProperty(position = 1, value = "The id of Hero.")
	private String id;

	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	@ApiModelProperty(position = 2, value = "The name of Hero.")
	private String name;

	public Hero() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
