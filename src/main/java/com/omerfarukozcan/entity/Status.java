package com.omerfarukozcan.entity;

public enum Status {
    ACTIVE("Hazırlanıyor"),
    DELETED("İptal Edildi"),
    COMPLETED("Tamamlandı");

    private final String desc;

    Status(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

}
