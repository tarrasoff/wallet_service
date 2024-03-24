package itrum.testexercisewallet.mapper;

import itrum.testexercisewallet.dto.WalletDto;
import itrum.testexercisewallet.entity.OperationType;
import itrum.testexercisewallet.entity.Wallet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class WalletMapperTest {
    @Spy
    private WalletMapperImpl walletMapper;

    @Test
    public void testToDto() {
        Wallet wallet = new Wallet();
        wallet.setId(UUID.randomUUID());
        wallet.setOperationType(OperationType.DEPOSIT);
        wallet.setAmount(100.0);

        WalletDto walletDto = walletMapper.toDto(wallet);

        assertEquals(wallet.getId(), walletDto.getId());
        assertEquals(wallet.getOperationType(), walletDto.getOperationType());
        assertEquals(wallet.getAmount(), walletDto.getAmount());
    }

    @Test
    public void testToEntity() {
        WalletDto walletDto = new WalletDto();
        walletDto.setId(UUID.randomUUID());
        walletDto.setOperationType(OperationType.DEPOSIT);
        walletDto.setAmount(100.0);

        Wallet wallet = walletMapper.toEntity(walletDto);

        assertEquals(walletDto.getId(), wallet.getId());
        assertEquals(walletDto.getOperationType(), wallet.getOperationType());
        assertEquals(walletDto.getAmount(), wallet.getAmount());
    }
}