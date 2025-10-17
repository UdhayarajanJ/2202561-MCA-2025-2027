package com.fptp.api.services;

import com.fptp.api.models.Summary;
import com.fptp.api.repository.DashboardRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired
    private DashboardRespository dashboardRespository;

    public Summary GetDashboardSummary(String ownerId){
        return dashboardRespository.GetDashboardSummary(ownerId);
    }
}
