package com.obgames.obgamesapi.dto.request;

public class ResetPassword {
    private String idUsuario;
    private String newPassword ;

    public String getNewPassword() {
        return newPassword;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
