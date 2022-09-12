package com.windula.demoetl.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@Entity(name = "beer")
public class Beer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private int id;
    @Column(name="beer_id", unique=true, nullable=false)
    private int beerId;
    @Column(name="beer_name")
    private String beerName;
    @Column(name="tagline")
    private String transactionDate;
    @Column(name="first_brewed")
    private String firstBrewed;
    @Column(name="beer_description")
    private String description;
    @Column(name="image_url")
    private String imageUrl;
    @Column(name="active_status")
    private int activeStatus;

    @Column(name="created_date")
    private Timestamp createdDate;

    @Column(name="modified_date")
    private Timestamp modifiedDate;

    public Beer(int beerId, String beerName, String transactionDate, String firstBrewed, String description, String imageUrl) {
        this.beerId = beerId;
        this.beerName = beerName;
        this.transactionDate = transactionDate;
        this.firstBrewed = firstBrewed;
        this.description = description;
        this.imageUrl = imageUrl;
        this.activeStatus = 1;
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
        this.modifiedDate = Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", beerId=" + beerId +
                ", beerName='" + beerName + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", firstBrewed=" + firstBrewed +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", activeStatus=" + activeStatus +
                '}';
    }
}
