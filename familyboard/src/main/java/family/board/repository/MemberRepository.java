package family.board.repository;

import family.board.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
   Optional<Member> findByAccount(String account);
   Optional<Member> findByPassword(String password);
   Member getByAccount(String account);
}
