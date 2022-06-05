package it.GFM.cashback.runner;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.GFM.cashback.impl.ERole;
import it.GFM.cashback.impl.Role;
import it.GFM.cashback.impl.User;
import it.GFM.cashback.impl.UserRepository;
import it.GFM.cashback.repository.AcquistoRepository;
import it.GFM.cashback.repository.CashBackRepository;
import it.GFM.cashback.repository.OffertaRepository;
import it.GFM.cashback.repository.PagamentoRepository;
import it.GFM.cashback.repository.ProfiloRepository;
import it.GFM.cashback.repository.UtenteRepository;




public class Runner implements ApplicationRunner {

	@Autowired AcquistoRepository ar;
	@Autowired CashBackRepository cr;
	@Autowired OffertaRepository or;
	@Autowired PagamentoRepository pr;
	@Autowired ProfiloRepository prr;
	@Autowired UtenteRepository ur;
	@Autowired PasswordEncoder encoder;
	@Autowired UserRepository urr;
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		Set hash = new HashSet<Role>();
		Role admin= Role.builder().roleName(ERole.ROLE_ADMIN).build();
		hash.add(admin);
		Set hash1 = new HashSet<Role>();
		Role user= Role.builder().roleName(ERole.ROLE_USER).build();
		hash1.add(user);
		User u1 = User.builder().username("mauro").nome("Roberto").cognome("Loris")
				.password( BCrypt.hashpw("mauro", BCrypt.gensalt())).email("ciaociao@hotmail.it").roles(hash1).accountActive(true).build();
		User u = User.builder().username("giovanni").nome("Lorella").cognome("Lorenzi")
				.password(encoder.encode("maurito")).roles(hash).email("ciaocaio@hotmail.it").accountActive(true).build();

		urr.save(u);
		urr.save(u1);	
	}

}
