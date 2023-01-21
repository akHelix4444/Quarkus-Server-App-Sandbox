package com.helix.explore.quarkus.rest;

import com.helix.explore.quarkus.dto.rest.FruitDto;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Slf4j
@Path("/fruits")
public class FruitRestApi {

    private final Set<FruitDto> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public FruitRestApi() {
        log.info("FruitRestApi constructor setup.");

        for (int i = 1; i <= 10; i++) {
            var fruit = new FruitDto();
            fruit.setName("Fruit " + i);
            fruit.setCount(i);
            fruit.setDescription("Description " + i);

            fruits.add(fruit);
        }
    }

    @GET
    public Response list() {
        var response = Response.ok()
                .entity(fruits)
                .build();

        log.info("Received {} fruits.", fruits.size());
        return response;
    }

}
