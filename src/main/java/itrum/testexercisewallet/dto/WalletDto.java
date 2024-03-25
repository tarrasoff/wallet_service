package itrum.testexercisewallet.dto;

import itrum.testexercisewallet.entity.OperationType;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Id cannot be null")
    private UUID id;

    @NotNull(message = "Operation type cannot be null")
    private OperationType operationType;

    @NotNull(message = "Amount cannot be null")
    private Double amount;
}