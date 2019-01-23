package com.application;


import com.clusterapi.ClusteringService;
import com.hazelcast.internal.cluster.ClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ClusterController {

    private final ClusteringService clusterService;

    @Autowired
    public ClusterController(ClusteringService clusterService) {
        this.clusterService = clusterService;
    }

    @GetMapping("/is-leader")
    public boolean isLeader(){
        return clusterService.isLeader();
    }

    @GetMapping("/{key}")
    public Optional<String> get(@PathVariable("key") String key){
        return clusterService.getMap("map").get(key);
    }

    @PutMapping("/{key}/{value}")
    public void put(@PathVariable("key") String key,@PathVariable("value") String value){
        clusterService.getMap("map").put(key,value);
    }


    @DeleteMapping("/{key}")
    public void delete(@PathVariable("key") String key){
        clusterService.getMap("map").remove(key);
    }
}
