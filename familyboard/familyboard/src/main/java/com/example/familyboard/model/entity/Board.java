package com.example.familyboard.model.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonAnyGetter
    private String title;
    @JsonAnyGetter
    private String content;

    @ManyToOne
    private Member member;

    public Board(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
