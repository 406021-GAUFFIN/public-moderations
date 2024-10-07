package ar.edu.utn.frc.tup.lc.iv.services.scheduled;

import ar.edu.utn.frc.tup.lc.iv.clients.ExpensesClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseDTO;
import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine.FineJpaRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class FineExpenseCron {

    private final ExpensesClient expensesClient;
    private final FineJpaRepository fineJpaRepository;


    private static final Logger log = LoggerFactory.getLogger(FineExpenseCron.class);


    @Scheduled(cron = "0 0 0 * * ?")
    public void imputeFines() {

        List<FineEntity> fineEntities = fineJpaRepository.findAllByFineState(FineState.APPROVED);

        fineEntities.forEach(fineEntity -> {
            try {

                if (LocalDateTime.now().isBefore(fineEntity.getLastUpdatedAt()
                        .plusDays(fineEntity.getSanctionType().getValidityPeriod()))) {
                    throw new IllegalArgumentException("Fine with id " + fineEntity.getId() + " can still be "
                            + "challenged by the owner of the plot");
                }


                FineExpenseDTO fineExpenseDTO = new FineExpenseDTO();
                fineExpenseDTO.setAmount(fineEntity.getSanctionType().getPrice());
                fineExpenseDTO.setFineId(fineEntity.getId());
                fineExpenseDTO.setPeriod(LocalDateTime.now());
                fineExpenseDTO.setType("extraordinary");


                expensesClient.sendToExpenses(fineExpenseDTO);


                fineEntity.setFineState(FineState.IMPUTED_ON_EXPENSE);
                fineJpaRepository.save(fineEntity);

            } catch (Exception e) {

                log.error("Error processing fine with id {}: {}", fineEntity.getId(), e.getMessage());
            }
        });


        log.info("Finished imputing fines for today");
    }

}
