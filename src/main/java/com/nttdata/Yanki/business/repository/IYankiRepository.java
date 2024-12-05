package com.nttdata.Yanki.business.repository;



import com.nttdata.Yanki.Model.entity.YankiEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IYankiRepository extends
        ReactiveMongoRepository<YankiEntity, String> {
    Mono<YankiEntity> findByPhoneNumber(String phoneNumber);  // Buscar por número de teléfono

}
