package petstore.utils;

import petstore.models.Category;
import petstore.models.Pet;
import petstore.models.Tag;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PetDataFactory {

	public static Pet criaPetValido() {
		long id = geraIdAleatorio();
		Category categoria = new Category(1, "dogs");
		Tag tag = new Tag(1, "vacinado");

		return new Pet(
				id,
				categoria,
				"Rex-" + id,
				List.of("https://example.com/foto.jpg"),
				List.of(tag),
				"available"
		);
	}

	public static long geraIdAleatorio() {
		// IDs entre 100000 e 999999 para reduzir chance de colisão com outros usuários da API pública
		return ThreadLocalRandom.current().nextLong(100000, 999999);
	}
}
