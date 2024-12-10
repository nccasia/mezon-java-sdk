package com.heroiclabs.mezon.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ApiClanDesc {

    private String banner;
    private String clanId;
    private String clanName;
    private String creatorId;
    private String logo;
    private Integer status;
    private Integer badgeCount;
    private Boolean isOnboarding;
}