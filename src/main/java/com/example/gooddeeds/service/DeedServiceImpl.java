package com.example.gooddeeds.service;

import com.example.gooddeeds.exceptions.InvalidFieldException;
import com.example.gooddeeds.model.ApplicationUser;
import com.example.gooddeeds.model.Deed;
import com.example.gooddeeds.repository.DeedRepository;
import com.example.gooddeeds.utils.DeedValidator;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
        List<Deed> orderedDeeds = deedRepository.findAllByOrderByDateAsc();

        return new ArrayList<>(currentDateFilter(orderedDeeds).subList(0, 3));
    }

    @Override
    public List<Deed> getAllUpcomingDeeds() throws ParseException {
        List<Deed> orderedDeeds = deedRepository.findAllByOrderByDateAsc();

        return currentDateFilter(orderedDeeds);
    }

    @Override
    public Deed createDeed(Deed deed, ApplicationUser applicationUser) {
        deed.setApplicationUser(applicationUser);
        try {
            DeedValidator.validateDeed(deed);
        } catch (InvalidFieldException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        return deedRepository.save(deed);
    }


    @Override
    public void deleteDeed(int id, ApplicationUser applicationUser) {

        Optional<Deed> deed = deedRepository.findById(id);
        if (deed.isPresent()) {
            if(applicationUser.getId() != deed.get().getApplicationUser().getId()){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ID_NOT_FOUND);
        }
        deedRepository.deleteById(id);
    }

    @Override
    public Deed editDeed(int id, Deed newDeed, ApplicationUser applicationUser) {

        Optional<Deed> deedToUpdate = deedRepository.findById(id);
        if (deedToUpdate.isPresent()) {
            if(applicationUser.getId() != deedToUpdate.get().getApplicationUser().getId()){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
            try {
                DeedValidator.validateDeed(newDeed);
                Deed newDeedWithId = new Deed.Builder(id)
                        .title(newDeed.getTitle())
                        .city(newDeed.getCity())
                        .date(newDeed.getDate())
                        .contactPerson(newDeed.getContactPerson())
                        .phoneNumber(newDeed.getPhoneNumber())
                        .email(newDeed.getEmail())
                        .organization(newDeed.getOrganization())
                        .maxPeople(newDeed.getMaxPeople())
                        .currentPeople(newDeed.getCurrentPeople())
                        .description(newDeed.getDescription())
                        .tags(newDeed.getTags())
                        .build();
                newDeedWithId.setApplicationUser(applicationUser);
                return deedRepository.save(newDeedWithId);
            } catch (InvalidFieldException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ID_NOT_FOUND);
        }
    }

    private List<Deed> currentDateFilter(List<Deed> listToFilter) throws ParseException {

        Iterator<Deed> deedIterator = listToFilter.iterator();

        Date currentDate = yesterday();

        while (deedIterator.hasNext()) {
            Deed currentDeed = deedIterator.next();
            Date deedDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentDeed.getDate());
            if (deedDate.compareTo(currentDate) < 0)
                deedIterator.remove();
        }

        return listToFilter;
    }

    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
