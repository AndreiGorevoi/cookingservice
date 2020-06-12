package com.gorevoi.cookingservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString
public class UserOfService extends BaseModel {

    @Column
    private String name;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> rolesList;

//    @Column
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Recipe> recipeList;

}
