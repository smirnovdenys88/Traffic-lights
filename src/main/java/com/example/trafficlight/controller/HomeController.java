package com.example.trafficlight.controller;

import com.example.trafficlight.model.Transport;
import com.example.trafficlight.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private TransportService transportService;

    @GetMapping("/statistics")
    @ResponseBody
    public String getStatics() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table border='2px'>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td>Id</td>");
        stringBuilder.append("<td>TransportName</td>");
        stringBuilder.append("<td>Road</td>");
        stringBuilder.append("<td>Status</td>");
        stringBuilder.append("<td>TimeCrossRoad (ms)</td>");
        stringBuilder.append("</tr>");

        for (Transport transport: transportService.getList()){
            stringBuilder.append("<tr>");
                stringBuilder.append("<td>" + transport.getId() + "</td>");
                stringBuilder.append("<td>" + transport.getName() + "</td>");
                stringBuilder.append("<td>" + transport.getRoad() + "</td>");
                stringBuilder.append("<td>" + transport.getStatus() + "</td>");
                stringBuilder.append("<td>" + transport.getTimeCrossRoad() + "</td>");
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");

        return stringBuilder.toString();
    }
}
