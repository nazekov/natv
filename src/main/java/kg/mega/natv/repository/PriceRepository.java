package kg.mega.natv.repository;

import kg.mega.natv.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(value = "select price_per_letter from tb_prices_text " +
                    "where channel_id = ?1 and end_date = ?2",
            nativeQuery = true)
    Double findByChannelIdAndEndDate(Long id, Date date);
}
