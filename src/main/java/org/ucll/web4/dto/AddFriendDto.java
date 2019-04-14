package org.ucll.web4.dto;

import javax.validation.constraints.NotBlank;

public class AddFriendDto {

    @NotBlank
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
