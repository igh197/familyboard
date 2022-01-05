package family.board.service;

import family.board.model.entity.Member;
import family.board.model.network.Header;
import family.board.model.network.request.MemberApiRequest;
import family.board.model.network.response.MemberApiResponse;
import family.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberApiLogicService{
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Header<MemberApiResponse> create(Header<MemberApiRequest> request) {
        MemberApiRequest memberApiRequest = request.getData();
        Member member = Member.builder()
                .name(memberApiRequest.getName())
                .account(memberApiRequest.getAccount())
                .role(memberApiRequest.getRole())
                .password(passwordEncoder.encode(memberApiRequest.getPassword()))
                .email(memberApiRequest.getEmail())
                .build();
        Member newMember = memberRepository.save(member);
        return Header.OK(response(newMember));
    }
    private MemberApiResponse response(Member member) {
        MemberApiResponse memberApiResponse = MemberApiResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .account(member.getAccount())
                .password(member.getPassword())
                .email(member.getEmail())
                .build();
        return memberApiResponse;
    }

    public Header<MemberApiResponse> read(Integer id) {
        Optional<Member> optional = memberRepository.findById(id);
        return optional.map(member -> response(member))
                .map(memberApiResponse -> Header.OK(memberApiResponse))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }
    public Header<MemberApiResponse> update(Header<MemberApiRequest> request){
            MemberApiRequest memberApiRequest = request.getData();
            Optional<Member> optional = memberRepository.findByPassword(passwordEncoder.encode(memberApiRequest.getPassword()));
            return optional.map(member->{
                member.setName(memberApiRequest.getName())
                        .setEmail(memberApiRequest.getEmail())
                        .setRole(memberApiRequest.getRole())
                        .setAccount(memberApiRequest.getAccount())
                        .setPassword(passwordEncoder.encode(memberApiRequest.getPassword()));
                        return member;

            }).map(member -> memberRepository.save(member)).map(member -> response(member))
                    .map(memberApiResponse -> Header.OK(memberApiResponse))
                    .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public Header delete(Integer id) {
        Optional<Member> optional = memberRepository.findById(id);
        return optional.map(member->{
            memberRepository.delete(member);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }
}
