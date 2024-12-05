package com.nttdata.Yanki.Model.api.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
/**
 * DTO para representar el monedero móvil Yanki.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YankiDto {

    @NotEmpty(message = "El ID no puede estar vacío")
    @Schema(description = "ID único del monedero móvil", example = "640b8f1d3fa72a5c2b4d4321")
    private String id;  // ID único del monedero móvil

    @NotEmpty(message = "El número de teléfono no puede estar vacío")
    @Pattern(regexp = "^\\d{10}$", message = "El número de teléfono debe tener 10 dígitos")
    @Schema(description = "Número de teléfono asociado al monedero", example = "1234567890")
    private String phoneNumber;  // Número de teléfono asociado al monedero

    @NotEmpty(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El correo electrónico debe ser válido")
    @Schema(description = "Correo electrónico del propietario del monedero", example = "user@example.com")
    private String email;  // Correo electrónico del propietario del monedero

    @NotEmpty(message = "El IMEI no puede estar vacío")
    @Pattern(regexp = "^\\d{15}$", message = "El IMEI debe tener 15 dígitos")
    @Schema(description = "IMEI del dispositivo móvil", example = "123456789012345")
    private String imei;  // IMEI del dispositivo móvil

    @NotNull(message = "El saldo debe ser proporcionado")
    @PositiveOrZero(message = "El saldo debe ser mayor o igual a cero")
    @Schema(description = "Saldo actual del monedero móvil", example = "100.50")
    private Double balance;  // Saldo actual del monedero móvil

    @Schema(description = "Estado del monedero", example = "ACTIVO", allowableValues = {"ACTIVO", "INACTIVO"})
    private String status;  // Estado del monedero (por ejemplo: ACTIVO, INACTIVO)

    @Schema(description = "Número de tarjeta de débito asociada (si aplica)", example = "1234-5678-9876-5432")
    private String associatedBankCard;  // Número de tarjeta de débito asociada (si aplica)
}
