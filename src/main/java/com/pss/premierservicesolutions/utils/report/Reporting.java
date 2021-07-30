package com.pss.premierservicesolutions.utils.report;

import com.pss.premierservicesolutions.entity.Contract;
import com.pss.premierservicesolutions.entity.ContractType;
import com.pss.premierservicesolutions.repositories.ContractRepository;
import com.pss.premierservicesolutions.repositories.ContractTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class Reporting {

//    This department must be able to examine the
//    performance of different offerings from data captured by the system, to support the process of
//    identifying opportunities for new products or measuring the profitability of existing products.

    @Autowired
    ContractTypeRepository contractTypeRepository;

    @Autowired
    ContractRepository contractRepository;


    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON)
    private Map<String, Long> stuff(){

        Map<String, Long> mapPerName = new HashMap<>();

        List<Contract> listofContracts = contractRepository.findAllActiveContracts();
        for (Contract contract: listofContracts){
            ContractType contractType = contract.getContractType();

            if (!mapPerName.containsKey(contractType.getName())){
                mapPerName.put(contractType.getName(), 1L);
            }else{
                mapPerName.put(contractType.getName(), mapPerName.get(contractType.getName()) + 1);
            }
        }

        List<ContractType> contractTypeIdList = contractTypeRepository.findAll();
        for (ContractType type: contractTypeIdList){
            if (!mapPerName.containsKey(type.getName())){
                mapPerName.put(type.getName(), 0L);
            }
        }

       return mapPerName;

    }
    
}
