package com.google.enterprise.connector.file.filewrap;

public interface IObjectStore {

	public IDocument getObject(String guidOrPath);

	public void setDisplayUrl(String displayUrl);

	public String getDisplayUrl();

	public String getName();

	public String getIsPublic();

	public void setIsPublic(String isPublic);

}
