package org.springframework.samples.petclinic;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.dao.Dao;
import org.springframework.samples.petclinic.dao.OwnerD;

public class DAOApplication {
	
	private static Dao<OwnerD> jpaUserDao;

    // standard constructors
    
    public static void main(String[] args) {
        //OwnerD owner1 = getOwner(1);
        //System.out.println(owner1);
        //updateUser(owner1, new String[]{"Jake"});
        //saveUser(new OwnerD("Monica"));
        //deleteUser(getOwner(2));
        getAllUsers().forEach(owner -> System.out.println(owner.getFirstName()));
    }
    
//    public static OwnerD getOwner(long id) {
//        Optional<OwnerD> owner = jpaUserDao.get(id);
//        
//        return owner.orElseGet(
//          () -> new OwnerD("non-existing owner"));
//    }
    
    public static List<OwnerD> getAllUsers() {
        return jpaUserDao.getAll();
    }
    
    public static void updateUser(OwnerD owner, String[] params) {
        jpaUserDao.update(owner, params);
    }
    
    public static void saveUser(OwnerD owner) {
        jpaUserDao.save(owner);
    }
    
    public static void deleteUser(OwnerD owner) {
        jpaUserDao.delete(owner);
    }
}
