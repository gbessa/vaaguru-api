package br.com.hoptech.vaaguruapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.hoptech.vaaguruapi.domain.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{

    @Transactional(readOnly=true)
    @Query(value = 
    		"SELECT * FROM TEAM INNER JOIN OWNER_TEAM ON OWNER_TEAM.TEAM_ID  = TEAM.ID WHERE OWNER_TEAM.ROWER_ID = :rowerId  UNION SELECT * FROM TEAM INNER JOIN MEMBER_TEAM ON MEMBER_TEAM.TEAM_ID  = TEAM.ID WHERE MEMBER_TEAM.ROWER_ID = :rowerId ",
	nativeQuery = true)
    List<Team> findAll(@Param("rowerId") Integer rowerId);
    
    @Transactional(readOnly=true)
    @Query(value = 
    		"SELECT * FROM TEAM INNER JOIN OWNER_TEAM ON OWNER_TEAM.TEAM_ID  = TEAM.ID WHERE OWNER_TEAM.ROWER_ID = :rowerId",
	nativeQuery = true)
    List<Team> findOwned(@Param("rowerId") Integer rowerId);
}
