package org.films;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {
    private String id;
    private String title;
    private Integer year;
    private String director;
    private String genre;
}
