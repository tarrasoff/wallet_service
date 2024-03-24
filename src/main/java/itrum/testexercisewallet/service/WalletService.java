package itrum.testexercisewallet.service;

import itrum.testexercisewallet.dto.WalletDto;
import itrum.testexercisewallet.entity.OperationType;
import itrum.testexercisewallet.entity.Wallet;
import itrum.testexercisewallet.exception.EntityNotFoundException;
import itrum.testexercisewallet.mapper.WalletMapper;
import itrum.testexercisewallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    public WalletDto create() {
        Wallet wallet = Wallet.builder()
                .operationType(OperationType.DEPOSIT)
                .amount(0.0)
                .build();
        walletRepository.save(wallet);
        return walletMapper.toDto(wallet);

    }
    public WalletDto update(UUID walletId, WalletDto walletDto) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found"));
        wallet.setOperationType(walletDto.getOperationType());
        wallet.setAmount(walletDto.getAmount());
        return walletDto;
    }

    public WalletDto getWallet(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found"));
        return walletMapper.toDto(wallet);
    }
}