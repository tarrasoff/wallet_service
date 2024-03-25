package itrum.testexercisewallet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "wallets")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType operationType;

    @Column(name = "amount")
    private Double amount;
}