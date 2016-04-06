package com.github.hiagoboliveira.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.view.Results;
import com.github.hiagoboliveira.entity.MyPojo;
import com.github.hiagoboliveira.repository.GenericDAO;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;

@Controller
@Path("mypojo")
public class MyPojoController {
    @Inject
    private Result result;
    @Inject
    private GenericDAO genericRepository;

    @Get
    @Path("/list")
    public void list() {
        result.include("list", genericRepository.listAll(MyPojo.class));
    }

    @Post
    @Path("/add")
    public void add(MyPojo myPojo) {

        ObjectMapper mapper = new ObjectMapper();

        genericRepository.add(myPojo);
        try {
            result.use(Results.json())
                    .withoutRoot()
                    .from(mapper.writeValueAsString(myPojo))
                    .serialize();

        } catch (Exception e) {
            result.use(Results.http()).sendError(500);
        }
    }


}
