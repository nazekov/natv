package kg.mega.natv.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.mega.natv.utils.DateUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_prices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Double pricePerLetter;

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(nullable = false)
    Date startDate;

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(nullable = false)
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