//package mistrzowie.shop.dao;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Any;
//import javax.enterprise.inject.Disposes;
//import javax.enterprise.inject.Produces;
//import javax.persistence.EntityManager;
//import javax.persistence.Persistence;
//
///**
// *
// * @author Arek
// */
//public class EMProducer {
//
//    @Produces
//    @ApplicationScoped
//    EntityManager getSudokuDatabaseJPA() {
//        return Persistence.createEntityManagerFactory("webShopUnit")
//                .createEntityManager();
//    }
//
//    void closeEntityManager(@Disposes @Any EntityManager em) {
//        em.close();
//    }
//    
//}