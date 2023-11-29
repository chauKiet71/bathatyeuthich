package form;

import Table.Model_Table;
import Table.tableDAO;
import dao.BaiHatDAO;
import dao.Playlist;
import dao.PlaylistDAO;
import entity.PlaylistEntity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import main.Main;
import saveEvent.PlaylistSelected;
import saveEvent.PlaylistSelectedCopy;
import swing.ScrollBarr;
import utils.EventManager;

public class FormChiTietPlaylist extends javax.swing.JPanel {

    PlaylistSelected playlist = new PlaylistSelected();
    tableDAO tbDao = new tableDAO();
    PlaylistDAO plDao = new PlaylistDAO();
    private static List<Model_Table> listTable;
    private boolean isTim = false;
    private PlaylistEntity plEntity = new PlaylistEntity();

    public FormChiTietPlaylist() {
        initComponents();
        init();
        EventManager.addListener(Main.PLAYLIST_SELECTED_EVENT2, (event, data) -> {
            if (data instanceof String) {
                String selected = (String) data;
                playlist.setPlaylistSelected(selected);
                fillTable();
            }
        });

    }

    public void fillTable() {
        String playlisString = playlist.getPlaylistSelected();
        try {
            listTable = tbDao.selectPlaylist(playlisString);
            if (listTable != null) {
                for (Model_Table bh : listTable) {
                    table1.addRow(new Model_Table(bh.getIcon(), bh.getName(), bh.getSing(), bh.getView(), bh.getTime(), bh.getMaBh()).toRowTable());
                }
                plEntity = plDao.selectById(playlisString);

                BufferedImage originalImage = ImageIO.read(getClass().getResource(plEntity.getAnh()));
                int width = 210;
                int height = 210;
                // Tạo ảnh mới với đường viền bo góc và kích thước vừa với lbanh
                BufferedImage roundedImage = createRoundedImage(originalImage, width, height);
                // Tạo ImageIcon từ ảnh mới và thiết lập cho lbanh
                ImageIcon roundedImageIcon = new ImageIcon(roundedImage);
                lbanh.setIcon(roundedImageIcon);
                lbMoTa.setText(plEntity.getLoiTua());
                lbTen.setText(plEntity.getTenPlaylist());
                lbTim.setText(plEntity.getTim() + " Lượt thích");

            } else {
                System.out.println("null rồi");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PlaylistEntity getEntity1(int index) {
        PlaylistEntity entity = new PlaylistEntity();
        entity.setTim(plEntity.getTim() + index);
        entity.setMaPlaylist(plEntity.getMaPlaylist());
        return entity;
    }

    public void tim() {

        if (isTim == false) {
            btnTim.setIcon(new ImageIcon(getClass().getResource("/icon/heart24.png")));
            PlaylistEntity pl = getEntity1(1);
            try {
                plDao.updateTim(pl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            isTim = true;
        } else {
            btnTim.setIcon(new ImageIcon(getClass().getResource("/icon/heart24null.png")));
            PlaylistEntity pl = getEntity1(-1);
            try {
                plDao.updateTim(pl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            isTim = false;
        }

    }

    private BufferedImage createRoundedImage(BufferedImage originalImage, int width, int height) {
        BufferedImage roundedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = roundedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(new RoundRectangle2D.Float(0, 0, width, height, 20, 20)); // Điều chỉnh bán kính bo góc ở đây
        g2.drawImage(originalImage, 0, 0, width, height, null);

        g2.setColor(new Color(38, 46, 57)); // Màu sắc của đường viền
        g2.setStroke(new BasicStroke(1)); // Độ đậm của đường viền
        g2.drawRoundRect(0, 0, width, height, 20, 20); // Vẽ đường viền

        g2.dispose();

        return roundedImage;
    }

    public void init() {
        content.setVerticalScrollBar(new ScrollBarr());

        setHoverCursor(table1);
        TableColumn nameColumn = table1.getColumnModel().getColumn(0);
        nameColumn.setPreferredWidth(290); // Đặt chiều ngang mong muốn

        TableColumn ageColumn = table1.getColumnModel().getColumn(1);
        ageColumn.setPreferredWidth(200); // Đặt chiều ngang mong muốn

        TableColumnModel genderColumn = table1.getColumnModel();
        TableColumn columnGender = genderColumn.getColumn(2);
        columnGender.setMinWidth(0);
        columnGender.setMaxWidth(0);
        columnGender.setWidth(0);
        columnGender.setPreferredWidth(0);
        columnGender.setResizable(false);

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbanh = new javax.swing.JLabel();
        panel1 = new swing.Panel();
        panel2 = new swing.Panel();
        content = new javax.swing.JScrollPane();
        table1 = new Table.TableMusic();
        jLabel5 = new javax.swing.JLabel();
        lbMoTa = new javax.swing.JLabel();
        lbTen = new javax.swing.JLabel();
        button1 = new swing.Button();
        lbTim = new javax.swing.JLabel();
        btnTim = new swing.Button();

        setBackground(new java.awt.Color(29, 38, 49));

        panel1.setBackground(new java.awt.Color(51, 59, 66));
        panel1.setForeground(new java.awt.Color(51, 59, 66));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panel2.setBackground(new java.awt.Color(40, 49, 60));
        panel2.setForeground(new java.awt.Color(40, 49, 60));
        panel2.setFocusable(false);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        content.setBackground(new java.awt.Color(29, 38, 49));
        content.setForeground(new java.awt.Color(255, 255, 255));

        table1.setBorder(null);
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bài Hát", "Ca Sĩ", "Lượt Nghe", "Thời Gian", ""
            }
        ));
        table1.setFocusable(false);
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        content.setViewportView(table1);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Lời tựa:");

        lbMoTa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMoTa.setForeground(new java.awt.Color(204, 204, 204));
        lbMoTa.setText("Thật là cảm xúc");

        lbTen.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbTen.setForeground(new java.awt.Color(255, 255, 255));
        lbTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTen.setText("Cảm Xúc Khi Yêu");

        button1.setBackground(new java.awt.Color(33, 42, 58));
        button1.setText("Danh Sách Phát");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        lbTim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTim.setForeground(new java.awt.Color(204, 204, 204));
        lbTim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTim.setText("16K Người Yêu Thích");

        btnTim.setBackground(new java.awt.Color(29, 38, 49));
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/heart24null.png"))); // NOI18N
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbTen, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbanh, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbMoTa))
                .addGap(103, 103, 103)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lbanh, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lbTim)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 70, Short.MAX_VALUE)
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
//        edit();
    }//GEN-LAST:event_table1MouseClicked

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        tim();
    }//GEN-LAST:event_btnTimActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnTim;
    private swing.Button button1;
    private javax.swing.JScrollPane content;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lbMoTa;
    private javax.swing.JLabel lbTen;
    private javax.swing.JLabel lbTim;
    private javax.swing.JLabel lbanh;
    private swing.Panel panel1;
    private swing.Panel panel2;
    private Table.TableMusic table1;
    // End of variables declaration//GEN-END:variables
}
