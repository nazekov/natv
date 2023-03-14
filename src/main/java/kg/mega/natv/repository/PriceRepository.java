package kg.mega.natv.repository;

import kg.mega.natv.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(value = "select price_per_letter from tb_prices " +
                    "where channel_id = ?1 and end_date = '2999-12-31 00:00:00'",
            nativeQuery = true)
    Double findByChannelIdAndEndDate(Long id);
}
