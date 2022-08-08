package com.example.familyboard.controller;
import antlr.collections.impl.LList;
import com.example.familyboard.model.entity.Header;
import com.example.familyboard.model.network.request.BoardRequest;
import com.example.familyboard.model.network.response.BoardResponse;
import com.example.familyboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")  //default path
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public Header<List<BoardResponse>> search(@PageableDefault Pageable pageable) {
        return boardService.search(pageable);
    }

    @PostMapping("/create")
    public BoardResponse create(BoardRequest boardRequest){  //crud create
        return boardService.create(boardRequest);
    }
    @GetMapping("/{id}")
    public BoardResponse read(@PathVariable Long id){
        return boardService.read(id);
    }
    @PatchMapping("/update/{id}")
    public BoardResponse update(@PathVariable Long id,@RequestBody BoardRequest boardRequest){
        return boardService.update(id, boardRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        boardService.delete(id);
    }
}
