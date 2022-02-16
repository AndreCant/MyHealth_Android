package it.mwt.myhealth.model;

import java.io.Serializable;

public class Exam implements Serializable {

    private long id;
    private String code;
    private String name;
    private Double price;
    private String specialization;
    private String subSpecialization;
    private String type;
    private String description;
    private String imageUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSubSpecialization() {
        return subSpecialization;
    }

    public void setSubSpecialization(String subSpecialization) {
        this.subSpecialization = subSpecialization;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
