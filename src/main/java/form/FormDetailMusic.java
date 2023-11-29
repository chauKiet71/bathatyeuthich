package form;

import entity.BaiHatEntity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import saveEvent.MusicDetails;
import swing.ScrollBarr;
import utils.EventManager;
import utils.linkDetails;

public class FormDetailMusic extends javax.swing.JPanel {

    MusicDetails manager;

    public FormDetailMusic() {
        initComponents();

        content.setVerticalScrollBar(new ScrollBarr());
        content.setBackground(new Color(29, 38, 49));

        EventManager.addListener(FormTrangChu.DETAILS_SELECTED_EVENT, (event, data) -> {
            if (data instanceof BaiHatEntity) {
                BaiHatEntity selectedBaiHat = (BaiHatEntity) data;
                manager = new MusicDetails();
                manager.setSelectedBaiHat(selectedBaiHat);
            }
        });

        fileTxt();
        fileTxtMota();
    }

    void fileTxt() {
        String linkBH = manager.getSelectedBaiHat().getLoiBh();
        String linkAnh = manager.getSelectedBaiHat().getAnh();
        String tenBaiHat = manager.getSelectedBaiHat().getTenBh();
        String caSi = manager.getSelectedBaiHat().getCaSi();
        int view = manager.getSelectedBaiHat().getSoluotNghe();
        System.out.println("link anh bai hat" + linkAnh);
//        String path = "D:\\loinho.txt"; 
        String absolutePath = new File(linkBH).getAbsolutePath();
        try {
            //tạo đối tượng file  
            File file = new File(absolutePath);
            //kiểm tra xem file có tồn tại hay không
            if (file.exists()) {
                //tạo đối tượng filereader và BufferedReader
                FileReader filereder = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereder);
                //đọc nội dung từ file
                StringBuffer content = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                    content.append("<br>");
                }
                bufferedReader.close();
                filereder.close();
                lbInNhac.setText("<html>" + content.toString() + "</html");
//                lbInNhac.setFont(new Font("Arial", Font.BOLD, 15) {
//                });
                //in ảnh lên lable
//                lbInNhac.setForeground(Color.white);
                lbTenBH.setText(tenBaiHat);
                lbCaSi.setText(caSi);
                lbView.setText("" + view + " lượt nghe");

                try {
                    BufferedImage originalImage = ImageIO.read(getClass().getResource(linkAnh));

                    int width = 230;
                    int height = 230;

                    // Tạo ảnh mới với đường viền bo góc và kích thước vừa với lbanh
                    BufferedImage roundedImage = createRoundedImage(originalImage, width, height);

                    // Tạo ImageIcon từ ảnh mới và thiết lập cho lbanh
                    ImageIcon roundedImageIcon = new ImageIcon(roundedImage);
                    lbAnh.setIcon(roundedImageIcon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    tạo ảnh bo tròn 4 góc và có border
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

    //txt mota
    void fileTxtMota() {
        String linkMt = manager.getSelectedBaiHat().getMoTa();
        String absolutePath = new File(linkMt).getAbsolutePath();
        try {
            //tạo đối tượng file  
            File file = new File(absolutePath);
            //kiểm tra xem file có tồn tại hay không
            if (file.exists()) {
                //tạo đối tượng filereader và BufferedReader
                FileReader filereder = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereder);
                //đọc nội dung từ file
                StringBuffer content = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                    content.append("<br>");
                }
                bufferedReader.close();
                filereder.close();
                lbMoTa.setText("<html>" + content.toString() + "</html");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbAnh = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbTenBH = new javax.swing.JLabel();
        imageAvarta1 = new swing.ImageAvarta();
        lbCaSi = new javax.swing.JLabel();
        lbView = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbMoTa = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        content = new javax.swing.JScrollPane();
        lbInNhac = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(29, 38, 49));

        lbAnh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setBackground(new java.awt.Color(29, 38, 49));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Bài hát:");
        jLabel1.setOpaque(true);

        lbTenBH.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lbTenBH.setForeground(new java.awt.Color(255, 255, 255));
        lbTenBH.setText("Lạc Trôi");

        lbCaSi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbCaSi.setForeground(new java.awt.Color(204, 204, 204));
        lbCaSi.setText("Sơn Tùng M-TP");

        lbView.setBackground(new java.awt.Color(29, 38, 49));
        lbView.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbView.setForeground(new java.awt.Color(204, 204, 204));
        lbView.setText("1.00.000.000 lượt nghe");
        lbView.setOpaque(true);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("02/12/2004");

        lbMoTa.setBackground(new java.awt.Color(29, 38, 49));
        lbMoTa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbMoTa.setForeground(new java.awt.Color(204, 204, 204));
        lbMoTa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbMoTa.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(33, 42, 53));

        content.setBackground(new java.awt.Color(29, 38, 49));
        content.setBorder(null);
        content.setForeground(new java.awt.Color(29, 38, 49));
        content.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        content.setHorizontalScrollBar(null);

        lbInNhac.setBackground(new java.awt.Color(33, 42, 53));
        lbInNhac.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        lbInNhac.setForeground(new java.awt.Color(255, 255, 255));
        lbInNhac.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbInNhac.setOpaque(true);
        content.setViewportView(lbInNhac);

        jLabel2.setBackground(new java.awt.Color(29, 38, 49));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("    Lời bài hát:");
        jLabel2.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 23, Short.MAX_VALUE)
                    .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 395, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 32, Short.MAX_VALUE)
                    .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(imageAvarta1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(lbCaSi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbTenBH, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lbView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lbTenBH))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imageAvarta1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lbCaSi)))
                        .addGap(12, 12, 12)
                        .addComponent(lbView)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane content;
    private swing.ImageAvarta imageAvarta1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbAnh;
    private javax.swing.JLabel lbCaSi;
    private javax.swing.JLabel lbInNhac;
    private javax.swing.JLabel lbMoTa;
    private javax.swing.JLabel lbTenBH;
    private javax.swing.JLabel lbView;
    // End of variables declaration//GEN-END:variables
}
