/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sge.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import sge.connexion.DBConn;

/**
 *
 * @author Intel
 */
public class ReportesDAO extends DBConn{

    public ArrayList cabeceraPOA(int ideapfacultad){
    String sql=" SELECT i.razonsocial, i.rector, fi.direccion,fi.celular, fi.idfilial, fi.categoria, fi.rector AS rectorfilial, "
            + " f.nombre AS nombrefacultad, f.idfacultad,  ff.idfilialfacultad ,e.nombre AS nombreeap, e.idtipoarea, "
            + " (CASE WHEN e.idtipoarea=1 THEN 'Pregrado' WHEN e.idtipoarea=2 THEN 'Postgrado' WHEN e.idtipoarea=3 THEN 'Areas de Apoyo' ELSE 'Otros' END) AS nombretipoarea, e.ideap, ef.ideapfacultad, ef.idcoordinadoreap, "
            + " (SELECT CONCAT(p.nombre,' ',p.apellipaterno,' ',p.apellimaterno) AS coordinador "
            + " FROM coordinadoreap ce, persona p "
            + " WHERE p.idpersona=ce.idpersona AND  ce.idcoordinadoreap=ef.idcoordinadoreap) AS coordinador, (SELECT  pp.periodo FROM periodo pp WHERE pp.idperiodo=(SELECT cea.idperiodo FROM  coordinadoreap  cea WHERE ef.idcoordinadoreap=cea.idcoordinadoreap )) AS periodo "
            + " FROM eapfacultad ef, eap e, filialfacultad ff, facultad f, filial fi, institucion i "
            + " WHERE ef.ideap=e.ideap  AND ff.idfilialfacultad=ef.idfilialfacultad  AND f.idfacultad=ff.idfacultad  "
            + " AND fi.idfilial=ff.idfilial AND fi.idinstitucion=i.idinstitucion "
            + " AND ef.ideapfacultad=?  ";
        ArrayList Lista = new ArrayList(); 
        Map userPriv;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, ideapfacultad);
            rs=ps.executeQuery();            
            while (rs.next()){            
                userPriv = new HashMap();
                userPriv.put("razonsocial", rs.getString("razonsocial"));
                userPriv.put("rector", rs.getString("rector"));
                userPriv.put("direccion", rs.getString("direccion"));
                userPriv.put("celular", rs.getString("celular"));
                userPriv.put("idfilial", rs.getInt("idfilial"));
                userPriv.put("categoria", rs.getString("categoria"));
                userPriv.put("rectorfilial", rs.getString("rectorfilial"));
                userPriv.put("nombrefacultad", rs.getString("nombrefacultad"));
                userPriv.put("idfacultad", rs.getInt("idfacultad"));
                userPriv.put("idfilialfacultad", rs.getInt("idfilialfacultad"));
                userPriv.put("nombreeap", rs.getString("nombreeap"));
                userPriv.put("idtipoarea", rs.getInt("idtipoarea"));
                userPriv.put("nombretipoarea", rs.getString("nombretipoarea"));
                userPriv.put("ideap", rs.getInt("ideap"));
                userPriv.put("ideapfacultad", rs.getInt("ideapfacultad"));
                userPriv.put("idcoordinadoreap", rs.getInt("idcoordinadoreap"));
                userPriv.put("coordinador", rs.getString("coordinador"));
                userPriv.put("periodo", rs.getString("periodo"));
                Lista.add(userPriv);
            } } catch (Exception e) {
            }
        finally{getCerrarConexion();}
        System.out.println(" Muetra la cabecera!!! ..>"+Lista.toArray().length);
    return Lista;
    }
    
    
    
    
    public ArrayList ejesSeleccionadosPOA(int ideapfacultad, int ideje){
        System.out.println("verrr>"+ideje);
        String qry="";
       
        if(ideje!=0){
          qry= " and r.idejeestrategico='"+ideje+"' ";
        }else{
            qry="";
        }
        
    String sql=" SELECT * FROM (SELECT  eje.idejeestrategico, eje.nombre AS nombreejeestrategico, eje.codigo AS  ejeestrategicocodigo, m.ideapfacultad, m.idperiodo FROM estrategia e,  perspectiva p,  ejeestrategia ej, estrategiaindicador ei, indicador i, ejeestrategico eje, meta m WHERE p.idperspectiva=e.idperspectiva  AND ej.idestrategia=e.idestrategia AND ei.idejeestrategia=ej.idejeestrategia AND ei.idindicador=i.idindicador AND eje.idejeestrategico=i.idejeestrategico AND m.idestrategiaIndicador=ei.idestrategiaIndicador ) AS r "
            + "  WHERE r.ideapfacultad='"+ideapfacultad+"' "+qry
            + "  GROUP BY r.idperiodo, r.ideapfacultad, r.ejeestrategicocodigo,  r.nombreejeestrategico "
            + "  ORDER BY r.ejeestrategicocodigo ASC ";
        ArrayList Lista = new ArrayList(); 
        Map userPriv;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql); 
            rs=ps.executeQuery();            
            while (rs.next()){            
                userPriv = new HashMap();
                userPriv.put("idejeestrategico", rs.getInt("idejeestrategico"));
                userPriv.put("nombreejeestrategico", rs.getString("nombreejeestrategico"));
                userPriv.put("ejeestrategicocodigo", rs.getString("ejeestrategicocodigo"));
                userPriv.put("ideapfacultad", rs.getInt("ideapfacultad"));
                userPriv.put("idperiodo", rs.getInt("idperiodo"));
                Lista.add(userPriv);
            } } catch (Exception e) {
            }
        finally{getCerrarConexion();}
        System.out.println(" Muetra la ejes!!! ..>"+Lista.toArray().length);
    return Lista;
    }    
    public ArrayList reporEapFacultadFilial(int idfilial){        
    String sql=" SELECT ef.ideapfacultad, e.idtipoarea, e.codigo, (CASE WHEN e.idtipoarea=1 THEN  CONCAT(e.nombre, \" (Pregrado)\") WHEN e.idtipoarea=2 THEN  CONCAT(e.nombre, \" (Postgrado)\") ELSE CONCAT(e.nombre, \" (A.Apoyo)\") END) AS nombreeap FROM filial f, filialfacultad ff, eapfacultad ef, eap e "
            + " WHERE f.idfilial=ff.idfilial  "
            + " AND ff.idfilialfacultad=ef.idfilialfacultad AND e.ideap=ef.ideap AND f.idfilial='"+idfilial+"' ";
        ArrayList Lista = new ArrayList(); 
        Map userPriv;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql); 
            rs=ps.executeQuery();            
            while (rs.next()){            
                userPriv = new HashMap();
                userPriv.put("ideapfacultad", rs.getInt("ideapfacultad"));
                userPriv.put("idtipoarea", rs.getInt("idtipoarea"));
                userPriv.put("codigo", rs.getString("codigo"));
                userPriv.put("nombreeap", rs.getString("nombreeap"));
                Lista.add(userPriv);
            } } catch (Exception e) {
            }
        finally{getCerrarConexion();}
        System.out.println(" Muetra las EAPs!!! ..>"+Lista.toArray().length);
    return Lista;
    }    
    public ArrayList reporEapFacultadFilialEap(int idfilial, int idfilialfacultad){        
    String sql=" SELECT ef.ideapfacultad, e.idtipoarea, e.codigo, (CASE WHEN e.idtipoarea=1 THEN  CONCAT(e.nombre, ' (Pregrado)') WHEN e.idtipoarea=2 THEN  CONCAT(e.nombre, ' (Postgrado)') ELSE CONCAT(e.nombre, ' (A.Apoyo)') END) AS nombreeap FROM filial f, filialfacultad ff, eapfacultad ef, eap e "
            + " WHERE f.idfilial=ff.idfilial  "
            + " AND ff.idfilialfacultad=ef.idfilialfacultad AND e.ideap=ef.ideap AND f.idfilial=? and ff.idfilialfacultad=? ";
        ArrayList Lista = new ArrayList(); 
        Map userPriv;
        try {
            System.out.println("sillaga::::"+idfilialfacultad+" dddd:::"+idfilial);
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idfilial);
            ps.setInt(2, idfilialfacultad);
            rs=ps.executeQuery();            
            while (rs.next()){            
                userPriv = new HashMap();
                userPriv.put("ideapfacultad", rs.getInt("ideapfacultad"));
                userPriv.put("idtipoarea", rs.getInt("idtipoarea"));
                userPriv.put("codigo", rs.getString("codigo"));
                userPriv.put("nombreeap", rs.getString("nombreeap"));
                Lista.add(userPriv);
            } } catch (Exception e) {
            }
        finally{getCerrarConexion();}
        System.out.println(" Muetra las EAPs!!! ..>"+Lista.toArray().length);
    return Lista;
    }    
    
    
    
    
    
    
    public ArrayList objetivosEstrategicosPOA(int ideapfacultad, int ideje){
        
    String sql=" SELECT * FROM (SELECT p.nombre AS perspectiva,eje.idejeestrategico, eje.nombre AS nombreejeestrategico, eje.codigo AS  ejeestrategicocodigo, e.codigo AS estrategiacodigo, e.nombre AS estrategia, e.idestrategia, ej.idejeestrategia, i.codigo AS indicadorcodigo, i.nombre AS indicador, i.metaideal, i.idtipometa, (CASE WHEN i.idtipometa=1 THEN '#' ELSE '%' END) AS tipometanombre, i.idfilial, i.idtipoarea, m.idmeta, m.meta, (SELECT p.periodo FROM periodo p WHERE p.idperiodo=m.idperiodo) AS periodo,(CASE WHEN i.idtipometa=1 THEN CONCAT('# ',m.meta) ELSE CONCAT(m.meta,' %') END) AS metatexto, m.ideapfacultad, m.idperiodo, ei.idestrategiaindicador FROM estrategia e,  perspectiva p, ejeestrategia ej, estrategiaindicador ei, indicador i, ejeestrategico eje, meta m WHERE p.idperspectiva=e.idperspectiva  AND ej.idestrategia=e.idestrategia AND ei.idejeestrategia=ej.idejeestrategia AND ei.idindicador=i.idindicador AND eje.idejeestrategico=i.idejeestrategico AND m.idestrategiaIndicador=ei.idestrategiaIndicador) AS r "
            + "  WHERE r.ideapfacultad=? AND CAST(meta AS SIGNED)<>0 AND r.ejeestrategicocodigo=? ORDER BY idestrategiaindicador ASC  ";
        ArrayList Lista = new ArrayList(); 
        Map userPriv;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql); 
            ps.setInt(1, ideapfacultad);
            ps.setInt(2, ideje);
            rs=ps.executeQuery();            
            while (rs.next()){            
                userPriv = new HashMap();
                userPriv.put("perspectiva", rs.getString("perspectiva"));
                userPriv.put("idejeestrategico", rs.getInt("idejeestrategico"));
                userPriv.put("nombreejeestrategico", rs.getString("nombreejeestrategico"));
                userPriv.put("ejeestrategicocodigo", rs.getString("ejeestrategicocodigo"));
                userPriv.put("estrategiacodigo", rs.getString("estrategiacodigo"));
                userPriv.put("estrategia", rs.getString("estrategia"));
                userPriv.put("idestrategia", rs.getInt("idestrategia"));
                userPriv.put("idejeestrategia", rs.getInt("idejeestrategia"));
                userPriv.put("indicadorcodigo", rs.getString("indicadorcodigo"));
                userPriv.put("indicador", rs.getString("indicador"));
                userPriv.put("idtipometa", rs.getInt("idtipometa"));
                userPriv.put("tipometanombre", rs.getString("tipometanombre"));
                userPriv.put("idfilial", rs.getInt("idfilial"));
                userPriv.put("idtipoarea", rs.getInt("idtipoarea"));
                userPriv.put("idmeta", rs.getInt("idmeta"));
                userPriv.put("meta", rs.getString("meta"));
                userPriv.put("metatexto", rs.getString("metatexto"));
                userPriv.put("ideapfacultad", rs.getInt("ideapfacultad"));
                userPriv.put("idperiodo", rs.getInt("idperiodo"));
                userPriv.put("periodo", rs.getInt("periodo"));
                userPriv.put("idestrategiaindicador", rs.getInt("idestrategiaindicador"));
                Lista.add(userPriv);
            } } catch (Exception e) {
            }
        finally{getCerrarConexion();}
        System.out.println(" Muetra la ejes!!! ..>"+Lista.toArray().length);
    return Lista;
    }    
    
    
    
    public ArrayList actividadesPOA(int idmeta, int mes1, int mes2){
        
    String sql=" SELECT * FROM (SELECT validar_mes(?,?,a.idactividad) AS validar,  a.nro, a.accion, a.cantidad, a.responsable, a.idmeta,  a.enero, a.febrero, a.marzo, a.abril, a.mayo, a.junio, a.julio, a.agosto, a.setiembre, a.octubre, a.noviembre, a.diciembre, (CASE WHEN a.presupuesto IS NULL THEN '0' ELSE a.presupuesto END) AS presupuesto "
            + " FROM actividad a, meta m WHERE a.idmeta=m.idmeta AND a.idmeta=? )  AS r WHERE validar=1  ";
        ArrayList Lista = new ArrayList(); 
        Map userPriv;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql); 
            ps.setInt(1, mes1);           
            ps.setInt(2, mes2);           
            ps.setInt(3, idmeta);           
            rs=ps.executeQuery();            
            while (rs.next()){          
                userPriv = new HashMap();
                userPriv.put("validar", rs.getInt("validar"));
                userPriv.put("nro", rs.getInt("nro"));
                userPriv.put("accion", rs.getString("accion"));
                userPriv.put("cantidad", rs.getInt("cantidad"));
                userPriv.put("responsable", rs.getString("responsable"));
                userPriv.put("idmeta", rs.getInt("idmeta"));
                userPriv.put("enero", rs.getString("enero"));
                userPriv.put("febrero", rs.getString("febrero"));
                userPriv.put("marzo", rs.getString("marzo"));
                userPriv.put("abril", rs.getString("abril"));
                userPriv.put("mayo", rs.getString("mayo"));
                userPriv.put("junio", rs.getString("junio"));
                userPriv.put("julio", rs.getString("julio"));
                userPriv.put("agosto", rs.getString("agosto"));
                userPriv.put("setiembre", rs.getString("setiembre"));
                userPriv.put("octubre", rs.getString("octubre"));
                userPriv.put("noviembre", rs.getString("noviembre"));
                userPriv.put("diciembre", rs.getString("diciembre"));
                
                userPriv.put("presupuesto", rs.getDouble("presupuesto"));
                
                Lista.add(userPriv);
            } } catch (Exception e) {
            }
        finally{getCerrarConexion();}
        System.out.println(" Muetra las actividades!!! ..>"+Lista.toArray().length);
    return Lista;
    }    
}
