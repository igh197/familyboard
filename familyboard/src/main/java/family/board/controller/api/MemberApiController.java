package family.board.controller.api;

import family.board.controller.ifs.CrudInterface;
import family.board.model.network.Header;
import family.board.model.network.request.MemberApiRequest;
import family.board.model.network.response.MemberApiResponse;
import family.board.repository.MemberRepository;
import family.board.service.BoardApiLogicService;
import family.board.service.MemberApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/member")
public class MemberApiController implements CrudInterface<MemberApiRequest,MemberApiResponse> {
    @Autowired
    private BoardApiLogicService boardApiLogicService;
    @Autowired
    private MemberApiLogicService memberApiLogicService;
    @Autowired
    private MemberRepository memberRepository;
    @Override
    @PostMapping("/create")
    public Header<MemberApiResponse> create(@RequestBody Header<MemberApiRequest> request) {
        return memberApiLogicService.create(request);
    }

    @Override
    @GetMapping("/read/{id}")
    public Header<MemberApiResponse> read(@PathVariable Integer id) {
        return memberApiLogicService.read(id);
    }

    @Override
    @PutMapping("/update")
    public Header<MemberApiResponse> update(@RequestBody Header<MemberApiRequest> request) {
        return memberApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public Header delete(@PathVariable Integer id) {
        return memberApiLogicService.delete(id);
    }

    @GetMapping("/login")
    public Header<MemberApiResponse> loginForm(HttpServletRequest req) {
        String referer = req.getHeader("Referer");
        req.getSession().setAttribute("prevPage", referer);
        return memberApiLogicService.create((Header<MemberApiRequest>) req);
    }
}
