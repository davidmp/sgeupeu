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
import sge.modelo.Filial;
import sge.modelo.Tipoarea;

/**
 *
 * @author Intel
 */
public class ReportesDAO extends DBConn{

    public ArrayList cabeceraPOA(int ideapfacultad){
    String sql=" SELECT i.razonsocial, i.rector, fi.direccion,fi.celular, fi.idfilial, fi.categoria, fi.rector AS rectorfilial, "
            + " f.nombre AS nombrefacultad, f.idfacultad,  ff.idfilialfacultad ,(CASE WHEN e.ideap=7 AND ef.ideapfacultad=36 THEN 'Administración ' ELSE e.nombre  END) AS nombreeap, e.idtipoarea, "
            + " (CASE WHEN e.idtipoarea=1 THEN 'Pregrado' WHEN e.idtipoarea=2 THEN 'Posgrado' WHEN e.idtipoarea=3 THEN 'Areas de Apoyo' ELSE 'Otros' END) AS nombretipoarea, e.ideap, ef.ideapfacultad, ef.idcoordinadoreap, "
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
        
    String sql=" SELECT * FROM (SELECT  eje.idejeestrategico, eje.nombre AS nombreejeestrategico, eje.objetivoestrategico AS objetivogeneral , eje.codigo AS  ejeestrategicocodigo, m.ideapfacultad, m.idperiodo FROM estrategia e,  perspectiva p,  ejeestrategia ej, estrategiaindicador ei, indicador i, ejeestrategico eje, meta m WHERE p.idperspectiva=e.idperspectiva  AND ej.idestrategia=e.idestrategia AND ei.idejeestrategia=ej.idejeestrategia AND ei.idindicador=i.idindicador AND eje.idejeestrategico=i.idejeestrategico AND m.idestrategiaIndicador=ei.idestrategiaIndicador ) AS r "
            + "  WHERE r.ideapfacultad='"+ideapfacultad+"' "+qry
            + "  GROUP BY r.idperiodo, r.ideapfacultad, r.ejeestrategicocodigo,  r.nombreejeestrategico,r.objetivogeneral, r.objetivogeneral "
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
                userPriv.put("objetivogeneral", rs.getString("objetivogeneral"));
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
    String sql=" SELECT ef.ideapfacultad, e.idtipoarea, e.codigo, (CASE WHEN e.idtipoarea=1 THEN  CONCAT(e.nombre, \" (Pregrado)\") WHEN e.idtipoarea=2 THEN  CONCAT(e.nombre, \" (Posgrado)\") ELSE CONCAT(e.nombre, \" (A.Apoyo)\") END) AS nombreeap FROM filial f, filialfacultad ff, eapfacultad ef, eap e "
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
    public ArrayList reporEapFacultadFilialAudit(int idfilial){        
    String sql=" SELECT ef.ideapfacultad, e.idtipoarea, e.codigo, (CASE WHEN e.idtipoarea=1 THEN  CONCAT(e.nombre, ' (Pregrado)') WHEN e.idtipoarea=2 THEN  CONCAT(e.nombre, ' (Posgrado)') ELSE CONCAT(e.nombre, ' (A.Apoyo)') END) AS nombreeap FROM filial f, filialfacultad ff, eapfacultad ef, eap e "
            + " WHERE f.idfilial=ff.idfilial  AND ff.idfilialfacultad=ef.idfilialfacultad AND e.ideap=ef.ideap AND e.idtipoarea=1 AND f.idfilial='"+idfilial+"' ";
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
    String sql=" SELECT ef.ideapfacultad, e.idtipoarea, e.codigo, (CASE WHEN e.idtipoarea=1 THEN  CONCAT(e.nombre, ' (Pregrado)') WHEN e.idtipoarea=2 THEN  CONCAT(e.nombre, ' (Posgrado)') ELSE CONCAT(e.nombre, ' (A.Apoyo)') END) AS nombreeap FROM filial f, filialfacultad ff, eapfacultad ef, eap e "
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
    
    
    public ArrayList actividadesPOAVencidos(int idmeta, int mes1, int mes2){
        
    String sql=" SELECT * FROM (SELECT validar_vencidos(?,?,a.idactividad) AS validar,  a.nro, a.accion, a.cantidad, a.responsable, a.idmeta,  a.enero, a.febrero, a.marzo, a.abril, a.mayo, a.junio, a.julio, a.agosto, a.setiembre, a.octubre, a.noviembre, a.diciembre, (CASE WHEN a.presupuesto IS NULL THEN '0' ELSE a.presupuesto END) AS presupuesto "
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
    
    public ArrayList evaluacionPlanEstrategico(int ideapfacultad, int idperiodo, int idtipoarea, int idfilial, int ejearea){
        String subqry="";
        String subqry2="";
        String subqry4="";
        if(idtipoarea==3){ subqry=" AND u.idtemporadaejeestrategico='"+ejearea+"' "; subqry2=" AND c.idejeestrategico='"+ejearea+"' "; }else{subqry=""; subqry2="";}
        if(ejearea!=0) {        
        subqry4=" WHERE idtemporadaejeestrategico='"+ejearea+"' ";
        }else{subqry4="";}
        
    String sql=" SELECT m.*,(CASE WHEN idmeta IS NULL THEN 4 WHEN idmeta IS NOT NULL AND avancereal =0 AND instrumento='Creciente' THEN 0 WHEN idmeta IS NOT NULL AND avancereal >0 AND avancereal<1 AND instrumento='Creciente' THEN 1 WHEN idmeta IS NOT NULL AND avancereal =1 AND instrumento='Creciente' THEN 2 WHEN idmeta IS NOT NULL AND avancereal >1 AND instrumento='Creciente' THEN 3  WHEN idmeta IS NOT NULL AND avancereal >=0 AND avancereal<1 AND instrumento='Decreciente' THEN 3  WHEN idmeta IS NOT NULL AND avancereal =1 AND instrumento='Decreciente' THEN 2 WHEN idmeta IS NOT NULL AND avancereal >1 AND instrumento='Decreciente' THEN 0  ELSE 0 END) AS tipoavance ,(SELECT cantidadeje FROM (SELECT idEjeEstrategico, COUNT(*) cantidadeje FROM (SELECT * FROM (SELECT * FROM estrategiaindicador INNER JOIN indicador USING(idindicador, instrumento)) b WHERE idfilial='"+idfilial+"' ) a GROUP BY idEjeEstrategico ) a WHERE a.idejeestrategico=m.idejeestrategico) AS cantidadeje , (SELECT cantidadperpectiva FROM (SELECT idEjeEstrategico, idperspectiva, COUNT(*) cantidadperpectiva FROM (SELECT * FROM (SELECT * FROM (SELECT * FROM estrategiaindicador INNER JOIN indicador USING(idindicador, instrumento) ) a INNER JOIN (SELECT idEjeEstrategia, idEstrategia, idperspectiva FROM ejeestrategia INNER JOIN estrategia USING(idEstrategia)) b USING(idEjeEstrategia) ) b WHERE idfilial='"+idfilial+"' ) a GROUP BY idEjeEstrategico, idperspectiva ) a WHERE a.idejeestrategico=m.idejeestrategico AND a.idperspectiva=m.idperspectiva) AS cantidadperpectiva ,(SELECT cantidadestrategia FROM (SELECT idEjeEstrategico, idperspectiva, idestrategia, COUNT(*) cantidadestrategia FROM (SELECT * FROM (SELECT * FROM (SELECT * FROM estrategiaindicador INNER JOIN indicador USING(idindicador, instrumento) ) a INNER JOIN (SELECT idEjeEstrategia, idEstrategia, idperspectiva FROM ejeestrategia INNER JOIN estrategia USING(idEstrategia)) b USING(idEjeEstrategia) ) b WHERE idfilial='"+idfilial+"' ) a GROUP BY idEjeEstrategico, idperspectiva, idestrategia ) a WHERE a.idejeestrategico=m.idejeestrategico AND a.idperspectiva=m.idperspectiva AND a.idestrategia=m.idestrategia ) AS cantidadestrategia, IFNULL((SELECT MAX(condicion) AS obs FROM (SELECT idavance,condicion FROM ( SELECT s.idavance, (CASE WHEN s.estado=1 AND id_tipo_seguim=(SELECT id_tipo_seguim  FROM tipo_seguimiento WHERE etiqueta='SEO') THEN 3 WHEN s.estado=1 AND id_tipo_seguim=(SELECT id_tipo_seguim  FROM tipo_seguimiento WHERE etiqueta='SEV') THEN 1 WHEN s.estado=2 THEN 2 ELSE 0  END) AS condicion FROM seguimiento s WHERE s.id_tipo_seguim IN (SELECT id_tipo_seguim  FROM tipo_seguimiento WHERE etiqueta='SEO' OR etiqueta='SEV') ) a GROUP BY idavance, condicion ) b WHERE b.idavance=m.idavance ), 0) AS obs "
            + "  FROM ( SELECT d.*, (CASE WHEN d.idmeta IS NOT NULL AND d.idavance IS NOT NULL  THEN (SELECT COUNT(*) AS cantidad FROM evidencia e WHERE e.idavance=d.idavance) ELSE 0 END) AS archivo, (CASE WHEN d.idmeta IS NOT NULL AND d.idavance IS NOT NULL  THEN (ROUND((CAST(avance AS UNSIGNED))/(CAST(meta AS UNSIGNED)),2)) WHEN d.idmeta IS NOT NULL AND d.idavance IS NULL  THEN  (IFNULL(avance,0)/(CAST(meta AS UNSIGNED))) ELSE 0 END) AS avancereal "
            + "  FROM ( SELECT * FROM ( SELECT * FROM ( SELECT m.*, (SELECT t.nombre  FROM tipoarea t WHERE t.idtipoarea=m.idtipoarea) AS nombrearea, (SELECT p.nombre FROM perspectiva p WHERE p.idperspectiva=m.idperspectiva) AS nombreperspectiva "
            + "  FROM ( SELECT * FROM ( SELECT * FROM ( SELECT idestrategiaindicador, nro AS codigoin, idindicador, idejeestrategia  FROM estrategiaindicador ) AS a INNER JOIN (SELECT idejeestrategia, idestrategia, idtemporadaejeestrategico "
            + "  FROM ejeestrategia) AS b USING(idejeestrategia) ) AS a INNER JOIN (SELECT * FROM estrategia) AS b USING(idestrategia) ) AS m  ORDER BY m.idfilial ASC, m.idtipoarea ASC, m.idejeestrategia ASC,  m.idestrategiaindicador ASC, m.idperspectiva DESC ) u "
            + "  WHERE u.idfilial='"+idfilial+"' AND u.idtipoarea='"+idtipoarea+"'   "+subqry+"  ) a LEFT JOIN ( SELECT  idestrategiaindicador, idindicador, idtipoarea, nombre AS nombreindicador, estado, instrumento, metaideal, codigo AS codigoin, idtipometa, idejeestrategico,idmeta, meta, ideapfacultad, idusuario , idavance, avance, id_ciclo "
            + "  FROM ( SELECT * FROM ( SELECT * FROM (SELECT * FROM indicador a  INNER JOIN (SELECT idestrategiaindicador, nro, idindicador, idejeestrategia FROM estrategiaindicador) b USING(idindicador) ) c "
            + "  WHERE c.idfilial='"+idfilial+"' AND c.idtipoarea='"+idtipoarea+"'  "+subqry2+" ) a  LEFT JOIN (SELECT idmeta, meta, ideapfacultad, idusuario, idestrategiaindicador, idperiodo, idavancevalida  FROM meta "
            + "  WHERE ideapfacultad='"+ideapfacultad+"' AND idperiodo='"+idperiodo+"' ) b  USING(idestrategiaindicador) ) m LEFT JOIN ( SELECT idavance, meta AS avance, idmeta, idusuario AS usuariosubio, id_ciclo FROM avance ) a USING(idmeta) ) b USING(idestrategiaindicador,idindicador,codigoin, idtipoarea) ) d ) m  "+subqry4+" ";
        ArrayList Lista = new ArrayList(); 
        Map userPriv;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);           
            rs=ps.executeQuery();            
            while (rs.next()){          
                userPriv = new HashMap();
                userPriv.put("idestrategiaindicador", rs.getInt("idestrategiaindicador"));
                userPriv.put("codigoin", rs.getString("codigoin"));
                userPriv.put("idindicador", rs.getInt("idindicador"));
                userPriv.put("idtipoarea", rs.getInt("idtipoarea"));
                userPriv.put("idestrategia", rs.getInt("idestrategia"));
                userPriv.put("idejeestrategia", rs.getInt("idejeestrategia"));
                userPriv.put("idtemporadaejeestrategico", rs.getString("idtemporadaejeestrategico"));
                userPriv.put("nombre", rs.getString("nombre"));
                userPriv.put("descripcion", rs.getString("descripcion"));
                userPriv.put("codigo", rs.getString("codigo"));
                userPriv.put("idperspectiva", rs.getInt("idperspectiva"));
                userPriv.put("idfilial", rs.getInt("idfilial"));
                userPriv.put("nombrearea", rs.getString("nombrearea"));
                userPriv.put("nombreperspectiva", rs.getString("nombreperspectiva"));
                userPriv.put("nombreindicador", rs.getString("nombreindicador"));
                userPriv.put("estado", rs.getString("estado"));
                userPriv.put("instrumento", rs.getString("instrumento"));
                userPriv.put("metaideal", rs.getString("metaideal"));
                userPriv.put("idtipometa", rs.getInt("idtipometa"));
                userPriv.put("idejeestrategico", rs.getInt("idejeestrategico"));
                userPriv.put("idmeta", rs.getInt("idmeta"));
                userPriv.put("meta", rs.getInt("meta"));
                userPriv.put("ideapfacultad", rs.getInt("ideapfacultad"));
                userPriv.put("idusuario", rs.getInt("idusuario"));
                userPriv.put("idavance", rs.getInt("idavance"));
                userPriv.put("avance", rs.getDouble("avance"));
                userPriv.put("id_ciclo", rs.getInt("id_ciclo"));
                userPriv.put("archivo", rs.getInt("archivo"));
                userPriv.put("avancereal", rs.getDouble("avancereal"));
                userPriv.put("tipoavance", rs.getInt("tipoavance"));
                userPriv.put("cantidadeje", rs.getInt("cantidadeje"));
                userPriv.put("cantidadperspectiva", rs.getInt("cantidadperpectiva"));
                userPriv.put("cantidadestrategia", rs.getInt("cantidadestrategia"));
                userPriv.put("obs", rs.getInt("obs"));
                
                Lista.add(userPriv);
            } } catch (Exception e) {
            }
        finally{getCerrarConexion();}
        System.out.println(" Muetra las actividades!!! ..>"+Lista.toArray().length);
        System.out.println(" iNICIO:"+sql);
    return Lista;
    }  
    
    public ArrayList reporteArchivos(int idAvance){        
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
    
    public List<Tipoarea> areaPrePosgrado(){        
    String sql=" SELECT * FROM tipoarea WHERE idtipoarea IN (1) ";
        List<Tipoarea> Lista = new ArrayList<Tipoarea>(); 
        Map userPriv;
        Tipoarea toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();            
            while (rs.next()){           
                toto = new Tipoarea();
                toto.setNombre(rs.getString("nombre"));
                toto.setEtiqueta(rs.getString("etiqueta"));
                toto.setIdtipoarea(rs.getInt("idtipoarea"));
                Lista.add(toto);
            } } catch (Exception e) {
            }
        finally{getCerrarConexion();}
        System.out.println(" Muetra areas!!! ..>"+Lista.toArray().length);
    return Lista;
    }     
    public Filial filialPerido(int idfilial, int idperiodo){        
    String sql=" SELECT a.*,(SELECT p.periodo FROM periodo p WHERE p.idperiodo=a.idperiodo) AS periodo  FROM ( SELECT a.*, b.idperiodo FROM filial a INNER JOIN estadoperiodofilial b USING(idfilial)) AS a WHERE a.idfilial=? and a.idperiodo=? ";

        Filial toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idfilial);
            ps.setInt(2, idperiodo);
            rs=ps.executeQuery();            
            while (rs.next()){           
                toto = new Filial();
                toto.setDescripcion(rs.getString("descripcion"));
                toto.setCategoria(rs.getString("categoria"));
                toto.setRector(rs.getString("rector"));
                toto.setEstado(rs.getString("periodo"));
               
            } } catch (Exception e) {
            }
        finally{getCerrarConexion();}
        System.out.println(" Muetra areas!!! ..>"+toto);
    return toto;
    }   
    
    public ArrayList reporteCarrerasPregrado(int idfilial){        
    String sql=" SELECT * FROM (SELECT  b.ideapfacultad, b.nombre, b.ideap,a.idfilialfacultad, a.idfilial, a.direccion FROM (SELECT * FROM filial INNER JOIN filialfacultad USING(idfilial)) a INNER JOIN ( SELECT * FROM eapfacultad INNER JOIN (SELECT * FROM eap WHERE idtipoarea NOT IN (3,2) AND ideap NOT IN (48,49,17,16,14,13,9,8)) a USING (ideap)) b USING(idfilialfacultad) ) c "
            + " WHERE c.idfilial=? ";
        ArrayList Lista = new ArrayList(); 
        Map userPriv;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idfilial);
            rs=ps.executeQuery();            
            while (rs.next()){           
                userPriv = new HashMap();
                userPriv.put("ideapfacultad", rs.getInt("ideapfacultad"));
                userPriv.put("nombre", rs.getString("nombre"));
                userPriv.put("ideap", rs.getInt("ideap"));
                userPriv.put("idfilialfacultad", rs.getInt("idfilialfacultad"));
                userPriv.put("idfilial", rs.getInt("idfilial"));                
                userPriv.put("direccion", rs.getString("direccion"));                
                Lista.add(userPriv);
            } } catch (Exception e) {
            }
        finally{getCerrarConexion();}
        System.out.println(" Muetra las carreras!!! ..>"+Lista.toArray().length);
    return Lista;
    }     
}
