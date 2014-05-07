/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.dao;

import java.util.ArrayList;
import java.util.List;
import sge.connexion.DBConn;
import sge.modelo.Eap;
import sge.modelo.Eapfacultad;
import sge.modelo.Facultad;
import sge.modelo.Filial;
import sge.modelo.Filialfacultad;
import sge.modelo.Institucion;
import sge.modelo.Periodo;
import sge.modelo.Temporada;

/**
 *
 * @author Edwin
 */
public class OrganizacionDao extends DBConn{
    
    public List Listar_Datos_Institucion() {
        List reporte = new ArrayList();
        Institucion to;
        try {
            getConexionDb();
            ps = con.prepareStatement("select * from institucion");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Institucion();
                to.setIdinstitucion(rs.getInt("idinstitucion"));
                
                to.setRazonsocial(rs.getString("razonsocial"));
                to.setRuc(rs.getString("ruc"));
                to.setDireccion(rs.getString("direccion"));
                to.setTelefono(rs.getString("telefono"));
                to.setCelular(rs.getString("celular"));
                to.setEmail(rs.getString("email"));
                to.setPaginaweb(rs.getString("paginaweb"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setRector(rs.getString("rector"));
                
                reporte.add(to);
                
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }

       public void Actualizar_Datos_Institucion(Institucion to, int idinstitucion){
        try {
            getConexionDb();
            ps = con.prepareStatement("update institucion set razonsocial=?, ruc=?, direccion=?,telefono=?, celular=?, email=?, paginaweb=?, descripcion=?, rector=?  WHERE idinstitucion=?");
            
            ps.setString(1, to.getRazonsocial());
            ps.setString(2, to.getRuc());
            ps.setString(3, to.getDireccion());
            ps.setString(4, to.getTelefono());
            ps.setString(5, to.getCelular());
            ps.setString(6, to.getEmail());
            ps.setString(7, to.getPaginaweb());
            ps.setString(8, to.getDescripcion());
            ps.setString(9, to.getRector());
            ps.setInt(10, idinstitucion);

            ps.executeUpdate();

            
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
   
          public Institucion Buscar_Institucion_Id(int idinstitucion) {
        Institucion b = new Institucion();
        try {
            getConexionDb();
            ps = con.prepareStatement("select * from institucion where idinstitucion=?");
            ps.setInt(1, idinstitucion);
            rs = ps.executeQuery();
            if (rs.next()) {
                b.setIdinstitucion(rs.getInt("idinstitucion"));
                b.setRazonsocial(rs.getString("razonsocial"));
                b.setRuc(rs.getString("ruc"));
                b.setDireccion(rs.getString("direccion"));
                b.setTelefono(rs.getString("telefono"));
                b.setCelular(rs.getString("celular"));
                b.setEmail(rs.getString("email"));
                b.setPaginaweb(rs.getString("paginaweb"));
                b.setDescripcion(rs.getString("descripcion"));
                b.setRector(rs.getString("rector"));
                
            }
        } catch (Exception e) {
        }
        return b;
    }
          
          
          // TODO DE FILIALES
          
          
              
    public List Listar_Filial() {
        List reporte = new ArrayList();
        Filial to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM filial f "
                    + "INNER JOIN institucion i ON f.idinstitucion = i.idinstitucion ORDER BY idfilial asc");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Filial();
                to.setIdfilial(rs.getInt("idfilial"));
                to.setDireccion(rs.getString("direccion"));
                to.setTelefono(rs.getString("telefono"));
                to.setCelular(rs.getString("celular"));
                to.setEmail(rs.getString("email"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setCategoria(rs.getString("categoria"));
                to.setEstado(rs.getString("estado"));
                to.setIdinstitucion(rs.getInt("idinstitucion"));
                to.setIdinstitucion_name(rs.getString("razonsocial"));
                to.setRector(rs.getString("rector"));
                
                reporte.add(to);
                
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }

    
    public Filial getFilialId(int id) {
        Filial to = new Filial();
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM filial f "
                    + "INNER JOIN institucion i ON f.idinstitucion = i.idinstitucion and f.idfilial=? ORDER BY idfilial DESC");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Filial();
                to.setIdfilial(rs.getInt("idfilial"));
                to.setDireccion(rs.getString("direccion"));
                to.setTelefono(rs.getString("telefono"));
                to.setCelular(rs.getString("celular"));
                to.setEmail(rs.getString("email"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setCategoria(rs.getString("categoria"));
                to.setEstado(rs.getString("estado"));
                to.setIdinstitucion(rs.getInt("idinstitucion"));
                to.setIdinstitucion_name(rs.getString("razonsocial"));
                to.setRector(rs.getString("rector"));

                
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return to;
    }    
    
    
    
    
    
    
    
    
    
    
    
    
    
        public int Registrar_Filial(Filial to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into filial(direccion,telefono,celular,email,descripcion,categoria,estado,idinstitucion,rector) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, to.getDireccion());
            ps.setString(2, to.getTelefono());
            ps.setString(3, to.getCelular());
            ps.setString(4, to.getEmail());
            ps.setString(5, to.getDescripcion());
            ps.setString(6, to.getCategoria());
            ps.setString(7, to.getEstado());
            ps.setInt(8, to.getIdinstitucion());
            ps.setString(9, to.getRector());
           
            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
        
        public Filial BuscarFilialId(String idFilial) {
        Filial f = new Filial();
        try {
            getConexionDb();
            ps = con.prepareStatement("select * from filial "
                    + "where idFilial=? ");
            ps.setInt(1, Integer.parseInt(idFilial));
            rs = ps.executeQuery();
            if (rs.next()) {
                f.setIdfilial(rs.getInt("idFilial"));
                f.setDireccion(rs.getString("direccion"));
                f.setTelefono(rs.getString("telefono"));
                f.setCelular(rs.getString("celular"));
                f.setEmail(rs.getString("email"));
                f.setDescripcion(rs.getString("descripcion"));
                f.setCategoria(rs.getString("categoria"));
                f.setEstado(rs.getString("estado"));
                f.setIdinstitucion(rs.getInt("idinstitucion"));
                f.setRector(rs.getString("rector"));
            }
        } catch (Exception e) {
        }
        return f;
    }

    public void EliminarFilial(int idFilial) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM filial WHERE idFilial=? ");
            ps.setInt(1, idFilial);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }

    public void ActualizarDatosFilial(Filial to, int idFilial) {
        try {

            getConexionDb();
            ps = con.prepareStatement("update filial set direccion=?, telefono=?, celular=?,email=?, descripcion=?, categoria=?, estado=?, idinstitucion=?, rector=? WHERE idFilial=?");
            ps.setString(1, to.getDireccion());
            ps.setString(2, to.getTelefono());
            ps.setString(3, to.getCelular());
            ps.setString(4, to.getEmail());
            ps.setString(5, to.getDescripcion());
            ps.setString(6, to.getCategoria());
            ps.setString(7, to.getEstado());
            ps.setInt(8, to.getIdinstitucion());
            ps.setString(9, to.getRector());
            ps.setInt(10, idFilial);

            ps.executeUpdate();

            System.out.println("Actualizado");

        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }

    // TODO DE FACULTADES
    
    public List Listar_Facultades() {
        List reporte = new ArrayList();
        Facultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT f.*, t.nombre AS tipoarea, t.idtipoarea FROM facultad f, tipoarea t "
                    + " WHERE f.idtipoarea=t.idtipoarea  ORDER BY idfacultad ASC ");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Facultad();
                to.setIdfacultad(rs.getInt("idfacultad"));
                to.setNombre(rs.getString("nombre"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setTipoarea(rs.getString("tipoarea"));
                to.setIdtipoarea(rs.getInt("idtipoarea"));
                reporte.add(to);
                
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }
    public List Listar_FacultadesAreas(int idtipoarea) {
        String subQry;
        if(idtipoarea==3){
        subQry=" AND t.idtipoarea='3' ";
        }else{
        subQry=" AND t.idtipoarea!='3' ";
        }        
        List reporte = new ArrayList();
        Facultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT f.*, t.nombre AS tipoarea, t.idtipoarea FROM facultad f, tipoarea t "
                    + " WHERE f.idtipoarea=t.idtipoarea "+subQry+"  ORDER BY idfacultad ASC ");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Facultad();
                to.setIdfacultad(rs.getInt("idfacultad"));
                to.setNombre(rs.getString("nombre"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setTipoarea(rs.getString("tipoarea"));
                to.setIdtipoarea(rs.getInt("idtipoarea"));
                reporte.add(to);
                
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }

        public int Registrar_Facultad(Facultad to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into facultad(nombre, descripcion) "
                    + "values (?, ?)");
            ps.setString(1, to.getNombre());
            ps.setString(2, to.getDescripcion());
           
            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
    
    public Facultad Buscar_Facultad_Id(int idfacultad) {
        Facultad b = new Facultad();
        try {
            getConexionDb();
            ps = con.prepareStatement("select * from facultad where idfacultad=?");
            ps.setInt(1, idfacultad);
            rs = ps.executeQuery();
            if (rs.next()) {
                b.setIdfacultad(rs.getInt("idfacultad"));
                b.setNombre(rs.getString("nombre"));
                b.setDescripcion(rs.getString("descripcion"));
                 
            }
        } catch (Exception e) {
        }
        return b;
    }
    
       public void Actualizar_Facultad(Facultad to, int idfacultad){
        try {
            getConexionDb();
            ps = con.prepareStatement("update facultad set nombre=?, descripcion=? WHERE idfacultad=?");
            ps.setString(1, to.getNombre());
            ps.setString(2, to.getDescripcion());
            ps.setInt(3, idfacultad);
            ps.executeUpdate();

            System.out.println("Actualizado ---- >"+to.getNombre());

        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
       
       public void EliminarFacultad(int idFacultad) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM facultad WHERE idFacultad=? ");
            ps.setInt(1, idFacultad);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
   
    
    // EAP
       
        public List Listar_EAP(int idTipoarea){
        String subQry;
        if(idTipoarea==3){
        subQry=" AND e.idtipoarea='3' ";
        }else{
        subQry=" AND e.idtipoarea!='3' ";
        }            
        List reporte = new ArrayList();
        Eap to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT e.*, t.nombre AS tipoarea FROM eap e, tipoarea t WHERE e.idtipoarea=t.idtipoarea "+subQry+" ORDER BY ideap ASC  ");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Eap();
                to.setIdeap(rs.getInt("ideap"));
                to.setNombre(rs.getString("nombre"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setCodigo(rs.getString("codigo"));
                to.setTipoarea(rs.getString("tipoarea"));
                reporte.add(to);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }
        public List Listar_EAPPrimero(){
            
        List reporte = new ArrayList();
        Eap to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT e.*, t.nombre AS tipoarea FROM eap e, tipoarea t WHERE e.idtipoarea=t.idtipoarea ORDER BY ideap ASC  ");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Eap();
                to.setIdeap(rs.getInt("ideap"));
                to.setNombre(rs.getString("nombre"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setCodigo(rs.getString("codigo"));
                to.setTipoarea(rs.getString("tipoarea"));
                reporte.add(to);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }
        
        public int Registrar_EAP(Eap to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into eap(nombre, descripcion, idtipoarea, codigo) "
                    + "values (?, ?, ?, ?)");
            ps.setString(1, to.getNombre());
            ps.setString(2, to.getDescripcion());
            ps.setInt(3, to.getIdtipoarea());
            ps.setString(4, to.getCodigo());
           
            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
        
        
        
        
       public Eap BuscarEapIdId(String idEap) {
        Eap x = new Eap();
        try {
            getConexionDb();
            ps = con.prepareStatement(" SELECT e.*, t.idtipoarea AS idtipoarea FROM eap e, tipoarea t WHERE e.idtipoarea=t.idtipoarea AND e.idEap=? ");
            ps.setInt(1, Integer.parseInt(idEap));
            rs = ps.executeQuery();
            if (rs.next()) {
                x.setIdeap(rs.getInt("idEap"));
                x.setNombre(rs.getString("nombre"));
                x.setDescripcion(rs.getString("descripcion"));
                x.setCodigo(rs.getString("codigo"));
                x.setIdtipoarea(rs.getInt("idtipoarea"));                
            }
        } catch (Exception e) {
        }
        return x;
    }

    
       public void ActualizarEap(Eap to, int idEap){
        try {
            getConexionDb();
            ps = con.prepareStatement("update eap set nombre=?, descripcion=?, idtipoarea=?, codigo=? WHERE idEap=?");
            ps.setString(1, to.getNombre());
            ps.setString(2, to.getDescripcion());
            ps.setInt(3, to.getIdtipoarea());
            ps.setString(4, to.getCodigo());
            ps.setInt(5, idEap);
            ps.executeUpdate();

            System.out.println("Actualizado ---- >"+to.getNombre());

        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
       
       public void EliminarEap(int idEap) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM eap WHERE idEap=? ");
            ps.setInt(1, idEap);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
   
        
        public List Listar_Periodo(){
        List reporte = new ArrayList();
            Periodo to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT p.*, t.descripcion FROM periodo p, temporada t WHERE p.idtemporada=t.idtemporada ");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Periodo();
                to.setIdperiodo(rs.getInt("idPeriodo"));
                to.setPeriodo(rs.getString("periodo"));
                to.setEstado(rs.getString("estado"));
                to.setFechainicio(rs.getString("fechainicio"));
                to.setFechafin(rs.getString("fechafin"));
                to.setNombretemporada(rs.getString("descripcion"));
                reporte.add(to);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }
        
        
        public List Listar_Periodo_Activo(){
        List reporte = new ArrayList();
            Periodo to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM periodo where estado=1");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Periodo();
                to.setIdperiodo(rs.getInt("idPeriodo"));
                to.setPeriodo(rs.getString("periodo"));
                to.setEstado(rs.getString("estado"));
                to.setFechainicio(rs.getString("fechainicio"));
                to.setFechafin(rs.getString("fechafin"));
                reporte.add(to);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }

        public int Registrar_Periodo(Periodo to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into periodo(periodo, estado,fechainicio, fechafin, idtemporada) "
                    + "values (?,'0',?,?,?)");
            ps.setString(1, to.getPeriodo());
            ps.setString(2, to.getFechainicio());
            ps.setString(3, to.getFechafin());
            ps.setInt(4, to.getIdtemporada());
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
        }
        public void Desactivar_Periodo(int idPeriodo) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update periodo set estado='0' where idPeriodo=?");
            ps.setInt(1, idPeriodo);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo la idPeriodo Nooo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
        }
        
        public void Activar_Periodo(int idPeriodo) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update periodo set estado='1' where idPeriodo=?");
            ps.setInt(1, idPeriodo);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Activo la idPeriodo Yess");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
        }
    
        public Periodo buscarPeriodoId(String idPeriodo) {
        Periodo p = new Periodo();
        try {
            getConexionDb();
            ps = con.prepareStatement(" SELECT p.*, t.descripcion FROM periodo p, temporada t WHERE p.idtemporada=t.idtemporada AND p.idPeriodo=? ");
            ps.setInt(1, Integer.parseInt(idPeriodo));
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setIdperiodo(rs.getInt("idPeriodo"));
                p.setPeriodo(rs.getString("periodo"));
                p.setEstado(rs.getString("estado"));
                p.setFechainicio(rs.getString("fechainicio"));
                p.setFechafin(rs.getString("fechafin"));
                p.setIdtemporada(rs.getInt("idtemporada"));
                
            }
        } catch (Exception e) {
        }
        return p;
        }
        
        public void actualizarDatosPeriodo(Periodo to, int idPeriodo) {
        try {

            getConexionDb();
            ps = con.prepareStatement("update periodo set periodo=?, estado=?, fechainicio=?,fechafin=?, idtemporada=? WHERE idPeriodo=?");
            ps.setString(1, to.getPeriodo());
            ps.setString(2, to.getEstado());
            ps.setString(3, to.getFechainicio());
            ps.setString(4, to.getFechafin());
            ps.setInt(5, to.getIdtemporada());
            ps.setInt(6, idPeriodo);

            ps.executeUpdate();

            System.out.println("Actualizado");

        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
        }
        
        
        public void EliminarPeriodo(int idPeriodo) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM periodo WHERE idPeriodo=? ");
            ps.setInt(1, idPeriodo);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }

    
        // LISTA DE FILIAL FACULTAD 
        
        public List listar_filial_facultad_desde_usuario(String idfilial, int idTipoarea) { 
        String subQry;
        if(idTipoarea==3){
        subQry=" AND fac.idtipoarea='3' ";
        }else{
        subQry=" AND fac.idtipoarea!='3' ";
        }       
            List reporte = new ArrayList();
            Filialfacultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT fifa.`idFilialfacultad`,fifa.`idcoordinadorfacultad`,(p.`nombre`)AS coordinador,(p.apellipaterno)AS coordinadorpaterno,(p.apellimaterno)AS coordinadormaterno, fifa.`idfilial`,fi.`direccion`,fifa.`idfacultad`,fac.`nombre` "
                    + "  FROM filialfacultad fifa \n" +
                    "INNER JOIN `coordinadorfacultad` cf ON fifa.idcoordinadorfacultad =cf.idcoordinadorfacultad \n" +
                    "INNER JOIN persona p ON cf.idpersona =p.idpersona\n" +
                    "INNER JOIN filial fi ON fifa.idfilial =fi.idfilial\n" +
                    "INNER JOIN facultad fac ON fifa.idfacultad=fac.idfacultad \n" +
                    "WHERE fifa.idfilial=? "+subQry+" ");
            ps.setString(1, idfilial);            
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Filialfacultad();
                to.setIdfilialfacultad(rs.getInt("idFilialfacultad"));
                to.setIdcoordinadorfacultad(rs.getInt("idcoordinadorfacultad"));
                to.setIdcoordinadorfacultad_nombre(rs.getString("coordinador"));
                to.setIdcoordinadorfacultad_apellipaterno(rs.getString("coordinadorpaterno"));
                to.setIdcoordinadorfacultad_apellimaterno(rs.getString("coordinadormaterno"));
                to.setIdfilial(rs.getInt("idfilial"));
                to.setIdfilial_direccion(rs.getString("direccion"));
                to.setIdfacultad(rs.getInt("idfacultad"));
                to.setIdfacultad_nombre(rs.getString("nombre"));
                reporte.add(to);

            }
        } catch (Exception e) {
            System.out.println("Error en Reporte de datos del usuario..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
        public List listar_filial_facultad_desde_usuarioPrimero(String idfilial) { 
        List reporte = new ArrayList();
            Filialfacultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT fifa.`idFilialfacultad`,fifa.`idcoordinadorfacultad`,(p.`nombre`)AS coordinador,(p.apellipaterno)AS coordinadorpaterno,(p.apellimaterno)AS coordinadormaterno, fifa.`idfilial`,fi.`direccion`,fifa.`idfacultad`,fac.`nombre` "
                    + "  FROM filialfacultad fifa \n" +
                    "INNER JOIN `coordinadorfacultad` cf ON fifa.idcoordinadorfacultad =cf.idcoordinadorfacultad \n" +
                    "INNER JOIN persona p ON cf.idpersona =p.idpersona\n" +
                    "INNER JOIN filial fi ON fifa.idfilial =fi.idfilial\n" +
                    "INNER JOIN facultad fac ON fifa.idfacultad=fac.idfacultad \n" +
                    "WHERE fifa.idfilial=?  ");
            ps.setString(1, idfilial);           
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Filialfacultad();
                to.setIdfilialfacultad(rs.getInt("idFilialfacultad"));
                to.setIdcoordinadorfacultad(rs.getInt("idcoordinadorfacultad"));
                to.setIdcoordinadorfacultad_nombre(rs.getString("coordinador"));
                to.setIdcoordinadorfacultad_apellipaterno(rs.getString("coordinadorpaterno"));
                to.setIdcoordinadorfacultad_apellimaterno(rs.getString("coordinadormaterno"));
                to.setIdfilial(rs.getInt("idfilial"));
                to.setIdfilial_direccion(rs.getString("direccion"));
                to.setIdfacultad(rs.getInt("idfacultad"));
                to.setIdfacultad_nombre(rs.getString("nombre"));
                reporte.add(to);

            }
        } catch (Exception e) {
            System.out.println("Error en Reporte de datos del usuario..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
        
        public int Registrar_Filial_Facultad_Filial(Filialfacultad to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into filialfacultad(idcoordinadorfacultad, idfilial,idfacultad) "
                    + "values (?,?,?)");
            ps.setInt(1, to.getIdcoordinadorfacultad());
            ps.setInt(2, to.getIdfilial());
            ps.setInt(3, to.getIdfacultad());
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
        }
        
          public void eliminar_Filialfacultad(int idFilialfacultad) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM filialfacultad WHERE idFilialfacultad=?");
            ps.setInt(1, idFilialfacultad);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
        }
          
          public List Listar_Eap_FacultadPrimero(String idfilial, int idtipoarea) { 
 String subQry;
        if(idtipoarea==3){
        subQry=" AND fac.idtipoarea='3' ";
        }else{
        subQry=" AND fac.idtipoarea!='3' ";
        }        
              List reporte = new ArrayList();
              Eapfacultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT ef.`idEapFacultad`,ef.`idfilialfacultad`,(f.`idFilial`)AS idfili,(fac.`nombre`)AS facultadd,ef.`ideap`,(e.`nombre`)AS eapname,ef.`idcoordinadoreap`,(per.`nombre`)AS name_cord_eap ,(per.`apellipaterno`)AS apellipaterno_cord_eap,(per.`apellimaterno`)AS apellimaterno_cord_eap FROM `eapfacultad` ef\n" +
"INNER JOIN `filialfacultad` ff ON ef.`idfilialfacultad`=ff.`idFilialfacultad`\n" +
"INNER JOIN `filial` f ON ff.idfilial=f.idfilial\n" +
"INNER JOIN facultad  fac ON ff.idfacultad=fac.idfacultad\n" +
"INNER JOIN `eap` e ON ef.ideap =e.ideap\n" +
"INNER JOIN `coordinadoreap` ce ON ef.idcoordinadoreap =ce.idcoordinadoreap\n" +
"INNER JOIN persona per ON ce.idpersona =per.idpersona\n" +
"WHERE ff.`idfilial`='"+idfilial+"' "+subQry+" ");                        
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Eapfacultad();
                to.setIdeapfacultad(rs.getInt("idEapFacultad"));
                to.setIdfilialfacultad(rs.getInt("idFilialfacultad"));
                to.setIdfilialfacultad_name(rs.getString("facultadd"));
                to.setIdeap(rs.getInt("ideap"));
                to.setIdeap_name(rs.getString("eapname"));
                to.setIdcoordinadoreap(rs.getInt("idcoordinadoreap"));
                to.setIdcoordinadoreap_name(rs.getString("name_cord_eap"));
                to.setIdcoordinadoreap_apellipaterno(rs.getString("apellipaterno_cord_eap"));
                to.setIdcoordinadoreap_apellimaterno(rs.getString("apellimaterno_cord_eap"));
                reporte.add(to);

            }
        } catch (Exception e) {
            System.out.println("Error en Reporte de datos." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

        }
          public List Listar_Eap_Facultad(String idfilial) { 
      
              List reporte = new ArrayList();
              Eapfacultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT ef.`idEapFacultad`,ef.`idfilialfacultad`,(f.`idFilial`)AS idfili,(fac.`nombre`)AS facultadd,ef.`ideap`,(e.`nombre`)AS eapname,ef.`idcoordinadoreap`,(per.`nombre`)AS name_cord_eap ,(per.`apellipaterno`)AS apellipaterno_cord_eap,(per.`apellimaterno`)AS apellimaterno_cord_eap FROM `eapfacultad` ef\n" +
"INNER JOIN `filialfacultad` ff ON ef.`idfilialfacultad`=ff.`idFilialfacultad`\n" +
"INNER JOIN `filial` f ON ff.idfilial=f.idfilial\n" +
"INNER JOIN facultad  fac ON ff.idfacultad=fac.idfacultad\n" +
"INNER JOIN `eap` e ON ef.ideap =e.ideap\n" +
"INNER JOIN `coordinadoreap` ce ON ef.idcoordinadoreap =ce.idcoordinadoreap\n" +
"INNER JOIN persona per ON ce.idpersona =per.idpersona\n" +
"WHERE ff.`idfilial`=?  ");
            ps.setString(1, idfilial);            
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Eapfacultad();
                to.setIdeapfacultad(rs.getInt("idEapFacultad"));
                to.setIdfilialfacultad(rs.getInt("idFilialfacultad"));
                to.setIdfilialfacultad_name(rs.getString("facultadd"));
                to.setIdeap(rs.getInt("ideap"));
                to.setIdeap_name(rs.getString("eapname"));
                to.setIdcoordinadoreap(rs.getInt("idcoordinadoreap"));
                to.setIdcoordinadoreap_name(rs.getString("name_cord_eap"));
                to.setIdcoordinadoreap_apellipaterno(rs.getString("apellipaterno_cord_eap"));
                to.setIdcoordinadoreap_apellimaterno(rs.getString("apellimaterno_cord_eap"));
                reporte.add(to);

            }
        } catch (Exception e) {
            System.out.println("Error en Reporte de datos." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

        }
          
          public int Registrar_EAP_Facultad_filial(Eapfacultad to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into eapfacultad(idfilialfacultad, ideap,idcoordinadoreap) "
                    + "values (?,?,?)");
            ps.setInt(1, to.getIdfilialfacultad());
            ps.setInt(2, to.getIdeap());
            ps.setInt(3, to.getIdcoordinadoreap());
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
        }
          
            public void eliminar_EAP_Facultad_Filial_ñññ(int idEapFacultad) {
            try {
            getConexionDb();
            ps = con.prepareStatement("DELETE FROM eapfacultad WHERE idEapFacultad=?");
            ps.setInt(1, idEapFacultad);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado ...aaaaaaaa");
            }else{
            
                System.out.println("erroooo edwnnn --------<");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
        }
            
           public void eliminar_EAP_Facultad_Filial______delete(int idEapFacultad) {
           try {
            getConexionDb();
            ps = con.prepareStatement("DELETE FROM eapfacultad WHERE idEapFacultad=? ");
            ps.setInt(1, idEapFacultad);
               System.out.println("---- id --- |||| --->"+idEapFacultad);
            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado --------------> 1222");
            }else{
            
                System.out.println("errro  al eliminar -- ");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
           
           public List Listar_Eap_Facultad_Perfil(String idfilial,String idFilialfacultad) { 
        List reporte = new ArrayList();
              Eapfacultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT ef.`idEapFacultad`,ef.`idfilialfacultad`,(f.`idFilial`)AS idfili,(fac.`nombre`)AS facultadd,ef.`ideap`,(e.`nombre`)AS eapname,ef.`idcoordinadoreap`,(per.`nombre`)AS name_cord_eap ,(per.`apellipaterno`)AS apellipaterno_cord_eap,(per.`apellimaterno`)AS apellimaterno_cord_eap FROM `eapfacultad` ef\n" +
"INNER JOIN `filialfacultad` ff ON ef.`idfilialfacultad`=ff.`idFilialfacultad`\n" +
"INNER JOIN `filial` f ON ff.idfilial=f.idfilial\n" +
"INNER JOIN facultad  fac ON ff.idfacultad=fac.idfacultad\n" +
"INNER JOIN `eap` e ON ef.ideap =e.ideap\n" +
"INNER JOIN `coordinadoreap` ce ON ef.idcoordinadoreap =ce.idcoordinadoreap\n" +
"INNER JOIN persona per ON ce.idpersona =per.idpersona\n" +
"WHERE ff.`idfilial`=? and ef.idfilialfacultad=?");
            ps.setString(1, idfilial);
            ps.setString(2, idFilialfacultad);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Eapfacultad();
                to.setIdeapfacultad(rs.getInt("idEapFacultad"));
                to.setIdfilialfacultad(rs.getInt("idFilialfacultad"));
                to.setIdfilialfacultad_name(rs.getString("facultadd"));
                to.setIdeap(rs.getInt("ideap"));
                to.setIdeap_name(rs.getString("eapname"));
                to.setIdcoordinadoreap(rs.getInt("idcoordinadoreap"));
                to.setIdcoordinadoreap_name(rs.getString("name_cord_eap"));
                to.setIdcoordinadoreap_apellipaterno(rs.getString("apellipaterno_cord_eap"));
                to.setIdcoordinadoreap_apellimaterno(rs.getString("apellimaterno_cord_eap"));
                reporte.add(to);

            }
        } catch (Exception e) {
            System.out.println("Error en Reporte de datos." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

        }
         
 
            public List Listar_FililFacultad(String idfilial) { 
        List reporte = new ArrayList();
              Filialfacultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM `filialfacultad` ff\n" +
"INNER JOIN `facultad` fa ON ff.idfacultad =fa.idfacultad\n" +
"WHERE ff.idfilial=?");
            ps.setString(1, idfilial);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Filialfacultad();
                to.setIdfilialfacultad(rs.getInt("idFilialfacultad"));
                to.setIdfilial(rs.getInt("idfilial"));
                to.setIdfacultad_nombre(rs.getString("nombre"));
                reporte.add(to);

            }
        } catch (Exception e) {
            System.out.println("Error en Reporte de datos." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

        }
            
            
            
        public List reporteFilialFacultad() {   //list:return del mismo tipo
        List reporte = new ArrayList();
        Filialfacultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM `filialfacultad` ff\n" +
"INNER JOIN `facultad` fa ON ff.idfacultad =fa.idfacultad GROUP BY fa.nombre");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Filialfacultad();
                to.setIdfilialfacultad(rs.getInt("idFilialfacultad"));
                to.setIdfilial(rs.getInt("idfilial"));
                to.setIdfacultad_nombre(rs.getString("nombre"));
                reporte.add(to);


                System.out.println("Reportado..!");

            }
        } catch (Exception e) {
            System.out.println("Error en Reporte alumno..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
        
        public List reporteFacultadEap() {   //list:return del mismo tipo
        List reporte = new ArrayList();
        Eapfacultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM eapfacultad ef\n" +
"INNER JOIN eap e ON ef.ideap =e.ideap\n" +
"GROUP BY e.nombre");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Eapfacultad();
                to.setIdeapfacultad(rs.getInt("idEapFacultad"));
                to.setIdeap(rs.getInt("ideap"));
                to.setIdeap_name(rs.getString("nombre"));
                reporte.add(to);


                System.out.println("Reportado..!");

            }
        } catch (Exception e) {
            System.out.println("Error en Reporte alumno..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
        
        
        public List ListarTemporadaActivaWeb() {
        List reporte = new ArrayList();
            Temporada to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM `temporada` WHERE estado=1");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Temporada();
                to.setIdtemporada(rs.getInt("idTemporada"));
                to.setInicio(rs.getString("inicio"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setFin(rs.getString("fin"));
                to.setEstado(rs.getString("estado"));
                reporte.add(to);
            }
        } catch (Exception e) {
            System.out.println("Error en" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
        
        
        public Filialfacultad getFilialFacultadId(int id) {   //list:return del mismo tipo
        Filialfacultad to = new Filialfacultad();
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM `filialfacultad` ff\n" +
"INNER JOIN `facultad` fa ON ff.idfacultad =fa.idfacultad and ff.idfilialfacultad=? GROUP BY fa.nombre");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Filialfacultad();
                to.setIdfilialfacultad(rs.getInt("idFilialfacultad"));
                to.setIdfilial(rs.getInt("idfilial"));
                to.setIdfacultad_nombre(rs.getString("nombre"));



                System.out.println("Reportado..!");

            }
        } catch (Exception e) {
            System.out.println("Error en Reporte alumno..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return to;

    }        
        
        
        
         
}
