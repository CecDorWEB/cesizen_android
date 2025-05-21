package com.example.cesizen.models;

public class ResultDTO {
    private Long id;
    private String title;
    private String content;
    private Integer minScore;
    private Integer maxScore;

    public ResultDTO(Long id, String title, String content, Integer minScore, Integer maxScore) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.minScore = minScore;
        this.maxScore = maxScore;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }
}
