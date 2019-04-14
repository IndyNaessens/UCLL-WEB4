package org.ucll.web4.dto;

import javax.validation.constraints.NotBlank;

public class ChangeStatusDto {

    @NotBlank
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
