package kg.mega.natv.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.mega.natv.utils.DateUtil;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_discounts_text")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer fromDaysCount;

    Double discountPercent;

    Date startDate;

    Date endDate;

    @ManyToOne
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    @JsonIgnore
    Channel channel;

    @PrePersist
    private void setDates() {
        setStartDate(new Date());
        setEndDate(DateUtil.getInstance().getEndDate());
    }
}