package List;

import component2.ListChill;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import model.Model_Chill;

public class Chill extends javax.swing.JPanel {

    public Chill() {
        initComponents();
        init();
    }

    public void init() {
        listChill1.addItem(new Model_Chill(new ImageIcon(getClass().getResource("/icon/ChillForm/5.jpg")), "Nhạc Chill Iu Đời"));
        listChill2.addItem(new Model_Chill(new ImageIcon(getClass().getResource("/icon/ChillForm/7.jpg")), "Nhạc Chill Tâm Trạng Buồn"));
        listChill3.addItem(new Model_Chill(new ImageIcon(getClass().getResource("/icon/ChillForm/13.jpg")), "Tình Iu Cực Chill"));
        listChill4.addItem(new Model_Chill(new ImageIcon(getClass().getResource("/icon/ChillForm/3.jpg")), "Nhạc Lâu Phai Chill Gây Nghiện"));

        setHoverCursor(listChill1);
        setHoverCursor(listChill2);
        setHoverCursor(listChill3);
        setHoverCursor(listChill4);
    }

// Tương tự cho listChill2, listChill3, listChill4
    private static void setHoverCursor(ListChill<?> listChill) {
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

        jLabel1 = new javax.swing.JLabel();
        listChill1 = new component2.ListChill<>();
        listChill2 = new component2.ListChill<>();
        listChill3 = new component2.ListChill<>();
        listChill4 = new component2.ListChill<>();

        setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Chill");

        listChill1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listChill1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                listChill1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                listChill1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(listChill1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(listChill2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(listChill3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(listChill4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listChill1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listChill2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listChill3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listChill4, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listChill1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listChill1MouseEntered
//        setHandCursor(listChill1);
    }//GEN-LAST:event_listChill1MouseEntered

    private void listChill1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listChill1MouseExited
//        setDefaultCursor(listChill1)
    }//GEN-LAST:event_listChill1MouseExited

    private void listChill1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listChill1MouseClicked
        listChill1.sout();
    }//GEN-LAST:event_listChill1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private component2.ListChill<String> listChill1;
    private component2.ListChill<String> listChill2;
    private component2.ListChill<String> listChill3;
    private component2.ListChill<String> listChill4;
    // End of variables declaration//GEN-END:variables
}
