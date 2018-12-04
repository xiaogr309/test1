package test.services;

import test.imp.HelloServerImp;

public class HelloServerImpl implements HelloServerImp {

    @Override
    public String sayHello(String name) {
        System.out.println(name);
        return "hello" + name;

    }
}
