package com.omerfarukozcan.model;

import lombok.Data;

@Data
public class UrunSaveResponse {

    private boolean success;
    private UrunItem urun;

    public UrunSaveResponse() {
    }

    public UrunSaveResponse(boolean success) {
        this.success = success;
    }

}
