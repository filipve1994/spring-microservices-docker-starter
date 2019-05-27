package com.filip.eurekaimage.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {

    private int id;
    private String name;
    private String url;

    public Image(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

}
