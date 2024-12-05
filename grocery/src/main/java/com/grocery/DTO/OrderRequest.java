package com.grocery.DTO;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private List<Long> groceryItemIds;
    private List<Integer> quantities;
}
