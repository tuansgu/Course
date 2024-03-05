/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.DAL;

import com.mycompany.course.DTO.PersonDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author phanq
 */
public class PersonDAL {

    public static PersonDAL getInstance() {
        return new PersonDAL();
    }

    public boolean addNewStudent(PersonDTO personDTO) {
        boolean result = false;
        try {
            String query = "INSERT INTO `person`(`Lastname`, `Firstname`, `EnrollmentDate`) VALUES (?,?,?)";
            Connection conn = MyConnection.connect();
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, personDTO.getLastName());
            pst.setString(2, personDTO.getFirstName());
            pst.setString(3, personDTO.getEnrollmentDate().toString());

            result = pst.executeUpdate() > 0;
            System.out.println(pst);
            MyConnection.close(conn);
        } catch (Exception e) {
            System.err.println(e);
        }
        return result;
    }
}
