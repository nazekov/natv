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
import java.util.List;

@Entity
@Table(name = "tb_channels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, length = 50)
    String name;

    @Column(columnDefinition = "boolean default true")
    boolean active = true;

    String pathLogo;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    List<Price> priceList;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    List<Discount> discounts;
}