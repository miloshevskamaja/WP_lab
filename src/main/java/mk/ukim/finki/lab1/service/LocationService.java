package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    public List<Location> findAll();
    public Optional<Location> findById(Long id);
}
