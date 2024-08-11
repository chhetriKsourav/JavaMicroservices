package com.demoproject.ProductService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String ProductName;
    private long productId;
    private long price;
    private long quantity;
}
