package com.example.trafficlight.service;

import com.example.trafficlight.repository.TransportRepository;

public class TransportServiceImpl implements TransportService {

    private TransportRepository transportRepository;

    public TransportServiceImpl(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }
}
