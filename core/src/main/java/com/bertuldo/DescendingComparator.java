package com.bertuldo;

import java.util.Comparator;
import com.bertuldo.ValueModel;

public class DescendingComparator implements Comparator<ValueModel> {

    @Override
    public int compare(ValueModel valueModel1, ValueModel valueModel2) {
	return (valueModel2.getValue1() + "" + valueModel2.getValue2()).compareTo((valueModel1.getValue1()+ ""
										   + valueModel1.getValue2()));
    }
}
