package com.example.p2p_loan_platform.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseDto {
    private List<StatisticSearch> StatisticSearch;

    @Getter
    @Setter
    public static class StatisticSearch {
        private String DATA_VALUE;

        public double getDataValue() {
            return Double.parseDouble(DATA_VALUE);
        }
    }
}