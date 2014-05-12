/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.service;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import sge.dao.IndicadorDAO;
import sge.modelo.*;
/**
 *
 * @author osdmdz
 */
public class IndicadorService {
   
    public IndicadorDAO dao;


   public List<Indicador> listaIndicador(HttpServletRequest rq ,int estadometa , int estadoavance, int idFilial){
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();      
        Eapfacultad ef = new Eapfacultad();
        ef.setIdeapfacultad(Integer.parseInt(rq.getParameter("ideapfacultad")));
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(null);
        }else{
            int a=Integer.parseInt(rq.getParameter("idperiodometa"));  
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
       if(Integer.parseInt(rq.getParameter("idtemporadaejeestrategico"))==0){
        return dao.listaIndicador(pm,ef,estadometa,estadoavance,idFilial);}
        else{
        return dao.listaIndicadorEje(pm,ef,estadometa,estadoavance,Integer.parseInt(rq.getParameter("idtemporadaejeestrategico")), idFilial);
         } 
       
        }
    
   

   
   
   
   
   
   
   
   
   public List<Periodometa> listaPeriodoMeta(HttpServletRequest rq){
        dao = new IndicadorDAO();
        return dao.listaPeriodoMeta();
    }
   
      public Periodometa idPeriodoMeta(HttpServletRequest rq){  
       dao = new IndicadorDAO();      
        int id=0;
        if(rq.getParameter("idperiodometa")!=null){
        id=Integer.parseInt(rq.getParameter("idperiodometa"));}
        return dao.idPeriodoMeta(id);
    }
   
   
      public int estadometa(HttpServletRequest rq){
         
        dao = new IndicadorDAO();
        int idpm =0;
        int idfi =0;
        int idfa =0;
        int idea =0;
        
        if(rq.getParameter("idperiodometa")!=null){
        idpm=Integer.parseInt(rq.getParameter("idperiodometa"));
        }         
        if(rq.getParameter("idfilial")!=null){
        idfi=Integer.parseInt(rq.getParameter("idfilial"));
        }
        if(rq.getParameter("idfilialfacultad")!=null){
        idfa=Integer.parseInt(rq.getParameter("idfilialfacultad"));
        }
        if(rq.getParameter("ideapfacultad")!=null){
        idea=Integer.parseInt(rq.getParameter("ideapfacultad"));
        }
        
        return dao.estadoMeta(idpm, idfi, idfa, idea);
    }
      
        public int estadoavance(HttpServletRequest rq){
         
        dao = new IndicadorDAO();
        int idpm =0;
        int idfi =0;
        int idfa =0;
        int idea =0;
        
        if(rq.getParameter("idperiodometa")!=null){
        idpm=Integer.parseInt(rq.getParameter("idperiodometa"));
        }         
        if(rq.getParameter("idfilial")!=null){
        idfi=Integer.parseInt(rq.getParameter("idfilial"));
        }
        if(rq.getParameter("idfacultad")!=null){
        idfa=Integer.parseInt(rq.getParameter("idfacultad"));
        }
        if(rq.getParameter("ideap")!=null){
        idea=Integer.parseInt(rq.getParameter("ideap"));
        }
        
        return dao.estadoAvance(idpm, idfi, idfa, idea);
    }
        
         public void insertarMeta(HttpServletRequest rq){
         dao = new IndicadorDAO();
        Meta m = new Meta();
        int nro=Integer.parseInt(rq.getParameter("nro_indicador"));
        m.setMeta(rq.getParameter("meta"+nro+""));
        m.setIdeapfacultad(Integer.parseInt(rq.getParameter("ideapfacultad")));
        m.setIdusuario(Integer.parseInt(rq.getParameter("idusuario")));
        m.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodo")));        
        m.setIdestrategiaindicador(Integer.parseInt(rq.getParameter("idestrategiaindicador"+nro+"")));
        if((Integer.parseInt(rq.getParameter("idmeta"+nro+"")))==0){
        dao.insertarMeta(m);
        }
        else{
         m.setIdmeta(Integer.parseInt(rq.getParameter("idmeta"+nro+"")));
         dao.actualizarMeta(m);
        }
        }
         
         
        public void actualizarMeta(HttpServletRequest rq){
        dao = new IndicadorDAO();
        Meta m = new Meta();
        int nro=Integer.parseInt(rq.getParameter("nro_indicador"));
        m.setMeta(rq.getParameter("meta"+nro+""));
         m.setIdmeta(Integer.parseInt(rq.getParameter("idmeta"+nro+"")));
        dao.actualizarMeta(m);
        }
        
        
         public void insertarEvidenciaMeta(String evidencia,String tipo,String url,int idmeta){
         dao = new IndicadorDAO();
         dao.insertarEvidenciaMeta(evidencia, tipo, url, idmeta);        
         }
         
         
         public void insertarEvidencia( String evidencia,String tipo,String url,int idAvance, int idUsuario){
         System.out.println("-------  EVIDENCIA __-  EVIDENCIA __-  EVIDENCIA __");    
         dao = new IndicadorDAO();
         dao.insertarEvidencia(evidencia, tipo, url, idAvance,idUsuario);        
         }
        
         
        public List<Ejeestrategico> listaEje(int idTipoarea){
        dao = new IndicadorDAO();
        return dao.listaEje(idTipoarea);
        }
        public List<Ejeestrategico> listaEjeIndividual(int ideje){
        dao = new IndicadorDAO();
        return dao.listaEjeIndividual(ideje);
        }
        public List<Ejeestrategico> listaEjeIndividualAreas(int idtipoarea, int idcodigo){
        dao = new IndicadorDAO();
        return dao.listaEjeIndividualAreas(idtipoarea, idcodigo);
        }
        
        public Ejeestrategico Eje(HttpServletRequest rq){
        dao = new IndicadorDAO();
        return dao.Eje(Integer.parseInt(rq.getParameter("idtemporadaejeestrategico")));
        }
        
        
         public void insertarActividad(HttpServletRequest rq){
        dao = new IndicadorDAO();
        
        int nro=Integer.parseInt(rq.getParameter("nro_indicador_3"));
        Actividad ac = new Actividad();
      
             if(rq.getParameter("enero")==null){
             ac.setEnero(0);
             }else{
             ac.setEnero(Integer.parseInt(rq.getParameter("enero")));
             }
             
             if(rq.getParameter("febrero")==null){
             ac.setFebrero(0);
             }else{
             ac.setFebrero(Integer.parseInt(rq.getParameter("febrero")));
             }
             
             if(rq.getParameter("marzo")==null){
             ac.setMarzo(0);
             }else{
             ac.setMarzo(Integer.parseInt(rq.getParameter("marzo")));
             }
             
             if(rq.getParameter("abril")==null){
             ac.setAbril(0);
             }else{
             ac.setAbril(Integer.parseInt(rq.getParameter("abril")));
             }
             
             if(rq.getParameter("mayo")==null){
             ac.setMayo(0);
             }else{
             ac.setMayo(Integer.parseInt(rq.getParameter("mayo")));
             }
             
             if(rq.getParameter("junio")==null){
             ac.setJunio(0);
             }else{
             ac.setJunio(Integer.parseInt(rq.getParameter("junio")));
             }
             
             if(rq.getParameter("julio")==null){
             ac.setJulio(0);
             }else{
             ac.setJulio(Integer.parseInt(rq.getParameter("julio")));
             }
             
             if(rq.getParameter("agosto")==null){
             ac.setAgosto(0);
             }else{
             ac.setAgosto(Integer.parseInt(rq.getParameter("agosto")));
             }
             
             if(rq.getParameter("setiembre")==null){
             ac.setSetiembre(0);
             }else{
             ac.setSetiembre(Integer.parseInt(rq.getParameter("setiembre")));
             }
             
             if(rq.getParameter("octubre")==null){
             ac.setOctubre(0);
             }else{
             ac.setOctubre(Integer.parseInt(rq.getParameter("octubre")));
             }
             
             if(rq.getParameter("noviembre")==null){
             ac.setNoviembre(0);
             }else{
             ac.setNoviembre(Integer.parseInt(rq.getParameter("noviembre")));
             }
             
             if(rq.getParameter("diciembre")==null){
             ac.setDiciembre(0);
             }else{
             ac.setDiciembre(Integer.parseInt(rq.getParameter("diciembre")));
             }
        
        int idMetaTemp=Integer.parseInt(rq.getParameter("idmeta"+nro+""));
        int numeroCodigo=dao.codigoActividadNumero(idMetaTemp);
        ac.setResponsable(String.valueOf(rq.getParameter("responsable")));
        ac.setNro(numeroCodigo);
        ac.setCantidad(Integer.parseInt(rq.getParameter("cantidad")));
        System.out.println(" -------------  idmeta -------------- "+rq.getParameter("idmeta"+nro+""));
        ac.setIdmeta(idMetaTemp);
        ac.setAccion(rq.getParameter("accion"));
        ac.setPresupuesto(Double.parseDouble(rq.getParameter("presupuesto")));
             System.out.println("verrrrr> "+Double.parseDouble(rq.getParameter("presupuesto")));
        ac.setRubro(rq.getParameter("rubro"));       
        

        dao = new IndicadorDAO();    
        dao.insertarActividad(ac);
    }
         
         
       public void actualizarActividad(HttpServletRequest rq){
        dao = new IndicadorDAO();

        Actividad ac = new Actividad();
      
             if(rq.getParameter("eneroxx")==null){
             ac.setEnero(0);
             }else{
             ac.setEnero(Integer.parseInt(rq.getParameter("eneroxx")));
             }
             
             if(rq.getParameter("febreroxx")==null){
             ac.setFebrero(0);
             }else{
             ac.setFebrero(Integer.parseInt(rq.getParameter("febreroxx")));
             }
             
             if(rq.getParameter("marzoxx")==null){
             ac.setMarzo(0);
             }else{
             ac.setMarzo(Integer.parseInt(rq.getParameter("marzoxx")));
             }
             
             if(rq.getParameter("abrilxx")==null){
             ac.setAbril(0);
             }else{
             ac.setAbril(Integer.parseInt(rq.getParameter("abrilxx")));
             }
             
             if(rq.getParameter("mayoxx")==null){
             ac.setMayo(0);
             }else{
             ac.setMayo(Integer.parseInt(rq.getParameter("mayoxx")));
             }
             
             if(rq.getParameter("junioxx")==null){
             ac.setJunio(0);
             }else{
             ac.setJunio(Integer.parseInt(rq.getParameter("junioxx")));
             }
             
             if(rq.getParameter("julioxx")==null){
             ac.setJulio(0);
             }else{
             ac.setJulio(Integer.parseInt(rq.getParameter("julioxx")));
             }
             
             if(rq.getParameter("agostoxx")==null){
             ac.setAgosto(0);
             }else{
             ac.setAgosto(Integer.parseInt(rq.getParameter("agostoxx")));
             }
             
             if(rq.getParameter("setiembrexx")==null){
             ac.setSetiembre(0);
             }else{
             ac.setSetiembre(Integer.parseInt(rq.getParameter("setiembrexx")));
             }
             
             if(rq.getParameter("octubrexx")==null){
             ac.setOctubre(0);
             }else{
             ac.setOctubre(Integer.parseInt(rq.getParameter("octubrexx")));
             }
             
             if(rq.getParameter("noviembrexx")==null){
             ac.setNoviembre(0);
             }else{
             ac.setNoviembre(Integer.parseInt(rq.getParameter("noviembrexx")));
             }
             
             if(rq.getParameter("diciembrexx")==null){
             ac.setDiciembre(0);
             }else{
             ac.setDiciembre(Integer.parseInt(rq.getParameter("diciembrexx")));
             }
        
   
        ac.setResponsable(String.valueOf(rq.getParameter("responsablexx")));     
        ac.setCantidad(Integer.parseInt(rq.getParameter("cantidadxx")));        
        ac.setAccion(rq.getParameter("accionxx"));
        ac.setPresupuesto(Double.parseDouble(rq.getParameter("presupuestoxx")));
        ac.setRubro(rq.getParameter("rubroxx"));       
        ac.setIdactividad(Integer.parseInt(rq.getParameter("idactividadxx")));       
        

        dao = new IndicadorDAO();    
        dao.actualizarActividad(ac);
    }
         
         
        public void insertarAvance(HttpServletRequest rq, String idUsuario){
        dao = new IndicadorDAO();
        Avance av = new Avance();
        
        int nr=Integer.parseInt(rq.getParameter("nro_indicador_2"));
        av.setId_ciclo(Integer.parseInt(rq.getParameter("nro_avance"+nr+"")));
        av.setMeta(rq.getParameter("avance"+nr+""));
        av.setIdmeta(Integer.parseInt(rq.getParameter("idmeta"+nr+"")));
        av.setIdusuario(Integer.parseInt(idUsuario));
        int idavanceX=0;
        idavanceX=dao.idAvanceReciente(av);
            System.out.println("Verrrrrr:"+idavanceX); 
        if(Integer.parseInt(rq.getParameter("idavance"+nr+""))==0 && idavanceX==0)
        {
        dao = new IndicadorDAO();
        dao.insertarAvance(av);
        dao=new IndicadorDAO();
        int idavance=dao.idAvanceReciente(av);
        dao=new IndicadorDAO();
        dao.actualizarAvanceValidaMeta(idavance, Integer.parseInt(rq.getParameter("idmeta"+nr+"")));
        }
        else{  
        av.setIdavance(Integer.parseInt(rq.getParameter("idavance"+nr+"")));
        dao = new IndicadorDAO();
        dao.actualizarAvance(av);
        dao=new IndicadorDAO();
        dao.actualizarAvanceValidaMeta(Integer.parseInt(rq.getParameter("idavance"+nr+"")), Integer.parseInt(rq.getParameter("idmeta"+nr+"")));        
        }
        
        
        
       }
        
        public void actualizarAvance(HttpServletRequest rq){
        dao = new IndicadorDAO();
        Avance av = new Avance();
        int nr=Integer.parseInt(rq.getParameter("nro_indicador_2"));
        av.setId_ciclo(Integer.parseInt(rq.getParameter("nro_avance"+nr+"")));
        av.setMeta(rq.getParameter("avance"+nr+""));
        av.setIdavance(Integer.parseInt(rq.getParameter("idavance"+nr+"")));
         dao.actualizarAvance(av);
         }
        
        
        public void eliminarEvidenciaMeta(HttpServletRequest rq){
        dao = new IndicadorDAO();
        int idmeta=Integer.parseInt(rq.getParameter("idmeta"));
        dao.eliminarEvidenciaMeta(idmeta);
         }
        
         public Avance avanceMeta(HttpServletRequest rq){
         dao = new IndicadorDAO();
         int nr=Integer.parseInt(rq.getParameter("nro_indicador_1"));
         int idmeta=Integer.parseInt(rq.getParameter("idmeta"+nr+""));
         int nro=Integer.parseInt(rq.getParameter("nro_avance"+nr+""));
         return dao.avanceMeta(idmeta,nro);
         }
         public CicloAcademico cicloActivo(){
         dao = new IndicadorDAO();
         return dao.cicloActivo();
         }
         
        public Avance nroMeta(HttpServletRequest rq){
            Avance av = new Avance();
         int nr=Integer.parseInt(rq.getParameter("nro_indicador_1"));  
         int nro=Integer.parseInt(rq.getParameter("nro_avance"+nr+""));
         int idmeta=Integer.parseInt(rq.getParameter("nro1"+nr+""));
         av.setId_ciclo(nro);
         av.setIdmeta(idmeta);
         
         System.out.println("---------------NRO----------- "+nro);
         return av;
         }
        
         public Indicador nroIndicador(HttpServletRequest rq){
            Indicador av = new Indicador();
            if(rq.getParameter("nroestindicador")!=null){
         int nro=Integer.parseInt(rq.getParameter("nroestindicador"));
         av.setNro(nro);}else{
         av.setNro(0);       
            }
         return av;
         }
         
        public Indicador buscar(HttpServletRequest rq){
            Indicador av = new Indicador();
            System.out.println("++++++++++ Valor ++++++++++++"+rq.getParameter("valor"));
            if(rq.getParameter("nroestindicador")!=null){
         int nro=Integer.parseInt(rq.getParameter("nroestindicador"));
         av.setNro(nro);}else{av.setNro(0);       
            }
         return av;
         }
        
       public List<Indicador> listaIndicadorBuscar(HttpServletRequest rq ,int estadometa , int estadoavance, int idFilial){
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();      
        Eapfacultad ef = new Eapfacultad();
        ef.setIdeapfacultad(Integer.parseInt(rq.getParameter("ideapfacultad")));
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(null);
        }else{
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
        if(Integer.parseInt(rq.getParameter("idtemporadaejeestrategico"))==0){
        //return dao.listaIndicador(pm,ef,estadometa,estadoavance,idFilial);
        return dao.listaIndicadorBuscar(pm,ef,estadometa,estadoavance,rq.getParameter("valor"),idFilial);
        }
        else{
        return dao.listaIndicadorBuscarPrimero(pm,ef,estadometa,estadoavance,rq.getParameter("valor"),idFilial, Integer.parseInt(rq.getParameter("idtemporadaejeestrategico")));
         } 
        
    }
       
       
        public Indicador nombreIndicador(HttpServletRequest rq ){
         Indicador in = new Indicador();
         in.setNombre(rq.getParameter("valor"));
        return in;
    }    
           
       

   public Ejeestrategico  ejeEap(HttpServletRequest rq ,int estadometa , int estadoavance, int idFilial){
        dao = new IndicadorDAO();
        int id=0;
        Periodometa pm = new Periodometa();      
        Eapfacultad ef = new Eapfacultad();
        ef.setIdeapfacultad(Integer.parseInt(rq.getParameter("ideapfacultad")));
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(null);
        }else{
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
        
        
        List<Indicador> lista;
        lista = dao.listaIndicadorBuscar(pm,ef,estadometa,estadoavance,rq.getParameter("valor"), idFilial);
        for(Indicador in : lista){
        id=in.getIdtemporadaejeestrategico();        
        }
        
        return dao.Eje(id);
    }   
       
       
       
       
       
       
     public List<Indicador> listaIndicadorEje(HttpServletRequest rq ,int estadometa , int estadoavance, int idFilial){
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();      
        Eapfacultad ef = new Eapfacultad();
        ef.setIdeapfacultad(Integer.parseInt(rq.getParameter("ideapfacultad")));
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(null);
        }else{
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
        return dao.listaIndicadorEje(pm,ef,estadometa,estadoavance,Integer.parseInt(rq.getParameter("idtemporadaejeestrategico")), idFilial);
    }       

           
       public List<Indicador> listaIndicadorU(HttpServletRequest rq ,int estadometa , int estadoavance){
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();      
        Eapfacultad ef = new Eapfacultad();
        ef.setIdeapfacultad(Integer.parseInt(rq.getParameter("ideapfacultad")));
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(null);
        }else{
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
        return dao.listaIndicadorU(pm,ef,estadometa,estadoavance,rq.getParameter("idestrategiaindicador"));
    }
         
         
      public List<Actividad> ListaActividadMeta(HttpServletRequest rq){
        dao = new IndicadorDAO();
        int nr=Integer.parseInt(rq.getParameter("nro_indicador_3")); 
        Meta da = new Meta();
        da.setIdmeta(Integer.parseInt(rq.getParameter("idmeta"+nr+"")));
        return dao.ListaActividadMeta(da);
     }
      public List<Actividad> ListaActividadMetaAx(HttpServletRequest rq){
        dao = new IndicadorDAO();
        int nr=Integer.parseInt(rq.getParameter("nro_indicador_3xx")); 
        Meta da = new Meta();
        da.setIdmeta(Integer.parseInt(rq.getParameter("idmetaxx")));
        return dao.ListaActividadMeta(da);
     }
      
      public void EliminarActividadMeta(HttpServletRequest rq){
        dao = new IndicadorDAO();
        int nro=Integer.parseInt(rq.getParameter("nro_actividad"));
        System.out.println(" -----------ssssss---------sss----ss "+nro);
        int id=Integer.parseInt(rq.getParameter("idactividad"+nro));
        dao.EliminarActividadMeta(id);
     }
      public void EliminarEvidencia(HttpServletRequest rq){
        dao = new IndicadorDAO();
  
        int nr=Integer.parseInt(rq.getParameter("nro_evidencia")); 
 
        int id=Integer.parseInt(rq.getParameter("idevidencia"+nr));
        dao.EliminarEvidencia(id);
     }
      
      
      
    public List<Actividad> ListaActividadIndicador(HttpServletRequest rq){
        dao = new IndicadorDAO();
        int nr=Integer.parseInt(rq.getParameter("nro_indicador_3")); 
        return dao.ListaActividadIndicador(Integer.parseInt(rq.getParameter("idestrategiaindicador"+nr+"")));
     }
    public List<Actividad> ListaActividadIndicadorAx(HttpServletRequest rq){
        dao = new IndicadorDAO();
        int nr=Integer.parseInt(rq.getParameter("nro_indicador_3xx")); 
        return dao.ListaActividadIndicador(Integer.parseInt(rq.getParameter("idestrategiaindicadorxx")));
     }
      
      
     public List<Evidencia> ListaEvidenciaMeta(HttpServletRequest rq){
        dao = new IndicadorDAO();
        int nr=Integer.parseInt(rq.getParameter("nro_indicador_4"));         
        int idavance=Integer.parseInt(rq.getParameter("idavancevalida"+nr+""));
        return dao.ListaEvidenciaMeta(idavance);
     }
      
       public Periodometa periodoMeta(HttpServletRequest rq){
         dao = new IndicadorDAO();
        int pm=Integer.parseInt(rq.getParameter("idperiodometa"));
        return dao.idPeriodoMeta(pm);
     }
       public Periodometa periodoMetaAx(HttpServletRequest rq){
         dao = new IndicadorDAO();
        int pm=Integer.parseInt(rq.getParameter("idperiodometaxx"));
        return dao.idPeriodoMeta(pm);
     }
       
       public Variables nroVariable(HttpServletRequest rq){
        Variables v = new Variables(); 
        v.setNro(Integer.parseInt(rq.getParameter("nro_indicador_3")));
        return v;
     }
       public Variables nroVariableAx(HttpServletRequest rq){
        Variables v = new Variables(); 
        v.setNro(Integer.parseInt(rq.getParameter("nro_indicador_3xx")));
        return v;
     }
       
       public Variables nroVariableEvidencia(HttpServletRequest rq){
       Variables v = new Variables(); 
        v.setNro(Integer.parseInt(rq.getParameter("nro_indicador_4")));
        return v;
     }
      
       
   
        public Indicador estrategiaIndicador(HttpServletRequest rq ,int estadometa , int estadoavance, int idFilial){
        dao = new IndicadorDAO();
        int nr=Integer.parseInt(rq.getParameter("nro_indicador_3")); 
        Periodometa pm = new Periodometa();      
        Eapfacultad ef = new Eapfacultad();
        ef.setIdeapfacultad(Integer.parseInt(rq.getParameter("ideapfacultad")));
            System.out.println("DAVIDMP:"+Integer.parseInt(rq.getParameter("ideapfacultad")));
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(null);
        }else{
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
        return dao.estrategiaIndicador(pm,ef,estadometa,estadoavance,rq.getParameter("idestrategiaindicador"+nr+""), idFilial);
    }
        public Indicador estrategiaIndicadorAx(HttpServletRequest rq ,int estadometa , int estadoavance, int idFilial){
        dao = new IndicadorDAO();
        int nr=Integer.parseInt(rq.getParameter("nro_indicador_3xx")); 
        Periodometa pm = new Periodometa();      
        Eapfacultad ef = new Eapfacultad();
        ef.setIdeapfacultad(Integer.parseInt(rq.getParameter("ideapfacultadxx")));
            
        if(rq.getParameter("idperiodometaxx")==null){
        pm.setIdperiodometa(null);
        }else{
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometaxx")));
        }
        return dao.estrategiaIndicador(pm,ef,estadometa,estadoavance,rq.getParameter("idestrategiaindicadorxx"), idFilial);
    }
        
        
     public Indicador estrategiaIndicadorEvidencia(HttpServletRequest rq ,int estadometa , int estadoavance, int idFilial){
        dao = new IndicadorDAO();
        int nr=Integer.parseInt(rq.getParameter("nro_indicador_4")); 
        Periodometa pm = new Periodometa();      
        Eapfacultad ef = new Eapfacultad();
        ef.setIdeapfacultad(Integer.parseInt(rq.getParameter("ideapfacultad")));
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(null);
        }else{
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
        return dao.estrategiaIndicador(pm,ef,estadometa,estadoavance,rq.getParameter("idestrategiaindicador"+nr+""), idFilial);
    }
        
        
//-------------------------> Consolidados
        
   public List<Indicador> listaIndicadorFacultad(HttpServletRequest rq){
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();      
        Filialfacultad ff = new Filialfacultad();
        System.out.println(" ---   rq.getParameter idperiodometa  ---- "+rq.getParameter("idperiodometa"));
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(0);
        ff.setIdfilialfacultad(0);
        }
        else{
        ff.setIdfilialfacultad(Integer.parseInt(rq.getParameter("idfilialfacultad")));
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
        return dao.listaIndicadorFacultad(pm, ff);
   }
      
   
   
      public List<Indicador> listaIndicadorFacultadBuscar(HttpServletRequest rq){
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();      
        Filialfacultad ff = new Filialfacultad();
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(0);
        ff.setIdfilialfacultad(0);
        }
        else{
        ff.setIdfilialfacultad(Integer.parseInt(rq.getParameter("idfilialfacultad")));
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
        return dao.listaIndicadorFacultadBuscar(pm, ff,rq.getParameter("valor"));
   }
   
      
   public Ejeestrategico  ejeFacultad(HttpServletRequest rq){
        dao = new IndicadorDAO();
        int id=0;
        Periodometa pm = new Periodometa();      
        Filialfacultad ff = new Filialfacultad();
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(0);
        ff.setIdfilialfacultad(0);
        }
        else{
        ff.setIdfilialfacultad(Integer.parseInt(rq.getParameter("idfilialfacultad")));
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
               
        List<Indicador> lista;
        lista = dao.listaIndicadorFacultadBuscar(pm, ff,rq.getParameter("valor"));
        for(Indicador in : lista){
        id=in.getIdtemporadaejeestrategico();        
        }
        
        return dao.Eje(id);
    }         
      
      
      
      
      
      
      
      
      
      
      
      
      
      public List<Indicador> listaIndicadorFacultadEje(HttpServletRequest rq){
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();      
        Filialfacultad ff = new Filialfacultad();
        System.out.println(" ---   rq.getParameter idperiodometa  ---- "+rq.getParameter("idperiodometa"));
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(0);
        ff.setIdfilialfacultad(0);
        }
        else{
        ff.setIdfilialfacultad(Integer.parseInt(rq.getParameter("idfilialfacultad")));
        ff.setIdfilial(Integer.parseInt(rq.getParameter("idfilial")));
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
        return dao.listaIndicadorFacultadEje(pm, ff,Integer.parseInt(rq.getParameter("idtemporadaejeestrategico")));
   }      
      
      
      
      
      
      
      
   
      public List<Indicador> listaIndicadorFilial(HttpServletRequest rq){
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();      
        Filial ff = new Filial();
        System.out.println(" ---   rq.getParameter idperiodometa  ---- "+rq.getParameter("idperiodometa"));
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(0);
        ff.setIdfilial(0);
        }
        else{
        ff.setIdfilial(Integer.parseInt(rq.getParameter("idfilial")));
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
        return dao.listaIndicadorFilial(pm, ff);
   }
   

      
      public List<Indicador> listaIndicadorFilialBuscar(HttpServletRequest rq){
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();      
        Filial ff = new Filial();
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(0);
        ff.setIdfilial(0);
        }
        else{
        ff.setIdfilial(Integer.parseInt(rq.getParameter("idfilial")));
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
        return dao.listaIndicadorFilialBuscar(pm, ff,rq.getParameter("valor"));
   }      
      
      
      
      public Ejeestrategico  ejeFilial(HttpServletRequest rq){
        int id=0;
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();      
        Filial ff = new Filial();
        System.out.println(" ---   rq.getParameter idperiodometa  ---- "+rq.getParameter("idperiodometa"));
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(0);
        ff.setIdfilial(0);
        }
        else{
        ff.setIdfilial(Integer.parseInt(rq.getParameter("idfilial")));
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
               
        List<Indicador> lista;
        lista = dao.listaIndicadorFilialBuscar(pm, ff,rq.getParameter("valor"));
        for(Indicador in : lista){
        id=in.getIdtemporadaejeestrategico();        
        }
        
        return dao.Eje(id);
    }         
      
      
      
      
      
      
      
      
  
      public List<Indicador> listaIndicadorFilialEje(HttpServletRequest rq){
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();      
        Filial ff = new Filial();
        System.out.println(" ---   rq.getParameter idperiodometa  ---- "+rq.getParameter("idperiodometa"));
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(0);
        ff.setIdfilial(0);
        }
        else{
        ff.setIdfilial(Integer.parseInt(rq.getParameter("idfilial")));
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
        return dao.listaIndicadorFilialEje(pm, ff,Integer.parseInt(rq.getParameter("idtemporadaejeestrategico")));
   }      
            
      
      
      
      
      
      
     public List<Eap> listaEapConsolidado(HttpServletRequest rq){
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();
        Filialfacultad ff = new Filialfacultad();
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(0);
        ff.setIdfilialfacultad(0);
        }
        else{
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        ff.setIdfilialfacultad(Integer.parseInt(rq.getParameter("idfilialfacultad")));
        }
        return dao.listaEapConsolidado(pm,ff);
   }
     
     
   public List<Facultad> listafacultadConsolidado(HttpServletRequest rq){
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();
        Filial f = new Filial();
      if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(0);
        f.setIdfilial(0);
        }
      else{
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        f.setIdfilial(Integer.parseInt(rq.getParameter("idfilial")));
      }
        return dao.listaFacultadConsolidado(pm, f);
   }  
      
   public List<Filial> listafilialConsolidado(HttpServletRequest rq){
        dao = new IndicadorDAO();
        Periodometa pm = new Periodometa();
  
       if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(0);        }
       else{
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
         }
       return dao.listaFilialConsolidado(pm);
 }     
   
}
