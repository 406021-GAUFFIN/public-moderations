package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.InfractionState;
import ar.edu.utn.frc.tup.lc.iv.models.Fine;
import ar.edu.utn.frc.tup.lc.iv.models.SanctionType;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ReducedInfractionDTO extends BaseDTO {
    private Integer userId;
    private String description;
    private InfractionState infractionState;
    private SanctionType sanctionType;


    
    }
