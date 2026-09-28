package petstore.store.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Store {
	private long id;
	private long petId;
	private int quantity;
	private Date shipDate;
	private String status;
	private boolean complete;
}
