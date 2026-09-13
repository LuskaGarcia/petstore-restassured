package petstore.services;

import petstore.models.User;
import petstore.utils.RequestSpecFactory;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.List;

public class UserService {

	public Response criaUserValido(User user){
		return given()
				.spec(RequestSpecFactory.build())
				.body(user)
				.when()
				.post("/user");
	}

	public Response logout(){
		return given()
				.spec(RequestSpecFactory.build())
				.when()
				.get("/user/logout");
	}
	public Response login(String username, String password){
		return given()
				.spec(RequestSpecFactory.build())
				.queryParam("username", username)
				.queryParam("password", password)
				.when()
				.get("/user/login");
	}

	public Response deleteUser(String username){
		return given()
				.spec(RequestSpecFactory.build())
				.when()
				.delete("/user/{username}", username);
	}

	public Response alteraUser(User user){
		return given()
				.spec(RequestSpecFactory.build())
				.body(user)
				.when()
				.put("/user/{username}", user.getUsername());
	}

	public Response buscarUser(String username){
		return given()
				.spec(RequestSpecFactory.build())
				.when()
				.get("/user/{username}", username);
	}

	public Response criaArrayUsers(List<User> users) {
		return given()
				.spec(RequestSpecFactory.build())
				.body(users)
				.when()
				.post("/user/createWithArray");
	}

	public Response criaListUsers(List<User> users) {
		return given()
				.spec(RequestSpecFactory.build())
				.body(users)
				.when()
				.post("/user/createWithList");
	}
}
