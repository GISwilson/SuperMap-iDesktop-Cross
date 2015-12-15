package com.supermap.desktop.ui.controls;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.supermap.desktop.controls.ControlsProperties;
import com.supermap.desktop.properties.CommonProperties;
import com.supermap.desktop.properties.CoreProperties;

/**
 *
 * @author Administrator
 */
public class JPanelWorkspaceSaveAsSQL extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates new form JPanelWorkspaceSaveAsSQL
	 */
	private int index;

	public JPanelWorkspaceSaveAsSQL(int index) {
		this.index = index;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initResources() {
		if (1 == index) {
			jLabelServer.setText(CommonProperties.getString("String_Label_DataSource_Instance"));
		} else {
			jLabelServer.setText(CommonProperties.getString("String_Label_DataSource_Server"));
		}
		jLabelDatabaseName.setText(CommonProperties.getString("String_Label_DatabaseName"));
		jLabelUserName.setText(CoreProperties.getString("String_Label_DataUser"));
		jLabelPassword.setText(CoreProperties.getString("String_Label_DataPassword"));
		jLabelWorkspaceName.setText(CoreProperties.getString("String_Label_WorkspaceName"));
		jLabelWorkspaceVersion.setText(ControlsProperties.getString("String_Label_WorkspaceVersion"));
	}

	private void initComponents() {

		jLabelPassword = new JLabel();
		jLabelServer = new JLabel();
		jTextFieldUserName = new JTextField();
		jComboBoxServer = new JComboBox<String>();
		jComboBoxServer.setEditable(true);
		jLabelWorkspaceVersion = new JLabel();
		passwordField = new JPasswordField();
		jTextFieldDatabaseName = new JTextField();
		jLabelWorkspaceName = new JLabel();
		jComboBoxWorkspaceName = new JComboBox<String>();
		jComboBoxWorkspaceName.setEditable(true);
		jLabelDatabaseName = new JLabel();
		jComboBoxWorkspaceVersion = new JComboBox<String>();
		jLabelUserName = new JLabel();

		jComboBoxServer.setModel(new DefaultComboBoxModel<String>());

		jComboBoxWorkspaceName.setModel(new DefaultComboBoxModel<String>());

		jComboBoxWorkspaceVersion.setModel(new DefaultComboBoxModel<String>(new String[] { "SuperMap UGC 7.0", "SuperMap UGC 6.0" }));

		initResources();

		//@formatter:off
       GroupLayout layout = new GroupLayout(this);
       this.setLayout(layout);
       layout.setHorizontalGroup(
		   layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                   .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                		   .addComponent(jLabelServer)
                		   .addComponent(jLabelPassword)
                           .addComponent(jLabelWorkspaceVersion)
                           .addComponent(jLabelWorkspaceName)
                           .addComponent(jLabelDatabaseName)
                           .addComponent(jLabelUserName))
                       .addGap(20, 20, 20)
                       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)                		   
                           .addComponent(jComboBoxServer)
                           .addComponent(jTextFieldDatabaseName)
                           .addComponent(jTextFieldUserName)
                           .addComponent(passwordField)
                           .addComponent(jComboBoxWorkspaceName)
                           .addComponent(jComboBoxWorkspaceVersion))))
               .addContainerGap())
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabelServer)
                   .addComponent(jComboBoxServer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(jTextFieldDatabaseName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                   .addComponent(jLabelDatabaseName))
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(jTextFieldUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                   .addComponent(jLabelUserName))
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                   .addComponent(jLabelPassword))
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(jComboBoxWorkspaceName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                   .addComponent(jLabelWorkspaceName))
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(jComboBoxWorkspaceVersion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                   .addComponent(jLabelWorkspaceVersion))
               .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );
   }// </editor-fold>                        


   // Variables declaration - do not modify                     
   private JComboBox<String> jComboBoxServer;
   private JComboBox<String> jComboBoxWorkspaceName;
   private JComboBox<String> jComboBoxWorkspaceVersion;
   private JLabel jLabelDatabaseName;
   private JLabel jLabelPassword;
   private JLabel jLabelServer;
   private JLabel jLabelUserName;
   private JLabel jLabelWorkspaceName;
   private JLabel jLabelWorkspaceVersion;
   private JTextField jTextFieldDatabaseName;
   private JPasswordField passwordField;
   private JTextField jTextFieldUserName;
   // End of variables declaration  

public JComboBox<String> getjComboBoxServer() {
	return jComboBoxServer;
}

public void setjComboBoxServer(JComboBox<String> jComboBoxServer) {
	this.jComboBoxServer = jComboBoxServer;
}

public JComboBox<String> getjComboBoxWorkspaceName() {
	return jComboBoxWorkspaceName;
}

public void setjComboBoxWorkspaceName(JComboBox<String> jComboBoxWorkspaceName) {
	this.jComboBoxWorkspaceName = jComboBoxWorkspaceName;
}

public JComboBox<String> getjComboBoxWorkspaceVersion() {
	return jComboBoxWorkspaceVersion;
}

public void setjComboBoxWorkspaceVersion(JComboBox<String> jComboBoxWorkspaceVersion) {
	this.jComboBoxWorkspaceVersion = jComboBoxWorkspaceVersion;
}

public JTextField getjTextFieldDatabaseName() {
	return jTextFieldDatabaseName;
}

public void setjTextFieldDatabaseName(JTextField jTextFieldDatabaseName) {
	this.jTextFieldDatabaseName = jTextFieldDatabaseName;
}

public JPasswordField getjTextFieldPassword() {
	return passwordField;
}

public void setjTextFieldPassword(JPasswordField passwordField) {
	this.passwordField = passwordField;
}

public JTextField getjTextFieldUserName() {
	return jTextFieldUserName;
}

public void setjTextFieldUserName(JTextField jTextFieldUserName) {
	this.jTextFieldUserName = jTextFieldUserName;
}
   
}
