package com.example.friv_projekat.service;

import com.example.friv_projekat.repository.IgraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IgraService {
    private final IgraRepository igraRepository;

    @Autowired
    public IgraService(IgraRepository igraRepository) {
        this.igraRepository = igraRepository;
    }


}
