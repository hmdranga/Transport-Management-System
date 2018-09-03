/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.tpmgt.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.handler.HandlerException;
import lk.ijse.tpmgt.business.custom.VehicleBO;
import lk.ijse.tpmgt.dao.DAOFactory;
import lk.ijse.tpmgt.dao.custom.VehicleDAO;
import lk.ijse.tpmgt.db.HibernateUtil;
import lk.ijse.tpmgt.dto.VehicleDTO;
import lk.ijse.tpmgt.entity.Vehicle;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class VehicleBOImpl implements VehicleBO {
    private VehicleDAO vehicleDAO;
    private SessionFactory sessionFactory;

    public VehicleBOImpl() {
        this.vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DaoTypes.VEHICLE);
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    @Override
    public boolean saveVehicle(VehicleDTO vehicle) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            vehicleDAO.setSession(session);
            session.beginTransaction();
            Vehicle vehicle1 = new Vehicle(vehicle.getRegNo(), vehicle.getBrand(), vehicle.getColour(), vehicle.getBoughtDate());
            vehicleDAO.save(vehicle1);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException exp){
            return false;
        }

       // return vehicleDAO.save(new Vehicle(vehicle.getRegNo(), vehicle.getBrand(), vehicle.getColour(), vehicle.getBoughtDate()));
    }

    @Override
    public boolean updateVehicle(VehicleDTO vehicle) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            vehicleDAO.setSession(session);
            session.beginTransaction();
            Vehicle vehicle1 = vehicleDAO.find(vehicle.getRegNo());
            vehicle1.setBrand(vehicle.getBrand());
            vehicle1.setColour(vehicle.getColour());
            vehicle1.setBoughtDate(vehicle.getBoughtDate());
            vehicleDAO.update(vehicle1);
            //Vehicle vehicle1 = new Vehicle(vehicle.getRegNo(), vehicle.getBrand(), vehicle.getColour(), vehicle.getBoughtDate());
            session.getTransaction().commit();
            return  true;

        }catch (HibernateException exp){
            return false;
        }
       // return vehicleDAO.update(new Vehicle(vehicle.getRegNo(), vehicle.getBrand(), vehicle.getColour(), vehicle.getBoughtDate()));

    }

    @Override
    public boolean deleteVehicle(String id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            vehicleDAO.setSession(session);
            session.beginTransaction();
            vehicleDAO.delete(id);
            session.getTransaction().commit();;
            return true;
        }catch (HibernateException exp){
            return false;
        }
        //return vehicleDAO.delete(id);
    }

//    @Override
//    public VehicleDTO findByID(String id) throws Exception {
//
//        Vehicle vehicle=vehicleDAO.find(id);
//        VehicleDTO vehicleDTO= new VehicleDTO(vehicle.getRegNo(),
//                vehicle.getBrand(),
//                vehicle.getColour(),
//                vehicle.getBoughtDate());
//       return vehicleDTO;
//    }

    @Override
    public ArrayList<VehicleDTO> getAllVehicle() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            vehicleDAO.setSession(session);
            session.beginTransaction();

            List<Vehicle> allVehicles = vehicleDAO.getAll();

            ArrayList<VehicleDTO> dtos = new ArrayList<>();

            for(Vehicle vehicle : allVehicles){
                VehicleDTO vehicleDTO= new VehicleDTO(vehicle.getRegNo(), vehicle.getBrand(), vehicle.getColour(), vehicle.getBoughtDate());
                dtos.add(vehicleDTO);
            }

            session.getTransaction().commit();

            return dtos;

        }catch (HibernateException exp){
            return null;
        }
//        ArrayList<Vehicle> allVehicles = vehicleDAO.getAll();
//        ArrayList<VehicleDTO> dtos = new ArrayList<>();
//
//        for (Vehicle vehicle : allVehicles) {
//            VehicleDTO vehicleDTO= new VehicleDTO(vehicle.getRegNo(),
//                    vehicle.getBrand(),
//                    vehicle.getColour(),
//                    vehicle.getBoughtDate());
//            dtos.add(vehicleDTO);
//        }
//        return dtos;
        
    }

    @Override
    public ArrayList<VehicleDTO> getVehicleID() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            vehicleDAO.setSession(session);
            session.beginTransaction();

            List<Vehicle> allVehicles = vehicleDAO.getAll();
            ArrayList<VehicleDTO> ids = new ArrayList<>();
            for (Vehicle vehicle : allVehicles) {
                String regNo = vehicle.getRegNo();
                VehicleDTO vehicleDTO= new VehicleDTO(regNo);
                ids.add(vehicleDTO);
            }

            session.getTransaction().commit();
            return ids;

        }catch (HandlerException exp){
            return null;
        }
//         ArrayList<Vehicle> allVehicles = vehicleDAO.getAll();
//          ArrayList<VehicleDTO> ids = new ArrayList<>();
//           for (Vehicle vehicle : allVehicles) {
//             String regNo = vehicle.getRegNo();
//            VehicleDTO vehicleDTO= new VehicleDTO(regNo);
//            ids.add(vehicleDTO);
//        }
//           return ids;
    }

    
    
}
