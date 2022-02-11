package org.springframework.samples.petclinic.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OwnerDao implements Dao<OwnerD>{

private List<OwnerD> users = new ArrayList<>();
    
//    public OwnerDao() {
//        users.add(new OwnerD("John"));
//        users.add(new OwnerD("Susan"));
//    }
    
    @Override
    public Optional<OwnerD> get(long id) {
        return Optional.ofNullable(users.get((int) id));
    }
    
    @Override
    public List<OwnerD> getAll() {
        return users;
    }
    
    @Override
    public void save(OwnerD user) {
        users.add(user);
    }
    
    @Override
    public void update(OwnerD user, String[] params) {
        user.setFirstName(Objects.requireNonNull(
          params[0], "Name cannot be null"));
        
        users.add(user);
    }
    
    @Override
    public void delete(OwnerD user) {
        users.remove(user);
    }

}
