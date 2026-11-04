
package com.taskmanagerapi.repository;

import java.util.List;
import java.util.Optional;

import com.taskmanagerapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Find all tasks belonging to a specific user.
    List<Task> findByUser_Id(Long userId);

    // Find a specific task belonging to a specific user.
    Optional<Task> findByIdAndUser_Id(Long taskId, Long userId);
}
