package me.EssexIO;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.md_5.jbeat.Patcher;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.google.common.hash.Hashing;
import com.google.common.io.Files;

public class SpigotPatcherGUI {

	protected Shell shlSpigotpatcher;
	private Text text;
    private Text text_1;
    private Text text_2;
    
	static JOptionPane msgbox = new JOptionPane();
	
	static FileNameExtensionFilter jarfilter = new FileNameExtensionFilter("JAR File", "jar");
	static FileNameExtensionFilter patchfilter = new FileNameExtensionFilter("Patch File", "bps");

	
	/**
	 * Patcher Files
	 */
	static File originalFile;
    static File patchFile;
    static File outputFile;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SpigotPatcherGUI window = new SpigotPatcherGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlSpigotpatcher.open();
		shlSpigotpatcher.layout();
		while (!shlSpigotpatcher.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSpigotpatcher = new Shell();
		shlSpigotpatcher.setSize(377, 307);
		shlSpigotpatcher.setText("SpigotPatcher");
		
		Button btnPatch = new Button(shlSpigotpatcher, SWT.NONE);
		btnPatch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Patcher();
				} catch (NullPointerException e1) {
		            JOptionPane.showMessageDialog(msgbox, "You did not select one or more of the required files!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPatch.setBounds(10, 218, 344, 25);
		btnPatch.setText("Patch");
		
		Label lblIfYouHave = new Label(shlSpigotpatcher, SWT.CENTER);
		lblIfYouHave.setBounds(61, 31, 238, 37);
		lblIfYouHave.setText("If you have any queries, please direct them to\r\nhttp://www.spigotmc.org/");
		
		Label lblWelcomeToThe = new Label(shlSpigotpatcher, SWT.CENTER);
		lblWelcomeToThe.setBounds(68, 10, 225, 15);
		lblWelcomeToThe.setText("Welcome to the Spigot patch applicator.");
		
		text = new Text(shlSpigotpatcher, SWT.BORDER);
		text.setEditable(false);
		text.setBounds(10, 95, 274, 21);
		
		Label lblOriginal = new Label(shlSpigotpatcher, SWT.NONE);
		lblOriginal.setBounds(10, 74, 64, 15);
		lblOriginal.setText("Original JAR");
		
		Button btnNewButton = new Button(shlSpigotpatcher, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final JFileChooser filech = new JFileChooser();
				filech.setFileFilter(jarfilter);
				
				int returnVal = filech.showOpenDialog(filech);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            originalFile = filech.getSelectedFile();
		            text.setText(originalFile.getAbsolutePath());
		        }
			}
		});
		
			
		btnNewButton.setBounds(290, 93, 64, 25);
		btnNewButton.setText("Browse");
		
		text_1 = new Text(shlSpigotpatcher, SWT.BORDER);
		text_1.setEditable(false);
		text_1.setBounds(10, 143, 274, 21);
		
		Button btnBrowse = new Button(shlSpigotpatcher, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final JFileChooser filech = new JFileChooser();
				
				filech.setFileFilter(patchfilter);
				
				int returnVal = filech.showOpenDialog(filech);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            patchFile = filech.getSelectedFile();
		            text_1.setText(patchFile.getAbsolutePath());
		        }
			}
		});
		btnBrowse.setBounds(290, 141, 64, 25);
		btnBrowse.setText("Browse");
		
		Label lblNewLabel = new Label(shlSpigotpatcher, SWT.NONE);
		lblNewLabel.setBounds(10, 122, 55, 15);
		lblNewLabel.setText("Patch File");
		
		Label lblPatchedJar = new Label(shlSpigotpatcher, SWT.NONE);
		lblPatchedJar.setBounds(10, 170, 73, 15);
		lblPatchedJar.setText("Output JAR");
		
		text_2 = new Text(shlSpigotpatcher, SWT.BORDER);
		text_2.setEditable(false);
		text_2.setBounds(10, 191, 274, 21);
		
		Button btnBrowse_1 = new Button(shlSpigotpatcher, SWT.NONE);
		btnBrowse_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final JFileChooser filech = new JFileChooser();
				
				filech.setFileFilter(jarfilter);
				
				int returnVal = filech.showSaveDialog(filech);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            outputFile = filech.getSelectedFile();
		            text_2.setText(outputFile.getAbsolutePath());
		        }
			}
		});
		btnBrowse_1.setBounds(290, 189, 64, 25);
		btnBrowse_1.setText("Browse");
		
		Label lblCreatedByEssexio = new Label(shlSpigotpatcher, SWT.NONE);
		lblCreatedByEssexio.setBounds(255, 249, 99, 15);
		lblCreatedByEssexio.setText("Created by EssexIO");
	}
	
	public static void Patcher() throws Exception {
		if ( !originalFile.canRead() )
        {
            JOptionPane.showMessageDialog(msgbox, "Specified original file " + originalFile + " does not exist or cannot be read!", "File does not exist", JOptionPane.ERROR_MESSAGE  );
            return;
        }
        if ( !patchFile.canRead() )
        {
            JOptionPane.showMessageDialog(msgbox, "Specified patch file " + patchFile + " does not exist or cannot be read!", "File does not exist", JOptionPane.ERROR_MESSAGE  );
            return;
        }
        if ( outputFile.exists() )
        {
            JOptionPane.showMessageDialog(msgbox, "Specified output file " + outputFile + " exists, please remove it before running this program!", "File already exists", JOptionPane.ERROR_MESSAGE  );
            return;
        }
        if ( !outputFile.createNewFile() )
        {
        	JOptionPane.showMessageDialog(msgbox, "Could not create specified output file " + outputFile + " please ensure that it is in a valid directory which can be written to.", "Could not create output file", JOptionPane.ERROR_MESSAGE );
            return;
        }

    	JOptionPane.showMessageDialog(msgbox, 
    			"Starting patching process, please wait.\n"
    			+ "\tInput md5 Checksum: " + Files.hash( originalFile, Hashing.md5() ) + "\n"
    			+ "\tPatch md5 Checksum: " + Files.hash( patchFile, Hashing.md5() ), "Starting patching process...", JOptionPane.INFORMATION_MESSAGE);

        try
        {
            new Patcher( patchFile, originalFile, outputFile ).patch();
        } catch ( Exception ex )
        {
        	JOptionPane.showMessageDialog(msgbox, "Exception occured whilst patching file!\n", "Exception occured", JOptionPane.ERROR_MESSAGE );
            ex.printStackTrace();
            outputFile.delete();
            return;
        }

        JOptionPane.showMessageDialog(msgbox, "Your file has been patched and verified! We hope you enjoy using Spigot!\n\tOutput md5 Checksum: " + Files.hash( outputFile, Hashing.md5() ), "File patch successful.", JOptionPane.INFORMATION_MESSAGE );
	}
}
