package form;

import dao.Playlist;
import SlideShow.Slide1;
import SlideShow.Slide2;
import SlideShow.Slide3;
import SlideShow.Slide4;
import SlideShow.Slide5;
import SlideShow.Slide6;
import Table.Model_Table;
import Table.tableDAO;
import dao.BaiHatDAO;
import entity.BaiHatEntity;
import saveEvent.BaiHatStateManager;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import saveEvent.MusicDetails;
import utils.EventManager;

public class FormTrangChu extends javax.swing.JPanel {

    private FormMainMusic formMainMusic;
    tableDAO tbdao = new tableDAO();
    BaiHatDAO bhDao = new BaiHatDAO();
    public static final String BAI_HAT_SELECTED_EVENT = "BAI_HAT_SELECTED_EVENT";
    public static final String DETAILS_SELECTED_EVENT = "DETAILS_SELECTED_EVENT";
    public static final String PLAYLIST_SELECTED_EVENT = "PLAYLIST_SELECTED_EVENT";
    private static BaiHatEntity bh;

    public FormTrangChu() {
        initComponents();
        addImg();
        init();
        fillTable();
    }

    public void addImg() {
        img1.setImage(new ImageIcon(getClass().getResource("/icon/ChillForm/5.jpg")));
        img2.setImage(new ImageIcon(getClass().getResource("/icon/ChillForm/7.jpg")));
        img3.setImage(new ImageIcon(getClass().getResource("/icon/ChillForm/13.jpg")));
        img4.setImage(new ImageIcon(getClass().getResource("/icon/ChillForm/3.jpg")));
        img5.setImage(new ImageIcon(getClass().getResource("/icon/remix/1.jpg")));
        img6.setImage(new ImageIcon(getClass().getResource("/icon/remix/2.jpg")));
        img7.setImage(new ImageIcon(getClass().getResource("/icon/remix/10.jpg")));
        img8.setImage(new ImageIcon(getClass().getResource("/icon/remix/5.jpg")));
        img9.setImage(new ImageIcon(getClass().getResource("/icon/rap/1.jpg")));
        img10.setImage(new ImageIcon(getClass().getResource("/icon/rap/5.jpg")));
        img11.setImage(new ImageIcon(getClass().getResource("/icon/rap/2.jpg")));
        img12.setImage(new ImageIcon(getClass().getResource("/icon/rap/6.jpg")));
        img13.setImage(new ImageIcon(getClass().getResource("/icon/ngheLaYeuDoi/1.jpg")));
        img14.setImage(new ImageIcon(getClass().getResource("/icon/ngheLaYeuDoi/6.jpg")));
        img15.setImage(new ImageIcon(getClass().getResource("/icon/ngheLaYeuDoi/3.jpg")));
        img16.setImage(new ImageIcon(getClass().getResource("/icon/ngheLaYeuDoi/5.jpg")));
        img17.setImage(new ImageIcon(getClass().getResource("/icon/tamTrangHoiSuy/2.jpg")));
        img18.setImage(new ImageIcon(getClass().getResource("/icon/tamTrangHoiSuy/3.jpg")));
        img19.setImage(new ImageIcon(getClass().getResource("/icon/tamTrangHoiSuy/4.jpg")));
        img20.setImage(new ImageIcon(getClass().getResource("/icon/tamTrangHoiSuy/5.jpg")));
        img21.setImage(new ImageIcon(getClass().getResource("/icon/Chill/hot7.png")));
        img22.setImage(new ImageIcon(getClass().getResource("/icon/Chill/hot2.png")));
        img23.setImage(new ImageIcon(getClass().getResource("/icon/Chill/hot3.png")));
        img24.setImage(new ImageIcon(getClass().getResource("/icon/Chill/hot8.png")));
        img25.setImage(new ImageIcon(getClass().getResource("/icon/Chill/a1.jpg")));
        img26.setImage(new ImageIcon(getClass().getResource("/icon/Chill/a2.jpg")));
        img27.setImage(new ImageIcon(getClass().getResource("/icon/Chill/a3.jpg")));
        img28.setImage(new ImageIcon(getClass().getResource("/icon/Chill/a5.jpg")));

    }

    public void init() {
        slideShow1.initSlideshow(new Slide1(), new Slide2(), new Slide3(), new Slide4(), new Slide5(), new Slide6());
        setHoverCursor(table1);
        TableColumn nameColumn = table1.getColumnModel().getColumn(0);
        nameColumn.setPreferredWidth(350); // Đặt chiều ngang mong muốn

        TableColumn ageColumn = table1.getColumnModel().getColumn(1);
        ageColumn.setPreferredWidth(250); // Đặt chiều ngang mong muốn

        TableColumn genderColumn = table1.getColumnModel().getColumn(2);
        genderColumn.setPreferredWidth(90); // Đặt chiều ngang mong muốn

        TableColumn timeColumn = table1.getColumnModel().getColumn(3);
        timeColumn.setPreferredWidth(40); // Đặt chiều ngang mong muốn

//        ẩn cột thứ 4 (cột mã bài hát)
        TableColumnModel columnModel = table1.getColumnModel();
        TableColumn column = columnModel.getColumn(4);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);
        column.setResizable(false);
    }

    void fillTable() {
        try {
            List<Model_Table> list = tbdao.selectAll();
            for (Model_Table bh : list) {

                table1.addRow(new Model_Table(bh.getIcon(), bh.getName(), bh.getSing(), bh.getView(), bh.getTime(), bh.getMaBh()).toRowTable());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BaiHatEntity getEntity() {
        BaiHatEntity entity = new BaiHatEntity();
        entity.setSoluotNghe(bh.getSoluotNghe() + 1);
        entity.setMaBh(bh.getMaBh());
        return entity;
    }

    int row = 0;

    void edit() {
        this.row = table1.getSelectedRow();
        String maBh = (String) table1.getValueAt(this.row, 4);
        bh = bhDao.selectById(maBh);    
        try {
            BaiHatEntity bhEntity = getEntity();
            bhDao.updateView(bhEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        BaiHatStateManager.setSelectedBaiHat(bh);
        MusicDetails.setSelectedBaiHat(bh);

        EventQueue.invokeLater(() -> {
            EventManager.fireEvent(BAI_HAT_SELECTED_EVENT, bh);
            EventManager.fireEvent(DETAILS_SELECTED_EVENT, bh);
        });
    }

    private static void setHoverCursor(JTable listChill) {
        listChill.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setHandCursor(listChill);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setDefaultCursor(listChill);
            }
        });
    }

    private static void setHandCursor(Component component) {
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
        component.setCursor(handCursor);
    }

    // Đặt hình mặc định cho chuột
    private static void setDefaultCursor(Component component) {
        Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
        component.setCursor(defaultCursor);
    }
    
    public void sendPlaylist(String text){
        EventQueue.invokeLater(() -> {
            EventManager.fireEvent(PLAYLIST_SELECTED_EVENT, text);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        slideShow1 = new SlideShow.SlideShow();
        jLabel1 = new javax.swing.JLabel();
        table1 = new Table.TableMusic();
        jLabel2 = new javax.swing.JLabel();
        img2 = new swing.RoundedImage();
        img3 = new swing.RoundedImage();
        img4 = new swing.RoundedImage();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        img1 = new swing.RoundedImage();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        img5 = new swing.RoundedImage();
        jLabel17 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        img6 = new swing.RoundedImage();
        img7 = new swing.RoundedImage();
        img8 = new swing.RoundedImage();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        img9 = new swing.RoundedImage();
        jLabel21 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        img10 = new swing.RoundedImage();
        img11 = new swing.RoundedImage();
        img12 = new swing.RoundedImage();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        img16 = new swing.RoundedImage();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        img15 = new swing.RoundedImage();
        jLabel5 = new javax.swing.JLabel();
        img13 = new swing.RoundedImage();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        img14 = new swing.RoundedImage();
        img17 = new swing.RoundedImage();
        jLabel28 = new javax.swing.JLabel();
        img19 = new swing.RoundedImage();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        img20 = new swing.RoundedImage();
        img18 = new swing.RoundedImage();
        jLabel7 = new javax.swing.JLabel();
        img21 = new swing.RoundedImage();
        img22 = new swing.RoundedImage();
        img23 = new swing.RoundedImage();
        img24 = new swing.RoundedImage();
        jLabel8 = new javax.swing.JLabel();
        img25 = new swing.RoundedImage();
        img26 = new swing.RoundedImage();
        img27 = new swing.RoundedImage();
        img28 = new swing.RoundedImage();

        setBackground(new java.awt.Color(29, 38, 49));

        slideShow1.setBackground(new java.awt.Color(29, 38, 49));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Dành Riêng Cho Bạn");

        table1.setBorder(null);
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Sing", "View", "Time", "Ma"
            }
        ));
        table1.setFocusable(false);
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Chill");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("Nhạc Chill Tâm Trạng Buồn");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("Tình Iu Cực Chill");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Nhạc Lâu Phai Chill Gây Nghiện");

        img1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                img1MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("Nhạc Chill Yêu Đời");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setText("Nhạc Trẻ Vinahouse");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("Remix Việt Ngày Nay");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Remix Là Dance Luôn");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setText("Dance Việt Hay Nhất");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 204, 204));
        jLabel19.setText("TikTok Remix");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 204, 204));
        jLabel20.setText("Đây Là Rap Love");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 204, 204));
        jLabel21.setText("Rap Việt Ngày Nay");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Rap Việt Cực Chất");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(204, 204, 204));
        jLabel22.setText("Ơ, Động Đất À !");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 204, 204));
        jLabel23.setText("Gia Vị Rap Việt");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 204, 204));
        jLabel24.setText("Bật Speed Up Lên");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(204, 204, 204));
        jLabel25.setText("Nhạc Dí Deadline");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nghe Là Yêu Đời");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(204, 204, 204));
        jLabel26.setText("Tự Nhiên Vui Ngang");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(204, 204, 204));
        jLabel27.setText("Tình Iu Cũng Vui Á Chứ");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(204, 204, 204));
        jLabel28.setText("Nhạc Để Suy");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(204, 204, 204));
        jLabel29.setText("Tâm Tình Lâu Phai");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(204, 204, 204));
        jLabel30.setText("Vẫn Mộng Mơ Dù Tình Đã Vỡ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tâm Trạng Hơi Suy");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(204, 204, 204));
        jLabel31.setText("Dạo Quanh Thành Phố Mình Ta");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Chủ Đề Hot");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Top 100");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(img21, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(img22, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(img23, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(img24, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(table1, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(img5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(img9, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(img13, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(img17, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(img6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(img10, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24)
                            .addComponent(img14, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(img18, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(img7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(img11, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(img15, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)
                            .addComponent(img19, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(img8, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(img12, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(img16, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel30)
                            .addComponent(img20, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(img1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(img2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(img3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(img4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(img25, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(img26, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(img27, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(img28, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(slideShow1, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 4, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(img1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(60, 60, 60)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(img5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img8, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel16))
                .addGap(60, 60, 60)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(img9, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img10, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img11, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img12, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel20))
                .addGap(60, 60, 60)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(img13, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img14, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img15, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img16, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel24)
                    .addComponent(jLabel27)
                    .addComponent(jLabel25))
                .addGap(60, 60, 60)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(img17, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img18, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img19, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img20, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel28)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30))
                .addGap(63, 63, 63)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(table1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(img21, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img22, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img23, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img24, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(img25, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img26, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img27, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img28, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 4, Short.MAX_VALUE)
                    .addComponent(slideShow1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 3129, Short.MAX_VALUE)))
        );

    }// </editor-fold>//GEN-END:initComponents

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        edit();
    }//GEN-LAST:event_table1MouseClicked

    private void img1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_img1MouseClicked
        sendPlaylist("PL001");
    }//GEN-LAST:event_img1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.RoundedImage img1;
    private swing.RoundedImage img10;
    private swing.RoundedImage img11;
    private swing.RoundedImage img12;
    private swing.RoundedImage img13;
    private swing.RoundedImage img14;
    private swing.RoundedImage img15;
    private swing.RoundedImage img16;
    private swing.RoundedImage img17;
    private swing.RoundedImage img18;
    private swing.RoundedImage img19;
    private swing.RoundedImage img2;
    private swing.RoundedImage img20;
    private swing.RoundedImage img21;
    private swing.RoundedImage img22;
    private swing.RoundedImage img23;
    private swing.RoundedImage img24;
    private swing.RoundedImage img25;
    private swing.RoundedImage img26;
    private swing.RoundedImage img27;
    private swing.RoundedImage img28;
    private swing.RoundedImage img3;
    private swing.RoundedImage img4;
    private swing.RoundedImage img5;
    private swing.RoundedImage img6;
    private swing.RoundedImage img7;
    private swing.RoundedImage img8;
    private swing.RoundedImage img9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private SlideShow.SlideShow slideShow1;
    private Table.TableMusic table1;
    // End of variables declaration//GEN-END:variables
}
