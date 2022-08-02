package family.board.controller.api;

import family.board.model.network.request.MemberApiRequest;
import family.board.service.MemberApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberApiController {
    private final MemberApiLogicService memberApiLogicService;


    @PostMapping(value = "/signup")
    public String signup(MemberApiRequest memberApiRequest) {
        memberApiLogicService.save(memberApiRequest);
        return "redirect:/api/board";
    }



}
