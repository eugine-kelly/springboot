package com.prod.scorpion.config;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class CustomStringIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 7);
        return "OFF" + uuid;
    }
}
