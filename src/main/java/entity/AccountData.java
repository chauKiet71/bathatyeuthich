/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author PC
 */
public class AccountData {

    private static String tenTK;
    private static String linkAnh;
    private static String anhPlaylist;
    private static String tenPlaylist;
    private static String savaMaBh;

    public static String getTenTK() {
        return tenTK;
    }

    public static void setTenTK(String name) {
        tenTK = name;

    }

    public static String getLink() {
        return linkAnh;
    }

    public static void setLink(String link) {
        linkAnh = link;
    }

    public static String getLinkAnh() {
        return linkAnh;
    }

    public static void setLinkAnh(String linkAnh) {
        AccountData.linkAnh = linkAnh;
    }

    public static String getTenPlaylist() {
        return tenPlaylist;
    }

    public static void setTenPlaylist(String tenPlaylist) {
        AccountData.tenPlaylist = tenPlaylist;
    }

    public static String getAnhPlaylist() {
        return anhPlaylist;
    }

    public static void setAnhPlaylist(String anhPlaylist) {
        AccountData.anhPlaylist = anhPlaylist;
    }

    public static String getSavaMaBh() {
        return savaMaBh;
    }

    public static void setSavaMaBh(String savaMaBh) {
        AccountData.savaMaBh = savaMaBh;
    }

    
    
}
