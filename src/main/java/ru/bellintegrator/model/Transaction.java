package ru.bellintegrator.model;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Measurement(name = "jmeter")
public class Transaction {
    @TimeColumn
    @Column(name = "time")
    private Instant time;
    private String name;
    @Column(name = "Min")
    private String min;
    @Column(name = "Average")
    private String average;
    @Column(name = "Max")
    private String max;
    @Column(name = "pct90")
    private String pct90;
    @Column(name = "Total")
    private String total;
    private String error;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getPct90() {
        return pct90;
    }

    public void setPct90(String pct90) {
        this.pct90 = pct90;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Transaction(String name, String min, String average, String max, String pct90, String total, String error) {
        this.name = name;
        this.min = min;
        this.average = average;
        this.max = max;
        this.pct90 = pct90;
        this.total = total;
        this.error = error;
    }

    public Transaction(Instant time, String name, String min, String average, String max, String pct90, String total, String error) {
        this.time = time;
        this.name = name;
        this.min = min;
        this.average = average;
        this.max = max;
        this.pct90 = pct90;
        this.total = total;
        this.error = error;
    }

    public Transaction() {
    }
}
