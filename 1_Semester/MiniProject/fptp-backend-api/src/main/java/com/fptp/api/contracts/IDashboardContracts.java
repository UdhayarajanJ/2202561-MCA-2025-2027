package com.fptp.api.contracts;

import com.fptp.api.models.Summary;

public interface IDashboardContracts {
    Summary GetDashboardSummary(String ownerId);
}
