package com.digiboy.platform.users.mapper;

import com.digiboy.platform.users.dto.DataTransferObject;
import com.digiboy.platform.users.to.DomainEntity;

public interface EntityMapper<T extends DomainEntity, R extends DataTransferObject> {

    /**
     * to entity
     */
    T map(R dto);

    /**
     * from entity
     */
    R map(T entity);
}