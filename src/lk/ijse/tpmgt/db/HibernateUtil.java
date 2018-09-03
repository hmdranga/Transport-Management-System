/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.tpmgt.db;

import lk.ijse.tpmgt.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;

/**
 *
 * @author A C E R
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        File hibernateProp = new File("resource/application.properties");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().loadProperties(hibernateProp)
                .build();

        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Driver.class)
                .addAnnotatedClass(Salary.class)
                .addAnnotatedClass(TripDetail.class)
                .addAnnotatedClass(Vehicle.class)
                .addAnnotatedClass(ExpenceDetail.class)
                .addAnnotatedClass(ExpenceType.class)
                .buildMetadata();

        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


//
//    private HibernateUtil() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//
//        File dbPropertiesFile = new File("resource/application.properties");
//        FileReader fileReader = new FileReader(dbPropertiesFile);
//
//        Properties dbProperties = new Properties();
//        dbProperties.load(fileReader);
//
//        String ip = dbProperties.getProperty("ip");
//        String port = dbProperties.getProperty("port");
//        String database = dbProperties.getProperty("database");
//        String username = dbProperties.getProperty("username");
//        String password = dbProperties.getProperty("password");
//
//        String url ="jdbc:mysql://"+ ip +":"+ port +"/"+ database;
//        connection = DriverManager.getConnection(url, username, password);
//    }
    
    
//    public static HibernateUtil getInstance()throws Exception{
//        if(dbConnection==null){
//        dbConnection=new HibernateUtil();
//        }
//            return dbConnection;
//    }
//
//    public Connection getConnection(){
//        return connection;
//    }
        
}
