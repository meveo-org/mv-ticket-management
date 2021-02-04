package org.meveo.model.customEntities;

import org.meveo.model.CustomEntity;
import org.meveo.model.persistence.DBStorageType;
import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class CREDENTIAL implements CustomEntity {

    private String uuid;

    private DBStorageType storages;

    private String DOMAIN;

    private Instant LAST_CONNECTION;

    private Instant TOKEN_EXPIRY;

    private String AUTHENTICATION_TYPE;

    private String PRIVATE_KEY;

    private String PUBLIC_KEY;

    private String HEADER_VALUE;

    private String REFRESH_TOKEN;

    private String HEADER_KEY;

    private String STATUS;

    private String PASSWORD;

    @JsonProperty(required = true)
    @NotNull
    private String USERNAME;

    private String TOKEN;

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

    public String getDOMAIN() {
        return DOMAIN;
    }

    public void setDOMAIN(String DOMAIN) {
        this.DOMAIN = DOMAIN;
    }

    public Instant getLAST_CONNECTION() {
        return LAST_CONNECTION;
    }

    public void setLAST_CONNECTION(Instant LAST_CONNECTION) {
        this.LAST_CONNECTION = LAST_CONNECTION;
    }

    public Instant getTOKEN_EXPIRY() {
        return TOKEN_EXPIRY;
    }

    public void setTOKEN_EXPIRY(Instant TOKEN_EXPIRY) {
        this.TOKEN_EXPIRY = TOKEN_EXPIRY;
    }

    public String getAUTHENTICATION_TYPE() {
        return AUTHENTICATION_TYPE;
    }

    public void setAUTHENTICATION_TYPE(String AUTHENTICATION_TYPE) {
        this.AUTHENTICATION_TYPE = AUTHENTICATION_TYPE;
    }

    public String getPRIVATE_KEY() {
        return PRIVATE_KEY;
    }

    public void setPRIVATE_KEY(String PRIVATE_KEY) {
        this.PRIVATE_KEY = PRIVATE_KEY;
    }

    public String getPUBLIC_KEY() {
        return PUBLIC_KEY;
    }

    public void setPUBLIC_KEY(String PUBLIC_KEY) {
        this.PUBLIC_KEY = PUBLIC_KEY;
    }

    public String getHEADER_VALUE() {
        return HEADER_VALUE;
    }

    public void setHEADER_VALUE(String HEADER_VALUE) {
        this.HEADER_VALUE = HEADER_VALUE;
    }

    public String getREFRESH_TOKEN() {
        return REFRESH_TOKEN;
    }

    public void setREFRESH_TOKEN(String REFRESH_TOKEN) {
        this.REFRESH_TOKEN = REFRESH_TOKEN;
    }

    public String getHEADER_KEY() {
        return HEADER_KEY;
    }

    public void setHEADER_KEY(String HEADER_KEY) {
        this.HEADER_KEY = HEADER_KEY;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
    }

    @Override()
    public String getCetCode() {
        return "CREDENTIAL";
    }
}
