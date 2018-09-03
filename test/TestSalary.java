import lk.ijse.tpmgt.dao.custom.impl.QueryDAOImpl;
import lk.ijse.tpmgt.db.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.math.BigDecimal;

public class TestSalary {


    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        QueryDAOImpl qq=new QueryDAOImpl();
        qq.setSession(session);
        try {
            BigDecimal totalDistanceForDriver = qq.getTotalDistanceForDriver("2018-02-02", "2018-03-22", "923423303V");
            System.out.println(totalDistanceForDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
    }

}
