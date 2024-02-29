/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.course.GUI;

import com.mycompany.course.BLL.CourseBLL;
import com.mycompany.course.BLL.OnLineCourseBLL;
import com.mycompany.course.BLL.OnSiteCourseBLL;
import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.OnLineCourseDTO;
import com.mycompany.course.DTO.OnSiteCourseDTO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tuvu
 */
public class HomeGUI extends javax.swing.JFrame {

    /**
     * Creates new form HomeGUI
     */
    private ArrayList<CourseDTO> courselist;
    private DefaultTableModel tableModel;
    CourseBLL course = new CourseBLL();
    JPopupMenu popupMenu = new JPopupMenu();
    JMenuItem editMenuItem;
    JMenuItem deleteMenuItem;
    JMenuItem viewDetailsMenuItem;

    public HomeGUI() {

        initComponents();
        this.setLocationRelativeTo(null);
        tableModel = (DefaultTableModel) jTable1.getModel();

        editMenuItem = new JMenuItem("Edit");
        deleteMenuItem = new JMenuItem("Delete");
        viewDetailsMenuItem = new JMenuItem("View Details");

        popupMenu.add(editMenuItem);
        popupMenu.add(deleteMenuItem);
        popupMenu.add(viewDetailsMenuItem);

        loadCourse();
        // edit course
        editMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTable1.getSelectedRow();
                CourseDTO coursedto;
                if (selectedRow != -1) {
                    int courseID = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                    //System.out.println(" get courseId:"+courseID)
                    coursedto = course.getCourseById(courseID).get(0);
                    if (coursedto instanceof OnLineCourseDTO) {
                        EditCourseOnlieDig editOnline = new EditCourseOnlieDig(new HomeGUI(), true);
                        editOnline.setCourseId(courseID);
                        editOnline.setCourseOnline(
                                coursedto.getTitle(),
                                coursedto.getCredits(),
                                ((OnLineCourseDTO) coursedto).getUrl(),
                                coursedto.getDepartmentID()
                        );
                        editOnline.setVisible(true);
                    } else if (coursedto instanceof OnSiteCourseDTO) {
                        EditCourseSiteDig editOnsite = new EditCourseSiteDig(new HomeGUI(), true);
                        editOnsite.setCourseOnsite(coursedto.getTitle(),
                                coursedto.getCredits(),
                                ((OnSiteCourseDTO) coursedto).getLocation(),
                                ((OnSiteCourseDTO) coursedto).getDays(),
                                ((OnSiteCourseDTO) coursedto).getTime(),
                                coursedto.getDepartmentID());
                        editOnsite.setVisible(true);
                    }
                }
            }
        });
        // view detail
        viewDetailsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowDetail = jTable1.getSelectedRow();
                CourseDTO coursedto;
                if (selectedRowDetail != -1) {
                    int courseID = Integer.parseInt(tableModel.getValueAt(selectedRowDetail, 0).toString());
                    coursedto = course.getCourseById(courseID).get(0);
                    if (coursedto instanceof OnLineCourseDTO) {
                        DetailCourseOnlieDig detailOnline = new DetailCourseOnlieDig(new HomeGUI(), true);
                        detailOnline.setCourseOnline(
                                coursedto.getCourseID(),
                                coursedto.getTitle(),
                                coursedto.getCredits(),
                                ((OnLineCourseDTO) coursedto).getUrl(),
                                coursedto.getDepartmentID()
                        );
                        detailOnline.setVisible(true);
                    } else if (coursedto instanceof OnSiteCourseDTO) {
                        DetailCourseSiteDig detailOnsite = new DetailCourseSiteDig(new HomeGUI(), true);
                        detailOnsite.setCourseOnsite(
                                coursedto.getCourseID(),
                                coursedto.getTitle(),
                                coursedto.getCredits(),
                                coursedto.getDepartmentID(),
                                ((OnSiteCourseDTO) coursedto).getLocation(),
                                ((OnSiteCourseDTO) coursedto).getDays(),
                                ((OnSiteCourseDTO) coursedto).getTime());

                        detailOnsite.setVisible(true);
                    }
                }
            }

        });
//      // delete Course
        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowDelete = jTable1.getSelectedRow();
                CourseDTO coursedto;
                if (selectedRowDelete != -1) {
                    int courseID = Integer.parseInt(tableModel.getValueAt(selectedRowDelete, 0).toString());
                    coursedto = course.getCourseNotInstructorByID(courseID);
                    if (coursedto instanceof OnLineCourseDTO) {
                        if (coursedto != null) {
                            int a = JOptionPane.showConfirmDialog(null, "you definitely want to delete", "delete course", JOptionPane.YES_NO_OPTION);
                            if (a == JOptionPane.YES_OPTION) {
                                OnLineCourseBLL onlineBLL = new OnLineCourseBLL();
                                if (onlineBLL.deleteCourseOnline((OnLineCourseDTO) coursedto) > 0) {
                                    JOptionPane.showMessageDialog(rootPane, "Delete course succefull");
                                    loadCourse();
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, "Fail, Delte course fail", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } else if (coursedto instanceof OnSiteCourseDTO) {
                        if (coursedto != null) {
                            int a = JOptionPane.showConfirmDialog(null, "you definitely want to delete", "delete course", JOptionPane.YES_NO_OPTION);
                            if (a == JOptionPane.YES_OPTION) {
                                OnSiteCourseBLL onsiteBLL = new OnSiteCourseBLL();
                                OnLineCourseBLL onlineBLL = new OnLineCourseBLL();
                                if (onsiteBLL.deleteCourseOnsite((OnSiteCourseDTO) coursedto) > 0) {
                                    JOptionPane.showMessageDialog(rootPane, "Delete course succefull");
                                    loadCourse();
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, "Fail, Delte course fail", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Fail, This course can't be delete", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        });
    }

    public void loadCourse() {

        courselist = course.getAllCourses();
        tableModel.setRowCount(0);
        for (CourseDTO dto : courselist) {
            String type = dto instanceof OnLineCourseDTO ? "Online" : "offline";
            Vector vec = new Vector();
            vec.add(dto.getCourseID());
            vec.add(dto.getTitle());
            vec.add(dto.getCredits());
            vec.add(dto.getDepartmentID());
            vec.add(type);
            tableModel.addRow(vec);
        }
    }

    // search course
    public void searchCourse() {
        String searchText = jTextField5.getText().trim();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Fail, Please enter search", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(searchText);
            CourseDTO courseFoundByID = course.searchCourseById(id);
            if (courseFoundByID != null) {
                displayCourseID(courseFoundByID);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Not found course", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (NumberFormatException e) {
            ArrayList<CourseDTO> coursesFoundByTitle = course.searchCourseByTitle(searchText);
            if (!coursesFoundByTitle.isEmpty()) {
                displayCourseID(coursesFoundByTitle.get(0));
            } else {
                JOptionPane.showMessageDialog(rootPane, "Not found course", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayCourseID(CourseDTO course) {
        jTextField5.setText("");
        tableModel.setRowCount(0);
        String type = course instanceof OnLineCourseDTO ? "Online" : "offline";
        Vector<Object> vec = new Vector<>();
        vec.add(course.getCourseID());
        vec.add(course.getTitle());
        vec.add(course.getCredits());
        vec.add(course.getDepartmentID());
        vec.add(type);
        tableModel.addRow(vec);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField5.setBorder(new MatteBorder(1, 1, 1, 1,Color.BLACK));

        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(0, 102, 102));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("List Courses"));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 14))); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CourseID", "Title", "Credits", "DepartmentID", "Category"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1127, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addContainerGap())
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selected add course", "OnlineCourse", "OnsiteCourse" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Add Course");

        jButton1.setText("Reload");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Khóa Học", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Phân công giảng dạy", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Kết quả khóa học", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        searchCourse();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String selectedOption = (String) jComboBox1.getSelectedItem();
        if (selectedOption.equals("OnlineCourse")) {
            AddCourseOnlieDig addcourseonline = new AddCourseOnlieDig(new HomeGUI(), true);
            addcourseonline.setVisible(true);
        } else if (selectedOption.equals("OnsiteCourse")) {
            AddCourseSiteDig addcourseonline = new AddCourseSiteDig(new HomeGUI(), true);
            addcourseonline.setVisible(true);
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = jTable1.rowAtPoint(evt.getPoint());
            jTable1.getSelectionModel().setSelectionInterval(row, row);
            popupMenu.show(jTable1, evt.getX(), evt.getY());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        loadCourse();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(HomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
