package com.n1netails.n1netails.kuda.model;

import lombok.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Tail Request sent to the N1netails Api
 * @author shahid foy
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
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

    /**
     * Tail Request Constructor
     */
    private TailRequest() {}
}
