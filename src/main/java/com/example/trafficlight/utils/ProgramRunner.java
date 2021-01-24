package com.example.trafficlight.utils;

import com.example.trafficlight.enums.Road;
import com.example.trafficlight.enums.TransportName;
import com.example.trafficlight.model.Transport;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Component
public class ProgramRunner {
    private static final Logger LOGGER = Logger.getLogger(ProgramRunner.class.getName());
    private static final int TIMEOUT_GENERATOR = 4000;
    private static final int TIMEOUT_SLEEP = 2000;

    private static AtomicLong count = new AtomicLong(100);
    private static final RoadListener ROAD_LISTENER = new RoadListener();
    private static Road road = Road.ROAD_A;

    public ProgramRunner() {
        new Thread(() -> {
            try {
                generateTransport();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(new RunLightRoad(ROAD_LISTENER)).start();
    }

    private static void generateTransport() throws InterruptedException {
        Random random = new Random();
        while (true) {

            int time = random.nextInt(TIMEOUT_GENERATOR + 1) + 1000;

            Transport transport = new Transport.Builder()
                    .idTransport(count.getAndIncrement())
                    .nameTransport(time % 2 == 0 ? TransportName.BUS : TransportName.CAR)
                    .road(time % 2 == 0 ? Road.ROAD_A : Road.ROAD_B)
                    .timeCrossRoad(time)
                    .build();

            if (Road.ROAD_A == transport.getRoad()) {
                ROAD_LISTENER.addTransportToRoadA(transport);
            } else {
                ROAD_LISTENER.addTransportToRoadB(transport);
            }
            Thread.sleep(TIMEOUT_SLEEP);
        }
    }
}
