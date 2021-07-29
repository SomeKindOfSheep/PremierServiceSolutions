package com.pss.premierservicesolutions.controllers.clientMaintenance;

import com.pss.premierservicesolutions.entity.Call;
import com.pss.premierservicesolutions.entity.ClientType;
import com.pss.premierservicesolutions.entity.Contract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceDTO {

    private long id;

    private String fullName;

    private String address;

    private String eMail;

    private String telephone;

    private ClientType clientType;

    private List<Long> callIds;

    private List<Contract> contract;


}
