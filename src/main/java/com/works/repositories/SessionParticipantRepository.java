package com.works.repositories;

import com.works.entities.Participant;
import com.works.entities.Program;
import com.works.entities.SessionParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionParticipantRepository extends JpaRepository<SessionParticipant, Long> {

    List<SessionParticipant> findAllBySession_Id(Long id);

    void deleteBySession_IdAndParticipant_Id(Long s_id, Long p_id);
}
