package epam.com.practice.trainerservice.repo;

import epam.com.practice.trainerservice.model.TrainerWorkload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerWorkloadRepository extends JpaRepository<TrainerWorkload, Long> {
    @Query(value = "select * from TRAINER_WORKLOAD", nativeQuery = true)
    List<TrainerWorkload> getTrainerMonthlySummary();

}
