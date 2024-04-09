package com.example.demo.entity.ViewModels;

import com.example.demo.entity.ChiTietHoaDon;
import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DatHang {
    private ArrayList<ChiTietHoaDon> chiTietHoaDons;
}

