package kg.mega.natv.repository;

import kg.mega.natv.model.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    Optional<Channel> findByNameIgnoreCase(String name);

    boolean existsById(Long id);

    Optional<String> findDistinctByPathLogoEndingWith(String logoName);

    @Query(value = "select * from tb_channels " +
                    "where active = ?1 " +
                    "order by name ",
            nativeQuery = true)
    List<Channel> findAllByActive(boolean active);

}
