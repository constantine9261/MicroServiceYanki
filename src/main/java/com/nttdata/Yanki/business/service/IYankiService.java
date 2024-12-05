package com.nttdata.Yanki.business.service;



import com.nttdata.Yanki.Model.api.account.YankiDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IYankiService {


    Mono<YankiDto> createYanki(YankiDto yankiDto);


    Mono<YankiDto> getYankiByPhoneNumber(String phoneNumber);


    Mono<YankiDto> updateBalance(String phoneNumber, Double balance);

}