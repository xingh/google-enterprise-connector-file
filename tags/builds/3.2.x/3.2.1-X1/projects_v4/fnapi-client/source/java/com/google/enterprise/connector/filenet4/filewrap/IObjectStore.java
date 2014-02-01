package com.google.enterprise.connector.filenet4.filewrap;

import com.filenet.api.core.ObjectStore;
import com.filenet.api.property.PropertyFilter;
import com.google.enterprise.connector.spi.RepositoryDocumentException;
import com.google.enterprise.connector.spi.RepositoryException;
import com.google.enterprise.connector.spi.RepositoryLoginException;

public interface IObjectStore {

  public IBaseObject getObject(String type, String id)
      throws RepositoryDocumentException;

  public IBaseObject fetchObject(String type, String id,
      PropertyFilter filter) throws RepositoryDocumentException;

  public String getName() throws RepositoryException;

  public ObjectStore getObjectStore() throws RepositoryException;

  public void refreshSUserContext() throws RepositoryLoginException;

  public String getSUserLogin();

  public String getSUserPassword();
}