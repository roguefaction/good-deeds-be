package com.example.gooddeeds.service;

import com.example.gooddeeds.exceptions.InvalidFieldException;
import com.example.gooddeeds.model.Deed;
import com.example.gooddeeds.repository.DeedRepository;
import com.example.gooddeeds.utils.DeedValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class DeedServiceImpl implements DeedService {

    private static final String ID_NOT_FOUND = "Deed not found with given ID";

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ID_NOT_FOUND);
        }
    }

    @Override
    public List<Deed> getUpcomingDeeds() throws ParseException {

        List<Deed> orderedDeeds = deedRepository.findAllByOrderByDeedDateAsc();

        Iterator<Deed> deedIterator = orderedDeeds.iterator();

        Date currentDate = new Date();

        while (deedIterator.hasNext()) {
            Deed currentDeed = deedIterator.next();
            Date deedDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentDeed.getDeedDate());
            if (deedDate.compareTo(currentDate) < 0)
                deedIterator.remove();
        }

        return new ArrayList<>(orderedDeeds.subList(0, 3));
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ID_NOT_FOUND);
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
                        .deedDate(newDeed.getDeedDate())
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ID_NOT_FOUND);
        }
    }
}
