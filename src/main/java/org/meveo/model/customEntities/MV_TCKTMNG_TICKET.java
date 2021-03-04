package org.meveo.model.customEntities;

import org.meveo.model.CustomEntity;
import org.meveo.model.persistence.DBStorageType;
import java.util.List;
import org.meveo.model.customEntities.MV_TCKTMNG_COMMENT;
import org.meveo.model.customEntities.MV_TCKTMNG_PROJECT;
import java.time.Instant;
import org.meveo.model.customEntities.MV_TCKTMNG_MILESTONE;

public class MV_TCKTMNG_TICKET implements CustomEntity {

    private String uuid;

    private DBStorageType storages;

    private String creator;

    private List<MV_TCKTMNG_COMMENT> comments;

    private List<String> assignees;

    private String description;

    private MV_TCKTMNG_PROJECT project;

    private String title;

    private String remoteId;

    private List<String> tags;

    private Instant createdAt;

    private Long number;

    private MV_TCKTMNG_MILESTONE milestone;

    private Instant closedAt;

    private Instant updatedAt;

    @Override()
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public DBStorageType getStorages() {
        return storages;
    }

    public void setStorages(DBStorageType storages) {
        this.storages = storages;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<MV_TCKTMNG_COMMENT> getComments() {
        return comments;
    }

    public void setComments(List<MV_TCKTMNG_COMMENT> comments) {
        this.comments = comments;
    }

    public List<String> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<String> assignees) {
        this.assignees = assignees;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MV_TCKTMNG_PROJECT getProject() {
        return project;
    }

    public void setProject(MV_TCKTMNG_PROJECT project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public MV_TCKTMNG_MILESTONE getMilestone() {
        return milestone;
    }

    public void setMilestone(MV_TCKTMNG_MILESTONE milestone) {
        this.milestone = milestone;
    }

    public Instant getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Instant closedAt) {
        this.closedAt = closedAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override()
    public String getCetCode() {
        return "MV_TCKTMNG_TICKET";
    }
}
