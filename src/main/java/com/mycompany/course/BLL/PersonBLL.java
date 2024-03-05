/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.BLL;

import com.mycompany.course.DAL.PersonDAL;
import com.mycompany.course.DTO.PersonDTO;

/**
 *
 * @author phanq
 */
public class PersonBLL {

    public static boolean addNewStudent(PersonDTO personDTO) {
        return PersonDAL.getInstance().addNewStudent(personDTO);
    }

}
