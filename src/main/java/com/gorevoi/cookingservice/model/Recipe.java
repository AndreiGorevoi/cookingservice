package com.gorevoi.cookingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Recipe extends BaseModel {
    @Column
    private String title;

    @Column
    private String link;

    @Column
    private String description;
//Dinner, breakfast or supper?
    @Column
    @Enumerated(value = EnumType.STRING)
    private ETypeOfMealTime mealTime;

    @Column
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id",insertable = false, updatable = false)
//    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private User user;

}
