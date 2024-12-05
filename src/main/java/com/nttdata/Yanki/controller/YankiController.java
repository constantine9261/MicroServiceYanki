package com.nttdata.Yanki.controller;


import com.nttdata.Yanki.Model.api.account.UpdateBalanceDto;
import com.nttdata.Yanki.Model.api.account.YankiDto;
import com.nttdata.Yanki.Model.api.shared.ResponseDto;
import com.nttdata.Yanki.business.service.IYankiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/yanki")
@RequiredArgsConstructor
public class YankiController {

    private final IYankiService yankiService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<YankiDto> createYanki(@Valid @RequestBody YankiDto yankiDto) {
        return yankiService.createYanki(yankiDto);
    }

    @GetMapping("/{phoneNumber}")
    public Mono<YankiDto> getYankiByPhoneNumber(@PathVariable String phoneNumber) {
        return yankiService.getYankiByPhoneNumber(phoneNumber);
    }

    @PutMapping("/{phoneNumber}/balance")
    @Operation(summary = "Actualiza el balance del monedero móvil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Balance actualizado con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Monedero no encontrado")
    })
    public Mono<YankiDto> updateBalance(@PathVariable String phoneNumber, @RequestBody UpdateBalanceDto updateBalanceDto) {
        return yankiService.updateBalance(phoneNumber, updateBalanceDto.getNewBalance());
    }
}
