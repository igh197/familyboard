package family.board.controller.api;

import family.board.model.entity.Member;
import family.board.repository.MemberRepository;
import family.board.service.MemberApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberApiController{
    @Autowired
    private MemberApiLogicService memberApiLogicService;
    @Autowired
    private MemberRepository memberRepository;
    @PostMapping("/create")
    public Member create(@RequestBody Member member) {
        return memberApiLogicService.joinUser(member);
    }

    @GetMapping("{id}")
    public Member memberAccess(@PathVariable Integer id){
        return memberApiLogicService.loadMemberById(id);
    }
}
