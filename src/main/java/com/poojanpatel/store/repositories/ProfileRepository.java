package com.poojanpatel.store.repositories;

import com.poojanpatel.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}