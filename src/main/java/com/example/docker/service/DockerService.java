package com.example.docker.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.model.Container;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DockerService {


    private final DockerClient client;

    public DockerService(DockerClient client) {
        this.client = client;
    }

    public List<Container> getContainers() {
        return client.listContainersCmd()
                .withShowSize(true)
                .withShowAll(true)
                .exec();

    }
//    public void getContainers() {
//        BuildImageCmd buildImageCmd = client.buildImageCmd()
//                .withDockerfilePath("");
//
//    }


}
