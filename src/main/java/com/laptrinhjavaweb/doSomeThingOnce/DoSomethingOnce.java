package com.laptrinhjavaweb.doSomeThingOnce;

import com.laptrinhjavaweb.initialDatabase.InitialDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class DoSomethingOnce {
    @Autowired
    private InitialDatabase initialDatabase;

    Map<Boolean, Boolean> cache = new ConcurrentHashMap<>();

    private void doSomething(){
        initialDatabase.createUser();
    }
    public void doSomethingOnce(){
        cache.computeIfAbsent(true, x ->{
            doSomething();;
            return true;
        });
    }
}
