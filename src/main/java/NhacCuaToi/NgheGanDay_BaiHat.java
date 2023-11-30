/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package NhacCuaToi;

import Table.Model_Table;

import Table.tableDAO;
import dao.NgheGanDayDAO;
import entity.AccountData;
import entity.BaiHatEntityTest;
import entity.NgheGanDay;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TRONG NGHIA
 */
public class NgheGanDay_BaiHat extends javax.swing.JPanel {

    /**
     * Creates new form NgheGanDay_BaiHat
     */
    public NgheGanDay_BaiHat() {
        initComponents();
        String maBh = AccountData.getSavaMaBh();
        System.out.println("ma bai hat o nghe gan day  ----   " + maBh);
        fillTable();
    }
    private static BaiHatEntityTest bh;
    private static NgheGanDay ngd;
    private static tableDAO tbDAO = new tableDAO();
    private static NgheGanDayDAO ngdDAO = new NgheGanDayDAO();
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
            List<NgheGanDay> list = ngdDAO.selectById(tentk);
            if (list != null) {
                for (NgheGanDay entity : list) {
                    Model_Table load = tbDAO.selectByIdMaBh(entity.getMaBh());
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


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        table1 = new Table.TableMusic();

        setBackground(new java.awt.Color(29, 38, 49));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Sing", "View", "Time", "Title 5"
            }
        ));
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
                .addGap(21, 21, 21)
                .addComponent(table1, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(table1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked

    }//GEN-LAST:event_table1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Table.TableMusic table1;
    // End of variables declaration//GEN-END:variables
}
