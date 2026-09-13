package petstore.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import petstore.models.User;

public class UserDataFactory {

	public static User criaUser(){
		long id = geraIdAleatorio();
		Random random = new Random();
		int userStatus = random.nextInt(3);

		return new User(
				id,
				"userTestename",
				"userFirst",
				"userLast",
				"teste@teste.com",
				"teste123",
				"99999999999",
				userStatus);
	}

	public static long geraIdAleatorio() {
		// IDs entre 100000 e 999999 para reduzir chance de colisão com outros usuários da API pública
		return ThreadLocalRandom.current().nextLong(100000, 999999);
	}
}
