package de.exxcellent.student.softwarearchitecture.transition.application.resources.contacts;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.BusinessResource;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.SecuredResource;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.user.CurrentUser;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.contacts.types.ContactTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.contacts.types.ContactsCTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.contacts.mapper.ContactMapper;
import de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.permissions.RequiresPermission;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.api.ContactComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.api.types.Permission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Tag(name = "Planning")
@RestController
@CrossOrigin
@RequestMapping("v1/contacts")
public class ContactsResourceV1 extends SecuredResource implements BusinessResource {

  private static final Logger LOG = LoggerFactory.getLogger(ContactsResourceV1.class);

  private final ContactComponent contactComponent;

  @Autowired
  public ContactsResourceV1(ContactComponent contactComponent) {
    this.contactComponent = contactComponent;
  }

  @Operation(summary = "Find All Contacts")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public ContactsCTO findAll() {
    var contactList = contactComponent.findAll();
    return ContactMapper.toContactsCTO.apply(contactList);
  }

  @Operation(summary = "Find Contact")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      path = "{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public ContactTO findById(@PathVariable("id") Long id) {
    return ContactMapper.toContactTO.apply(contactComponent.findById(id));
  }

  @Operation(summary = "Create Contact")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.WRITE_ALL)
  public ResponseEntity<ContactTO> create(@RequestBody ContactTO contactTO) {
    var contactDO = ContactMapper.toContactDO.apply(contactTO);
    var createdContactDO = contactComponent.create(contactDO, CurrentUser.getUser());
    var response = ContactMapper.toContactTO.apply(createdContactDO);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Operation(summary = "Update Contact")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.PUT,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.WRITE_ALL)
  public ContactTO update(@RequestBody ContactTO contactTO) {
    var contactDO = ContactMapper.toContactDO.apply(contactTO);
    var updatedContactDO = contactComponent.update(contactDO, CurrentUser.getUser());
    return ContactMapper.toContactTO.apply(updatedContactDO);
  }

  @Operation(summary = "Delete Contact")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.DELETE,
      path = "{id}",
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
      contactComponent.delete(id);
    return ResponseEntity.ok().build();
  }
}
