package com.heroiclabs.mezon.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IEmbedProps {
    private String color;
    private String title;
    private String url;
    private Author author;
    private String description;
    private Thumbnail thumbnail;
    private List<Field> fields;
    private Image image;
    private String timestamp;
    private Footer footer;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Author {
        private String name;
        private String iconUrl;
        private String url;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Thumbnail {
        private String url;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Field {
        private String name;
        private String value;
        private Boolean inline;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {
        private String url;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Footer {
        private String text;
        private String iconUrl;
    }
}
