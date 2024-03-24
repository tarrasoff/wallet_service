package itrum.testexercisewallet.controller;

import itrum.testexercisewallet.dto.WalletDto;
import itrum.testexercisewallet.service.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.UUID;

import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class WalletControllerTest {
    @InjectMocks
    private WalletController walletController;
    @Mock
    private WalletService walletService;
    @Mock
    private WalletDto walletDto;
    @Mock
    private UUID walletId;

    @Test
    void tesCreateWallet() {
        walletController.createWallet(walletDto);
        verify(walletService, times(1)).createWallet(walletDto);

    }

    @Test
    void testGetWallet() {
        walletController.getWallet(walletId);
        verify(walletService, times(1)).getWallet(walletId);
    }
}