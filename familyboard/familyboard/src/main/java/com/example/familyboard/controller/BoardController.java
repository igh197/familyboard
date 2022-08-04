package com.example.familyboard.controller;
import com.example.familyboard.model.network.request.BoardRequest;
import com.example.familyboard.model.network.response.BoardResponse;
import com.example.familyboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")  //default path
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    public BoardResponse create(BoardRequest boardRequest){  //crud create
        return boardService.create(boardRequest);
    }
}
