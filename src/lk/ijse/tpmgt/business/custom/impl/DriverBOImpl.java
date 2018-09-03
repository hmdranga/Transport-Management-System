/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.tpmgt.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.handler.HandlerException;
import lk.ijse.tpmgt.business.custom.DriverBO;
import lk.ijse.tpmgt.dao.DAOFactory;
import lk.ijse.tpmgt.dao.custom.DriverDAO;
import lk.ijse.tpmgt.db.HibernateUtil;
import lk.ijse.tpmgt.dto.DriverDTO;
import lk.ijse.tpmgt.entity.Driver;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class DriverBOImpl implements DriverBO {
    private DriverDAO driverDAO;
    private SessionFactory sessionFactory;

    public DriverBOImpl() {

        this.driverDAO = (DriverDAO) DAOFactory.getInstance().getDAO(DAOFactory.DaoTypes.DRIVER);
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    

    @Override
    public boolean saveDriver(DriverDTO driver) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            driverDAO.setSession(session);
            session.beginTransaction();
            Driver driver1 = new Driver(driver.getNic(),driver.getName(),driver.getAddress(),driver.getContactNo(),driver.getDrivingLicenceNo());
            driverDAO.save(driver1);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException exp){
            return false;
        }

//        return driverDAO.save(new Driver(driver.getNic(),
//                 driver.getName(),
//                 driver.getAddress(),
//                 driver.getContactNo(),
//                 driver.getDrivingLicenceNo()));
    }


    @Override
    public boolean updateDriver(DriverDTO driver) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            driverDAO.setSession(session);
            session.beginTransaction();
            Driver driver1= driverDAO.find(driver.getNic());
            driver1.setAddress(driver.getAddress());
            driver1.setName(driver.getName());
            driver1.setContactNo(driver.getContactNo());
            driver1.setDrivingLicenceNo(driver.getDrivingLicenceNo());
//            Driver driver1 = new Driver(driver.getNic(),driver.getName(),driver.getAddress(),driver.getContactNo(),driver.getDrivingLicenceNo());
            driverDAO.update(driver1);
            session.getTransaction().commit();
            return  true;

        }catch (HibernateException exp){
            return false;
        }
//        return driverDAO.update(new  Driver(driver.getNic(),
//                driver.getName(),
//                driver.getAddress(),
//                driver.getContactNo(),
//                driver.getDrivingLicenceNo()));
    }
//
    @Override
    public boolean deleteDriver(String id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            driverDAO.setSession(session);
            session.beginTransaction();
            driverDAO.delete(id);
            session.getTransaction().commit();;
            return true;
        }catch (HibernateException exp){
            return false;
        }
        //return driverDAO.delete(id);
    }
//
//    @Override
//    public DriverDTO findByID(String id) throws Exception {
//        Driver driver= driverDAO.find(id);
//        DriverDTO driverDTO= new DriverDTO(driver.getNic(),
//                driver.getName(),
//                driver.getAddress(),
//                driver.getContactNo(),
//                driver.getDrivingLicenceNo());
//
//        return driverDTO;
//    }
//
    @Override
    public ArrayList<DriverDTO> getAllDriver() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            driverDAO.setSession(session);
            session.beginTransaction();

            List<Driver> allDrivers = driverDAO.getAll();

            session.getTransaction().commit();

            ArrayList<DriverDTO> dtos = new ArrayList<>();

            for(Driver driver : allDrivers){
                DriverDTO driverDTO = new DriverDTO(driver.getNic(),driver.getName(),driver.getAddress(),driver.getContactNo(),driver.getDrivingLicenceNo());
                dtos.add(driverDTO);
            }
            return dtos;

        }catch (HibernateException exp){
            return null;
        }
//        ArrayList<Driver> allDrivers= driverDAO.getAll();
//            ArrayList<DriverDTO> dtos= new ArrayList<>();
//
//            for(Driver driver : allDrivers){
//                DriverDTO driverDTO= new DriverDTO(driver.getNic(), driver.getName(), driver.getAddress(), driver.getContactNo(), driver.getDrivingLicenceNo());
//                dtos.add(driverDTO);
//            }
//            return dtos;
    }
    @Override
    public ArrayList<DriverDTO> getDriverID() throws Exception {

        try (Session session = sessionFactory.openSession()) {
            driverDAO.setSession(session);
            session.beginTransaction();

            List<Driver> allDrivers = driverDAO.getAll();
            ArrayList<DriverDTO> ids = new ArrayList<>();
            for (Driver driver : allDrivers) {
             String nic = driver.getNic();
            DriverDTO driverDTO= new DriverDTO(nic);
            ids.add(driverDTO);
        }

            session.getTransaction().commit();
            return ids;

        }catch (HandlerException exp){
            return null;
        }
//        ArrayList<Driver> allDrivers = driverDAO.getAll();
//          ArrayList<DriverDTO> ids = new ArrayList<>();
//           for (Driver driver : allDrivers) {
//             String nic = driver.getNic();
//            DriverDTO driverDTO= new DriverDTO(nic);
//            ids.add(driverDTO);
//        }
//           return ids;
    }

}
