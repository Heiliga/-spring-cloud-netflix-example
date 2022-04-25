package net.devh.exception;

import net.devh.utils.Messages;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public class CompareException extends RuntimeException{
    private final UUID guid;
    @Override
    public String getMessage() {
        return Messages.compareError;
    }

    public CompareException(UUID guid){
        this.guid=guid;
    }

    public UUID getGuid() {
        return guid;
    }
}
