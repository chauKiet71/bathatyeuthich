/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.BaiHatYeuThich;
import entity.NgheGanDay;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbcc;

/**
 *
 * @author kle99
 */
public class BaiHatYeuThichDAO {

    String select_All = "select * from BaiHatYeuThich";
    String insert = "insert into BaiHatYeuThich (MaBH, TenTK) values (?, ?)";
    String select_by_id = "select * from BaiHatYeuThich where TenTK = ?";
    String delete_by_id = "delete from BaiHatYeuThich where MaBH = ?";

    public void insert(BaiHatYeuThich entity) throws SQLException {
        XJdbcc.update(insert, entity.getMaBH(), entity.getTenTK());
    }

    public List<BaiHatYeuThich> selectById(String tentk) {
        List<BaiHatYeuThich> list = this.selectBySql(select_by_id, tentk);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    public BaiHatYeuThich deleteByid(String tentk) {
        List<BaiHatYeuThich> list = this.selectBySql(delete_by_id, tentk);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    protected List<BaiHatYeuThich> selectBySql(String sql, Object... args) {
        List<BaiHatYeuThich> list = new ArrayList<BaiHatYeuThich>();
        try {
            ResultSet rs = XJdbcc.query(sql, args);
            while (rs.next()) {
                BaiHatYeuThich entity = new BaiHatYeuThich();

                entity.setMaBH(rs.getString(2));
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
