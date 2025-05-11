package com.example.cesizen.models;

import java.sql.Date;

public class RessourceDTO {
    private Long id;
    private String title;
    private String headerImage;
    private String altHeaderImage;
    private String headerIntroduction;
    private Date publicationDate;
    private Date updateDate;
    private Boolean statut;
    private Long typeId;
    private Integer userId;

    // Constructeur
    public RessourceDTO(Long id, String title, String headerImage, String altHeaderImage,
                        String headerIntroduction, Date publicationDate, Date updateDate,
                        Boolean statut, Long typeId, Integer userId) {
        this.id = id;
        this.title = title;
        this.headerImage = headerImage;
        this.altHeaderImage = altHeaderImage;
        this.headerIntroduction = headerIntroduction;
        this.publicationDate = publicationDate;
        this.updateDate = updateDate;
        this.statut = statut;
        this.typeId = typeId;
        this.userId = userId;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getHeaderImage() { return headerImage; }
    public void setHeaderImage(String headerImage) { this.headerImage = headerImage; }

    public String getAltHeaderImage() { return altHeaderImage; }
    public void setAltHeaderImage(String altHeaderImage) { this.altHeaderImage = altHeaderImage; }

    public String getHeaderIntroduction() { return headerIntroduction; }
    public void setHeaderIntroduction(String headerIntroduction) { this.headerIntroduction = headerIntroduction; }

    public Date getPublicationDate() { return publicationDate; }
    public void setPublicationDate(Date publicationDate) { this.publicationDate = publicationDate; }

    public Date getUpdateDate() { return updateDate; }
    public void setUpdateDate(Date updateDate) { this.updateDate = updateDate; }

    public Boolean getStatut() { return statut; }
    public void setStatut(Boolean statut) { this.statut = statut; }

    public Long getTypeId() { return typeId; }
    public void setTypeId(Long typeId) { this.typeId = typeId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
}