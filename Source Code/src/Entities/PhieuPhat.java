/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class PhieuPhat implements Serializable{
    private String MaPhieuPhat, MaDocGia, MaPhieuTra;
    private int TienPhat;
    
    public String getMaPhieuPhat(){
        return MaPhieuPhat;
    }
    public void setMaPhieuPhat(String MaPhieuPhat){
        this.MaPhieuPhat = MaPhieuPhat;
    }
    ///////////////
    public String getMaPhieuTra(){
        return MaPhieuTra;
    }
    public void setMaPhieuTra(String MaPhieuTra){
        this.MaPhieuTra = MaPhieuTra;
    }
    //////////////////
    public String getMaDocGia(){
        return MaDocGia;
    }
    public void setMaDocGia(String MaDocGia){
        this.MaDocGia = MaDocGia;
    }
    //////////////////
    public int getTienPhat(){
        return TienPhat;
    }
    public void setTienPhat(int TienPhat){
        this.TienPhat = TienPhat;
    }
}
