package petstore.utils;


import petstore.models.Store;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class StoreDataFactory {

	public static Store criaOrder(){
		long id = geraIdAleatorio();
		long petId = geraIdAleatorio();
		Date dataAtual = new Date();
		Random random = new Random();
		int quantity = random.nextInt(100);
		return new Store(
				id,
				petId,
				quantity,
				dataAtual,
				"Em Processo",
				true
		);

	}

	public static long geraIdAleatorio() {
		// IDs entre 100000 e 999999 para reduzir chance de colisão com outros usuários da API pública
		return ThreadLocalRandom.current().nextLong(100000, 999999);
	}

}
