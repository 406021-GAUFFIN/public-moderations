package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ModerationState;
import ar.edu.utn.frc.tup.lc.iv.models.Infraction;
import ar.edu.utn.frc.tup.lc.iv.models.SanctionType;
import lombok.Getter;

import java.util.List;
@Getter

public class FineDTO {
    private Long id;
    private String description;
    private Double price;
    private ModerationState moderationState;
    private SanctionType sanctionType;
    private List<InfractionDTO> infractions;





    }
