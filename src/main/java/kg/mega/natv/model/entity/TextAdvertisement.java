package kg.mega.natv.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_text_advs")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TextAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String text;

    Integer countSymbols;

    Date createdDate;

    @PrePersist
    private void setDate() {
        createdDate = new Date();
    }
}