package com.group.booking.Models.Addons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse {
    
    private Object data;
    private int totalElement;
    private int totalPage;
    private int currentPage;
    private int size;
}
