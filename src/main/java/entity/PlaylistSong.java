/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import javax.swing.Icon;

/**
 *
 * @author PC
 */
public class PlaylistSong {

    private Icon anhBaiHat;
    private String tenBaihat;
    private String caSi;
    private Integer luotXem;
    private String thoiGian;
    private String maBh;

    public PlaylistSong() {
    }

    public PlaylistSong(Icon anhBaiHat, String tenBaihat, String caSi, Integer luotXem, String thoiGian, String maBh) {
        this.anhBaiHat = anhBaiHat;
        this.tenBaihat = tenBaihat;
        this.caSi = caSi;
        this.luotXem = luotXem;
        this.thoiGian = thoiGian;
        this.maBh = maBh;
    }

    public Icon getAnhBaiHat() {
        return anhBaiHat;
    }

    public void setAnhBaiHat(Icon anhBaiHat) {
        this.anhBaiHat = anhBaiHat;
    }

    public String getTenBaihat() {
        return tenBaihat;
    }

    public void setTenBaihat(String tenBaihat) {
        this.tenBaihat = tenBaihat;
    }

    public String getCaSi() {
        return caSi;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }

    public Integer getLuotXem() {
        return luotXem;
    }

    public void setLuotXem(Integer luotXem) {
        this.luotXem = luotXem;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getMaBh() {
        return maBh;
    }

    public void setMaBh(String maBh) {
        this.maBh = maBh;
    }

    public void setAnhBaiHat(String iconPath) {
        this.tenBaihat = tenBaihat;
    }
}
