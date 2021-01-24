package com.example.trafficlight.component;

import com.example.trafficlight.enums.Road;

public class RoadState {
    private static volatile RoadState instance;
    private Road road = Road.ROAD_A;

    public static RoadState getInstance() {
        RoadState localInstance = instance;
        if (localInstance == null) {
            synchronized (RoadState.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RoadState();
                }
            }
        }
        return localInstance;
    }

    public void  setRoad(Road road){
        synchronized(road){
            this.road = road;
        }
    }

    public Road getRoad(){
        synchronized (road){
            return this.road;
        }
    }
}
