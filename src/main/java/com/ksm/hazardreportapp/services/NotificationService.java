package com.ksm.hazardreportapp.services;

import com.ksm.hazardreportapp.entities.Notifications;
import com.ksm.hazardreportapp.repositories.NotificationRepository;
import com.ksm.hazardreportapp.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    public List<Notifications> getByUser(String userId) {
        return userRepository.findById(userId).get().getNotificationsList();
    }

    public List<Notifications> getByUserUnSeen(String userId, int limit) {
        if (limit == 0) {
            return notificationRepository.findByUserIdAndReadStatusOrderByIdDesc(userId, 0);
        } else {
            if (notificationRepository.findByUserIdAndReadStatusOrderByIdDesc(userId, 0).size() < 5) {
                return notificationRepository.findByUserIdAndReadStatusOrderByIdDesc(userId, 0);
            } else {
                return notificationRepository.findByUserIdAndReadStatusOrderByIdDesc(userId, 0).subList(0, limit);
            }
        }
    }

    public List<Notifications> getByUserId(String userId) {
        return notificationRepository.findByUserIdOrderByIdDesc(userId);
    }

    public boolean readNotification(int id) {
        Notifications notifications = new Notifications();
        notifications = notificationRepository.findById(id).get();
        notifications.setReadStatus(1);
        try {
            notificationRepository.save(notifications);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
