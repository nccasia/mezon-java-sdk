package com.heroiclabs.mezon.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HashtagOnMessage extends Hashtag {
    private Integer s;
    private Integer e;
}
