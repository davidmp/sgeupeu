/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sge.directori;

/**
 *
 * @author Plsnificacion
 */
public class FileDirectori {
    public String direc="";
    public FileDirectori(){}
    
    public String directorioFilial(int idFilial){
    if(idFilial==1){direc="Lima/";}
    if(idFilial==2){direc="Juliaca/";}
    if(idFilial==3){direc="Tarapoto/";}
    return direc;
    }
}
