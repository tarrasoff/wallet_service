package itrum.testexercisewallet.dto;

import itrum.testexercisewallet.entity.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {

    private UUID id;

    private OperationType operationType;

    private Double amount;
}