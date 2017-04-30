package conditionService;

import models.Datatype;
import parser.StringToData;

public class ComparisonMaster {
    
    /**
     * Default constructor.
     */
	private ComparisonMaster() {
	}
	
	/**
	 * compares two cells according to their common datatype.
	 * @param data1 the data in the first cell.
	 * @param data2 the data in the second cell.
	 * @param datatype their common datatype.
	 * @return 0 when they are equal, 1 when data1 is greater than data2
	 * and -1 when data1 is smaller than data2.
	 */
	public static int compareTo(String data1, String data2, Datatype datatype) {
		switch(datatype) {
		case VARCHAR:
			return data1.compareTo(data2);
		case INTEGER:
			return compareTwoIntegerCells(data1, data2);
		case FLOAT:
			return compareTwoFloatCells(data1, data2);
		case DATE:
			return StringToData.stringToDate(data1).compareTo(
					StringToData.stringToDate(data2));
		default:
			return 0;
		}
	}

    /**
     * compare two cells data which contain floats.
     * @param data1 the data in the first cell.
     * @param data2 the data in the second cell.
     * @return 0 when they are equal, 1 when data1 is greater than data2
     * and -1 when data1 is smaller than data2.
     */
    private static int compareTwoFloatCells(String data1, String data2) {
        float floatData1 = Float.parseFloat(data1);
        float floatData2 = Float.parseFloat(data2);
        if (floatData1 == floatData2) {
        	return 0;
        } else if (floatData1 > floatData2) {
        	return 1;
        } else {
        	return -1;
        }
    }

    /**
     * compare two cells data which contain integers.
     * @param data1 the data in the first cell.
     * @param data2 the data in the second cell.
     * @return 0 when they are equal, 1 when data1 is greater than data2
     * and -1 when data1 is smaller than data2.
     */
    private static int compareTwoIntegerCells(String data1, String data2) {
        int intData1 = Integer.parseInt(data1);
        int intData2 = Integer.parseInt(data2);
        if (intData1 == intData2) {
        	return 0;
        } else if (intData1 > intData2) {
        	return 1;
        } else {
        	return -1;
        }
    }
}
