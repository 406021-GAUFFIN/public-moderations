package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.models.SanctionType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class FineDTO extends BaseDTO {
    private String description;
    private Double price;
    private FineState fineState;
    private SanctionType sanctionType;
    private List<ReducedInfractionDTO> infractions;




    }

