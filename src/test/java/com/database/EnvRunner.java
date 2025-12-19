package com.database;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvRunner {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		String data = dotenv.get("DB_URL", "ABC");
		System.out.println(data);
	}

}
