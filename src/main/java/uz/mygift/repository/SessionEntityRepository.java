package uz.mygift.repository;

import uz.mygift.base.BaseRepository;
import uz.mygift.entity.SessionEntity;

import java.util.Optional;

public interface SessionEntityRepository extends BaseRepository<SessionEntity> {
    Optional<SessionEntity> findBySessionId(String sessionId);
}
