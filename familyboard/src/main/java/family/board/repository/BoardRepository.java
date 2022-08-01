package family.board.repository;

import family.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    Optional<Board> findByTitle(String title);
    Optional<Board> findById(Long id);
}
