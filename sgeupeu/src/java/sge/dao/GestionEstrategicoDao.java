/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.dao;

import java.util.ArrayList;
import java.util.List;
import sge.connexion.DBConn;
import sge.modelo.Actividadindicador;
import sge.modelo.Coordinadorfacultad;
import sge.modelo.Eapfacultad;
import sge.modelo.EjeEstrategia;
import sge.modelo.Ejeestrategico;
import sge.modelo.Estadoperiodoeap;
import sge.modelo.Estadoperiodofacultad;
import sge.modelo.Estadoperiodofilial;
import sge.modelo.Estrategia;
import sge.modelo.EstrategiaIndicador;
import sge.modelo.Filial;
import sge.modelo.Indicador;
import sge.modelo.Objetivoespecifico;
import sge.modelo.Periodo;
import sge.modelo.Periodometa;
import sge.modelo.Politicaupeu;
import sge.modelo.Temporada;
import sge.modelo.TemporadaEjeObjEstrategico;
import sge.modelo.Tipometa;

/**
 *
 * @author Edwin
 */
public class GestionEstrategicoDao extends DBConn{
    
    public List Listar_Politica_Upeu() {
        List reporte = new ArrayList();
        Politicaupeu to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM politicaupeu");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Politicaupeu();
                to.setIdpoliticasupeu(rs.getInt("idpoliticasupeu"));
                to.setMision(rs.getString("mision"));
                to.setVision(rs.getString("vision"));
                to.setEstado(rs.getString("estado"));
                to.setImagen(rs.getString("imagen"));
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
    
    public Politicaupeu Buscar_Politica_Upeu_Id(String idpoliticaupeu) {
        Politicaupeu p = new Politicaupeu();
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM politicaupeu WHERE idpoliticasupeu=?");
            ps.setInt(1, Integer.parseInt(idpoliticaupeu));
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setIdpoliticasupeu(rs.getInt("idpoliticasupeu"));
                p.setMision(rs.getString("mision"));
                p.setVision(rs.getString("vision"));
                p.setEstado(rs.getString("estado"));
                
            }
        } catch (Exception e) {
        }
        return p;
    }
   public void Actualizar_Datos_Politica_Upeu(Politicaupeu to, int idpoliticasupeu) {
        try {
            
            getConexionDb();
            ps = con.prepareStatement("update politicaupeu set mision=?, vision=?, estado=? WHERE idpoliticasupeu=?");
            ps.setString(1, to.getMision());
            ps.setString(2, to.getVision());
            ps.setString(3, to.getEstado());
            ps.setInt(4, idpoliticasupeu);
            ps.executeUpdate();

           
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
   
    public List Listar_Ejes_Estrategicos(int idtipoarea) {
        String subQry;
        if(idtipoarea==3){
        subQry=" AND t.idtipoarea='3' ";
        }else{
        subQry=" AND t.idtipoarea!='3' ";
        }
        List reporte = new ArrayList();
        Ejeestrategico to;
        try {
            getConexionDb();
            ps = con.prepareStatement(" SELECT e.*, t.nombre tipoarea FROM ejeestrategico e, tipoarea t WHERE e.idtipoarea=t.idtipoarea AND e.estado=1  "+subQry+"  ORDER BY e.idtipoarea ASC, CAST(e.codigo AS SIGNED) ASC  ");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Ejeestrategico();
                to.setIdejeestrategico(rs.getInt("idejeestrategico"));
                to.setNombre(rs.getString("nombre"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setEstado(rs.getInt("estado"));
                to.setObjetivoestrategico(rs.getString("objetivoestrategico"));
                to.setMapaestrategico(rs.getString("mapaestrategico"));
                to.setCodigo(rs.getString("codigo"));
                to.setTipoarea(rs.getString("tipoarea"));
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
    
    public Ejeestrategico buscarEjeEstrategicoId(String idEjeEstrategico) {
        Ejeestrategico x = new Ejeestrategico();
        try {
            getConexionDb();
            ps = con.prepareStatement("select * from ejeestrategico "
                    + "where idEjeEstrategico=? ");
            ps.setInt(1, Integer.parseInt(idEjeEstrategico));
            rs = ps.executeQuery();
            if (rs.next()) {
                x.setIdejeestrategico(rs.getInt("idEjeEstrategico"));
                x.setNombre(rs.getString("nombre"));
                x.setDescripcion(rs.getString("descripcion"));
                x.setEstado(rs.getInt("estado"));
                x.setObjetivoestrategico(rs.getString("objetivoestrategico"));
                x.setMapaestrategico(rs.getString("mapaestrategico"));
                x.setCodigo(rs.getString("codigo"));
            }
        } catch (Exception e) {
        }
        return x;
    }
    public int idEjeXSeleccionada(int  idTemporadaEje) {
        int id=0;
        try {
            getConexionDb();
            ps = con.prepareStatement(" SELECT idejeestrategico, nro FROM  temporadaejeestrategico WHERE IdTemporadaEjeEstrategico=? ");
            ps.setInt(1, idTemporadaEje);
            rs = ps.executeQuery();
            if (rs.next()) {
                id=rs.getInt("nro");
            }
        } catch (Exception e) {
        }
        return id;
    }
    
     public void ActualizarDatosEjeEstrategico(Ejeestrategico to, int idEjeEstrategico) {
        try {

            getConexionDb();
            ps = con.prepareStatement("update ejeestrategico set nombre=?, descripcion=?, objetivoestrategico=?, mapaestrategico=? WHERE idEjeEstrategico=?");
            ps.setString(1, to.getNombre());
            ps.setString(2, to.getDescripcion());
            ps.setString(3, to.getObjetivoestrategico());
            ps.setString(4, to.getMapaestrategico());
            ps.setInt(5, idEjeEstrategico);

            ps.executeUpdate();

            System.out.println("Actualizado");

        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
     
     // ------- fin de crud Eje Estrategico
    
    
    public List Listar_Ejes_Objetivos_Estrategicos_Temporada(String idtemporada, int idTipoarea) { 
        String subQry;
        if(idTipoarea==3){
        subQry=" AND eo.idtipoarea='3' ";
        }else{
        subQry=" AND eo.idtipoarea!='3' ";
        }        
        
        List reporte = new ArrayList();
        TemporadaEjeObjEstrategico to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT te.`IdTemporadaEjeEstrategico`,tem.`idTemporada`,tem.`inicio`,tem.`fin`,(te.`nro`) AS nrote ,eo.`idEjeEstrategico`,eo.`nombre`,eo.`descripcion`,eo.`objetivoestrategico`,eo.`mapaestrategico`,(eo.`codigo`)AS nroeo, (SELECT ta.nombre  FROM tipoarea ta WHERE ta.idtipoarea=eo.idtipoarea) AS nombrearea  FROM  temporadaejeestrategico te "
                    + "INNER JOIN ejeestrategico eo ON te.idEjeEstrategico = eo.idEjeEstrategico "
                    + "INNER JOIN temporada tem ON te.idTemporada = tem.idTemporada "
                    + "WHERE tem.idTemporada='"+idtemporada+"' "+subQry+" ORDER BY eo.idtipoarea ASC, CAST(eo.codigo AS SIGNED) ASC ");            
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new TemporadaEjeObjEstrategico();
                to.setIdTemporadaEjeEstrategico(rs.getInt("IdTemporadaEjeEstrategico"));
                to.setIdtemporada(rs.getInt("idTemporada"));
                to.setIdtemporada_inicio(rs.getString("inicio"));
                to.setIdtemporada_fin(rs.getString("fin"));
                to.setNro(rs.getInt("nrote"));
                to.setIdEjeEstrategico(rs.getInt("idEjeEstrategico"));
                to.setIdEjeEstrategico_nombre(rs.getString("nombre")+" ("+rs.getString("nombrearea")+")");
                to.setIdEjeEstrategico_descripcion(rs.getString("descripcion"));
                to.setIdEjeEstrategico_Objetivo(rs.getString("objetivoestrategico"));
                to.setIdEjeEstrategico_mapa(rs.getString("mapaestrategico"));
                to.setIdEjeEstrategico_nro(rs.getString("nroeo"));
                to.setNombreTipoArea(rs.getString("nombrearea"));
                reporte.add(to);
                }
        } catch (Exception e) {
            System.out.println("Error !!" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
    
    
    public int registro_Temporada_Eje_Obj_Estrategico_array(TemporadaEjeObjEstrategico to) {
        int r = 0;
        try {
            String[] edwin=to.getIdEjeEstrategicoArray();
            if ( edwin!= null) {
            for (int w = 0; w < edwin.length; w++) {
            
            getConexionDb();
            ps = con.prepareStatement("insert into temporadaejeestrategico(idTemporada, idEjeEstrategico) "
                    + "values (?, ?)");
            ps.setInt(1,to.getIdtemporada());
            ps.setString(2, edwin[w]);
         
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado Array!!!");
            }else{
                System.out.println("error !!"+r);
            }
            
            }
   }else{System.out.println("Selecciona por lo menos un dato !!! ---> | edwin calsin|");}
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
    
     
  
        
        public List Listar_Objetivo_Especifico() {
        List reporte = new ArrayList();
            Objetivoespecifico to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM objetivoespecifico");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Objetivoespecifico();
                to.setIdobjetivoespecifico(rs.getInt("idobjetivoespecifico"));
                to.setNombre(rs.getString("nombre"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setNro(rs.getString("nro"));
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
        
        
        public List Listar_Perido() {
        List reporte = new ArrayList();
            Periodo to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM periodo where estado=1");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Periodo();
                to.setIdperiodo(rs.getInt("idperiodo"));
                to.setPeriodo(rs.getString("periodo"));
                to.setEstado(rs.getString("estado"));
                to.setFechainicio(rs.getString("fechainicio"));
                to.setFechafin(rs.getString("fechafin"));
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
        
    /*    
         public int registro_coordinadorfacultad_array(Coordinadorfacultad to) {
        int r = 0;
        try {
            String[] edwin=to.getIdpersona();
            
            
           if ( edwin!= null) {
            for (int w = 0; w < edwin.length; w++) {
            
            getConexionDb();
            ps = con.prepareStatement("insert into coordinadorfacultad(idcoordinadorfacultad, idpersona,idperiodo,idfilial) "
                    + "values (?, ?, ?, ?)");
            ps.setInt(1, to.getIdcoordinadorfacultad());
            ps.setString(2, edwin[w]);
            ps.setInt(3, to.getIdperiodo());
            ps.setInt(4, to.getIdfilial());
         
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
         
         
    // Metodos Ajax  para los Indicadores
      */   
         
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
 
    public List<Indicador> listaIndicador(Periodometa id){        
    String sql= "  SELECT i.idindicador,i.nombre,i.descripcion,i.estado,li.nro ,li.instrumento,li.idestrategiaindicador, "+
                "  (CASE WHEN m.meta IS NULL THEN 0 ELSE m.meta END) AS meta FROM indicador i   "+
                "  INNER JOIN estrategiaindicador li ON li.idindicador=i.idindicador  "+
                "  INNER JOIN ejeestrategia ee ON ee.idejeestrategia=li.idejeestrategia   "+
                "  INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico "+ 
                "  INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1'   "+
                "  LEFT JOIN meta m ON li.idestrategiaindicador=m.idestrategiaindicador AND m.idperiodo=? "+
                "  ORDER BY li.nro ";        

    List<Indicador> Lista = new ArrayList<Indicador>(); 
      sge.modelo.Indicador Toto = null;

        try {
            getConexionDb();
            ps=con.prepareStatement(sql);
            if(id.getIdperiodometa()==null){
            ps.setInt(1,0);
            }else{
            ps.setInt(1,id.getIdperiodometa());
            }
            
            rs=ps.executeQuery();
        while (rs.next()) 
        {  System.err.println("---> While -->");          
           Toto  = new sge.modelo.Indicador();       
           Toto.setIdindicador(rs.getInt("idindicador"));
           Toto.setNombre(rs.getString("nombre"));
           Toto.setDescripcion(rs.getString("descripcion"));
           Toto.setEstado(rs.getInt("estado"));
           Toto.setInstrumento(rs.getString("instrumento"));
           Toto.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
           Toto.setNro(rs.getInt("idestrategiaindicador"));//numero
           Toto.setMeta(rs.getInt("meta"));
           Lista.add(Toto);
        }
        } catch (Exception e) {
        }
        finally{getCerrarConexion();}
        System.out.println("---> LISTA INDICADORES DAO --> $$ -> "+Lista.toArray().length);
    return Lista;
    }
    
    
    
     public List listar_indicador_periodo(String idperiodometa) { 
        List reporte = new ArrayList();
        Indicador to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT i.idindicador,i.nombre,i.descripcion,i.estado,li.nro ,li.instrumento,li.idestrategiaindicador," +
" (CASE WHEN m.meta IS NULL THEN 0 ELSE m.meta END) AS meta FROM indicador i " +
" INNER JOIN estrategiaindicador li ON li.idindicador=i.idindicador " +
" INNER JOIN ejeestrategia ee ON ee.idejeestrategia=li.idejeestrategia " +
" INNER JOIN temporadaejeestrategico te ON te.idtemporadaejeestrategico=ee.idtemporadaejeestrategico" +
" INNER JOIN temporada t ON t.idtemporada=te.idtemporada AND t.estado='1'" +
" LEFT JOIN meta m ON li.idestrategiaindicador=m.idestrategiaindicador AND m.idperiodo=?" +
" ORDER BY li.nro");
            ps.setString(1, idperiodometa);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Indicador();
                to.setIdindicador(rs.getInt("idindicador"));
                to.setNombre(rs.getString("nombre"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setEstado(rs.getInt("estado"));
                to.setInstrumento(rs.getString("instrumento"));
                to.setIdestrategiaindicador(rs.getInt("idestrategiaindicador"));
                to.setNro(rs.getInt("nro"));
                to.setMeta(rs.getInt("meta"));
                reporte.add(to);

            }
        } catch (Exception e) {
            System.out.println("Error !!" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
    
     
     // CRUD ESTRATEGIA 
         
     
      public List Listar_Estrategia(int idTipoarea, int idFlial) {
         String subQry;
        if(idTipoarea==3){
        subQry=" AND e.idtipoarea='3' ";
        }else{
        subQry=" AND e.idtipoarea!='3' ";
        }
          List reporte = new ArrayList();
        Estrategia to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT e.*, CONCAT(codigo,' ',nombre) AS codnombre FROM estrategia e WHERE e.idfilial="+idFlial+" "+subQry+" order by e.idfilial asc, e.idtipoarea asc , e.idEstrategia asc,  e.codigo asc ");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Estrategia();
                to.setIdestrategia(rs.getInt("idEstrategia"));
                to.setNombre(rs.getString("codnombre"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setNro(rs.getString("codigo"));
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
      
      public int RegistrarEstrategia(Estrategia to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into "
                    + "estrategia(nombre, descripcion, codigo,idtipoarea, idperspectiva, idfilial) "
                    + "values (?, ?, ?, ?, ?, ?)");
            ps.setString(1, to.getNombre());
            ps.setString(2, to.getDescripcion());
            ps.setString(3, to.getNro());
            ps.setInt(4, to.getIdareaunidad());
            ps.setInt(5, to.getIdperspectiva());
            ps.setInt(6, to.getIdfilial());
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r;
    }
      
      public Estrategia BuscarEstrategiaId(String idEstrategia) {
        Estrategia x = new Estrategia();
        try {
            getConexionDb();
            ps = con.prepareStatement("select * from estrategia "
                    + "where idEstrategia=? ");
            ps.setInt(1, Integer.parseInt(idEstrategia));
            rs = ps.executeQuery();
            if (rs.next()) {
                x.setIdestrategia(rs.getInt("idEstrategia"));
                x.setNombre(rs.getString("nombre"));
                x.setDescripcion(rs.getString("descripcion"));
                x.setNro(rs.getString("nro"));
            }
        } catch (Exception e) {
        }
        return x;
    }

      public void ActualizarDatosEstrategia(Estrategia to, int idEstrategia) {
        try {

            getConexionDb();
            ps = con.prepareStatement("update estrategia set nombre=?, descripcion=?,nro=? WHERE idEstrategia=?");
            ps.setString(1, to.getNombre());
            ps.setString(2, to.getDescripcion());
            ps.setString(3, to.getNro());
            ps.setInt(4, idEstrategia);

            ps.executeUpdate();

            System.out.println("Actualizado");

        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
      
      public void EliminaridEstrategia(int idEstrategia) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM estrategia WHERE idEstrategia=? ");
            ps.setInt(1, idEstrategia);
            if (ps.executeUpdate() == 1) {
                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
      
      // FIN DE CRUD ESTRATEGIA
      
    
      public List Listar_Indicador(int idTipoarea, int idFilial, int idEjeEstrategico) {
        String subQry;
        String subQry2;
        if(idTipoarea==3){
        subQry=" AND idtipoarea='3' ";
        }else{
        subQry=" AND idtipoarea!='3' ";
        }
        if(idEjeEstrategico!=0){
        subQry2=" and idejeestrategico="+idEjeEstrategico+" ";
        }else{
        subQry2="";
        }
        String sql="";
        List reporte = new ArrayList();
        Indicador to;
        try {
            getConexionDb();
            sql="SELECT i.*,tm.*,  CONCAT(codigo,' ',nombre) AS codnombre FROM indicador i INNER JOIN tipometaindicador tm ON i.idTipoMeta=tm.idTipoMeta WHERE idfilial="+idFilial+"  "+subQry+" "+subQry2+" ORDER BY i.idindicador ASC, i.codigo ASC  ";
            ps = con.prepareStatement(sql);
            System.out.println("Ver Consultar: "+sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Indicador();
                to.setIdindicador(rs.getInt("idIndicador"));
                to.setNombre(rs.getString("codnombre"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setEstado(rs.getInt("estado"));
                to.setCodigo(rs.getString("codigo"));
                to.setIdtipometa(rs.getInt("idTipoMeta"));
                to.setIdtipometa_nombre(rs.getString("tipo"));
                
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
      
      
      public List listaIndicadoresEje(int idEje) {
        List reporte = new ArrayList();
        Indicador to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM indicador i INNER JOIN tipometaindicador tm ON i.idTipoMeta=tm.idTipoMeta AND idEjeEstrategico=? ORDER BY i.codigo ASC  " );
            ps.setInt(1, idEje);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Indicador();
                to.setIdindicador(rs.getInt("idIndicador"));
                to.setNombre(rs.getString("nombre"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setEstado(rs.getInt("estado"));
                to.setNro(rs.getInt("codigo"));
                to.setIdtipometa(rs.getInt("idTipoMeta"));
                to.setIdtipometa_nombre(rs.getString("tipo"));
                
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
      
      public List Listar_Tipo_Meta() {
        List reporte = new ArrayList();
          Tipometa to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM tipometaindicador");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Tipometa();
                to.setIdtipometa(rs.getInt("idTipoMeta"));
                to.setTipo(rs.getString("tipo"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setEstado(rs.getInt("estado"));
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
    
       public int Registrar_Indicador(Indicador to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into "
                    + "indicador (nombre, descripcion, estado, codigo, idTipoMeta, idEjeEstrategico, idtipoarea, idfilial ) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, to.getNombre());
            ps.setString(2, to.getDescripcion());
            ps.setInt(3, to.getEstado());
            ps.setString(4, to.getCodigo());
            ps.setInt(5, to.getIdtipometa());
            ps.setInt(6, to.getIdejeestrategico());
            ps.setInt(7, to.getIdtipoarea());
            ps.setInt(8, to.getIdfilial());
            
            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
       
       
       public Indicador BuscarIndicadorId(String idIndicador) {
        Indicador in = new Indicador();
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM indicador i INNER JOIN tipometaindicador tm ON i.idTipoMeta=tm.idTipoMeta "
                    + "where i.idIndicador=? ");
            ps.setInt(1, Integer.parseInt(idIndicador));
            rs = ps.executeQuery();
            if (rs.next()) {
                in.setIdindicador(rs.getInt("idIndicador"));
                in.setNombre(rs.getString("nombre"));
                in.setDescripcion(rs.getString("descripcion"));
                in.setEstado(rs.getInt("estado"));
                in.setNro(rs.getInt("nro"));
                in.setIdtipometa(rs.getInt("idTipoMeta"));
                in.setIdtipometa_nombre(rs.getString("tipo"));
                in.setInstrumento(rs.getString("instrumento"));
            }
        } catch (Exception e) {
        }
        return in;
    }
       
       
       public void ActualizarDatosIndicador(Indicador to, int idIndicador) {
        try {

            getConexionDb();
            ps = con.prepareStatement("update indicador set nombre=?, descripcion=?, estado=?, nro=?, idTipoMeta=?,instrumento=? WHERE idIndicador=?");
            ps.setString(1, to.getNombre());
            ps.setString(2, to.getDescripcion());
            ps.setInt(3, to.getEstado());
            ps.setInt(4, to.getNro());
            ps.setInt(5, to.getIdtipometa());
            ps.setString(6, to.getInstrumento());
            ps.setInt(7, idIndicador);

            ps.executeUpdate();

            System.out.println("Actualizado");

        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
       
       
       
       // ESTADO DE PERIODOS Y APERTURA
       
        public List Listar_Temporada(){
        List reporte = new ArrayList();
            Temporada to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM temporada ORDER BY idtemporada DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Temporada();
                to.setIdtemporada(rs.getInt("idtemporada"));
                to.setInicio(rs.getString("inicio"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setFin(rs.getString("fin"));
                to.setEstado(rs.getString("estado"));
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
     
        
        public List Listar_Rango_Temporada(String idtemporada) { 
        List reporte = new ArrayList();
        Temporada to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT idtemporada,(YEAR(inicio))AS v_inicio, (YEAR(fin))AS v_fin FROM temporada WHERE idtemporada=? AND estado=1;");
            ps.setString(1, idtemporada);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Temporada();
                to.setIdtemporada(rs.getInt("idtemporada"));
                to.setInicio(rs.getString("v_inicio"));
                to.setFin(rs.getString("v_fin"));
                reporte.add(to);
                }
        } catch (Exception e) {
            System.out.println("Error !!" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
     
        
        public List Listar_Temporada_Print(){
        List reporte = new ArrayList();
            Temporada to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM temporada where estado='1' ORDER BY idtemporada DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Temporada();
                to.setIdtemporada(rs.getInt("idtemporada"));
                to.setInicio(rs.getString("inicio"));
                to.setDescripcion(rs.getString("descripcion"));
                to.setFin(rs.getString("fin"));
                to.setEstado(rs.getString("estado"));
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
        
        
    public void Desactivar_Temporada(int idTemporada) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update temporada set estado='0' where idTemporada=?");
            ps.setInt(1, idTemporada);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo la Temporada Nooo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
    public void Activar_Temporada(int idTemporada) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update temporada set estado='1' where idTemporada=?");
            ps.setInt(1, idTemporada);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Activo la Temporada Yess");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
    
    public int Registrar_Temporada(Temporada to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into "
                    + "temporada (inicio,descripcion,fin,estado) "
                    + "values (?, ?, ?, ?)");
            ps.setString(1, to.getInicio());
            ps.setString(2, to.getDescripcion());
            ps.setString(3, to.getFin());
            ps.setString(4, to.getEstado());
            
            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
    
    public Temporada BuscarTemporadaId(String idTemporada) {
        Temporada t = new Temporada();
        try {
            getConexionDb();
            ps = con.prepareStatement("select * from temporada "
                    + "where idTemporada=? ");
            ps.setInt(1, Integer.parseInt(idTemporada));
            rs = ps.executeQuery();
            if (rs.next()) {
                t.setIdtemporada(rs.getInt("idTemporada"));
                t.setInicio(rs.getString("inicio"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setFin(rs.getString("fin"));
                t.setEstado(rs.getString("estado"));
            }
        } catch (Exception e) {
        }
        return t;
    }
    
    public void ActualizarDatosTemporada(Temporada to, int idTemporada) {
        try {

            getConexionDb();
            ps = con.prepareStatement("update temporada set inicio=?, descripcion=?, fin=?, estado=? WHERE idTemporada=?");
            ps.setString(1, to.getInicio());
            ps.setString(2, to.getDescripcion());
            ps.setString(3, to.getFin());
            ps.setString(4, to.getEstado());
            ps.setInt(5, idTemporada);

            ps.executeUpdate();

            System.out.println("Actualizado");

        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
     
    public void EliminaridTemporada(int idTemporada) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM temporada WHERE idTemporada=? ");
            ps.setInt(1, idTemporada);
            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }

    // DESARROLLO DE PERIODO META
    
     public Temporada GetIdTemporada(String idtemporada) {
        Temporada get = new Temporada();
        try {
            getConexionDb();
            ps = con.prepareStatement("select * from temporada "
                    + "where idtemporada=? ");
            ps.setInt(1, Integer.parseInt(idtemporada));
            rs = ps.executeQuery();
            if (rs.next()) {
                get.setIdtemporada(rs.getInt("idtemporada"));
                get.setInicio(rs.getString("inicio"));
                get.setDescripcion(rs.getString("descripcion"));
                get.setFin(rs.getString("fin"));
                get.setEstado(rs.getString("estado"));
            }
        } catch (Exception e) {
        }
        return get;
    }
    
     public List Listar_PeriodoMeta_Temporada(String idtemporada) { 
        List reporte = new ArrayList();
        Periodometa to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM periodo where idperiodo=? order by idperiodo desc");
            ps.setString(1, idtemporada);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Periodometa();
                to.setIdperiodometa(rs.getInt("idperiodo"));
                to.setPeriodo(rs.getString("periodo"));
                to.setIdtemporada(rs.getInt("idtemporada"));
                to.setEstado(rs.getInt("estado"));
                reporte.add(to);
                }
        } catch (Exception e) {
            System.out.println("Error !!" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
     
     public void EliminarPeriodoMeta(int idperiodometa) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM periodo WHERE idperiodo=? ");
            ps.setInt(1, idperiodometa);
            if (ps.executeUpdate() == 1) {
            System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
     
     
     
    public List Listar_PeriodoMeta(){
        List reporte = new ArrayList();
            Periodometa to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM periodo where estado='1'");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Periodometa();
                to.setIdperiodometa(rs.getInt("idperiodometa"));
                to.setPeriodo(rs.getString("periodo"));
                to.setIdtemporada(rs.getInt("idtemporada"));
                to.setEstado(rs.getInt("estado"));
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
        
        
    public void Desactivar_Periodometa(int idperiodometa) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update periodo set estado='0' where idperiodo=?");
            ps.setInt(1, idperiodometa);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo la Temporada Nooo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
    public void Activar_Periodometa(int idperiodometa) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update periodo set estado='1' where idperiodo=?");
            ps.setInt(1, idperiodometa);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Activo la Temporada Yess");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
    
     public int Registrar_Periodo_Meta(Periodometa to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into "
                    + "periodo (periodo,estado, idtemporada) "
                    + "values (?, ?, ?)");
            ps.setString(1, to.getPeriodo());            
            ps.setInt(2, to.getEstado());
            ps.setInt(3, to.getIdtemporada());
            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
       
     
     public Periodometa BuscarPeriodoMetaId(String idperiodometa) {
        Periodometa per = new Periodometa();
        try {
            getConexionDb();
            ps = con.prepareStatement("select * from periodo "
                    + "where idperiodo=? ");
            ps.setInt(1, Integer.parseInt(idperiodometa));
            rs = ps.executeQuery();
            if (rs.next()) {
                per.setIdperiodometa(rs.getInt("idperiodo"));
                per.setPeriodo(rs.getString("periodo"));
                per.setIdtemporada(rs.getInt("idtemporada"));
                per.setEstado(rs.getInt("estado"));
            }
        } catch (Exception e) {
        }
        return per;
    }
     public Indicador listarEjeIndicadorCodigo(int idIndicador) {
        Indicador per = new Indicador();
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM indicador WHERE idindicador=?  ");
            ps.setInt(1, idIndicador);
            rs = ps.executeQuery();
            if (rs.next()) {
                per.setIdindicador(rs.getInt("idindicador"));
                per.setCodigo(rs.getString("codigo"));
            }
        } catch (Exception e) {
        }
        return per;
    }
     
     public void ActualizarDatosPeriodometa(Periodometa to, int idperiodometa) {
        try {

            getConexionDb();
            ps = con.prepareStatement("update periodo set periodo=?, idtemporada=?, estado=? WHERE idperiodo=?");
            ps.setString(1, to.getPeriodo());
            ps.setInt(2, to.getIdtemporada());
            ps.setInt(3, to.getEstado());
            ps.setInt(4, idperiodometa);
            ps.executeUpdate();
            System.out.println("Actualizado");

        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
     
     
     
     
     
     
     
     // REGISTRO DE ESTADO PERIODO FILIAL
     
     
     
     public List Listar_Estado_Periodo_Filial(){
        List reporte = new ArrayList();
            Estadoperiodofilial to;
        try {getConexionDb();
            ps = con.prepareStatement("SELECT * FROM estadoperiodofilial epf "
                    + "INNER JOIN periodo pm ON epf.idperiodo=pm.idperiodo "
                    + "INNER JOIN filial f ON epf.idFilial=f.idFilial");
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Estadoperiodofilial();
                to.setIdestadoperiodofilial(rs.getInt("idestadoperiodofilial"));
                to.setIdperiodometa(rs.getString("periodo"));
                to.setEstadometa(rs.getString("estadometa"));
                to.setEstadoavance(rs.getString("estadoavance"));
                to.setEstadopoa(rs.getString("estadopoa"));
                to.setEstadopm(rs.getString("estadopm"));
                
                to.setIdFilial(rs.getString("direccion"));
                reporte.add(to);
                System.out.println("Reportado..!");}
        } catch (Exception e) {
            System.out.println("Error en Reporte ..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;
    }
     public Estrategia listarEstrategiaCodigo(int idEstrategia){    
            Estrategia to=null;
        try {getConexionDb();
            ps = con.prepareStatement(" SELECT * FROM estrategia WHERE idEstrategia="+idEstrategia+" ");
            rs = ps.executeQuery();
            if (rs.next()) {
                to = new Estrategia();
                to.setIdestrategia(rs.getInt("idestrategia"));
                to.setNro(rs.getString("codigo"));    
                
                System.out.println("Reportado..!");}
        } catch (Exception e) {
            System.out.println("Error en Reporte ..." + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return to;
    }
     
        public List Listar_Filial() {
        List reporte = new ArrayList();
        Filial to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM filial f "
                    + "INNER JOIN institucion i ON f.idinstitucion = i.idinstitucion");
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
        
        
        public int Registrar_Eje_Objetivo_Estrategico(Ejeestrategico to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into "
                    + "ejeestrategico(nombre,descripcion,objetivoestrategico,mapaestrategico) "
                    + "values (?, ?, ?, ?)");
            ps.setString(1, to.getNombre());
            ps.setString(2, to.getDescripcion());
            ps.setString(3, to.getObjetivoestrategico());
            ps.setString(4, to.getMapaestrategico());
            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Se Inserto ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
        }
        
        public int Registrar_Eje_Objetivo_Estrategico_Temporada(TemporadaEjeObjEstrategico to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into temporadaejeestrategico(idtemporada,idEjeEstrategico) "
                    + "values (?, ?)");
            ps.setInt(1, to.getIdtemporada());
            ps.setInt(2, to.getIdEjeEstrategico());
            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Se Inserto ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
        }
        
        
        public void Eliminar_Eje_Objetivo_Estrategico_Temporada(int IdTemporadaEjeEstrategico) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM temporadaejeestrategico WHERE IdTemporadaEjeEstrategico=? ");
            ps.setInt(1, IdTemporadaEjeEstrategico);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
        
        public void Eliminar_Eje_Objetivo_Estrategico(int idEjeEstrategico) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM ejeestrategico WHERE idEjeEstrategico=? ");
            ps.setInt(1, idEjeEstrategico);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }


        
        public List Listar_Estrategias_eje(String idtemporadaejeestrategico, int idTipoarea, int idFilial) { 
        String subQry;
        if(idTipoarea==3){
        subQry=" AND es.idtipoarea='3' ";
        }else{
        subQry=" AND es.idtipoarea!='3' ";
        }        
            List reporte = new ArrayList();
            EjeEstrategia to;
            String sql="SELECT ejest.`idEjeEstrategia`,ejest.`idestrategia`,CONCAT(es.`codigo`,' ',es.`nombre`)AS estrategia,ejest.nro,es.`descripcion`,ejest.`idtemporadaejeestrategico`,ejestra.`nombre`,ejestra.`objetivoestrategico` FROM `ejeestrategia` ejest\n" +
"INNER JOIN `estrategia` es ON ejest.idestrategia = es.idestrategia\n" +
"INNER JOIN `temporadaejeestrategico` tem ON ejest.idtemporadaejeestrategico = tem.idtemporadaejeestrategico\n" +
"INNER JOIN `ejeestrategico` ejestra ON tem.idEjeEstrategico = ejestra.idEjeEstrategico\n" +
"WHERE ejest.`idtemporadaejeestrategico`="+idtemporadaejeestrategico+" AND es.idfilial="+idFilial+" "+subQry+" ORDER BY CAST(es.codigo AS SIGNED) ASC  ";
                    
                    try {
            getConexionDb();
            ps = con.prepareStatement(sql);            
            System.out.println("CONSULTA: "+sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new EjeEstrategia();
                to.setIdEjeEstrategia(rs.getInt("idEjeEstrategia"));
                to.setIdestrategia(rs.getInt("idestrategia"));
                to.setNro(rs.getString("nro"));
                to.setIdestrategia_nombre(rs.getString("estrategia"));
                to.setIdestrategia_descripcion(rs.getString("descripcion"));
                to.setIdtemporadaejeestrategico(rs.getInt("idtemporadaejeestrategico"));
                to.setIdtemporadaejenombre(rs.getString("nombre"));
                to.setIdtemporadaejeobjetivo(rs.getString("objetivoestrategico"));
                
                reporte.add(to);
                }
        } catch (Exception e) {
            System.out.println("Error !!" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
        
        
        public int Registrar_Eje_Estrategia(EjeEstrategia to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into ejeestrategia(idestrategia,nro,idtemporadaejeestrategico) "
                    + "values (?, ?, ?)");
            ps.setInt(1, to.getIdestrategia());
            ps.setString(2, to.getNro());
            ps.setInt(3, to.getIdtemporadaejeestrategico());
            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Se Inserto ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
        }
        
        
        
        // FIN DE EJE ESTRATEGIA
        
        
        public void Eliminar_Eje_Estrategia(int idEjeEstrategia) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM ejeestrategia WHERE idEjeEstrategia=? ");
            ps.setInt(1, idEjeEstrategia);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
        
        
        public List Listar_Estrategia_Indicador(String idEjeEstrategia, int idTipoarea, int idFilial) { 
        String subQry;
        String sql="";
        if(idTipoarea==3){
        subQry=" AND ind.idtipoarea='3' ";
        }else{
        subQry=" AND ind.idtipoarea!='3' ";
        }        
        List reporte = new ArrayList();
            EstrategiaIndicador to;
        try {
            getConexionDb();
            sql="SELECT ei.`idestrategiaIndicador`,ei.`instrumento`,ei.`nro`,ei.`idIndicador`,(ind.`nombre`)AS indicador ,ind.`idTipoMeta`,tmi.`tipo`,ei.`idEjeEstrategia`,estra.`nombre` FROM `estrategiaindicador` ei\n" +
"INNER JOIN `indicador` ind ON ei.idIndicador = ind.idIndicador\n" +
"INNER JOIN `tipometaindicador` tmi ON ind.idTipoMeta = tmi.idTipoMeta\n" +
"INNER JOIN `ejeestrategia` ees ON ei.idEjeEstrategia = ees.idEjeEstrategia\n" +
"INNER JOIN `estrategia` estra ON ees.`idestrategia`=estra.`idEstrategia`\n" +
"WHERE ei.`idEjeEstrategia`="+idEjeEstrategia+" AND ind.idfilial="+idFilial+"  "+subQry+" ";
            
            ps = con.prepareStatement(sql);  
            System.out.println("DDDDD: "+sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new EstrategiaIndicador();
                to.setIdestrategiaIndicador(rs.getInt("IdestrategiaIndicador"));
                to.setInstrumento(rs.getString("instrumento"));
                to.setNro(rs.getString("nro"));
                to.setIdIndicador(rs.getInt("idIndicador"));
                to.setIdIndicador_nombre(rs.getString("indicador"));
                to.setIdIndicador_tipo(rs.getString("tipo"));
                to.setIdEjeEstrategia(rs.getInt("idEjeEstrategia"));
                to.setIdEjeEstrategia_nombre(rs.getString("nombre"));
                
                reporte.add(to);
                }
            
        } catch (Exception e) {
            System.out.println("Error !!" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
        
        public int Registrar_Estrategia_Indicador(EstrategiaIndicador to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into estrategiaindicador(instrumento,nro,idIndicador,idEjeEstrategia) "
                    + "values (?, ?, ?, ?)");
            ps.setString(1, to.getInstrumento());
            ps.setString(2, to.getNro());
            ps.setInt(3, to.getIdIndicador());
            ps.setInt(4, to.getIdEjeEstrategia());
            if (ps.executeUpdate() == 1) {
                r = 1;
                
                System.out.println("Se Inserto Estrategia Indicador");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
        }
     
        public void Eliminar_Estrategia_Indicador(int idestrategiaIndicador) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM estrategiaindicador WHERE idestrategiaIndicador=? ");
            ps.setInt(1, idestrategiaIndicador);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
        public void Eliminar_Indicador(int idIndicador) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM indicador WHERE idIndicador=? ");
            ps.setInt(1, idIndicador);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
        
        
        
        
        
        
        
        // ESTADOS DE PERIODOS  APERTURAS POR FECHAS :::::::::::::::::::::::::::::::::::::::
        
        
        public List Listar_Estado_Periodo_Filial(String idperiodo, String idFilial) { 
        List reporte = new ArrayList();
            Estadoperiodofilial to;
        try {
            getConexionDb();
            ps = con.prepareStatement(" SELECT epf.`idestadoperiodofilial`,epf.`idperiodo`,pm.`periodo`,epf.`estadometa`,epf.`estadoavance`,epf.`estadopoa`,epf.`estadopm`,epf.`idFilial`,f.`direccion` "
                    + " FROM estadoperiodofilial epf INNER JOIN filial f ON epf.idFilial = f.idFilial "
                    + " INNER JOIN periodo pm ON epf.idperiodo = pm.idperiodo "
                    + " WHERE epf.`idperiodo`=? AND epf.`idFilial`=? ");
            ps.setString(1, idperiodo);
            ps.setString(2, idFilial);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Estadoperiodofilial();
                to.setIdestadoperiodofilial(rs.getInt("idestadoperiodofilial"));
                to.setIdperiodometa(rs.getString("idperiodo"));
                to.setIdperiodometa_nombre(rs.getString("periodo"));
                to.setEstadometa(rs.getString("estadometa"));
                to.setEstadoavance(rs.getString("estadoavance"));
                to.setEstadopoa(rs.getString("estadopoa"));
                to.setEstadopm(rs.getString("estadopm"));
                to.setIdFilial(rs.getString("idFilial"));
                to.setIdFilial_nombre(rs.getString("direccion"));
                reporte.add(to);
                }
        } catch (Exception e) {
            System.out.println("Error !!" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
        
        public int InsertarEstadoPeriodoFilial(Estadoperiodofilial to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into "
                    + "estadoperiodofilial(idperiodo, estadometa, estadoavance, idFilial) "
                    + "values (?, ?, ?, ?)");
            ps.setString(1, to.getIdperiodometa());
            ps.setString(2, to.getEstadometa());
            ps.setString(3, to.getEstadoavance());
            ps.setString(4, to.getIdFilial());
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
     
        
        
        
        public void Aperturar_Estado_Meta_Filial(int idestadoperiodofilial) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofilial set estadometa='1' where idestadoperiodofilial=?");
            ps.setInt(1, idestadoperiodofilial);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void Cerrar_Estado_Meta_Filial(int idestadoperiodofilial) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofilial set estadometa='0' where idestadoperiodofilial=?");
            ps.setInt(1, idestadoperiodofilial);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        
        public void Aperturar_Estado_Avance_Filial(int idestadoperiodofilial) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofilial set estadoavance='1' where idestadoperiodofilial=?");
            ps.setInt(1, idestadoperiodofilial);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void Cerrar_Estado_Avance_Filial(int idestadoperiodofilial) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofilial set estadoavance='0' where idestadoperiodofilial=?");
            ps.setInt(1, idestadoperiodofilial);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void EliminarEstadoPeriodoFilialAper(int idestadoperiodofilial) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM estadoperiodofilial WHERE idestadoperiodofilial=? ");
            ps.setInt(1, idestadoperiodofilial);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Elimino");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        /*inicio POA Filial*/
        public void aperturarPOAestadoFilial(int idestadoperiodofilial) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofilial set estadopoa='1' where idestadoperiodofilial=?");
            ps.setInt(1, idestadoperiodofilial);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Activo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void cerrarPOAestadoFilial(int idestadoperiodofilial) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofilial set estadopoa='0' where idestadoperiodofilial=?");
            ps.setInt(1, idestadoperiodofilial);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }        
        /*Fin POA Filial*/
        
        /*Inicio PM Filial*/
        public void aperturarPMestadoFilial(int idestadoperiodofilial) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofilial set estadopm='1' where idestadoperiodofilial=?");
            ps.setInt(1, idestadoperiodofilial);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Activo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void cerrarPMestadoFilial(int idestadoperiodofilial) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofilial set estadopm='0' where idestadoperiodofilial=?");
            ps.setInt(1, idestadoperiodofilial);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }        
        
        /*Fin PM Filial*/
        
        
        
        // ESTADO PERIODO FACULTAD
        
        
        public List Listar_Estado_Periodo_Facultad(String idperiodometa, String idFilialfacultad) { 
        List reporte = new ArrayList();
            Estadoperiodofacultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement(" SELECT epf.`idestadoperiodofacultad`,epf.`idperiodo`,pm.`periodo`,epf.`estadometa`,epf.`estadoavance`,epf.`estadopoa`,epf.`estadopm`,epf.`idFilialfacultad`,fac.nombre "
                    + " FROM `estadoperiodofacultad`  epf INNER JOIN periodo pm ON epf.idperiodo = pm.idperiodo "
                    + " INNER JOIN `filialfacultad` ff ON epf.idFilialfacultad = ff.idFilialfacultad "
                    + " INNER JOIN `facultad` fac ON ff.idfacultad = fac.idfacultad "
                    + " WHERE epf.`idperiodo`=? AND epf.`idFilialfacultad`=? ");
            ps.setString(1, idperiodometa);
            ps.setString(2, idFilialfacultad);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Estadoperiodofacultad();
                to.setIdestadoperiodofacultad(rs.getInt("idestadoperiodofacultad"));
                to.setIdperiodometa(rs.getString("idperiodo"));
                to.setIdperiodometa_nombre(rs.getString("periodo"));
                to.setEstadometa(rs.getString("estadometa"));
                to.setEstadoavance(rs.getString("estadoavance"));
                to.setIdFilialfacultad(rs.getString("idFilialfacultad"));
                to.setIdFilialfacultad_nombre(rs.getString("nombre"));
                to.setEstadopoa(rs.getString("estadopoa"));
                to.setEstadopm(rs.getString("estadopm"));
                reporte.add(to);
                }
        } catch (Exception e) {
            System.out.println("Error !!" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
        
    
        public int InsertarEstadoPeriodoFacultad(Estadoperiodofacultad to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into "
                    + "estadoperiodofacultad(idperiodo, estadometa, estadoavance, idFilialfacultad) "
                    + "values (?, ?, ?, ?)");
            ps.setString(1, to.getIdperiodometa());
            ps.setString(2, to.getEstadometa());
            ps.setString(3, to.getEstadoavance());
            ps.setString(4, to.getIdFilialfacultad());
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
        }
     
        
        public void Aperturar_Estado_Meta_Facultad(int idestadoperiodofacultad) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofacultad set estadometa='1' where idestadoperiodofacultad=?");
            ps.setInt(1, idestadoperiodofacultad);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void Cerrar_Estado_Meta_Facultad(int idestadoperiodofacultad) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofacultad set estadometa='0' where idestadoperiodofacultad=?");
            ps.setInt(1, idestadoperiodofacultad);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        
        public void Aperturar_Estado_Avance_Facultad(int idestadoperiodofacultad) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofacultad set estadoavance='1' where idestadoperiodofacultad=?");
            ps.setInt(1, idestadoperiodofacultad);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void Cerrar_Estado_Avance_Facultad(int idestadoperiodofacultad) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofacultad set estadoavance='0' where idestadoperiodofacultad=?");
            ps.setInt(1, idestadoperiodofacultad);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void EliminarEstadoPeriodoFacultadAper(int idestadoperiodofacultad) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM estadoperiodofacultad WHERE idestadoperiodofacultad=? ");
            ps.setInt(1, idestadoperiodofacultad);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Elimino");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
     /*inicio POA Facultad*/   
        public void aperturarPOAestadoFacultad(int idestadoperiodofacultad) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofacultad set estadopoa='1' where idestadoperiodofacultad=?");
            ps.setInt(1, idestadoperiodofacultad);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Activo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void cerrarPOAestadoFacultad(int idestadoperiodofacultad) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofacultad set estadopoa='0' where idestadoperiodofacultad=?");
            ps.setInt(1, idestadoperiodofacultad);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }        
     /*Fin POA Facultad*/ 
        
    /*Inicio Plan Mejora Facultad*/
        public void aperturarPMestadoFacultad(int idestadoperiodofacultad) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofacultad set estadopm='1' where idestadoperiodofacultad=?");
            ps.setInt(1, idestadoperiodofacultad);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Activo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void cerrarPMestadoFacultad(int idestadoperiodofacultad) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodofacultad set estadopm='0' where idestadoperiodofacultad=?");
            ps.setInt(1, idestadoperiodofacultad);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }         
    /*Fin Plan Mejora Facultad*/        
        
        public EstrategiaIndicador BuscarEstrategiaIndicadorId(String idestrategiaIndicador) {
        EstrategiaIndicador alumno = new EstrategiaIndicador();
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT ei.`idestrategiaIndicador`,ei.`instrumento`,ei.`nro`,ei.`idIndicador`,ind.`nombre`,ind.`idTipoMeta`,tmi.`tipo`,ei.`idEjeEstrategia`,est.`idestrategia` FROM `estrategiaindicador` ei\n" +
"INNER JOIN `indicador` ind ON ei.idIndicador = ind.idIndicador\n" +
"INNER JOIN `tipometaindicador` tmi ON ind.idTipoMeta = tmi.idTipoMeta\n" +
"INNER JOIN `ejeestrategia` est ON ei.idEjeEstrategia = est.idEjeEstrategia\n" +
"WHERE ei.`idestrategiaIndicador`=?");
            ps.setInt(1, Integer.parseInt(idestrategiaIndicador));
            rs = ps.executeQuery();
            if (rs.next()) {
                alumno.setIdestrategiaIndicador(rs.getInt("idestrategiaIndicador"));
                alumno.setInstrumento(rs.getString("instrumento"));
                alumno.setNro(rs.getString("nro"));
                alumno.setIdIndicador(rs.getInt("idIndicador"));
                alumno.setIdIndicador_nombre(rs.getString("nombre"));
                alumno.setIdIndicador_tipo(rs.getString("tipo"));
                alumno.setIdEjeEstrategia(rs.getInt("idEjeEstrategia"));
            }
        } catch (Exception e) {
        }
        return alumno;
    }
        
        public List Listar_Actividad_Indicador(String idestrategiaIndicador) { 
        List reporte = new ArrayList();
            Actividadindicador to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM actividad_indicador WHERE idestrategiaIndicador=?");
            ps.setString(1, idestrategiaIndicador);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Actividadindicador();
                to.setIdActividadIndicador(rs.getInt("idActividadIndicador"));
                to.setNro(rs.getString("nro"));
                to.setAccion(rs.getString("accion"));
                to.setCantidad(rs.getString("cantidad"));
                to.setPresupuesto(rs.getString("presupuesto"));
                to.setRubro(rs.getString("rubro"));
                to.setEvidencia(rs.getString("evidencia"));
                to.setEnero(rs.getInt("enero"));
                to.setFebrero(rs.getInt("febrero"));
                to.setMarzo(rs.getInt("marzo"));
                to.setAbril(rs.getInt("abril"));
                to.setMayo(rs.getInt("mayo"));
                to.setJunio(rs.getInt("junio"));
                to.setJulio(rs.getInt("julio"));
                to.setAgosto(rs.getInt("agosto"));
                to.setSetiembre(rs.getInt("setiembre"));
                to.setOctubre(rs.getInt("octubre"));
                to.setNoviembre(rs.getInt("noviembre"));
                to.setDiciembre(rs.getInt("diciembre"));
                to.setIdestrategiaIndicador(rs.getInt("idestrategiaIndicador"));
                reporte.add(to);
                }
        } catch (Exception e) {
            System.out.println("Error !!" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
        
   
        public int InsertarActividadindicadorSI(Actividadindicador to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement("insert into "
                    + "actividad_indicador(nro, accion, cantidad, presupuesto, rubro, evidencia, febrero, marzo, abril, mayo, junio, julio, agosto, setiembre, octubre, noviembre, diciembre, enero, idestrategiaIndicador) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, to.getNro());
            ps.setString(2, to.getAccion());
            ps.setString(3, to.getCantidad());
            ps.setString(4, to.getPresupuesto());
            ps.setString(5, to.getRubro());
            ps.setString(6, to.getEvidencia());
            ps.setInt(7, to.getFebrero());
            ps.setInt(8, to.getMarzo());
            ps.setInt(9, to.getAbril());
            ps.setInt(10, to.getMayo());
            ps.setInt(11, to.getJunio());
            ps.setInt(12, to.getJulio());
            ps.setInt(13, to.getAgosto());
            ps.setInt(14, to.getSetiembre());
            ps.setInt(15, to.getOctubre());
            ps.setInt(16, to.getNoviembre());
            ps.setInt(17, to.getDiciembre());
            ps.setInt(18, to.getEnero());
            ps.setInt(19, to.getIdestrategiaIndicador());
            
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
        
        public void EliminarActividadindicadorSI(int idActividadIndicador) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM actividad_indicador WHERE idActividadIndicador=?");
            ps.setInt(1, idActividadIndicador);

            if (ps.executeUpdate() == 1) {

                System.out.println(" Eliminado");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
    }
        
        
        public List Listar_ComboEAPFacultad(String idfilialfacultad) { 
        List reporte = new ArrayList();
            Eapfacultad to;
        try {
            getConexionDb();
            ps = con.prepareStatement("SELECT * FROM `eapfacultad` ef\n" +
"INNER JOIN eap e ON ef.`ideap`=e.`idEap`\n" +
"WHERE ef.`idfilialfacultad`=?");
            ps.setString(1, idfilialfacultad);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Eapfacultad();
                to.setIdeapfacultad(rs.getInt("IdEapFacultad"));
                to.setIdfilialfacultad(rs.getInt("idfilialfacultad"));
                to.setIdeap(rs.getInt("ideap"));
                to.setIdeap_name(rs.getString("nombre"));
                to.setIdcoordinadoreap(rs.getInt("idcoordinadoreap"));
                reporte.add(to);
                }
        } catch (Exception e) {
            System.out.println("Error !!" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

        }
        
        
        // ESTADO PERIODO EAP ---> Especificamentte a una eap
        
        public List Listar_Estado_Periodo_EAP(String idperiodometa, String idEapFacultad) { 
        List reporte = new ArrayList();
            Estadoperiodoeap to;
        try {
            getConexionDb();
            ps = con.prepareStatement(" SELECT ep.idestadoperiodoeap,ep.idperiodo,pm.periodo ,ep.estadometa,ep.estadoavance,ep.estadopoa,ep.estadopm,ep.idEapFacultad,e.nombre "
                    + " FROM estadoperiodoeap ep INNER JOIN periodo pm ON ep.idperiodo=pm.idperiodo "
                    + " INNER JOIN eapfacultad ef ON ep.idEapFacultad=ef.idEapFacultad INNER JOIN eap e ON ef.ideap=e.ideap "
                    + " WHERE ep.idperiodo=? AND ep.idEapFacultad=? ");
            ps.setString(1, idperiodometa);
            ps.setString(2, idEapFacultad);
            rs = ps.executeQuery();
            while (rs.next()) {
                to = new Estadoperiodoeap();
                to.setIdestadoperiodoeap(rs.getInt("idestadoperiodoeap"));
                to.setIdperiodometa(rs.getInt("idperiodo"));
                to.setIdperiodometa_nombre(rs.getString("periodo"));
                to.setEstadometa(rs.getString("estadometa"));
                to.setEstadoavance(rs.getString("estadoavance"));
                to.setIdEapFacultad(rs.getInt("idEapFacultad"));
                to.setIdEapFacultad_nombre(rs.getString("nombre"));
                to.setEstadopoa(rs.getString("estadopoa"));
                to.setEstadopm(rs.getString("estadopm"));
                reporte.add(to);
                }
        } catch (Exception e) {
            System.out.println("Error !!" + e.getMessage());
        } finally {
            getCerrarConexion();
        }
        return reporte;

    }
        
        public int InsertarEstadoPeriodoEap(Estadoperiodoeap to) {
        int r = 0;
        try {
            getConexionDb();
            ps = con.prepareStatement(" insert into "
                    + "estadoperiodoeap(idperiodo, estadometa, estadoavance, idEapFacultad) "
                    + "values (?, ?, ?, ?)");
            ps.setInt(1, to.getIdperiodometa());
            ps.setString(2, to.getEstadometa());
            ps.setString(3, to.getEstadoavance());
            ps.setInt(4, to.getIdEapFacultad());
            if (ps.executeUpdate() == 1) {
                r = 1;
                System.out.println("Insertado!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
        }
        
        public void Aperturar_Estado_Meta_Eap(int idestadoperiodoeap) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodoeap set estadometa='1' where idestadoperiodoeap=?");
            ps.setInt(1, idestadoperiodoeap);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void Cerrar_Estado_Meta_Eap(int idestadoperiodoeap) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodoeap set estadometa='0' where idestadoperiodoeap=?");
            ps.setInt(1, idestadoperiodoeap);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        
        public void Aperturar_Estado_Avance_Eap(int idestadoperiodoeap) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodoeap set estadoavance='1' where idestadoperiodoeap=?");
            ps.setInt(1, idestadoperiodoeap);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void Cerrar_Estado_Avance_Eap(int idestadoperiodoeap) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodoeap set estadoavance='0' where idestadoperiodoeap=?");
            ps.setInt(1, idestadoperiodoeap);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void EliminarEstadoPeriodoEapAper(int idestadoperiodoeap) {
        try {
            getConexionDb();
            ps = con.prepareStatement("Delete FROM estadoperiodoeap WHERE idestadoperiodoeap=?");
            ps.setInt(1, idestadoperiodoeap);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Elimino");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        

/* Inicio Control POA*/    
        
        public void aperturarPOAestadoEAP(int idestadoperiodoeap) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodoeap set estadopoa='1' where idestadoperiodoeap=?");
            ps.setInt(1, idestadoperiodoeap);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se activo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void cerrarPOAestadoEAP(int idestadoperiodoeap) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodoeap set estadopoa='0' where idestadoperiodoeap=?");
            ps.setInt(1, idestadoperiodoeap);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }        
     /* Fin Control POA*/     
        
     /* Inicio Control Plan Mejora*/     
        
        public void aperturarPMestadoEAP(int idestadoperiodoeap) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodoeap set estadopm='1' where idestadoperiodoeap=?");
            ps.setInt(1, idestadoperiodoeap);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se activo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }
        
        public void cerrarPMestadoEAP(int idestadoperiodoeap) {
        try {
            getConexionDb();
            ps = con.prepareStatement("update estadoperiodoeap set estadopm='0' where idestadoperiodoeap=?");
            ps.setInt(1, idestadoperiodoeap);
            if (ps.executeUpdate() == 1) {
                System.out.println("Se Desactivo");
            }
        } catch (Exception e) {
        } finally {
            getCerrarConexion();
        }
       }        
        
      /* Fin Control Plan Mejora*/     
        
    
}
