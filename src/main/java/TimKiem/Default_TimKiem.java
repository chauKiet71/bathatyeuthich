package TimKiem;

import Table.Model_Table;
import Table.tableDAO;
import dao.BaiHatDAO;
import entity.BaiHatEntity;
import static form.FormTrangChu.BAI_HAT_SELECTED_EVENT;
import static form.FormTrangChu.DETAILS_SELECTED_EVENT;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import saveEvent.BaiHatStateManager;
import saveEvent.MusicDetails;
import utils.EventManager;

public class Default_TimKiem extends javax.swing.JPanel {

    BaiHatDAO bhDao = new BaiHatDAO();
    tableDAO tbDao = new tableDAO();
    private static BaiHatEntity bh;

    public Default_TimKiem() {
        initComponents();
        init();
        fillTable();
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
        System.out.println("xin chào");
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

    void fillTable() {
        String sql_selectBySoLuotNghe = "select * from BaiHat where SoLuotNghe > 500 order by SoLuotNghe desc";
        try {
            List<Model_Table> list = tbDao.selectBySoLuotNghe(sql_selectBySoLuotNghe);
            for (Model_Table bh : list) {
                table1.addRow(new Model_Table(bh.getIcon(), bh.getName(), bh.getSing(), bh.getView(), bh.getTime(), bh.getMaBh()).toRowTable());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        table1 = new Table.TableMusic();

        setBackground(new java.awt.Color(29, 38, 49));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bài Hát Được Tìm Kiếm Nhiều Nhất");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(table1, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(table1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        edit();
    }//GEN-LAST:event_table1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private Table.TableMusic table1;
    // End of variables declaration//GEN-END:variables
}
