package com.github.hiagoboliveira.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import com.github.hiagoboliveira.entity.MyPojo;
import com.github.hiagoboliveira.repository.GenericDAO;

import javax.inject.Inject;

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
        result.use(Results.json())
                .withoutRoot()
                .from(genericRepository.listAll(MyPojo.class))
                .serialize();
    }

    @Get
    @Path("/add")
    public void add(MyPojo myPojo) {

        genericRepository.add(myPojo);

        result.use(Results.json())
                .withoutRoot()
                .from("Add!")
                .serialize();

    }


}
