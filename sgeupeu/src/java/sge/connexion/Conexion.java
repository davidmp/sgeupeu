/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.connexion; 
/**
 *
 * @author HP
 */
import java.sql.*;


public class Conexion {

	private Connection con = null;

	public Conexion() {
            
            try {
                	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_sge_v10","sge.upeu","s@g@e@$upeu");
            } catch (Exception e) {
            }
       }
	
	public Connection getConexion(){
		return con;
	}
	
	public void cerrarConexion(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
