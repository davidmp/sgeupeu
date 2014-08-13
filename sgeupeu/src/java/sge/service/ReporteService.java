/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sge.service;

import java.util.ArrayList;
import java.util.List;
import sge.dao.ReportesDAO;
import sge.modelo.Filial;
import sge.modelo.Tipoarea;

/**
 *
 * @author Intel
 */
public class ReporteService {
  public ReportesDAO dao;
    
  public ArrayList cabeceraPOA(int ideapfacultad){
      dao=new ReportesDAO();
      return dao.cabeceraPOA(ideapfacultad);
  }
  public ArrayList reporEapFacultadFilial(int idfilial){
      dao=new ReportesDAO();
      return dao.reporEapFacultadFilial(idfilial);
  }
  public ArrayList reporEapFacultadFilialAudit(int idfilial){
      dao=new ReportesDAO();
      return dao.reporEapFacultadFilialAudit(idfilial);
  }
  
  public ArrayList reporEapFacultadFilialEap(int idfilial, int idfilialfacultad){
      dao=new ReportesDAO();
      return dao.reporEapFacultadFilialEap(idfilial,idfilialfacultad);
  }
  public ArrayList ejesSeleccionadosPOA(int ideapfacultad, int  ideje){
      dao=new ReportesDAO();
      return dao.ejesSeleccionadosPOA(ideapfacultad, ideje);
  }
  public ArrayList objetivosEstrategicosPOA(int ideapfacultad, int  ideje){
      dao=new ReportesDAO();
      return dao.objetivosEstrategicosPOA(ideapfacultad, ideje);
  }
  public ArrayList actividadesPOA(int idmeta, int mes1, int mes2){
      dao=new ReportesDAO();
      return dao.actividadesPOA(idmeta, mes1, mes2);
  }
  public ArrayList actividadesPOAVencidos(int idmeta, int mes1, int mes2){
      dao=new ReportesDAO();
      return dao.actividadesPOAVencidos(idmeta, mes1, mes2);
  }
  public ArrayList evaluacionPlanEstrategico(int ideapfacultad, int idperiodo, int idtipoarea, int idfilial, int ejearea){
      dao=new ReportesDAO();
      return dao.evaluacionPlanEstrategico(ideapfacultad, idperiodo, idtipoarea, idfilial,ejearea);
  }
  public ArrayList reporteArchivos(int idAvance){
      dao=new ReportesDAO();
      return dao.reporteArchivos(idAvance);
  }
  public List<Tipoarea> areaPrePosgrado(){
      dao=new ReportesDAO();
      return dao.areaPrePosgrado();
  }
  public Filial filialPerido(int idfilial, int idperiodo){
      dao=new ReportesDAO();
      return dao.filialPerido(idfilial,idperiodo);
  }
  public ArrayList reporteCarrerasPregrado(int idfilial){
      dao=new ReportesDAO();
      return dao.reporteCarrerasPregrado(idfilial);
  }    
}
