package family.board.controller.api;

import family.board.controller.ifs.CrudInterface;
import family.board.model.network.Header;
import family.board.model.network.request.BoardApiRequest;
import family.board.model.network.response.BoardApiResponse;
import family.board.service.BoardApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/board")
public class BoardApiController implements CrudInterface<BoardApiRequest, BoardApiResponse> {
    @Autowired
    private BoardApiLogicService boardApiLogicService;

    @Override
    @PostMapping("/create")
    public Header<BoardApiResponse> create(@RequestBody Header<BoardApiRequest> request) {
        return boardApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<BoardApiResponse> read(@PathVariable Integer id) {
        return boardApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<BoardApiResponse> update(@RequestBody Header<BoardApiRequest> request) {
        return boardApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Integer id) {
        return boardApiLogicService.delete(id);
    }
    @GetMapping("")
    public Header<List<BoardApiResponse>> search(@PageableDefault(sort = "id",direction = Sort.Direction.ASC,size = 15) Pageable pageable){
        return boardApiLogicService.search(pageable);
    }
}
