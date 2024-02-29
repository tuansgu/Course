/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.course.GUI;

import com.mycompany.course.BLL.CourseBLL;
import com.mycompany.course.BLL.CourseInstructorBLL;
import com.mycompany.course.BLL.OnLineCourseBLL;
import com.mycompany.course.BLL.OnSiteCourseBLL;
import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.CourseInstructorDTO;
import com.mycompany.course.DTO.OnLineCourseDTO;
import com.mycompany.course.DTO.OnSiteCourseDTO;
import com.mycompany.course.DTO.PersonDTO;
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
    private CourseDTO Course;
    private PersonDTO Person;
    private CourseInstructorDTO courseInstructorDto;
    private ArrayList<CourseInstructorDTO> courseInstructorList;
    private DefaultTableModel tableCourseInstructor;
    private DefaultTableModel tableModel;
    CourseBLL course = new CourseBLL();
    CourseInstructorBLL courseInstructor = new CourseInstructorBLL();
    JPopupMenu popupMenu = new JPopupMenu();
    JPopupMenu jPopupMenu = new JPopupMenu();
    JMenuItem editMenuItem;
    JMenuItem deleteMenuItem;
    JMenuItem viewDetailsMenuItem;

    public HomeGUI() {

        initComponents();
        this.setLocationRelativeTo(null);
        tableModel = (DefaultTableModel) jTable1.getModel();
        tableCourseInstructor = (DefaultTableModel) courseInstructorTable.getModel();

        editMenuItem = new JMenuItem("Edit");
        deleteMenuItem = new JMenuItem("Delete");
        viewDetailsMenuItem = new JMenuItem("View Details");
        
        JMenuItem editItem = new JMenuItem("Edit");
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem viewDetailsItem = new JMenuItem("View Details");

        popupMenu.add(editMenuItem);
        popupMenu.add(deleteMenuItem);
        popupMenu.add(viewDetailsMenuItem);
        
        jPopupMenu.add(editItem);
        jPopupMenu.add(deleteItem);
        jPopupMenu.add(viewDetailsItem);

        loadCourse();
        loadCourseInstructor();
        loadCourseIdInCombobox();
        loadPersonIdInCombobox();
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
        
        editItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = courseInstructorTable.getSelectedRow();

                if (selectedRow != -1) {
                    // Lấy dữ liệu từ hàng được chọn
                    Object CourseID = courseInstructorTable.getValueAt(selectedRow, 0);
                    Object PersonID = courseInstructorTable.getValueAt(selectedRow, 2);

                    EditCourseInstructorDig dialog = new EditCourseInstructorDig(new javax.swing.JFrame(), true, CourseID.toString(), PersonID.toString());
                    dialog.setVisible(true);
                }
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = courseInstructorTable.getSelectedRow();

                if (selectedRow != -1) {
                    int CourseID = (int) courseInstructorTable.getValueAt(selectedRow, 0);
                    int PersonID = (int) courseInstructorTable.getValueAt(selectedRow, 2);
                    if (courseInstructor.checkCourseActive(CourseID) != -1) {
                        JOptionPane.showMessageDialog(null, "Delete failed! Course is active", "Fail", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int deletionResult = courseInstructor.deleteCourseInstructor(CourseID);
                        if (deletionResult == 1) {
                            loadCourseIdInCombobox();
                            loadCourseInstructor();
                            JOptionPane.showMessageDialog(null, "Delete successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Delete failed! An error occurred", "Fail", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }
            }
        });

        viewDetailsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = courseInstructorTable.getSelectedRow();

                if (selectedRow != -1) {
                    // Lấy dữ liệu từ hàng được chọn
                    int CourseID = (int) courseInstructorTable.getValueAt(selectedRow, 0);
                    int PersonID = (int) courseInstructorTable.getValueAt(selectedRow, 2);

                    // Khởi tạo đối tượng DetailCourseInstructor
                    DetailCourseInstructor dialog = new DetailCourseInstructor(new javax.swing.JFrame(), true, CourseID, PersonID);
                    dialog.setVisible(true);
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

    public void loadCourseInstructor() {
        courseInstructorList = courseInstructor.getCourseInstructor();

        tableCourseInstructor.setRowCount(0);
        for (CourseInstructorDTO dto : courseInstructorList) {
            Course = courseInstructor.getCourseByID(dto.getCourseID());
            Person = courseInstructor.getPersonByPersonID(dto.getPersonID());
            Vector vec = new Vector();
            vec.add(Course.getCourseID());
            vec.add(Course.getTitle());
            vec.add(Person.getPersonID());
            vec.add(Person.getFirstName());
            tableCourseInstructor.addRow(vec);
        }
    }

    public void loadCourseIdInCombobox() {
        int[] courseId = new int[0];
        String title = "";
        String courseType = "";
        courseId = course.getAllCourseId();

        courseIdCombobox.removeAllItems();
        courseIdCombobox.addItem("Select course id");

        for (int item : courseId) {
            title = course.getTitleById(item);
            courseType = course.getCourseTypeById(item);
            courseIdCombobox.addItem(String.valueOf(item) + " - " + title + " - " + courseType);
        }

        courseIdCombobox.setSelectedIndex(0);
    }

    public void loadPersonIdInCombobox() {
        int[] personId = new int[0];
        personId = courseInstructor.getAllPersonId();

        personIdCombobox.removeAllItems();
        personIdCombobox.addItem("Select person id");

        for (int item : personId) {
            Person = courseInstructor.getNamePersonById(item);
            personIdCombobox.addItem(String.valueOf(item) + " - " + Person.getFirstName() + " " + Person.getLastName());
        }

        personIdCombobox.setSelectedIndex(0);
    }

    public void loadSearchCourseInstructor(String selectedValue) {
        courseInstructorList = courseInstructor.findCoursesInstructorByTitle(selectedValue);
        System.out.println(selectedValue);
        tableCourseInstructor.setRowCount(0);
        for (CourseInstructorDTO dto : courseInstructorList) {
            Course = courseInstructor.getCourseByID(dto.getCourseID());
            Person = courseInstructor.getPersonByPersonID(dto.getPersonID());
            Vector vec = new Vector();
            vec.add(Course.getCourseID());
            vec.add(Course.getTitle());
            vec.add(Person.getPersonID());
            vec.add(Person.getFirstName());
            tableCourseInstructor.addRow(vec);
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
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        courseInstructorTable = new javax.swing.JTable();
        courseIdCombobox = new javax.swing.JComboBox<>();
        personIdCombobox = new javax.swing.JComboBox<>();
        inputSearchTextField = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        reloadBtn = new javax.swing.JButton();
        addCourseInstructorCombobox = new javax.swing.JButton();
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1113, Short.MAX_VALUE)
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

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("List Courses Instructor"));

        courseInstructorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CourseID", "Title", "PersonID", "FirstName"
            }
        ));
        courseInstructorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                courseInstructorTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(courseInstructorTable);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addContainerGap())
        );

        courseIdCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseIdComboboxActionPerformed(evt);
            }
        });

        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        reloadBtn.setText("Reload");
        reloadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadBtnActionPerformed(evt);
            }
        });

        addCourseInstructorCombobox.setText("Add");
        addCourseInstructorCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCourseInstructorComboboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(courseIdCombobox, 0, 282, Short.MAX_VALUE)
                        .addComponent(personIdCombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(addCourseInstructorCombobox)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(inputSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(searchBtn)
                        .addGap(27, 27, 27)
                        .addComponent(reloadBtn))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn)
                    .addComponent(reloadBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(courseIdCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(personIdCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(addCourseInstructorCombobox))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Phân công giảng dạy", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1137, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
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

    private void courseInstructorTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseInstructorTableMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = courseInstructorTable.rowAtPoint(evt.getPoint());
            courseInstructorTable.getSelectionModel().setSelectionInterval(row, row);
            jPopupMenu.show(courseInstructorTable, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_courseInstructorTableMouseClicked

    private void courseIdComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseIdComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_courseIdComboboxActionPerformed

    private void addCourseInstructorComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCourseInstructorComboboxActionPerformed
        try {
            String stringValue1 = (String) courseIdCombobox.getSelectedItem();
            String[] parts1 = stringValue1.split(" - ");
            String CourseId = parts1[0];
            int value1 = Integer.parseInt(CourseId);

            String stringValue2 = (String) personIdCombobox.getSelectedItem();
            String[] parts2 = stringValue2.split(" - ");
            String PersonId = parts2[0];
            int value2 = Integer.parseInt(PersonId);

            courseInstructorDto = new CourseInstructorDTO();

            courseInstructorDto.setCourseID(value1);
            courseInstructorDto.setPersonID(value2);

            int rs = courseInstructor.insertCourseInstructor(courseInstructorDto);
            if (rs == 1) {
                JOptionPane.showMessageDialog(null, "Insert successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadCourseInstructor();
                loadCourseIdInCombobox();
                loadPersonIdInCombobox();
            } else {
                JOptionPane.showMessageDialog(null, "Insert failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please select valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addCourseInstructorComboboxActionPerformed

    private void reloadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadBtnActionPerformed
        loadCourseInstructor();
    }//GEN-LAST:event_reloadBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String valueInput = (String) inputSearchTextField.getText();
        loadSearchCourseInstructor(valueInput);
    }//GEN-LAST:event_searchBtnActionPerformed

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
    private javax.swing.JButton addCourseInstructorCombobox;
    private javax.swing.JComboBox<String> courseIdCombobox;
    private javax.swing.JTable courseInstructorTable;
    private javax.swing.JTextField inputSearchTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JComboBox<String> personIdCombobox;
    private javax.swing.JButton reloadBtn;
    private javax.swing.JButton searchBtn;
    // End of variables declaration//GEN-END:variables
}
