package com.supermap.desktop.iml;

import com.supermap.data.conversion.*;
import com.supermap.desktop.Interface.IExportSettingFactory;

/**
 * Created by xie on 2016/10/28.
 */
public class ExportSettingFactory implements IExportSettingFactory {
    @Override
    public ExportSetting createExportSetting(FileType fileType) {
        ExportSetting result = new ExportSetting();
        if (fileType.equals(FileType.BMP)) {
            result = new ExportSettingBMP();
        } else if (fileType.equals(FileType.DWG)) {
            result = new ExportSettingDWG();
        } else if (fileType.equals(FileType.DXF)) {
            result = new ExportSettingDXF();
        } else if (fileType.equals(FileType.GIF)) {
            result = new ExportSettingGIF();
        } else if (fileType.equals(FileType.JPG)) {
            result = new ExportSettingJPG();
        } else if (fileType.equals(FileType.KML)) {
            result = new ExportSettingKML();
        } else if (fileType.equals(FileType.KMZ)) {
            result = new ExportSettingKMZ();
        } else if (fileType.equals(FileType.ModelX)) {
            result = new ExportSettingModelX();
        } else if (fileType.equals(FileType.PNG)) {
            result = new ExportSettingPNG();
        } else if (fileType.equals(FileType.SIT)) {
            result = new ExportSettingSIT();
        } else if (fileType.equals(FileType.TEMSVector)) {
            result = new ExportSettingTEMSVector();
        } else if (fileType.equals(FileType.TEMSClutter)) {
            result = new ExportSettingTEMSClutter();
        } else if (fileType.equals(FileType.TEMSBuildingVector)) {
            result = new ExportSettingTEMSBuildingVector();
        } else if (fileType.equals(FileType.TIF)) {
            result = new ExportSettingTIF();
        } else if (fileType.equals(FileType.VCT)) {
            result = new ExportSettingVCT();
        } else if (fileType.equals(FileType.CSV)) {
            result = new ExportSettingCSV();
        }
        // 复制目标文件路径到新的exportsetting中
        return result;
    }
}
