package com.f5_oops.o3_properties.interfaces.extend;

public class DeathNote implements Light, Misa{
    @Override
    public void lightYagami() {

    }

    @Override
    public void misaAmane() {

    }
    // likewise class( interface to interface extends)
    // Misa interface extends the Light interface
    // when a class implements Misa interfaces
    // class must implement methods in both
    // Misa and Light
}
