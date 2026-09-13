package petstore.services;

import petstore.models.Pet;
import petstore.utils.RequestSpecFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.File;

public class PetService {

	public Response criaPet(Pet pet) {
		return given()
				.spec(RequestSpecFactory.build())
				.body(pet)
				.when()
				.post("/pet");
	}

	public Response buscaPetId(long id) {
		return given()
				.spec(RequestSpecFactory.build())
				.when()
				.get("/pet/{id}", id);
	}

	public Response atualizaPetIdPost(long id, String nome, String status){
		return given()
				.spec(RequestSpecFactory.build())
				.contentType("application/x-www-form-urlencoded")
				.formParam("name", nome)
				.formParam("status", status)
				.when()
				.post("/pet/{id}", id);

	}


	public Response atualizaPet(Pet pet) {
		return given()
				.spec(RequestSpecFactory.build())
				.body(pet)
				.when()
				.put("/pet");
	}

	public Response atualizaImagePet(long petId, String additionalMetadata, File arquivo) {
		return given()
				.spec(RequestSpecFactory.build())
				.contentType("multipart/form-data")
				.multiPart("additionalMetadata", additionalMetadata)
				.multiPart("file", arquivo)
				.when()
				.post("/pet/{id}/uploadImage", petId);
	}

	public Response deletaPet(long id) {
		return given()
				.spec(RequestSpecFactory.build())
				.header("api_key", "special-key")
				.when()
				.delete("/pet/{id}", id);
	}

	public Response buscaStatus(String status) {
		return given()
				.spec(RequestSpecFactory.build())
				.queryParam("status", status)
				.when()
				.get("/pet/findByStatus");
	}
}
