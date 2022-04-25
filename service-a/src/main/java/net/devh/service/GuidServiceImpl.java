package net.devh.service;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import net.devh.cache.CachableGuid;
import net.devh.exception.CompareException;
import net.devh.exception.ServiceException;
import net.devh.feign.ServiceBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GuidServiceImpl implements GuidService{
    @Autowired
    private CachableGuid cachableGuid;
    @Autowired
    private ServiceBClient serviceBClient;

    @Override
    public UUID getGuid(){
        boolean isNotExistNumber = random.nextBoolean();
        if(isNotExistNumber)
            return UUID.randomUUID();
        return defaultGuid;
    }

    @Override
    public UUID compareGuid(){
        UUID guid = getGuid();
        compareGuid(guid);
        sendGuidToServiceB(guid);
        return guid;
    }

    private void compareGuid(UUID guid){
        boolean notCompare = cachableGuid.notExistsGuid(guid);
        if(notCompare)
            throw new CompareException(guid);
    }

    private void sendGuidToServiceB(UUID guid){
        try{
            serviceBClient.getGuid(guid);
        }
        catch (HystrixRuntimeException ex){
            throw new ServiceException(guid);
        }
    }
}
