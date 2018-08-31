package br.com.hoptech.vaaguruapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.hoptech.vaaguruapi.domain.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Integer>{
    
    @Transactional(readOnly=true)
    List<Invitation> findByTeam_id(Integer id);
    
    @Transactional(readOnly=true)
    List<Invitation> findByInvitedEmail(String email);    

}
