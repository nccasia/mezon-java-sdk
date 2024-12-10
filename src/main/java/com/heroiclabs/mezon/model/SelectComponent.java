package com.heroiclabs.mezon.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectComponent implements IMessageActionRow.Component {
    private String placeholder;
    private List<String> options;
}
