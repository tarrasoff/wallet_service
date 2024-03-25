package itrum.testexercisewallet.service;

import itrum.testexercisewallet.dto.WalletDto;
import itrum.testexercisewallet.entity.Wallet;
import itrum.testexercisewallet.exception.EntityNotFoundException;
import itrum.testexercisewallet.mapper.WalletMapper;
import itrum.testexercisewallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Retryable(retryFor = {OptimisticLockingFailureException.class})
    public WalletDto createWallet(WalletDto walletDto) {
        Optional<Wallet> walletOptional = walletRepository.findById(walletDto.getId());
        if (walletOptional.isPresent()) {
            log.info("Find wallet by id: {}", walletDto.getId());
            Wallet wallet = walletOptional.get();
            wallet.setOperationType(walletDto.getOperationType());
            wallet.setAmount(wallet.getAmount() + walletDto.getAmount());
            log.info("Update wallet: {}", wallet);
        } else {
            log.info("Wallet not found");
            Wallet newWallet = Wallet.builder()
                    .operationType(walletDto.getOperationType())
                    .amount(walletDto.getAmount())
                    .build();
            log.info("Create and save new wallet: {}", newWallet);
            return walletMapper.toDto(walletRepository.save(newWallet));
        }
        return walletDto;
    }

    @Transactional(readOnly = true)
    public WalletDto getWallet(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found"));
        return walletMapper.toDto(wallet);
    }
}