package TimKiem;

import Table.Model_Table;
import dao.BaiHatDAO;
import entity.BaiHatEntity;
import form.FormTrangChu;
import static form.FormTrangChu.BAI_HAT_SELECTED_EVENT;
import static form.FormTrangChu.DETAILS_SELECTED_EVENT;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import saveEvent.BaiHatStateManager;
import saveEvent.MusicDetails;
import saveEvent.SearchStateManager;
import saveEvent.SearchStateManagerTable;
import saveEvent.SearchedText;
import utils.EventManager;

public class NotNull extends javax.swing.JPanel {

    SearchStateManager manager = new SearchStateManager();
    SearchedText managerText = new SearchedText();
    SearchStateManagerTable managerTable = new SearchStateManagerTable();
    private static BaiHatEntity bh;
    BaiHatDAO bhDao = new BaiHatDAO();

    public NotNull() {
        initComponents();

        EventManager.addListener(FormTimKiem2.BAI_HAT_SEARCHED2, (event, data) -> {
            if (data instanceof List<?>) {
                List<Model_Table> selectedBaiHatTable = (List<Model_Table>) data;
                managerTable.setSearchedTable(selectedBaiHatTable);
                fillTable();
            }
        });

        EventManager.addListener(FormTimKiem2.BAI_HAT_SEARCHED2, (event, data) -> {
            if (data instanceof String) {
                String selectedBaiHatText = (String) data;
                managerText.setSearchedText(selectedBaiHatText);
                setLabel(managerText.getSearchedText());
            }
        });

        init();

    }
    
    public void setLabel(String string){
         
    }

    void fillTable() {
        try {
            List<Model_Table> list = managerTable.getSearchedTable();
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

    public void init() {
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

        jLabel1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        lb3 = new javax.swing.JLabel();
        lb4 = new javax.swing.JLabel();
        lb1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        table1 = new Table.TableMusic();

        setBackground(new java.awt.Color(29, 38, 49));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Lich Sử Tìm Kiếm");

        lb2.setBackground(new java.awt.Color(37, 47, 60));
        lb2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        lb3.setBackground(new java.awt.Color(37, 47, 60));
        lb3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lb3.setForeground(new java.awt.Color(204, 204, 204));
        lb3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        lb4.setBackground(new java.awt.Color(37, 47, 60));
        lb4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lb4.setForeground(new java.awt.Color(204, 204, 204));
        lb4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        lb1.setBackground(new java.awt.Color(37, 47, 60));
        lb1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lb1.setForeground(new java.awt.Color(204, 204, 204));
        lb1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel2.setBackground(new java.awt.Color(29, 38, 49));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Bài Hát");
        jLabel2.setOpaque(true);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(table1, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(table1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        edit();
    }//GEN-LAST:event_table1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private Table.TableMusic table1;
    // End of variables declaration//GEN-END:variables
}
