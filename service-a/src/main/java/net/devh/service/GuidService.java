package net.devh.service;

import java.util.Random;
import java.util.UUID;

public interface GuidService {
    Random random = new Random();
    UUID defaultGuid = UUID.fromString("383e0e97-86f6-4b7c-8eb0-d1d94dd67eb6");

    UUID getGuid();
    UUID compareGuid();
}
