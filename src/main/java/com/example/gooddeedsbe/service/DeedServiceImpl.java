package com.example.gooddeedsbe.service;

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
    public Deed getDeedById(int id) throws InvalidFieldException {
        Optional<Deed> deed = deedRepository.findById(id);
        if (deed.isPresent()) {
            return deed.get();
        } else {
            throw new InvalidFieldException("Deed not found with given ID");
        }
    }

    @Override
    public Deed editDeed(Deed deed) throws InvalidFieldException {
        DeedHelper.validateDeed(deed);
        return deedRepository.save(deed);
    }

    @Override
    public void deleteDeed(int id) {
        deedRepository.deleteById(id);
    }

    @Override
    public Deed editDeed(int id, Deed newDeed) throws InvalidFieldException {
        Optional<Deed> deedToUpdate = deedRepository.findById(id);
        if (deedToUpdate.isPresent()) {
            DeedHelper.validateDeed(newDeed);
            newDeed.setId(id);
            return deedRepository.save(newDeed);
        } else {
            throw new InvalidFieldException("Deed not found with given ID");
        }

    }
}
