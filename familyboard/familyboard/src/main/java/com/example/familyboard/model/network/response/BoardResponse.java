package com.example.familyboard.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@Accessors(chain = true)
@NoArgsConstructor
public class BoardResponse {
    public String title;

    public String content;

    public BoardResponse(String title, String content){
        this.title = title;
        this.content = content;
    }
}
