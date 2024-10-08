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
/**
 * Service to run a scheduled job for imputing fines as expenses.
 * Runs daily at midnight using a cron expression.
 */
@Service
@AllArgsConstructor
public class FineExpenseCron {

    /**
     * Client for interacting with the expenses system.
     */
    private final ExpensesClient expensesClient;

    /**
     * Repository for accessing fine entities.
     */
    private final FineJpaRepository fineJpaRepository;

    /**
     * Logger for logging errors and information.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FineExpenseCron.class);


    /**
     * Processes approved fines and sends them to the expense system
     * if they can no longer be challenged. Updates fines to "IMPUTED_ON_EXPENSE".
     * Runs daily at midnight.
     */

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

                LOGGER.error("Error processing fine with id {}: {}", fineEntity.getId(), e.getMessage());
            }
        });


        LOGGER.info("Finished imputing fines for today");
    }

}
