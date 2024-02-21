package org.example;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App
{

    private static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();

        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Patients.class);
        configuration.addAnnotatedClass(Doctors.class);
        configuration.addAnnotatedClass(Departments.class);
        configuration.addAnnotatedClass(PD.class);

        ServiceRegistry serviceRegistry = new
                StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GENERATORS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void generatePatients() throws Exception {
        for (int i = 0; i < 3; i++) {
            Patients patient = new Patients(i+1, "patientFisrt"+(i+1), "patientLast"+(i+1),
                    LocalDateTime.now(), i+1);
            session.save(patient);
            session.flush();
        }
    }

    private static void generateDoctors() throws Exception {
        // makle 5ra
        // shof el hash
        for (int i = 0; i < 6; i++) {
            Doctors doctor = new Doctors("patientFisrt"+(i+1), "patientLast"+(i+1),
                    "email"+(i+1), "password"+(i+1));
            session.save(doctor);
            session.flush();
        }
    }

    private static void generateDepartments() throws Exception {
        for (int i = 0; i < 3; i++) {
            Departments dep = new Departments("Department#"+(i+1),i+1);
            session.save(dep);
            session.flush();
        }
    }


    private static void generatePDs() throws Exception {
        for (int i = 0; i < 3; i++) {
            PD pd = new PD(i+1, i+1, LocalDateTime.now(), "description #"+(i+1));
            session.save(pd);
            session.flush();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // LIST GETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static List<Patients> getAllPatients() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Patients> query = builder.createQuery(Patients.class);
        query.from(Patients.class);
        List<Patients> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<Doctors> getAllDoctors() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Doctors> query = builder.createQuery(Doctors.class);
        query.from(Doctors.class);
        List<Doctors> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<Departments> getAllDepartments() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Departments> query = builder.createQuery(Departments.class);
        query.from(Departments.class);
        List<Departments> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<PD> getAllPDs() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PD> query = builder.createQuery(PD.class);
        query.from(PD.class);
        List<PD> data = session.createQuery(query).getResultList();
        return data;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // QUERIES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static List<Object[]> queryAResults () throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Patients> patientsRoot = query.from(Patients.class);
        Join<Patients, Departments> departmentsJoin = patientsRoot.join("Departments", JoinType.LEFT);
        query.multiselect(patientsRoot, departmentsJoin.get("depName")).orderBy(builder.asc(patientsRoot.get("patientNum")));
        return session.createQuery(query).getResultList();
    }

    private static List<Object[]> queryBResults () throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Departments> departmentsRoot = query.from(Departments.class);
        Join<Doctors, Departments> doctorsJoin = departmentsRoot.join("Departments", JoinType.LEFT);
        query.multiselect(departmentsRoot, doctorsJoin.get("first"), doctorsJoin.get("last"));
        return session.createQuery(query).getResultList();
    }

    private static List<Doctors> queryCResults () throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Doctors> query = builder.createQuery(Doctors.class);
        Root<Doctors> doctorsRoot = query.from(Doctors.class);
        query.select(doctorsRoot).orderBy(builder.asc(doctorsRoot.get("first")), builder.asc(doctorsRoot.get("last")));
        return session.createQuery(query).getResultList();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PRINTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void printA () throws Exception {
        List<Object[]> list = queryAResults();
        for (Object[] item : list) {
            //print
        }
    }

    private static void printB () throws Exception {
        List<Object[]> list = queryBResults();
        for (Object[] item : list) {
            //print
        }
    }

    private static void printC () throws Exception {
        List<Doctors> list = queryCResults();
        for (Doctors doctor : list) {
            //print
        }
    }

    public static void main( String[] args ) {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();


            generatePatients();
            generateDoctors();
            generateDepartments();
            generatePDs();

            // not sure if to use
            /*List<Patients> patients = getAllPatients();
            List<Doctors> doctors = getAllDoctors();
            List<Departments> departments = getAllDepartments();
            List<PD> PDs = getAllPDs();*/

            printA();
            printB();
            printC();

            session.getTransaction().commit(); // Save everything.

        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
                    exception.printStackTrace();
        } finally {
            session.close();
        }
    }
}

    /*
    private static void printAllCars() throws Exception {
        List<Car> cars = getAllCars();
        for (Car car : cars) {
            System.out.print("Id: ");
            System.out.print(car.getId());
            System.out.print(", License plate: ");
            System.out.print(car.getLicensePlate());
            System.out.print(", Price: ");
            System.out.print(car.getPrice());
            System.out.print(", Year: ");
            System.out.print(car.getYear());
            System.out.print('\n');
        }
    }
    */
