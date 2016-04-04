package com.github.hiagoboliveira.controller;

import br.com.caelum.vraptor.*;
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
        result.include("list", genericRepository.listAll(MyPojo.class));
    }

    @Post
    @Path("/add")
    public void add(MyPojo myPojo) {

        genericRepository.add(myPojo);
        //result.redirectTo(this).list();
        result.use(Results.json())
                .withoutRoot()
                .from("Add!")
                .serialize();

    }


}
