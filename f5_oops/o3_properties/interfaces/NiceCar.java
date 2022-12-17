package com.f5_oops.o3_properties.interfaces;

public class NiceCar {

    private Engine engine;
    private static final Media player = new CDPlayer();
    // u can't change media player whenever declared like this

    public NiceCar(){
        engine = new TurboEngine(); // default engine
    }

    public NiceCar(Engine engine) { // whatever engine u need , provide that
        this.engine = engine;
    }

    public void start(){
        engine.start();
    }
    public void stop(){
        engine.stop();
    }
    public void startMusic(){
        player.start();
    }
    public void stopMusic(){
        player.stop();
    }
    public void upgradeEngine(Engine engine){
        // engine put as final means u can't change engine by this
        this.engine = engine;
    }
    public void upgradeEngine(){
        this.engine = new ElectricEngine();
    }
}
