package com.example.familyboard.model.network.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
public class BoardRequest {
    private Long id;
    private String title;
    private String content;

    public BoardRequest(String title, String content){
        this.title = title;
        this.content = content;
    }
}
