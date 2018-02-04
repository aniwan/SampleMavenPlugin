package sample.plugin.org.apache.maven.archetypes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;



import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "mcopy", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class MyMojo extends AbstractMojo {
	
	
	
	@Parameter(property = "fileTxfr")
	private List<FileTransferTO> fileTxfr;

	public void execute() throws MojoExecutionException {

		
		for (FileTransferTO tFile : fileTxfr) {

			File source = new File(tFile.getSourceFolder());
			File destination = new File(tFile.getDestinationFolder());

			copyFolder(source, destination);

		}

	}

	public  void copyFolder(File source, File destination) throws MojoExecutionException {
		if (source.isDirectory()) {
			if (!destination.exists()) {
				destination.mkdirs();
			}

			String files[] = source.list();

			for (String file : files) {
				File srcFile = new File(source, file);
				File destFile = new File(destination, file);

				copyFolder(srcFile, destFile);
			}
		} else {
			try {
				boolean exclude=false;
                 if(source.getName().startsWith("._")) {
                	 exclude=true;
                 }
			//	if (source.lastModified() != destination.lastModified()) {
				if( !exclude && !destination.exists()) {
					getLog().info( "New File Copied from "+source.getAbsoluteFile().toPath()+" to"+ destination.getAbsoluteFile().toPath());
					
					Files.copy(source.getAbsoluteFile().toPath(), destination.getAbsoluteFile().toPath(),StandardCopyOption.REPLACE_EXISTING);
					//getLog().info( "New File Copied from "+source.getAbsoluteFile().toPath()+" to"+ destination.getAbsoluteFile().toPath());
					
				}
				else if (!exclude && (Files.getLastModifiedTime(source.toPath()).toMillis()/1000) != (Files.getLastModifiedTime(destination.toPath()).toMillis()/1000)) {
					getLog().info( "File Copied from "+source.getAbsoluteFile().toPath()+" "+Files.getLastModifiedTime(source.toPath()).toMillis() +" to"+ destination.getAbsoluteFile().toPath()+"  "+Files.getLastModifiedTime(destination.toPath()).toMillis());
					Files.copy(source.getAbsoluteFile().toPath(), destination.getAbsoluteFile().toPath(),StandardCopyOption.REPLACE_EXISTING);
					destination.setLastModified(source.lastModified());
				//	getLog().info( "File Copied from "+source.getAbsoluteFile().toPath()+" to"+ destination.getAbsoluteFile().toPath());
				}

			} catch (IOException e) {
				throw new MojoExecutionException("Error Copying file :: Source :" + source.getAbsolutePath()
						+ " Destination : " + destination.getAbsolutePath(), e);
			}

		}
	}

}
