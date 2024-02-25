
package com.mycompany.course.DAL;

import com.mycompany.course.DTO.OnLineCourseDTO;
import com.mycompany.course.DTO.OnSiteCourseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author pc
 */
public class OnSiteCourseDAL {
    public int insertCourseOnsite(OnSiteCourseDTO dto) {
        int result = -1;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "INSERT INTO onsitecourse (CourseID, Location,Days,Time) VALUES (?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, dto.getCourseID());
            st.setString(2, dto.getLocation());
            st.setString(3, dto.getDays());
            st.setTime(4, dto.getTime());
            result = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return result;
    }
}
