package com.example.gooddeeds.service;

import com.example.gooddeeds.model.Deed;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeedService {
    List<Deed> getAllDeeds();

    Deed getDeedById(int id);

    Deed createDeed(Deed deed);

    void deleteDeed(int id);

    Deed editDeed(int id, Deed deed);
}
