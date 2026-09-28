package petstore.user.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import petstore.user.models.User;

public class UserDataFactory {

	public static User criaUser() {
		long id = geraIdAleatorio();
		Random random = new Random();
		int userStatus = random.nextInt(3);

		return User.builder()
				.id(id)
				.username("userTestename")
				.firstName("userFirst")
				.lastName("userLast")
				.email("teste@teste.com")
				.password("teste123")
				.phone("99999999999")
				.userStatus(userStatus)
				.build();
	}

	public static long geraIdAleatorio() {
		// IDs entre 100000 e 999999 para reduzir chance de colisão com outros usuários da API pública
		return ThreadLocalRandom.current().nextLong(100000, 999999);
	}
}
