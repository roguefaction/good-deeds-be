package com.example.gooddeeds.service;

import com.example.gooddeeds.exceptions.IdNotFoundException;
import com.example.gooddeeds.exceptions.InvalidFieldException;
import com.example.gooddeeds.model.Deed;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeedService {
    List<Deed> getAllDeeds();

    Deed getDeedById(int id) throws IdNotFoundException;

    Deed createDeed(Deed deed) throws InvalidFieldException;

    void deleteDeed(int id);

    Deed editDeed(int id, Deed deed) throws IdNotFoundException, InvalidFieldException;
}
