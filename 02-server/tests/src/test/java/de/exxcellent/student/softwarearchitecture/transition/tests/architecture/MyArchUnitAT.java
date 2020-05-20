package de.exxcellent.student.softwarearchitecture.transition.tests.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "de.exxcellent.student.softwarearchitecture.transition")
public class MyArchUnitAT {

  @ArchTest
  public static final ArchRule appointments = classes()
        .that().resideInAPackage("de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment")
        .should().onlyBeAccessed().byAnyPackage("de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments");

  @ArchTest
  public static final ArchRule moduleDependencies = classes()
      .that().resideInAPackage("de.exxcellent.student.softwarearchitecture.transition.application.resources")
      .should()
        .onlyHaveDependentClassesThat()
          .resideInAnyPackage("de.exxcellent.student.softwarearchitecture.transition.businesslogic.components");

  @ArchTest
  public static final ArchRule appointmentComponent = layeredArchitecture()
      .layer("REST_RESOURCE").definedBy("de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments")
      .layer("ACCESS:API").definedBy("de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api")
      .layer("ACCESS:Facade").definedBy("de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.businesslogic")
      .layer("BUSINESS").definedBy("de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.businesslogic.logic")
      .layer("DATA_ACCESS").definedBy("de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.data")

//      .layer("COMMON").definedBy(
//          "de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.businesslogic",
//          "de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.data",
//          "de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.datetime",
//          "de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.errorhandling",
//          "de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.json",
//          "de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.resilience",
//          "de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.types",
//          "de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.validation",
//
//          "de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.common"
//      )

      .whereLayer("REST_RESOURCE").mayNotBeAccessedByAnyLayer()
      .whereLayer("ACCESS:API").mayOnlyBeAccessedByLayers("REST_RESOURCE", "ACCESS:Facade") // TODO <--- Problem sollte mit der hexagonalen Architektur behoben werden
      .whereLayer("ACCESS:Facade").mayOnlyBeAccessedByLayers("ACCESS:API")
      .whereLayer("BUSINESS").mayOnlyBeAccessedByLayers("ACCESS:Facade")
      .whereLayer("DATA_ACCESS").mayOnlyBeAccessedByLayers("BUSINESS");

}
