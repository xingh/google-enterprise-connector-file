package com.google.enterprise.connector.file.filejavawrap;

import com.filenet.api.constants.ClassNames;
import com.filenet.api.util.UserContext;
import com.google.enterprise.connector.file.FileConnector;
import com.google.enterprise.connector.file.FileSession;
import com.google.enterprise.connector.file.filewrap.IConnection;
import com.google.enterprise.connector.file.filewrap.IDocument;
import com.google.enterprise.connector.file.filewrap.IObjectFactory;
import com.google.enterprise.connector.file.filewrap.IObjectStore;
import com.google.enterprise.connector.file.TestConnection;
import com.google.enterprise.connector.spi.RepositoryException;
import com.google.enterprise.connector.spi.RepositoryLoginException;
import com.google.enterprise.connector.spi.SpiConstants.ActionType;

import junit.framework.TestCase;

public class FnObjectStoreTest extends TestCase {

	FileSession fs;
	IObjectStore ios;
	IConnection conn;
	UserContext uc;
	IObjectFactory iof;
	IDocument fd;

	protected void setUp() throws RepositoryLoginException, RepositoryException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		FileConnector connec = new FileConnector();
		connec.setUsername(TestConnection.adminUsername);
		connec.setPassword(TestConnection.adminPassword);
		connec.setObject_store(TestConnection.objectStore);
		connec.setWorkplace_display_url(TestConnection.displayURL);
		connec.setObject_factory(TestConnection.objectFactory);
		connec.setContent_engine_url(TestConnection.uri);
		
		fs = (FileSession)connec.login();

		iof= (IObjectFactory) Class.forName(TestConnection.objectFactory).newInstance();
		IConnection conn = iof.getConnection(TestConnection.uri);
//		Domain domain = Factory.Domain.getInstance(conn.getConnection(), "P8.V4");
		ios = iof.getObjectStore(TestConnection.objectStore, conn, TestConnection.username, TestConnection.password);

	}

	/*
	 * Test method for 'com.google.enterprise.connector.file.filejavawrap.FnObjectStore.getObject(String, String)'
	 */
	public void testGetObject() throws RepositoryException {
		fd = (IDocument) ios.getObject(ClassNames.DOCUMENT, TestConnection.docId1);
		fd.fetch(TestConnection.included_meta);
		assertNotNull(fd);
		 assertEquals("{"+TestConnection.docId1+"}", fd.getId(ActionType.ADD));
	}

	/*
	 * Test method for 'com.google.enterprise.connector.file.filejavawrap.FnObjectStore.getName()'
	 */
	public void testGetName() throws RepositoryException {
		assertEquals("GED", ios.getName());

	}

	/*
	 * Test method for 'com.google.enterprise.connector.file.filejavawrap.FnObjectStore.getObjectStore()'
	 */
	public void testGetObjectStore() throws RepositoryException {
		assertNotNull(ios.getObjectStore());

	}

}
