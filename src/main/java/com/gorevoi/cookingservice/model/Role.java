package com.gorevoi.cookingservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
@NoArgsConstructor
@ToString
public class Role extends BaseModel {
    @Column
    @Enumerated(value = EnumType.STRING)
    private ERoles name;

}
