package TimKiem;

import Table.Model_Table;
import Table.tableDAO;
import dao.BaiHatDAO;
import entity.BaiHatEntity;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JComponent;
import utils.EventManager;

public class FormTimKiem2 extends javax.swing.JPanel {
    
    BaiHatDAO bhDao = new BaiHatDAO();
    tableDAO tbDao = new tableDAO();
    public static final String BAI_HAT_SEARCHED1 = "BAI_HAT_SEARCHED1";
    public static final String BAI_HAT_SEARCHED2 = "BAI_HAT_SEARCHED2";
    private static List<BaiHatEntity> listBh;
    private static List<Model_Table> listTable;
    
    public FormTimKiem2() {
        initComponents();
        init();
        
    }
    
    public void init() {
        Default_TimKiem wel = new Default_TimKiem();
        addComponent(wel);
    }
    
    public void search() {
        String textSearch = txtSearch.getText();
        String query = "SELECT * FROM BaiHat WHERE TenBH COLLATE Latin1_General_CI_AI LIKE N'%" + textSearch + "%'";
//        listBh = bhDao.selectByNameMusic(query);
        listTable = tbDao.selectByNameMusic(query);
        if (listTable != null) {
            EventQueue.invokeLater(() -> {
                EventManager.fireEvent(BAI_HAT_SEARCHED2, listTable);
                EventManager.fireEvent(BAI_HAT_SEARCHED1, textSearch);
            });
            NotNull wel = new NotNull();
            addComponent(wel);
        } else {
            EventQueue.invokeLater(() -> {
                EventManager.fireEvent(BAI_HAT_SEARCHED2, textSearch);
            });
            Null wel = new Null();
            addComponent(wel);
        }
    }
    
    public void addComponent(JComponent com) {
        com.setSize(882, 635);
        com.setLocation(0, 0);
        
        content.removeAll();
        content.add(com, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

//    private static void setHoverCursor(JLabel label) {
//        label.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                label.setBackground(new Color(39, 50, 60));
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                label.setBackground(new Color(32, 42, 53));
//            }
//        });
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearch = new swing.TextFieldAnimation();
        content = new javax.swing.JPanel();

        setBackground(new java.awt.Color(29, 38, 49));

        txtSearch.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        content.setOpaque(false);

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 882, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        search();
    }//GEN-LAST:event_txtSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    private swing.TextFieldAnimation txtSearch;
    // End of variables declaration//GEN-END:variables
}
