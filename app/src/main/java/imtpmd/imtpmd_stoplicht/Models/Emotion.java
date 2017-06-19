package imtpmd.imtpmd_stoplicht.Models;

import java.io.Serializable;
import java.util.Date;

public class Emotion implements Serializable {

    private int    id;
    private String name;
    private String slug;
    private Date   created_at;
    private Date   updated_at;

    public Emotion(int id, String name, String slug, Date created_at, Date updated_at) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Emotion(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
