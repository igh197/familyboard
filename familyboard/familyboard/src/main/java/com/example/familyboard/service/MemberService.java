package com.example.familyboard.service;

import com.example.familyboard.model.entity.Member;
import com.example.familyboard.model.network.request.MemberRequest;
import com.example.familyboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    PasswordEncoder passwordEncoder;

    public Long save(MemberRequest memberRequest){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        memberRequest.setPassword(passwordEncoder.encode(memberRequest.getPassword())); //password encryption

        return memberRepository.save(Member.builder().account(memberRequest.getAccount()).name(memberRequest.getName())
                .auth(memberRequest.getAuth()).email(memberRequest.getEmail()).password(memberRequest.getPassword()).build()).getId();
    }

    @Override
    public Member loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByAccount(username).orElseThrow(() -> new UsernameNotFoundException((username)));
    }

}
