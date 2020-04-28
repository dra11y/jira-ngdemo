package com.grushka.demo.entity;

import net.java.ao.Entity;

public interface Hero extends Entity {
    // ID already provided by Entity interface

    String getName();
    void setName(String name);
}
