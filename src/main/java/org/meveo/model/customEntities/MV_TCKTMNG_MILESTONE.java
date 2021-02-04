package org.meveo.model.customEntities;

import org.meveo.model.CustomEntity;
import org.meveo.model.persistence.DBStorageType;
import java.time.Instant;
import org.meveo.model.customEntities.MV_TCKTMNG_PROJECT;

public class MV_TCKTMNG_MILESTONE implements CustomEntity {

    private String uuid;

    private DBStorageType storages;

    private Instant endDate;

    private Instant dueDate;

    private String description;

    private MV_TCKTMNG_PROJECT project;

    private String title;

    private Instant startDate;

    private String remoteId;

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

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
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

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }

    @Override()
    public String getCetCode() {
        return "MV_TCKTMNG_MILESTONE";
    }
}
