package com.example.docker.controller;

import com.example.docker.service.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerController {

    @Autowired
    DockerService dockerService;


    @GetMapping("/containers")
    public ResponseEntity getContainers(){
        return new ResponseEntity(dockerService.getContainers(), HttpStatus.OK);
    }

    @GetMapping("/containers")
    public ResponseEntity buildImage(){
        return new ResponseEntity(dockerService.getContainers(), HttpStatus.OK);
    }

}
