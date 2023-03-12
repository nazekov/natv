package kg.mega.natv.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_channels")
@Getter
@Setter
//@ToString()
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
    boolean active = true;

    String pathLogo;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    List<Price> priceList;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    List<Discount> discountsText;

    public void addNewPriceText(Price price) {
        if (priceList == null) {
            priceList = new ArrayList<>();
        }
        if (priceList.size() != 0) {
            priceList.get(priceList.size() - 1)
                    .setEndDate(new Date());
        }

        price.setChannel(this);
        priceList.add(price);
    }
}