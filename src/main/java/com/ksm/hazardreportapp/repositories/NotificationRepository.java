package com.ksm.hazardreportapp.repositories;

import com.ksm.hazardreportapp.entities.Notifications;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, Integer> {

    List<Notifications> findByUserIdOrderByIdDesc(String userId);

    List<Notifications> findByUserIdAndReadStatusOrderByIdDesc(String userId, int readStatus);
}
