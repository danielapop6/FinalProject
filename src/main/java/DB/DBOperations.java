package DB;

import java.util.List;

import Entities.Teacher;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.apache.log4j.Logger;

public class DBOperations {
    private final static Logger logger = Logger.getLogger(DBOperations.class);

    public static SessionFactory getSessionFactory() {

        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");

        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

        return configObj.buildSessionFactory(serviceRegistryObj);
    }

    public static Integer createRecord(Teacher teacher) {
        Session sessionObj = getSessionFactory().openSession();

        Transaction transObj = sessionObj.beginTransaction();
        sessionObj.save(teacher);

        transObj.commit();

        sessionObj.close();
        logger.info("Successfully Created " + teacher.toString());
        return teacher.getId();
    }

    @SuppressWarnings("unchecked")
    public static List getRecords() {
        Session sessionObj = getSessionFactory().openSession();
        List teachersList = sessionObj.createQuery("FROM Teacher").list();

        sessionObj.close();
        logger.info("Teacher Records Available In Database Are?= " + teachersList.size());
        return teachersList;
    }

    public static void updateRecord(Teacher teacher) {
        Session sessionObj = getSessionFactory().openSession();

        Transaction transObj = sessionObj.beginTransaction();
        Teacher teacher1 = (Teacher) sessionObj.load(Teacher.class, teacher.getId());
        teacher1.setName(teacher.getName());
        teacher1.setContact(teacher.getContact());

        transObj.commit();

        sessionObj.close();
        logger.info("Teacher Record Is Successfully Updated!= " + teacher.toString());
    }

    public static void deleteRecord(Integer id) {
        Session sessionObj = getSessionFactory().openSession();

        Transaction transObj = sessionObj.beginTransaction();
        Teacher teacher = findRecordById(id);
        sessionObj.delete(teacher);

        transObj.commit();

        sessionObj.close();
        logger.info("Successfully Record Is Successfully Deleted!=  " + teacher.toString());

    }

    public static Teacher findRecordById(Integer id) {
        Session sessionObj = getSessionFactory().openSession();
        Teacher teacher = (Teacher) sessionObj.load(Teacher.class, id);

        sessionObj.close();
        return teacher;
    }

    public static void deleteAllRecords() {
        Session sessionObj = getSessionFactory().openSession();

        Transaction transObj = sessionObj.beginTransaction();
        Query queryObj = sessionObj.createQuery("DELETE FROM Teacher");
        queryObj.executeUpdate();

        transObj.commit();

        sessionObj.close();
        logger.info("Successfully Deleted All Records From The Database Table!");
    }
}
