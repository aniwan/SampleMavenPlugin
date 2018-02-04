package sample.plugin.org.apache.maven.archetypes;

public class FileTransferTO {
	
	String sourceFolder;
	String destinationFolder;
	String[] includeExt;
	String[] excludeExt;
	
	
	public String getSourceFolder() {
		return sourceFolder;
	}
	public void setSourceFolder(String sourceFolder) {
		this.sourceFolder = sourceFolder;
	}
	public String getDestinationFolder() {
		return destinationFolder;
	}
	public void setDestinationFolder(String destinationFolder) {
		this.destinationFolder = destinationFolder;
	}
	public String[] getIncludeExt() {
		return includeExt;
	}
	public void setIncludeExt(String[] includeExt) {
		this.includeExt = includeExt;
	}
	public String[] getExcludeExt() {
		return excludeExt;
	}
	public void setExcludeExt(String[] excludeExt) {
		this.excludeExt = excludeExt;
	}

}
