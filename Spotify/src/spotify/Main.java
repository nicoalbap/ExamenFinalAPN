package spotify;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		System.out.println("Probando");
		
		Conector instancia = Conector.getInstancia();
		
		try {
			ArrayList<String> listUsers = instancia.getUsers();
			for (String user:listUsers) {
				System.out.println(user);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
		try {
			ArrayList<String> listSongs = instancia.getSongs();
			for (String song:listSongs) {
				System.out.println(song);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ArrayList<String> listPopSongs = instancia.getPopSongs();
			for (String song:listPopSongs) {
				System.out.println(song);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
