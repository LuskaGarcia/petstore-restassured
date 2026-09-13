package petstore.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private static final Properties properties = new Properties();

	static {
		String env = System.getProperty("env", "config"); // default = config.properties
		String fileName = env.equals("config") ? "config.properties" : "config-" + env + ".properties";

		try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
			if (input == null) {
				throw new RuntimeException("Arquivo de configuração não encontrado: " + fileName);
			}
			properties.load(input);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao carregar configurações", e);
		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static int getInt(String key) {
		return Integer.parseInt(properties.getProperty(key));
	}
}
