package mk.ukim.finki.lab1.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.lab1.model.SavedBooking;
import mk.ukim.finki.lab1.model.User;
import mk.ukim.finki.lab1.repository.jpa.SavedBookingRepository;
import mk.ukim.finki.lab1.repository.jpa.UserRepository;
import mk.ukim.finki.lab1.service.SavedBookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SavedBookingServiceImpl implements SavedBookingService {
    private final SavedBookingRepository savedBookingRepository;
    private final UserRepository userRepository;

    public SavedBookingServiceImpl(SavedBookingRepository savedBookingRepository, UserRepository userRepository) {
        this.savedBookingRepository = savedBookingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<SavedBooking> getSavedBookings(){
        return savedBookingRepository.findAll();
    }
    @Override
    public Optional<SavedBooking> save(String name, int number ,String user){
        User userNov = this.userRepository.findByUsername(user);
        if(userNov!=null){
            SavedBooking booking=new SavedBooking(name,number,userNov);
            return Optional.of(this.savedBookingRepository.save(booking));
        }
    return Optional.empty();
    }

}
