package net.devh.exception;

import net.devh.utils.Messages;

import java.util.UUID;

public class ServiceException extends RuntimeException{
    private final UUID guid;
    @Override
    public String getMessage() {
        return Messages.discoveryServiceError;
    }

    public ServiceException(UUID guid){
        this.guid=guid;
    }

    public UUID getGuid() {
        return guid;
    }
}
