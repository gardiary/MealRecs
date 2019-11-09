package com.meal.recs.model;

/**
 * User: gardiary
 * Date: 09/11/19, 14.55
 */
public class Time {
    private Integer value;
    private TimeUnit timeUnit;

    public Time() {
    }

    public Time(Integer value, TimeUnit timeUnit) {
        this.value = value;
        this.timeUnit = timeUnit;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getTimeAsRecipeSchemaContent() {
        return "PT" + this.value + timeUnit.code;
    }
}
