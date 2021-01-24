package com.example.trafficlight.service;

import com.example.trafficlight.model.Transport;
import com.example.trafficlight.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    private TransportRepository transportRepository;

    public TransportServiceImpl(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @Override
    public void add(Transport transport) {
        transportRepository.save(transport);
    }

    @Override
    public void update(Transport transport) {
        transportRepository.save(transport);
    }
}
