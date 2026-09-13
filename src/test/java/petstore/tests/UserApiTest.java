package petstore.tests;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import petstore.base.BaseTest;
import petstore.models.User;
import petstore.services.UserService;
import petstore.utils.UserDataFactory;
import io.restassured.response.Response;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserApiTest extends BaseTest {
	private static final UserService userService = new UserService();
	private static User userCriado;

	@Test
	@Order(1)
	@DisplayName("Cria um User")
	public void criaUser(){
		userCriado = UserDataFactory.criaUser();

		Response response = userService.criaUserValido(userCriado);

		response.then()
				.statusCode(200)
				.body("message", equalTo(String.valueOf(userCriado.getId())));
	}

	@Test
	@Order(2)
	@DisplayName("Buscar o user")
	public void buscarUser(){
		Response response = userService.buscarUser(userCriado.getUsername());

		response.then()
				.statusCode(200)
				.body("id", equalTo((int)userCriado.getId()))
				.body("username", equalTo(userCriado.getUsername()))
				.body("firstName", equalTo(userCriado.getFirstName()));
	}

	@Test
	@Order(3)
	@DisplayName("Altera o primeiro nome do user")
	public void alteraUser() {
		userCriado.setFirstName("First Nome Alterado");
		Response response = userService.alteraUser(userCriado);

		response.then()
				.statusCode(200)
				.body("message", equalTo(String.valueOf(userCriado.getId())));
	}

	@Test
	@Order(4)
	@DisplayName("Deve fazer login com sucesso e retornar headers de sessão")
	public void loginUser(){
		Response response = userService.login(userCriado.getUsername(), userCriado.getPassword());

		response.then()
				.statusCode(200)
				.header("X-Rate-Limit", notNullValue())
				.header("X-Expires-After", notNullValue())
				.body(containsString("logged in user session"));
	}

	@Test
	@Order(5)
	@DisplayName("Retorn 400 ao tentar logar com senha inválida")
	public void loginComSenhaInvalida(){
		Response response = userService.login(userCriado.getUsername(), "senha-errada-123");

		response.then()
				.statusCode(400);
	}

	@Test
	@Order(6)
	@DisplayName("Faz o logout")
	public void logoutUser(){
		Response response = userService.logout();

		response.then()
				.statusCode(200);
	}

	@Test
	@Order(7)
	@DisplayName("Faz o login invalido")
	public void loginUserInvalido(){
		Response response = userService.login("a", "teste");

		response.then()
				.statusCode(400);
	}

	@Test
	@Order(8)
	@DisplayName("Deleta o user")
	public void deletaUser(){
		Response response = userService.deleteUser(userCriado.getUsername());

		response.then()
				.statusCode(200);
	}

	@Test
	@Order(9)
	@DisplayName("Deleta o user inexistente")
	public void deletaUserInexistente(){
		Response response = userService.deleteUser(userCriado.getUsername());

		response.then()
				.statusCode(404);
	}

	@Test
	@Order(10)
	@DisplayName("Buscar o user inexistente")
	public void buscaUserInexistente(){
		Response response = userService.buscarUser(userCriado.getUsername());

		response.then()
				.statusCode(404);
	}

	@Test
	@Order(11)
	@DisplayName("Tenta alterar o nome do user inexistente")
	public void alteraUserInexistente() {
		userCriado.setFirstName("First Nome Alterado Inexistente");
		Response response = userService.alteraUser(userCriado);

		response.then()
				.statusCode(404);
	}

}
