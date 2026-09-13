package petstore.base;

import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

	@BeforeAll
	public static void setupGlobal() {

		System.out.println("Iniciando suíte de testes da Petstore API");
	}
}
