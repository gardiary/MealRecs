package com.meal.recs.model;

/**
 * User: gardiary
 * Date: 09/11/19, 14.59
 */
public enum TimeUnit {
    SECOND("S", "sec"), MINUTE("M", "min"), HOUR("H", "hour");

    final String code;
    final String label;

    TimeUnit(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
