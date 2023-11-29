package component2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Model_Chill;

public class ItemChill extends javax.swing.JPanel {
    
    public Model_Chill getData() {
        return data;
    }
    
    private final Model_Chill data;
    
    public ItemChill(Model_Chill data) {
        this.data = data;
        initComponents();
        setLabel(lbMota);
        setOpaque(false);
        lbMota.setText(data.getTitle());
        if (data.getImage() != null) {
            roundedImage.setImage(data.getImage());
        }
    }
    
    public void setLabel(JLabel label) {
        label.setPreferredSize(new Dimension(210, 60)); // Kích thước chiều ngang cố định
        label.setHorizontalAlignment(SwingConstants.LEFT); // Canh lề trái

        // Set dấu "..." nếu vượt quá chiều ngang và 2 dòng
        label.setVerticalTextPosition(SwingConstants.TOP);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setMaximumSize(new Dimension(210, 60));
    }
    
    private static void setHandCursor(Component component) {
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
        component.setCursor(handCursor);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedImage = new swing.RoundedImage();
        lbMota = new javax.swing.JLabel();

        roundedImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roundedImageMouseClicked(evt);
            }
        });

        lbMota.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbMota.setForeground(new java.awt.Color(255, 255, 255));
        lbMota.setText("Chill");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbMota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundedImage, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundedImage, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMota, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundedImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundedImageMouseClicked
       JOptionPane.showMessageDialog(null, "Hello");
    }//GEN-LAST:event_roundedImageMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbMota;
    private swing.RoundedImage roundedImage;
    // End of variables declaration//GEN-END:variables
}
