package com.heroiclabs.mezon.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ButtonComponent implements IMessageActionRow.Component {
    private String label;
    private String action;
    private String style;
}
