package org.enes.repository;

import org.enes.entity.Mahalle;

import java.util.UUID;

public class MahalleRepository extends RepositoryManager<Mahalle, UUID> {

    public MahalleRepository(Mahalle mahalle) {
        super(mahalle);
    }
}
