package com.grushka.demo.rest;

import com.grushka.demo.entity.Hero;

import javax.xml.bind.annotation.*;
@XmlAccessorType(XmlAccessType.FIELD)
public class HeroModel {
    Integer id;
    String name;

    public HeroModel() {
    }

    public HeroModel(Hero hero) {
        this.id = hero.getID();
        this.name = hero.getName();
    }
}