package com.example.trafficlight.service;

import com.example.trafficlight.model.Transport;

import java.util.List;

public interface TransportService {
    void add(Transport transport);
    void update(Transport transport);

    List<Transport> getList();
}
