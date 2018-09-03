/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.tpmgt.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import lk.ijse.tpmgt.business.custom.ExpenceTypeBO;
import lk.ijse.tpmgt.dao.DAOFactory;
import lk.ijse.tpmgt.dao.custom.ExpenceTypeDAO;
import lk.ijse.tpmgt.db.HibernateUtil;
import lk.ijse.tpmgt.dto.ExpenceTypeDTO;
import lk.ijse.tpmgt.entity.Driver;
import lk.ijse.tpmgt.entity.ExpenceType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class ExpenceTypeBOImpl implements ExpenceTypeBO {
    private ExpenceTypeDAO expenceTypeDAO;
    private SessionFactory sessionFactory;

    public ExpenceTypeBOImpl() {
        this.expenceTypeDAO = (ExpenceTypeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DaoTypes.EXPENCETYPE);
        sessionFactory = HibernateUtil.getSessionFactory();
    }


    @Override
    public boolean saveExpenceType(ExpenceTypeDTO expenceType) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            expenceTypeDAO.setSession(session);
            session.beginTransaction();
            ExpenceType expenceType1 = new ExpenceType(expenceType.getExId(), expenceType.getType());
            expenceTypeDAO.save(expenceType1);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException exp){
            return false;
        }

        // return expenceTypeDAO.save(new ExpenceType(expenceType.getExId(),expenceType.getType()));
    }

//    @Override
//    public boolean updateExpenceType(ExpenceTypeDTO expenceType) throws Exception {
//        return expenceTypeDAO.update(new ExpenceType(expenceType.getExId(), expenceType.getType()));
//    }
//
//    @Override
//    public boolean deleteExpenceType(String id) throws Exception {
//        return expenceTypeDAO.delete(id);
//    }
//
    @Override
    public ExpenceTypeDTO findByID(String id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            expenceTypeDAO.setSession(session);
            session.beginTransaction();
            ExpenceType expenceType = expenceTypeDAO.find(id);
            ExpenceTypeDTO expenceTypeDTO = new ExpenceTypeDTO(expenceType.getExId(),expenceType.getType());
            session.getTransaction().commit();
            return expenceTypeDTO;
        }catch (HibernateException exp){
            return null;
        }
//        ExpenceType expenceType= expenceTypeDAO.find(id);
//        ExpenceTypeDTO expenceTypeDTO=new ExpenceTypeDTO(expenceType.getExId(), expenceType.getType());
//        return expenceTypeDTO;
    }
//
//    @Override
//    public ArrayList<ExpenceTypeDTO> getAllExpenceType() throws Exception {
//        ArrayList<ExpenceType> allExpenceTypes=expenceTypeDAO.getAll();
//        ArrayList<ExpenceTypeDTO> dtos=new ArrayList<>();
//        for (ExpenceType expenceType : allExpenceTypes) {
//            ExpenceTypeDTO expenceTypeDTO = new ExpenceTypeDTO(expenceType.getExId(), expenceType.getType());
//            dtos.add(expenceTypeDTO);
//        }
//        return dtos;
//    }
//
    @Override
    public ArrayList<ExpenceTypeDTO> getExpenceTypeID() throws Exception {

        try (Session session = sessionFactory.openSession()) {

            expenceTypeDAO.setSession(session);

            session.beginTransaction();

            List<ExpenceType> allTypeIDS = expenceTypeDAO.getAll();

            ArrayList<ExpenceTypeDTO> ids = new ArrayList<>();

            for(ExpenceType expenceType : allTypeIDS){

                        String exID = expenceType.getExId();
                         ExpenceTypeDTO expenceTypeDTO= new ExpenceTypeDTO(exID);
                         ids.add(expenceTypeDTO);
            }

            session.getTransaction().commit();
            return ids;

        }catch (HibernateException exp){
            return null;
        }

//         ArrayList<ExpenceType> allTypeIDS = expenceTypeDAO.getAll();
//          ArrayList<ExpenceTypeDTO> ids = new ArrayList<>();
//           for (ExpenceType expenceType : allTypeIDS) {
//             String exID = expenceType.getExId();
////            ExpenceTypeDTO expenceTypeDTO= new ExpenceTypeDTO(exID);
////            ids.add(expenceTypeDTO);
////        }
////           return ids;
    }

}
