package br.com.fiap.trabalho_final.Domain;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class new_transaction {
    private double amount ;
    private long timestamp;
    private long dataInsert;
}
