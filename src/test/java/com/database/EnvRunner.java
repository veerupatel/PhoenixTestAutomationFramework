package com.database;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvRunner {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		String dburl = dotenv.get("DB_URL", "ABC");
		String dbusername=dotenv.get("DB_USER_NAME");
		String dbpassword=dotenv.get("DB_PASSWORD");
		System.out.println( "DB_URL" + "   =    " + dburl);
		System.out.println( "DB_USER_NAME" + "   =   " + dbusername);
		System.out.println( "DB_PASSWORD" + "   =   " + dbpassword);
	}

}
