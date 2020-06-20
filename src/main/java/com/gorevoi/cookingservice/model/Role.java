package com.gorevoi.cookingservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString
public class Role extends BaseModel {
    @Column
    @Enumerated(value = EnumType.STRING)
    private ERoles name;

//    @ManyToMany(fetch = FetchType.LAZY)
//    private List<UserOfService> users;

}
