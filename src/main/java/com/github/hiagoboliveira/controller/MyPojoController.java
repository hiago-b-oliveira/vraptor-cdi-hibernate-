package com.github.hiagoboliveira.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.view.Results;
import com.github.hiagoboliveira.beans.NumberGeneratorBean;
import com.github.hiagoboliveira.beans.RemoteNumberGenerator;
import com.github.hiagoboliveira.entity.MyPojo;
import com.github.hiagoboliveira.repository.GenericDAO;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ejb.EJB;
import javax.inject.Inject;

@Controller
@Path("mypojo")
public class MyPojoController {
    @Inject
    private Result result;
    @Inject
    private GenericDAO genericRepository;
    @EJB
    private RemoteNumberGenerator remoteNumberGenerator;

    @Get
    @Path("/list")
    public void list() {
        result.include("id", remoteNumberGenerator.generateNumber());
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
