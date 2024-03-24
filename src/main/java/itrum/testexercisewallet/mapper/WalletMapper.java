package itrum.testexercisewallet.mapper;

import itrum.testexercisewallet.dto.WalletDto;
import itrum.testexercisewallet.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WalletMapper {

    WalletDto toDto(Wallet wallet);

    Wallet toEntity(WalletDto walletDto);
}