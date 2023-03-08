package kg.mega.natv.model.entity;

import kg.mega.natv.utils.DateUtil;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_channels")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String name;

    @Column(nullable = false)
    boolean isActive = true;

    String pathLogo;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    List<PriceText> pricesText;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    List<DiscountText> discountsText;

    public void addNewPriceText(PriceText price) {
        if (pricesText == null) {
            pricesText = new ArrayList<>();
        }
        if (pricesText.size() != 0) {
            pricesText.get(pricesText.size() - 1)
                    .setEndDate(new Date());
        }

        price.setChannel(this);
        pricesText.add(price);
    }
}