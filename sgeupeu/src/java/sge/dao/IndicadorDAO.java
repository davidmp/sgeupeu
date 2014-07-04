/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.dao;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import sge.connexion.DBConn;
import sge.modelo.*;


/**
 *
 * @author oscdmdz
 */
public class IndicadorDAO extends DBConn{
    
    public List<Periodometa> listaPeriodoMeta(){
    String sql="SELECT * FROM periodo WHERE estado='1'";
    List<Periodometa> Lista = new ArrayList<Periodometa>(); 
      Periodometa Toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Periodometa();
           Toto.setIdperiodometa(rs.getInt("idperiodo"));
           Toto.setIdtemporada(rs.getInt("idtemporada"));
           Toto.setPeriodo(rs.getString("periodo"));
           Toto.setEstado(rs.getInt("estado"));
           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
        System.out.println(" Lista periodo meta ..>"+Lista.toArray().length);
    return Lista;
    }  
 
               
    public Periodometa idPeriodoMeta(int idperiodometa){
    String sql="SELECT * FROM periodo WHERE estado='1' and idperiodo=?";
    Integer id=0;
         Periodometa Toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idperiodometa);
            rs=ps.executeQuery();
        while (rs.next()) 
        {
           Toto  = new Periodometa();
           Toto.setIdperiodometa(rs.getInt("idperiodo"));
           Toto.setIdtemporada(rs.getInt("idtemporada"));
           Toto.setPeriodo(rs.getString("periodo"));
           Toto.setEstado(rs.getInt("estado"));
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
    return Toto;
    }  
               
       

    
 
   public int totalavance(int idmeta){
    String sql="SELECT (CASE WHEN SUM(meta) IS NULL THEN 0 ELSE SUM(meta) END) AS valor FROM avance WHERE idmeta=?  ";
 int valor=0;
         try {
            getConexionDb1();
            ps1=con1.prepareStatement(sql);
            ps1.setInt(1, idmeta);
            rs1=ps1.executeQuery();
        while (rs1.next()) 
        {            
         valor=rs1.getInt("valor");
        }
        } catch (Exception e) {
        }
       finally{}
    return valor;
    }   
   
   public String nombreArchivoDenominacion(int idmeta){
    String sql="SELECT idmeta, UPPER(CONCAT(nperiodo,'-',eapn,'-', codigoi,'-N',cantidad)) AS nombrefile FROM (SELECT m.*, (IFNULL(m.nrofile,0)+1) AS cantidad FROM (SELECT m.idmeta, ( SELECT nrofile FROM (SELECT idmeta, idavance, COUNT(*) nrofile FROM (SELECT a.idavance, a.idmeta, e.idevidencia FROM avance a INNER JOIN evidencia e USING (idavance)) a GROUP BY idmeta, idavance) a WHERE a.idmeta=m.idmeta) AS nrofile,m.ideapfacultad,(SELECT eapn FROM(SELECT ideapfacultad, CONCAT(codigof,'-', idtipoarea,'-',codigo) AS eapn FROM (SELECT * FROM (SELECT * FROM eapfacultad INNER JOIN eap USING(ideap)) a INNER JOIN (SELECT idfilial, codigo AS codigof, idfilialfacultad,idfacultad FROM filial INNER JOIN filialfacultad USING(idfilial) ) b USING (idfilialfacultad)) a ) b WHERE b.ideapfacultad=m.ideapfacultad) AS eapn, m.idestrategiaindicador, (SELECT codigoi FROM (SELECT a.idestrategiaindicador, CONCAT(b.descripcion,'-',a.codigoi) AS codigoi FROM (SELECT a.idestrategiaindicador,b.idindicador, b.codigo AS codigoi,b.idejeestrategico  FROM estrategiaindicador a INNER JOIN indicador b USING (idindicador)) a INNER JOIN (SELECT * FROM ejeestrategico) b USING(idejeestrategico)) a WHERE a.idestrategiaindicador=m.idestrategiaindicador) AS codigoi , m.idperiodo, (SELECT periodo FROM periodo p WHERE p.idperiodo=m.idperiodo) AS nperiodo "
            + " FROM meta m WHERE m.idmeta=? ) m) p  ";
        String valor="";
         try {
            getConexionDb1();
            ps1=con1.prepareStatement(sql);
            ps1.setInt(1, idmeta);
            rs1=ps1.executeQuery();
        while (rs1.next()) 
        {            
         valor=rs1.getString("nombrefile");
        }
        } catch (Exception e) {
        }
       finally{}
    return valor;
    }   
               
  public int semaforo(int idmeta){
    String sql="SELECT valor_indicador(?) AS valor; ";
    
 int valor=0;
         try {
            getConexionDb2();
            ps2=con2.prepareStatement(sql);
            ps2.setInt(1, idmeta);
            rs2=ps2.executeQuery();
        while (rs2.next()) 
        {            
         valor=rs2.getInt("valor");
        }
        } catch (Exception e) {
        }
       finally{}
    return valor;
    }
  
    public int estadoMeta(int idpm,int idfi,int idfa, int idea)
    {        
    String sql=" SELECT estado_meta(?,?,?,?) AS estado; ";
    int valor=0;
         try {
            getConexionDb3();
            ps3=con3.prepareStatement(sql);
            ps3.setInt(1, idpm);
            ps3.setInt(2, idfi);
            ps3.setInt(3, idfa);
            ps3.setInt(4, idea);
            rs3=ps3.executeQuery();
        while (rs3.next()) 
        {            
         valor=rs3.getInt("estado");       
        }
        } catch (Exception e) {
        }
       finally{}
         return valor; 
    } 
    
        public int estadoAvance(int idpm,int idfi,int idfa, int idea){
    String sql=" SELECT estado_avance(?,?,?,?) AS estado; ";
    int valor=0;
         try {
            getConexionDb4();
            ps4=con4.prepareStatement(sql);
            ps4.setInt(1, idpm);
            ps4.setInt(2, idfi);
            ps4.setInt(3, idfa);
            ps4.setInt(4, idea);
            rs4=ps4.executeQuery();
        while (rs4.next()) 
        {            
         valor=rs4.getInt("estado");       
        }
        } catch (Exception e) {
        }
       finally{}
         System.out.println("----> valor ---> "+valor);
         return valor; 
    } 
  
    public int progreso(int idmeta){
    String sql=" SELECT (CASE WHEN SUM(a.meta)=0 OR SUM(a.meta) IS NULL THEN '0' ELSE SUM(a.meta)  END)  AS valor FROM avance a "+
               " INNER JOIN meta m ON a.idmeta=m.idmeta AND m.idmeta=?";
    int valor=0;
         try {
            getConexionDb5();
            ps5=con5.prepareStatement(sql);
            ps5.setInt(1, idmeta);
            rs5=ps5.executeQuery();
        while (rs5.next()) 
        {            
         valor=rs5.getInt("valor");       
        }
        } catch (Exception e) {
        }
       finally{}
         return valor;
    
    } 
               
               
    public List<Indicador> listaIndicador(Periodometa id,Eapfacultad ef, int estadometa,int estadoavance, int idFilial ){
        
    String sql= "   SELECT ei.*,i.*, m.idmeta,m.idperiodo,m.evidencia,m.idavancevalida,m.tipo,m.url, concat(es.codigo,' ', es.nombre) AS estrategia, "+ 
   "   (CASE WHEN m.evidencia IS NULL OR m.evidencia=''  THEN 0 ELSE 1 END) AS valorevidencia, "+
   "   (CASE WHEN m.meta=0 OR m.meta IS NULL THEN '0' ELSE m.meta END) AS meta  "+
   "   FROM estrategiaindicador ei   "+
   "   LEFT JOIN meta m ON m.idestrategiaindicador=ei.idestrategiaindicador AND m.ideapfacultad="+ef.getIdeapfacultad()+" AND m.idperiodo="+id.getIdperiodometa()+" "+   
   "   INNER JOIN indicador i ON i.idindicador=ei.idindicador   "+   
   "   INNER JOIN ejeestrategia ee ON ee.idejeestrategia=ei.idejeestrategia "+
   "   INNER JOIN estrategia es ON ee.idestrategia=es.idestrategia "+    
   "   INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico "+   
   "   INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1'  AND i.idfilial="+idFilial+" ";      
       
        System.out.println("DMPPP "+sql);
      List<Indicador> Lista = new ArrayList<Indicador>(); 
      sge.modelo.Indicador Toto = null;

        try {
            getConexionDb();
            ps=con.prepareStatement(sql);

            rs=ps.executeQuery();
        while (rs.next()) 
        {
           Toto  = new sge.modelo.Indicador();       
           Toto.setIdindicador(rs.getInt("idindicador"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
           Toto.setEstado(rs.getInt("estado"));
           Toto.setInstrumento(rs.getString("instrumento"));
           Toto.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
           Toto.setNro(rs.getInt("idestrategiaindicador"));//numero
           Toto.setMeta(rs.getInt("meta"));
           Toto.setIdmeta(rs.getInt("idmeta"));
           Toto.setIdperiodometa(rs.getInt("idperiodo"));
           Toto.setEvidencia(rs.getString("evidencia"));
           Toto.setTipo(rs.getString("tipo"));
           Toto.setUrl(rs.getString("url"));
           Toto.setValorevidencia(rs.getInt("valorevidencia"));
           Toto.setEstrategia(rs.getString("estrategia"));
           Toto.setIdtipometa(rs.getInt("idtipometa"));
           
           Toto.setEstadoactualizar(1);                  
           Toto.setSemaforo(semaforo(rs.getInt("idmeta")));
           Toto.setProgreso(progreso(rs.getInt("idmeta")));
           Toto.setTotalavance(totalavance(rs.getInt("idmeta")));
           Toto.setIdavancevalida(rs.getInt("idavancevalida"));
           Toto.setEstadometa(estadometa);
           Toto.setEstadoavance(estadoavance);         
           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
    return Lista;
    }
    
    
              public void insertarMeta(Meta to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("INSERT INTO meta(meta,ideapfacultad,idusuario,idestrategiaindicador,fecha_rg, idperiodo) VALUES (?,?,?,?,(SELECT NOW()),?)");
            ps.setString(1, to.getMeta());
            ps.setInt(2, to.getIdeapfacultad());
            ps.setInt(3, to.getIdusuario());           
            ps.setInt(4, to.getIdestrategiaindicador());
            ps.setInt(5, to.getIdperiodometa());

 
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(" --ERROR ! "+e.getMessage());
        }   
    } 
              
       public void actualizarMeta(Meta  to) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update meta set meta=? , fecha_md=(SELECT NOW()) WHERE idmeta=?");
            ps.setString(1, to.getMeta());
            ps.setInt(2, to.getIdmeta());
            ps.executeUpdate();
            System.out.println("Actualizado");
        } catch (Exception e) {
        } finally {
           getCerrarConexion();
        }
    }
       
       
       public void insertarEvidenciaMeta(String evidencia,String tipo,String url,int idmeta) {
        try {

            getConexionDb();
            ps = con.prepareStatement("update meta set evidencia=?, tipo=?, url=? WHERE idmeta=?");
            ps.setString(1, evidencia);
            ps.setString(2, tipo);
            ps.setString(3, url);
            ps.setInt(4, idmeta);
            ps.executeUpdate();
            System.out.println("Actualizado");
        } catch (Exception e) {
        } finally {
           getCerrarConexion();
        }
    } 
              
    public List<Ejeestrategico> listaEje(int idTipoarea){
    String subQry;
        if(idTipoarea==3){
        subQry=" AND e.idtipoarea='3' ";
        }else{
        subQry=" AND e.idtipoarea!='3' ";
        }        
    String sql="SELECT e.idejeestrategico, concat(e.codigo,' ',e.nombre) as nombre ,e.descripcion,e.estado,e.objetivoestrategico,e.mapaestrategico, te.idtemporadaejeestrategico, \n" +
        " te.nro, (SELECT ta.nombre  FROM tipoarea ta WHERE ta.idtipoarea=e.idtipoarea) AS nombrearea FROM ejeestrategico e INNER JOIN temporadaejeestrategico te ON e.idejeestrategico=te.idejeestrategico\n" +
        " INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1' "+subQry+"  ORDER BY e.idtipoarea ASC, e.codigo ASC, te.nro ASC ";

    List<Ejeestrategico> Lista = new ArrayList<Ejeestrategico>(); 
      Ejeestrategico Toto = null;
    int i=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Ejeestrategico();
           
           Toto.setIdejeestrategico(rs.getInt("idejeestrategico"));
           Toto.setNombre(rs.getString("nombre")+" ("+rs.getString("nombrearea")+")");
           Toto.setDescripcion(rs.getString("descripcion"));
           Toto.setEstado(rs.getInt("estado"));
           Toto.setMapaestrategico(rs.getString("mapaestrategico"));
           Toto.setObjetivoestrategico(rs.getString("objetivoestrategico"));
           Toto.setIdtemporadaejeestrategico(rs.getInt("idtemporadaejeestrategico"));
           Toto.setCodigo(rs.getString("nro"));

           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
    return Lista;
    }
    
    public List<Ejeestrategico> listaEjeIndividual(int ideje){
    String sql="SELECT e.idejeestrategico, concat(e.codigo,' - ',e.nombre) as nombre ,e.descripcion,e.estado,e.objetivoestrategico,e.mapaestrategico, te.idtemporadaejeestrategico, " +
        " te.nro FROM ejeestrategico e INNER JOIN temporadaejeestrategico te ON e.idejeestrategico=te.idejeestrategico " +
        " INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND (t.estado='1' AND e.idejeestrategico="+ideje+" )  ORDER BY te.nro ";

    List<Ejeestrategico> Lista = new ArrayList<Ejeestrategico>(); 
      Ejeestrategico Toto = null;
    int i=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Ejeestrategico();
           
           Toto.setIdejeestrategico(rs.getInt("idejeestrategico"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
           Toto.setEstado(rs.getInt("estado"));
           Toto.setMapaestrategico(rs.getString("mapaestrategico"));
           Toto.setObjetivoestrategico(rs.getString("objetivoestrategico"));
           Toto.setIdtemporadaejeestrategico(rs.getInt("idtemporadaejeestrategico"));
           Toto.setCodigo(rs.getString("nro"));

           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
    return Lista;
    }
    public List<Ejeestrategico> listaEjeIndividualAreas(int idtipoarea, int idcodigo){
    String sql="SELECT e.idejeestrategico, concat(e.codigo,' - ',e.nombre) as nombre ,e.descripcion,e.estado,e.objetivoestrategico,e.mapaestrategico, te.idtemporadaejeestrategico, " +
        " te.nro FROM ejeestrategico e INNER JOIN temporadaejeestrategico te ON e.idejeestrategico=te.idejeestrategico " +
        " INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND (t.estado='1' AND e.codigo="+idcodigo+"  and e.idtipoarea="+idtipoarea+" )  ORDER BY te.nro ";

    List<Ejeestrategico> Lista = new ArrayList<Ejeestrategico>(); 
      Ejeestrategico Toto = null;
    int i=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Ejeestrategico();
           
           Toto.setIdejeestrategico(rs.getInt("idejeestrategico"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
           Toto.setEstado(rs.getInt("estado"));
           Toto.setMapaestrategico(rs.getString("mapaestrategico"));
           Toto.setObjetivoestrategico(rs.getString("objetivoestrategico"));
           Toto.setIdtemporadaejeestrategico(rs.getInt("idtemporadaejeestrategico"));
           Toto.setCodigo(rs.getString("nro"));

           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
    return Lista;
    }
    
    public List<Ejeestrategico> listaEjeIndividualAreasAudit( int idusuario){
    String sql=" SELECT e.idejeestrategico, CONCAT(e.codigo,' - ',e.nombre) AS nombre ,e.descripcion,e.estado,e.objetivoestrategico,e.mapaestrategico, te.idtemporadaejeestrategico,  te.nro FROM usuario_auditeje u INNER JOIN ejeestrategico e ON u.idejeestrategico=e.idejeestrategico INNER JOIN temporadaejeestrategico te ON e.idejeestrategico=te.idejeestrategico "
            + " INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND (u.idusuario='"+idusuario+"' AND u.estado=1) AND (t.estado='1' )  ORDER BY te.nro ";

    List<Ejeestrategico> Lista = new ArrayList<Ejeestrategico>(); 
      Ejeestrategico Toto = null;
    int i=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Ejeestrategico();
           
           Toto.setIdejeestrategico(rs.getInt("idejeestrategico"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
           Toto.setEstado(rs.getInt("estado"));
           Toto.setMapaestrategico(rs.getString("mapaestrategico"));
           Toto.setObjetivoestrategico(rs.getString("objetivoestrategico"));
           Toto.setIdtemporadaejeestrategico(rs.getInt("idtemporadaejeestrategico"));
           Toto.setCodigo(rs.getString("nro"));

           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
    return Lista;
    }       
     public Ejeestrategico Eje(int id){
    String sql=" SELECT e.idejeestrategico,e.nombre,e.descripcion,e.estado,e.objetivoestrategico,e.mapaestrategico, te.idtemporadaejeestrategico, "+
        " e.codigo FROM ejeestrategico e INNER JOIN temporadaejeestrategico te ON e.idejeestrategico=te.idejeestrategico "+
        " INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1' AND te.idtemporadaejeestrategico=? ";
      Ejeestrategico Toto = null;
    int i=0;
        try {
            getConexionDb1();
            ps1=con1.prepareStatement(sql);
            ps1.setInt(1,id);
            rs1=ps1.executeQuery();
        while (rs1.next()) 
        {
           Toto  = new Ejeestrategico();
           
           Toto.setIdejeestrategico(rs1.getInt("idejeestrategico"));
           Toto.setNombre(rs1.getString("nombre"));
           Toto.setDescripcion(rs1.getString("descripcion"));
           Toto.setEstado(rs1.getInt("estado"));
           Toto.setMapaestrategico(rs1.getString("mapaestrategico"));
           Toto.setObjetivoestrategico(rs1.getString("objetivoestrategico"));
           Toto.setIdtemporadaejeestrategico(rs1.getInt("idtemporadaejeestrategico"));
           Toto.setCodigo(rs1.getString("codigo"));
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion1();}
    return Toto;
    }
                   

           
           
   public void insertarAvance(Avance to) {
        int r = 0;
        try {
            getConexionDb();
             System.out.println("insetar avance");
            ps = con.prepareStatement(" INSERT INTO avance ( fecha_rg, meta, idmeta, idusuario, id_ciclo, estado) "
                    + " VALUES( now(), ?, ?, ?, ?, '0') ");            
            ps.setString(1, to.getMeta());
            ps.setInt(2, to.getIdmeta());
            ps.setInt(3, to.getIdusuario());
            ps.setInt(4, to.getId_ciclo());
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(" --ERROR ! "+e.getMessage());
        }    
    }
   
          public void eliminarEvidenciaMeta(int idmeta) {
        try {

           getConexionDb();
            ps = con.prepareStatement("update meta set evidencia=?, tipo=?, url=? WHERE idmeta=?");
            ps.setString(1, "");
            ps.setString(2, "");
            ps.setString(3, "");
            ps.setInt(4, idmeta);
            ps.executeUpdate();
            System.out.println("Actualizado");
        } catch (Exception e) {
        } finally {
           getCerrarConexion();
        }
    }
          
   public void actualizarAvance(Avance to) {
        int r = 0;
        try {  
            getConexionDb();
            ps = con.prepareStatement("update avance set meta=?,fecha_md=(SELECT NOW()) where idavance=?");
            ps.setString(1, to.getMeta());
            ps.setInt(2, to.getIdavance());
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Actualizado!!!");
            }
        } catch (Exception e) {
            System.out.println(" --ERROR ! "+e.getMessage());
        }    
    }
         
 
     public CicloAcademico cicloActivo(){
     CicloAcademico ca=null;
     String sql=" SELECT * FROM ciclo_academico WHERE estado=1 ";     
            try {
                getConexionDb();
                ps=con.prepareStatement(sql);
                rs=ps.executeQuery();
            if (rs.next()) 
            {            
               ca  = new CicloAcademico();
               ca.setId_ciclo(rs.getInt("id_ciclo"));
               ca.setEtiqueta(rs.getString("etiqueta"));
               ca.setNombre(rs.getString("nombre"));
               ca.setEstado(rs.getInt("estado"));
            }

            } catch (Exception e) {
            }
            finally{getCerrarConexion();}     
     return ca;
     }
         
         
          
       public Avance avanceMeta(int m,int nro){
    String sql="SELECT * FROM avance WHERE idMeta=? and id_ciclo=? ";
      Avance Toto = null;
    int id=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, m);
            ps.setInt(2, nro);
            rs=ps.executeQuery();
        while (rs.next()){            
           Toto  = new Avance();
           Toto.setId_ciclo(rs.getInt("id_ciclo"));
           Toto.setIdavance(rs.getInt("idavance"));
           Toto.setMeta(rs.getString("meta"));
           Toto.setIdmeta(rs.getInt("idmeta"));
        }
} catch (Exception e) {
        }
   finally{getCerrarConexion();}
    return Toto;
    }
    public int idAvanceReciente(Avance av){
    String sql="SELECT idavance FROM avance WHERE idmeta=? AND idusuario=? AND id_ciclo=? ";
      int  idAvance = 0;    
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, av.getIdmeta());
            ps.setInt(2, av.getIdusuario());
            ps.setInt(3, av.getId_ciclo());
            rs=ps.executeQuery();
        if (rs.next()){                       
           idAvance=rs.getInt("idavance");
        }
    } catch (Exception e) {}
   finally{getCerrarConexion();}
    return idAvance;
    }
    public void actualizarAvanceValidaMeta(int idAvance, int idMeta){
    String sql=" UPDATE meta SET idavancevalida=? WHERE idmeta=? ";         
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idAvance);
            ps.setInt(2, idMeta); 
            ps.executeUpdate();
    } catch (Exception e) {}
   finally{getCerrarConexion();}   
   }
       
       
       
    public List<Indicador> listaIndicadorBuscar(Periodometa id,Eapfacultad ef, int estadometa,int estadoavance ,String valor, int idFilial){
        
    String sql= "   SELECT ei.*,i.*, m.idmeta,m.idperiodo,m.evidencia,m.tipo,m.url,concat(es.codigo,' ', es.nombre) AS estrategia, te.idtemporadaejeestrategico,  "+ 
   "   (CASE WHEN m.evidencia IS NULL OR m.evidencia=''  THEN 0 ELSE 1 END) AS valorevidencia, "+
   "   (CASE WHEN m.meta=0 OR m.meta IS NULL THEN '0' ELSE m.meta END) AS meta  "+
   "   FROM estrategiaindicador ei   "+
   "   LEFT JOIN meta m ON m.idestrategiaindicador=ei.idestrategiaindicador AND m.ideapfacultad="+ef.getIdeapfacultad()+" AND m.idperiodo="+id.getIdperiodometa()+" "+   
   "   INNER JOIN indicador i ON i.idindicador=ei.idindicador   "+  
   "   INNER JOIN ejeestrategia ee ON ee.idejeestrategia=ei.idejeestrategia "+
   "   INNER JOIN estrategia es ON ee.idestrategia=es.idestrategia "+    
   "   INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico "+   
   "   INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1' AND i.nombre LIKE '%"+valor+"%' AND i.idfilial="+idFilial+" ";      
    
    
      List<Indicador> Lista = new ArrayList<Indicador>(); 
      sge.modelo.Indicador Toto = null;
        System.out.println("DMPP-->"+sql);
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
        while (rs.next()) 
        {
           Toto  = new sge.modelo.Indicador();       
           Toto.setIdindicador(rs.getInt("idindicador"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
           Toto.setEstado(rs.getInt("estado"));
           Toto.setInstrumento(rs.getString("instrumento"));
           Toto.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
           Toto.setNro(rs.getInt("idestrategiaindicador"));
           Toto.setCodigo(rs.getString("codigo"));
           Toto.setMeta(rs.getInt("meta"));
           Toto.setIdmeta(rs.getInt("idmeta"));
           Toto.setIdperiodometa(rs.getInt("idperiodo"));
           Toto.setEvidencia(rs.getString("evidencia"));
           Toto.setTipo(rs.getString("tipo"));
           Toto.setUrl(rs.getString("url"));
           Toto.setIdtipometa(rs.getInt("idtipometa"));
           Toto.setValorevidencia(rs.getInt("valorevidencia"));
           Toto.setEstrategia(rs.getString("estrategia"));
            if(ef.getIdeapfacultad()!=0){
               if(id.getIdperiodometa()!=0){Toto.setEstadoactualizar(1);}
               else{Toto.setEstadoactualizar(1);}
              }
             else{Toto.setEstadoactualizar(1);}
           
                  
           Toto.setSemaforo(semaforo(rs.getInt("idmeta")));
           Toto.setProgreso(progreso(rs.getInt("idmeta")));
           Toto.setTotalavance(totalavance(rs.getInt("idmeta")));
           Toto.setEstadometa(estadometa);
           Toto.setEstadoavance(estadoavance);
           Toto.setIdtemporadaejeestrategico(rs.getInt("idtemporadaejeestrategico"));
           
           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
        System.out.println("---> LISTA INDICADORES DAO --> $$ -> "+Lista.toArray().length);
    return Lista;
    }
    public List<Indicador> listaIndicadorBuscarPrimero(Periodometa id,Eapfacultad ef, int estadometa,int estadoavance ,String valor, int idFilial, int idEjeEstrategico){
        
    String sql= "   SELECT ei.*,i.*, m.idmeta,m.idperiodo,m.evidencia,m.tipo,m.url,concat(es.codigo,' ', es.nombre) AS estrategia, te.idtemporadaejeestrategico,  "+ 
   "   (CASE WHEN m.evidencia IS NULL OR m.evidencia=''  THEN 0 ELSE 1 END) AS valorevidencia, "+
   "   (CASE WHEN m.meta=0 OR m.meta IS NULL THEN '0' ELSE m.meta END) AS meta  "+
   "   FROM estrategiaindicador ei   "+
   "   LEFT JOIN meta m ON m.idestrategiaindicador=ei.idestrategiaindicador AND m.ideapfacultad=? AND m.idperiodo=? "+   
   "   INNER JOIN indicador i ON i.idindicador=ei.idindicador   "+  
   "   INNER JOIN ejeestrategia ee ON ee.idejeestrategia=ei.idejeestrategia "+
   "   INNER JOIN estrategia es ON ee.idestrategia=es.idestrategia "+    
   "   INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico "+   
   "   INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1' AND i.nombre LIKE ? AND i.idfilial=? and te.idtemporadaejeestrategico=? ";      
    
    
      List<Indicador> Lista = new ArrayList<Indicador>(); 
      sge.modelo.Indicador Toto = null;

        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1,ef.getIdeapfacultad());
            ps.setInt(2,id.getIdperiodometa());
            ps.setString(3,"%"+valor+"%");
            ps.setInt(4,idFilial);
            ps.setInt(5,idEjeEstrategico);
            rs=ps.executeQuery();
        while (rs.next()) 
        {
           Toto  = new sge.modelo.Indicador();       
           Toto.setIdindicador(rs.getInt("idindicador"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
           Toto.setEstado(rs.getInt("estado"));
           Toto.setInstrumento(rs.getString("instrumento"));
           Toto.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
           Toto.setNro(rs.getInt("idestrategiaindicador"));//numero
           Toto.setCodigo(rs.getString("codigo"));
           Toto.setMeta(rs.getInt("meta"));
           Toto.setIdmeta(rs.getInt("idmeta"));
           Toto.setIdperiodometa(rs.getInt("idperiodo"));
           Toto.setEvidencia(rs.getString("evidencia"));
           Toto.setTipo(rs.getString("tipo"));
           Toto.setUrl(rs.getString("url"));
           Toto.setIdtipometa(rs.getInt("idtipometa"));
           Toto.setValorevidencia(rs.getInt("valorevidencia"));
           Toto.setEstrategia(rs.getString("estrategia"));
            if(ef.getIdeapfacultad()!=0){
               if(id.getIdperiodometa()!=0){Toto.setEstadoactualizar(1);}
               else{Toto.setEstadoactualizar(1);}
              }
             else{Toto.setEstadoactualizar(1);}
           
                  
           Toto.setSemaforo(semaforo(rs.getInt("idmeta")));
           Toto.setProgreso(progreso(rs.getInt("idmeta")));
           Toto.setTotalavance(totalavance(rs.getInt("idmeta")));
           Toto.setEstadometa(estadometa);
           Toto.setEstadoavance(estadoavance);
           Toto.setIdtemporadaejeestrategico(rs.getInt("idtemporadaejeestrategico"));
           
           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
        System.out.println("---> LISTA INDICADORES DAO --> $$ -> "+Lista.toArray().length);
    return Lista;
    }
 
    
    
    public String nombreIndicador(String valor){
    return valor;
    }
    

    
    
    public List<Indicador> listaIndicadorEje(Periodometa id,Eapfacultad ef, int estadometa,int estadoavance ,int idtemeje,int idFilial){
        
    String sql= " SELECT ei.*,i.*, m.idmeta,m.idperiodo,m.evidencia,m.idavancevalida,m.tipo,m.url, concat(es.codigo,' ', es.nombre) AS estrategia, "+  
      " (CASE WHEN m.evidencia IS NULL OR m.evidencia=''  THEN 0 ELSE 1 END) AS valorevidencia, "+ 
      " (CASE WHEN m.meta=0 OR m.meta IS NULL THEN '0' ELSE m.meta END) AS meta "+  
      " FROM estrategiaindicador ei "+   
      " LEFT JOIN meta m ON m.idestrategiaindicador=ei.idestrategiaindicador AND m.ideapfacultad="+ef.getIdeapfacultad()+" AND m.idperiodo="+id.getIdperiodometa()+" "+  
      " INNER JOIN indicador i ON i.idindicador=ei.idindicador "+   
      " INNER JOIN ejeestrategia ee ON ee.idejeestrategia=ei.idejeestrategia "+ 
      " INNER JOIN estrategia es ON ee.idestrategia=es.idestrategia "+    
      " INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico "+   
      " INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1' AND te.idtemporadaejeestrategico="+idtemeje+" AND i.idfilial="+idFilial+" ";      
    
      
      List<Indicador> Lista = new ArrayList<Indicador>(); 
      sge.modelo.Indicador Toto = null;

        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
//            ps.setInt(1,ef.getIdeapfacultad());
//            ps.setInt(2,id.getIdperiodometa());
//            ps.setInt(3,idtemeje);
            System.out.println("Consulta:"+ef.getIdeapfacultad()+"\n"+id.getIdperiodometa()+"\n"+idtemeje);
            System.out.println("DMP:"+sql);
            rs=ps.executeQuery();
            
        while (rs.next()) 
        {
           Toto  = new sge.modelo.Indicador();       
           Toto.setIdindicador(rs.getInt("idindicador"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
           Toto.setEstado(rs.getInt("estado"));
           Toto.setInstrumento(rs.getString("instrumento"));
           Toto.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
           Toto.setNro(rs.getInt("idestrategiaindicador"));//numero
           Toto.setCodigo(rs.getString("codigo"));
           Toto.setMeta(rs.getInt("meta"));
           Toto.setIdmeta(rs.getInt("idmeta"));
           Toto.setIdperiodometa(rs.getInt("idperiodo"));
           Toto.setEvidencia(rs.getString("evidencia"));
           Toto.setTipo(rs.getString("tipo"));
           Toto.setUrl(rs.getString("url"));
           Toto.setIdtipometa(rs.getInt("idtipometa"));
           Toto.setIdavancevalida(rs.getInt("idavancevalida"));
           Toto.setValorevidencia(rs.getInt("valorevidencia"));
           Toto.setEstrategia(rs.getString("estrategia"));
            if(ef.getIdeapfacultad()!=0){
               if(id.getIdperiodometa()!=0){Toto.setEstadoactualizar(1);}
               else{Toto.setEstadoactualizar(1);}
              }
             else{Toto.setEstadoactualizar(1);}
           
                  
           Toto.setSemaforo(semaforo(rs.getInt("idmeta")));
           Toto.setProgreso(progreso(rs.getInt("idmeta")));
           Toto.setTotalavance(totalavance(rs.getInt("idmeta")));
           Toto.setEstadometa(estadometa);
           Toto.setEstadoavance(estadoavance);
           
           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
        System.out.println("---> LISTA INDICADORES DAO ATRAS --> $$ -> "+Lista.toArray().length);
    return Lista;
    }  
            
          
    
    
    
        public List<Indicador> listaIndicadorU(Periodometa id,Eapfacultad ef, int estadometa,int estadoavance ,String valor){
        
    String sql= "   SELECT ei.*,i.*, m.idmeta,m.idperiodo,m.evidencia,m.tipo,m.url,es.nombre AS estrategia,  "+ 
   "   (CASE WHEN m.evidencia IS NULL OR m.evidencia=''  THEN 0 ELSE 1 END) AS valorevidencia, "+
   "   (CASE WHEN m.meta=0 OR m.meta IS NULL THEN '0' ELSE m.meta END) AS meta  "+
   "   FROM estrategiaindicador ei   "+
   "   LEFT JOIN meta m ON m.idestrategiaindicador=ei.idestrategiaindicador AND m.ideapfacultad=? AND m.idperiodo=? "+   
   "   INNER JOIN indicador i ON i.idindicador=ei.idindicador   "+  
   "   INNER JOIN ejeestrategia ee ON ee.idejeestrategia=ei.idejeestrategia "+
   "    INNER JOIN estrategia es ON ee.idestrategia=es.idestrategia "+    
   "   INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico "+   
   "   INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1'  AND ei.idestrategiaindicador=?";      
    
    
      List<Indicador> Lista = new ArrayList<Indicador>(); 
      sge.modelo.Indicador Toto = null;

        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1,ef.getIdeapfacultad());
            ps.setInt(2,id.getIdperiodometa());
            ps.setString(3,valor);
            rs=ps.executeQuery();
        while (rs.next()) 
        {
           Toto  = new sge.modelo.Indicador();       
           Toto.setIdindicador(rs.getInt("idindicador"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
           Toto.setEstado(rs.getInt("estado"));
           Toto.setInstrumento(rs.getString("instrumento"));
           Toto.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
           Toto.setNro(rs.getInt("idestrategiaindicador"));//Numero
           Toto.setCodigo(rs.getString("codigo"));
           Toto.setMeta(rs.getInt("meta"));
           Toto.setIdmeta(rs.getInt("idmeta"));
           Toto.setIdperiodometa(rs.getInt("idperiodometa"));
           Toto.setEvidencia(rs.getString("evidencia"));
           Toto.setTipo(rs.getString("tipo"));
           Toto.setUrl(rs.getString("url"));
           Toto.setIdtipometa(rs.getInt("idtipometa"));
           Toto.setValorevidencia(rs.getInt("valorevidencia"));
           Toto.setEstrategia(rs.getString("estrategia"));
            if(ef.getIdeapfacultad()!=0){
               if(id.getIdperiodometa()!=0){Toto.setEstadoactualizar(1);}
               else{Toto.setEstadoactualizar(0);}
              }
             else{Toto.setEstadoactualizar(0);}
           
                  
           Toto.setSemaforo(semaforo(rs.getInt("idmeta")));
           Toto.setProgreso(progreso(rs.getInt("idmeta")));
           Toto.setTotalavance(totalavance(rs.getInt("idmeta")));
           Toto.setEstadometa(estadometa);
           Toto.setEstadoavance(estadoavance);
            
           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
        System.out.println("---> LISTA INDICADORES DAO --> $$ -> "+Lista.toArray().length);
    return Lista;
    }
        
        
        public List<Actividad> ListaActividadMeta(Meta id){
    String sql="SELECT * FROM actividad WHERE idmeta=? ORDER BY nro";

    List<Actividad> Lista = new ArrayList<Actividad>(); 
      Actividad Toto = null;
    int i=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id.getIdmeta());
            System.out.println("id -- Meta :-->"+id.getIdmeta());
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Actividad();
           
           Toto.setIdactividad(rs.getInt("idactividad"));
           Toto.setNro(rs.getInt("nro"));
           Toto.setAccion(rs.getString("accion"));
           Toto.setCantidad(rs.getInt("cantidad"));
           Toto.setPresupuesto(rs.getDouble("presupuesto"));
           Toto.setRubro(rs.getString("rubro"));
           Toto.setEvidencia(rs.getString("evidencia"));  
           Toto.setIdmeta(rs.getInt("idmeta"));
           
           Toto.setEnero(rs.getInt("enero"));
           Toto.setFebrero(rs.getInt("febrero"));
           Toto.setMarzo(rs.getInt("marzo"));
           Toto.setAbril(rs.getInt("abril"));
           Toto.setMayo(rs.getInt("mayo"));
           Toto.setJunio(rs.getInt("junio"));
           Toto.setJulio(rs.getInt("julio"));
           Toto.setAgosto(rs.getInt("agosto"));
           Toto.setSetiembre(rs.getInt("setiembre"));
           Toto.setOctubre(rs.getInt("octubre"));
           Toto.setNoviembre(rs.getInt("noviembre"));
           Toto.setDiciembre(rs.getInt("diciembre"));
           Toto.setResponsable(rs.getString("responsable"));
           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
            System.out.println(" Lista meta --->"+Lista.toArray().length);
    return Lista;
    }        
  
        
        
   
     
   public void EliminarActividadMeta(int id){
    String sql="Delete FROM actividad WHERE idactividad=?";
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
  
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
    } 
   
   
      public void EliminarEvidencia(int id){
    String sql="Delete FROM evidencia WHERE idevidencia=?";
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
  
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
    } 
        
        
       
        
        public List<Actividad> ListaActividadIndicador(int id){
    String sql=" SELECT * FROM actividad_indicador WHERE idestrategiaindicador=? ORDER BY nro";

    List<Actividad> Lista = new ArrayList<Actividad>(); 
      Actividad Toto = null;
    int i=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            System.out.println("id -- ESTrATEGIA INDICADOR:-->"+id);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Actividad();
           
           Toto.setIdactividad(rs.getInt("idactividadindicador"));
           Toto.setNro(rs.getInt("nro"));
           Toto.setAccion(rs.getString("accion"));
           Toto.setCantidad(rs.getInt("cantidad"));
           Toto.setPresupuesto(rs.getInt("presupuesto"));
           Toto.setRubro(rs.getString("rubro"));
           Toto.setEvidencia(rs.getString("evidencia"));             
           Toto.setEnero(rs.getInt("enero"));
           Toto.setFebrero(rs.getInt("febrero"));
           Toto.setMarzo(rs.getInt("marzo"));
           Toto.setAbril(rs.getInt("abril"));
           Toto.setMayo(rs.getInt("mayo"));
           Toto.setJunio(rs.getInt("junio"));
           Toto.setJulio(rs.getInt("julio"));
           Toto.setAgosto(rs.getInt("agosto"));
           Toto.setSetiembre(rs.getInt("setiembre"));
           Toto.setOctubre(rs.getInt("octubre"));
           Toto.setNoviembre(rs.getInt("noviembre"));
           Toto.setDiciembre(rs.getInt("diciembre"));
           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
            System.out.println(" Lista meta --->"+Lista.toArray().length);
    return Lista;
    }        
        
        
        
        
        
        
        
        
      
   public List<Evidencia> ListaEvidenciaMeta(int idAvance){
    String sql="SELECT * FROM evidencia WHERE idavance=? ORDER BY nro";
    List<Evidencia> Lista = new ArrayList<Evidencia>(); 
      Evidencia Toto = null;
    int i=0;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idAvance);
            System.out.println("id -- Meta :-->"+idAvance);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto  = new Evidencia();
           
           Toto.setIdevidencia(rs.getInt("idevidencia"));
           Toto.setEvidencia(rs.getString("evidencia"));
           Toto.setTipo(rs.getString("tipo"));
           Toto.setUrl(rs.getString("url"));
           Toto.setFecha_reg(rs.getString("fecha_reg"));
           Toto.setIdavance(rs.getInt("idavance"));
           
          
           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
            System.out.println(" Lista meta --->"+Lista.toArray().length);
    return Lista;
    }          
        
       public void insertarEvidencia(String evidencia,String tipo,String url,int idAvance, int idUsuario) {
        System.out.println(" --------------------- insertarevidencia -----------  ");
        int r = 0;
        try {
            getConexionDb();
            //ps = con.prepareStatement("insert into evidencia(evidencia,tipo,url,idmeta,fecha_reg) values(?,?,?,?,(SELECT NOW()))");
            ps = con.prepareStatement("INSERT INTO evidencia(evidencia,tipo,url,fecha_reg,idusuario,idavance) VALUES(?,?,?,(SELECT NOW()),?,? ) ");
            ps.setString(1, evidencia);
            ps.setString(2, tipo);
            ps.setString(3, url);
            ps.setInt(4, idUsuario);
            ps.setInt(5, idAvance);
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }     
       public int codigoActividadNumero( int idMeta) {
        System.out.println(" --------------------- insertarevidencia -----------  ");
        int codigo = 0;
        try {
            getConexionDb();                       
            ps = con.prepareStatement(" SELECT (CASE WHEN COUNT(*)=0 THEN 1 ELSE (COUNT(*)+1) END) AS codigo FROM ( SELECT * FROM  actividad WHERE idmeta=? ) AS d ");
            ps.setInt(1, idMeta);
            rs=ps.executeQuery();
            if (rs.next()) {
                codigo = rs.getInt("codigo");               
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return codigo;
    }   
        
    public Indicador estrategiaIndicador(Periodometa id,Eapfacultad ef, int estadometa,int estadoavance ,String valor, int idFilial){        
    String sql= "   SELECT ei.*,i.*, CONCAT('(',i.codigo,') ',i.nombre) as nombreindicador, m.idmeta,m.idperiodo,m.evidencia,m.idavancevalida,m.tipo,m.url,es.nombre AS estrategia,  "+ 
                "   (CASE WHEN m.evidencia IS NULL OR m.evidencia=''  THEN 0 ELSE 1 END) AS valorevidencia, "+
                "   (CASE WHEN m.meta=0 OR m.meta IS NULL THEN '0' ELSE m.meta END) AS meta  "+
                "   FROM estrategiaindicador ei   "+
                "   LEFT JOIN meta m ON m.idestrategiaindicador=ei.idestrategiaindicador AND m.ideapfacultad=? AND m.idperiodo=? "+   
                "   INNER JOIN indicador i ON i.idindicador=ei.idindicador   "+   
                "   INNER JOIN ejeestrategia ee ON ee.idejeestrategia=ei.idejeestrategia "+
                "   INNER JOIN estrategia es ON ee.idestrategia=es.idestrategia "+    
                "   INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico "+   
                "   INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1'  AND ei.idestrategiaindicador=? and i.idfilial=? ";      
    
    
      List<Indicador> Lista = new ArrayList<Indicador>(); 
      sge.modelo.Indicador Toto = null;

        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1,ef.getIdeapfacultad());
            ps.setInt(2,id.getIdperiodometa());
            ps.setString(3,valor);
            ps.setInt(4,idFilial);
            rs=ps.executeQuery();
        while (rs.next()) 
        {
           Toto  = new sge.modelo.Indicador();       
           Toto.setIdindicador(rs.getInt("idindicador"));
           Toto.setNombre(rs.getString("nombreindicador"));
           Toto.setDescripcion(rs.getString("descripcion"));
           Toto.setEstado(rs.getInt("estado"));
           Toto.setInstrumento(rs.getString("instrumento"));
           Toto.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
           Toto.setNro(rs.getInt("idestrategiaindicador"));//numero
           Toto.setMeta(rs.getInt("meta"));
           Toto.setIdmeta(rs.getInt("idmeta"));
           Toto.setIdperiodometa(rs.getInt("idperiodo"));
           Toto.setEvidencia(rs.getString("evidencia"));
           Toto.setIdavancevalida(rs.getInt("idavancevalida"));
           Toto.setTipo(rs.getString("tipo"));
           Toto.setUrl(rs.getString("url"));
           Toto.setIdtipometa(rs.getInt("idtipometa"));
           Toto.setValorevidencia(rs.getInt("valorevidencia"));
           Toto.setEstrategia(rs.getString("estrategia"));
            if(ef.getIdeapfacultad()!=0){
               if(id.getIdperiodometa()!=0){Toto.setEstadoactualizar(1);}
               else{Toto.setEstadoactualizar(0);}
              }
             else{Toto.setEstadoactualizar(0);}               
           Toto.setSemaforo(semaforo(rs.getInt("idmeta")));
           Toto.setProgreso(progreso(rs.getInt("idmeta")));
           Toto.setTotalavance(totalavance(rs.getInt("idmeta")));
           Toto.setEstadometa(estadometa);
           Toto.setEstadoavance(estadoavance);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
        System.out.println("---> LISTA INDICADORES DAO --> $$ -> "+Lista.toArray().length);
    return Toto;
    }        
        
        
        
        
        
    public void insertarActividad(Actividad to) {
        int r = 0;
        try {
            System.out.println("ver llego>"+to.getPresupuesto());
            getConexionDb();
            ps = con.prepareStatement("insert into actividad(nro,accion,cantidad,presupuesto,rubro,idmeta,enero,febrero,marzo,abril,mayo,junio,julio,agosto,setiembre,octubre,noviembre,diciembre, responsable)\n" +
           " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, to.getNro());
            ps.setString(2, to.getAccion());
            ps.setInt(3, to.getCantidad());
            ps.setDouble(4, to.getPresupuesto());
            ps.setString(5, to.getRubro());
            ps.setInt(6, to.getIdmeta());
            ps.setInt(7, to.getEnero());
            ps.setInt(8, to.getFebrero());
            ps.setInt(9, to.getMarzo());
            ps.setInt(10, to.getAbril());
            ps.setInt(11, to.getMayo());
            ps.setInt(12, to.getJunio());
            ps.setInt(13, to.getJulio());
            ps.setInt(14, to.getAgosto());
            ps.setInt(15, to.getSetiembre());
            ps.setInt(16, to.getOctubre());
            ps.setInt(17, to.getNoviembre());
            ps.setInt(18, to.getDiciembre());
            ps.setString(19, to.getResponsable());
 
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

     
    }
    
    
        
 // --------------> Consolidados ------------------- >
     // -------------------------- Filial -------- ------------------- >
       
    
    public int metaIndicadorFacultad(int idfilialfacultad,int idestrategiaindicador, int idPeriodometa){        
    String sql= " SELECT (CASE WHEN SUM(m.meta)=0 OR SUM(m.meta) IS NULL THEN '0' ELSE SUM(m.meta) END) AS meta  FROM meta m  "+
                " INNER JOIN eapfacultad ea ON m.ideapfacultad=ea.ideapfacultad   "+
                " INNER JOIN filialfacultad ff ON ea.idfilialfacultad=ff.idfilialfacultad    "+
                " INNER JOIN periodo pm ON m.idperiodo=pm.idperiodo   "+
                " WHERE idestrategiaindicador=? AND ff.idfilialfacultad =? AND pm.idperiodo=? ";           
   int meta=0;
        try {
            getConexionDb1();
            ps1=con1.prepareStatement(sql);
            ps1.setInt(1,idestrategiaindicador);
            ps1.setInt(2,idfilialfacultad);
            ps1.setInt(3,idPeriodometa);
            rs1=ps1.executeQuery();
           while (rs1.next()) 
        {  
           meta=rs1.getInt("meta");
        }
        } catch (Exception e) {
            System.out.println("Error meta "+e);
        }
        finally{}
    return meta;
    }  
    
    public int metaIndicadorFacultadPromedio(int idfilialfacultad,int idestrategiaindicador, int idPeriodometa){        
    String sql= " SELECT (CASE WHEN AVG(m.meta)=0 OR AVG(m.meta) IS NULL THEN '0' ELSE AVG(m.meta) END) AS meta  FROM meta m  "+
                " INNER JOIN eapfacultad ea ON m.ideapfacultad=ea.ideapfacultad   "+
                " INNER JOIN filialfacultad ff ON ea.idfilialfacultad=ff.idfilialfacultad    "+
                " INNER JOIN periodo pm ON m.idperiodo=pm.idperiodo   "+
                " WHERE idestrategiaindicador=? AND ff.idfilialfacultad =? AND pm.idperiodo=? ";           
   int meta=0;
        try {
            getConexionDb1();
            ps1=con1.prepareStatement(sql);
            ps1.setInt(1,idestrategiaindicador);
            ps1.setInt(2,idfilialfacultad);
            ps1.setInt(3,idPeriodometa);
            rs1=ps1.executeQuery();
           while (rs1.next()) 
        {  
           meta=rs1.getInt("meta");
        }
        } catch (Exception e) {
            System.out.println("Error meta "+e);
        }
        finally{}
    return meta;
    }  
    
    
    public int metaIndicadorFacultadcount(int idfilialfacultad,int idestrategiaindicador, int idPeriodometa){        
    String sql= " SELECT (CASE WHEN SUM(m.meta)=0 OR SUM(m.meta) IS NULL THEN '0' ELSE SUM(m.meta) END) AS meta  FROM meta m  "+
                " INNER JOIN eapfacultad ea ON m.ideapfacultad=ea.ideapfacultad   "+
                " INNER JOIN filialfacultad ff ON ea.idfilialfacultad=ff.idfilialfacultad    "+
                " INNER JOIN periodo pm ON m.idperiodo=pm.idperiodo   "+
                " WHERE idestrategiaindicador=? AND ff.idfilialfacultad =? AND pm.idperiodo=? ";           
   int meta=0;
        try {
            getConexionDb1();
            ps1=con1.prepareStatement(sql);
            ps1.setInt(1,idestrategiaindicador);
            ps1.setInt(2,idfilialfacultad);
            ps1.setInt(3,idPeriodometa);
            rs1=ps1.executeQuery();
           while (rs1.next()) 
        {  
        meta++;
        }
        } catch (Exception e) {
            System.out.println("Error meta "+e);
        }
        finally{}
    return meta;
    }      
    
    
    
    public int metaIndicadorFilial(int idfilial,int idestrategiaindicador, int idPeriodometa){        
    String sql= " SELECT (CASE WHEN SUM(m.meta)=0 OR SUM(m.meta) IS NULL THEN '0' ELSE SUM(m.meta) END) AS meta  "+  
		" FROM meta m  "+
		"  INNER JOIN periodo pm ON m.idperiodo=pm.idperiodo "+ 
                "  INNER JOIN eapfacultad ea ON m.ideapfacultad=ea.ideapfacultad "+
                "  INNER JOIN filialfacultad ff ON ea.idfilialfacultad=ff.idfilialfacultad "+
		"  INNER JOIN filial f ON ff.idfilial=f.idfilial "+     
                "  WHERE idestrategiaindicador=? AND f.idfilial=? AND pm.idperiodo=? ";           
   int meta=0;
        try {
            getConexionDb1();
            ps1=con1.prepareStatement(sql);
            ps1.setInt(1,idestrategiaindicador);
            ps1.setInt(2,idfilial);
            ps1.setInt(3,idPeriodometa);
            rs1=ps1.executeQuery();
           while (rs1.next()) 
        {  
           meta=rs1.getInt("meta");
        }
        } catch (Exception e) {
            System.out.println("Error meta "+e);
        }
        finally{}
    return meta;
    }      
    public int metaIndicadorFilialPromedio(int idfilial,int idestrategiaindicador, int idPeriodometa){        
    String sql= " SELECT (CASE WHEN avg(m.meta)=0 OR avg(m.meta) IS NULL THEN '0' ELSE avg(m.meta) END) AS meta  "+  
		" FROM meta m  "+
		"  INNER JOIN periodo pm ON m.idperiodo=pm.idperiodo "+ 
                "  INNER JOIN eapfacultad ea ON m.ideapfacultad=ea.ideapfacultad "+
                "  INNER JOIN filialfacultad ff ON ea.idfilialfacultad=ff.idfilialfacultad "+
		"  INNER JOIN filial f ON ff.idfilial=f.idfilial "+     
                "  WHERE idestrategiaindicador=? AND f.idfilial=? AND pm.idperiodo=? ";           
   int meta=0;
        try {
            getConexionDb1();
            ps1=con1.prepareStatement(sql);
            ps1.setInt(1,idestrategiaindicador);
            ps1.setInt(2,idfilial);
            ps1.setInt(3,idPeriodometa);
            rs1=ps1.executeQuery();
           while (rs1.next()) 
        {  
           meta=rs1.getInt("meta");
        }
        } catch (Exception e) {
            System.out.println("Error meta "+e);
        }
        finally{}
    return meta;
    }      
    
    
    
public void actualizarActividad(Actividad to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement(" UPDATE actividad SET accion =?, cantidad =?, presupuesto = ?, rubro =? , "
                    + " febrero =?, marzo =? , abril =?, mayo =?, junio =?, julio =?, "
                    + " agosto =?, setiembre =?, octubre =? , noviembre =? , diciembre =?, "
                    + " enero =?, responsable =? WHERE idactividad =? ");
            
            ps.setString(++r, to.getAccion());
            ps.setInt(++r, to.getCantidad());
            ps.setDouble(++r, to.getPresupuesto());
            ps.setString(++r, to.getRubro());            
            
            ps.setInt(++r, to.getFebrero());
            ps.setInt(++r, to.getMarzo());
            ps.setInt(++r, to.getAbril());
            ps.setInt(++r, to.getMayo());
            ps.setInt(++r, to.getJunio());
            ps.setInt(++r, to.getJulio());
            ps.setInt(++r, to.getAgosto());
            ps.setInt(++r, to.getSetiembre());
            ps.setInt(++r, to.getOctubre());
            ps.setInt(++r, to.getNoviembre());
            ps.setInt(++r, to.getDiciembre());
            ps.setInt(++r, to.getEnero());            
            ps.setString(++r, to.getResponsable());
            ps.setInt(++r, to.getIdactividad());
            if (ps.executeUpdate() == 1) {
              
                System.out.println("Se Actualizao Correctamente!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

     
    }    
    
    
    public int avanceIndicadorFacultad(int idfilialfacultad,int idestrategiaindicador, int idPeriodometa){        
    String sql= " SELECT (CASE WHEN SUM(a.meta)=0 OR SUM(a.meta) IS NULL THEN '0' ELSE SUM(a.meta) END) AS avance  FROM avance a "+ 
                " INNER JOIN  meta m ON a.idmeta=m.idmeta "+
                " INNER JOIN eapfacultad ea ON m.ideapfacultad=ea.ideapfacultad "+
                " INNER JOIN filialfacultad ff ON ea.idfilialfacultad=ff.idfilialfacultad "+
                " INNER JOIN periodo pm ON m.idperiodo=pm.idperiodo "+
                " WHERE idestrategiaindicador=? AND ff.idfilialfacultad =? AND pm.idperiodo=?";           
   int avance=0;
        try {
            getConexionDb3();
            ps3=con3.prepareStatement(sql);
            ps3.setInt(1,idestrategiaindicador);
            ps3.setInt(2,idfilialfacultad);
            ps3.setInt(3,idPeriodometa);
            rs3=ps3.executeQuery();
        while (rs3.next()) 
        {
           avance=rs3.getInt("avance");
        }               
        } catch (Exception e) {
            System.out.println("Error Avance  "+e);
        }
        finally{}
    return avance;
    } 
    public int avanceIndicadorFacultadPromedio(int idfilialfacultad,int idestrategiaindicador, int idPeriodometa){        
    String sql= " SELECT (CASE WHEN avg(a.meta)=0 OR avg(a.meta) IS NULL THEN '0' ELSE avg(a.meta) END) AS avance  FROM avance a "+ 
                " INNER JOIN  meta m ON a.idmeta=m.idmeta "+
                " INNER JOIN eapfacultad ea ON m.ideapfacultad=ea.ideapfacultad "+
                " INNER JOIN filialfacultad ff ON ea.idfilialfacultad=ff.idfilialfacultad "+
                " INNER JOIN periodo pm ON m.idperiodo=pm.idperiodo "+
                " WHERE idestrategiaindicador=? AND ff.idfilialfacultad =? AND pm.idperiodo=?";           
   int avance=0;
        try {
            getConexionDb3();
            ps3=con3.prepareStatement(sql);
            ps3.setInt(1,idestrategiaindicador);
            ps3.setInt(2,idfilialfacultad);
            ps3.setInt(3,idPeriodometa);
            rs3=ps3.executeQuery();
        while (rs3.next()) 
        {
           avance=rs3.getInt("avance");
        }               
        } catch (Exception e) {
            System.out.println("Error Avance  "+e);
        }
        finally{}
    return avance;
    } 
    

    public int avanceIndicadorFilial(int idfilial,int idestrategiaindicador, int idPeriodometa){        
    String sql= " SELECT (CASE WHEN SUM(a.meta)=0 OR SUM(a.meta) IS NULL THEN '0' ELSE SUM(a.meta) END) AS avance "+  
	         " FROM avance a   "+
                 " INNER JOIN  meta m ON a.idmeta=m.idmeta  "+
		 " INNER JOIN periodo pm ON m.idperiodo=pm.idperiodo "+
                 " INNER JOIN eapfacultad ea ON m.ideapfacultad=ea.ideapfacultad  "+
                 " INNER JOIN filialfacultad ff ON ea.idfilialfacultad=ff.idfilialfacultad  "+
		 " INNER JOIN filial f ON ff.idfilial=f.idfilial "+
                 " WHERE idestrategiaindicador=? AND f.idfilial=? AND pm.idperiodo=?";           
   int avance=0;
        try {
            getConexionDb3();
            ps3=con3.prepareStatement(sql);
            ps3.setInt(1,idestrategiaindicador);
            ps3.setInt(2,idfilial);
            ps3.setInt(3,idPeriodometa);
            rs3=ps3.executeQuery();
        while (rs3.next()) 
        {
           avance=rs3.getInt("avance");
        }               
        } catch (Exception e) {
            System.out.println("Error Avance  "+e);
        }
        finally{}
    return avance;
    } 
    public int avanceIndicadorFilialPromedio(int idfilial,int idestrategiaindicador, int idPeriodometa){        
    String sql= " SELECT (CASE WHEN avg(a.meta)=0 OR avg(a.meta) IS NULL THEN '0' ELSE avg(a.meta) END) AS avance "+  
	         " FROM avance a   "+
                 " INNER JOIN  meta m ON a.idmeta=m.idmeta  "+
		 " INNER JOIN periodo pm ON m.idperiodo=pm.idperiodo "+
                 " INNER JOIN eapfacultad ea ON m.ideapfacultad=ea.ideapfacultad  "+
                 " INNER JOIN filialfacultad ff ON ea.idfilialfacultad=ff.idfilialfacultad  "+
		 " INNER JOIN filial f ON ff.idfilial=f.idfilial "+
                 " WHERE idestrategiaindicador=? AND f.idfilial=? AND pm.idperiodo=?";           
   int avance=0;
        try {
            getConexionDb3();
            ps3=con3.prepareStatement(sql);
            ps3.setInt(1,idestrategiaindicador);
            ps3.setInt(2,idfilial);
            ps3.setInt(3,idPeriodometa);
            rs3=ps3.executeQuery();
        while (rs3.next()) 
        {
           avance=rs3.getInt("avance");
        }               
        } catch (Exception e) {
            System.out.println("Error Avance  "+e);
        }
        finally{}
    return avance;
    } 
    
    
    
    
    
    
    public List<Indicador> listaIndicadorFacultad(Periodometa id,Filialfacultad ff){        
    String sql= "  SELECT ei.*,i.nombre,i.instrumento,i.idtipometa,es.nombre AS estrategia"+
     " FROM estrategiaindicador ei  "+   
     " INNER JOIN indicador i ON i.idindicador=ei.idindicador "+  
     " INNER JOIN ejeestrategia ee ON ee.idejeestrategia=ei.idejeestrategia "+ 
     " INNER JOIN estrategia es ON ee.idestrategia=es.idestrategia "+
     " INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico "+    
     " INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1' ";      
       
      List<Indicador> Lista = new ArrayList<Indicador>();
      Indicador Toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto = new Indicador();
           Toto.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
           Toto.setNro(rs.getInt("nro"));     
           Toto.setIdindicador(rs.getInt("idindicador"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setInstrumento(rs.getString("instrumento"));
           Toto.setEstrategia(rs.getString("estrategia"));
           Toto.setIdtipometa(rs.getInt("idtipometa"));
           Toto.setMeta(metaIndicadorFacultad(ff.getIdfilialfacultad(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           Toto.setTotalavance(avanceIndicadorFacultad(ff.getIdfilialfacultad(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           Lista.add(Toto);
        }      
        } catch (Exception e) {
        }
        finally{}
        System.out.println(" LISTA INDICADOR FACUTLAD  "+Lista.toArray().length);
    return Lista;
    }
    
   
    
    
    
        public List<Indicador> listaIndicadorFacultadBuscar(Periodometa id,Filialfacultad ff,String valor){        
    String sql= "  SELECT ei.*,i.nombre,i.instrumento,i.idtipometa,es.nombre AS estrategia, te.idtemporadaejeestrategico "+
     " FROM estrategiaindicador ei  "+   
     " INNER JOIN indicador i ON i.idindicador=ei.idindicador "+   
     " INNER JOIN ejeestrategia ee ON ee.idejeestrategia=ei.idejeestrategia "+ 
     " INNER JOIN estrategia es ON ee.idestrategia=es.idestrategia "+
     " INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico "+    
     " INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1' AND i.nombre LIKE ?";      
       
      List<Indicador> Lista = new ArrayList<Indicador>();
      Indicador Toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setString(1,"%"+valor+"%");
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto = new Indicador();
           Toto.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
           Toto.setNro(rs.getInt("nro"));     
           Toto.setIdindicador(rs.getInt("idindicador"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setInstrumento(rs.getString("instrumento"));
           Toto.setEstrategia(rs.getString("estrategia"));
           Toto.setIdtipometa(rs.getInt("idtipometa"));
           Toto.setMeta(metaIndicadorFacultad(ff.getIdfilialfacultad(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           Toto.setTotalavance(avanceIndicadorFacultad(ff.getIdfilialfacultad(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           Toto.setIdtemporadaejeestrategico(rs.getInt("idtemporadaejeestrategico"));
           Lista.add(Toto);
        }      
        } catch (Exception e) {
        }
        finally{}
        System.out.println(" LISTA INDICADOR FACUTLAD  "+Lista.toArray().length);
    return Lista;
    }
    
   
        
        
        
   public List<Indicador> listaIndicadorFacultadEje(Periodometa id,Filialfacultad ff,int idtemeje){        
    String sql= "  SELECT ei.*,i.nombre, i.codigo,i.instrumento,i.idtipometa,es.nombre AS estrategia "+
     " FROM estrategiaindicador ei  "+   
     " INNER JOIN indicador i ON i.idindicador=ei.idindicador "+   
     " INNER JOIN filial f ON f.idfilial=i.idfilial "+   
     " INNER JOIN ejeestrategia ee ON ee.idejeestrategia=ei.idejeestrategia "+ 
     " INNER JOIN estrategia es ON ee.idestrategia=es.idestrategia "+
     " INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico "+    
     " INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1' AND te.idtemporadaejeestrategico=? AND f.idfilial=? ";      
      List<Indicador> Lista = new ArrayList<Indicador>();
      Indicador Toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1,idtemeje);
            ps.setInt(2,ff.getIdfilial());
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto = new Indicador();
           Toto.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
           Toto.setNro(rs.getInt("nro"));     
           Toto.setIdindicador(rs.getInt("idindicador"));
           Toto.setNombre("("+rs.getString("codigo")+") "+rs.getString("nombre"));
           Toto.setInstrumento(rs.getString("instrumento"));
           Toto.setEstrategia(rs.getString("estrategia"));
           Toto.setIdtipometa(rs.getInt("idtipometa"));           
           if(rs.getInt("idtipometa")==1){
           Toto.setMeta(metaIndicadorFacultad(ff.getIdfilialfacultad(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           }else{
           Toto.setMeta(metaIndicadorFacultadPromedio(ff.getIdfilialfacultad(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           }
           if(rs.getInt("idtipometa")==1){
           Toto.setTotalavance(avanceIndicadorFacultad(ff.getIdfilialfacultad(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           }else{
           Toto.setTotalavance(avanceIndicadorFacultadPromedio(ff.getIdfilialfacultad(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           }
           Lista.add(Toto);
        }      
        } catch (Exception e) {
        }
        finally{}
        System.out.println(" LISTA INDICADOR FACUTLAD  "+Lista.toArray().length);
    return Lista;
    }
    
    
    
    
    public List<Indicador> listaIndicadorFilial(Periodometa id,Filial f){        
    String sql= "  SELECT ei.*,i.nombre,i.instrumento,i.idtipometa,es.nombre AS estrategia"+
     " FROM estrategiaindicador ei  "+   
     " INNER JOIN indicador i ON i.idindicador=ei.idindicador "+   
     " INNER JOIN ejeestrategia ee ON ee.idejeestrategia=ei.idejeestrategia "+ 
     " INNER JOIN estrategia es ON ee.idestrategia=es.idestrategia "+
     " INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico "+    
     " INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1' ";      
       
      List<Indicador> Lista = new ArrayList<Indicador>();
      Indicador Toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto = new Indicador();
           Toto.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
           Toto.setNro(rs.getInt("nro"));     
           Toto.setIdindicador(rs.getInt("idindicador"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setInstrumento(rs.getString("instrumento"));
           Toto.setEstrategia(rs.getString("estrategia"));
           Toto.setIdtipometa(rs.getInt("idtipometa"));
           Toto.setMeta(metaIndicadorFilial(f.getIdfilial(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           Toto.setTotalavance(avanceIndicadorFilial(f.getIdfilial(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           Lista.add(Toto);
        }      
        } catch (Exception e) {
        }
        finally{}
        System.out.println(" LISTA INDICADOR FACUTLAD  "+Lista.toArray().length);
    return Lista;
    }  
    
    public List<Indicador> listaIndicadorFilialEje(Periodometa id,Filial f,int idtemeje){        
    String sql= "  SELECT ei.*,i.nombre, i.codigo,i.instrumento,i.idtipometa,es.nombre AS estrategia"+
     " FROM estrategiaindicador ei  "+          
     " INNER JOIN indicador i ON i.idindicador=ei.idindicador "+ 
     " INNER JOIN filial f ON f.idfilial=i.idfilial  "+ 
     " INNER JOIN ejeestrategia ee ON ee.idejeestrategia=ei.idejeestrategia "+ 
     " INNER JOIN estrategia es ON ee.idestrategia=es.idestrategia "+
     " INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico "+    
     " INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1' AND te.idtemporadaejeestrategico=? AND f.idfilial=? ";      
       
      List<Indicador> Lista = new ArrayList<Indicador>();
      Indicador Toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            ps.setInt(1,idtemeje);
            ps.setInt(2,f.getIdfilial());
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto = new Indicador();
           Toto.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
           Toto.setNro(rs.getInt("nro"));     
           Toto.setIdindicador(rs.getInt("idindicador"));
           Toto.setNombre("("+rs.getString("codigo")+") "+rs.getString("nombre"));
           Toto.setInstrumento(rs.getString("instrumento"));
           Toto.setEstrategia(rs.getString("estrategia"));
           Toto.setIdtipometa(rs.getInt("idtipometa"));
           if(rs.getInt("idtipometa")==1){
           Toto.setMeta(metaIndicadorFilial(f.getIdfilial(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           Toto.setTotalavance(avanceIndicadorFilial(f.getIdfilial(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           }else{
           Toto.setMeta(metaIndicadorFilialPromedio(f.getIdfilial(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           Toto.setTotalavance(avanceIndicadorFilialPromedio(f.getIdfilial(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           }                     
           Lista.add(Toto);
        }      
        } catch (Exception e) {
        }
        finally{}
        System.out.println(" LISTA INDICADOR FACULTAD  "+Lista.toArray().length);
    return Lista;
    }   
    
    
      public List<Indicador> listaIndicadorFilialBuscar(Periodometa id,Filial f,String valor){        
    String sql= "  SELECT ei.*,i.nombre,i.instrumento,i.idtipometa,es.nombre AS estrategia ,te.idtemporadaejeestrategico "+
     " FROM estrategiaindicador ei  "+   
     " INNER JOIN indicador i ON i.idindicador=ei.idindicador "+  
     " INNER JOIN ejeestrategia ee ON ee.idejeestrategia=ei.idejeestrategia "+ 
     " INNER JOIN estrategia es ON ee.idestrategia=es.idestrategia "+
     " INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico "+    
     " INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1' AND i.nombre LIKE ?";      
       
      List<Indicador> Lista = new ArrayList<Indicador>();
      Indicador Toto = null;
        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
             ps.setString(1,"%"+valor+"%");
            rs=ps.executeQuery();
        while (rs.next()) 
        {            
           Toto = new Indicador();
           Toto.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
           Toto.setNro(rs.getInt("nro"));     
           Toto.setIdindicador(rs.getInt("idindicador"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setInstrumento(rs.getString("instrumento"));
           Toto.setEstrategia(rs.getString("estrategia"));
           Toto.setIdtipometa(rs.getInt("idtipometa"));
           Toto.setMeta(metaIndicadorFilial(f.getIdfilial(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           Toto.setTotalavance(avanceIndicadorFilial(f.getIdfilial(),rs.getInt("idestrategiaindicador"),id.getIdperiodometa()));
           Toto.setIdtemporadaejeestrategico(rs.getInt("idtemporadaejeestrategico"));
           Lista.add(Toto);
        }      
        } catch (Exception e) {
        }
        finally{}
        System.out.println(" LISTA INDICADOR FACUTLAD  "+Lista.toArray().length);
    return Lista;
    }    
    
    
    
    
    
    
   
// ---- Lista de conlolidados --------------------- >        
        
    public List<Eap> listaEapConsolidado(Periodometa id,Filialfacultad ff){        
    String sql= "SELECT ea.*, (CASE WHEN e.ideap=7 AND ideapfacultad=36 THEN 'Administración' ELSE e.nombre  END) as nombre FROM eapfacultad  ea INNER JOIN eap e  ON ea.ideap=e.ideap  WHERE ea.idfilialfacultad=?";           
      List<Eap> Lista = new ArrayList<Eap>(); 
      List<Integer> eap = new ArrayList<Integer>();     
      Eap Toto = null;
        try {  
            getConexionDb4();
            ps4=con4.prepareStatement(sql);
            ps4.setInt(1,ff.getIdfilialfacultad());
            rs4=ps4.executeQuery();
        while (rs4.next()) 
        {  
            Toto = new Eap();
            Eapfacultad ef = new Eapfacultad();
            ef.setIdeapfacultad(rs4.getInt("ideapfacultad"));   
            
            Toto.setIdeapfacultad(rs4.getInt("ideapfacultad"));
            Toto.setNombre(rs4.getString("nombre"));
            Toto.setMeta(eap.get(0));
            Lista.add(Toto);           
        }            
        } catch (Exception e) {
        }
        finally{getCerrarConexion4();}
    return Lista;
    }        
        

    public List<Facultad> listaFacultadConsolidado(Periodometa id,Filial f){        
    String sql= "SELECT ff.*,f.nombre FROM filialfacultad  ff INNER JOIN facultad f  ON ff.idfacultad=f.idfacultad  WHERE ff.idfilial=?";           
      List<Facultad> Lista = new ArrayList<Facultad>(); 
      List<Integer> facultad = new ArrayList<Integer>();     
      Facultad Toto = null;
        try {  
            getConexionDb4();
            ps4=con4.prepareStatement(sql);
            ps4.setInt(1,f.getIdfilial());
            rs4=ps4.executeQuery();
        while (rs4.next()) 
        {  
            Toto = new Facultad();
            Filialfacultad ff = new Filialfacultad();
            ff.setIdfilialfacultad(rs4.getInt("idfilialfacultad"));  
            
            Toto.setIdfilialfacultad(rs4.getInt("idfilialfacultad"));
            Toto.setNombre(rs4.getString("nombre"));
            Toto.setConsolidado(facultad.get(0));
            Lista.add(Toto);           
        }            
        } catch (Exception e) {
        }
        finally{getCerrarConexion4();}
    return Lista;
    }            
    
   
        public List<Filial> listaFilialConsolidado(Periodometa id){        
    String sql= "SELECT * FROM filial";           
      List<Filial> Lista = new ArrayList<Filial>(); 
      List<Integer> filial = new ArrayList<Integer>();     
      Filial Toto = null;
        try {  
            getConexionDb4();
            ps4=con4.prepareStatement(sql);
            rs4=ps4.executeQuery();
        while (rs4.next()) 
        {  
            Toto = new Filial();
            Filial f = new Filial();
            f.setIdfilial(rs4.getInt("idfilial"));  
            
            Toto.setIdfilial(rs4.getInt("idfilial"));
            Toto.setDescripcion(rs4.getString("descripcion"));
            Toto.setConsolidado(filial.get(0));
            Lista.add(Toto);           
        }            
        } catch (Exception e) {
        }
        finally{getCerrarConexion4();}
    return Lista;
    }    
    
    
}
