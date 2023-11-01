package se.lexicon.g46todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.g46todoapi.domain.entity.Task;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t where t.title = :title")
    List<Task> findTasksByTitle(@Param("title") String title);

    @Query("select t from Task t where t.person = :person_id")
    List<Task> findTasksByPersonId(@Param("person_id") Long id);

    @Query("select t from Task t where t.done = :status")
    List<Task> findTasksByStatus(@Param("status") boolean done);

    // todo: fix startdate
    @Query("select t from Task t where t.startDate >= :startdate and t.deadline <= :enddate")
    List<Task> findTasksByDateBetweenStartAndEnd(@Param("startdate") LocalDate startDate, @Param("enddate") LocalDate endDate);

    @Query("select t from Task t where t.deadline = :deadline")
    List<Task> findTasksByDeadline(@Param("deadline") LocalDate deadline);

    @Query("select t from Task t where t.person = null")
    List<Task> findTasksByUnassigned();

    @Query("select t from Task t where t.done = false")
    List<Task> findTasksByNotDone();

}
