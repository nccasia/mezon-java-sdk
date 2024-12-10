package com.heroiclabs.mezon.model;

import lombok.Data;

@Data
public class ClanJoin {
    private ClanJoinDetails clanJoin;

    @Data
    public static class ClanJoinDetails {
        private String clanId;
    }
}
