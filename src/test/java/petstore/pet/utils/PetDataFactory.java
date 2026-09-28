package petstore.pet.utils;

import petstore.pet.models.Category;
import petstore.pet.models.Pet;
import petstore.pet.models.Tag;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PetDataFactory {

	public static Pet criaPetValido() {
		long id = gerarIdAleatorio();
		return Pet.builder()
				.id(id)
				.name("Rex-" + id)
				.category(new Category(1, "dogs"))
				.tags(List.of(new Tag(1, "vacinado")))
				.photoUrls(List.of("https://example.com/foto.jpg"))
				.status("available")
				.build();
	}

	public static Pet criarPetSemNome() {
		return Pet.builder()
				.id(gerarIdAleatorio())
				.status("available")
				.build();
	}

	public static long gerarIdAleatorio() {
		// IDs entre 100000 e 999999 para reduzir chance de colisão com outros usuários da API pública
		return ThreadLocalRandom.current().nextLong(100000, 999999);
	}
}
