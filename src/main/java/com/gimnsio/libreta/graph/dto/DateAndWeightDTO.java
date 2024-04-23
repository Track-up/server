package com.gimnsio.libreta.graph.dto;


import java.util.Date;

public class DateAndWeightDTO {

    private long id;
    private Date date;
    private double weight;

    public DateAndWeightDTO(Date date, double weight) {
        this.date = date;
        this.weight = weight;
    }

    public DateAndWeightDTO(long id, Date date, double weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DateAndWeightDTO{" +
                "date=" + date +
                ", weight=" + weight +
                '}';
    }
}
