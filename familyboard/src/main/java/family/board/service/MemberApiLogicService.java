package family.board.service;

import family.board.model.entity.Member;
import family.board.model.network.request.MemberApiRequest;
import family.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberApiLogicService implements UserDetailsService {

    private final MemberRepository memberRepository;

    PasswordEncoder passwordEncoder;



    public Long save(MemberApiRequest memberApiRequest){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        memberApiRequest.setPassword(passwordEncoder.encode(memberApiRequest.getPassword()));

        return memberRepository.save(Member.builder().account(memberApiRequest.getAccount()).name(memberApiRequest.getName())
        .auth(memberApiRequest.getAuth()).email(memberApiRequest.getEmail()).password(memberApiRequest.getPassword()).build()).getId();
    }

    @Override
    public Member loadUserByUsername(String username) throws UsernameNotFoundException {
       return memberRepository.findByAccount(username).orElseThrow(() -> new UsernameNotFoundException((username)));
    }
}
