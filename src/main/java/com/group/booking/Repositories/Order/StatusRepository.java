package com.group.booking.Repositories.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Order.StatusModel;

@Repository
public interface StatusRepository extends JpaRepository<StatusModel, String> {
    
}
