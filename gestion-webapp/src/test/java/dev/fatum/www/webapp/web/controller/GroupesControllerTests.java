package dev.fatum.www.webapp.web.controller;

import dev.fatum.www.business.DaoFactory;
import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.consumer.dao.*;
import dev.fatum.www.consumer.dao.documents.DevisDao;
import dev.fatum.www.consumer.dao.documents.FactureDao;
import dev.fatum.www.consumer.dao.documents.NumerosDocumentsDao;
import dev.fatum.www.webapp.security.UserDetailsServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GroupesController.class)
@ExtendWith(SpringExtension.class)
class GroupesControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserDetailsServiceImpl userDetailsService;

	@MockBean
	private ManagerFactory managerFactory;

	@MockBean
	private DaoFactory daoFactory;

	@MockBean
	private GroupesDao groupesDao;

	@MockBean
	private AdministrateurDao administrateurDao;

	@MockBean
	private UtilisateurDao utilisateurDao;

	@MockBean
	private PersonneDao personneDao;

	@MockBean
	private ProfilDao profilDao;

	@MockBean
	private EntrepriseDao entrepriseDao;

	@MockBean
	private NumerosDocumentsDao numerosDocumentsDao;

	@MockBean
	private FactureDao factureDao;

	@MockBean
	private DevisDao devisDao;

	private static Instant startTime;

	private static Instant eachTime;

	@Autowired
//	private MockMvc mockMvc;

	@BeforeAll
	static void initAll() {
		System.out.println("##### Before All : " + Instant.now());
		startTime = Instant.now();
	}

	@BeforeEach
	void init() {
		System.out.println("##### Before Each");
		eachTime = Instant.now();
	}

	@Test
//	@WithMockUser(username="jack@example.com")
	void pageDeLaListeDesGroupes() throws Exception {
//		mockMvc.perform(get("/Groupes")).andExpect(status().isOk());
	}


	@Test
	void contextLoads() {
		assumeTrue("abc".contains("abc"));
//		fail("test should have been aborted");
	}

	@Test
	void testTests() {
		assumeTrue("abc".contains("abc"));
//		fail("test should have been aborted");
	}

	@AfterEach
	void tearDown() {
		Instant endedAt = Instant.now();
		Duration duration = Duration.between(eachTime, endedAt);
		System.out.println("##### After Each : " + duration.toString());
	}

	@AfterAll
	static void tearDownAll() {
		System.out.println("##### After All : " + Instant.now());
		Instant endTime = Instant.now();
		long duration = Duration.between(startTime, endTime).toMillis();
		System.out.println(MessageFormat.format("##### Durée des tests : {0} ms", duration));
	}
}
