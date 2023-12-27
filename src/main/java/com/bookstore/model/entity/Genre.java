package com.bookstore.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    @Column(nullable = false)
    private String genreTitle;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
    private List<Book> books;

    @Override
    public String toString(){
        return "Genre{"
                +"title='" + this.genreTitle
                +"' }";
    }

}
