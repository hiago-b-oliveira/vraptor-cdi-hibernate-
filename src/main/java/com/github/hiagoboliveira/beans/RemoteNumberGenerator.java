package com.github.hiagoboliveira.beans;

import javax.ejb.Remote;

@Remote
public interface RemoteNumberGenerator {
    Integer generateNumber();
}
