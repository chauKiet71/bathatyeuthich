/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import javax.swing.Icon;

/**
 *
 * @author kle99
 */
public class NgheGanDay {

  
    private String maBh;
    private String TenTK;

    public NgheGanDay() {
    }

    public NgheGanDay(String maBh, String TenTK) {
      
        this.maBh = maBh;
        this.TenTK = TenTK;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }
    

    public String getMaBh() {
        return maBh;
    }

    public void setMaBh(String maBh) {
        this.maBh = maBh;
    }

    
}
