package com.example.cesizen.models;

public class ParagraphDTO {
    private Long id;
    private Integer paragraphOrder;
    private String title;
    private String body;
    private String visualSupport;
    private String altVisualSupport;

    public ParagraphDTO(Long id, Integer paragraphOrder, String title, String body, String visualSupport, String altVisualSupport) {
        this.id = id;
        this.paragraphOrder = paragraphOrder;
        this.title = title;
        this.body = body;
        this.visualSupport = visualSupport;
        this.altVisualSupport = altVisualSupport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParagraphOrder() {
        return paragraphOrder;
    }

    public void setParagraphOrder(Integer paragraphOrder) {
        this.paragraphOrder = paragraphOrder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getVisualSupport() {
        return visualSupport;
    }

    public void setVisualSupport(String visualSupport) {
        this.visualSupport = visualSupport;
    }

    public String getAltVisualSupport() {
        return altVisualSupport;
    }

    public void setAltVisualSupport(String altVisualSupport) {
        this.altVisualSupport = altVisualSupport;
    }
}
