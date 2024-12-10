package com.heroiclabs.mezon.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IMessageActionRow {
    private List<Component> components;

    // Interface Component để đại diện cho các loại thành phần khác nhau
    public interface Component {}
}
