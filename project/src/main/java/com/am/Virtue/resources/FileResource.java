package com.am.Virtue.resources;

public class FileResource {

    private String id;

    private String type;

    private String name;

    public FileResource() {

    }

    public FileResource(Long id, String type, String name) {
        super();
        id =Long.parseLong(String.valueOf(this.id));
        this.type = type;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


