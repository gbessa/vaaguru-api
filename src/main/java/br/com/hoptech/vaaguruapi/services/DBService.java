package br.com.hoptech.vaaguruapi.services;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hoptech.vaaguruapi.domain.Inscription;
import br.com.hoptech.vaaguruapi.domain.Rower;
import br.com.hoptech.vaaguruapi.domain.Schedule;
import br.com.hoptech.vaaguruapi.domain.Team;
import br.com.hoptech.vaaguruapi.repositories.InscriptionRepository;
import br.com.hoptech.vaaguruapi.repositories.RowerRepository;
import br.com.hoptech.vaaguruapi.repositories.ScheduleRepository;
import br.com.hoptech.vaaguruapi.repositories.TeamRepository;

@Service
public class DBService {
    
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private RowerRepository rowerRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private InscriptionRepository inscriptionRepository;

    public void instantiateDatabase() throws ParseException {

	//CREATING TEAMS AND ROWERS
	Team team1 = new Team(null, "Fusão VAA", "Equipe do Rodrigão", "");
	Team team2 = new Team(null, "Mauna Loa - Charitas", "Primeira unidade da Mauna Loa", "");
	Team team3 = new Team(null, "Mauna Loa - Itaipu", "Segunda unidade da Mauna Loa", "");
	Team team4 = new Team(null, "Mauna Loa - Icaraí", "Terceira unidade da Mauna Loa", "");
	
	Rower rower1 = new Rower(null, "Gustavo Bessa", "gbvirtual@gmail.com", "2199999991", true, "");
	Rower rower2 = new Rower(null, "Fernando Tostes", "ftostes@gmail.com.blocksend", "2199999992", true, "");
	Rower rower3 = new Rower(null, "Adriana Fagundes", "dri@gmail.com.blocksend", "2199999993", true, "");
	Rower rower4 = new Rower(null, "Plinio Salgado", "plinio@gmail.com.blocksend", "2199999994", true, "");
	Rower rower5 = new Rower(null, "Ana Rita", "ana.rita@gmail.com.blocksend", "2199999995", true, "");
	Rower rower6 = new Rower(null, "Paulo Eduardo", "peduardo@gmail.com.blocksend", "2199999996", true, "");
	
	rower1.getTeams().addAll(Arrays.asList(team2, team4));
	rower2.getTeams().addAll(Arrays.asList(team4));
	rower3.getTeams().addAll(Arrays.asList(team4));
	rower4.getTeams().addAll(Arrays.asList(team4));
	rower5.getTeams().addAll(Arrays.asList(team1));
	rower6.getTeams().addAll(Arrays.asList(team1));
	
	team2.getRowers().addAll(Arrays.asList(rower1));
	team4.getRowers().addAll(Arrays.asList(rower1, rower2, rower3, rower4));
	team1.getRowers().addAll(Arrays.asList(rower5, rower6));
	
	teamRepository.saveAll(Arrays.asList(team1, team2, team3, team4));
	rowerRepository.saveAll(Arrays.asList(rower1, rower2, rower3, rower4, rower5, rower6));
	
	
	//CREATING SCHEDULE AND INSCRIPTIONS
	Schedule schedule1 = new Schedule(null, new Date(), 1, 12, team1, "Primeiro treino depois da Copa");
	Schedule schedule2 = new Schedule(null, new Date(), 1, 24, team4, "Mauna BI-CAMPEÃ");
	
	Inscription inscr1 = new Inscription(null, rower5, schedule1, new Date());
	Inscription inscr2 = new Inscription(null, rower6, schedule1, new Date());
	Inscription inscr3 = new Inscription(null, rower1, schedule2, new Date());
	Inscription inscr4 = new Inscription(null, rower2, schedule2, new Date());
	Inscription inscr5 = new Inscription(null, rower3, schedule2, new Date());
	
	schedule1.getInscriptions().addAll(Arrays.asList(inscr1, inscr2));
	schedule2.getInscriptions().addAll(Arrays.asList(inscr3, inscr4, inscr5));
	
	scheduleRepository.saveAll(Arrays.asList(schedule1, schedule2));
	inscriptionRepository.saveAll(Arrays.asList(inscr1, inscr2, inscr3, inscr4, inscr5));
    }

}
