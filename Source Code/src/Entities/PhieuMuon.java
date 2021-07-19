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
public class PhieuMuon implements Serializable{
    private String MaPhieuMuon, MaDocGia, MaSach, TinhTrangPM;
    private Date NgayMuonSach;
    
    public String getMaPhieuMuon(){
        return MaPhieuMuon;
    }
    public void setMaPhieuMuon(String MaPhieuMuon){
        this.MaPhieuMuon = MaPhieuMuon;
    }
    ///////////////
    public String getMaDocGia(){
        return MaDocGia;
    }
    public void setMaDocGia(String MaDocGia){
        this.MaDocGia = MaDocGia;
    }
    //////////////////////////
    public String getMaSach(){
        return MaSach;
    }
    public void setMaSach(String MaSach){
        this.MaSach = MaSach;
    }
    ///////////
    public String getTinhTrangPM(){
        return TinhTrangPM;
    }
    public void setTinhTrangPM(String TinhTrangPM){
        this.TinhTrangPM = TinhTrangPM;
    }
    //////////////
    public Date getNgayMuonSach(){
        return NgayMuonSach;
    }
    public void setNgayMuonSach(Date NgayMuonSach){
        this.NgayMuonSach = NgayMuonSach;
    }
}
