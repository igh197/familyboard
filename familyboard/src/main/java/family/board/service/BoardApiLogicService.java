package family.board.service;

import family.board.controller.ifs.CrudInterface;
import family.board.model.entity.Board;
import family.board.model.network.Header;
import family.board.model.network.request.BoardApiRequest;
import family.board.model.network.response.BoardApiResponse;
import family.board.repository.BoardRepository;
import family.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BoardApiLogicService implements CrudInterface<BoardApiRequest, BoardApiResponse> {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public Header<BoardApiResponse> create(Header<BoardApiRequest> request) {
        BoardApiRequest boardApiRequest = request.getData();
        Board board = Board.builder()
                .id(boardApiRequest.getId())
                .title(boardApiRequest.getTitle())
                .content(boardApiRequest.getContent())
                .createdAt(LocalDateTime.now())
                .createdBy(boardApiRequest.getCreatedBy())
                .updatedAt(LocalDateTime.now())
                .updatedBy(boardApiRequest.getUpdatedBy())
                .member(memberRepository.getById(boardApiRequest.getId()))
                .build();
        Board newBoard = boardRepository.save(board);
        return Header.OK(response(newBoard));
    }

    private BoardApiResponse response(Board board) {
        BoardApiResponse boardApiResponse = BoardApiResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .file(board.getFile())
                .createdAt(board.getCreatedAt())
                .createdBy(board.getCreatedBy())
                .updatedAt(LocalDateTime.now())
                .updatedBy(board.getUpdatedBy())
                .memberId(board.getMember().getId())
                .build();
        return boardApiResponse;
    }

    @Override
    public Header<BoardApiResponse> read(Integer id) {
        Optional<Board> optional = boardRepository.findById(id);
        return optional.map(board -> response(board))
                .map(boardApiResponse -> Header.OK(boardApiResponse))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header update(Header<BoardApiRequest> request) {
        BoardApiRequest boardApiRequest = request.getData();
        Optional<Board> optional = boardRepository.findById(boardApiRequest.getId());
        return optional.map(board -> {
            board.setTitle(boardApiRequest.getTitle())
                 .setContent(boardApiRequest.getContent())
                 .setFile(boardApiRequest.getFile())
                 .setUpdatedAt(LocalDateTime.now())
                 .setUpdatedBy(boardApiRequest.getUpdatedBy());
            return board;
        }).map(board -> boardRepository.save(board)).map(board->response(board))
                .map(boardApiResponse -> Header.OK(boardApiResponse))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Integer id) {
        Optional<Board> optional = boardRepository.findById(id);
        return optional.map(board->{
            boardRepository.delete(board);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }
}
