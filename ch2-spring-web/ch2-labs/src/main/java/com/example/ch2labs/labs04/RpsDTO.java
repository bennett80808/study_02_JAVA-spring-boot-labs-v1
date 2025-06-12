package com.example.ch2labs.labs04;

public class RpsDTO {
    private String user;
    private String server;
    private String result;

    public RpsDTO(String user, String server, String result) {
        this.user = user;
        this.server = server;
        this.result = result;
    }

    public String getUser() {
        return user;
    }

    public String getServer() {
        return server;
    }

    public String getResult() {
        return result;
    }
}
