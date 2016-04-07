package com.github.hiagoboliveira.beans;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.Random;


@Stateless
public class NumberGeneratorBean implements RemoteNumberGenerator {

    private Random random;

    public Integer generateNumber() {
        return random.nextInt(Integer.MAX_VALUE);

    }

    @PostConstruct
    public void init() {
        random = new Random();
    }
    
}
