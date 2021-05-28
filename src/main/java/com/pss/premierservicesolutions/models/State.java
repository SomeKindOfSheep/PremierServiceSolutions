package com.pss.premierservicesolutions.models;

public enum State {

    CALL_OPEN,
    CALL_QUERY,
    CALL_CLOSED,

    COMPLAINT_OPEN,
    COMPLAINT_NEW_INFORMATION,
    COMPLAINT_UNDER_INVESTIGATION,
    COMPLAINT_MANAGEMENT_INTERVENTION,
    COMPLAINT_RESOLVED,

    SERVICE_OPENED,
    SERVICE_IN_PROGRESS,
    SERVICE_NEEDS_MANAGER_ATTENTION,

}
