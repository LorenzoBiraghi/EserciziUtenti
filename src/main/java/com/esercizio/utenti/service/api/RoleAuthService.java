package com.esercizio.utenti.service.api;

import com.esercizio.utenti.entity.RoleAuth;
import com.esercizio.utenti.repository.RoleAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleAuthService {
    private final RoleAuthRepository roleJpaRepository;

    /**
     * Save in Database a Role
     * @param roleEntity
     * @return Object saved
     */
    public RoleAuth save(RoleAuth roleEntity) {
        log.info("Saving role {} to the database", roleEntity.getName());
        return roleJpaRepository.save(roleEntity);
    }
}
