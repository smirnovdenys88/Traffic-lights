package com.example.trafficlight.service;

import com.example.trafficlight.model.Transport;
import com.example.trafficlight.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    private TransportRepository transportRepository;

    public TransportServiceImpl(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @Override
    @Transactional
    public void add(Transport transport) {
        transportRepository.save(transport);
    }

    @Override
    @Transactional
    public void update(Transport transport) {
        transportRepository.save(transport);
    }

    @Override
    public List<Transport> getList() {
        return transportRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }
}
