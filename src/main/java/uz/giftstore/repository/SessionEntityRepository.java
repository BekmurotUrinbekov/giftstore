package uz.giftstore.repository;

import uz.giftstore.base.BaseRepository;
import uz.giftstore.entity.SessionEntity;

import java.util.Optional;

public interface SessionEntityRepository extends BaseRepository<SessionEntity> {
    Optional<SessionEntity> findBySessionId(String sessionId);
}
