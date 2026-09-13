package petstore.tests;

import petstore.base.BaseTest;
import petstore.models.Store;
import petstore.services.StoreService;
import petstore.utils.StoreDataFactory;
import io.restassured.response.Response;

import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StoreApiTest extends BaseTest {

	private static final StoreService storeService = new StoreService();
	private static Store orderCriado;

	@Test
	@Order(1)
	@DisplayName("Cria um Order")
	public void criaOrder(){
		orderCriado = StoreDataFactory.criaOrder();

		Response response = storeService.criaOrderValido(orderCriado);

		response.then()
				.statusCode(200)
				.body("id", equalTo((int)orderCriado.getId()))
				.body("petId", equalTo((int) orderCriado.getPetId()))
				.body("quantity", equalTo(orderCriado.getQuantity()));
	}

	@Test
	@Order(2)
	@DisplayName("Busca o inventario de pets por status")
	public void buscarInventario(){
		Response response = storeService.buscaInventory();

		response.then()
				.statusCode(200)
				.body(containsString("sold"));
	}

	@Test
	@Order(3)
	@DisplayName("Busca a Order por Id")
	public void buscaOrderId(){
		Response response = storeService.buscaOrderId(orderCriado.getId());

		response.then()
				.statusCode(200)
				.body("id", equalTo((int)orderCriado.getId()))
				.body("petId", equalTo((int) orderCriado.getPetId()))
				.body("quantity", equalTo(orderCriado.getQuantity()));
	}

	@Test
	@Order(4)
	@DisplayName("Deleta uma order por item")
	public void deletaOrderId(){
		Response response = storeService.deleteOrder(orderCriado.getId());

		response.then()
				.statusCode(200);
	}

	@Test
	@Order(5)
	@DisplayName("Busca a Order que não existe mais")
	public void buscarOrderInexistente(){
		Response response = storeService.buscaOrderId(orderCriado.getId());

		response.then()
				.statusCode(404);
	}
}
