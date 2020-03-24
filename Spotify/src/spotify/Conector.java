package spotify;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Conector {

	
	//Atribustos de clase
	
		private static Connection con;
		private static Conector INSTANCE = null;
		
		//Constructor
		
		private Conector(){
			
		}
		
		//Crear instancia
		
		private synchronized static void crearInstancia() {
			if (INSTANCE == null)	{
				INSTANCE = new Conector();
				crearConexion();
			}
			
		}
		
		// Obtener instancia
		
		public static Conector getInstancia(){
			if (INSTANCE == null)	{
				crearInstancia();
			}
			return INSTANCE;
		}
		
		//Crear conexion
		
		private static void crearConexion() {
			String host = "127.0.0.1";
			String user = "root";
			String password = "familiadenico";
			String dataBase = "spotify";
			try {
				//Importando la libreria de conexion
				Class.forName("com.mysql.jdbc.Driver");
				String urlConexion = "jdbc:mysql://"+host+"/"+dataBase+"?user="+user+"&password="+password;
				con = DriverManager.getConnection(urlConexion);
				System.out.println("Lo lograste :)");
				
			}catch(Exception e) {
				System.out.println("Error al conectar la base de datos :(");
			}
			
		}
		
		public ArrayList<String> getUsers() throws SQLException {
			ArrayList<String> listaUsers = new ArrayList<String>(); 
			PreparedStatement ps = con.prepareStatement("Select username from users\r\n" + 
					"where username like'%k%'\r\n" + 
					"and id order by username;");
			ResultSet rs = ps.executeQuery();
			System.out.println("\nConsulta 1");
			while(rs.next()) {
				listaUsers.add(rs.getString("username"));
			}
			rs.close();
			return listaUsers;
		}
		
		public ArrayList<String> getSongs() throws SQLException {
			ArrayList<String> listaSongs = new ArrayList<String>(); 
			PreparedStatement ps = con.prepareStatement("Select title from songs\r\n" + 
					"where plays > (Select avg (plays) from songs)");
			ResultSet rs = ps.executeQuery();
			System.out.println("\nConsulta 2");
			while(rs.next()) {
				listaSongs.add(rs.getString("title"));
			}
			rs.close();
			return listaSongs;
		}
		
		public ArrayList<String> getPopSongs() throws SQLException {
			ArrayList<String> listaPopSongs = new ArrayList<String>(); 
			PreparedStatement ps = con.prepareStatement("Select songs.title from songs\r\n" + 
					"inner join albums on songs.album = albums.id\r\n" + 
					"inner join genres on albums.genre = genres.id\r\n" + 
					"where genres.name = 'Pop'");
			ResultSet rs = ps.executeQuery();
			System.out.println("\nConsulta 3");
			while(rs.next()) {
				listaPopSongs.add(rs.getString("songs.title"));
			}
			rs.close();
			return listaPopSongs;
		}
		
		
	
}
