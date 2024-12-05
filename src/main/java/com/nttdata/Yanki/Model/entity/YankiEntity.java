package com.nttdata.Yanki.Model.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "yanki_wallets")
public class YankiEntity implements Serializable {

    @Id
    private String id;  // ID único del monedero móvil

    private String phoneNumber;  // Número de teléfono asociado al monedero

    private String email;  // Correo electrónico del propietario

    private String imei;  // IMEI del dispositivo móvil

    private Double balance;  // Saldo actual del monedero

    private String status;  // Estado del monedero (ACTIVO, INACTIVO)

    private String associatedBankCard;  // Número de tarjeta de débito asociada (si aplica)
}
