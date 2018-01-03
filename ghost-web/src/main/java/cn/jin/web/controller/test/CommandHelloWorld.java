/*
package cn.jin.userapi.controller.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;



public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws InterruptedException {
        // a real example would do work like a network call here
        return "Hello " + name + "!";
    }

    @Override protected String getFallback() {
        return "fallback_"+name;
    }
}*/
