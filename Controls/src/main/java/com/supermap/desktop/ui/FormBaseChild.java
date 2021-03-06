package com.supermap.desktop.ui;

import com.supermap.desktop.Application;
import com.supermap.desktop.Interface.IForm;
import com.supermap.desktop.Interface.IFormMain;
import com.supermap.desktop.enums.WindowType;
import com.supermap.desktop.event.*;
import com.supermap.desktop.implement.SmStatusbar;
import com.supermap.desktop.ui.controls.DockbarManager;
import com.supermap.desktop.ui.mdi.MdiGroup;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;

public abstract class FormBaseChild extends JPanel implements IForm {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SmStatusbar statusbar;

	private FormEventHelper eventHelper = new FormEventHelper();
	private String title;
	private Icon icon;

	public FormBaseChild(String title, Icon icon, Component component) {
		setLayout(new BorderLayout());
		this.statusbar = createStatusbar();
		this.title = title;
		this.icon = icon;
	}

	@Override
	public String getText() {
		return this.title;
	}

	@Override
	public void setText(String text) {
		this.title = text;
	}

	public Icon getIcon() {
		return this.icon;
	}

	@Override
	public WindowType getWindowType() {
		return WindowType.UNKNOWN;
	}

	@Override
	public boolean isActivated() {
		boolean result = false;
		try {
			IForm childForm = Application.getActiveApplication().getActiveForm();
			if (childForm.equals(this)) {
				result = true;
			}
		} catch (Exception ex) {
			Application.getActiveApplication().getOutput().output(ex);
		}
		return result;
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(boolean notify, boolean newWindow) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveAs(boolean isNewWindow) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNeedSave() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setNeedSave(boolean needSave) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean saveFormInfos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actived() {
		// 默认实现,后续进行初始化操作
	}

	@Override
	public void deactived() {
		// 默认实现,后续进行初始化操作
	}

	@Override
	public void formShown(FormShownEvent e) {
		// 默认实现,后续进行初始化操作
	}

	@Override
	public void formClosing(FormClosingEvent e) {
		// 默认实现,后续进行初始化操作
	}

	@Override
	public void formClosed(FormClosedEvent e) {
		// 默认实现,后续进行初始化操作
	}

	/**
	 * 先执行 formClosing，再调用事件
	 *
	 * @param listener
	 */
	@Override
	public void addFormClosingListener(FormClosingListener listener) {
		this.eventHelper.addFormClosingListener(listener);
	}

	/**
	 * 先执行 formClosed，再调用事件
	 *
	 * @param listener
	 */
	@Override
	public void removeFormClosingListener(FormClosingListener listener) {
		this.eventHelper.removeFormClosingListener(listener);
	}

	/**
	 * 先执行 FormClosed，再调用事件
	 *
	 * @param listener
	 */
	@Override
	public void addFormClosedListener(FormClosedListener listener) {
		this.eventHelper.addFormClosedListener(listener);
	}

	@Override
	public void removeFormClosedListener(FormClosedListener listener) {
		this.eventHelper.removeFormClosedListener(listener);
	}

	@Override
	public void addFormShownListener(FormShownListener listener) {
		this.eventHelper.addFormShownListener(listener);
	}

	@Override
	public void removeFormShownListener(FormShownListener listener) {
		this.eventHelper.removeFormShownListener(listener);
	}

	void fireFormClosing(FormClosingEvent e) {
		this.eventHelper.fireFormClosing(e);
	}

	void fireFormClosed(FormClosedEvent e) {
		this.eventHelper.fireFormClosed(e);
	}

	void fireFormShown(FormShownEvent e) {
		this.eventHelper.fireFormShown(e);
	}

	private SmStatusbar createStatusbar() {
		SmStatusbar smstatusbar = null;
		Class<?> formClass = this.getClass();
		IFormMain formMain = Application.getActiveApplication().getMainFrame();
		StatusbarManager statusbarManager = (StatusbarManager) formMain.getStatusbarManager();
		if (formClass != null) {
			smstatusbar = statusbarManager.getStatusbar(formClass.getName());
			if (smstatusbar != null) {
				add(smstatusbar, BorderLayout.SOUTH);
				smstatusbar.build(this);
			}
		}
		return smstatusbar;
	}

	public SmStatusbar getStatusbar() {
		return statusbar;
	}

	public void setStatusbar(SmStatusbar statusbar) {
		this.statusbar = statusbar;
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isClosed() {
		// 默认的 FormBaseChild 是已 JPanel 的形式添加到 FormManager 里的
		// 关闭之后会从 MdiGroup 里移除，以后支持了 Floating 特性之后，再进行浮动的判断即可
		return !(getParent() instanceof MdiGroup);
	}
}
