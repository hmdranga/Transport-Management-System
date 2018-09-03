/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.tpmgt.business.custom.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ijse.tpmgt.business.BOFactory;
import lk.ijse.tpmgt.business.custom.DriverBO;
import lk.ijse.tpmgt.business.custom.SalaryBO;
import lk.ijse.tpmgt.dao.DAOFactory;
import lk.ijse.tpmgt.dao.custom.DriverDAO;
import lk.ijse.tpmgt.dao.custom.QueryDAO;
import lk.ijse.tpmgt.dao.custom.SalaryDAO;
import lk.ijse.tpmgt.db.HibernateUtil;
import lk.ijse.tpmgt.dto.DriverDTO;
import lk.ijse.tpmgt.dto.SalaryDTO;
import lk.ijse.tpmgt.entity.Driver;
import lk.ijse.tpmgt.entity.Salary;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class SalaryBOImpl implements SalaryBO {
    private DriverDAO driverDAO;
    private SalaryDAO salaryDAO;
    private QueryDAO queryDAO;
    private SessionFactory sessionFactory;

    public SalaryBOImpl() {
        this.salaryDAO = (SalaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DaoTypes.SALARY);
        this.queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DaoTypes.QUERY);
        driverDAO = (DriverDAO) DAOFactory.getInstance().getDAO(DAOFactory.DaoTypes.DRIVER);
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    

    @Override
    public boolean saveSalary(SalaryDTO salary) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            queryDAO.setSession(session);
            Date d= new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String curdate=df.format(d);
        String startDate=df.format(salary.getsDate());
        BigDecimal totalKm=queryDAO.getTotalDistanceForDriver(startDate, curdate,salary.getNic());
        BigDecimal earn = totalKm.multiply(salary.getAmountPerKm());
        BigDecimal total= earn.add(salary.getBonus());

            salaryDAO.setSession(session);
            driverDAO.setSession(session);

            Driver driver =  driverDAO.find(salary.getNic());
            Salary salary1 = new Salary(salary.getsId(), df.parse(curdate), totalKm, salary.getBonus(), salary.getAmountPerKm() , earn, total, driver);
            salaryDAO.save(salary1);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException exp){
            return false;
        }
    }

//    @Override
//    public boolean updateSalary(SalaryDTO salary) throws Exception {
//        return salaryDAO.update(new Salary(salary.getsId(),
//                salary.getsDate(),
//                salary.getTotalKm(),
//                salary.getBonus(),
//                salary.getAmountPerKm(),
//                salary.getEarn(),
//                salary.getTotal(),
//                salary.getNic()));
//    }

//    @Override
//    public boolean deleteSalary(String id) throws Exception {
//       return salaryDAO.delete(id);
//    }

//    @Override
//    public SalaryDTO findByID(String id) throws Exception {
//
////        Salary salary=salaryDAO.find(id);
////        SalaryDTO salaryDTO= new SalaryDTO(salary.getsId(),
////                salary.getsDate(),
////                salary.getTotalKm(),
////                salary.getBonus(),
////                salary.getAmountPerKm(),
////                salary.getEarn(),
////                salary.getTotal(),salary.getNic());
////        return salaryDTO;
//    }

    @Override
    public ArrayList<SalaryDTO> getAllSalary() throws Exception {
        try (Session session = sessionFactory.openSession()) {

            salaryDAO.setSession(session);

            session.beginTransaction();

            List<Salary> allSalarys = salaryDAO.getAll();

            ArrayList<SalaryDTO> salaryDTOs = new ArrayList<>();

            for(Salary salary : allSalarys){
                SalaryDTO salaryDTO = new SalaryDTO(salary.getsId(),
                        salary.getsDate(),
                        salary.getTotalKm(),
                        salary.getBonus(),
                        salary.getAmountPerKm(),
                        salary.getEarn(),
                        salary.getTotal(),salary.getDriver().getNic());
                salaryDTOs.add(salaryDTO);
            }
            session.getTransaction().commit();
            return salaryDTOs;

        }catch (HibernateException exp){
            return null;
        }


//        ArrayList<Salary> allSalarys = salaryDAO.getAll();
//        ArrayList<SalaryDTO> salaryDTOs=new ArrayList<>();
//        for (Salary salary : allSalarys) {
//            SalaryDTO salaryDTO = new SalaryDTO(salary.getsId(),
//                    salary.getsDate(),
//                    salary.getTotalKm(),
//                    salary.getBonus(),
//                    salary.getAmountPerKm(),
//                    salary.getEarn(),
//                    salary.getTotal(),salary.getDriver().getNic());
//            salaryDTOs.add(salaryDTO);
//        }
//        return salaryDTOs;
    }

    @Override
    public ArrayList<DriverDTO> getDriverID() throws Exception {

        DriverBO driverBO = (DriverBO) BOFactory.getInstance().getBO(BOFactory.BoTypes.DRIVER);
        return driverBO.getDriverID();

    }
    
}
