package petstore.services;

import petstore.models.Store;
import petstore.utils.RequestSpecFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StoreService {

	public Response criaOrderValido(Store store){
		return given()
				.spec(RequestSpecFactory.build())
				.body(store)
				.when()
				.post("/store/order");
	}

	public Response buscaInventory(){
		return given()
				.spec(RequestSpecFactory.build())
				.when()
				.get("/store/inventory");
	}

	public Response buscaOrderId(long id){
		return given()
				.spec(RequestSpecFactory.build())
				.body(id)
				.when()
				.get("/store/order/{id}", id);
	}

	public Response deleteOrder(long id){
		return given()
				.spec(RequestSpecFactory.build())
				.body(id)
				.when()
				.delete("/store/order/{id}", id);
	}
}
