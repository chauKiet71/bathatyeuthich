package TimKiem;

import saveEvent.SearchedText;
import utils.EventManager;

public class Null extends javax.swing.JPanel {

    SearchedText manager = new SearchedText();

    public Null() {
        initComponents();
        EventManager.addListener(FormTimKiem2.BAI_HAT_SEARCHED2, (event, data) -> {
            if (data instanceof String) {
                String selectedBaiHatText = (String) data;
                manager.setSearchedText(selectedBaiHatText);
                setLabel(manager.getSearchedText());
            }
        });
    }

    public void setLabel(String string) {
        lbText.setText("Không có kết quả nào cho " + string + ". Vui lòng kiểm tra lại chính tả của từ khóa");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbText = new javax.swing.JLabel();

        setBackground(new java.awt.Color(29, 38, 49));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/null200.png"))); // NOI18N

        lbText.setBackground(new java.awt.Color(29, 38, 49));
        lbText.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lbText.setForeground(new java.awt.Color(204, 204, 204));
        lbText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbText.setText("Không có kết quả nào cho gì đó. Vui lòng kiểm tra lại chính tả của từ khóa");
        lbText.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbText, javax.swing.GroupLayout.DEFAULT_SIZE, 882, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbText, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(235, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbText;
    // End of variables declaration//GEN-END:variables
}
