package com.example.familyboard.service;

import com.example.familyboard.model.entity.Board;
import com.example.familyboard.model.entity.Header;
import com.example.familyboard.model.entity.Member;
import com.example.familyboard.model.network.Pagination;
import com.example.familyboard.model.network.request.BoardRequest;
import com.example.familyboard.model.network.response.BoardResponse;
import com.example.familyboard.repository.BoardRepository;
import com.example.familyboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    private BoardResponse response(Board board) {
        BoardResponse boardResponse = BoardResponse.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .build();
        return boardResponse;
    }
    public BoardResponse create(BoardRequest boardRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();   //사용자 기억용
        Board board = new Board();
        board.setTitle(boardRequest.getTitle()); //board 객체에 임시 저장
        board.setContent(boardRequest.getContent());
        board.setMember(memberRepository.findByAccount(((Member) principal).getAccount()).orElseThrow()); //member_id저장
        boardRepository.save(board); //familyboard db에 저장
        return response(board);
    }


    public Header<List<BoardResponse>> search(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        List<BoardResponse> boardResponseList = boards.stream().map(board->response(board)).collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(boards.getTotalPages())
                .totalElements(boards.getTotalElements())
                .currentPage(boards.getNumber())
                .currentElements(boards.getNumberOfElements())
                .build();
        return Header.OK(boardResponseList,pagination);
    }

    public BoardResponse read(Long id) {
        Optional<Board> optional = boardRepository.findById(id);

        return optional.map(board -> response(board)).orElseThrow();
    }

    public BoardResponse update(Long id,BoardRequest boardRequest) {

        Optional<Board> optional = boardRepository.findById(boardRequest.getId());
        //DB에 저장 먼저 하기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();  //member_id 채우기용
        Board newBoard = new Board(boardRequest.getId(), boardRequest.getTitle(), boardRequest.getContent(),memberRepository.findByAccount(((Member) principal).getAccount()).orElseThrow());
        boardRepository.save(newBoard);
        return optional.map(board ->  //반환
            response(board.setTitle(boardRequest.getTitle()).setContent(boardRequest.getContent()))).orElseThrow();
        }


    public void delete(Long id) {
        Optional<Board> optional = boardRepository.findById(id);
        boardRepository.deleteById(id);

    }
}
