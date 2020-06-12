package com.gorevoi.cookingservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Recipe extends BaseModel {
    @Column
    private String title;

    @Column
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserOfService user;
}
