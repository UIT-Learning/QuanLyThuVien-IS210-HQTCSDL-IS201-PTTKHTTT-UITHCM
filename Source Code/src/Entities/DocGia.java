
package Entities;
import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author DELL
 */
public class DocGia implements Serializable{
    private String MaDocGia, MatKhau, HoTen, GioiTinh ,SDT, DiaChi , Email, VaiTro;
    private Date NgSinh;
    public String getHoTen;
    public String getMaDocGia(){
        return MaDocGia;
    }
    public void setMaDocGia(String MaDocGia){
        this.MaDocGia = MaDocGia;
    }
    ////////
    public String getMatKhau(){
        return MatKhau;
    }
    public void setMatKhau(String MatKhau){
        this.MatKhau = MatKhau;
    }
    /////////////
    public String getHoTen(){
        return HoTen;
    }
    public void setHoTen(String HoTen){
        this.HoTen = HoTen;
    }
    /////
    public String getGioiTinh(){
        return GioiTinh;
    }
    public void setGioiTinh(String GioiTinh){
        this.GioiTinh = GioiTinh;
    }
    ////////////////////
    public String getSDT(){
        return SDT;
    }
    public void setSDT(String SDT){
        this.SDT = SDT;
    }
    ///////////////
    public String getDiaChi(){
        return DiaChi;
    }
    public void setDiaChi(String DiaChi){
        this.DiaChi = DiaChi;
    }
    ////////////
    public String getEmail(){
        return Email;
    }
    public void setEmail(String Email){
        this.Email = Email;
    }
    /////////////
    public String getVaiTro(){
        return VaiTro;
    }
    public void setVaiTro(String VaiTro){
        this.VaiTro = VaiTro;
    }
    ///////////////////
    public Date getNgSinh(){
        return NgSinh;
    }
    public void setNgSinh(Date NgSinh){
        this.NgSinh = NgSinh;
    }
}
