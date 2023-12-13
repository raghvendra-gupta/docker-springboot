package com.example.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.SearchItem;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestDocker {

    private DockerClientConfig initDockerConfig() {
        return DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://localhost:2375")
                .withDockerTlsVerify(false)
                .build();
    }

    public DockerClient initDockerClient() {
        DockerClientConfig dockerClientConfig = initDockerConfig();
        ApacheDockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(dockerClientConfig.getDockerHost())
                .sslConfig(dockerClientConfig.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
        return DockerClientImpl.getInstance(dockerClientConfig, dockerHttpClient);
    }

    @Test
    public void getContainers() {
        DockerClient client = initDockerClient();
        List<Container> exec = client.listContainersCmd()
                .withShowSize(true)
                .withShowAll(true)
                .exec();

        System.out.println(exec);

    }

    @Test
    public void buildImage() {
        DockerClient client = initDockerClient();
        String imageId = "";
        Set<String> tags = new HashSet<>();
        tags.add("opus-backend:v1");
        try {
            imageId = client.buildImageCmd()
                    .withTags(tags)
                    .withDockerfile(new File("C:\\Users\\raghv\\Documents\\Opus\\opus-backend\\Dockerfile"))
                    .withPull(true)
                    .withNoCache(true)
                    .exec(new BuildImageResultCallback())
                    .awaitImageId();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(imageId);

    }

    @Test
    public void getImageId() {
        DockerClient client = initDockerClient();
        List<SearchItem> searchItems = client.searchImagesCmd("opus")
                .exec();

        for (SearchItem item: searchItems){
//            System.out.println(item.ge);
//            System.out.println(item.hashCode());
            System.out.println(item.getRawValues().toString());
        }
//        String description = searchItems.getFirst().getDescription();
//        System.out.println(description);
    }

    @Test
    public void createContainer() {
        DockerClient client = initDockerClient();
//        client.createContainerCmd("opus-backend")
//                .withImage();
    }

}
