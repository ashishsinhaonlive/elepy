package com.elepy.admin.models;

import java.util.Objects;

public class Attachment implements Comparable<Attachment> {
    private final String fileName;
    private final String contentType;
    private final byte[] src;

    private final AttachmentType type;

    private final boolean fromDirectory;

    private final String directory;


    public Attachment(String fileName, String contentType, byte[] src, AttachmentType type, boolean fromDirectory, String directory) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.src = src;
        this.type = type;
        this.fromDirectory = fromDirectory;
        this.directory = directory;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getSrc() {
        return src;
    }

    public AttachmentType getType() {
        return type;
    }

    public boolean isFromDirectory() {
        return fromDirectory;
    }

    public String getDirectory() {
        return directory;
    }

    @Override
    public int compareTo(Attachment o) {
        return this.getFileName().compareTo(o.getFileName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return fileName.equals(that.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName);
    }
}
