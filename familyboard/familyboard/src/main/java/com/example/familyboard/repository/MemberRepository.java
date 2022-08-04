package com.example.familyboard.repository;

import com.example.familyboard.model.entity.Member;
import com.example.familyboard.model.network.request.MemberRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByAccount(String account);
}
