package com.sparta.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
public class Image extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private int size;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] content;

    public Image(String filename, String contentType, int size, byte[] content) {
        this.filename = filename;
        this.contentType = contentType;
        this.size = size;
        this.content = content;
    }

    public void update(String filename, String contentType, int size, byte[] content){
        this.filename = filename;
        this.contentType = contentType;
        this.size = size;
        this.content = content;
    }

}
