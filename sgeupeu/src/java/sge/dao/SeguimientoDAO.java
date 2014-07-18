/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sge.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sge.connexion.DBConn;
import sge.modelo.Seguimiento;
import sge.modelo.TipoSeguimiento;

/**
 *
 * @author Intel
 */
public class SeguimientoDAO extends DBConn{
    
 public int insertarSeguimiento(Seguimiento to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("INSERT INTO seguimiento ( idAvance, id_ciclo, id_tipo_seguim, id_user_obs, fecha_obs, observacion, estado ) "
                    + " VALUES( ?, ?, ?, ?, (SELECT NOW()), ?, ?)");
            ps.setInt(1, to.getIdAvance());
            ps.setInt(2, to.getIdCiclo());
            ps.setInt(3, to.getIdTipoSeguim());
            ps.setInt(4, to.getIdUserObs());            
            ps.setString(5, to.getObservacion());
            ps.setInt(6, to.getEstado());
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {System.out.println(" --ERROR ! "+e.getMessage());}   
        return r;
    } 
 public int actulizarEstado(int idseguimiento, int estado) {
     int r = 0;   
     try {
            getConexionDb();
            ps = con.prepareStatement(" UPDATE seguimiento SET estado = ?  WHERE id_seguimiento = ? ");
            ps.setInt(1, estado);
            ps.setInt(2, idseguimiento);
            if (ps.executeUpdate() == 1) {
                 r = 1;
                System.out.println("Actualizado Estado!!!");
            }
        } catch (Exception e) {System.out.println(" --ERROR ! "+e.getMessage());}  
     return r;
 } 
 public void actulizarUserViewEstado(int iduserview, int idavance) {
        try {
            getConexionDb();
            ps = con.prepareStatement(" UPDATE seguimiento SET estado = 2 , id_user_view = ?, fecha_view = (SELECT NOW()) WHERE idavance = ? and  estado = 1 and id_tipo_seguim=( SELECT id_tipo_seguim FROM tipo_seguimiento WHERE etiqueta='SEV' ) ");
            ps.setInt(1, iduserview);
            ps.setInt(2, idavance);
            if (ps.executeUpdate() == 1) {
                System.out.println("Actualiza User View!!!");
            }
        } catch (Exception e) {System.out.println(" --ERROR ! "+e.getMessage());}   
 } 
 public void actulizarUserRespEstado(int iduserresp, int idavance) {
        try {
            getConexionDb();
            ps = con.prepareStatement(" UPDATE seguimiento SET estado = 0 , id_user_resp = ?,  fecha_resp = (SELECT NOW()) WHERE idavance = ? and  estado = 2 and id_tipo_seguim=( SELECT id_tipo_seguim FROM tipo_seguimiento WHERE etiqueta='SEV' ) ");
            ps.setInt(1, iduserresp);
            ps.setInt(2, idavance);
            if (ps.executeUpdate() == 1) {
                System.out.println("Actualiza User Resp!!!");
            }
        } catch (Exception e) {System.out.println(" --ERROR ! "+e.getMessage());}   
 } 
 
    public ArrayList reporteObservaciones(int idAvance){        
    String sql=" SELECT e.idevidencia, e.evidencia, (CASE WHEN LOWER(e.tipo) ='.doc' OR LOWER(e.tipo)='.docx' THEN 'doc' WHEN LOWER(e.tipo) ='.rar' OR LOWER(e.tipo)='.zip' THEN 'zip' WHEN LOWER(e.tipo) ='.pdf' THEN 'pdf' ELSE 'otro' END)  AS tipo ,e.url, e.fecha_reg, e.idusuario,(SELECT usuario FROM (SELECT idusuario, idpersona, CONCAT(nombre, ' ', apellipaterno, ' ', apellimaterno) AS usuario FROM usuario INNER JOIN persona USING(idpersona)) a WHERE a.idusuario=e.idusuario ) AS usuario, e.idavance "
            + " FROM evidencia e WHERE e.idavance=? ";
        ArrayList Lista = new ArrayList(); 
        Map userPriv;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idAvance);
            rs=ps.executeQuery();            
            while (rs.next()){           
                userPriv = new HashMap();
                userPriv.put("idevidencia", rs.getInt("idevidencia"));
                userPriv.put("evidencia", rs.getString("evidencia"));
                userPriv.put("tipo", rs.getString("tipo"));
                userPriv.put("url", rs.getString("url"));
                userPriv.put("fecha_reg", rs.getString("fecha_reg"));
                userPriv.put("idusuario", rs.getInt("idusuario"));
                userPriv.put("usuario", rs.getString("usuario"));
                userPriv.put("idavance", rs.getInt("idavance"));
                Lista.add(userPriv);
            } } catch (Exception e) {
            }
        finally{getCerrarConexion();}
        System.out.println(" Muetra los Archivos!!! ..>"+Lista.toArray().length);
    return Lista;
    }   
    public ArrayList reporteObservacionesModelo2(int idAvance){        
    String sql=" SELECT id_seguimiento, idavance, id_ciclo AS idciclo, id_user_obs ,id_tipo_seguim, fecha_obs, observacion, estado, fecha_view, fecha_resp, (SELECT usuario FROM (SELECT idpersona, idusuario, CONCAT(nombre,' ', apellipaterno, ' ', apellimaterno) AS usuario FROM persona INNER JOIN usuario USING(idpersona) ) a WHERE idusuario=s.id_user_obs) AS usuarioobs ,(SELECT usuario FROM (SELECT idpersona, idusuario, CONCAT(nombre,' ', apellipaterno, ' ', apellimaterno) AS usuario FROM persona INNER JOIN usuario USING(idpersona) ) a WHERE idusuario=s.id_user_view) AS usuarioview , (SELECT usuario FROM (SELECT idpersona, idusuario, CONCAT(nombre,' ', apellipaterno, ' ', apellimaterno) AS usuario FROM persona INNER JOIN usuario USING(idpersona) ) a WHERE idusuario=s.id_user_resp) AS usuarioresp ,(SELECT etiqueta FROM tipo_seguimiento WHERE id_tipo_seguim=s.id_tipo_seguim) AS obstipo FROM seguimiento s WHERE s.idavance=? AND id_tipo_seguim IN (SELECT id_tipo_seguim  FROM tipo_seguimiento WHERE etiqueta='SEO' OR etiqueta='SEV') ";
        ArrayList Lista = new ArrayList(); 
        Map userPriv;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idAvance);
            rs=ps.executeQuery();            
            while (rs.next()){           
                userPriv = new HashMap();
                userPriv.put("idseguimiento", rs.getInt("id_seguimiento"));
                userPriv.put("idavance", rs.getInt("idavance"));
                userPriv.put("idciclo", rs.getInt("idciclo"));
                userPriv.put("idtiposeguim", rs.getInt("id_tipo_seguim"));
                userPriv.put("iduserobs", rs.getInt("id_user_obs"));
                userPriv.put("fechaobs", rs.getString("fecha_obs"));
                userPriv.put("observacion", rs.getString("observacion"));
                userPriv.put("estado", rs.getInt("estado"));
                userPriv.put("fechaview", rs.getString("fecha_view"));
                userPriv.put("fecharesp", rs.getString("fecha_resp"));
                userPriv.put("usuarioobs", rs.getString("usuarioobs"));
                userPriv.put("usuarioview", rs.getString("usuarioview"));
                userPriv.put("usuarioresp", rs.getString("usuarioresp"));
                userPriv.put("obstipo", rs.getString("obstipo"));
                Lista.add(userPriv);
            } } catch (Exception e) {
            }
        finally{getCerrarConexion();}
        System.out.println(" Muetra las observaciones!!! ..>"+Lista.toArray().length);
    return Lista;
    }   
 
    public List<TipoSeguimiento> listaTipoSeguimientoEstado(){
    String sql=" SELECT id_tipo_seguim, etiqueta, nombre, estado FROM tipo_seguimiento WHERE estado=1 ";
    List<TipoSeguimiento> Lista = new ArrayList<TipoSeguimiento>(); 
      TipoSeguimiento toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
        while (rs.next()){            
           toto  = new TipoSeguimiento();
           toto.setEtiqueta(rs.getString("etiqueta"));
           toto.setNombre(rs.getString("nombre"));
           toto.setEstado(rs.getInt("estado"));
           toto.setIdTipoSeguim(rs.getInt("id_tipo_seguim"));
           Lista.add(toto);
        }} catch (Exception e) {}
        finally{getCerrarConexion();} System.out.println(" Lista Tipo Seguimiento ..>"+Lista.toArray().length);
    return Lista;
    }
    
    
    
}
