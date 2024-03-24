package itrum.testexercisewallet.controller;

import itrum.testexercisewallet.dto.WalletDto;
import itrum.testexercisewallet.entity.OperationType;
import itrum.testexercisewallet.service.WalletService;
import jakarta.validation.constraints.NotNull;
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
    public WalletDto create() {
        log.info("Create new wallet");
        return walletService.create();
    }

    @PutMapping("{walletId}")
    public WalletDto update(@PathVariable
                            @NotNull UUID walletId,
                            @RequestBody WalletDto walletDto) {
        log.info("Update walletDto: {}", walletDto);
        return walletService.update(walletId, walletDto);
    }

    @GetMapping("{walletId}")
    public WalletDto get(@PathVariable UUID walletId) {
        log.info("Get wallet by id: {}", walletId);
        return walletService.getWallet(walletId);
    }
}