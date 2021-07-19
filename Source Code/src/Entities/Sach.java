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
public class Sach implements Serializable{
    private String MaSach, TenSach, TheLoai,  TacGia, NXB, ViTri , TinhTrang;
    private int SoTrang,SoLuong;
    
    public String getMaSach(){
        return MaSach;
    }
    public void setMaSach(String MaSach){
        this.MaSach = MaSach;
    }
    /////////////
    public String getTenSach(){
        return TenSach;
    }
    public void setTenSach(String TenSach){
        this.TenSach = TenSach;
    }
    /////////////////
    public String getTheLoai(){
        return TheLoai;
    }
    public void setTheLoai(String TheLoai){
        this.TheLoai = TheLoai;
    }
    ///////////////////
    public String getTacGia(){
        return TacGia;
    }
    public void setTacGia(String TacGia){
        this.TacGia = TacGia;
    }
    ////////////////////
    public String getNXB(){
        return NXB;
    }
    public void setNXB(String NXB){
        this.NXB = NXB;
    }
    ///////////////////
    public String getViTri(){
        return ViTri;
    }
    public void setViTri(String ViTri){
        this.ViTri = ViTri;
    }
    ///////////
    public String getTinhTrang(){
        return TinhTrang;
    }
    public void setTinhTrang(String TinhTrang){
        this.TinhTrang = TinhTrang;
    }
    ///////////////////
    public int getSoTrang(){
        return SoTrang;
    }
    public void setSoTrang(int SoTrang){
        this.SoTrang = SoTrang;
    }
    ///////////////////
    public int getSoLuong(){
        return SoLuong;
    }
    public void setSoLuong(int SoLuong){
        this.SoLuong = SoLuong;
    }
}
