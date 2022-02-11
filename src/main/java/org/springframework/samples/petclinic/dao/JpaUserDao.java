package org.springframework.samples.petclinic.dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class JpaUserDao implements Dao<OwnerD> {
    
    private EntityManager entityManager;
    
    // standard constructors
    
    @Override
    public Optional<OwnerD> get(long id) {
        return Optional.ofNullable(entityManager.find(OwnerD.class, id));
    }
    
    @Override
    public List<OwnerD> getAll() {
        Query query = entityManager.createQuery("SELECT * FROM owners");
        return query.getResultList();
    }
    
    @Override
    public void save(OwnerD owner) {
        //executeInsideTransaction(entityManager -> entityManager.persist(owner));
    }
    
    @Override
    public void update(OwnerD owner, String[] params) {
        owner.setFirstName(Objects.requireNonNull(params[0], "Name cannot be null"));
       // executeInsideTransaction(entityManager -> entityManager.merge(owner));
    }
    
    @Override 
    public void delete(OwnerD owner) {
        //executeInsideTransaction(entityManager -> entityManager.remove(owner));
    }
    
    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit(); 
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
