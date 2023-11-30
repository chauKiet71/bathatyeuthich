/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Table.Model_Table;
import entity.BaiHatEntity;
import entity.PlaylistSong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import utils.XJdbcc;

/**
 *
 * @author PC
 */
public class LichSuNghe {
    
    String sql_selectAll = "Select * from LichSuNghe";
    String INSERT = "insert into PlaylistSong (AnhBaihat, TenBaiHat, CaSi, SoLuotNghe, Thoigian, MaBH) values (?,?,?,?,?,?) ";
    String SELECT_BY_ID = "SELECT  * FROM BaiHat WHERE MaBH = ?";

    public List<Model_Table> selectAll() {
        return this.selectBySql(sql_selectAll);
    }

    public void insert(PlaylistSong entity) {
        try {
            XJdbcc.update(INSERT, entity.getAnhBaiHat(), entity.getTenBaihat(), entity.getCaSi(), entity.getLuotXem(), entity.getThoiGian(), entity.getMaBh());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Model_Table selectById(String id) {
        List<Model_Table> list = this.selectBySql(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    protected List<Model_Table> selectBySql(String sql, Object... args) {
        List<Model_Table> list = new ArrayList<Model_Table>();
        try {
            ResultSet rs = XJdbcc.query(sql, args);
            while (rs.next()) {
                Model_Table entity = new Model_Table();
                entity.setIcon(new ImageIcon(getClass().getResource(rs.getString(2))));
                entity.setName(rs.getString(3));
                entity.setSing(rs.getString(4));
                entity.setView(rs.getInt(5));
                entity.setTime(rs.getString(6));
                entity.setMaBh(rs.getString(7));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insert(BaiHatEntity bhEntity) {
        try {
            XJdbcc.update(INSERT, bhEntity.getAnh(), bhEntity.getTenBh(), bhEntity.getCaSi(), bhEntity.getSoluotNghe(), bhEntity.getThoiGian(), bhEntity.getMaBh());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
