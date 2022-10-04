package com.am.Virtue.entities;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name ="FILES")
public class FileDb {
    @GenericGenerator(
            name = "File_Seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "File_seq", value = "FILE_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            })
    @Id
    @GeneratedValue(generator = "File_Seq")
    @Column(name = "FILE_ID")
    private Long id;
    @Column(name = "File_Name_Code")

    private String name;
    @Column(name = "File_Type")
    private String type;

    @Lob
    private byte[] data;

    public FileDb() {

    }

    public FileDb(Long id, String name, String type, byte[] data) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }




}
