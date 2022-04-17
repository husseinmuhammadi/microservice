package com.digiboy.platform.user.mapper;

import com.digiboy.platform.user.dto.DataTransferObject;
import com.digiboy.platform.user.to.DomainEntity;

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