package com.itheima.health.model.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetmealReportVO {
    private List<String> setmealNames;
    private List<HashMap<String, Object>> setmealCount;
}
