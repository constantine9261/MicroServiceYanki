package com.nttdata.Yanki.business.service.impl;

import com.nttdata.Yanki.Model.api.account.YankiDto;
import com.nttdata.Yanki.Model.entity.YankiEntity;
import com.nttdata.Yanki.business.repository.IYankiRepository;
import com.nttdata.Yanki.business.service.IYankiService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class YankiServiceImpl implements IYankiService {

    private final IYankiRepository yankiRepository;

    @Override
    public Mono<YankiDto> createYanki(YankiDto yankiDto) {
        YankiEntity yankiEntity = YankiEntity.builder()
                .phoneNumber(yankiDto.getPhoneNumber())
                .email(yankiDto.getEmail())
                .imei(yankiDto.getImei())
                .balance(yankiDto.getBalance())
                .status(yankiDto.getStatus())
                .associatedBankCard(yankiDto.getAssociatedBankCard())
                .build();

        return yankiRepository.save(yankiEntity)
                .map(this::convertToDto);
    }

    @Override
    public Mono<YankiDto> getYankiByPhoneNumber(String phoneNumber) {
        return yankiRepository.findByPhoneNumber(phoneNumber)
                .map(this::convertToDto);  // Convertir la entidad de vuelta al DTO
    }

    @Override
    public Mono<YankiDto> updateBalance(String phoneNumber, Double newBalance) {
        return yankiRepository.findByPhoneNumber(phoneNumber)
                .flatMap(yanki -> {
                    yanki.setBalance(newBalance); // Actualizamos el balance
                    return yankiRepository.save(yanki)
                            .map(updatedYanki -> convertToDto(updatedYanki)); // Convertimos a DTO para la respuesta
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Monedero no encontrado")));
    }

    private YankiDto convertToDto(YankiEntity yankiEntity) {
        return YankiDto.builder()
                .id(yankiEntity.getId())
                .phoneNumber(yankiEntity.getPhoneNumber())
                .email(yankiEntity.getEmail())
                .imei(yankiEntity.getImei())
                .balance(yankiEntity.getBalance())
                .status(yankiEntity.getStatus())
                .associatedBankCard(yankiEntity.getAssociatedBankCard())
                .build();
    }
}
