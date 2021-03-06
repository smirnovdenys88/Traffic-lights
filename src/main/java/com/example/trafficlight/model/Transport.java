package com.example.trafficlight.model;

import com.example.trafficlight.enums.Road;
import com.example.trafficlight.enums.Status;
import com.example.trafficlight.enums.TransportName;
import com.example.trafficlight.utils.Channel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transport")
public class Transport implements Comparable<Transport>, Channel {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TransportName name;
    private Road road;
    private Status status;
    private int timeCrossRoad;

    public Transport() {
        //For serialization
    }

    @Override
    public int compareTo(Transport o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public void runTransportCrossStreet() {
        try {
            Thread.sleep(timeCrossRoad);
            this.status = Status.PASS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static class Builder {
        private Long id;
        private TransportName name;
        private Road road;
        private Status status = Status.DO_NOT_PASS;
        private int timeCrossRoad;

        public Builder() {
        }

        public Builder(TransportName name, Road road, int timeCrossRoad) {
            this.name = name;
            this.road = road;
            this.timeCrossRoad = timeCrossRoad;
        }

        public Builder idTransport(Long id) {
            this.id = id;
            return this;
        }

        public Builder nameTransport(TransportName name) {
            this.name = name;
            return this;
        }

        public Builder road(Road road) {
            this.road = road;
            return this;
        }

        public Builder timeCrossRoad(int timeCrossRoad) {
            this.timeCrossRoad = timeCrossRoad;
            return this;
        }

        public Transport build() {
            return new Transport(this);
        }
    }

    private Transport(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.road = builder.road;
        this.status = builder.status;
        this.timeCrossRoad = builder.timeCrossRoad;
    }

    public Long getId() {
        return id;
    }

    public TransportName getName() {
        return name;
    }

    public Road getRoad() {
        return road;
    }

    public Status getStatus() {
        return status;
    }

    public int getTimeCrossRoad() {
        return timeCrossRoad;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", road=" + road +
                ", status=" + status +
                ", timeCrossRoad=" + timeCrossRoad +
                '}';
    }
}
