package com.pss.premierservicesolutions.controllers.clientSatisfaction;

import com.pss.premierservicesolutions.entity.FollowUp;
import com.pss.premierservicesolutions.entity.State;

import java.util.List;

public class SatisfactionDTO {

    // this is kind of a placeholder
    // I like the fact that we display everything for complaints
    // but it can get a bit much if there are like 20 calls

    private long id;
    private String description;
    private State state;
    private long clientId;
    private List<Long> callIdsLinkedToComplaint;
    private List<FollowUp> followUp;

}
