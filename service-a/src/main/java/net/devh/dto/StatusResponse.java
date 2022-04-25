package net.devh.dto;

public enum StatusResponse {
    OK("SUCCESS"),
    ERROR("ERROR");

    private final String value;

    StatusResponse(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
