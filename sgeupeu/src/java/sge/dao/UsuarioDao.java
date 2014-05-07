/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.dao;

import java.util.ArrayList;
import java.util.List;
import sge.connexion.DBConn;
import sge.modelo.Acceso;
import sge.modelo.Categoriausuario;
import sge.modelo.CoordinadorEje;
import sge.modelo.CoordinadorEjeeap;
import sge.modelo.Coordinadoreap;
import sge.modelo.Coordinadorfacultad;
import sge.modelo.Eap;
import sge.modelo.Ejeestrategico;
import sge.modelo.Facultad;
import sge.modelo.Filial;
import sge.modelo.Filialfacultad;
import sge.modelo.Periodo;
import sge.modelo.Persona;
import sge.modelo.Perspectiva;
import sge.modelo.Privilegio;
import sge.modelo.Tipoarea;
import sge.modelo.Usuario;
import sge.util.STRCrypto;

/**
 *
 * @author edwin
 */
public class UsuarioDao  extends DBConn{
    
    STRCrypto cry=new STRCrypto();
    
    
    public int Id_usuario (Usuario to){
    String sql="select * from usuario where usuario=? and password=?";
    List<Usuario> Lista = new ArrayList<Usuario>(); 
    Usuario Toto = null;
    int id;
   
    int iks=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setString(++iks, to.getUsuario());
            ps.setString(++iks, cry.encrypt(to.getPassword()));
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Usuario();
           Toto.setId(rs.getInt("idusuario"));
           Lista.add(Toto);
        }

} catch (Exception e) {
        }
        finally{getCerrarConexion();}
              id=Toto.getId();
        return id;
     }
    
    public Eap eapUsuario(int to){
    String sql="SELECT e.ideap,e.nombre,e.descripcion,ef.ideapfacultad FROM persona p INNER JOIN usuario u ON " +
    " p.idpersona=u.idpersona INNER JOIN coordinadoreap ce ON  " +
    " ce.idpersona=p.idpersona INNER JOIN eapfacultad ef ON  " +
    " ef.idcoordinadoreap=ce.idcoordinadoreap INNER JOIN eap e ON e.ideap=ef.ideap AND u.idusuario=? ";
    Eap Toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, to);
            System.out.println("idUsuario--->"+to);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
         Toto  = new Eap();
           Toto.setIdeapfacultad(rs.getInt("ideapfacultad"));
           Toto.setIdeap(rs.getInt("ideap"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
        }

   } catch (Exception e) {
        }
   finally{getCerrarConexion();}
    return Toto;
    }
    
    //Inicio UPeU---->
    public List areasUnidadUpeU(){
    String sql=" SELECT * FROM tipoarea WHERE estado=1 ";
    List lista=new ArrayList();
    Tipoarea to;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);            
            rs=ps.executeQuery();
                while (rs.next()){       
                to=new Tipoarea();
                to.setDescripcion(rs.getString("descripcion"));
                to.setNombre(rs.getString("nombre"));
                to.setIdtipoarea(rs.getInt("idtipoarea"));
                to.setEstado(rs.getInt("estado"));
                to.setEtiqueta(rs.getString("etiqueta"));                
                lista.add(to);
                }
            } catch (Exception e) { e.getMessage();}
    finally{getCerrarConexion();}
    return lista;
    }
    
    public List listarCoordinadoresEjeEaps(int idFilal, int idEapFilial){
    String sql=" SELECT ce.idcoordinadoreje, p.email, p.dni, ce.idPersona, CONCAT(p.nombre,' ', p.apellipaterno, ' ', p.apellimaterno) AS nombre  FROM  coordinador_eje ce INNER JOIN persona p ON (ce.idPersona=p.idPersona) INNER JOIN coordinador_ejeeap ceap ON ceap.idcoordinadoreje=ce.idcoordinadoreje AND p.idfilial=? AND ceap.ideapfacultad=? ";
    List lista=new ArrayList();
    CoordinadorEje to;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idFilal);
            ps.setInt(2, idEapFilial);
            rs=ps.executeQuery();
                while (rs.next()){       
                to=new CoordinadorEje();
                to.setIdcoordinadoreje(rs.getInt("idcoordinadoreje"));
                to.setEmail(rs.getString("email"));
                to.setDni(rs.getString("dni"));
                to.setIdPersona(rs.getInt("idPersona"));
                to.setNombrepersona(rs.getString("nombre"));                
                lista.add(to);
                }
            } catch (Exception e) { e.getMessage();}
    finally{getCerrarConexion();}
    return lista;
    }
    
    public List perspectivasUPeU(){
    String sql=" SELECT * FROM perspectiva  ";
    List lista=new ArrayList();
    Perspectiva to;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);            
            rs=ps.executeQuery();
                while (rs.next()){      
                to=new Perspectiva();
                to.setDescripcion(rs.getString("descripcion"));
                to.setNombre(rs.getString("nombre"));           
                to.setCodigo(rs.getString("codigo"));           
                to.setIdperspectiva(rs.getInt("idperspectiva"));           
                lista.add(to);
                }
            } catch (Exception e) { e.getMessage();}
    finally{getCerrarConexion();}
    return lista;
    }
    
    public int compararClaveAnterior(int idUsuario, String clave){
    String sql=" SELECT * FROM usuario WHERE idusuario=? AND PASSWORD=? ";    
    int to=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setString(2, cry.encrypt(clave));
            rs=ps.executeQuery();
                if (rs.next()){      
                to=1;
                }
            } catch (Exception e) { e.getMessage();}
    finally{getCerrarConexion();}
    return to;
    }
    
    
    public List ejesUPeU(){
              
    String sql=" SELECT e.*, t.nombre tipoarea FROM ejeestrategico e, tipoarea t WHERE e.idtipoarea=t.idtipoarea AND e.estado=1 ORDER BY e.idtipoarea ASC, e.codigo ASC ";
    List lista=new ArrayList();
        Ejeestrategico to;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);            
            rs=ps.executeQuery();
                while (rs.next()){
                to=new Ejeestrategico();
                to.setDescripcion(rs.getString("descripcion"));
                to.setNombre(rs.getString("nombre"));
                to.setIdejeestrategico(rs.getInt("idEjeEstrategico"));
                to.setEstado(rs.getInt("estado"));
                to.setObjetivoestrategico(rs.getString("objetivoestrategico"));                
                to.setMapaestrategico(rs.getString("mapaestrategico"));                
                to.setCodigo(rs.getString("codigo"));                
                to.setIdtipoarea(rs.getInt("idtipoarea"));                
                to.setTipoarea(rs.getString("tipoarea"));                
                lista.add(to);
                }
            } catch (Exception e) { e.getMessage();}
    finally{getCerrarConexion();}
    return lista;
    } 
        // Fin UPeU---->
    
    
    public Eap eapUsuarioEje(int to, int idFilial){
    String sql=" SELECT e.ideap,e.nombre,e.descripcion,ef.ideapfacultad FROM persona p INNER JOIN usuario u ON  "
            + " p.idpersona=u.idpersona INNER JOIN coordinador_eje ce ON    ce.idpersona=p.idpersona "
            + " INNER JOIN coordinador_ejeeap ee ON  ee.idcoordinadoreje=ce.idcoordinadoreje "
            + " INNER JOIN eapfacultad ef ON  ef.ideapfacultad=ee.ideapfacultad "
            + " INNER JOIN eap e ON e.ideap=ef.ideap AND u.idusuario=? AND u.idfilial=? ";
    Eap Toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, to);
            ps.setInt(2, idFilial);
            System.out.println("idUsuario--->"+to);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
         Toto  = new Eap();
           Toto.setIdeapfacultad(rs.getInt("ideapfacultad"));
           Toto.setIdeap(rs.getInt("ideap"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
        }

   } catch (Exception e) {
        }
   finally{getCerrarConexion();}
    return Toto;
    } 
    
    public Eap eapEjeUsuario(int to){
    String sql="SELECT e.ideap,e.nombre,e.descripcion,ef.ideapfacultad FROM persona p INNER JOIN usuario u ON " +
    " p.idpersona=u.idpersona INNER JOIN coordinadoreap ce ON  " +
    " ce.idpersona=p.idpersona INNER JOIN eapfacultad ef ON  " +
    " ef.idcoordinadoreap=ce.idcoordinadoreap INNER JOIN eap e ON e.ideap=ef.ideap AND u.idusuario=? ";
    Eap Toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, to);
            System.out.println("idUsuario--->"+to);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
         Toto  = new Eap();
           Toto.setIdeapfacultad(rs.getInt("ideapfacultad"));
           Toto.setIdeap(rs.getInt("ideap"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
        }

   } catch (Exception e) {
        }
   finally{getCerrarConexion();}
    return Toto;
    } 
    

    
       
// Objeto idfilial
       
  public Facultad facultadUsuario(int to){
    String sql="SELECT ff.idfilialfacultad,f.idfacultad,f.nombre,f.descripcion FROM " +
                "persona p INNER JOIN usuario u ON p.idpersona=u.idpersona " +
                "INNER JOIN coordinadoreap ce ON ce.idpersona=p.idpersona INNER " +
                "JOIN eapfacultad ef ON ef.idcoordinadoreap=ce.idcoordinadoreap " +
                "INNER JOIN filialfacultad ff ON ff.idfilialfacultad=ef.idfilialfacultad " +
                "INNER JOIN facultad f ON f.idfacultad=ff.idfacultad AND u.idusuario=?";
      Facultad Toto = null;
    int id=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, to);
            System.out.println("idUsuario--->"+to);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Facultad();
           Toto.setIdfilialfacultad(rs.getInt("idfilialfacultad"));
           Toto.setIdfacultad(rs.getInt("idfacultad"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
        }

} catch (Exception e) {
        }
   finally{getCerrarConexion();}
    return Toto;
    } 
  public Facultad facultadUsuarioEje(int to, int idFilial){
    String sql=" SELECT ff.idfilialfacultad,f.idfacultad,f.nombre,f.descripcion FROM persona p "
            + " INNER JOIN usuario u ON p.idpersona=u.idpersona "
            + " INNER JOIN coordinador_eje ce ON ce.idpersona=p.idpersona "
            + " INNER JOIN coordinador_ejeeap ee ON  ee.idcoordinadoreje=ce.idcoordinadoreje "
            + " INNER JOIN eapfacultad ef ON ef.ideapfacultad=ee.ideapfacultad "
            + " INNER JOIN filialfacultad ff ON ff.idfilialfacultad=ef.idfilialfacultad "
            + " INNER JOIN facultad f ON f.idfacultad=ff.idfacultad AND u.idusuario=? AND u.idfilial=? ";
      Facultad Toto = null;
    int id=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, to);
            ps.setInt(2, idFilial);
            System.out.println("idUsuario--->"+to);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Facultad();
           Toto.setIdfilialfacultad(rs.getInt("idfilialfacultad"));
           Toto.setIdfacultad(rs.getInt("idfacultad"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
        }

} catch (Exception e) {
        }
   finally{getCerrarConexion();}
    return Toto;
    } 
    
    
public Filial filialUsuario(int to){
    String sql="SELECT f.idfilial, f.descripcion FROM filial f " +
            " INNER JOIN usuario u ON u.idfilial=f.idfilial AND u.idusuario=?";

   Filial Toto = null;
    int iksf=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(++iksf, to);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Filial();
           Toto.setIdfilial(rs.getInt("idfilial"));
           Toto.setDescripcion(rs.getString("descripcion"));

        }

} catch (Exception e) {
        }
        finally{getCerrarConexion();}
        return Toto;
     }
    
    
  
       
       
    
    public List listar_usuario() {
        List reporte = new ArrayList();
        Usuario to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT u.idusuario,u.usuario,u.password,u.estado,u.fecha_acceso,u.fecha_alta,u.idpersona,p.nombre,p.apellipaterno,p.apellimaterno,u.idfilial,f.direccion,u.idcategoriausuario,(cu.nombre)AS perfil FROM usuario u "
                    + "INNER JOIN persona p ON u.idpersona = p.idpersona "
                    + "INNER JOIN filial f ON u.idfilial = f.idfilial "
                    + "INNER JOIN categoriausuario cu ON u.idcategoriausuario = cu.idcategoriausuario ORDER BY idusuario asc ");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Usuario();
                to.setIdusuario(rs.getString("idusuario"));
                to.setUsuario(rs.getString("usuario"));
                to.setPassword(rs.getString("password"));
                to.setEstado(rs.getString("estado"));
                to.setFechaAcceso(rs.getString("fecha_acceso"));
                to.setFechaAlta(rs.getString("fecha_alta"));
                to.setIdpersona(rs.getString("idpersona"));
                to.setIdpersona_nombre(rs.getString("nombre"));
                to.setIdpersona_apell_paterno(rs.getString("apellipaterno"));
                to.setIdpersona_apell_materno(rs.getString("apellimaterno"));
                to.setIdfilial(rs.getString("idfilial"));
                to.setIdfilial_nombre(rs.getString("direccion"));
                to.setIdcategoriausuario(rs.getString("idcategoriausuario"));
                to.setIdcategoriausuario_nombre(rs.getString("perfil"));
                reporte.add(to);
                System.out.println("Reportado..!");

            }
        } catch (Exception e) {
            System.out.println("Error en Reporte ..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }
    
    
     public List listar_categoria_usuario() {
        List reporte = new ArrayList();
        Categoriausuario to;
        try {
            getConexionDb();
            ps = con.prepareStatement("select * from categoriausuario");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Categoriausuario();
                to.setIdcategoriausuario(rs.getInt("idcategoriausuario"));
                to.setNombre(rs.getString("nombre"));
                to.setDescripcion(rs.getString("descripcion"));
                reporte.add(to);
                
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }
     
     public List listar_categoria_usuario_filial() {
        List reporte = new ArrayList();
        Categoriausuario to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM categoriausuario WHERE idCategoriaUsuario>=3 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Categoriausuario();
                to.setIdcategoriausuario(rs.getInt("idcategoriausuario"));
                to.setNombre(rs.getString("nombre"));
                to.setDescripcion(rs.getString("descripcion"));
                reporte.add(to);
                
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }
     
     public List listar_categoria_usuario_facultad() {
        List reporte = new ArrayList();
        Categoriausuario to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM categoriausuario WHERE idcategoriausuario=4");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Categoriausuario();
                to.setIdcategoriausuario(rs.getInt("idcategoriausuario"));
                to.setNombre(rs.getString("nombre"));
                to.setDescripcion(rs.getString("descripcion"));
                reporte.add(to);
                
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }
     
     
     
      public List listar_filial_usuario() {
        List reporte = new ArrayList();
        Filial to;
        try {
            getConexionDb();
            ps = con.prepareStatement("select * from filial");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Filial();
                to.setIdfilial(rs.getInt("idfilial"));
                to.setDireccion(rs.getString("direccion"));
                reporte.add(to);
                
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }
      
      public List listar_persona() {
        List reporte = new ArrayList();
        Persona to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT p.idpersona,p.nombre,p.apellipaterno,p.apellimaterno,p.dni,p.sexo,p.email,p.telefono,p.celular,p.fechanacimiento,p.idfilial,f.direccion FROM persona p INNER JOIN filial f ON p.idfilial=f.idfilial ORDER BY idpersona asc ");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Persona();
                to.setIdpersona(rs.getInt("idpersona"));
                to.setNombre(rs.getString("nombre"));
                to.setApellipaterno(rs.getString("apellipaterno"));
                to.setApellimaterno(rs.getString("apellimaterno"));
                to.setDni(rs.getString("dni"));
                to.setSexo(rs.getString("sexo"));
                to.setTelefono(rs.getString("telefono"));
                to.setCelular(rs.getString("celular"));
                to.setEmail(rs.getString("email"));
                to.setFechanacimiento(rs.getString("fechanacimiento"));
                to.setIdfilial(rs.getInt("idfilial"));
                to.setIdfilialname(rs.getString("direccion"));
                
                reporte.add(to);
                
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }
    
      // registrar Usuario como superadministrador
      
      
      public int registrar_usuario(Usuario to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into "
                    + "usuario(usuario, password, estado, fecha_acceso, fecha_alta, idpersona, idfilial, idcategoriausuario, idperspectiva, idejeestrategico, idtipoarea) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, to.getUsuario());
            ps.setString(2, cry.encrypt(to.getPassword()));
            ps.setString(3, to.getEstado());
            ps.setString(4, to.getFechaAcceso());
            ps.setString(5, to.getFechaAlta());
            ps.setString(6, to.getIdpersona());
            ps.setString(7, to.getIdfilial());
            ps.setString(8, to.getIdcategoriausuario());
            
            ps.setInt(9, to.getIdperspectiva());
            ps.setInt(10, to.getIdejeestrategico());
            ps.setInt(11, to.getIdtipoarea());

            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
    
      public int registrarCoordinadorEje(CoordinadorEje to){
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement(" INSERT INTO coordinador_eje(idPersona, idPeriodo) VALUES( ?, ?) ");
            ps.setInt(1, to.getIdPersona());
            ps.setInt(2, to.getIdPeriodo());            
            if (ps.executeUpdate() == 1) {
                r = 1;                
                System.out.println("Insertado!!!");
            }
            } catch (Exception e) {
            System.out.println(e.getMessage());
            }
        return r;
    }
      public int registrarEapCoordinadorEje(CoordinadorEjeeap to){
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement(" INSERT INTO coordinador_ejeeap(idEapFacultad, idcoordinadoreje) VALUES( ?, ?) ");
            ps.setInt(1, to.getIdEapFacultad());
            ps.setInt(2, to.getIdcoordinadoreje());            
            if (ps.executeUpdate() == 1) {
                r = 1;                
                System.out.println("Insertado!!!");
            }
            } catch (Exception e) {
            System.out.println(e.getMessage());
            }
        return r;
    }
      
      
      // eliminar usuario como super administrador
      
      
        public void eliminar_usuario(int idusuario) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM usuario WHERE idusuario=? ");
            ps.setInt(1, idusuario);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
      
        public void Desactivar_Usuario(int idusuario) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update usuario set estado='0' where idUsuario=?");
            ps.setInt(1, idusuario);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo la Temporada Nooo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
    public void Activar_Usuario(int idusuario) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update usuario set estado='1' where idUsuario=?");
            ps.setInt(1, idusuario);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Activo la Temporada Yess");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
    public void cambiarClaveUsuario(int idusuario, String clavenuevo) {
        try {
            getConexionDb();
            ps = con.prepareStatement("UPDATE usuario SET PASSWORD=? WHERE idusuario=? ");
            ps.setString(1, cry.encrypt(clavenuevo));
            ps.setInt(2, idusuario);            
            if (ps.executeUpdate() == 1) {
                System.out.println("Se cambio correctamente!");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
    
        
        // validacion de login usuario
        
        
           
 public List<Usuario> validar(Usuario to){
    
     String sql="select * from usuario where usuario=? and password=? and estado=1";
    List<Usuario> Lista = new ArrayList<Usuario>(); 
      Usuario u;
    int ik=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setString(++ik, to.getUsuario());
            ps.setString(++ik, cry.encrypt(to.getPassword()));
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           u = new Usuario();
           u.setIdusuario(rs.getString("idusuario"));
           u.setUsuario(rs.getString("usuario"));
           u.setPassword(rs.getString("password"));
           Lista.add(u);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
    return Lista;
    }    
 
    
    public String Id_usuario_session (Usuario to){
    String sql="select * from usuario where usuario=? and password=?";
    List<Usuario> Lista = new ArrayList<Usuario>(); 
    Usuario Toto = null;
    
   
    int iks=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setString(++iks, to.getUsuario());
            ps.setString(++iks, cry.encrypt(to.getPassword()));
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Usuario();
           Toto.setIdusuario(rs.getString("idusuario"));
           Lista.add(Toto);
        }

} catch (Exception e) {
        }
        finally{getCerrarConexion();}
             String  id=Toto.getIdusuario();
        return id;
     }
    public int idPeriodoActivo(){
    String sql=" SELECT * FROM  periodo WHERE estado=1 ";
    int id=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);        
            rs=ps.executeQuery();
        if(rs.next()) {            
         id=rs.getInt("idPeriodo");
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}            
        return id;
     }
    
    public List coordinadorEje(int idFilial){
    String sql=" SELECT ce.idcoordinadoreje, ce.idPersona, CONCAT(p.nombre,' ', p.apellipaterno, ' ', p.apellimaterno) AS nombre  FROM  coordinador_eje ce INNER JOIN persona p ON (ce.idPersona=p.idPersona) AND idfilial=? ";
    List lista=new ArrayList();
    CoordinadorEje to;
    int id=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idFilial);
            rs=ps.executeQuery();
            while(rs.next()) {
            to=new CoordinadorEje();
            to.setIdcoordinadoreje(rs.getInt("idcoordinadoreje"));
            to.setIdPersona(rs.getInt("idPersona"));
            to.setNombrepersona(rs.getString("nombre"));            
            lista.add(to);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}            
        return lista;
     }
    
    
    public String Id_filial_usuario (Usuario to){
    String sql="select * from usuario where usuario=? and password=?";
    List<Usuario> Lista = new ArrayList<Usuario>(); 
    Usuario Toto = null;
    
   
    int iksf=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setString(++iksf, to.getUsuario());
            ps.setString(++iksf, cry.encrypt(to.getPassword()));
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Usuario();
           Toto.setIdfilial(rs.getString("idfilial"));
           Lista.add(Toto);
        }

} catch (Exception e) {
        }
        finally{getCerrarConexion();}
             String  id=Toto.getIdfilial();
        return id;
     }
    
    public String Id_categoria_usuario (Usuario to){
    String sql="select * from usuario where usuario=? and password=?";
    List<Usuario> Lista = new ArrayList<Usuario>(); 
    Usuario Toto = null;
    int x=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setString(++x, to.getUsuario());
            ps.setString(++x, cry.encrypt(to.getPassword()));
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Usuario();
           Toto.setIdcategoriausuario(rs.getString("idcategoriausuario"));
           Lista.add(Toto);
        }

} catch (Exception e) {
        }
        finally{getCerrarConexion();}
             String  id=Toto.getIdcategoriausuario();
        return id;
     }
    public int extraUltimoIdPersona(String dni){
    String sql=" SELECT MAX(idpersona) as id FROM persona WHERE dni=? ";
    int x=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setString(1, dni);            
            rs=ps.executeQuery();
        if (rs.next()) 
        {                       
           x=rs.getInt("id");           
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}             
        return x;
     }
    public Periodo periodoVigenteUPeU(){
    String sql=" SELECT * FROM periodo WHERE estado=1 AND YEAR(NOW())=periodo ";
    Periodo per=new Periodo();
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);                        
            rs=ps.executeQuery();
        if (rs.next()){                       
           per.setIdperiodo(rs.getInt("idperiodo"));
           per.setPeriodo(rs.getString("periodo"));
           per.setEstado(rs.getString("estado"));
           per.setFechainicio(rs.getString("fechainicio"));
           per.setFechafin(rs.getString("fechafin"));
           per.setIdtemporada(rs.getInt("idtemporada"));
        }} catch (Exception e) { System.out.println(e.getMessage());
        }
        finally{getCerrarConexion();}             
        return per;
     }
    
    
    
     
 public List listar_usuario_filial(String idfilial) { 
        List reporte = new ArrayList();
        Usuario to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT u.idusuario,u.usuario,u.password,u.estado,u.fecha_acceso,u.fecha_alta,u.idpersona,p.nombre,p.apellipaterno,p.apellimaterno,u.idfilial,f.direccion,u.idcategoriausuario,(cu.nombre)AS perfil FROM usuario u "
                    + "INNER JOIN persona p ON u.idpersona = p.idpersona "
                    + "INNER JOIN filial f ON u.idfilial = f.idfilial "
                    + "INNER JOIN categoriausuario cu ON u.idcategoriausuario = cu.idcategoriausuario where f.idfilial=? and u.estado=1 "
                    + "AND u.idcategoriausuario IN (SELECT cu.idcategoriausuario FROM categoriausuario cu WHERE cu.idcategoriausuario IN ('1', '2', '3', '4', '5','6'));");
            ps.setString(1, idfilial);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Usuario();
                to.setIdusuario(rs.getString("idusuario"));
                to.setUsuario(rs.getString("usuario"));
                to.setPassword(rs.getString("password"));
                to.setEstado(rs.getString("estado"));
                to.setFechaAcceso(rs.getString("fecha_acceso"));
                to.setFechaAlta(rs.getString("fecha_alta"));
                to.setIdpersona(rs.getString("idpersona"));
                to.setIdpersona_nombre(rs.getString("nombre"));
                to.setIdpersona_apell_paterno(rs.getString("apellipaterno"));
                to.setIdpersona_apell_materno(rs.getString("apellimaterno"));
                to.setIdfilial(rs.getString("idfilial"));
                to.setIdfilial_nombre(rs.getString("direccion"));
                to.setIdcategoriausuario(rs.getString("idcategoriausuario"));
                to.setIdcategoriausuario_nombre(rs.getString("perfil"));
                reporte.add(to);

            }
        } catch (Exception e) {
            System.out.println("Error en Reporte de datos del usuario..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
 
 public List listar_usuario_filial_priv(String idfilial) { 
        List reporte = new ArrayList();
        Usuario to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT u.idusuario,u.usuario,u.password,u.estado,u.fecha_acceso,u.fecha_alta,u.idpersona,p.nombre,p.apellipaterno,p.apellimaterno,u.idfilial,f.direccion,u.idcategoriausuario,(cu.nombre)AS perfil FROM usuario u "
                    + "INNER JOIN persona p ON u.idpersona = p.idpersona "
                    + "INNER JOIN filial f ON u.idfilial = f.idfilial "
                    + "INNER JOIN categoriausuario cu ON u.idcategoriausuario = cu.idcategoriausuario where f.idfilial=?");
            ps.setString(1, idfilial);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Usuario();
                to.setIdusuario(rs.getString("idusuario"));
                to.setUsuario(rs.getString("usuario"));
                to.setPassword(rs.getString("password"));
                to.setEstado(rs.getString("estado"));
                to.setFechaAcceso(rs.getString("fecha_acceso"));
                to.setFechaAlta(rs.getString("fecha_alta"));
                to.setIdpersona(rs.getString("idpersona"));
                to.setIdpersona_nombre(rs.getString("nombre"));
                to.setIdpersona_apell_paterno(rs.getString("apellipaterno"));
                to.setIdpersona_apell_materno(rs.getString("apellimaterno"));
                to.setIdfilial(rs.getString("idfilial"));
                to.setIdfilial_nombre(rs.getString("direccion"));
                to.setIdcategoriausuario(rs.getString("idcategoriausuario"));
                to.setIdcategoriausuario_nombre(rs.getString("perfil"));
                reporte.add(to);

            }
        } catch (Exception e) {
            System.out.println("Error en Reporte de datos del usuario..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
 
 public List listar_personas_filial(String idfilial) { 
        List reporte = new ArrayList();
        Persona to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT p.idpersona,p.nombre,p.apellipaterno,p.apellimaterno,p.dni,p.sexo,p.email,p.telefono,p.celular,p.fechanacimiento,p.idfilial,f.direccion FROM persona p "
                    + "INNER JOIN filial f ON p.idfilial=f.idfilial WHERE f.idFilial=?");
            ps.setString(1, idfilial);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Persona();
                to.setIdpersona(rs.getInt("idpersona"));
                to.setNombre(rs.getString("nombre"));
                to.setApellipaterno(rs.getString("apellipaterno"));
                to.setApellimaterno(rs.getString("apellimaterno"));
                to.setDni(rs.getString("dni"));
                to.setSexo(rs.getString("sexo"));
                to.setTelefono(rs.getString("telefono"));
                to.setCelular(rs.getString("celular"));
                to.setEmail(rs.getString("email"));
                to.setFechanacimiento(rs.getString("fechanacimiento"));
                to.setIdfilial(rs.getInt("idfilial"));
                to.setIdfilialname(rs.getString("direccion"));
                
                reporte.add(to);
            }
        } catch (Exception e) {
            System.out.println("Error en Reporte de datos del usuario..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
 
 
    public Usuario datos_usuario(String idusuario) {
        Usuario u = new Usuario();
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT u.idusuario,u.usuario,u.password, u.idejeestrategico, "
                    + "f.idfilial, u.idperspectiva, u.idtipoarea, f.direccion,p.idpersona,(p.nombre)as per_name,p.apellipaterno,"
                    + "p.apellimaterno,c.idcategoriausuario,(c.nombre)as name_cat FROM usuario u "
                    + "INNER JOIN filial f ON u.idfilial = f.idfilial "
                    + "INNER JOIN persona p ON u.idpersona = p.idpersona "
                    + "INNER JOIN categoriausuario c ON u.idcategoriausuario = c.idcategoriausuario "
                    + " where u.idusuario=?");
            ps.setString(1, idusuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                u.setIdusuario(rs.getString("u.idusuario"));
                u.setUsuario(rs.getString("u.usuario"));
                u.setPassword(cry.decrypt(rs.getString("u.password")));
                u.setIdpersona(rs.getString("p.idpersona"));
                u.setIdpersona_nombre(rs.getString("per_name"));
                u.setIdpersona_apell_paterno(rs.getString("p.apellipaterno"));
                u.setIdpersona_apell_materno(rs.getString("p.apellimaterno"));
                u.setIdfilial(rs.getString("f.idfilial"));
                u.setIdfilial_nombre(rs.getString("f.direccion"));
                u.setIdcategoriausuario(rs.getString("idcategoriausuario"));
                u.setIdcategoriausuario_nombre(rs.getString("name_cat"));
                u.setIdejeestrategico(rs.getInt("idejeestrategico"));
                u.setIdperspectiva(rs.getInt("idperspectiva"));
                u.setIdtipoarea(rs.getInt("idtipoarea"));
            }
        } catch (Exception e) {
        }
        return u;
    }

 public int registrar_persona(Persona to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into "
                    + "persona(nombre, apellipaterno, apellimaterno, dni, sexo, telefono, celular, email, fechanacimiento, idfilial) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, to.getNombre());
            ps.setString(2, to.getApellipaterno());
            ps.setString(3, to.getApellimaterno());
            ps.setString(4, to.getDni());
            ps.setString(5, to.getSexo());
            ps.setString(6, to.getTelefono());
            ps.setString(7, to.getCelular());
            ps.setString(8, to.getEmail());
            ps.setString(9, to.getFechanacimiento());
            ps.setInt(10, to.getIdfilial());

            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Insertado!!!");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
 
   public void eliminar_persona(int idpersona) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM persona WHERE idpersona=? ");
            ps.setInt(1, idpersona);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado --------------> 1222");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
   public Persona Buscar_Persona_Id(String idPersona) {
        Persona p = new Persona();
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM persona p "
                    + "INNER JOIN filial f ON p.idfilial=f.idfilial "
                    + "WHERE idpersona=?");
            ps.setInt(1, Integer.parseInt(idPersona));
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setIdpersona(rs.getInt("idpersona"));
                p.setNombre(rs.getString("nombre"));
                p.setApellipaterno(rs.getString("apellipaterno"));
                p.setApellimaterno(rs.getString("apellimaterno"));
                p.setDni(rs.getString("dni"));
                p.setSexo(rs.getString("sexo"));
                p.setTelefono(rs.getString("telefono"));
                p.setCelular(rs.getString("celular"));
                p.setEmail(rs.getString("email"));
                p.setFechanacimiento(rs.getString("fechanacimiento"));
                p.setIdfilial(rs.getInt("idfilial"));
                p.setIdfilialname(rs.getString("direccion"));
                
            }
        } catch (Exception e) {
        }
        return p;
    }
   public void Actualizar_Datos_Persona(Persona to, int idpersona) {
        try {
            System.out.println(" metodoooo");
            getConexionDb();
            ps = con.prepareStatement("update persona set nombre=?, apellipaterno=?, apellimaterno=?,dni=?, sexo=?, telefono=?, celular=?, email=?, fechanacimiento=?, idfilial=? WHERE idpersona=?");
           
            System.out.println("------------>"+to.getApellimaterno());
            ps.setString(1, to.getNombre());
            ps.setString(2, to.getApellipaterno());
            ps.setString(3, to.getApellimaterno());
            ps.setString(4, to.getDni());
            ps.setString(5, to.getSexo());
            ps.setString(6, to.getTelefono());
            ps.setString(7, to.getCelular());
            ps.setString(8, to.getEmail());
            ps.setString(9, to.getFechanacimiento());
            ps.setInt(10, to.getIdfilial());
            ps.setInt(11, idpersona);

            ps.executeUpdate();

            System.out.println("Actualizado ---- >"+to.getNombre());

        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
   
   public void Actualizar_Datos_Usuario(Usuario to, int idusuario) {
        try {
            System.out.println(" metodoooo usuario");
            getConexionDb();
            ps = con.prepareStatement("update usuario set usuario=?, password=?, estado=?,fecha_acceso=?, idpersona=?, idfilial=?, idcategoriausuario=? WHERE idusuario=?");
           
            System.out.println("------------>"+to.getIdusuario());
            ps.setString(1, to.getUsuario());
            ps.setString(2, cry.encrypt(to.getPassword()));
            ps.setString(3, to.getEstado());
            ps.setString(4, to.getFechaAcceso());
            ps.setString(5, to.getIdpersona());
            ps.setString(6, to.getIdfilial());
            ps.setString(7, to.getIdcategoriausuario());
            ps.setInt(8, idusuario);

            ps.executeUpdate();

            System.out.println("Actualizado ---- >"+to.getUsuario());

        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
   
   public Usuario Buscar_Usuario_Id(String idusuario) {
        Usuario p = new Usuario();
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT u.idusuario,u.usuario,u.password,u.fecha_acceso,"
                    + "f.idfilial,f.direccion,p.idpersona,(p.nombre)AS per_name,p.apellipaterno,"
                    + "p.apellimaterno,c.idcategoriausuario,(c.nombre)AS name_cat FROM usuario u "
                    + "INNER JOIN filial f ON u.idfilial = f.idfilial "
                    + "INNER JOIN persona p ON u.idpersona = p.idpersona "
                    + "INNER JOIN categoriausuario c ON u.idcategoriausuario = c.idcategoriausuario "
                    + "WHERE u.idusuario=?");
             ps.setInt(1, Integer.parseInt(idusuario));
            rs = ps.executeQuery();
            if (rs.next()){
                p.setIdusuario(rs.getString("idusuario"));
                p.setUsuario(rs.getString("usuario"));
                p.setPassword(cry.decrypt(rs.getString("password")));
                p.setFechaAcceso(rs.getString("fecha_acceso"));
                p.setIdpersona(rs.getString("idpersona"));
                p.setIdpersona_nombre(rs.getString("per_name"));
                p.setIdpersona_apell_paterno(rs.getString("apellipaterno"));
                p.setIdpersona_apell_materno(rs.getString("apellimaterno"));
                p.setIdfilial(rs.getString("idfilial"));
                p.setIdfilial_nombre(rs.getString("direccion"));
                p.setIdcategoriausuario(rs.getString("idcategoriausuario"));
                p.setIdcategoriausuario_nombre(rs.getString("name_cat"));
            }
        } catch (Exception e) {
        }
        return p;
    }
   
    
 public List Autocomplete_Persona(String query) { 
        List reporte = new ArrayList();
        Persona to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM persona WHERE dni LIKE '%"+query+"%'");
            rs = ps.executeQuery();
            int cnt=1;
            while (rs.next()) {
                to = new Persona();
                to.setDni(rs.getString("dni"));
                //to.setNombre(rs.getString("nombre"));
                if(cnt>=10)break;
			cnt++;
                reporte.add(to);
            }
        } catch (Exception e) {
            System.out.println("Error en autocomplete" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
    
 // registro array
 
 
 
 
 public int registro_privilegio_array(Privilegio to) {
        int r = 0;
        try {
            
            
            String[] edwin=to.getIdacceso_array();
            String[] edwin_edu=to.getEstado();
             
            if ( edwin!= null) {
  for (int w = 0; w < edwin.length; w++) {
            
            getConexionDb();
            ps = con.prepareStatement("insert into privilegio(idusuario, idacceso, estado) "
                    + "values (?, ?, ?)");
            ps.setString(1, to.getIdusuario());
            ps.setString(2, edwin[w]);
            ps.setString(3, edwin_edu[w]);
         
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado Array!!!");
            }else{
                System.out.println("error !!"+r);
            }
            
            }
   }else{System.out.println("No Hay  accesos de array elegidos");}
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
      
      public List Lista_Accesos() {
        List reporte = new ArrayList();
          Acceso to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM acceso");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Acceso();
                to.setIdacceso(rs.getInt("idacceso"));
                to.setNombre(rs.getString("nombre"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setUrl(rs.getString("url"));
                reporte.add(to);
                System.out.println("Reportado..!");
            }
        } catch (Exception e) {
            System.out.println("Error en Reporte ..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }
    
      
      
       public List Proximo_IDPersona() {
        List reporte = new ArrayList();
          Persona to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT (AUTO_INCREMENT)AS proximoIDP FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'bd_sge_v10' AND TABLE_NAME = 'persona'");
            rs = ps.executeQuery();
            if (rs.next()) {
                to = new Persona();
                to.setIdpersona(rs.getInt("proximoIDP"));
                reporte.add(to);
                System.out.println("Proximo ID de la Persona");
            }else{}
        } catch (Exception e) {
            System.out.println("Error al Obtener..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }
       
       
        public int Registrar_Datos_Cuenta(Persona to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into "
                    + "persona(idpersona,nombre, apellipaterno, apellimaterno, dni, sexo, telefono, celular, email, fechanacimiento, idfilial) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, to.getIdpersona());
            ps.setString(2, to.getNombre());
            ps.setString(3, to.getApellipaterno());
            ps.setString(4, to.getApellimaterno());
            ps.setString(5, to.getDni());
            ps.setString(6, to.getSexo());
            ps.setString(7, to.getTelefono());
            ps.setString(8, to.getCelular());
            ps.setString(9, to.getEmail());
            ps.setString(10, to.getFechanacimiento());
            ps.setInt(11, to.getIdfilial());

            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Se Inserto Datos de Cuenta");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
    
        
        
        
        // COORDINADORES
        
        
        public List listar_Coordinador_facultad(String idPeriodo, String idfilial) { 
        List reporte = new ArrayList();
            Coordinadorfacultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM coordinadorfacultad cf "
                    + "INNER JOIN persona p ON cf.idpersona =p.idpersona "
                    + "INNER JOIN filial f ON p.idfilial =f.idfilial "
                    + "INNER JOIN periodo pr ON cf.idPeriodo =pr.idPeriodo "
                    + "WHERE pr.idPeriodo=? AND f.idfilial=?");
            ps.setString(1, idPeriodo);
            ps.setString(2, idfilial);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Coordinadorfacultad();
                to.setIdcoordinadorfacultad(rs.getInt("idCoordinadorFacultad"));
                to.setIdpersona(rs.getInt("idPersona"));
                to.setIdpersona_nombre(rs.getString("nombre"));
                to.setIdpersona_apellpaterno(rs.getString("apellipaterno"));
                to.setIdpersona_apellmaterno(rs.getString("apellimaterno"));
                to.setIdpersona_dni(rs.getString("dni"));
                to.setIdperiodo(rs.getInt("idPeriodo"));
                to.setIdperiodo_name(rs.getString("periodo"));
                reporte.add(to);
            }
        } catch (Exception e) {
            System.out.println("Error en Reporte ..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
        public List listar_Coordinador_facultad_ALL(String idfilial) { 
        List reporte = new ArrayList();
            Coordinadorfacultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM coordinadorfacultad cf "
                    + "INNER JOIN persona p ON cf.idpersona =p.idpersona "
                    + "INNER JOIN filial f ON p.idfilial =f.idfilial "
                    + "INNER JOIN periodo pr ON cf.idPeriodo =pr.idPeriodo "
                    + "WHERE f.idfilial=? ORDER BY idCoordinadorFacultad ");
            ps.setString(1, idfilial);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Coordinadorfacultad();
                to.setIdcoordinadorfacultad(rs.getInt("idCoordinadorFacultad"));
                to.setIdpersona(rs.getInt("idPersona"));
                to.setIdpersona_nombre(rs.getString("nombre"));
                to.setIdpersona_apellpaterno(rs.getString("apellipaterno"));
                to.setIdpersona_apellmaterno(rs.getString("apellimaterno"));
                to.setIdpersona_dni(rs.getString("dni"));
                to.setIdperiodo(rs.getInt("idPeriodo"));
                to.setIdperiodo_name(rs.getString("periodo"));
                reporte.add(to);
            }
        } catch (Exception e) {
            System.out.println("Error en Reporte ..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
        
        
        public int Registrar_Coordinador_Facultad(Coordinadorfacultad to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into "
                    + "coordinadorfacultad(idpersona,idPeriodo) "
                    + "values (?, ?)");
            ps.setInt(1, to.getIdpersona());
            ps.setInt(2, to.getIdperiodo());
            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Se Inserto ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
        }
    
        public void EliminarCoordinadorFacultad(int idCoordinadorFacultad) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM coordinadorfacultad WHERE idCoordinadorFacultad=? ");
            ps.setInt(1, idCoordinadorFacultad);
            if (ps.executeUpdate() == 1) {
            System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
        }
        
        public Coordinadorfacultad buscarCoordinadorFacultadId(String idCoordinadorFacultad) {
        Coordinadorfacultad c = new Coordinadorfacultad();
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM `coordinadorfacultad` cf "
                    + "INNER JOIN persona p ON cf.idpersona =p.idpersona "
                    + "INNER JOIN periodo per ON cf.idPeriodo =per.idPeriodo "
                    + "where idCoordinadorFacultad=? ");
            ps.setInt(1, Integer.parseInt(idCoordinadorFacultad));
            rs = ps.executeQuery();
            if (rs.next()) {
                c.setIdcoordinadorfacultad(rs.getInt("idCoordinadorFacultad"));
                c.setIdpersona(rs.getInt("idpersona"));
                c.setIdpersona_nombre(rs.getString("nombre"));
                c.setIdpersona_apellpaterno(rs.getString("apellipaterno"));
                c.setIdpersona_apellmaterno(rs.getString("apellimaterno"));
                c.setIdperiodo(rs.getInt("idPeriodo"));
                c.setIdperiodo_name(rs.getString("periodo"));
            }
        } catch (Exception e) {
        }
        return c;
        }
        
        public void ActualizarCoordinadorfacultad(Coordinadorfacultad to, int idCoordinadorFacultad) {
        try {

            getConexionDb();
            ps = con.prepareStatement("update coordinadorfacultad set idpersona=?, idPeriodo=? WHERE idCoordinadorFacultad=?");
            ps.setInt(1, to.getIdpersona());
            ps.setInt(2, to.getIdperiodo());
            ps.setInt(3, idCoordinadorFacultad);

            ps.executeUpdate();

            System.out.println("Actualizado");

        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }

        
        public List listar_Coordinador_EAP(String idPeriodo, String idfilial) { 
        List reporte = new ArrayList();
            Coordinadoreap to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM coordinadoreap cf "
                    + "INNER JOIN persona p ON cf.idpersona =p.idpersona "
                    + "INNER JOIN filial f ON p.idfilial =f.idfilial "
                    + "INNER JOIN periodo pr ON cf.idPeriodo =pr.idPeriodo "
                    + "WHERE pr.idPeriodo=? AND f.idfilial=?");
            ps.setString(1, idPeriodo);
            ps.setString(2, idfilial);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Coordinadoreap();
                to.setIdCoordinadorEap(rs.getInt("idCoordinadorEap"));
                to.setIdpersona(rs.getInt("idPersona"));
                to.setIdpersona_nombre(rs.getString("nombre"));
                to.setIdpersona_apellpaterno(rs.getString("apellipaterno"));
                to.setIdpersona_apellmaterno(rs.getString("apellimaterno"));
                to.setIdpersona_dni(rs.getString("dni"));
                to.setIdperiodo(rs.getInt("idPeriodo"));
                to.setIdperiodo_name(rs.getString("periodo"));
                reporte.add(to);
            }
        } catch (Exception e) {
            System.out.println("Error en Reporte ..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

        }
        
        public List listar_Coordinador_EAP_ALL(String idfilial) { 
        List reporte = new ArrayList();
            Coordinadoreap to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM coordinadoreap cf "
                    + "INNER JOIN persona p ON cf.idpersona =p.idpersona "
                    + "INNER JOIN filial f ON p.idfilial =f.idfilial "
                    + "INNER JOIN periodo pr ON cf.idPeriodo =pr.idPeriodo "
                    + "WHERE f.idfilial=? ORDER BY cf.idCoordinadorEap desc");
            ps.setString(1, idfilial);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Coordinadoreap();
                to.setIdCoordinadorEap(rs.getInt("idCoordinadorEap"));
                to.setIdpersona(rs.getInt("idPersona"));
                to.setIdpersona_nombre(rs.getString("nombre"));
                to.setIdpersona_apellpaterno(rs.getString("apellipaterno"));
                to.setIdpersona_apellmaterno(rs.getString("apellimaterno"));
                to.setIdpersona_dni(rs.getString("dni"));
                to.setIdperiodo(rs.getInt("idPeriodo"));
                to.setIdperiodo_name(rs.getString("periodo"));
                reporte.add(to);
            }
        } catch (Exception e) {
            System.out.println("Error en Reporte ..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

        }
        
        
        public int Registrar_Coordinador_EAP(Coordinadoreap to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into "
                    + "coordinadoreap(idpersona,idPeriodo) "
                    + "values (?, ?)");
            ps.setInt(1, to.getIdpersona());
            ps.setInt(2, to.getIdperiodo());
            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Se Inserto ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
        }
    
        public void EliminarCoordinadorEAP(int idCoordinadorEap) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM coordinadoreap WHERE idCoordinadorEap=? ");
            ps.setInt(1, idCoordinadorEap);
            if (ps.executeUpdate() == 1) {
            System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
        }
        
 
        public Coordinadoreap buscarCoordinadorEAPId(String idCoordinadorEap) {
        Coordinadoreap c = new Coordinadoreap();
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM `coordinadoreap` cf "
                    + "INNER JOIN persona p ON cf.idpersona =p.idpersona "
                    + "INNER JOIN periodo per ON cf.idPeriodo =per.idPeriodo "
                    + "where idCoordinadorEap=? ");
            ps.setInt(1, Integer.parseInt(idCoordinadorEap));
            rs = ps.executeQuery();
            if (rs.next()) {
                c.setIdCoordinadorEap(rs.getInt("idCoordinadorEap"));
                c.setIdpersona(rs.getInt("idpersona"));
                c.setIdpersona_nombre(rs.getString("nombre"));
                c.setIdpersona_apellpaterno(rs.getString("apellipaterno"));
                c.setIdpersona_apellmaterno(rs.getString("apellimaterno"));
                c.setIdperiodo(rs.getInt("idPeriodo"));
                c.setIdperiodo_name(rs.getString("periodo"));
            }
        } catch (Exception e) {
        }
        return c;
        }
        
        public void ActualizarCoordinadorEAP(Coordinadoreap to, int idCoordinadorEap) {
        try {

            getConexionDb();
            ps = con.prepareStatement("update coordinadoreap set idpersona=?, idPeriodo=? WHERE idCoordinadorEap=?");
            ps.setInt(1, to.getIdpersona());
            ps.setInt(2, to.getIdperiodo());
            ps.setInt(3, idCoordinadorEap);

            ps.executeUpdate();

            System.out.println("Actualizado");

        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }

        
      
        
}

  
          		
	