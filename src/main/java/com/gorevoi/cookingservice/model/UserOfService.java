package com.gorevoi.cookingservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> rolesList;


}
