package com.heroiclabs.mezon.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputComponent implements IMessageActionRow.Component {
    private String placeholder;
    private String value;
    private String type;
}
