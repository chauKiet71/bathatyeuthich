package entity;

public class PlaylistEntity {

    private String maPlaylist;
    private String tenPlaylist;
    private Integer tim;
    private String loiTua;
    private String anh;

    public PlaylistEntity() {
    }

    public PlaylistEntity(String maPlaylist, String tenPlaylist, Integer tim, String loiTua, String anh) {
        this.maPlaylist = maPlaylist;
        this.tenPlaylist = tenPlaylist;
        this.tim = tim;
        this.loiTua = loiTua;
        this.anh = anh;
    }

    public String getMaPlaylist() {
        return maPlaylist;
    }

    public void setMaPlaylist(String maPlaylist) {
        this.maPlaylist = maPlaylist;
    }

    public String getTenPlaylist() {
        return tenPlaylist;
    }

    public void setTenPlaylist(String tenPlaylist) {
        this.tenPlaylist = tenPlaylist;
    }

    public Integer getTim() {
        return tim;
    }

    public void setTim(Integer tim) {
        this.tim = tim;
    }

    public String getLoiTua() {
        return loiTua;
    }

    public void setLoiTua(String loiTua) {
        this.loiTua = loiTua;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

}
