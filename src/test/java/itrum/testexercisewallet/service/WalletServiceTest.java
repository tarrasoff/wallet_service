package itrum.testexercisewallet.service;

import itrum.testexercisewallet.dto.WalletDto;
import itrum.testexercisewallet.entity.OperationType;
import itrum.testexercisewallet.entity.Wallet;
import itrum.testexercisewallet.exception.EntityNotFoundException;
import itrum.testexercisewallet.mapper.WalletMapper;
import itrum.testexercisewallet.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class WalletServiceTest {
    @InjectMocks
    private WalletService walletService;
    @Mock
    private WalletRepository walletRepository;
    @Spy
    private WalletMapper walletMapper;
    private WalletDto inputDto;

    private UUID walletId;

    @BeforeEach
    void setUp() {
        inputDto = new WalletDto();
        inputDto.setId(UUID.randomUUID());
        inputDto.setOperationType(OperationType.DEPOSIT);
        inputDto.setAmount(100.0);

        walletId = UUID.randomUUID();
    }


    @Test
    void testCreateWalletWhenWalletExists() {
        Wallet existingWallet = new Wallet();
        existingWallet.setId(inputDto.getId());
        existingWallet.setOperationType(OperationType.WITHDRAW);
        existingWallet.setAmount(50.0);

        when(walletRepository.findById(inputDto.getId())).thenReturn(Optional.of(existingWallet));

        WalletDto resultDto = walletService.createWallet(inputDto);

        verify(walletRepository, times(1)).findById(inputDto.getId());
        verify(walletRepository, times(0)).save(any(Wallet.class));
        assertEquals(existingWallet.getOperationType(), inputDto.getOperationType());
        assertEquals(150, existingWallet.getAmount());
        assertEquals(existingWallet.getId(), resultDto.getId());
    }

    @Test
    void testCreateWalletWhenWalletDoesNotExist() {
        Wallet newWallet = new Wallet();
        newWallet.setId(inputDto.getId());
        newWallet.setOperationType(inputDto.getOperationType());
        newWallet.setAmount(inputDto.getAmount());

        Wallet savedWallet = new Wallet();
        savedWallet.setId(inputDto.getId());
        savedWallet.setOperationType(inputDto.getOperationType());
        savedWallet.setAmount(inputDto.getAmount());

        when(walletRepository.findById(inputDto.getId())).thenReturn(Optional.empty());
        when(walletMapper.toDto(savedWallet)).thenReturn(inputDto);
        when(walletRepository.save(any(Wallet.class))).thenReturn(savedWallet);

        WalletDto resultDto = walletService.createWallet(inputDto);

        verify(walletRepository, times(1)).findById(inputDto.getId());
        verify(walletRepository, times(1)).save(any(Wallet.class));
        verify(walletMapper, times(1)).toDto(savedWallet);
        assertEquals(inputDto.getId(), resultDto.getId());
        assertEquals(inputDto.getOperationType(), resultDto.getOperationType());
        assertEquals(inputDto.getAmount(), resultDto.getAmount());
    }

    @Test
    void testGetWalletWhenWalletExists() {
        Wallet wallet = new Wallet();
        wallet.setId(walletId);

        WalletDto expectedDto = new WalletDto();
        expectedDto.setId(walletId);

        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));
        when(walletMapper.toDto(wallet)).thenReturn(expectedDto);

        WalletDto resultDto = walletService.getWallet(walletId);

        assertEquals(expectedDto.getId(), resultDto.getId());
        verify(walletRepository, times(1)).findById(walletId);
        verify(walletMapper, times(1)).toDto(wallet);
    }

    @Test
    public void testGetWalletWhenWalletDoesNotExist() {
        when(walletRepository.findById(walletId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> walletService.getWallet(walletId), "Wallet not found");
        verify(walletRepository, times(1)).findById(walletId);
        verify(walletMapper, never()).toDto(any());
    }
}