
package com.taskApp.taskApp.repository;

import com.taskApp.taskApp.model.Task;
import com.taskApp.taskApp.model.AppUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
    List<Task> findByUser(AppUser user);
    List<Task> findByUserAndTitleContaining(AppUser user, String title);
}
