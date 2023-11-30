/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;



import entity.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import utils.XJdbcc;

/**
 *
 * @author kle99
 */
public class NgheGanDayDAO {

    String Select_All = "Select * from NgheGanDay";
    String INSERT = "insert into NgheGanDay ( MaBH, TenTK) values (?,?) ";
    String SELECT_BY_ID = "SELECT  * FROM NgheGanDay WHERE TenTK = ?";
    String DELETE_BY_ID = "DELETE  FROM NgheGanDay WHERE TenTK = ? AND MaBH = ?";
    public void insert(NgheGanDay entity) throws SQLException {
        XJdbcc.update(INSERT, entity.getMaBh(), entity.getTenTK());
    }

    public List<NgheGanDay> selectById(String tentk) {
        List<NgheGanDay> list = this.selectBySql(SELECT_BY_ID, tentk);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
     public NgheGanDay deleteByid(String tentk,String baihat) {
        List<NgheGanDay> list = this.selectBySql(DELETE_BY_ID, tentk,baihat);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public List<NgheGanDay> selectAll() {
        return this.selectBySql(Select_All);
    }

    protected List<NgheGanDay> selectBySql(String sql, Object... args) {
        List<NgheGanDay> list = new ArrayList<NgheGanDay>();
        try {
            ResultSet rs = XJdbcc.query(sql, args);
            while (rs.next()) {
                NgheGanDay entity = new NgheGanDay();
               
                entity.setMaBh(rs.getString(2));
                entity.setTenTK(rs.getString(3));
                list.add(entity);
            }
            rs.getStatement().getConnection();
            rs.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
}
