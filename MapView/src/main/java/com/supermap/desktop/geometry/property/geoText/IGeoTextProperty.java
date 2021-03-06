package com.supermap.desktop.geometry.property.geoText;

import com.supermap.data.Recordset;

import javax.swing.*;

public interface IGeoTextProperty {
	
	JPanel getPanel();
	
	public void reset(Recordset recordset);
	
	public void apply(Recordset recordset);
	
	public void enabled(boolean enabled);
	
	public void addGeoTextChangeListener(GeoInfoChangeListener l);

	public void removeGeoTextChangeListener(GeoInfoChangeListener l);

	void fireGeoTextChanged(boolean isModified);
	
	boolean isModified();
}
