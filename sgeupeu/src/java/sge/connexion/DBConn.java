
package sge.connexion;

import com.mysql.jdbc.Driver;
import java.sql.*;

public class DBConn {
public Connection con=null; 
public PreparedStatement ps=null;
public ResultSet rs=null;
public CallableStatement cs=null;
public int i=0;


public void getConexionDb(){
    try {
        DriverManager.registerDriver(new Driver());
        if(con==null){
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_sge_v10", "sge.upeu", "s@g@e@$upeu");
        //con=DriverManager.getConnection("jdbc:mysql://ec2-176-34-253-124.eu-west-1.compute.amazonaws.com:3306/bd_sge_v10", "root","");
        System.out.println("conexion off -->"+con);
        }
    } catch (Exception e) {
        System.out.println("Error de Conexion "+e.getMessage());
    }

}

public void getCerrarConexion(){
    try {
        if(rs!=null){rs.close();}
        if(ps!=null){ps.close();}
        if(cs!=null){cs.close();}
        if (con!=null) {con.close();}
    } catch (Exception e) {
        System.out.print(e.getMessage());
    }
}


public Connection con1=null; 
public PreparedStatement ps1=null;
public ResultSet rs1=null;
public CallableStatement cs1=null;


public void getConexionDb1(){
    try {
        DriverManager.registerDriver(new Driver());
        if(con1==null){
       con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_sge_v10", "sge.upeu", "s@g@e@$upeu");
       // con1=DriverManager.getConnection("jdbc:mysql://ec2-176-34-253-124.eu-west-1.compute.amazonaws.com:3306/bd_sge_v10", "root","");
        
        }
    } catch (Exception e) {
        System.out.println("Error de Conexion "+e.getMessage());
    }

}

public void getCerrarConexion1(){
    try {
        if(rs1!=null){rs1.close();}
        if(ps1!=null){ps1.close();}
        if(cs1!=null){cs1.close();}
        if (con1!=null) {con1.close();}
    } catch (Exception e) {
    }
}



public Connection con2=null; 
public PreparedStatement ps2=null;
public ResultSet rs2=null;
public CallableStatement cs2=null;


public void getConexionDb2(){
    try {
        DriverManager.registerDriver(new Driver());
        if(con2==null){
        con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_sge_v10", "sge.upeu", "s@g@e@$upeu");
       // con2=DriverManager.getConnection("jdbc:mysql://ec2-176-34-253-124.eu-west-1.compute.amazonaws.com:3306/bd_sge_v10", "root","");
        }
    } 
    
    catch (Exception e) {
        System.out.println("Error de Conexion "+e.getMessage());
    }

}

public void getCerrarConexion2(){
    try {
        if(rs2!=null){rs2.close();}
        if(ps2!=null){ps2.close();}
        if(cs2!=null){cs2.close();}
        if (con2!=null) {con2.close();}
    } catch (Exception e) {
    }
}






public Connection con3=null; 
public PreparedStatement ps3=null;
public ResultSet rs3=null;
public CallableStatement cs3=null;


public void getConexionDb3(){
    try {
        DriverManager.registerDriver(new Driver());
        if(con3==null){
        con3=DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_sge_v10", "sge.upeu", "s@g@e@$upeu");
       // con3=DriverManager.getConnection("jdbc:mysql://ec2-176-34-253-124.eu-west-1.compute.amazonaws.com:3306/bd_sge_v10", "root","");
        }
    } 
    
    catch (Exception e) {
        System.out.println("Error de Conexion "+e.getMessage());
    }

}

public void getCerrarConexion3(){
    try {
        if(rs3!=null){rs3.close();}
        if(ps3!=null){ps3.close();}
        if(cs3!=null){cs3.close();}
        if (con3!=null) {con3.close();}
    } catch (Exception e) {
    }
}



public Connection con4=null; 
public PreparedStatement ps4=null;
public ResultSet rs4=null;
public CallableStatement cs4=null;


public void getConexionDb4(){
    try {
        DriverManager.registerDriver(new Driver());
        if(con4==null){
        con4=DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_sge_v10", "sge.upeu", "s@g@e@$upeu");
       // con4=DriverManager.getConnection("jdbc:mysql://ec2-176-34-253-124.eu-west-1.compute.amazonaws.com:3306/bd_sge_v10", "root","");
        }
    } 
    
    catch (Exception e) {
        System.out.println("Error de Conexion "+e.getMessage());
    }

}

public void getCerrarConexion4(){
    try {
        if(rs4!=null){rs4.close();}
        if(ps4!=null){ps4.close();}
        if(cs4!=null){cs4.close();}
        if (con4!=null) {con4.close();}
    } catch (Exception e) {
    }
}



public Connection con5=null; 
public PreparedStatement ps5=null;
public ResultSet rs5=null;
public CallableStatement cs5=null;


public void getConexionDb5(){
    try {
        DriverManager.registerDriver(new Driver());
        if(con5==null){
        con5=DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_sge_v10", "sge.upeu", "s@g@e@$upeu");
       // con5=DriverManager.getConnection("jdbc:mysql://ec2-176-34-253-124.eu-west-1.compute.amazonaws.com:3306/bd_sge_v10", "root","");
        }
    } 
    
    catch (Exception e) {
        System.out.println("Error de Conexion "+e.getMessage());
    }

}

public void getCerrarConexion5(){
    try {
        if(rs5!=null){rs5.close();}
        if(ps5!=null){ps5.close();}
        if(cs5!=null){cs5.close();}
        if (con5!=null) {con5.close();}
    } catch (Exception e) {
    }
}


}
