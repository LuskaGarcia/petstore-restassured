package petstore.tests;

import petstore.base.BaseTest;
import petstore.models.Pet;
import petstore.services.PetService;
import petstore.utils.PetDataFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.*;

import java.io.File;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetApiTest extends BaseTest {

	private static final PetService petService = new PetService();
	private static Pet petCriado;

	@Test
	@Order(1)
	@DisplayName("Deve criar um pet com sucesso")
	public void criacaoPetValido() {
		petCriado = PetDataFactory.criaPetValido();

		Response resposta = petService.criaPet(petCriado);

		resposta.then()
				.statusCode(200)
				.body("name", equalTo(petCriado.getName()))
				.body("status", equalTo("available"))
				.body("id", equalTo((int) petCriado.getId()));
	}

	@Test
	@Order(2)
	@DisplayName("Deve buscar o pet criado por ID")
	public void buscaPetIdValido() {
		Response resposta = petService.buscaPetId(petCriado.getId());

		resposta.then()
				.statusCode(200)
				.body("id", equalTo((int) petCriado.getId()))
				.body("name", equalTo(petCriado.getName()));
	}

	@Test
	@Order(3)
	@DisplayName("Deve atualizar o status do pet para 'sold'")
	public void atualizaStatusPetValido() {
		petCriado.setStatus("sold");

		Response resposta = petService.atualizaPet(petCriado);

		resposta.then()
				.statusCode(200)
				.body("status", equalTo("sold"));
	}

	@Test
	@Order(4)
	@DisplayName("Deve atualizar a imagem do pet")
	public void atualizaImagePetValido(){
		File imagem = new File("src/test/resources/images/0.png");

		Response resposta = petService.atualizaImagePet(
				petCriado.getId(),
				"Foto de perfil do pet",
				imagem
		);

		resposta.then()
				.statusCode(200)
				.body("code", equalTo(200))
				.body("type", equalTo("unknown"))
				.body("message", containsString("File uploaded to"))
				.body("message", containsString(imagem.getName()));
	}


	@Test
	@Order(5)
	@DisplayName("Deve retornar 404 ao buscar pet inexistente")
	public void buscaPetInvalido() {
		long idInexistente = 999999999L;

		petService.buscaPetId(idInexistente)
				.then()
				.statusCode(404);
	}

	@Test
	@Order(6)
	@DisplayName("Deve atualizar um pet via Form Data")
	public void atualizaPetIdForm(){
		petCriado.setName("Cleiton");
		petCriado.setStatus("pending");
		Response resposta = petService.atualizaPetIdPost(
				petCriado.getId(),
				petCriado.getName(),
				petCriado.getStatus());

		resposta.then()
				.statusCode(200);
	}

	@Test
	@Order(7)
	@DisplayName("Deve buscar o pet com os dados atualizados pelo teste 6")
	public void buscaPetAlterado() {
		Response resposta = petService.buscaPetId(petCriado.getId());

		resposta.then()
				.statusCode(200)
				.body("id", equalTo((int) petCriado.getId()))
				.body("name", equalTo(petCriado.getName()))
				.body("status", equalTo(petCriado.getStatus()));
	}


	@Test
	@Order(8)
	@DisplayName("Deve deletar o pet criado")
	public void deletaPetValido() {
		Response resposta = petService.deletaPet(petCriado.getId());

		resposta.then()
				.statusCode(200);
	}


	//a saída aqui é muito grande
	@Test
	@Order(9)
	@DisplayName("Deve retornar os pets no estado 'available'")
	public void buscaPetStatus(){
		Response resposta = petService.buscaStatus("available");

		resposta.then()
				.statusCode(200)
				.body("status", everyItem(equalTo("available")));

	}

	@Test
	@Order(10)
	@DisplayName("Deve retornar erro 404 ao tentar atualizar um pet não existente")
	public void atualizaPetInexistente(){
		Response resposta = petService.atualizaPet(petCriado);

		resposta.then()
				.statusCode(404);

	}

	@Test
	@Order(11)
	@DisplayName("Deve retornar 404 ao tentar atualizar um pet com id inexistente")
	public void atualizaPetInexistentePost(){
		Response resposta = petService.atualizaPetIdPost(
				petCriado.getId(),
				petCriado.getName(),
				petCriado.getStatus());

		resposta.then()
				.statusCode(404);
	}

	@Test
	@Order(12)
	@DisplayName("Deve retornar 404 ao tentar deleter um pet inexistente")
	public void deletaPetInexistente(){
		Response resposta = petService.deletaPet(petCriado.getId());

		resposta.then()
				.statusCode(404);
	}
	@AfterAll
	public static void finalizarSuite() {
		System.out.println("Suíte de testes finalizada.");
	}
}
