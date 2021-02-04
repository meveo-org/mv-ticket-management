package org.meveo.model.customEntities;

import org.meveo.model.CustomEntity;
import org.meveo.model.persistence.DBStorageType;
import java.util.Map;

public class MV_TCKTMNG_PROJECT implements CustomEntity {

    private String uuid;

    private DBStorageType storages;

    private String name;

    private String description;

    private Map<String, String> remoteSpaces;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getRemoteSpaces() {
        return remoteSpaces;
    }

    public void setRemoteSpaces(Map<String, String> remoteSpaces) {
        this.remoteSpaces = remoteSpaces;
    }

    @Override()
    public String getCetCode() {
        return "MV_TCKTMNG_PROJECT";
    }
}
