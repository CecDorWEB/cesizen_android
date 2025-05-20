package com.example.cesizen.models;

public class AnswerDTO {
    private Long id;
    private String title;
    private Integer point;
    private boolean multiplied;

    public AnswerDTO(Long id, String title, Integer point, boolean multiplied) {
        this.id = id;
        this.title = title;
        this.point = point;
        this.multiplied = multiplied;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public boolean isMultiplied() {
        return multiplied;
    }

    public void setMultiplied(boolean multiplied) {
        this.multiplied = multiplied;
    }
}
