package mk.ukim.finki.lab1.repository.impl;

import mk.ukim.finki.lab1.bootstrap.DataHolder;
import mk.ukim.finki.lab1.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryLocationRepository {

    public List<Location> findAll(){
        return DataHolder.locations;
    }
    public Optional<Location> findById(Long id){
        return DataHolder.locations.stream().filter(r->r.getId().equals(id)).findFirst();
    }

}
