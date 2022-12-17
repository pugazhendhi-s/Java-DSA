package com.f5_oops.o3_properties.interfaces;

public class TurboEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Turbo Engine start");
    }

    @Override
    public void stop() {
        System.out.println("Turbo Engine stop");
    }

    @Override
    public void acc() {
        System.out.println("Turbo Engine accelerate");
    }
}
