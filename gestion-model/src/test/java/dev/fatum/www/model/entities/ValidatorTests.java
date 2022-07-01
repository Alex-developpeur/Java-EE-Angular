package dev.fatum.www.model.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author LEOANRD Simple test pour être sure que le Bean Validation fonctionne
 * (utile pour les mise à jour Hibernate Validator / Bean Validator)
 */

class ValidatorTests {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void neDoitPasValiderQuandNomVide() {

		LocaleContextHolder.setLocale(Locale.FRANCE);
		Groupes groupes = new Groupes();
		groupes.setNom("");
		groupes.setDescription("description");

		Validator validator = createValidator();
		Set<ConstraintViolation<Groupes>> constraintViolations = validator.validate(groupes);

		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<Groupes> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("nom");
		assertThat(violation.getMessage()).isEqualTo("{groupes.nom}");
	}

}
