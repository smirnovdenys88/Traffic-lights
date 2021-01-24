package com.example.trafficlight.utils;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Constants {
    public static final int TIMEOUT_SLEEP_TRAFFIC_LIGHT = 20;
    public static final int TIMEOUT_GENERATOR = 4000;
    public static final int TIMEOUT_SLEEP = 1000;
}
