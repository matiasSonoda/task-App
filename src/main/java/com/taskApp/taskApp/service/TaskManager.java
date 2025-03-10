
package com.taskApp.taskApp.service;

import com.taskApp.taskApp.model.Task;
import com.taskApp.taskApp.model.AppUser;

public interface TaskManager {
    void placerOrder(AppUser user,Task task);
    
}

