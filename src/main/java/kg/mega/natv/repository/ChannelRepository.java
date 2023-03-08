package kg.mega.natv.repository;

import kg.mega.natv.model.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    Optional<Channel> findByNameIgnoreCase(String name);

//    Optional<Channel> findById(Long id);

    boolean existsById(Long id);
}
