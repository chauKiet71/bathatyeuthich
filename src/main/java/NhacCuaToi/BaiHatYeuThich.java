package NhacCuaToi;

import Table.Model_Table;
import Table.tableDAO;
import dao.BaiHatYeuThichDAO;
import dao.NgheGanDayDAO;
import entity.AccountData;
import entity.BaiHatEntityTest;
import java.util.ArrayList;
import java.util.List;

public class BaiHatYeuThich extends javax.swing.JPanel {

    public BaiHatYeuThich() {
        initComponents();
        fillTable();
    }

    private static BaiHatEntityTest bh;
    private static entity.NgheGanDay ngd;
    private static tableDAO tbDAO = new tableDAO();
    private static BaiHatYeuThichDAO bhytDao = new BaiHatYeuThichDAO();
    private static String tentk;

    public BaiHatEntityTest getEntity() {
        BaiHatEntityTest entity = new BaiHatEntityTest();
        entity.setAnh(bh.getAnh());
        entity.setTenBh(bh.getTenBh());
        entity.setCaSi(bh.getCaSi());
        entity.setSoluotNghe(bh.getSoluotNghe());
        entity.setThoiGian(bh.getThoiGian());
        entity.setMaBh(bh.getMaBh());
        return entity;
    }

    void fillTable() {
        List<Model_Table> listmusic = new ArrayList<Model_Table>();
        try {
            tentk = AccountData.getTenTK();
            List<entity.BaiHatYeuThich> list = bhytDao.selectById(tentk);
            if (list != null) {
                for (entity.BaiHatYeuThich entity : list) {
                    Model_Table load = tbDAO.selectByIdMaBh(entity.getMaBH());
                    listmusic.add(load);
                    System.out.println("testt cu" + load);
                }
                for (Model_Table bh : listmusic) {
                    table1.addRow(new Model_Table(bh.getIcon(), bh.getName(), bh.getSing(), bh.getView(), bh.getTime(), bh.getMaBh()).toRowTable());
                }
            } else {
                System.out.println("Không tìm thấy dữ liệu.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        table1 = new Table.TableMusic();
        jLabel1 = new javax.swing.JLabel();
        button3 = new swing.Button();

        setBackground(new java.awt.Color(29, 38, 49));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Sing", "View", "Time"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 153));
        jLabel1.setText("Thư Viện");

        button3.setBackground(new java.awt.Color(204, 204, 204));
        button3.setForeground(new java.awt.Color(255, 51, 153));
        button3.setText("+");
        button3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(table1, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(table1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(489, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked

    }//GEN-LAST:event_table1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button button3;
    private javax.swing.JLabel jLabel1;
    private Table.TableMusic table1;
    // End of variables declaration//GEN-END:variables
}
