package com.coupon;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Coupon {

    private long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private int amount;
    private couponTypes type;
    private String message;
    private double price;
    private String image;


    public Coupon(long id, String title, int amount, couponTypes type, String message, double price, String image) {
        LocalDate initialDate = LocalDate.now();
        setId(id);
        setTitle(title);
        setStartDate(initialDate);
        setEndDate(initialDate.plusDays(1));
        setAmount(amount);
        setType(type);
        setMessage(message);
        setPrice(price);
        setImage(image);
    }
    public Coupon(){

    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {

        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {

        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public couponTypes getType() {
        return type;
    }

    private void setType(couponTypes type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format(
                "Coupon (id=%s, title=%s, startDate=%s, endDate=%s, amount=%s, type=%s, message=%s, price=%s, image=%s)", this.id, this.title, this.startDate, this.endDate, this.amount, this.type, this.message, this.price, this.image);
    }
}
