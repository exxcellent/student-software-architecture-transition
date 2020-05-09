package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.RouteDO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RouteComponent {

  List<RouteDO> findAll(Set<Long> filterByInspectorIds);

  List<RouteDO> findAllByDate(LocalDate date, Set<Long> filterByInspectorIds);

  RouteDO findAllByDateAndInspector(LocalDate date, Long inspector);

}
