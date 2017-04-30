package fileManager;

import exceptions.UnknownExtension;

/**.
 *
 * @author Marc Magdi
 *
 */
public class FileWriterFactoryImp implements FileWriterFactory {

	/**.
	 * A xml file writer to be served as one object
	 */
	private static XMLFileWriter xmlFileWriter;

	/**.
	 * A json file writer to be served as one object
	 * */
	private static JsonFileWriter jsonFileWriter;

	/**.
	 * A proto file writer to be served as one object
	 * */
	private static ProtoFileWriter protoFileWriter;

	@Override
	public final FileWriter getSaveHelper(final SaveExtension extension)
				throws UnknownExtension {
		FileWriter helper = null;
		switch (extension) {
			case XML:
				helper = getXmlWriter();
				break;
			case JSON:
				helper = getJsonWriter();
				break;
			case PROTO:
				helper = getProtoWriter();
				break;
			default:
				throw new UnknownExtension();
		}

		return helper;
	}

	/**.
	 * Serve the xml file writer as singelton
	 * @return an object of the xml writer
	 */
	private static FileWriter getXmlWriter() {
		if (xmlFileWriter == null) {
			xmlFileWriter = new XMLFileWriter();
		}

		return xmlFileWriter;
	}

	/**.
	 * Serve the json file writer as singelton
	 * @return an object of the json writer
	 */
	private static FileWriter getJsonWriter() {
		if (jsonFileWriter == null) {
			jsonFileWriter = new JsonFileWriter();
		}

		return jsonFileWriter;
	}

	/**.
	 * Serve the xml file writer as singelton
	 * @return an object of the xml writer
	 */
	private static FileWriter getProtoWriter() {
		if (protoFileWriter == null) {
			protoFileWriter = new ProtoFileWriter();
		}

		return protoFileWriter;
	}

}
