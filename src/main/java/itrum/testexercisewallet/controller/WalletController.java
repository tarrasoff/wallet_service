package itrum.testexercisewallet.controller;

import itrum.testexercisewallet.dto.WalletDto;
import itrum.testexercisewallet.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/wallet")
@RequiredArgsConstructor
@Slf4j
@Validated
public class WalletController {
    private final WalletService walletService;

    @PostMapping
    public WalletDto createWallet(@Valid @RequestBody WalletDto walletDto) {
        log.info("Create walletDto: {}", walletDto);
        return walletService.createOrUpdateWallet(walletDto);
    }

    @GetMapping("{walletId}")
    public WalletDto getWallet(@PathVariable UUID walletId) {
        log.info("Get wallet by id: {}", walletId);
        return walletService.getWallet(walletId);
    }
}