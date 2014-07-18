/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sge.service;

import java.util.ArrayList;
import java.util.List;
import sge.dao.SeguimientoDAO;
import sge.modelo.Seguimiento;
import sge.modelo.TipoSeguimiento;

/**
 *
 * @author Intel
 */
public class SeguimientoService {
   public SeguimientoDAO dao;  
   
   public List<TipoSeguimiento> listaPeriodoMeta(){
        dao = new SeguimientoDAO();
        return dao.listaTipoSeguimientoEstado();
    }   
   public ArrayList reporteObservacionesModelo2(int idAvance){
        dao = new SeguimientoDAO();
        return dao.reporteObservacionesModelo2(idAvance);
    }   
   public int insertarSeguimiento(Seguimiento to){
        dao = new SeguimientoDAO();
        return dao.insertarSeguimiento(to);
    }   
   public int actulizarEstado(int idseguimiento, int estado){
        dao = new SeguimientoDAO();
        return dao.actulizarEstado(idseguimiento,estado);
    }   
 public void actulizarUserViewEstado(int iduserview, int idavance){
        dao = new SeguimientoDAO();
        dao.actulizarUserViewEstado(iduserview,idavance);
    }   
 public void actulizarUserRespEstado(int iduserresp, int idavance){
        dao = new SeguimientoDAO();
        dao.actulizarUserRespEstado(iduserresp,idavance);
    }   
   
   
}
