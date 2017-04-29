package eg.edu.alexu.csd.oop.paint.fileManager;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

import eg.edu.alexu.csd.oop.paint.abstractComponents.GeoShapes;
import eg.edu.alexu.csd.oop.paint.commandHandler.CommandManager;
import eg.edu.alexu.csd.oop.paint.drawComponents.DrawingData;
import eg.edu.alexu.csd.oop.paint.helpers.Config;
import eg.edu.alexu.csd.oop.paint.interfaces.IDrawingDataCore;
import eg.edu.alexu.csd.oop.paint.interfaces.IPaintSaver;

/**
 * Our implementation of the IPaintSave interface.
 * @author Amr&Mico
 *
 */
public class WriteReadFile implements IPaintSaver {
	@Override
	public final void writeXml(final IDrawingDataCore data,
			final File saveName)
			throws Exception {
		XStream xstream = new XStream(new DomDriver());
		 xstream.setMode(XStream.NO_REFERENCES);
	     xstream.alias("dataCore", Config.getDefaultIdrawingDataCore());
	     xstream.toXML(data, new FileWriter(saveName));
		
    }
	@Override
	public final IDrawingDataCore readXml(final ClassLoader cl,
			final File loadFile) throws Exception {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("dataCore", Config.getDefaultIdrawingDataCore());
		xstream.setClassLoader(cl);
		return (IDrawingDataCore) xstream.fromXML(loadFile);
    }
	@Override
	public final void writeJson(final IDrawingDataCore f,
			final File filename)
			throws Exception {
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.alias("dataCore", Config.getDefaultIdrawingDataCore());
        xstream.toXML(f, new FileWriter(filename));
	}
	@Override
	public final IDrawingDataCore readJson(final ClassLoader cl,
			final File loadFile) throws Exception {
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.alias("dataCore", Config.getDefaultIdrawingDataCore());
		xstream.setClassLoader(cl);
		IDrawingDataCore core = (IDrawingDataCore) xstream.fromXML(loadFile);
		int i = core.getHistory().getHistoryIndex();
		core.getHistory().setHistoryIndex(0);
		core.setShapes(new ArrayList<GeoShapes>());
		while (i > 0) {
			core.redo();
			i--;
		}
		return core;
    }
}
