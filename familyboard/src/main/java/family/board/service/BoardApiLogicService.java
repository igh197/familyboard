package family.board.service;

import family.board.controller.ifs.CrudInterface;
import family.board.model.entity.Board;
import family.board.model.network.Header;
import family.board.model.network.Pagination;
import family.board.model.network.response.BoardApiResponse;
import family.board.repository.BoardRepository;
import family.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardApiLogicService implements CrudInterface<Board, BoardApiResponse> {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Header<List<BoardApiResponse>> search(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        List<BoardApiResponse> boardApiResponseList = boards.stream().map(board->response(board)).collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(boards.getTotalPages())
                .totalElements(boards.getTotalElements())
                .currentPage(boards.getNumber())
                .currentElements(boards.getNumberOfElements())
                .build();
        return Header.OK(boardApiResponseList,pagination);
    }

    @Override
    public Header<BoardApiResponse> create(Header<Board> request) {

        Board board = request.getData();
        Board newBoard = Board.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .file(board.getFile())
                .build();
        return Header.OK(response(newBoard));
    }

    private BoardApiResponse response(Board board) {
        BoardApiResponse boardApiResponse = BoardApiResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .file(board.getFile())
                .build();
        return boardApiResponse;
    }

    @Override
    public Header<BoardApiResponse> read(Long id) {
        Optional<Board> optional = boardRepository.findById(id);
        return optional.map(board -> response(board))
                .map(boardApiResponse -> Header.OK(boardApiResponse))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    @Override
    public Header<BoardApiResponse> update(Header<Board> request) {
        Board board = request.getData();
        Optional<Board> optional = boardRepository.findByTitle(board.getTitle());
        return optional.map(newBoard -> {
            newBoard.setTitle(board.getTitle())
                 .setContent(board.getContent());


            return board;
        }).map(newBoard -> boardRepository.save(newBoard)).map(newBoard->response(board))
                .map(boardApiResponse -> Header.OK(boardApiResponse))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Board> optional = boardRepository.findById(id);
        return optional.map(board->{
            boardRepository.delete(board);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }
}
