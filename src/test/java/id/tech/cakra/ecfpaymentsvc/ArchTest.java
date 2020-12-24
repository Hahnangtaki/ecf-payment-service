package id.tech.cakra.ecfpaymentsvc;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("id.tech.cakra.ecfpaymentsvc");

        noClasses()
            .that()
            .resideInAnyPackage("id.tech.cakra.ecfpaymentsvc.service..")
            .or()
            .resideInAnyPackage("id.tech.cakra.ecfpaymentsvc.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..id.tech.cakra.ecfpaymentsvc.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
