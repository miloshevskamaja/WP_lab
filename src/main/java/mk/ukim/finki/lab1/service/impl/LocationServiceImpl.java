package mk.ukim.finki.lab1.service.impl;

import mk.ukim.finki.lab1.model.Location;
import mk.ukim.finki.lab1.repository.impl.InMemoryLocationRepository;
import mk.ukim.finki.lab1.repository.jpa.LocationRepository;
import mk.ukim.finki.lab1.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(Long id) {
        return this.locationRepository.findById(id);
    }

}
