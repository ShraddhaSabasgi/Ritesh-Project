package com.example.blogging.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String category_title;

    private String category_description;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Post> posts=new ArrayList<>();

}
