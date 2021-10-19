package family.board.repository;

import family.board.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findMemberById(Integer id);
    UserDetails findMemberByAccount(String account);
}
