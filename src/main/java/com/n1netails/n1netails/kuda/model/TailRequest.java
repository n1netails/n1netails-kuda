package com.n1netails.n1netails.kuda.model;

import lombok.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TailRequest {

    private String title;
    private String description;
    private String details;
    @Builder.Default
    private Instant timestamp = Instant.now();
    private String level;
    private String type;
    @Builder.Default
    private Map<String, String> metadata = new HashMap<>();
}
