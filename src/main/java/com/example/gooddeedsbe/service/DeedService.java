package com.example.gooddeedsbe.service;

import com.example.gooddeedsbe.exceptions.InvalidFieldException;
import com.example.gooddeedsbe.model.Deed;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeedService {
    List<Deed> getAllDeeds();

    Deed getDeedById(int id) throws InvalidFieldException;

    Deed editDeed(Deed deed) throws InvalidFieldException;

    void deleteDeed(int id);

    Deed editDeed(int id, Deed deed) throws InvalidFieldException;
}
