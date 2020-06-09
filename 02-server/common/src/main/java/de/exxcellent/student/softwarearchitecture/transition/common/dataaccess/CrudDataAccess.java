package de.exxcellent.student.softwarearchitecture.transition.common.dataaccess;

import java.util.List;

public interface CrudDataAccess<T extends DataTransferObject> {

    List<T> findAll();

    T findById(Long id);

    T create(T newDTO, User user);

    T update(T updatedDTO, User user);

    void deleteById(Long id);
}
