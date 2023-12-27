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
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String penName;

    @JsonIgnore
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> books;

    @Override
    public String toString(){
        return "Author{"
                +"firstName='" + this.firstName +"', "
                +"lastName='" + this.lastName +"', "
                +"penName='" +this.penName
                +"' }";
    }
}











