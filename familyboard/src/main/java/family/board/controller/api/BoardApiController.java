package family.board.controller.api;

import family.board.controller.ifs.CrudInterface;
import family.board.model.entity.Board;
import family.board.model.network.Header;
import family.board.model.network.response.BoardApiResponse;
import family.board.service.BoardApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/board")
public class BoardApiController implements CrudInterface<Board, BoardApiResponse> {
    private final BoardApiLogicService boardApiLogicService;

    @Override
    @PostMapping("/save")
    public Header<BoardApiResponse> save(Header<Board> request) {
        return boardApiLogicService.save(request);
    }

    @Override
    @GetMapping("/{id}")
    public Header<BoardApiResponse> read(@PathVariable Long id) {
        return boardApiLogicService.read(id);
    }

    @Override
    @PutMapping("/")
    public Header<BoardApiResponse> update(@ModelAttribute Header<Board> request) {
        return boardApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        return boardApiLogicService.delete(id);
    }
    @GetMapping("")
    public Header<List<BoardApiResponse>> search(@PageableDefault(sort = "id",direction = Sort.Direction.ASC,size = 15) Pageable pageable){
        return boardApiLogicService.search(pageable);
    }
}
