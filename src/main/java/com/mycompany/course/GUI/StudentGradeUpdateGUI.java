/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.course.GUI;

import com.mycompany.course.BLL.StudentGradeBLL;
import com.mycompany.course.DTO.StudentGradeDTO;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phanq
 */
public class StudentGradeUpdateGUI extends javax.swing.JFrame {

    /**
     * Creates new form StudentGradeAddGUI
     */
    public int id;
    public float grade;

    public StudentGradeUpdateGUI() {
        initComponents();
        try {
            loadDataStudentGrade();
        } catch (SQLException ex) {
            Logger.getLogger(StudentGradeUpdateGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadDataStudentGrade() throws SQLException {

        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        while (jTable1.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        ArrayList<StudentGradeDTO> list = StudentGradeBLL.getAll();
        for (int i = 0; i < list.size(); i++) {
            String title = StudentGradeBLL.getTitle(list.get(i).getCourseID()).getTitle();
            String lastName = StudentGradeBLL.getName(list.get(i).getStudentID()).getLastName();
            String firstName = StudentGradeBLL.getName(list.get(i).getStudentID()).getFirstName();

            tableModel.addRow(new Object[]{list.get(i).getEnrollmentID(),
                list.get(i).getCourseID(), title,
                list.get(i).getStudentID(),
                lastName + " " + firstName,
                list.get(i).getGrade()}
            );
        }
    }

    public boolean update(int currentRow, int rowCount, int colCount) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        boolean check = false;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                try {
                    StudentGradeDTO studentGradeDTO = new StudentGradeDTO();
                    studentGradeDTO.setEnrollmentID(Integer.parseInt(model.getValueAt(currentRow, 0).toString()));
                    studentGradeDTO.setGrade(Float.parseFloat(model.getValueAt(currentRow, 5).toString()));
                    check = StudentGradeBLL.updateStudentGrade(studentGradeDTO);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi cập nhật điểm", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (check) {
            JOptionPane.showMessageDialog(rootPane, "Cập nhật điểm thành công");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Cập nhật điểm không thành công");
        }
        return check;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EnrollmentID", "CourseID", "Title", "StudenID", "Name", "Grade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(35);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cập nhật bảng điểm sinh viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        int currentRow = jTable1.getSelectedRow();
        int currentColumn = jTable1.getSelectedColumn();
        int rowCount = jTable1.getRowCount();
        int columnCount = jTable1.getColumnCount();

        try {
            jTable1.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {

                    jTable1.getCellEditor();

                }
            });
            // Tạo KeyBinding cho Enter
            String enterActionKey = "ENTER_ACTION";
            jTable1.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enterActionKey);
            jTable1.getActionMap().put(enterActionKey, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (currentColumn == columnCount - 1) {
                        if (currentRow == rowCount - 1) {
                            jTable1.changeSelection(0, currentColumn, false, false);
                            System.out.println("AAAAAA\n");
                        } else {

                            jTable1.changeSelection(currentRow + 1, currentColumn, false, false);
                            System.out.println("BBBBBBB\n");
                        }

                    } else {
                        jTable1.changeSelection(currentRow, currentColumn + 1, false, false);
                        System.out.println("CCCCCCC\n");
                    }

                    System.out.println("DDDDDDDDD\n");
                    jTable1.getCellEditor();
                    jTable1.editCellAt(jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                    update(currentRow, rowCount, columnCount);
                }
            });

        } catch (Exception e) {
            System.err.println("Loi: " + e);
        }
    }//GEN-LAST:event_jTable1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentGradeUpdateGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentGradeUpdateGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentGradeUpdateGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentGradeUpdateGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentGradeUpdateGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
