/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author kle99
 */
public class BaiHatYeuThich {
    public String maBH;
    public String tenTK;

    public BaiHatYeuThich(String maBH, String tenTK) {
        this.maBH = maBH;
        this.tenTK = tenTK;
    }

    public BaiHatYeuThich() {
    }

    public String getMaBH() {
        return maBH;
    }

    public void setMaBH(String maBH) {
        this.maBH = maBH;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }
    
    
}
