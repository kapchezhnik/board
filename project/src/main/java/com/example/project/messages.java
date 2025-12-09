package com.example.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class messages{
    @JsonProperty(required = false)
    private Integer id;
    private String title;
    @JsonProperty(required = false)
    private Integer price;
    private String message;
    private String author;
    @JsonProperty(required = false)
    private LocalDateTime createdAt;
    @JsonProperty(required = false)
    private LocalDateTime updatedAt;
}