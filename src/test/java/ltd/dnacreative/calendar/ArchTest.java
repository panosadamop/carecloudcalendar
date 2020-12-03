package ltd.dnacreative.calendar;

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
            .importPackages("ltd.dnacreative.calendar");

        noClasses()
            .that()
            .resideInAnyPackage("ltd.dnacreative.calendar.service..")
            .or()
            .resideInAnyPackage("ltd.dnacreative.calendar.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..ltd.dnacreative.calendar.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
