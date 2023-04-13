package com.group.booking.Models.Addons;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RevenueOn12MonthAgo {
    
    @Id
    private int ind;
    private String month;
    private int total;
}
