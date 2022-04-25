package net.devh.dto;

import net.devh.utils.Messages;

import static net.devh.dto.StatusResponse.*;

public class JsonResponseBuilder {

    public static <T> JsonResponse<T> error(String message, T data){
        JsonResponse<T> response = new JsonResponse<>();
        response.setStatus(ERROR.getValue());
        response.setDescription(message);
        response.setData(data);
        return response;
    }

    public static <T> JsonResponse<T> success(T data){
        JsonResponse<T> response = new JsonResponse<>();
        response.setStatus(OK.getValue());
        response.setDescription(Messages.compareSuccess);
        response.setData(data);
        return response;
    }
}
