package com.example.familyboard.service;

import com.example.familyboard.model.entity.Board;
import com.example.familyboard.model.network.request.BoardRequest;
import com.example.familyboard.model.network.response.BoardResponse;
import com.example.familyboard.repository.BoardRepository;
import com.example.familyboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    public BoardResponse create(BoardRequest boardRequest) {
        Board board = new Board();
        BoardResponse boardResponse = new BoardResponse();
        board.setTitle(boardRequest.getTitle()); //board 객체에 임시 저장
        board.setContent(boardRequest.getContent());
        board.setMember(memberRepository.findByAccount("ymlee68").orElseThrow());
        boardRepository.save(board); //familyboard db에 저장
        boardResponse.setTitle(board.getTitle()); //응답값 저장
        boardResponse.setContent(board.getContent());
        return boardResponse;
    }


}
