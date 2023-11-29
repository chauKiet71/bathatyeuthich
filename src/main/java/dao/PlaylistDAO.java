package dao;

import entity.PlaylistEntity;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import utils.XJdbcc;

public class PlaylistDAO {

    String tim_update = "update Playlist set Tim = ? where MaPlaylist = ?";
    String sql_selectById = "Select * from Playlist where MaPlaylist = ?";

    public void updateTim(PlaylistEntity entity) {
        try {
            XJdbcc.update(tim_update,
                    entity.getTim(),
                    entity.getMaPlaylist()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public List<PlaylistEntity> selectAll() {
//        return this.selectBySql(sql_selectAll);
//    }
    public PlaylistEntity selectById(String id) {
        List<PlaylistEntity> list = this.selectBySql(sql_selectById, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    protected List<PlaylistEntity> selectBySql(String sql, Object... args) {
        List<PlaylistEntity> list = new ArrayList<PlaylistEntity>();
        try {
            ResultSet rs = XJdbcc.query(sql, args);
            while (rs.next()) {
                PlaylistEntity entity = new PlaylistEntity();

                entity.setMaPlaylist(rs.getString(1));
                entity.setTenPlaylist(rs.getString(2));
                entity.setTim(rs.getInt(3));
                entity.setLoiTua(rs.getString(4));
                entity.setAnh(rs.getString(5));

                list.add(entity);

            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
