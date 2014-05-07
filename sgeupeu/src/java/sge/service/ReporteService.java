/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sge.service;

import java.util.ArrayList;
import sge.dao.ReportesDAO;

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
    
}
