/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class PhieuTra implements Serializable{
    private String MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach;
    private Date NgayTraSach;
    private int SoNgayMuon, SoNgayTraTre;
    
    public String getMaPhieuTra(){
        return MaPhieuTra;
    }
    public void setMaPhieuTra(String MaPhieuTra){
        this.MaPhieuTra = MaPhieuTra;
    }
    ////////////
    public String getMaPhieuMuon(){
        return MaPhieuMuon;
    }
    public void setMaPhieuMuon(String MaPhieuMuon){
        this.MaPhieuMuon = MaPhieuMuon;
    }
    //////////////
    public String getMaDocGia(){
        return MaDocGia;
    }
    public void setMaDocGia(String MaDocGia){
        this.MaDocGia = MaDocGia;
    }
    ////////////////
    public String getMaSach(){
        return MaSach;
    }
    public void setMaSach(String MaSach){
        this.MaSach = MaSach;
    }
    //////////////
    public Date getNgayTraSach(){
        return NgayTraSach;
    }
    public void setNgayTraSach(Date NgayTraSach){
        this.NgayTraSach = NgayTraSach;
    }
    /////////////
    public int getSoNgayMuon(){
        return SoNgayMuon;
    }
    public void setSoNgayMuon(int SoNgayMuon){
        this.SoNgayMuon = SoNgayMuon;
    }
    ////////////////
    public int getSoNgayTraTre(){
        return SoNgayTraTre;
    }
    public void setSoNgayTraTre(int SoNgayTraTre){
        this.SoNgayTraTre = SoNgayTraTre;
    }
}
