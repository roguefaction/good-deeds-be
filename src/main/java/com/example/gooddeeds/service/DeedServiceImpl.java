package com.example.gooddeeds.service;

import com.example.gooddeeds.exceptions.IdNotFoundException;
import com.example.gooddeeds.exceptions.InvalidFieldException;
import com.example.gooddeeds.model.Deed;
import com.example.gooddeeds.repository.DeedRepository;
import com.example.gooddeeds.utils.DeedValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DeedServiceImpl implements DeedService {


    private DeedRepository deedRepository;

    @Autowired
    public DeedServiceImpl(DeedRepository deedRepository) {
        this.deedRepository = deedRepository;
    }

    @Override
    public List<Deed> getAllDeeds() {
        return deedRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Deed getDeedById(int id) {
        Optional<Deed> deed = deedRepository.findById(id);
        if (deed.isPresent()) {
            return deed.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deed not found with given ID");
        }
    }

    @Override
    public Deed createDeed(Deed deed) {
        try {
            DeedValidator.validateDeed(deed);
        } catch (InvalidFieldException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        return deedRepository.save(deed);
    }

    @Override
    public void deleteDeed(int id) {
        Optional<Deed> deed = deedRepository.findById(id);
        if (deed.isPresent()) {
            deedRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deed not found with given ID");
        }
    }

    @Override
    public Deed editDeed(int id, Deed newDeed) {
        Optional<Deed> deedToUpdate = deedRepository.findById(id);
        if (deedToUpdate.isPresent()) {
            try {
                DeedValidator.validateDeed(newDeed);
                Deed newDeedWithId = new Deed.Builder(id)
                        .title(newDeed.getTitle())
                        .city(newDeed.getCity())
                        .contactPerson(newDeed.getContactPerson())
                        .phoneNumber(newDeed.getPhoneNumber())
                        .email(newDeed.getEmail())
                        .organization(newDeed.getOrganization())
                        .maxPeople(newDeed.getMaxPeople())
                        .currentPeople(newDeed.getCurrentPeople())
                        .description(newDeed.getDescription())
                        .tags(newDeed.getTags())
                        .build();
                return deedRepository.save(newDeedWithId);
            } catch (InvalidFieldException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deed not found with given ID");
        }
    }
}
