package br.com.hoptech.vaaguruapi.services;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    public void instantiateDatabase() throws ParseException {

	//CREATING TEAMS AND ROWERS
	Team team1 = new Team(null, "Fusão VAA", "Equipe do Rodrigão", "fusaovaa_logo.jpeg");
	Rower rowerResp1 = new Rower(null, "Rodrigao", "rodrigao@gmail.com", "219999902", true, "rodrigao.jpg", pwdEncoder.encode("123"));

	Team team2 = new Team(null, "Mauna Loa - Charitas", "Primeira unidade da Mauna Loa", "maunaloa_logo.jpg");
	Rower rowerResp2 = new Rower(null, "Manel", "manel@gmail.com", "219999902", true, "", pwdEncoder.encode("123"));

	Team team3 = new Team(null, "Mauna Loa - Itaipu", "Segunda unidade da Mauna Loa", "maunaloa_logo.jpg");
	Rower rowerResp3 = new Rower(null, "Vini", "vini@gmail.com", "219999902", true, "", pwdEncoder.encode("123"));

	Team team4 = new Team(null, "Mauna Loa - Icaraí", "Terceira unidade da Mauna Loa", "maunaloa_logo.jpg");
	Rower rowerResp4 = new Rower(null, "Thiago Barcelos", "thiagao@gmail.com", "219999901", true, "thiagao_prof.jpeg", pwdEncoder.encode("123"));
	Rower rowerResp4_1 = new Rower(null, "Hau", "hau@gmail.com", "219999901", true, "thiagao_prof.jpeg", pwdEncoder.encode("123"));
	
	Team team5 = new Team(null, "Wavemasters", "Equipe de remadores brabos", "wavemasters_logo.jpeg");
		
	Rower rower1 = new Rower(null, "Gustavo Bessa", "gbvirtual@gmail.com", "2199999991", true, "gbessa.jpg", pwdEncoder.encode("123"));
	Rower rower2 = new Rower(null, "Fernando Tostes", "ftostes@gmail.com.blocksend", "2199999992", true, "", pwdEncoder.encode("123"));
	Rower rower3 = new Rower(null, "Adriana Fagundes", "dri@gmail.com.blocksend", "2199999993", true, "", pwdEncoder.encode("123"));
	Rower rower4 = new Rower(null, "Plinio Salgado", "plinio@gmail.com.blocksend", "2199999994", true, "", pwdEncoder.encode("123"));
	Rower rower5 = new Rower(null, "Ana Rita", "ana.rita@gmail.com.blocksend", "2199999995", true, "", pwdEncoder.encode("123"));
	Rower rower6 = new Rower(null, "Paulo Eduardo", "peduardo@gmail.com.blocksend", "2199999996", true, "", pwdEncoder.encode("123"));
	
	rowerResp1.getOwners().addAll(Arrays.asList(team1));
	rower6.getMembers().addAll(Arrays.asList(team1));
	rower5.getMembers().addAll(Arrays.asList(team1));
	
	rowerResp2.getOwners().addAll(Arrays.asList(team2));
	rower1.getMembers().addAll(Arrays.asList(team2, team4, team5));
	
	rowerResp3.getOwners().addAll(Arrays.asList(team3));
	
	rowerResp4.getOwners().addAll(Arrays.asList(team4));
	rowerResp4.getMembers().addAll(Arrays.asList(team2, team4));
	rowerResp4_1.getOwners().addAll(Arrays.asList(team4));	
	rower2.getMembers().addAll(Arrays.asList(team4));
	rower3.getMembers().addAll(Arrays.asList(team4));
	rower4.getMembers().addAll(Arrays.asList(team4));
	
	team1.getOwners().addAll(Arrays.asList(rowerResp1));
	team1.getMembers().addAll(Arrays.asList(rower5, rower6));
		
	team2.getOwners().addAll(Arrays.asList(rowerResp2));
	team2.getMembers().addAll(Arrays.asList(rower1, rowerResp4));
	
	team3.getOwners().addAll(Arrays.asList(rowerResp3, rowerResp4));
	
	team4.getOwners().addAll(Arrays.asList(rowerResp4, rowerResp4_1));
	team4.getMembers().addAll(Arrays.asList(rower1, rower2, rower3, rower4, rowerResp4));
	
	team5.getMembers().addAll(Arrays.asList(rower1));
	
	teamRepository.saveAll(Arrays.asList(team1, team2, team3, team4, team5));
	rowerRepository.saveAll(Arrays.asList(rowerResp1, rowerResp2, rowerResp3, rowerResp4, rowerResp4_1));
	rowerRepository.saveAll(Arrays.asList(rower1, rower2, rower3, rower4, rower5, rower6));
		
	//CREATING SCHEDULE AND INSCRIPTIONS
	Calendar cal = Calendar.getInstance();
	cal.set(Calendar.HOUR_OF_DAY, 5);
	cal.set(Calendar.MINUTE, 45);
	Schedule schedule1 = new Schedule(null, cal.getTime(), 1, 12, team1, rowerResp2, "Primeiro treino depois da Copa");
	Schedule schedule2 = new Schedule(null, cal.getTime(), 1, 24, team4, rowerResp1, "Mauna BI-CAMPEÃ");
	cal.set(Calendar.HOUR_OF_DAY, 7);
	cal.set(Calendar.MINUTE, 15);
	Schedule schedule3 = new Schedule(null, cal.getTime(), 1, 18, team4, rowerResp1, "Mauna BI-CAMPEÃ");
	
	Inscription inscr1 = new Inscription(null, rower5, schedule1, new Date());
	Inscription inscr2 = new Inscription(null, rower6, schedule1, new Date());
	Inscription inscr3 = new Inscription(null, rower1, schedule2, new Date());
	Inscription inscr4 = new Inscription(null, rower2, schedule2, new Date());
	Inscription inscr5 = new Inscription(null, rower3, schedule2, new Date());
	
	schedule1.getInscriptions().addAll(Arrays.asList(inscr1, inscr2));
	schedule2.getInscriptions().addAll(Arrays.asList(inscr3, inscr4, inscr5));
	
	scheduleRepository.saveAll(Arrays.asList(schedule1, schedule2, schedule3));
	inscriptionRepository.saveAll(Arrays.asList(inscr1, inscr2, inscr3, inscr4, inscr5));
    }

}
