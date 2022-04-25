package net.devh.cache;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
public class CachableGuid {

    ConcurrentSkipListSet<UUID> listGuid = new ConcurrentSkipListSet<>();

    public boolean notExistsGuid(UUID guid){
        return listGuid.add(guid);
    }
}
