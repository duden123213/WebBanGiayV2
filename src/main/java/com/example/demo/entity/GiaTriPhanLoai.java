package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GiaTriPhanLoai {

    private UUID value;
    private String label;
}
