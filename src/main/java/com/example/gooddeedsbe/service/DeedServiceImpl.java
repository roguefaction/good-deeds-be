package com.example.gooddeedsbe.service;

import com.example.gooddeedsbe.exceptions.IdNotFoundException;
import com.example.gooddeedsbe.exceptions.InvalidFieldException;
import com.example.gooddeedsbe.model.Deed;
import com.example.gooddeedsbe.repository.DeedRepository;
import com.example.gooddeedsbe.utils.DeedHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Deed getDeedById(int id) throws IdNotFoundException {
        Optional<Deed> deed = deedRepository.findById(id);
        if (deed.isPresent()) {
            return deed.get();
        } else {
            throw new IdNotFoundException("Deed not found with given ID");
        }
    }

    @Override
    public Deed createDeed(Deed deed) throws InvalidFieldException {
        DeedHelper.validateDeed(deed);
        return deedRepository.save(deed);
    }

    @Override
    public void deleteDeed(int id) {
        deedRepository.deleteById(id);
    }

    @Override
    public Deed editDeed(int id, Deed newDeed) throws IdNotFoundException, InvalidFieldException {
        Optional<Deed> deedToUpdate = deedRepository.findById(id);
        if (deedToUpdate.isPresent()) {
            DeedHelper.validateDeed(newDeed);
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
        } else {
            throw new IdNotFoundException("Deed not found with given ID");
        }

    }
}
