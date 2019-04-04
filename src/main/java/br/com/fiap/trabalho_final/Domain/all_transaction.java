package br.com.fiap.trabalho_final.Domain;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class all_transaction {

    private double sum;
    private double min;
    private double max;
    private double avg;
    private long count;
}