/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.tpmgt.dao.custom.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;

import lk.ijse.tpmgt.dao.custom.QueryDAO;
import lk.ijse.tpmgt.db.HibernateUtil;
import lk.ijse.tpmgt.entity.TripDetail;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;


public class QueryDAOImpl implements QueryDAO {


    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }


    @Override
    public BigDecimal getTotalDistanceForDriver(String startDate, String curdate, String nic) throws Exception {

        String sql="SELECT SUM(END) - SUM(START) AS TOTAL FROM TRIPDETAIL WHERE DATE BETWEEN '"+startDate+"' AND '"+curdate+"' AND driverNic='"+nic+"'";
        BigDecimal totalKM = (BigDecimal) session.createNativeQuery(sql).getSingleResult();

        return totalKM;

  }
}
