package tacos.entities;

import org.springframework.data.domain.Persistable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force = true)
@Entity
public class Ingredient {
	
	@Id
	private String id;
	
	private final  String name;
	private final Type type;
	
	public enum Type{
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}

}
